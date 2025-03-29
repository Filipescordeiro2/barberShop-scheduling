package com.barberShop.scheduling.validation.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.repository.agenda.AgendaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Validador que verifica conflitos de horários em um período.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduleConflictPeriodValidator implements AgendaValidator {

    private final AgendaRepository agendaRepository;

    @Override
    public void validate(Agenda agenda) {
        log.info("Inicializado o metodo de [validate]");
        LocalDate date = agenda.getDate();
        LocalTime time = agenda.getTime();

        boolean conflictExists = agendaRepository.existsByProfissionalCpfAndDateAndTime(
                agenda.getProfissional().getCpf(),
                date,
                time
        );

        if (conflictExists) {
            throw new RuntimeException("Schedule conflict: the professional already has an appointment during this period.");
        }
        log.info("Finalizado o metodo de [validate]");
    }
}