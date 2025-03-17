package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.domain.*;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.AgendamentoException;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AgendamentoValidation {

    private final AgendaRepository agendaRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ClienteRepository clienteRepository;
    private final BarbeariaRepository barbeariaRepository;
    private final AgendamentoRepository agendamentoRepository;

    public Agenda validateAgenda(UUID agendaId) {
        if (agendaId == null) {
            throw new IllegalArgumentException("Agenda ID cannot be null");
        }
        return agendaRepository.findById(agendaId)
                .orElseThrow(() -> new AgendamentoException("Agenda not found"));
    }

    public Cliente validateClient(String clientId) {
        return clienteRepository.findById(clientId)
                .orElseThrow(() -> new AgendamentoException("Client not found"));
    }

    public Cliente validateClientByCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("Client CPF cannot be null or empty");
        }
        return clienteRepository.findById(cpf)
                .orElseThrow(() -> new AgendamentoException("Client not found"));
    }

    public void validateAgendaStatus(Agenda agenda) {
        if (!agenda.getStatusAgenda().equals(StatusAgenda.ABERTO)) {
            throw new AgendamentoException("The agenda is not open for appointments");
        }
    }

    public void validateProfessionalStatus(Profissional professional) {
        if (!professional.isActive()) {
            throw new AgendamentoException("The professional is not active");
        }
    }

    public void validateBarberShopStatus(Barbearia barberShop) {
        if (!barberShop.isActive()) {
            throw new AgendamentoException("The barber shop is not active");
        }
    }


    public Agendamento validateAppointmentExists(UUID appointmentId) {
        return agendamentoRepository.findById(appointmentId)
                .orElseThrow(() -> new ServicosBarbeariaException("Appointment not found"));
    }

    public void validateAppointmentNotCancelled(Agendamento appointment) {
        if (appointment.getStatus() == StatusAgenda.CANCELADO) {
            throw new ServicosBarbeariaException("Appointment is already cancelled");
        }
    }
}