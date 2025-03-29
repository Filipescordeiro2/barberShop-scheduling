package com.barberShop.scheduling.controller.barbearia;

import com.barberShop.scheduling.dto.request.barbearia.BarbeariaRequest;
import com.barberShop.scheduling.dto.request.login.LoginRequest;
import com.barberShop.scheduling.dto.response.barbearia.BarbeariaDeseableResponse;
import com.barberShop.scheduling.dto.response.barbearia.BarbeariaRegisterResponse;
import com.barberShop.scheduling.dto.response.barbearia.BarbeariaResponse;
import com.barberShop.scheduling.service.barbearia.BarbeariaAuthenticationService;
import com.barberShop.scheduling.service.barbearia.BarbeariaFindBarbeariaService;
import com.barberShop.scheduling.service.barbearia.BarbeariaRegistrationService;
import com.barberShop.scheduling.service.barbearia.BarbeariaStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barbearia")
@RequiredArgsConstructor
@Slf4j
public class BarbeariaController {

    private final BarbeariaRegistrationService barbeariaRegistrationService;
    private final BarbeariaAuthenticationService barbeariaAuthenticationService;
    private final BarbeariaStatusService barbeariaStatusService;
    private final BarbeariaFindBarbeariaService barbeariaFindBarbeariaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BarbeariaRegisterResponse registerBarbearia(@RequestBody BarbeariaRequest request) {
        log.info("Iniciado o endPoint [registerBarbearia] - Request: {}", request);
        var response = barbeariaRegistrationService.createBarbearia(request);
        log.info("Finalizado o endPoint [registerBarbearia] - Response: {}", response);
        return response;
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public BarbeariaResponse authBarbearia(@RequestBody LoginRequest request) {
        log.info("Iniciado o endPoint [authBarbearia] - Request: {}", request);
        var response = barbeariaAuthenticationService.authenticateBarbearia(request);
        log.info("Finalizado o endPoint [authBarbearia] - Response: {}", response);
        return response;
    }

    @PatchMapping("/deseable/{cnpjBarbearia}")
    @ResponseStatus(HttpStatus.OK)
    public BarbeariaDeseableResponse deseableBarcelona(@PathVariable String cnpjBarbearia) {
        log.info("Iniciado o endPoint [deseableBarcelona] - CNPJ: {}", cnpjBarbearia);
        var response = barbeariaStatusService.deseableBarbearia(cnpjBarbearia);
        log.info("Finalizado o endPoint [deseableBarcelona] - Response: {}", response);
        return response;
    }

    @GetMapping("/deseable/{cnpjBarbearia}")
    @ResponseStatus(HttpStatus.OK)
    public BarbeariaResponse findById(@PathVariable String cnpjBarbearia) {
        log.info("Iniciado o endPoint [findById] - CNPJ: {}", cnpjBarbearia);
        var response = barbeariaFindBarbeariaService.findBarbeariaByCNPJ(cnpjBarbearia);
        log.info("Finalizado o endPoint [findById] - Response: {}", response);
        return response;
    }
}