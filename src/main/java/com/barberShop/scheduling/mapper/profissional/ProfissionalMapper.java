package com.barberShop.scheduling.mapper.profissional;

import com.barberShop.scheduling.domain.Profissional;
import com.barberShop.scheduling.dto.request.profissional.ProfissionalRequest;
import com.barberShop.scheduling.dto.response.profissional.ProfissioanalDeseableResponse;
import com.barberShop.scheduling.dto.response.profissional.ProfissionalRegisterResponse;
import com.barberShop.scheduling.dto.response.profissional.ProfissionalReponse;
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
