package com.barberShop.scheduling.validation.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.exception.AgendamentoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Validador que verifica se a data inicial está antes ou igual à data final.
 */
@Component
@Slf4j
public class DateRangeValidator implements AgendaValidator {

    @Override
    public void validate(Agenda agenda) {
        log.info("Inicializado o metodo de [validate]");
        LocalDate startDate = agenda.getDate(); // Data da agenda como data inicial
        LocalDate endDate = agenda.getDate(); // Para esse caso, usamos a mesma data como exemplo

        if (startDate.isAfter(endDate)) {
            log.error("Data inicial está após a data final - StartDate: {}, EndDate: {}", startDate, endDate);
            throw new AgendamentoException("The start date cannot be after the end date.");
        }
        log.info("Finalizado o metodo de [validate]");
    }
}