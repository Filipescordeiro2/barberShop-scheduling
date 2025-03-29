package com.barberShop.scheduling.validation.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.AgendaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AgendaNotCancelledValidator implements AgendaValidator {

    @Override
    public void validate(Agenda agenda) {
        log.info("Inicializado o metodo de [validate]");
        if (agenda.getStatusAgenda() == StatusAgenda.CANCELADO) {
            throw new AgendaException("The schedule has already been canceled.");
        }
        log.info("Finalizado o metodo de [validate]");
    }
}