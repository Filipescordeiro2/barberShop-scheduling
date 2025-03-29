package com.barberShop.scheduling.service.barbearia;

import com.barberShop.scheduling.dto.response.barbearia.BarbeariaResponse;
import com.barberShop.scheduling.exception.BarbeariaException;
import com.barberShop.scheduling.mapper.barbearia.BarbeariaMapper;
import com.barberShop.scheduling.repository.barbearia.BarbeariaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BarbeariaFindBarbeariaService {

    private final BarbeariaRepository barbeariaRepository;

    public BarbeariaResponse findBarbeariaByCNPJ(String cnpj) {
        log.info("Inicializado o metodo de [findBarbeariaByCNPJ] - CNPJ: {}", cnpj);
        try {
            var barbearia = barbeariaRepository.findById(cnpj)
                    .orElseThrow(() -> {
                        return new BarbeariaException("Barbearia not found");
                    });

            BarbeariaResponse response = BarbeariaMapper.INSTANCE.convertEntityToDto(barbearia);
            log.info("Finalizado o metodo de [findBarbeariaByCNPJ] - Response: {}", response);
            return response;
        } catch (Exception e) {
            throw new BarbeariaException("Error finding barbearia: " + e.getMessage());
        }
    }
}