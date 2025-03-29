package com.barberShop.scheduling.controller.servicoBarbearia;

import com.barberShop.scheduling.dto.request.servicosBarbearia.ServicosBarbeariaRequest;
import com.barberShop.scheduling.dto.response.servicosBarbearia.ServicosBarbeariaResponse;
import com.barberShop.scheduling.service.servicosBarbearia.ServicosBarbeariaRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servicosBarbearia")
@Slf4j
public class ServicosBarbeariaController {

    private final ServicosBarbeariaRegistrationService servicosBarbeariaRegistrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicosBarbeariaResponse createServicoBarbearia(@Valid @RequestBody ServicosBarbeariaRequest request) {
        log.info("Iniciado o endPoint [createServicoBarbearia] - Request: {}", request);
        var response = servicosBarbeariaRegistrationService.createServicosBarbearia(request);
        log.info("Finalizado o endPoint [createServicoBarbearia] - Response: {}", response);
        return response;
    }
}