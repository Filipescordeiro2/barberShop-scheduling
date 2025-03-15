package com.barberShop.scheduling.controller;

import com.barberShop.scheduling.dto.request.ServicosBarbeariaRequest;
import com.barberShop.scheduling.dto.response.ServicosBarbeariaResponse;
import com.barberShop.scheduling.service.ServicosBarbeariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/ServicosBarbearia")
@RequiredArgsConstructor
public class ServicosBarbeariaController {

    private final ServicosBarbeariaService servicosBarbearia;

    @PostMapping
    public ResponseEntity<ServicosBarbeariaResponse> createBarbearia(@Valid @RequestBody ServicosBarbeariaRequest request) {
        var response = servicosBarbearia.createServicosBarbearia(request);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response.cnpjBarbearia())
                        .toUri())
                .body(response);
    }
}
