package com.barberShop.scheduling.service.agendamento;

import com.barberShop.scheduling.domain.Agendamento;
import com.barberShop.scheduling.dto.request.agendamento.AgendamentoRequest;
import com.barberShop.scheduling.dto.response.agendamento.AgendamentoResponse;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.AgendaException;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.mapper.agendamento.AgendamentoMapper;
import com.barberShop.scheduling.repository.agenda.AgendaRepository;
import com.barberShop.scheduling.repository.agendamento.AgendamentoRepository;
import com.barberShop.scheduling.repository.cliente.ClienteRepository;
import com.barberShop.scheduling.validation.agenda.AgendaValidation;
import com.barberShop.scheduling.validation.barbearia.BarbeariaValidation;
import com.barberShop.scheduling.validation.profissional.ProfissionalValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendamentoCreateAppointmentService {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendaRepository agendaRepository;
    private final ClienteRepository clienteRepository;
    private final BarbeariaValidation barbeariaValidation;
    private final ProfissionalValidation profissionalValidation;
    private final AgendaValidation agendaValidation;

    @Transactional
    public AgendamentoResponse createAppointment(AgendamentoRequest request) {
        log.info("Inicializado o metodo de [createAppointment] - Request: {}", request);

        var agenda = agendaRepository.findById(request.getAgendaId())
                .orElseThrow(() -> {
                    return new AgendaException("Agenda Not Found");
                });

        var client = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> {
                    return new ClienteException("Cliente Not Found");
                });

        profissionalValidation.validateProfissionalIsActive(agenda.getProfissional());
        barbeariaValidation.validateBarbeariaIsActive(agenda.getBarbearia());

        var appointment = new Agendamento();
        appointment.setAgenda(agenda);
        appointment.setCliente(client);
        agendamentoRepository.save(appointment);

        agenda.setStatusAgenda(StatusAgenda.AGENDADO);
        agendaRepository.save(agenda);

        log.info("Finalizado o metodo de [createAppointment] - Appointment ID: {}", appointment.getId());
        return AgendamentoMapper.INSTANCE.convertEntityToResponse(appointment);
    }
}