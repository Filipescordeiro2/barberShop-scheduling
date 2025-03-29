package com.barberShop.scheduling.validation.servicosBarbearia;

import com.barberShop.scheduling.enums.ServicosEnum;

public interface ServicosBarbeariaValidator {
    void validateServicoPreSave(ServicosEnum servico, String cnpjBarbearia);
}