package com.barberShop.scheduling.service;

import com.barberShop.scheduling.dto.request.ServicosBarbeariaRequest;
import com.barberShop.scheduling.dto.response.ServicosBarbeariaResponse;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.mapper.ServicosBarbeariaMapper;
import com.barberShop.scheduling.repository.ServicosBarbeariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicosBarbeariaService {

    private final ServicosBarbeariaRepository servicosBarbeariaRepository;

    public ServicosBarbeariaResponse createServicosBarbearia (ServicosBarbeariaRequest request){
        try {
            var servicosBarbearia = ServicosBarbeariaMapper.INSTANCE.convertDtoToEntity(request);
            return ServicosBarbeariaMapper.INSTANCE.
                    convertEntityToServicosBarbeariaRegisterResponse(servicosBarbeariaRepository.save(servicosBarbearia));
        }catch (Exception e){
            throw new ServicosBarbeariaException("Error creating services barbearia: " + e.getMessage());
        }
    }
}
