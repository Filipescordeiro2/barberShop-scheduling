package com.barberShop.scheduling.service.profissional;

import com.barberShop.scheduling.dto.request.login.LoginRequest;
import com.barberShop.scheduling.dto.response.profissional.ProfissionalReponse;
import com.barberShop.scheduling.validation.autenticador.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfisisonalAuthenticationService {

    private final List<Authenticator> authenticators; // Injeta autenticadores

    public ProfissionalReponse authenticateProfissional(LoginRequest request) {
        // Usar o primeiro autenticador encontrado (pode ser ajustado com lógica de seleção)
        return authenticators.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No authenticators configured"))
                .authenticateProfissional(request);
    }
}
