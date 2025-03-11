package com.barberShop.scheduling.mapper;

import com.barberShop.scheduling.domain.Cliente;
import com.barberShop.scheduling.dto.request.ClienteRequest;
import com.barberShop.scheduling.dto.response.ClienteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente convertDtoToEntity(ClienteRequest request);

    ClienteResponse convertEntityToDto(Cliente cliente);

    List<ClienteResponse> convertListEntityToListResponse(Iterable<Cliente> cliente);

}
