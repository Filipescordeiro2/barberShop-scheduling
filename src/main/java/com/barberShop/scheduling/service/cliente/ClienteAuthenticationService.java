package com.barberShop.scheduling.service.cliente;

import com.barberShop.scheduling.dto.request.login.LoginRequest;
import com.barberShop.scheduling.dto.response.cliente.ClienteResponse;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.validation.autenticador.Authenticator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClienteAuthenticationService {

    private final List<Authenticator> authenticators;

    public ClienteResponse authenticateCliente(LoginRequest request) {
        log.info("Inicializado o servico [authenticateCliente] -- Resquest {}", request);

        var response = authenticators.stream()
                .findFirst()
                .orElseThrow(() -> new ClienteException("No authenticators configured"))
                .authenticateCliente(request);

        log.info("Finalizado o servico [authenticateCliente]");
        return response;
    }
}
