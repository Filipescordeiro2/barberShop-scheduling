package com.barberShop.scheduling.service;

import com.barberShop.scheduling.dto.request.LoginRequest;
import com.barberShop.scheduling.dto.request.ProfissionalRequest;
import com.barberShop.scheduling.dto.response.ClienteResponse;
import com.barberShop.scheduling.dto.response.ProfissioanalDeseableResponse;
import com.barberShop.scheduling.dto.response.ProfissionalRegisterResponse;
import com.barberShop.scheduling.dto.response.ProfissionalReponse;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.exception.ProfissionalException;
import com.barberShop.scheduling.mapper.ClienteMapper;
import com.barberShop.scheduling.mapper.ProfissionalMapper;
import com.barberShop.scheduling.repository.ProfissionalRepository;
import com.barberShop.scheduling.utils.ProfissionalUtils;
import com.barberShop.scheduling.utils.ProfissionalValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalValidation profissionalValidation;
    private final ProfissionalUtils profissionalUtils;

    public ProfissionalRegisterResponse registerProfissional (ProfissionalRequest request){
        try {
            profissionalValidation.validPreSave(request);
            var profissional = ProfissionalMapper.INSTANCE.convertDtoToEntity(request);
            return ProfissionalMapper.INSTANCE.convertEntityToClienteRegisterResponse(profissionalRepository.save(profissional));
        }catch (Exception e){
            throw new ProfissionalException("Error registering profissional: " + e.getMessage());
        }
    }

    public ProfissionalReponse authenticateProfissional(LoginRequest request){
        return profissionalRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())
                .map(ProfissionalMapper.INSTANCE::convertEntityToDto)
                    .orElseThrow(() -> new ProfissionalException("Invalid login or password"));
    }

    public ProfissioanalDeseableResponse deseableProfissional(String cpfProfissional) {
        try {
            var profissional = profissionalValidation.validateProfissional(cpfProfissional);
            profissional.setActive(false);

            profissionalUtils.cancelAgendas(cpfProfissional);
            profissionalUtils.cancelAgendamentos(cpfProfissional);

            return ProfissionalMapper.INSTANCE
                    .convertEntityProfissioanalDeseableResponse(profissionalRepository.save(profissional));
        } catch (Exception e) {
            throw new ProfissionalException("Error registering profissional: " + e.getMessage());
        }
    }
}
