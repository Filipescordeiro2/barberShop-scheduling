package com.barberShop.scheduling.service;

import com.barberShop.scheduling.dto.request.ClienteRequest;
import com.barberShop.scheduling.dto.response.ClienteRegisterResponse;
import com.barberShop.scheduling.dto.response.ClienteResponse;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.mapper.ClienteMapper;
import com.barberShop.scheduling.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteRegisterResponse save (ClienteRequest request){
        try {
            var cliente = ClienteMapper.INSTANCE.convertDtoToEntity(request);
            return ClienteMapper.INSTANCE.convertEntityToClienteRegisterResponse(clienteRepository.save(cliente));
        }catch (Exception e){
            throw new ClienteException("Erro ao salvar cliente "+e.getMessage());
        }
    }

    public ClienteResponse findById(String cpf){
        return clienteRepository.findById(cpf)
                .map(ClienteMapper.INSTANCE::convertEntityToDto)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado"));
    }
}
