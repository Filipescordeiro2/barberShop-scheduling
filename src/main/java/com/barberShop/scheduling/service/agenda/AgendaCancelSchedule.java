package com.barberShop.scheduling.service.agenda;

import com.barberShop.scheduling.dto.response.agenda.AgendaCancelarResponse;
import com.barberShop.scheduling.exception.AgendaException;
import com.barberShop.scheduling.mapper.agenda.AgendaMapper;
import com.barberShop.scheduling.repository.agenda.AgendaRepository;
import com.barberShop.scheduling.utils.agenda.AgendaCancelManager;
import com.barberShop.scheduling.utils.agendamento.AgendamentoCancelador;
import com.barberShop.scheduling.validation.agenda.AgendaValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendaCancelSchedule {

    private final AgendaRepository agendaRepository;
    private final AgendaCancelManager agendaCancelManager;
    private final AgendamentoCancelador agendamentoCancelador;
    private final AgendaValidation validation;

    @Transactional
    public AgendaCancelarResponse cancelSchedule(UUID agendaId) {
        log.info("Inicializado o metodo de [cancelSchedule] - Agenda ID: {}", agendaId);

        var agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> {
                    return new AgendaException("Agenda Not Found");
                });
        validation.validate(agenda);

        agendaCancelManager.cancelAgenda(agenda);
        agendamentoCancelador.cancelarAgendamentoForAgenda(agenda);

        log.info("Finalizado o metodo de [cancelSchedule] - Agenda ID: {}", agendaId);
        return AgendaMapper.INSTANCE.convertEntityToCancelarResponse(agenda);
    }
}