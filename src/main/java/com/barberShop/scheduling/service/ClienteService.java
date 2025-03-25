package com.barberShop.scheduling.service;

import com.barberShop.scheduling.dto.request.ClienteRequest;
import com.barberShop.scheduling.dto.request.LoginRequest;
import com.barberShop.scheduling.dto.response.ClienteActivateResponse;
import com.barberShop.scheduling.dto.response.ClienteDeseableResponse;
import com.barberShop.scheduling.dto.response.ClienteRegisterResponse;
import com.barberShop.scheduling.dto.response.ClienteResponse;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.mapper.ClienteMapper;
import com.barberShop.scheduling.repository.ClienteRepository;
import com.barberShop.scheduling.utils.ClienteUtils;
import com.barberShop.scheduling.utils.ClienteValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteValidation clienteValidation;
    private final ClienteUtils clienteUtils;

    public ClienteRegisterResponse registerCliente (ClienteRequest request){
        try {
            clienteValidation.validPreSave(request);
            var cliente = ClienteMapper.INSTANCE.convertDtoToEntity(request);
            return ClienteMapper.INSTANCE.convertEntityToClienteRegisterResponse(clienteRepository.save(cliente));
        }catch (Exception e){
            throw new ClienteException("Error registering cliente: " + e.getMessage());
        }
    }

    public ClienteResponse findById(String cpf){
        return clienteRepository.findById(cpf)
                .map(ClienteMapper.INSTANCE::convertEntityToDto)
                .orElseThrow(() -> new ClienteException("Cliente not found with CPF: " + cpf));
    }

    public ClienteResponse authenticateCliente(LoginRequest request){
        try{
            var cliente = clienteRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())
                    .orElseThrow(() -> new ClienteException("Invalid login or password"));

            if (!cliente.isActive()) {
                throw new ClienteException("Cliente is not active");
            }

            return ClienteMapper.INSTANCE.convertEntityToDto(cliente);

        }catch (Exception e){
            throw new ClienteException("Error authenticating cliente: " + e.getMessage());
        }

    }

    public ClienteDeseableResponse deseableCliente(String cpf) {
        try {

            var cliente = clienteValidation.findByCpf(cpf);
            clienteUtils.deseableAgendamento(cliente);
            cliente.setActive(false);
            clienteRepository.save(cliente);

            return ClienteMapper.INSTANCE
                    .convertEntityToClienteDeseableResponse(cliente);

        } catch (Exception e) {
            throw new ClienteException("Error deseable cliente: " + e.getMessage());
        }
    }

    public ClienteActivateResponse activateCliente(String cpf) {
        try {

            var cliente = clienteValidation.findByCpf(cpf);

            if (cliente.isActive()) {
                throw new ClienteException("Cliente is already active");
            }
            cliente.setActive(true);
            clienteRepository.save(cliente);

            return ClienteMapper.INSTANCE
                    .convertEntityToClienteActivateResponse(cliente);

        } catch (Exception e) {
            throw new ClienteException("Error activate cliente: " + e.getMessage());
        }
    }

}
