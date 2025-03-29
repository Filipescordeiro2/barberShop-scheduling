package com.barberShop.scheduling.service.agenda;

import com.barberShop.scheduling.dto.request.agenda.AgendaRequestWithoutJornada;
import com.barberShop.scheduling.dto.response.agenda.AgendaResponse;
import com.barberShop.scheduling.enums.JornadaPeriod;
import com.barberShop.scheduling.exception.AgendaException;
import com.barberShop.scheduling.utils.agenda.AgendaCreatedManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendaGenerateScheduleService {

    private final AgendaCreatedManager agendaCreatedManager;

    public List<AgendaResponse> generateScheduleForPeriod(AgendaRequestWithoutJornada request) {
        log.info("Inicializado o metodo de [generateScheduleForPeriod] - Request: {}", request);
        try {
            JornadaPeriod period = JornadaPeriod.fromJornada(request.getJornadaEnum());
            List<AgendaResponse> response = agendaCreatedManager.generateScheduleForPeriod(
                    request,
                    period.getStartTime(),
                    period.getEndTime(),
                    period.getJornadaEnum()
            );
            log.info("Finalizado o metodo de [generateScheduleForPeriod] - Request: {}", request);
            return response;
        } catch (IllegalArgumentException e) {
            throw new AgendaException("Invalid JornadaEnum: " + request.getJornadaEnum());
        } catch (Exception e) {
            throw new AgendaException("Error in creating agenda: " + e.getMessage());
        }
    }

    public List<AgendaResponse> generateSchedule(AgendaRequestWithoutJornada request) {
        log.info("Inicializado o metodo de [generateSchedule] - Request: {}", request);
        List<AgendaResponse> fullSchedule = new ArrayList<>();

        for (JornadaPeriod period : JornadaPeriod.values()) {
            List<AgendaResponse> schedule = agendaCreatedManager.generateScheduleForPeriod(
                    request,
                    period.getStartTime(),
                    period.getEndTime(),
                    period.getJornadaEnum()
            );
            fullSchedule.addAll(schedule);
        }

        if (fullSchedule.isEmpty()) {
            log.error("Conflito de agenda: o profissional já possui compromissos para o dia inteiro - Request: {}", request);
            throw new AgendaException("Conflito de agenda: o profissional já possui compromissos para o dia inteiro.");
        }

        log.info("Finalizado o metodo de [generateSchedule] - Request: {}", request);
        return fullSchedule;
    }
}