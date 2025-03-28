package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.domain.Agendamento;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.repository.AgendaRepository;
import com.barberShop.scheduling.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AgendamentoUtils {

    private final AgendaRepository agendaRepository;
    private final AgendamentoRepository agendamentoRepository;

    public void updateAgendaStatusToOpen(UUID agendaId) {
        agendaRepository.findById(agendaId).ifPresent(agenda -> {
            agenda.setStatusAgenda(StatusAgenda.ABERTO);
            agendaRepository.save(agenda);
        });
    }

    public void cancelAppointment(Agendamento appointment) {
        appointment.setStatus(StatusAgenda.CANCELADO);
        agendamentoRepository.save(appointment);
    }
}
