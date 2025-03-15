package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.domain.Barbearia;
import com.barberShop.scheduling.domain.Profissional;
import com.barberShop.scheduling.domain.ServicosBarbearia;
import com.barberShop.scheduling.enums.JornadaEnum;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.repository.AgendaRepository;
import com.barberShop.scheduling.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AgendaUtils {

    private final ProfissionalRepository profissionalRepository;
    private final AgendaRepository agendaRepository;

    public static List<Agenda> filterAgendasByProfissional(List<Agenda> agendas, String cpfProfissional, ProfissionalRepository profissionalRepository) {
        if (cpfProfissional != null) {
            if (!profissionalRepository.existsById(cpfProfissional)) {
                throw new ServicosBarbeariaException("Profissional não encontrado");
            }
            return agendas.stream()
                    .filter(agenda -> agenda.getProfissional().isActive())
                    .collect(Collectors.toList());
        }
        return agendas;
    }

    public static List<Agenda> filterAgendasByBarbearia(List<Agenda> agendas, String cnpjBarbearia) {
        return agendas.stream()
                .filter(agenda -> agenda.getBarbearia().getCnpj().equals(cnpjBarbearia))
                .filter(agenda -> agenda.getProfissional().isActive())
                .collect(Collectors.toList());
    }

    public static List<Agenda> filterAgendas(List<Agenda> agendas, LocalDate data, LocalTime hora, String nomeProfissional, String nomeServico) {
        return agendas.stream()
                .filter(agenda -> agenda.getStatusAgenda() == StatusAgenda.ABERTO || agenda.getStatusAgenda() == StatusAgenda.AGENDADO)
                .filter(agenda -> data == null || agenda.getDate().equals(data))
                .filter(agenda -> hora == null || agenda.getTime().equals(hora))
                .filter(agenda -> nomeProfissional == null || agenda.getProfissional().getNameProfissional().equalsIgnoreCase(nomeProfissional))
                .filter(agenda -> nomeServico == null || agenda.getServicosBarbearia().getServices().name().equalsIgnoreCase(nomeServico))
                .collect(Collectors.toList());
    }

    public List<Agenda> gerarHorarios(Profissional profissional,
                                      Barbearia barbearia,
                                      ServicosBarbearia servico,
                                      LocalDate data, LocalTime inicio,
                                      LocalTime fim, int intervaloMinutos, JornadaEnum jornada) {
        List<Agenda> horarios = new ArrayList<>();
        LocalTime horarioAtual = inicio;
        while (horarioAtual.isBefore(fim)) {
            Agenda entity = new Agenda();
            entity.setProfissional(profissional);
            entity.setBarbearia(barbearia);
            entity.setServicosBarbearia(servico);
            entity.setDate(data);
            entity.setTime(horarioAtual);
            entity.setJornada(jornada);
            entity.setStatusAgenda(StatusAgenda.ABERTO);

            horarios.add(entity);
            horarioAtual = horarioAtual.plusMinutes(intervaloMinutos);
        }
        return horarios;
    }
}