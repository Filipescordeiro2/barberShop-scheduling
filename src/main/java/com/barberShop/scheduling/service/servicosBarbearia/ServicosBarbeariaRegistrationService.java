package com.barberShop.scheduling.service.servicosBarbearia;

import com.barberShop.scheduling.dto.request.servicosBarbearia.ServicosBarbeariaRequest;
import com.barberShop.scheduling.dto.response.servicosBarbearia.ServicosBarbeariaResponse;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.mapper.servicosBarbearia.ServicosBarbeariaMapper;
import com.barberShop.scheduling.repository.servicosBarbearia.ServicosBarbeariaRepository;
import com.barberShop.scheduling.validation.servicosBarbearia.ServicosBarbeariaValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicosBarbeariaRegistrationService {

    private final ServicosBarbeariaRepository servicosBarbeariaRepository;
    private final ServicosBarbeariaValidation servicosBarbeariaValidation;

    public ServicosBarbeariaResponse createServicosBarbearia(ServicosBarbeariaRequest request) {
        try {
            // Validações antes de salvar
            servicosBarbeariaValidation.validPreSaveServicos(request.getServico(), request.getCnpjBarbearia());

            // Conversão e salvamento
            var servicosBarbearia = ServicosBarbeariaMapper.INSTANCE.convertDtoToEntity(request);

            return ServicosBarbeariaMapper.INSTANCE
                    .convertEntityToServicosBarbeariaRegisterResponse(servicosBarbeariaRepository.save(servicosBarbearia));

        } catch (Exception e) {
            throw new ServicosBarbeariaException("Error creating services barbearia: " + e.getMessage());
        }
    }
}