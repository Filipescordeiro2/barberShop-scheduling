package com.barberShop.scheduling.service.profissional;


import com.barberShop.scheduling.dto.response.profissional.ProfissioanalDeseableResponse;
import com.barberShop.scheduling.enums.CancelType;
import com.barberShop.scheduling.exception.ProfissionalException;
import com.barberShop.scheduling.mapper.profissional.ProfissionalMapper;
import com.barberShop.scheduling.repository.profissional.ProfissionalRepository;
import com.barberShop.scheduling.utils.agenda.AgendaCancelManager;
import com.barberShop.scheduling.utils.agendamento.AgendamentoCancelador;
import com.barberShop.scheduling.validation.profissional.ProfissionalValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalStatusService {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalValidation profissionalValidation;
    private final AgendamentoCancelador agendamentoCancelador;
    private final AgendaCancelManager agendaCancelManager;

    public ProfissioanalDeseableResponse deseableProfissional(String cpfProfissional) {
        try {
            var profissional = profissionalRepository.findById(cpfProfissional)
                    .orElseThrow(() -> new ProfissionalException("Profissional not found with CPF: " + cpfProfissional));

            profissionalValidation.validateProfissionalIsActive(profissional);

            profissional.setActive(false);

            agendaCancelManager.cancelAgendas(cpfProfissional, CancelType.PROFISSIONAL);
            agendamentoCancelador.cancelarAgendamentosProfissional(cpfProfissional);

            return ProfissionalMapper.INSTANCE
                    .convertEntityProfissioanalDeseableResponse(profissionalRepository.save(profissional));
        } catch (Exception e) {
            throw new ProfissionalException("Error registering profissional: " + e.getMessage());
        }
    }
}
