package com.barberShop.scheduling.utils;
import com.barberShop.scheduling.domain.*;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.RegraDeNegocioException;
import com.barberShop.scheduling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AgendamentoValidation {

    private final AgendaRepository agendaRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ClienteRepository clienteRepository;
    private final BarbeariaRepository barbeariaRepository;
    private final AgendamentoRepository agendamentoRepository;

    public Agenda validateAgenda(UUID agendaId) {
        if (agendaId == null) {
            throw new IllegalArgumentException("O ID da agenda não pode ser nulo");
        }
        return agendaRepository.findById(agendaId)
                .orElseThrow(() -> new RegraDeNegocioException("Agenda não encontrada"));
    }

    public Agendamento validateAgendamento(Agenda agenda) {
        return agendamentoRepository.findByAgenda(agenda)
                .orElseThrow(() -> new RegraDeNegocioException("Agendamento não encontrado para a agenda fornecida"));
    }

    public Cliente validateCliente(String clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
    }

    public Cliente validateClienteCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente não pode ser nulo ou vazio");
        }
        return clienteRepository.findById(cpf)
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
    }

    public Barbearia validateBarbearia(String barbeariaId) {
        return barbeariaRepository.findById(barbeariaId)
                .orElseThrow(() -> new RegraDeNegocioException("Barbearia não encontrada"));
    }

    public void validateAgendaStatus(Agenda agenda) {
        if (!agenda.getStatusAgenda().equals(StatusAgenda.ABERTO)) {
            throw new RegraDeNegocioException("A agenda não está aberta para agendamento");
        }
    }

    public void validateProfissionalStatus(Profissional profissional) {
        if (!profissional.isActive()) {
            throw new RegraDeNegocioException("O profissional não está ativo.");
        }
    }

    public void validateBarbeariaStatus(Barbearia barbearia) {
        if (!barbearia.isActive()) {
            throw new RegraDeNegocioException("A barbearia não está ativa.");
        }
    }

    public List<Agendamento> validateAgendamentosByCliente(Cliente cliente) {
        List<Agendamento> agendamentos = agendamentoRepository.findByCliente(cliente);
        if (agendamentos.isEmpty()) {
            throw new RegraDeNegocioException("Nenhum agendamento encontrado para o cliente fornecido");
        }
        return agendamentos;
    }
}