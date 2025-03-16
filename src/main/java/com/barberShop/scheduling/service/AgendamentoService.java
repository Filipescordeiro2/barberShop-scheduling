package com.barberShop.scheduling.service;

import com.barberShop.scheduling.domain.*;
import com.barberShop.scheduling.dto.request.AgendamentoRequest;
import com.barberShop.scheduling.dto.response.AgendamentoCancelarResponse;
import com.barberShop.scheduling.dto.response.AgendamentoDetalhadaResponse;
import com.barberShop.scheduling.dto.response.AgendamentoResponse;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.mapper.AgendamentoMapper;
import com.barberShop.scheduling.repository.AgendaRepository;
import com.barberShop.scheduling.repository.AgendamentoRepository;
import com.barberShop.scheduling.utils.AgendamentoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendaRepository agendaRepository;
    private final AgendamentoValidation agendamentoValidation;
    private final AgendamentoMapper agendamentoMapper = AgendamentoMapper.INSTANCE;

    @Transactional
    public AgendamentoResponse criarAgendamento(AgendamentoRequest request) {
        var agenda = agendamentoValidation.validateAgenda(request.getAgendaId());
        var cliente = agendamentoValidation.validateCliente(request.getClienteId());

        agendamentoValidation.validateAgendaStatus(agenda);
        agendamentoValidation.validateProfissionalStatus(agenda.getProfissional());
        agendamentoValidation.validateBarbeariaStatus(agenda.getBarbearia());

        var agendamento = new Agendamento();
        agendamento.setAgenda(agenda);
        agendamento.setCliente(cliente);
        agendamentoRepository.save(agendamento);

        agenda.setStatusAgenda(StatusAgenda.AGENDADO);
        agendaRepository.save(agenda);

        return agendamentoMapper.convertEntityToResponse(agendamento);
    }

    @Transactional
    public List<AgendamentoDetalhadaResponse> getAgendamentoDetalhado(UUID agendaId) {
        var agenda = agendamentoValidation.validateAgenda(agendaId);
        var agendamentos = agendamentoRepository.findByAgenda(agenda);

        return agendamentos.stream()
                .map(agendamentoMapper::convertEntityToDetalhadaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<AgendamentoDetalhadaResponse> getAgendamentoDetalhadoPorCpf(String cpf) {
        var cliente = agendamentoValidation.validateClienteCPF(cpf);
        var agendamentos = agendamentoRepository.findByCliente(cliente);

        return agendamentos.stream()
                .map(agendamentoMapper::convertEntityToDetalhadaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public AgendamentoCancelarResponse cancelarAgendamento(UUID agendamentoId) {
        var agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new ServicosBarbeariaException("Agendamento not found"));
        if (agendamento.getStatus() == StatusAgenda.CANCELADO) {
            throw new ServicosBarbeariaException("Agendamento já está cancelada");
        }
        agendaRepository.findById(agendamento.getAgenda().getId()).stream().forEach(agenda -> {
            agenda.setStatusAgenda(StatusAgenda.ABERTO);
            agendaRepository.save(agenda);
        });
        agendamento.setStatus(StatusAgenda.CANCELADO);
        agendamentoRepository.save(agendamento);

        return AgendamentoMapper.INSTANCE.convertEntityToCancelarResponse(agendamento);
    }
}