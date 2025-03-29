package com.barberShop.scheduling.validation.servicosBarbearia;

import com.barberShop.scheduling.enums.ServicosEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServicosBarbeariaValidation {

    private final List<ServicosBarbeariaValidator> validators; // Injeta todos os validadores específicos

    public void validPreSaveServicos(ServicosEnum servico, String cnpjBarbearia) {
        log.info("Inicializado o metodo de [validPreSaveServicos]");
        validators.forEach(validator -> validator.validateServicoPreSave(servico, cnpjBarbearia)); // Executa cada validador
        log.info("Finalizado o metodo de [validPreSaveServicos] ");
    }
}