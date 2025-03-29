package com.barberShop.scheduling.validation.ExistsValidator;

import com.barberShop.scheduling.dto.request.barbearia.BarbeariaRequest;
import com.barberShop.scheduling.exception.BarbeariaException;
import com.barberShop.scheduling.repository.barbearia.BarbeariaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BarbeariaAttributesValidator implements AttributesExistingValidator<BarbeariaRequest> {

    private final BarbeariaRepository barbeariaRepository;

    @Override
    public void validatePreSave(BarbeariaRequest request) {
        log.info("Inicializado o metodo de [validatePreSave]");
        if (barbeariaRepository.existsById(request.getCnpj())) {
            throw new BarbeariaException("Barber already registered with this CNPJ: " + request.getCnpj());
        }
        if (barbeariaRepository.existsByEmail(request.getEmail())) {
            throw new BarbeariaException("Barber already registered with this email: " + request.getEmail());
        }
        log.info("Finalizado o metodo de [validatePreSave]");
    }
}