package com.barberShop.scheduling.mapper;

import com.barberShop.scheduling.domain.Agendamento;
import com.barberShop.scheduling.dto.response.AgendamentoCancelarResponse;
import com.barberShop.scheduling.dto.response.AgendamentoDetalhadaResponse;
import com.barberShop.scheduling.dto.response.AgendamentoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AgendamentoMapper {

    AgendamentoMapper INSTANCE = Mappers.getMapper(AgendamentoMapper.class);

    @Mapping(target = "mesage", constant = "Agendamento criado com sucesso")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    AgendamentoResponse convertEntityToResponse(Agendamento agendamento);

    @Mapping(target = "idAgenda", source = "agenda.id")
    @Mapping(target = "data", source = "agenda.date")
    @Mapping(target = "hora", source = "agenda.time")
    @Mapping(target = "periodoAgenda", expression = "java(agendamento.getAgenda().getJornada().name())")
    @Mapping(target = "statusAgenda", expression = "java(agendamento.getAgenda().getStatusAgenda().name())")
    @Mapping(target = "namebarbearia", source = "agenda.barbearia.nameBarber")
    @Mapping(target = "emailBarbearia", source = "agenda.barbearia.email")
    @Mapping(target = "celularBarbearia", source = "agenda.barbearia.phone")
    @Mapping(target = "nomeProfissional", source = "agenda.profissional.nameProfissional")
    @Mapping(target = "nomeServico", expression = "java(agendamento.getAgenda().getServicosBarbearia().getServices().name())")
    @Mapping(target = "descricaoServico", source = "agenda.servicosBarbearia.services.descricaoDetalhada")
    @Mapping(target = "cpfCliente", source = "cliente.cpf")
    @Mapping(target = "nomeCliente", source = "cliente.name")
    @Mapping(target = "emailCliente", source = "cliente.email")
    @Mapping(target = "celularCliente", source = "cliente.phone")
    @Mapping(target = "dataDeNascimentoCliente", source = "cliente.dateOfBirth")
    @Mapping(target = "idade", source = "cliente.age")
    AgendamentoDetalhadaResponse convertEntityToDetalhadaResponse(Agendamento agendamento);

    @Mapping(target = "message", constant = "Agendamento cancelado com sucesso")
    @Mapping(target = "cpfCliente", source = "cliente.cpf")
    @Mapping(target = "idAgendamento", source = "id")
    AgendamentoCancelarResponse convertEntityToCancelarResponse(Agendamento agendamento);



    List<AgendamentoDetalhadaResponse> convertEntityListToDetalhadaResponseList(List<Agendamento> agendamentos);
}