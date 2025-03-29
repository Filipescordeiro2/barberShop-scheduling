package com.barberShop.scheduling.mapper.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.dto.request.agenda.AgendaRequest;
import com.barberShop.scheduling.dto.response.agenda.AgendaCancelarResponse;
import com.barberShop.scheduling.dto.response.agenda.AgendaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AgendaMapper {

    AgendaMapper INSTANCE = Mappers.getMapper(AgendaMapper.class);

    @Mapping(target = "profissional.cpf", source = "cpfProfissional")
    @Mapping(target = "barbearia.cnpj", source = "cnpjBarbearia")
    @Mapping(target = "servicosBarbearia.id", source = "servicosBarbeariaId")
    Agenda convertDtoToEntity(AgendaRequest request);

    @Mapping(target = "cpfProfissional", source = "profissional.cpf")
    @Mapping(target = "cnpjBarbearia", source = "barbearia.cnpj")
    @Mapping(target = "servicosBarbeariaId", source = "servicosBarbearia.id")
    AgendaRequest convertEntityToDto(Agenda agenda);

    @Mapping(target = "message", constant = "Agenda successfully created")
    AgendaResponse convertEntityToResponse(Agenda agenda);

    @Mapping(target = "message", constant = "Agenda successfully canceled")
    @Mapping(target = "cpfProfissional", source = "profissional.cpf")
    @Mapping(target = "idAgenda", source = "id")
    AgendaCancelarResponse convertEntityToCancelarResponse(Agenda agenda);

    List<AgendaResponse> convertEntityListToResponseList(List<Agenda> agendas);
}