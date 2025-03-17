package com.barberShop.scheduling.controller;

import com.barberShop.scheduling.dto.request.BarbeariaRequest;
import com.barberShop.scheduling.dto.response.BarbeariaDeseableResponse;
import com.barberShop.scheduling.dto.response.BarbeariaRegisterResponse;
import com.barberShop.scheduling.dto.response.BarbeariaResponse;
import com.barberShop.scheduling.service.BarbeariaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<BarbeariaResponse> findBarbeariaByCNPJ(@PathVariable String cnpj) {
        var response = barbeariaService.findBarbeariaByCNPJ(cnpj);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/deseable/{cnpj}")
    public ResponseEntity<BarbeariaDeseableResponse> deseableBarbearia(@PathVariable String cnpj) {
        var response = barbeariaService.deseableBarbearia(cnpj);
        return ResponseEntity.ok(response);
    }

}
