package com.barberShop.scheduling.service;

import com.barberShop.scheduling.dto.request.ClienteRequest;
import com.barberShop.scheduling.dto.response.ClienteResponse;
import com.barberShop.scheduling.mapper.ClienteMapper;
import com.barberShop.scheduling.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponse save (ClienteRequest request){
        try {
            var cliente = ClienteMapper.INSTANCE.convertDtoToEntity(request);
            return ClienteMapper.INSTANCE.convertEntityToDto(clienteRepository.save(cliente));
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar cliente");
        }
    }

}
