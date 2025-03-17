package com.barberShop.scheduling.service;

import com.barberShop.scheduling.dto.request.BarbeariaRequest;
import com.barberShop.scheduling.dto.response.BarbeariaDeseableResponse;
import com.barberShop.scheduling.dto.response.BarbeariaRegisterResponse;
import com.barberShop.scheduling.dto.response.BarbeariaResponse;
import com.barberShop.scheduling.exception.BarbeariaException;
import com.barberShop.scheduling.mapper.BarbeariaMapper;
import com.barberShop.scheduling.repository.BarbeariaRepository;
import com.barberShop.scheduling.utils.BarbeariaUtils;
import com.barberShop.scheduling.utils.BarbeariaValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarbeariaService {

    private final BarbeariaRepository barbeariaRepository;
    private final BarbeariaValidation barbeariaValidation;
    private final BarbeariaUtils barbeariaUtils;

    public BarbeariaRegisterResponse createBarbearia (BarbeariaRequest request){
        try {

            barbeariaValidation.validPreSave(request);
            var barbearia = BarbeariaMapper.INSTANCE.convertDtoToEntity(request);
            return BarbeariaMapper.INSTANCE.
                    convertEntityToBarbeariaRegisterResponse(barbeariaRepository.save(barbearia));

        }catch (Exception e){
            throw new BarbeariaException("Error creating barbearia: " + e.getMessage());
        }
    }

    public BarbeariaResponse findBarbeariaByCNPJ(String cnpj){
        try {

            var barbearia = barbeariaRepository.findById(cnpj)
                    .orElseThrow(() -> new BarbeariaException("Barbearia not found"));
            return BarbeariaMapper.INSTANCE.convertEntityToDto(barbearia);

        }catch (Exception e){
            throw new BarbeariaException("Error finding barbearia: " + e.getMessage());
        }
    }

    public BarbeariaDeseableResponse deseableBarbearia(String cnpj) {
        try {

            var barberia = barbeariaValidation.validateAndGetBarbearia(cnpj);
            barbeariaUtils.cancelAgendasAndAgendamentos(barberia.getCnpj());
            barberia.setActive(false);
            return BarbeariaMapper.INSTANCE
                    .convertEntityToBarbeariaDeseableResponse(barbeariaRepository.save(barberia));

        } catch (Exception e) {
            throw new BarbeariaException("Error finding barbearia: " + e.getMessage());
        }
    }
}
