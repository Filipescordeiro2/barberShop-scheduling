package com.barberShop.scheduling.validation.servicosBarbearia;

import com.barberShop.scheduling.enums.ServicosEnum;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TipoServicoValidator implements ServicosBarbeariaValidator {

    @Override
    public void validateServicoPreSave(ServicosEnum servico, String cnpjBarbearia) {
        log.info("Inicializado o metodo de [validateServicoPreSave]");
        if (servico == null) {
            throw new ServicosBarbeariaException("Service type cannot be null.");
        }
        if (!ServicosEnum.isValid(servico)) {
            throw new ServicosBarbeariaException("Invalid service type.");
        }
        log.info("Finalizado o metodo de [validateServicoPreSave] ");
    }
}