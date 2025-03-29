package com.barberShop.scheduling.validation.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Validador que verifica se o profissional e a barbearia estão ativos.
 */
@Component
@Slf4j
public class ActiveStatusValidator implements AgendaValidator {

    @Override
    public void validate(Agenda agenda) {
        log.info("Inicializado o metodo de [validate]");

        if (!agenda.getProfissional().isActive()) {
            throw new ServicosBarbeariaException("The professional is not active.");
        }

        if (!agenda.getBarbearia().isActive()) {
            throw new ServicosBarbeariaException("The barbershop is not active.");
        }

        log.info("Finalizado o metodo de [validate]");
    }
}