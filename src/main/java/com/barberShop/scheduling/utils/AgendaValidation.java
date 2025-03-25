package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.domain.*;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.AgendamentoException;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AgendaValidation {

    private final ProfissionalRepository profissionalRepository;
    private final AgendaRepository agendaRepository;
    private final BarbeariaRepository barbeariaRepository;
    private final ServicosBarbeariaRepository servicosBarbeariaRepository;
    private final ProfissionalBarbeariaRepository profissionalBarbeariaRepository;


    public void checkDateBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new AgendamentoException("Data de início não pode ser maior que a data final");
        }
    }

    public void checkProfessionalBarberShopLink(String cpfProfissional, String cnpjBarbearia) {
        boolean linkExists = profissionalBarbeariaRepository.existsByProfissionalCpfAndBarbeariaCnpj(cpfProfissional, cnpjBarbearia);
        if (!linkExists) {
            throw new ServicosBarbeariaException("The professional is not linked to the specified barbershop.");
        }
    }

    public void checkActiveStatus(Profissional profissional, Barbearia barbearia) {
        if (!profissional.isActive()) {
            throw new ServicosBarbeariaException("The professional is not active.");
        }
        if (!barbearia.isActive()) {
            throw new ServicosBarbeariaException("The barbershop is not active.");
        }
    }

    public void checkScheduleConflict(String cpfProfissional, LocalDate date, LocalTime time) {
        boolean conflictExists = agendaRepository.existsByProfissionalCpfAndDateAndTime(cpfProfissional, date, time);
        if (conflictExists) {
            throw new ServicosBarbeariaException("Schedule conflict: the professional already has an appointment at this time.");
        }
    }

    public boolean checkScheduleConflictPeriod(String cpfProfissional, LocalDate date, LocalTime time) {
        return agendaRepository.existsByProfissionalCpfAndDateAndTime(cpfProfissional, date, time);
    }

    public Profissional validateProfessional(String cpfProfissional) {
        return profissionalRepository.findById(cpfProfissional)
                .orElseThrow(() -> new ServicosBarbeariaException("Professional not found"));
    }

    public Barbearia validateBarberShop(String cnpjBarbearia) {
        return barbeariaRepository.findById(cnpjBarbearia)
                .orElseThrow(() -> new ServicosBarbeariaException("Barbershop not found"));
    }

    public ServicosBarbearia validateBarberShopService(UUID servicosBarbeariaId) {
        return servicosBarbeariaRepository.findById(servicosBarbeariaId)
                .orElseThrow(() -> new ServicosBarbeariaException("Barber service not found"));
    }

    public ValidacaoResultado validateProfessionalAndBarberShop(String cpfProfissional, String cnpjBarbearia, UUID servicosBarbeariaId) {
        checkProfessionalBarberShopLink(cpfProfissional, cnpjBarbearia);
        Profissional profissional = validateProfessional(cpfProfissional);
        Barbearia barbearia = validateBarberShop(cnpjBarbearia);
        ServicosBarbearia service = validateBarberShopService(servicosBarbeariaId);
        checkActiveStatus(profissional, barbearia);
        return new ValidacaoResultado(profissional, barbearia, service);
    }

    public Agenda validateScheduleExists(UUID agendaId) {
        return agendaRepository.findById(agendaId)
                .orElseThrow(() -> new ServicosBarbeariaException("Schedule not found"));
    }

    public void validateScheduleNotCancelled(Agenda agenda) {
        if (agenda.getStatusAgenda() == StatusAgenda.CANCELADO) {
            throw new ServicosBarbeariaException("The schedule is already cancelled");
        }
    }
}