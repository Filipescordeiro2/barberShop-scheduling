package com.barberShop.scheduling.mapper;

import com.barberShop.scheduling.domain.Cliente;
import com.barberShop.scheduling.dto.request.ClienteRequest;
import com.barberShop.scheduling.dto.response.ClienteActivateResponse;
import com.barberShop.scheduling.dto.response.ClienteDeseableResponse;
import com.barberShop.scheduling.dto.response.ClienteRegisterResponse;
import com.barberShop.scheduling.dto.response.ClienteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente convertDtoToEntity(ClienteRequest request);

    ClienteResponse convertEntityToDto(Cliente cliente);

    @Mapping(target = "message", constant = "Client successfully registered")
    ClienteRegisterResponse convertEntityToClienteRegisterResponse(Cliente cliente);

    @Mapping(target = "message", constant = "Client successfully Deseable")
    ClienteDeseableResponse convertEntityToClienteDeseableResponse(Cliente cliente);

    @Mapping(target = "message", constant = "Client successfully Activate")
    ClienteActivateResponse convertEntityToClienteActivateResponse(Cliente cliente);

    List<ClienteResponse> convertListEntityToListResponse(Iterable<Cliente> cliente);

}
