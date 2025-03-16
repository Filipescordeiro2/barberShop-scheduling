package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.domain.Profissional;
import com.barberShop.scheduling.exception.ProfissionalException;
import com.barberShop.scheduling.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfissionalValidation {

    private final ProfissionalRepository profissionalRepository;

    public Profissional validateProfissional(String cpfProfissional) {
        var profissional = profissionalRepository.findById(cpfProfissional)
                .orElseThrow(() -> new ProfissionalException("Profissional not found"));
        if (!profissional.isActive()) {
            throw new ProfissionalException("Profissional is already disabled");
        }
        return profissional;
    }
}
