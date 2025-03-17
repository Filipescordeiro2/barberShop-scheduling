package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.repository.AgendaRepository;
import com.barberShop.scheduling.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BarbeariaUtils {

    private final AgendaRepository agendaRepository;
    private final AgendamentoRepository agendamentoRepository;

    public void cancelAgendasAndAgendamentos(String cnpj) {
        var agendas = agendaRepository.findByBarbeariaCnpj(cnpj);
        agendas.forEach(agenda -> {
            agenda.setStatusAgenda(StatusAgenda.CANCELADO);
            agendaRepository.save(agenda);
        });

        var agendamentos = agendamentoRepository.findByAgenda_Barbearia_cnpj(cnpj);
        agendamentos.forEach(agendamento -> {
            agendamento.setStatus(StatusAgenda.CANCELADO);
            agendamentoRepository.save(agendamento);
        });
    }
}
