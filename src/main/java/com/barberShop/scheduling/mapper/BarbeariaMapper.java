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

    @Mapping(target = "enderecoBarbearia.barbearia.cnpj", source = "cnpj")
    @Mapping(target = "enderecoBarbearia.cep", source = "cep")
    @Mapping(target = "enderecoBarbearia.logradouro", source = "logradouro")
    @Mapping(target = "enderecoBarbearia.bairro", source = "bairro")
    @Mapping(target = "enderecoBarbearia.cidade", source = "cidade")
    @Mapping(target = "enderecoBarbearia.estado ", source = "estado")
    @Mapping(target = "enderecoBarbearia.numero ", source = "numero")
    @Mapping(target = "enderecoBarbearia.complemento ", source = "complemento")
    Barbearia convertDtoToEntity(BarbeariaRequest request);


    @Mapping(target = "cnpj", source = "enderecoBarbearia.barbearia.cnpj")
    @Mapping(target = "logradouro", source = "enderecoBarbearia.logradouro")
    @Mapping(target = "bairro", source = "enderecoBarbearia.bairro")
    @Mapping(target = "cidade", source = "enderecoBarbearia.cidade")
    @Mapping(target = "estado", source = "enderecoBarbearia.estado")
    @Mapping(target = "numero", source = "enderecoBarbearia.numero")
    @Mapping(target = "complemento", source = "enderecoBarbearia.complemento")
    BarbeariaResponse convertEntityToDto(Barbearia barbearia);

    @Mapping(target = "message", constant = "Barbearia cadastrado com sucesso")
    BarbeariaRegisterResponse convertEntityToClienteRegisterResponse(Barbearia barbearia);

}
