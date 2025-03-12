package com.barberShop.scheduling.service;

import com.barberShop.scheduling.dto.request.ClienteRequest;
import com.barberShop.scheduling.dto.request.LoginRequest;
import com.barberShop.scheduling.dto.response.ClienteRegisterResponse;
import com.barberShop.scheduling.dto.response.ClienteResponse;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.mapper.ClienteMapper;
import com.barberShop.scheduling.repository.ClienteRepository;
import com.barberShop.scheduling.utils.ClienteValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteValidation clienteValidation;

    public ClienteRegisterResponse save (ClienteRequest request){
        try {
            clienteValidation.validPreSave(request);
            var cliente = ClienteMapper.INSTANCE.convertDtoToEntity(request);
            return ClienteMapper.INSTANCE.convertEntityToClienteRegisterResponse(clienteRepository.save(cliente));
        }catch (Exception e){
            throw new ClienteException(e.getMessage());
        }
    }

    public ClienteResponse findById(String cpf){
        return clienteRepository.findById(cpf)
                .map(ClienteMapper.INSTANCE::convertEntityToDto)
                .orElseThrow(() -> new ClienteException("Cliente não encontrado"));
    }

    public ClienteResponse authCliente(LoginRequest request){
        return clienteRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())
                .map(ClienteMapper.INSTANCE::convertEntityToDto)
                .orElseThrow(() -> new ClienteException("Login ou senha invalidos"));
    }

}
