package com.barberShop.scheduling.controller.profissionail;

import com.barberShop.scheduling.dto.request.login.LoginRequest;
import com.barberShop.scheduling.dto.request.profissional.ProfissionalRequest;
import com.barberShop.scheduling.dto.response.profissional.ProfissioanalDeseableResponse;
import com.barberShop.scheduling.dto.response.profissional.ProfissionalRegisterResponse;
import com.barberShop.scheduling.dto.response.profissional.ProfissionalReponse;
import com.barberShop.scheduling.service.profissional.ProfisisonalAuthenticationService;
import com.barberShop.scheduling.service.profissional.ProfissionalRegistrationService;
import com.barberShop.scheduling.service.profissional.ProfissionalStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profissional")
@Slf4j
public class ProfissionalController {

    private final ProfisisonalAuthenticationService profisisonalAuthenticationService;
    private final ProfissionalRegistrationService profissionalRegistrationService;
    private final ProfissionalStatusService profissionalStatusService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalRegisterResponse registerProfissional(@Valid @RequestBody ProfissionalRequest request) {
        log.info("Iniciado o endPoint [registerProfissional] - Request: {}", request);
        var response = profissionalRegistrationService.registerProfissional(request);
        log.info("Finalizado o endPoint [registerProfissional] - Response: {}", response);
        return response;
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public ProfissionalReponse authProfisisonal(@Valid @RequestBody LoginRequest request) {
        log.info("Iniciado o endPoint [authProfisisonal] - Request: {}", request);
        var response = profisisonalAuthenticationService.authenticateProfissional(request);
        log.info("Finalizado o endPoint [authProfisisonal] - Response: {}", response);
        return response;
    }

    @PatchMapping("/deseable/{cpfProfissional}")
    @ResponseStatus(HttpStatus.OK)
    public ProfissioanalDeseableResponse deseableProfissional(@PathVariable String cpfProfissional) {
        log.info("Iniciado o endPoint [deseableProfissional] - CPF: {}", cpfProfissional);
        var response = profissionalStatusService.deseableProfissional(cpfProfissional);
        log.info("Finalizado o endPoint [deseableProfissional] - Response: {}", response);
        return response;
    }
}