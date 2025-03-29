package com.barberShop.scheduling.controller.agenda;

import com.barberShop.scheduling.dto.request.agenda.AgendaRequestWithoutJornada;
import com.barberShop.scheduling.dto.response.agenda.AgendaResponse;
import com.barberShop.scheduling.service.agenda.AgendaGenerateScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agenda")
@Slf4j
public class AgendaController {

    private final AgendaGenerateScheduleService scheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<AgendaResponse> generateCompleteSchedule(@RequestBody AgendaRequestWithoutJornada request) {
        log.info("Iniciado o endPoint [generateCompleteSchedule] - Request: {}", request);
        var response = scheduleService.generateSchedule(request);
        log.info("Finalizado o endPoint [generateCompleteSchedule] - Response: {}", response);
        return response;
    }

    @PostMapping("/period")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AgendaResponse> generateScheduleForPeriod(@RequestBody AgendaRequestWithoutJornada request) {
        log.info("Iniciado o endPoint [generateScheduleForPeriod] - Request: {}", request);
        var response = scheduleService.generateScheduleForPeriod(request);
        log.info("Finalizado o endPoint [generateScheduleForPeriod] - Response: {}", response);
        return response;
    }
}