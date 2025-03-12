package com.barberShop.scheduling.controller;

import com.barberShop.scheduling.domain.Cliente;
import com.barberShop.scheduling.dto.request.ClienteRequest;
import com.barberShop.scheduling.dto.request.LoginRequest;
import com.barberShop.scheduling.dto.response.ClienteRegisterResponse;
import com.barberShop.scheduling.dto.response.ClienteResponse;
import com.barberShop.scheduling.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final PagedResourcesAssembler<Cliente> pagedResourcesAssembler;

    @PostMapping
    public ResponseEntity<ClienteRegisterResponse> createCliente(@Valid @RequestBody ClienteRequest request) {
        var response = clienteService.registerCliente(request);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(response.cpf())
                        .toUri())
                .body(response);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> getClienteByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.findById(cpf));
    }

    @PostMapping("/auth")
    public ResponseEntity<ClienteResponse> authenticateCliente(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(clienteService.authenticateCliente(request));
    }
}