package com.barberShop.scheduling.service.cliente;

import com.barberShop.scheduling.dto.response.cliente.ClienteActivateResponse;
import com.barberShop.scheduling.dto.response.cliente.ClienteDeseableResponse;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.mapper.cliente.ClienteMapper;
import com.barberShop.scheduling.repository.cliente.ClienteRepository;
import com.barberShop.scheduling.utils.agenda.AgendaCreatedManager;
import com.barberShop.scheduling.utils.agendamento.AgendamentoCancelador;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClienteStatusService {

    private final ClienteRepository clienteRepository;
    private final AgendamentoCancelador agendamentoCancelador;
    private final AgendaCreatedManager agendaCreatedManager;

    public ClienteDeseableResponse deseableCliente(String cpf) {
        log.info("Inicializado o servico [deseableCliente] -- Cliente: {}", cpf);
        try {
            var cliente = clienteRepository.findById(cpf)
                    .orElseThrow(() -> {
                        return new ClienteException("Cliente not found with CPF: " + cpf);
                    });

            cliente.setActive(false);
            clienteRepository.save(cliente);

            agendamentoCancelador.cancelarAgendamentosCliente(cliente);
            agendaCreatedManager.abrirAgendaDoProfissional(cliente);

            log.info("Finalizado o servico [deseableCliente] -- Cliente Desabilitado: {}", cliente);
            return ClienteMapper.INSTANCE.convertEntityToClienteDeseableResponse(cliente);
        } catch (Exception e) {
            throw new ClienteException("Error deseabling cliente: " + e.getMessage());
        }
    }

    public ClienteActivateResponse activateCliente(String cpf) {
        log.info("Inicializado o servico [activateCliente] -- Cliente: {}", cpf);
        try {
            var cliente = clienteRepository.findById(cpf)
                    .orElseThrow(() -> {
                        return new ClienteException("Cliente not found with CPF: " + cpf);
                    });

            if (cliente.isActive()) {
                throw new ClienteException("Cliente is already active");
            }
            cliente.setActive(true);
            clienteRepository.save(cliente);

            log.info("Finalizado o servico [activateCliente] -- Cliente Ativado: {}", cliente);
            return ClienteMapper.INSTANCE.convertEntityToClienteActivateResponse(cliente);
        } catch (Exception e) {
            log.error("Erro ao ativar cliente - CPF: {}", cpf, e);
            throw new ClienteException("Error activating cliente: " + e.getMessage());
        }
    }
}