package com.barberShop.scheduling.controller;

import com.barberShop.scheduling.dto.request.AgendaRequest;
import com.barberShop.scheduling.dto.response.AgendaCancelarResponse;
import com.barberShop.scheduling.dto.response.AgendaResponse;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendas")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @PostMapping("/gerar")
    public ResponseEntity<List<AgendaResponse>> gerarAgenda(@RequestBody AgendaRequest request) {
        List<AgendaResponse> response = agendaService.gerarAgenda(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/gerar/manha")
    public ResponseEntity<List<AgendaResponse>> gerarAgendaDaManha(@RequestBody AgendaRequest request) {
        List<AgendaResponse> response = agendaService.gerarAgendaDaManha(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/gerar/tarde")
    public ResponseEntity<List<AgendaResponse>> gerarAgendaDaTarde(@RequestBody AgendaRequest request) {
        List<AgendaResponse> response = agendaService.gerarAgendaDaTarde(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/gerar/noite")
    public ResponseEntity<List<AgendaResponse>> gerarAgendaDaNoite(@RequestBody AgendaRequest request) {
        List<AgendaResponse> response = agendaService.gerarAgendaDaNoite(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/avulso")
    public ResponseEntity<Void> criarAgendaAvulso(@RequestBody AgendaRequest request) {
        agendaService.criarAgendaAvulso(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<AgendaCancelarResponse> cancelarAgenda(@PathVariable UUID id) {
        AgendaCancelarResponse response = agendaService.cancelarAgenda(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AgendaResponse>> buscarAgendaPorStatus(@PathVariable StatusAgenda status) {
        List<AgendaResponse> response = agendaService.buscarAgendaPorStatus(status);
        return ResponseEntity.ok(response);
    }
}