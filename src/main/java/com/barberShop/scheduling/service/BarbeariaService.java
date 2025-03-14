package com.barberShop.scheduling.service;

import com.barberShop.scheduling.dto.request.BarbeariaRequest;
import com.barberShop.scheduling.dto.response.BarbeariaRegisterResponse;
import com.barberShop.scheduling.exception.BarbeariaException;
import com.barberShop.scheduling.mapper.BarbeariaMapper;
import com.barberShop.scheduling.repository.BarbeariaRepository;
import com.barberShop.scheduling.utils.BarbeariaValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarbeariaService {

    private final BarbeariaRepository barbeariaRepository;
    private final BarbeariaValidation barbeariaValidation;

    public BarbeariaRegisterResponse createBarbearia (BarbeariaRequest request){
        try {
            barbeariaValidation.validPreSave(request);
            var barbearia = BarbeariaMapper.INSTANCE.convertDtoToEntity(request);
            return BarbeariaMapper.INSTANCE.convertEntityToClienteRegisterResponse(barbeariaRepository.save(barbearia));
        }catch (Exception e){
            throw new BarbeariaException("Error creating barbearia: " + e.getMessage());
        }
    }
}
