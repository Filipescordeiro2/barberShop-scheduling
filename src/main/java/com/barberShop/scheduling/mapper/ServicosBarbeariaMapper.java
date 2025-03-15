package com.barberShop.scheduling.mapper;

import com.barberShop.scheduling.domain.ServicosBarbearia;
import com.barberShop.scheduling.dto.request.ServicosBarbeariaRequest;
import com.barberShop.scheduling.dto.response.ServicosBarbeariaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServicosBarbeariaMapper {

    ServicosBarbeariaMapper INSTANCE = Mappers.getMapper(ServicosBarbeariaMapper.class);

    @Mapping(target = "barbearia.cnpj", source = "cnpjBarbearia")
    @Mapping(target = "services", source = "servico")
    ServicosBarbearia convertDtoToEntity(ServicosBarbeariaRequest request);

    @Mapping(target = "cnpjBarbearia", source = "barbearia.cnpj")
    @Mapping(target = "message", constant = "Serviço de barbearia cadastrado com sucesso")
    ServicosBarbeariaResponse convertEntityToServicosBarbeariaRegisterResponse(ServicosBarbearia servicosBarbearia);
}