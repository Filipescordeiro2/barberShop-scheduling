package com.barberShop.scheduling.controller;

import com.barberShop.scheduling.dto.request.AgendaRequest;
import com.barberShop.scheduling.dto.request.AgendaRequestWithoutJornada;
import com.barberShop.scheduling.dto.response.AgendaCancelarResponse;
import com.barberShop.scheduling.dto.response.AgendaResponse;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendas")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @PostMapping("/gerar")
    public ResponseEntity<List<AgendaResponse>> gerarAgenda(@RequestBody AgendaRequestWithoutJornada request) {
        List<AgendaResponse> response = agendaService.generateSchedule(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/gerar/manha")
    public ResponseEntity<List<AgendaResponse>> gerarAgendaDaManha(@RequestBody AgendaRequestWithoutJornada request) {
        List<AgendaResponse> response = agendaService.generateMorningSchedule(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/gerar/tarde")
    public ResponseEntity<List<AgendaResponse>> gerarAgendaDaTarde(@RequestBody AgendaRequestWithoutJornada request) {
        List<AgendaResponse> response = agendaService.generateAfternoonSchedule(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/gerar/noite")
    public ResponseEntity<List<AgendaResponse>> gerarAgendaDaNoite(@RequestBody AgendaRequestWithoutJornada request) {
        List<AgendaResponse> response = agendaService.generateEveningSchedule(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/avulso")
    public ResponseEntity<Void> criarAgendaAvulso(@RequestBody AgendaRequest request) {
        agendaService.createSingleSchedule(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/cancelar/{id}")
    public ResponseEntity<AgendaCancelarResponse> cancelarAgenda(@PathVariable UUID id) {
        AgendaCancelarResponse response = agendaService.cancelSchedule(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AgendaResponse>> buscarAgendaPorStatus(@PathVariable StatusAgenda status) {
        List<AgendaResponse> response = agendaService.findSchedulesByStatus(status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AgendaResponse>> buscarAgendasPorParametros(
                                                                @RequestParam(required = false) String cpfProfissional,
                                                                @RequestParam(required = false) StatusAgenda status,
                                                                @RequestParam(required = false) LocalDate startDate,
                                                                @RequestParam(required = false) LocalDate endDate) {
        List<AgendaResponse> response = agendaService.findByProfissionalAndStatusAgenda(cpfProfissional,
                status, startDate, endDate);
        return ResponseEntity.ok(response);
    }
}