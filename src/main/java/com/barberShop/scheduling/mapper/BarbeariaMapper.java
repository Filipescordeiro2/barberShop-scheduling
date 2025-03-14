package com.barberShop.scheduling.mapper;

import com.barberShop.scheduling.domain.Barbearia;
import com.barberShop.scheduling.dto.request.BarbeariaRequest;
import com.barberShop.scheduling.dto.response.BarbeariaRegisterResponse;
import com.barberShop.scheduling.dto.response.BarbeariaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BarbeariaMapper {

    BarbeariaMapper INSTANCE = Mappers.getMapper(BarbeariaMapper.class);

    Barbearia convertDtoToEntity(BarbeariaRequest request);

    @Mapping(target = "enderecoBarbearia", source = "enderecoBarbearia")
    BarbeariaResponse convertEntityToDto(Barbearia barbearia);

    @Mapping(target = "message", constant = "Barbearia cadastrado com sucesso")
    BarbeariaRegisterResponse convertEntityToClienteRegisterResponse(Barbearia barbearia);

}
