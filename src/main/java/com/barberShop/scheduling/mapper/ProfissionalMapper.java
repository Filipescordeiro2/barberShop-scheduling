package com.barberShop.scheduling.mapper;

import com.barberShop.scheduling.domain.Agendamento;
import com.barberShop.scheduling.domain.Profissional;
import com.barberShop.scheduling.dto.request.ProfissionalRequest;
import com.barberShop.scheduling.dto.response.AgendamentoCancelarResponse;
import com.barberShop.scheduling.dto.response.ProfissioanalDeseableResponse;
import com.barberShop.scheduling.dto.response.ProfissionalRegisterResponse;
import com.barberShop.scheduling.dto.response.ProfissionalReponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfissionalMapper {

    ProfissionalMapper INSTANCE = Mappers.getMapper(ProfissionalMapper.class);

    Profissional convertDtoToEntity(ProfissionalRequest request);

    ProfissionalReponse convertEntityToDto(Profissional profissional);

    @Mapping(target = "message", constant = "profissional desabilitado com sucesso")
    ProfissioanalDeseableResponse convertEntityProfissioanalDeseableResponse(Profissional profissional);

    @Mapping(target = "message", constant = "Professional successfully registered")
    ProfissionalRegisterResponse convertEntityToClienteRegisterResponse(Profissional profissional);

}
