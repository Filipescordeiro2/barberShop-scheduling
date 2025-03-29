package com.barberShop.scheduling.utils.agendamento;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.domain.Cliente;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.repository.agendamento.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendamentoCanceladorImpl implements AgendamentoCancelador {

        private final AgendamentoRepository agendamentoRepository;

    @Override
    public void cancelarAgendamentosCliente(Cliente cliente) {
        log.info("Inicializado o metodo de [cancelarAgendamentosCliente] - Cliente: {}",cliente);
        var agendamentos = agendamentoRepository.findByCliente(cliente);
        agendamentos.forEach(agendamento -> {
            agendamento.setStatus(StatusAgenda.CANCELADO);
        });
        log.info("Finalizado o metodo de [cancelarAgendamentosCliente] - Agendamentos Cancelados: {}",agendamentos);
    }

    @Override
    public void cancelarAgendamentosProfissional(String cpfProfissional) {
        log.info("Inicializado o metodo de [cancelarAgendamentosProfissional] - CPF Profissional: {}", cpfProfissional);
        var agendamentos = agendamentoRepository.findByAgenda_Profissional_Cpf(cpfProfissional);
        agendamentos.forEach(agendamento -> {
            agendamento.setStatus(StatusAgenda.CANCELADO);
            agendamentoRepository.save(agendamento);
        });
        log.info("Finalizado o metodo de [cancelarAgendamentosProfissional] - Agendamentos Cancelados: {}", agendamentos);
    }

    @Override
    public void cancelarAgendamentosBarbearia(String cnpjBarbearia) {
        log.info("Inicializado o metodo de [cancelarAgendamentosBarbearia] - CNPJ Barbearia: {}", cnpjBarbearia);
        var agendamentos = agendamentoRepository.findByAgenda_Barbearia_cnpj(cnpjBarbearia);
        agendamentos.forEach(agendamento -> {
            agendamento.setStatus(StatusAgenda.CANCELADO);
            agendamentoRepository.save(agendamento);
        });
        log.info("Finalizado o metodo de [cancelarAgendamentosBarbearia] - Agendamentos Cancelados: {}", agendamentos);
    }

    @Override
    public void cancelarAgendamentoForAgenda(Agenda agenda) {
        log.info("Inicializado o metodo de [cancelarAgendamentoForAgenda] - Agenda: {}", agenda);
        var appointments = agendamentoRepository.findByAgenda(agenda);
        if (!appointments.isEmpty()) {
            appointments.stream().forEach(appointment -> {
                appointment.setStatus(StatusAgenda.CANCELADO);
                agendamentoRepository.save(appointment);
            });
        }
        log.info("Finalizado o metodo de [cancelarAgendamentoForAgenda] - Agendamentos Cancelados: {}", appointments);
    }
}
