package com.barberShop.scheduling.utils.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.enums.CancelType;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.repository.agenda.AgendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendaCancelManagerImpl implements AgendaCancelManager {

    private final AgendaRepository agendaRepository;

    @Override
    public void cancelAgendas(String identifier, CancelType type) {
        log.info("Inicializado o metodo de [cancelAgendas] - Identifier: {}, CancelType: {}", identifier, type);
        List<Agenda> agendas;

        // Verifica o tipo de cancelamento (por profissional ou barbearia)
        if (type == CancelType.PROFISSIONAL) {
            agendas = agendaRepository.findByProfissionalCpf(identifier);
        } else if (type == CancelType.BARBEARIA) {
            agendas = agendaRepository.findByBarbeariaCnpj(identifier);
        } else {
            throw new IllegalArgumentException("Invalid CancelType");
        }

        // Cancela todas as agendas encontradas
        agendas.forEach(this::cancelAgenda);
        log.info("Finalizado o metodo de [cancelAgendas] - Agendas Canceladas: {}", agendas);
    }

    @Override
    public void cancelAgenda(Agenda agenda) {
        log.info("Inicializado o metodo de [cancelAgenda] - Agenda ID: {}", agenda.getId());
        agenda.setStatusAgenda(StatusAgenda.CANCELADO);
        agendaRepository.save(agenda);
        log.info("Finalizado o metodo de [cancelAgenda] - Agenda Cancelada: {}", agenda);
    }
}