package com.barberShop.scheduling.controller.agendamento;

import com.barberShop.scheduling.dto.request.agendamento.AgendamentoRequest;
import com.barberShop.scheduling.dto.response.agendamento.AgendamentoResponse;
import com.barberShop.scheduling.service.agendamento.AgendamentoCreateAppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamento")
@Slf4j
public class AgendamentoController {

    private final AgendamentoCreateAppointmentService agendamentoCreateAppointmentService;

    @PostMapping
    public AgendamentoResponse createAgendamento(@RequestBody AgendamentoRequest request) {
        log.info("Iniciado o endPoint [createAgendamento] - Request: {}", request);
        var response = agendamentoCreateAppointmentService.createAppointment(request);
        log.info("Finalizado o endPoint [createAgendamento] - Response: {}", response);
        return response;
    }
}