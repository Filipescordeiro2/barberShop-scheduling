package com.barberShop.scheduling.service.profissional;


import com.barberShop.scheduling.dto.request.profissional.ProfissionalRequest;
import com.barberShop.scheduling.dto.response.profissional.ProfissionalRegisterResponse;
import com.barberShop.scheduling.exception.ProfissionalException;
import com.barberShop.scheduling.mapper.profissional.ProfissionalMapper;
import com.barberShop.scheduling.repository.profissional.ProfissionalRepository;
import com.barberShop.scheduling.validation.profissional.ProfissionalValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalRegistrationService {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalValidation profissionalValidation;


    public ProfissionalRegisterResponse registerProfissional (ProfissionalRequest request){
        try {
           profissionalValidation.validatePreSaveProfissional(request);

            var profissional = ProfissionalMapper.INSTANCE.convertDtoToEntity(request);

            return ProfissionalMapper.INSTANCE
                    .convertEntityToClienteRegisterResponse(profissionalRepository.save(profissional));
        }catch (Exception e){
            throw new ProfissionalException("Error registering profissional: " + e.getMessage());
        }
    }
}
