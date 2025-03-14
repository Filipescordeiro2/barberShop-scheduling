package com.barberShop.scheduling.controller;

import com.barberShop.scheduling.dto.request.BarbeariaRequest;
import com.barberShop.scheduling.dto.response.BarbeariaRegisterResponse;
import com.barberShop.scheduling.service.BarbeariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/barbearia")
@RequiredArgsConstructor
public class BarbeariaController {

    private final BarbeariaService barbeariaService;

    @PostMapping
    public ResponseEntity<BarbeariaRegisterResponse> createBarbearia(@Valid @RequestBody BarbeariaRequest request) {
        var response = barbeariaService.createBarbearia(request);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response.cnpj())
                        .toUri())
                .body(response);
    }}
