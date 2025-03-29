package com.barberShop.scheduling.validation.barbearia;

import com.barberShop.scheduling.domain.Barbearia;
import com.barberShop.scheduling.dto.request.barbearia.BarbeariaRequest;
import com.barberShop.scheduling.validation.ExistsValidator.AttributesExistingValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BarbeariaValidation {

    private final List<AttributesExistingValidator<BarbeariaRequest>> validators; // Injeta todos os validadores específicos de Barbearia
    private final List<BarbeariaValidatorIsStatus> statusValidators;

    public void validatePreSaveBarbearia(BarbeariaRequest request) {
        log.info("Inicializado o metodo de [validatePreSaveBarbearia]");
        validators.forEach(validator -> validator.validatePreSave(request)); // Executa todos os validadores injetados
        log.info("Finalizado o metodo de [validatePreSaveBarbearia]");
    }

    public void validateBarbeariaIsActive(Barbearia barbearia) {
        log.info("Inicializado o metodo de [validateBarbeariaIsActive]");
        statusValidators.forEach(validator -> validator.validateBarbeariaIsActive(barbearia));
        log.info("Finalizado o metodo de [validateBarbeariaIsActive]");
    }
}