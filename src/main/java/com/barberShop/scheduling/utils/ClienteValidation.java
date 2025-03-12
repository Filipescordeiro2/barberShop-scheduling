package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.dto.request.ClienteRequest;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteValidation {

    private final ClienteRepository clienteRepository;

    public void validPreSave(ClienteRequest request) {
        validExistsCliente(request.getCpf());
        validExistsLogin(request.getEmail());
    }

    private void validExistsCliente(String cpf) {
        if (clienteRepository.existsById(cpf)) {
            throw new ClienteException("Client already registered with this CPF: " + cpf);
        }
    }

    private void validExistsLogin(String email) {
        if (clienteRepository.existsByEmail(email)) {
            throw new ClienteException("Client already registered with this email: " + email);
        }
    }
}