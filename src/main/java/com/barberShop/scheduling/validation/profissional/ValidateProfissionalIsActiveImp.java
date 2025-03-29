package com.barberShop.scheduling.validation.profissional;

import com.barberShop.scheduling.domain.Profissional;
import com.barberShop.scheduling.exception.ProfissionalException;
import com.barberShop.scheduling.repository.profissional.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ValidateProfissionalIsActiveImp implements ProfissionalValidatorIsStatus {

    private final ProfissionalRepository profissionalRepository;

    @Override
    public void validateProfissionalIsActive(Profissional profissional) {
        log.info("Inicializado o metodo de [validateProfissionalIsActive]");
        if (!profissional.isActive()) {
            throw new ProfissionalException("Profissional is already disabled");
        }
        log.info("Finalizado o metodo de [validateProfissionalIsActive]");
    }
}