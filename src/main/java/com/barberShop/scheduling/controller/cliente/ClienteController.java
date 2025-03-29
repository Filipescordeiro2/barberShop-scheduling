package com.barberShop.scheduling.controller.cliente;

import com.barberShop.scheduling.dto.request.cliente.ClienteRequest;
import com.barberShop.scheduling.dto.request.login.LoginRequest;
import com.barberShop.scheduling.dto.response.cliente.ClienteActivateResponse;
import com.barberShop.scheduling.dto.response.cliente.ClienteDeseableResponse;
import com.barberShop.scheduling.dto.response.cliente.ClienteRegisterResponse;
import com.barberShop.scheduling.dto.response.cliente.ClienteResponse;
import com.barberShop.scheduling.service.cliente.ClienteAuthenticationService;
import com.barberShop.scheduling.service.cliente.ClienteRegistrationService;
import com.barberShop.scheduling.service.cliente.ClienteStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
@Slf4j
public class ClienteController {

    private final ClienteAuthenticationService clienteAuthenticationService;
    private final ClienteRegistrationService clienteRegistrationService;
    private final ClienteStatusService clienteStatusService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteRegisterResponse registerCliente(@Valid @RequestBody ClienteRequest request){
        log.info("Iniciado o endPoint [registerCliente]");
        var response = clienteRegistrationService.registerCliente(request);
        log.info("Finalizado o endPoint [registerCliente] -- Response: {}",response);
        return response;
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponse authCliente (@Valid @RequestBody LoginRequest request){
        log.info("Iniciado o endPoint [authCliente]");
        var response = clienteAuthenticationService.authenticateCliente(request);
        log.info("Finalizado o endPoint [authCliente] -- Response: {}",response);
        return response;
    }

    @PatchMapping("/deseable/{cpfCliente}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDeseableResponse deseableCliente (@PathVariable String cpfCliente){
        log.info("Iniciado o endPoint [deseableCliente]");
        var response = clienteStatusService.deseableCliente(cpfCliente);
        log.info("Finalizado o endPoint [deseableCliente] -- Response: {}",response);
        return response;
    }

    @PatchMapping("/activate/{cpfCliente}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteActivateResponse activateCliente (@PathVariable String cpfCliente){
        log.info("Iniciado o endPoint [activateCliente]");
        var response = clienteStatusService.activateCliente(cpfCliente);
        log.info("Finalizado o endPoint [activateCliente] -- Response: {}",response);
        return response;
    }
}
