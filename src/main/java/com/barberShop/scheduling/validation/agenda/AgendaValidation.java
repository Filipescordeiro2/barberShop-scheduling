package com.barberShop.scheduling.validation.agenda;

import com.barberShop.scheduling.domain.Agenda;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe que gerencia validações de agendas.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AgendaValidation {

    private final List<AgendaValidator> validators;

    public void validate(Agenda agenda) {
        log.info("Inicializado o metodo de [validate]");
        validators.forEach(validator -> validator.validate(agenda));
        log.info("Finalizado o metodo de [validate] - Agenda ID: {}", agenda.getId());
    }

    public void validateWithSpecificValidators(Agenda agenda, List<Class<? extends AgendaValidator>> specificValidators) {
        log.info("Inicializado o metodo de [validateWithSpecificValidators] - Agenda ID: {}, Specific Validators: {}", agenda.getId(), specificValidators);
        specificValidators.forEach(validatorClass -> {
            validators.stream()
                    .filter(validator -> validatorClass.isAssignableFrom(validator.getClass()))
                    .findFirst()
                    .ifPresent(validator -> validator.validate(agenda));
        });
        log.info("Finalizado o metodo de [validateWithSpecificValidators]");
    }
}