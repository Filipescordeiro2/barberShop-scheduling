package com.barberShop.scheduling.service;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.dto.request.AgendaRequest;
import com.barberShop.scheduling.dto.response.AgendaCancelarResponse;
import com.barberShop.scheduling.dto.response.AgendaResponse;
import com.barberShop.scheduling.enums.JornadaEnum;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.AgendamentoException;
import com.barberShop.scheduling.mapper.AgendaMapper;
import com.barberShop.scheduling.repository.AgendaRepository;
import com.barberShop.scheduling.utils.AgendaUtils;
import com.barberShop.scheduling.utils.AgendaValidation;
import com.barberShop.scheduling.utils.ValidacaoResultado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final AgendaValidation validation;
    private final AgendaUtils agendaUtils;
    private final AgendaMapper agendaMapper = AgendaMapper.INSTANCE;

    public List<AgendaResponse> generateSchedule(AgendaRequest request) {
        ValidacaoResultado validationResult = validation.validateProfessionalAndBarberShop(
                request.getCpfProfissional(),
                request.getCnpjBarbearia(),
                request.getServicosBarbeariaId()
        );
        validation.checkScheduleConflict(request.getCpfProfissional(), request.getDate(), request.getTime());

        List<Agenda> agendaEntities = agendaUtils.generateSchedules(
                validationResult.getProfissional(),
                validationResult.getBarbearia(),
                validationResult.getServicosBarbearia(),
                request.getDate(),
                LocalTime.of(9, 0),
                LocalTime.of(22, 0),
                30,
                JornadaEnum.MANHA
        );
        agendaRepository.saveAll(agendaEntities);
        return agendaMapper.convertEntityListToResponseList(agendaEntities);
    }

    public List<AgendaResponse> generateMorningSchedule(AgendaRequest request) {
        ValidacaoResultado validationResult = validation.validateProfessionalAndBarberShop(
                request.getCpfProfissional(),
                request.getCnpjBarbearia(),
                request.getServicosBarbeariaId()
        );
        validation.checkScheduleConflict(request.getCpfProfissional(), request.getDate(), request.getTime());

        List<Agenda> agendaEntities = agendaUtils.generateSchedules(
                validationResult.getProfissional(),
                validationResult.getBarbearia(),
                validationResult.getServicosBarbearia(),
                request.getDate(),
                LocalTime.of(9, 0),
                LocalTime.of(12, 0),
                30,
                JornadaEnum.MANHA
        );
        agendaRepository.saveAll(agendaEntities);
        return agendaMapper.convertEntityListToResponseList(agendaEntities);
    }

    public List<AgendaResponse> generateAfternoonSchedule(AgendaRequest request) {
        ValidacaoResultado validationResult = validation.validateProfessionalAndBarberShop(
                request.getCpfProfissional(),
                request.getCnpjBarbearia(),
                request.getServicosBarbeariaId()
        );
        validation.checkScheduleConflict(request.getCpfProfissional(), request.getDate(), request.getTime());

        List<Agenda> agendaEntities = agendaUtils.generateSchedules(
                validationResult.getProfissional(),
                validationResult.getBarbearia(),
                validationResult.getServicosBarbearia(),
                request.getDate(),
                LocalTime.of(13, 0),
                LocalTime.of(19, 0),
                30,
                JornadaEnum.TARDE
        );
        agendaRepository.saveAll(agendaEntities);
        return agendaMapper.convertEntityListToResponseList(agendaEntities);
    }

    public List<AgendaResponse> generateEveningSchedule(AgendaRequest request) {
        ValidacaoResultado validationResult = validation.validateProfessionalAndBarberShop(
                request.getCpfProfissional(),
                request.getCnpjBarbearia(),
                request.getServicosBarbeariaId()
        );
        validation.checkScheduleConflict(request.getCpfProfissional(), request.getDate(), request.getTime());

        List<Agenda> agendaEntities = agendaUtils.generateSchedules(
                validationResult.getProfissional(),
                validationResult.getBarbearia(),
                validationResult.getServicosBarbearia(),
                request.getDate(),
                LocalTime.of(19, 0),
                LocalTime.of(22, 0),
                30,
                JornadaEnum.NOITE
        );
        agendaRepository.saveAll(agendaEntities);
        return agendaMapper.convertEntityListToResponseList(agendaEntities);
    }

    public void createSingleSchedule(AgendaRequest request) {
        ValidacaoResultado validationResult = validation.validateProfessionalAndBarberShop(
                request.getCpfProfissional(),
                request.getCnpjBarbearia(),
                request.getServicosBarbeariaId()
        );
        validation.checkScheduleConflict(request.getCpfProfissional(), request.getDate(), request.getTime());
        JornadaEnum shift = determineShift(request.getTime());
        Agenda agenda = Agenda.builder()
                .profissional(validationResult.getProfissional())
                .barbearia(validationResult.getBarbearia())
                .servicosBarbearia(validationResult.getServicosBarbearia())
                .date(request.getDate())
                .time(request.getTime())
                .jornada(shift)
                .statusAgenda(StatusAgenda.ABERTO)
                .build();
        agendaRepository.save(agenda);
    }

    @Transactional
    public AgendaCancelarResponse cancelSchedule(UUID agendaId) {
        var agenda = validation.validateScheduleExists(agendaId);

        validation.validateScheduleNotCancelled(agenda);
        agendaUtils.cancelAppointments(agenda);
        agendaUtils.cancelSchedule(agenda);

        return agendaMapper.convertEntityToCancelarResponse(agenda);
    }

    public List<AgendaResponse> findSchedulesByStatus(StatusAgenda status) {
        List<Agenda> agendaList = agendaRepository.findByStatusAgenda(status);
        return agendaMapper.convertEntityListToResponseList(agendaList);
    }

    public List<AgendaResponse> findByProfissionalAndStatusAgenda(String cpf, StatusAgenda statusAgenda,
                                                                  LocalDate startDate, LocalDate endDate) {
        var agendaList = agendaUtils.findAgendaFiltro(cpf, statusAgenda, startDate, endDate);
        return agendaMapper.convertEntityListToResponseList(agendaList);
    }

    private JornadaEnum determineShift(LocalTime time) {
        if (time.isBefore(LocalTime.of(12, 0))) {
            return JornadaEnum.MANHA;
        } else if (time.isBefore(LocalTime.of(19, 0))) {
            return JornadaEnum.TARDE;
        } else {
            return JornadaEnum.NOITE;
        }
    }
}