package com.barberShop.scheduling.validation.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Validador que verifica se o horário está dentro do expediente permitido.
 */
@Component
@Slf4j
public class WorkingHoursValidator implements AgendaValidator {

    private static final LocalTime START_TIME = LocalTime.of(9, 0); // Início do expediente
    private static final LocalTime END_TIME = LocalTime.of(22, 0); // Fim do expediente

    @Override
    public void validate(Agenda agenda) {
        log.info("Inicializado o metodo de [validate]");
        LocalTime time = agenda.getTime();

        if (time.isBefore(START_TIME) || time.isAfter(END_TIME)) {
            throw new ServicosBarbeariaException("The schedule time is outside of working hours.");
        }
        log.info("Finalizado o metodo de [validate]");
    }
}