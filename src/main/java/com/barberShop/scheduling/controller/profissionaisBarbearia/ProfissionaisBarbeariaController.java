package com.barberShop.scheduling.controller.profissionaisBarbearia;

import com.barberShop.scheduling.dto.request.ProfissionalBarbeariaRequest;
import com.barberShop.scheduling.dto.response.profissionalBarbearia.ProfissionalBarbeariaResponse;
import com.barberShop.scheduling.service.profissionalBarbearia.ProfissionalBarbeariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profissionaisBarbearia")
@RequiredArgsConstructor
@Slf4j
public class ProfissionaisBarbeariaController {

    private final ProfissionalBarbeariaService profissionalBarbeariaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalBarbeariaResponse createLinked(@Valid @RequestBody ProfissionalBarbeariaRequest request) {
        log.info("Iniciado o endPoint [createLinked] - Request: {}", request);
        var response = profissionalBarbeariaService.createProfissionalBarbearia(request);
        log.info("Finalizado o endPoint [createLinked] - Response: {}", response);
        return response;
    }
}