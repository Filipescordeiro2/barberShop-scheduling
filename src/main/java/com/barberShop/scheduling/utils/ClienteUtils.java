package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.domain.Cliente;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.repository.AgendaRepository;
import com.barberShop.scheduling.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteUtils {


    private final AgendamentoRepository agendamentoRepository;
    private final AgendaRepository agendaRepository;


    public void deseableAgendamento(Cliente cliente){
        var agendamentos = agendamentoRepository.findByCliente(cliente);
        agendamentos.forEach(agendamento -> {
            agendamento.setStatus(StatusAgenda.CANCELADO);
            var agenda = agendaRepository.findById(agendamento.getAgenda().getId())
                    .orElseThrow(() -> new ClienteException("Agenda not found with ID: " +
                            agendamento.getAgenda().getId()));
            agenda.setStatusAgenda(StatusAgenda.ABERTO);
            agendaRepository.save(agenda);
        });
    }
}
