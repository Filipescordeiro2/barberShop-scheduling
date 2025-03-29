package com.barberShop.scheduling.validation.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.repository.agenda.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class AgendaScheduleConflictValidator implements AgendaValidator {

    private final AgendaRepository agendaRepository;

    @Override
    public void validate(Agenda agenda) {
        boolean conflictExists = agendaRepository.existsByProfissionalCpfAndDateAndTime(
                agenda.getProfissional().getCpf(), agenda.getDate(), agenda.getTime()
        );
        if (conflictExists) {
            throw new RuntimeException("Schedule conflict: the professional already has an appointment at this time.");
        }
    }
}