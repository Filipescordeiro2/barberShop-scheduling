package com.barberShop.scheduling.utils;


import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.domain.Agendamento;
import com.barberShop.scheduling.domain.Cliente;
import com.barberShop.scheduling.dto.response.AgendamentoDetalhadaResponse;
import com.barberShop.scheduling.dto.response.AgendamentoResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AgendamentoUtils {

    public AgendamentoResponse convertAgendamentoResponse(Agendamento agendamento) {
        return AgendamentoResponse.builder()
                .mesage("Agendamento criado com sucesso")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public List<AgendamentoDetalhadaResponse> createAgendamentoDetalhadaResponse(Agenda agenda, Cliente cliente) {
        return List.of(new AgendamentoDetalhadaResponse(
                agenda.getId(),
                agenda.getDate(),
                agenda.getTime(),
                agenda.getJornada().name(),
                agenda.getStatusAgenda().name(),
                agenda.getBarbearia().getNameBarber(),
                agenda.getBarbearia().getEmail(),
                agenda.getBarbearia().getPhone(),
                agenda.getProfissional().getNameProfissional(),
                agenda.getServicosBarbearia().getServices().name(),
                agenda.getServicosBarbearia().getServices().getDescricaoDetalhada(),
                cliente.getCpf(),
                cliente.getName(),
                cliente.getEmail(),
                cliente.getPhone(),
                cliente.getDateOfBirth(),
                cliente.getAge()
        ));
    }
}