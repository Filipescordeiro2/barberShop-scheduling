package com.barberShop.scheduling.validation.autenticador;

import com.barberShop.scheduling.dto.request.login.LoginRequest;
import com.barberShop.scheduling.dto.response.barbearia.BarbeariaResponse;
import com.barberShop.scheduling.dto.response.cliente.ClienteResponse;
import com.barberShop.scheduling.dto.response.profissional.ProfissionalReponse;
import com.barberShop.scheduling.exception.BarbeariaException;
import com.barberShop.scheduling.exception.ClienteException;
import com.barberShop.scheduling.exception.ProfissionalException;
import com.barberShop.scheduling.mapper.barbearia.BarbeariaMapper;
import com.barberShop.scheduling.mapper.cliente.ClienteMapper;
import com.barberShop.scheduling.mapper.profissional.ProfissionalMapper;
import com.barberShop.scheduling.repository.barbearia.BarbeariaRepository;
import com.barberShop.scheduling.repository.cliente.ClienteRepository;
import com.barberShop.scheduling.repository.profissional.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PasswordAuthenticatorImp implements Authenticator {

    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final BarbeariaRepository barbeariaRepository;

    @Override
    public ClienteResponse authenticateCliente(LoginRequest request) {
        log.info("Inicializado o metodo de [authenticateCliente]");
        var cliente = clienteRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())
                .orElseThrow(() -> new ClienteException("Invalid login or password"));

        if (!cliente.isActive()) {
            log.error("Cliente não está ativo - Login: {}", request.getLogin());
            throw new ClienteException("Cliente is not active");
        }

        log.info("Finalizado o metodo de [authenticateCliente]");
        return ClienteMapper.INSTANCE.convertEntityToDto(cliente);
    }

    @Override
    public ProfissionalReponse authenticateProfissional(LoginRequest request) {
        log.info("Inicializado o metodo de [authenticateProfissional]");
        var profissional = profissionalRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())
                .orElseThrow(() -> new ProfissionalException("Invalid login or password"));

        if (!profissional.isActive()) {
            log.error("Profissional não está ativo - Login: {}", request.getLogin());
            throw new ProfissionalException("Profissional is not active");
        }

        log.info("Finalizado o metodo de [authenticateProfissional]");
        return ProfissionalMapper.INSTANCE.convertEntityToDto(profissional);
    }

    @Override
    public BarbeariaResponse authenticateBarbearia(LoginRequest request) {
        log.info("Inicializado o metodo de [authenticateBarbearia]");
        var response = barbeariaRepository.findByLoginAndPassword(request.getLogin(), request.getPassword())
                .map(BarbeariaMapper.INSTANCE::convertEntityToDto)
                .orElseThrow(() -> new BarbeariaException("Invalid login or password"));

        log.info("Finalizado o metodo de [authenticateBarbearia] ");
        return response;
    }
}