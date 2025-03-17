package com.barberShop.scheduling.utils;


import com.barberShop.scheduling.enums.ServicosEnum;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.repository.ServicosBarbeariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicoBarbeariaValidation {

    private final ServicosBarbeariaRepository servicosBarbeariaRepository;

    public void validateServicoPreSave(ServicosEnum servico, String cnpjBarbearia) {
        var existingService = servicosBarbeariaRepository.findByServicesAndBarbeariaCnpjAndActiveTrue(servico, cnpjBarbearia);
        if (existingService.isPresent()) {
            throw new ServicosBarbeariaException("Service already registered for this barber shop.");
        }
    }

    public void validateServicoPreUpdate(ServicosEnum servico, String cnpjBarbearia) {
        var existingService = servicosBarbeariaRepository.findByServicesAndBarbeariaCnpjAndActiveTrue(servico, cnpjBarbearia);
        if (existingService.isPresent()) {
            throw new ServicosBarbeariaException("Service already registered for this barber shop.");
        }
    }
}
