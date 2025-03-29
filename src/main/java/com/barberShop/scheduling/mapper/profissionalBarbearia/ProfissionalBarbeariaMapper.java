package com.barberShop.scheduling.mapper.profissionalBarbearia;

import com.barberShop.scheduling.domain.ProfissionalBarbearia;
import com.barberShop.scheduling.dto.request.ProfissionalBarbeariaRequest;
import com.barberShop.scheduling.dto.response.profissionalBarbearia.ProfissionalBarbeariaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfissionalBarbeariaMapper {

    ProfissionalBarbeariaMapper INSTANCE = Mappers.getMapper(ProfissionalBarbeariaMapper.class);

    @Mapping(target = "barbearia.cnpj", source = "cnpjBarbearia")
    @Mapping(target = "profissional.cpf", source = "cpfProfissional")
    ProfissionalBarbearia convertDtoToEntity(ProfissionalBarbeariaRequest request);

    @Mapping(target = "cnpjBarbearia", source = "barbearia.cnpj")
    @Mapping(target = "cpfProfissional", source = "profissional.cpf")
    @Mapping(target = "message", constant = "Professional linked to barber shop successfully")
    ProfissionalBarbeariaResponse convertEntityToProfissionalBarbeariaResponse(ProfissionalBarbearia profissionalBarbearia);

}
