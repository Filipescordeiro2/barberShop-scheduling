package com.barberShop.scheduling.validation.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProfessionalBarberShopLinkValidator implements AgendaValidator {

    @Override
    public void validate(Agenda agenda) {
        log.info("Inicializado o metodo de [validate]");
        boolean isLinked = agenda.getProfissional().getProfissionalBarbearias().stream()
                .anyMatch(profissionalBarbearia ->
                        profissionalBarbearia.getBarbearia().equals(agenda.getBarbearia()));

        if (!isLinked) {
            throw new ServicosBarbeariaException("The professional is not linked to the specified barbershop.");
        }
        log.info("Finalizado o metodo de [validate]");
    }
}