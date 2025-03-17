package com.barberShop.scheduling.service;

import com.barberShop.scheduling.domain.*;
import com.barberShop.scheduling.dto.request.AgendamentoRequest;
import com.barberShop.scheduling.dto.response.AgendamentoCancelarResponse;
import com.barberShop.scheduling.dto.response.AgendamentoDetalhadaResponse;
import com.barberShop.scheduling.dto.response.AgendamentoResponse;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.mapper.AgendamentoMapper;
import com.barberShop.scheduling.repository.AgendaRepository;
import com.barberShop.scheduling.repository.AgendamentoRepository;
import com.barberShop.scheduling.utils.AgendamentoUtils;
import com.barberShop.scheduling.utils.AgendamentoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendaRepository agendaRepository;
    private final AgendamentoValidation agendamentoValidation;
    private final AgendamentoUtils agendamentoUtils;
    private final AgendamentoMapper agendamentoMapper = AgendamentoMapper.INSTANCE;

    @Transactional
    public AgendamentoResponse createAppointment(AgendamentoRequest request) {

        var agenda = agendamentoValidation.validateAgenda(request.getAgendaId());
        var client = agendamentoValidation.validateClient(request.getClienteId());

        agendamentoValidation.validateAgendaStatus(agenda);
        agendamentoValidation.validateProfessionalStatus(agenda.getProfissional());
        agendamentoValidation.validateBarberShopStatus(agenda.getBarbearia());

        var appointment = new Agendamento();
        appointment.setAgenda(agenda);
        appointment.setCliente(client);
        agendamentoRepository.save(appointment);

        agenda.setStatusAgenda(StatusAgenda.AGENDADO);
        agendaRepository.save(agenda);

        return agendamentoMapper.convertEntityToResponse(appointment);
    }

    @Transactional
    public List<AgendamentoDetalhadaResponse> getDetailedAppointment(UUID agendaId) {

        var agenda = agendamentoValidation.validateAgenda(agendaId);
        var appointments = agendamentoRepository.findByAgenda(agenda);

        return appointments.stream()
                .map(agendamentoMapper::convertEntityToDetalhadaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<AgendamentoDetalhadaResponse> getDetailedAppointmentByCpf(String cpf) {

        var client = agendamentoValidation.validateClientByCpf(cpf);
        var appointments = agendamentoRepository.findByCliente(client);

        return appointments.stream()
                .map(agendamentoMapper::convertEntityToDetalhadaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public AgendamentoCancelarResponse cancelAppointment(UUID appointmentId) {

        var appointment = agendamentoValidation.validateAppointmentExists(appointmentId);
        agendamentoValidation.validateAppointmentNotCancelled(appointment);
        agendamentoUtils.updateAgendaStatusToOpen(appointment.getAgenda().getId());
        agendamentoUtils.cancelAppointment(appointment);

        return agendamentoMapper.convertEntityToCancelarResponse(appointment);
    }
}