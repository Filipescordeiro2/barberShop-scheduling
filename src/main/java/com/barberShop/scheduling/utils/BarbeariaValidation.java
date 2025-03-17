package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.domain.Barbearia;
import com.barberShop.scheduling.dto.request.BarbeariaRequest;
import com.barberShop.scheduling.exception.BarbeariaException;
import com.barberShop.scheduling.repository.BarbeariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BarbeariaValidation {

    private final BarbeariaRepository barbeariaRepository;

    public void validPreSave(BarbeariaRequest request) {
        validExistsBarber(request.getCnpj());
        validExistsLogin(request.getEmail());
    }

    private void validExistsBarber(String cnpj) {
        if (barbeariaRepository.existsById(cnpj)) {
            throw new BarbeariaException("Barber already registered with this CNPJ: " + cnpj);
        }
    }

    private void validExistsLogin(String email) {
        if (barbeariaRepository.existsByEmail(email)) {
            throw new BarbeariaException("Barber already registered with this email: " + email);
        }
    }

    public Barbearia validateAndGetBarbearia(String cnpj) {
        return barbeariaRepository.findById(cnpj)
                .orElseThrow(() -> new BarbeariaException("Barbearia not found"));
    }
}
