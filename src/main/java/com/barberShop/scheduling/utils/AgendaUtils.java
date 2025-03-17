package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.domain.Barbearia;
import com.barberShop.scheduling.domain.Profissional;
import com.barberShop.scheduling.domain.ServicosBarbearia;
import com.barberShop.scheduling.enums.JornadaEnum;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.repository.AgendaRepository;
import com.barberShop.scheduling.repository.AgendamentoRepository;
import com.barberShop.scheduling.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.coyote.http11.Constants.a;

@Component
@RequiredArgsConstructor
public class AgendaUtils {

    private final AgendaRepository agendaRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final AgendaValidation validation;

    public void cancelAppointments(Agenda agenda) {
        var appointments = agendamentoRepository.findByAgenda(agenda);
        if (!appointments.isEmpty()) {
            appointments.stream().forEach(appointment -> {
                appointment.setStatus(StatusAgenda.CANCELADO);
                agendamentoRepository.save(appointment);
            });
        }
    }

    public void cancelSchedule(Agenda agenda) {
        agenda.setStatusAgenda(StatusAgenda.CANCELADO);
        agendaRepository.save(agenda);
    }

    public List<Agenda> generateSchedules(Profissional professional,
                                          Barbearia barberShop,
                                          ServicosBarbearia service,
                                          LocalDate date, LocalTime start,
                                          LocalTime end, int intervalMinutes, JornadaEnum shift) {
        List<Agenda> schedules = new ArrayList<>();
        LocalTime currentTime = start;
        while (currentTime.isBefore(end)) {
            Agenda entity = new Agenda();
            entity.setProfissional(professional);
            entity.setBarbearia(barberShop);
            entity.setServicosBarbearia(service);
            entity.setDate(date);
            entity.setTime(currentTime);
            entity.setJornada(shift);
            entity.setStatusAgenda(StatusAgenda.ABERTO);

            schedules.add(entity);
            currentTime = currentTime.plusMinutes(intervalMinutes);
        }
        return schedules;
    }

    public List<Agenda> findAgendaFiltro(String cpf, StatusAgenda statusAgenda,
                                         LocalDate startDate, LocalDate endDate) {
        List<Agenda> agendaList;
        if (startDate == null && endDate == null) {
            agendaList = agendaRepository.findByProfissionalCpfAndStatusAgenda(cpf, statusAgenda);
        } else if (startDate == null) {
            agendaList = agendaRepository.findByProfissionalCpfAndStatusAgendaAndDateBefore(cpf, statusAgenda, endDate);
        } else if (endDate == null) {
            agendaList = agendaRepository.findByProfissionalCpfAndStatusAgendaAndDateAfter(cpf, statusAgenda, startDate);
        } else if (statusAgenda == null && cpf == null) {
            validation.checkDateBetween(startDate, endDate);
            agendaList = agendaRepository.findByDateBetween(startDate, endDate);
        } else if (statusAgenda == null) {
            validation.checkDateBetween(startDate, endDate);
            agendaList = agendaRepository.findByProfissionalCpfAndDateBetween(cpf, startDate, endDate);
        } else if (cpf == null) {
            validation.checkDateBetween(startDate, endDate);
            agendaList = agendaRepository.findByStatusAgendaAndDateBetween(statusAgenda, startDate, endDate);
        } else {
            validation.checkDateBetween(startDate, endDate);
            agendaList = agendaRepository.findByProfissionalCpfAndStatusAgendaAndDateBetween(cpf,
                    statusAgenda, startDate, endDate);
        }
        return agendaList;
    }

}