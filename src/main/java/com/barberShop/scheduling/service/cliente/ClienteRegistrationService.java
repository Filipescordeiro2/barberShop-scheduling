package com.barberShop.scheduling.service.cliente;

import com.barberShop.scheduling.dto.request.cliente.ClienteRequest;
import com.barberShop.scheduling.dto.response.cliente.ClienteRegisterResponse;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.mapper.cliente.ClienteMapper;
import com.barberShop.scheduling.repository.cliente.ClienteRepository;
import com.barberShop.scheduling.validation.cliente.ClienteValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClienteRegistrationService {

    private final ClienteRepository clienteRepository;
    private final ClienteValidation clienteValidation;

    public ClienteRegisterResponse registerCliente(ClienteRequest request) {
        log.info("Inicializado o servico [registerCliente] -- Request: {}",request);
        try {
            clienteValidation.validatePreSaveCliente(request);

            var cliente = ClienteMapper.INSTANCE.convertDtoToEntity(request);
            var clienteSaved = clienteRepository.save(cliente);

            log.info("Finalizado o servico [registerCliente] -- Cliente Gerado: {}",clienteSaved);
            return ClienteMapper.INSTANCE
                    .convertEntityToClienteRegisterResponse(clienteSaved);
        } catch (Exception e) {
            throw new ClienteException("Error registering cliente: " + e.getMessage());
        }
    }
}
