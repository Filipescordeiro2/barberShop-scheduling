package com.barberShop.scheduling.validation.ExistsValidator;

import com.barberShop.scheduling.dto.request.cliente.ClienteRequest;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClienteAttributesValidator implements AttributesExistingValidator<ClienteRequest> {

    private final ClienteRepository clienteRepository;

    @Override
    public void validatePreSave(ClienteRequest request) {
        log.info("Inicializado o metodo de [validatePreSave]");
        if (clienteRepository.existsById(request.getCpf())) {
            throw new ClienteException("Client already registered with this CPF: " + request.getCpf());
        }
        if (clienteRepository.existsByEmail(request.getEmail())) {
            throw new ClienteException("Client already registered with this email: " + request.getEmail());
        }
        log.info("Finalizado o metodo de [validatePreSave]");
    }
}