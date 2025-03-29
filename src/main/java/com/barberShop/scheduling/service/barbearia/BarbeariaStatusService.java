package com.barberShop.scheduling.service.barbearia;

import com.barberShop.scheduling.dto.response.barbearia.BarbeariaDeseableResponse;
import com.barberShop.scheduling.enums.CancelType;
import com.barberShop.scheduling.exception.BarbeariaException;
import com.barberShop.scheduling.mapper.barbearia.BarbeariaMapper;
import com.barberShop.scheduling.repository.barbearia.BarbeariaRepository;
import com.barberShop.scheduling.utils.agenda.AgendaCancelManager;
import com.barberShop.scheduling.utils.agendamento.AgendamentoCancelador;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BarbeariaStatusService {

    private final BarbeariaRepository barbeariaRepository;
    private final AgendamentoCancelador agendamentoCancelador;
    private final AgendaCancelManager agendaCancelManager;

    public BarbeariaDeseableResponse deseableBarbearia(String cnpj) {
        log.info("Inicializado o metodo de [deseableBarbearia] - CNPJ: {}", cnpj);
        try {
            var barberia = barbeariaRepository.findById(cnpj)
                    .orElseThrow(() -> {
                        return new BarbeariaException("Barbearia not found");
                    });

            agendaCancelManager.cancelAgendas(barberia.getCnpj(), CancelType.BARBEARIA);
            agendamentoCancelador.cancelarAgendamentosBarbearia(barberia.getCnpj());

            barberia.setActive(false);
            BarbeariaDeseableResponse response = BarbeariaMapper.INSTANCE
                    .convertEntityToBarbeariaDeseableResponse(barbeariaRepository.save(barberia));

            log.info("Finalizado o metodo de [deseableBarbearia] - Response: {}", response);
            return response;
        } catch (Exception e) {
            throw new BarbeariaException("Error finding barbearia: " + e.getMessage());
        }
    }
}