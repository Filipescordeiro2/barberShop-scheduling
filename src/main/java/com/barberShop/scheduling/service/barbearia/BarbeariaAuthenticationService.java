package com.barberShop.scheduling.service.barbearia;

import com.barberShop.scheduling.dto.request.login.LoginRequest;
import com.barberShop.scheduling.dto.response.barbearia.BarbeariaResponse;
import com.barberShop.scheduling.validation.autenticador.Authenticator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BarbeariaAuthenticationService {

    private final List<Authenticator> authenticators; // Injeta autenticadores

    public BarbeariaResponse authenticateBarbearia(LoginRequest request) {
        log.info("Inicializado o metodo de [authenticateBarbearia] - Request: {}", request);
        BarbeariaResponse response = authenticators.stream()
                .findFirst()
                .orElseThrow(() -> {
                    return new RuntimeException("No authenticators configured");
                })
                .authenticateBarbearia(request);
        log.info("Finalizado o metodo de [authenticateBarbearia] - Response: {}", response);
        return response;
    }
}