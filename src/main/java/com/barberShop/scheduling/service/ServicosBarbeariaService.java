package com.barberShop.scheduling.service;

import com.barberShop.scheduling.dto.request.ServicosBarbeariaRequest;
import com.barberShop.scheduling.dto.response.ServicosBarbeariaResponse;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.mapper.ServicosBarbeariaMapper;
import com.barberShop.scheduling.repository.ServicosBarbeariaRepository;
import com.barberShop.scheduling.utils.ServicoBarbeariaValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicosBarbeariaService {

    private final ServicosBarbeariaRepository servicosBarbeariaRepository;
    private final ServicoBarbeariaValidation servicoBarbeariaValidation;

    public ServicosBarbeariaResponse createServicosBarbearia(ServicosBarbeariaRequest request) {
        try {
            servicoBarbeariaValidation.validateServicoPreSave(request.getServico(), request.getCnpjBarbearia());

            var servicosBarbearia = ServicosBarbeariaMapper.INSTANCE.convertDtoToEntity(request);
            return ServicosBarbeariaMapper.INSTANCE
                    .convertEntityToServicosBarbeariaRegisterResponse(servicosBarbeariaRepository.save(servicosBarbearia));
        } catch (Exception e) {
            throw new ServicosBarbeariaException("Error creating services barbearia: " + e.getMessage());
        }
    }

    public ServicosBarbeariaResponse updateServicosBarbearia(ServicosBarbeariaRequest request) {
        try {
            var servicoBarbearia = servicosBarbeariaRepository
                    .findByServicesAndBarbeariaCnpj(request.getServico(),request.getCnpjBarbearia())
                    .orElseThrow(()-> new ServicosBarbeariaException("Service not registered for this barber shop"));

            servicoBarbearia.setPrice(request.getPrice());
            servicoBarbearia.setTimeOfJob(request.getTimeOfJob());

            return ServicosBarbeariaMapper.INSTANCE
                    .convertEntityToServicosBarbeariaRegisterResponse(servicosBarbeariaRepository.save(servicoBarbearia));

        } catch (Exception e) {
            throw new ServicosBarbeariaException("Error updating services barbearia: " + e.getMessage());
        }
    }

}
