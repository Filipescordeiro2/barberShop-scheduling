package com.barberShop.scheduling.validation.profissional;

import com.barberShop.scheduling.domain.Profissional;
import com.barberShop.scheduling.dto.request.profissional.ProfissionalRequest;
import com.barberShop.scheduling.validation.ExistsValidator.AttributesExistingValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProfissionalValidation {

    private final List<AttributesExistingValidator<ProfissionalRequest>> validators;
    private final List<ProfissionalValidatorIsStatus> statusValidators;

    public void validatePreSaveProfissional(ProfissionalRequest request) {
        log.info("Inicializado o metodo de [validatePreSaveProfissional]");
        validators.forEach(validator -> validator.validatePreSave(request));
        log.info("Finalizado o metodo de [validatePreSaveProfissional] - Request: {}", request);
    }

    public void validateProfissionalIsActive(Profissional profissional) {
        log.info("Inicializado o metodo de [validateProfissionalIsActive]");
        statusValidators.forEach(validator -> validator.validateProfissionalIsActive(profissional));
        log.info("Finalizado o metodo de [validateProfissionalIsActive] ");
    }
}