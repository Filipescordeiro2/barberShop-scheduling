package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.domain.Profissional;
import com.barberShop.scheduling.dto.request.ClienteRequest;
import com.barberShop.scheduling.dto.request.ProfissionalRequest;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.exception.ProfissionalException;
import com.barberShop.scheduling.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfissionalValidation {

    private final ProfissionalRepository profissionalRepository;

    public void validPreSave(ProfissionalRequest request) {
        validExistsProfissional(request.getCpf());
        validExistsLogin(request.getEmail());
    }

    private void validExistsProfissional(String cpf) {
        if (profissionalRepository.existsById(cpf)) {
            throw new ClienteException("Profissional already registered with this CPF: " + cpf);
        }
    }

    private void validExistsLogin(String email) {
        if (profissionalRepository.existsByEmail(email)) {
            throw new ClienteException("Profissional already registered with this email: " + email);
        }
    }


    public Profissional validateProfissional(String cpfProfissional) {
        var profissional = profissionalRepository.findById(cpfProfissional)
                .orElseThrow(() -> new ProfissionalException("Profissional not found"));
        if (!profissional.isActive()) {
            throw new ProfissionalException("Profissional is already disabled");
        }
        return profissional;
    }
}
