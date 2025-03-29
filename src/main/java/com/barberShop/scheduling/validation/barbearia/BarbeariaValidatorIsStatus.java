package com.barberShop.scheduling.validation.barbearia;

import com.barberShop.scheduling.domain.Barbearia;
import com.barberShop.scheduling.domain.Profissional;

public interface BarbeariaValidatorIsStatus {
    void validateBarbeariaIsActive(Barbearia barbearia);

}
