package com.barberShop.scheduling.validation.ExistsValidator;

import com.barberShop.scheduling.dto.request.profissional.ProfissionalRequest;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.repository.profissional.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProfissionalAttributesValidator implements AttributesExistingValidator<ProfissionalRequest> {

    private final ProfissionalRepository profissionalRepository;

    @Override
    public void validatePreSave(ProfissionalRequest request) {
        log.info("Inicializado o metodo de [validatePreSave]");
        if (profissionalRepository.existsById(request.getCpf())) {
            throw new ClienteException("Profissional already registered with this CPF: " + request.getCpf());
        }
        if (profissionalRepository.existsByEmail(request.getEmail())) {
            throw new ClienteException("Profissional already registered with this email: " + request.getEmail());
        }
        log.info("Finalizado o metodo de [validatePreSave]");
    }
}