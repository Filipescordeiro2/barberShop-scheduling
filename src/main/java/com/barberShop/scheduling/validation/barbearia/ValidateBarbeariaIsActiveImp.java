package com.barberShop.scheduling.validation.barbearia;

import com.barberShop.scheduling.domain.Barbearia;
import com.barberShop.scheduling.exception.AgendamentoException;
import com.barberShop.scheduling.repository.barbearia.BarbeariaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ValidateBarbeariaIsActiveImp implements BarbeariaValidatorIsStatus {

    private final BarbeariaRepository barbeariaRepository;

    @Override
    public void validateBarbeariaIsActive(Barbearia barbearia) {
        log.info("Inicializado o metodo de [validateBarbeariaIsActive]");
        if (!barbearia.isActive()) {
            throw new AgendamentoException("The barber shop is not active");
        }
        log.info("Finalizado o metodo de [validateBarbeariaIsActive]");
    }
}