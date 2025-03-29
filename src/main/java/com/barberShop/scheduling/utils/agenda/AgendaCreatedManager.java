package com.barberShop.scheduling.utils.agenda;

import com.barberShop.scheduling.domain.Cliente;
import com.barberShop.scheduling.dto.request.agenda.AgendaRequest;
import com.barberShop.scheduling.dto.request.agenda.AgendaRequestWithoutJornada;
import com.barberShop.scheduling.dto.response.agenda.AgendaResponse;
import com.barberShop.scheduling.enums.JornadaEnum;

import java.time.LocalTime;
import java.util.List;

public interface AgendaCreatedManager {
    void abrirAgendaDoProfissional(Cliente cliente);
    AgendaResponse createSingleSchedule(AgendaRequest request);
    List<AgendaResponse> generateScheduleForPeriod(AgendaRequestWithoutJornada request,
                                                   LocalTime startTime, LocalTime endTime,
                                                   JornadaEnum jornada);
}