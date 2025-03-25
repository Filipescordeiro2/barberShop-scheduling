package com.barberShop.scheduling.controller;

import com.barberShop.scheduling.dto.request.ProfissionalBarbeariaRequest;
import com.barberShop.scheduling.dto.request.ServicosBarbeariaRequest;
import com.barberShop.scheduling.dto.response.ProfissionalBarbeariaResponse;
import com.barberShop.scheduling.dto.response.ServicosBarbeariaResponse;
import com.barberShop.scheduling.service.ProfissionalBarbeariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/profissionaisBarbearia")
@RequiredArgsConstructor
public class ProfissionaisBarbeariaController {

    private final ProfissionalBarbeariaService profissionalBarbeariaService;

    @PostMapping("/linked")
    public ResponseEntity<ProfissionalBarbeariaResponse> createBarbearia(@Valid @RequestBody ProfissionalBarbeariaRequest request) {
        var response = profissionalBarbeariaService.createProfissionalBarbearia(request);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response.cnpjBarbearia())
                        .toUri())
                .body(response);
    }

}
