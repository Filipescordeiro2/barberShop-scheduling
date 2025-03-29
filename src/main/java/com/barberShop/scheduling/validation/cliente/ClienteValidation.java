package com.barberShop.scheduling.validation.cliente;

import com.barberShop.scheduling.dto.request.cliente.ClienteRequest;
import com.barberShop.scheduling.validation.ExistsValidator.AttributesExistingValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClienteValidation {

    private final List<AttributesExistingValidator<ClienteRequest>> validators;

    public void validatePreSaveCliente(ClienteRequest request) {
        log.info("Inicializado o metodo de [validatePreSaveCliente]");
        validators.forEach(validator -> validator.validatePreSave(request));
        log.info("Finalizado o metodo de [validatePreSaveCliente]");
    }
}