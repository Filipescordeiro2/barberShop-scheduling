package com.barberShop.scheduling.service.barbearia;

import com.barberShop.scheduling.dto.request.barbearia.BarbeariaRequest;
import com.barberShop.scheduling.dto.response.barbearia.BarbeariaRegisterResponse;
import com.barberShop.scheduling.exception.BarbeariaException;
import com.barberShop.scheduling.mapper.barbearia.BarbeariaMapper;
import com.barberShop.scheduling.repository.barbearia.BarbeariaRepository;
import com.barberShop.scheduling.validation.barbearia.BarbeariaValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BarbeariaRegistrationService {

    private final BarbeariaRepository barbeariaRepository;
    private final BarbeariaValidation barbeariaValidation;

    public BarbeariaRegisterResponse createBarbearia(BarbeariaRequest request) {
        log.info("Inicializado o metodo de [createBarbearia] - Request: {}", request);
        try {
            barbeariaValidation.validatePreSaveBarbearia(request);

            var barbearia = BarbeariaMapper.INSTANCE.convertDtoToEntity(request);
            BarbeariaRegisterResponse response = BarbeariaMapper.INSTANCE
                    .convertEntityToBarbeariaRegisterResponse(barbeariaRepository.save(barbearia));

            log.info("Finalizado o metodo de [createBarbearia] - Response: {}", response);
            return response;
        } catch (Exception e) {
            throw new BarbeariaException("Error creating barbearia: " + e.getMessage());
        }
    }
}