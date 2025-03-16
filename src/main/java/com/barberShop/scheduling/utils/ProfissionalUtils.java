package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.repository.AgendaRepository;
import com.barberShop.scheduling.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfissionalUtils {

    private final AgendaRepository agendaRepository;
    private final AgendamentoRepository agendamentoRepository;

    public void cancelAgendas(String cpfProfissional) {
        var agendas = agendaRepository.findByProfissionalCpf(cpfProfissional);
        agendas.forEach(agenda -> {
            agenda.setStatusAgenda(StatusAgenda.CANCELADO);
            agendaRepository.save(agenda);
        });
    }

    public void cancelAgendamentos(String cpfProfissional) {
        var agendamentos = agendamentoRepository.findByAgenda_Profissional_Cpf(cpfProfissional);
        agendamentos.forEach(agendamento -> {
            agendamento.setStatus(StatusAgenda.CANCELADO);
            agendamentoRepository.save(agendamento);
        });
    }
}
