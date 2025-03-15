package com.barberShop.scheduling.service;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.dto.request.AgendaRequest;
import com.barberShop.scheduling.dto.response.AgendaCancelarResponse;
import com.barberShop.scheduling.dto.response.AgendaResponse;
import com.barberShop.scheduling.enums.JornadaEnum;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.mapper.AgendaMapper;
import com.barberShop.scheduling.repository.AgendaRepository;
import com.barberShop.scheduling.utils.AgendaUtils;
import com.barberShop.scheduling.utils.AgendaValidation;
import com.barberShop.scheduling.utils.ValidacaoResultado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<AgendaResponse> gerarAgenda(AgendaRequest request) {
        ValidacaoResultado validacao = validation.validarProfissionalEBarbearia(
                request.getCpfProfissional(),
                request.getCnpjBarbearia(),
                request.getServicosBarbeariaId()
        );
        validation.verificarConflitoDeAgenda(request.getCpfProfissional(), request.getDate(), request.getTime());

        List<Agenda> agendaEntities = agendaUtils.gerarHorarios(
                validacao.getProfissional(),
                validacao.getBarbearia(),
                validacao.getServicosBarbearia(),
                request.getDate(),
                LocalTime.of(9, 0),
                LocalTime.of(22, 0),
                30,
                JornadaEnum.MANHA
        );
        agendaRepository.saveAll(agendaEntities);
        return agendaMapper.convertEntityListToResponseList(agendaEntities);
    }

    public List<AgendaResponse> gerarAgendaDaManha(AgendaRequest request) {
        ValidacaoResultado validacao = validation.validarProfissionalEBarbearia(
                request.getCpfProfissional(),
                request.getCnpjBarbearia(),
                request.getServicosBarbeariaId()
        );
        validation.verificarConflitoDeAgenda(request.getCpfProfissional(), request.getDate(), request.getTime());

        List<Agenda> agendaEntities = agendaUtils.gerarHorarios(
                validacao.getProfissional(),
                validacao.getBarbearia(),
                validacao.getServicosBarbearia(),
                request.getDate(),
                LocalTime.of(9, 0),
                LocalTime.of(12, 0),
                30,
                JornadaEnum.MANHA
        );
        agendaRepository.saveAll(agendaEntities);
        return agendaMapper.convertEntityListToResponseList(agendaEntities);
    }

    public List<AgendaResponse> gerarAgendaDaTarde(AgendaRequest request) {
        ValidacaoResultado validacao = validation.validarProfissionalEBarbearia(
                request.getCpfProfissional(),
                request.getCnpjBarbearia(),
                request.getServicosBarbeariaId()
        );
        validation.verificarConflitoDeAgenda(request.getCpfProfissional(), request.getDate(), request.getTime());

        List<Agenda> agendaEntities = agendaUtils.gerarHorarios(
                validacao.getProfissional(),
                validacao.getBarbearia(),
                validacao.getServicosBarbearia(),
                request.getDate(),
                LocalTime.of(13, 0),
                LocalTime.of(19, 0),
                30,
                JornadaEnum.TARDE
        );
        agendaRepository.saveAll(agendaEntities);
        return agendaMapper.convertEntityListToResponseList(agendaEntities);
    }

    public List<AgendaResponse> gerarAgendaDaNoite(AgendaRequest request) {
        ValidacaoResultado validacao = validation.validarProfissionalEBarbearia(
                request.getCpfProfissional(),
                request.getCnpjBarbearia(),
                request.getServicosBarbeariaId()
        );
        validation.verificarConflitoDeAgenda(request.getCpfProfissional(), request.getDate(), request.getTime());

        List<Agenda> agendaEntities = agendaUtils.gerarHorarios(
                validacao.getProfissional(),
                validacao.getBarbearia(),
                validacao.getServicosBarbearia(),
                request.getDate(),
                LocalTime.of(19, 0),
                LocalTime.of(22, 0),
                30,
                JornadaEnum.NOITE
        );
        agendaRepository.saveAll(agendaEntities);
        return agendaMapper.convertEntityListToResponseList(agendaEntities);
    }

    public void criarAgendaAvulso(AgendaRequest request) {
        ValidacaoResultado validacao = validation.validarProfissionalEBarbearia(
                request.getCpfProfissional(),
                request.getCnpjBarbearia(),
                request.getServicosBarbeariaId()
        );
        validation.verificarConflitoDeAgenda(request.getCpfProfissional(), request.getDate(), request.getTime());
        JornadaEnum jornada = determinarJornada(request.getTime());
        Agenda agenda = Agenda.builder()
                .profissional(validacao.getProfissional())
                .barbearia(validacao.getBarbearia())
                .servicosBarbearia(validacao.getServicosBarbearia())
                .date(request.getDate())
                .time(request.getTime())
                .jornada(jornada)
                .statusAgenda(StatusAgenda.ABERTO)
                .build();
        agendaRepository.save(agenda);
    }

    @Transactional
    public AgendaCancelarResponse cancelarAgenda(UUID agendaId) {
        var agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new ServicosBarbeariaException("Agenda not found"));
        if (agenda.getStatusAgenda() == StatusAgenda.CANCELADO) {
            throw new ServicosBarbeariaException("A agenda já está cancelada");
        }
        agenda.setStatusAgenda(StatusAgenda.CANCELADO);
        agendaRepository.save(agenda);

        return AgendaMapper.INSTANCE.convertEntityToCancelarResponse(agenda);
    }

    public List<AgendaResponse> buscarAgendaPorStatus(StatusAgenda status) {
        List<Agenda> agendaList = agendaRepository.findByStatusAgenda(status);
        return agendaMapper.convertEntityListToResponseList(agendaList);
    }

    private JornadaEnum determinarJornada(LocalTime time) {
        if (time.isBefore(LocalTime.of(12, 0))) {
            return JornadaEnum.MANHA;
        } else if (time.isBefore(LocalTime.of(19, 0))) {
            return JornadaEnum.TARDE;
        } else {
            return JornadaEnum.NOITE;
        }
    }
}