package com.barberShop.scheduling.service;

import com.barberShop.scheduling.dto.request.ProfissionalRequest;
import com.barberShop.scheduling.dto.response.ProfissionalRegisterResponse;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.mapper.ProfissionalMapper;
import com.barberShop.scheduling.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    public ProfissionalRegisterResponse registerProfissional (ProfissionalRequest request){
        try {
            var profissional = ProfissionalMapper.INSTANCE.convertDtoToEntity(request);
            return ProfissionalMapper.INSTANCE.convertEntityToClienteRegisterResponse(profissionalRepository.save(profissional));
        }catch (Exception e){
            throw new ClienteException("Error registering profissional: " + e.getMessage());
        }
    }
}
