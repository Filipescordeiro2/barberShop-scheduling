package com.barberShop.scheduling.controller;

import com.barberShop.scheduling.dto.request.LoginRequest;
import com.barberShop.scheduling.dto.request.ProfissionalRequest;
import com.barberShop.scheduling.dto.response.ClienteResponse;
import com.barberShop.scheduling.dto.response.ProfissionalRegisterResponse;
import com.barberShop.scheduling.dto.response.ProfissionalReponse;
import com.barberShop.scheduling.service.ProfissionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/profissional")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    @PostMapping
    public ResponseEntity<ProfissionalRegisterResponse> createProfissional(@Valid @RequestBody ProfissionalRequest request) {
        var response = profissionalService.registerProfissional(request);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response.cpf())
                        .toUri())
                .body(response);
    }

    @PostMapping("/auth")
    public ResponseEntity<ProfissionalReponse> authenticateProfissional(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(profissionalService.authenticateProfissional(request));
    }
}
