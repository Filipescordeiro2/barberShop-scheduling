package com.barberShop.scheduling.validation.servicosBarbearia;

import com.barberShop.scheduling.enums.ServicosEnum;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.repository.servicosBarbearia.ServicosBarbeariaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServicoExistenteValidator implements ServicosBarbeariaValidator {

    private final ServicosBarbeariaRepository servicosBarbeariaRepository;

    @Override
    public void validateServicoPreSave(ServicosEnum servico, String cnpjBarbearia) {
        log.info("Inicializado o metodo de [validateServicoPreSave]");

        var existingService = servicosBarbeariaRepository.findByServicesAndBarbeariaCnpjAndActiveTrue(servico, cnpjBarbearia);
        if (existingService.isPresent()) {
            throw new ServicosBarbeariaException("Service already registered for this barber shop.");
        }

        log.info("Finalizado o metodo de [validateServicoPreSave]");
    }
}