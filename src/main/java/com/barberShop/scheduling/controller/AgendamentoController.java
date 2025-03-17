package com.barberShop.scheduling.controller;

import com.barberShop.scheduling.dto.request.AgendamentoRequest;
import com.barberShop.scheduling.dto.response.AgendamentoCancelarResponse;
import com.barberShop.scheduling.dto.response.AgendamentoDetalhadaResponse;
import com.barberShop.scheduling.dto.response.AgendamentoResponse;
import com.barberShop.scheduling.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponse> criarAgendamento(@RequestBody AgendamentoRequest request)   {
        AgendamentoResponse response = agendamentoService.createAppointment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{agendaId}")
    public ResponseEntity<List<AgendamentoDetalhadaResponse>> getAgendamentoDetalhado(@PathVariable UUID agendaId) {
        List<AgendamentoDetalhadaResponse> response = agendamentoService.getDetailedAppointment(agendaId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<List<AgendamentoDetalhadaResponse>> getAgendamentoDetalhadoPorCpf(@PathVariable String cpf) {
        List<AgendamentoDetalhadaResponse> response = agendamentoService.getDetailedAppointmentByCpf(cpf);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancelar/{agendamentoId}")
    public ResponseEntity<AgendamentoCancelarResponse> cancelarAgendamento(@PathVariable UUID agendamentoId) {
        AgendamentoCancelarResponse response = agendamentoService.cancelAppointment(agendamentoId);
        return ResponseEntity.ok(response);
    }
}