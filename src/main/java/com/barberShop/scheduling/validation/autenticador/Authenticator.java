package com.barberShop.scheduling.validation.autenticador;
import com.barberShop.scheduling.dto.request.login.LoginRequest;
import com.barberShop.scheduling.dto.response.barbearia.BarbeariaResponse;
import com.barberShop.scheduling.dto.response.cliente.ClienteResponse;
import com.barberShop.scheduling.dto.response.profissional.ProfissionalReponse;

public interface Authenticator {
    ClienteResponse authenticateCliente(LoginRequest request);
    ProfissionalReponse authenticateProfissional(LoginRequest request);
    BarbeariaResponse authenticateBarbearia(LoginRequest request);

}
