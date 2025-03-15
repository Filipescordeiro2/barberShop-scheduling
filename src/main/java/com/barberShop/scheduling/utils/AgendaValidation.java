package com.barberShop.scheduling.utils;

import com.barberShop.scheduling.domain.*;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AgendaValidation {

    private final ProfissionalRepository profissionalRepository;
    private final AgendaRepository agendaRepository;
    private final BarbeariaRepository barbeariaRepository;
    private final ServicosBarbeariaRepository servicosBarbeariaRepository;
    private final ProfissionalBarbeariaRepository profissionalBarbeariaRepository;

    public ServicosBarbearia validarServico(UUID servicosBarbeariaId) {
        return servicosBarbeariaRepository.findById(servicosBarbeariaId)
                .orElseThrow(() -> new ServicosBarbeariaException("Serviço de barbearia não encontrado"));
    }

    public void verificarVinculoProfissionalBarbearia(String cpfProfissional, String cnpjBarbearia) {
        boolean vinculoExiste = profissionalBarbeariaRepository.existsByProfissionalCpfAndBarbeariaCnpj(cpfProfissional, cnpjBarbearia);
        if (!vinculoExiste) {
            throw new ServicosBarbeariaException("O profissional não está vinculado à barbearia especificada.");
        }
    }

    public void verificarStatusAtivo(Profissional profissional, Barbearia barbearia) {
        if (!profissional.isActive()) {
            throw new ServicosBarbeariaException("O profissional não está ativo.");
        }
        if (!barbearia.isActive()) {
            throw new ServicosBarbeariaException("A barbearia não está ativa.");
        }
    }

    public void verificarConflitoDeAgenda(String cpfProfissional, LocalDate data, LocalTime hora) {
        boolean existeConflito = agendaRepository.existsByProfissionalCpfAndDateAndTime(cpfProfissional, data, hora);
        if (existeConflito) {
            throw new ServicosBarbeariaException("Conflito de agenda: o profissional já possui um compromisso nesse horário.");
        }
    }

    public Agenda validarAgenda(UUID agendaId) {
        return agendaRepository.findById(agendaId)
                .orElseThrow(() -> new ServicosBarbeariaException("Agenda não encontrada"));
    }

    public Profissional validarProfissional(String cpfProfissional) {
        return profissionalRepository.findById(cpfProfissional)
                .orElseThrow(() -> new ServicosBarbeariaException("Profissional não encontrado"));
    }

    public Barbearia validarBarbearia(String cnpjBarbearia) {
        return barbeariaRepository.findById(cnpjBarbearia)
                .orElseThrow(() -> new ServicosBarbeariaException("Barbearia não encontrada"));
    }

    public ServicosBarbearia validarServicoBarbearia(UUID servicosBarbeariaId) {
        return servicosBarbeariaRepository.findById(servicosBarbeariaId)
                .orElseThrow(() -> new ServicosBarbeariaException("Serviço de barbearia não encontrado"));
    }

    public ValidacaoResultado validarProfissionalEBarbearia(String cpfProfissional, String cnpjBarbearia, UUID servicosBarbeariaId) {
        verificarVinculoProfissionalBarbearia(cpfProfissional, cnpjBarbearia);
        Profissional profissional = validarProfissional(cpfProfissional);
        Barbearia barbearia = validarBarbearia(cnpjBarbearia);
        ServicosBarbearia servico = validarServicoBarbearia(servicosBarbeariaId);
        verificarStatusAtivo(profissional, barbearia);
        return new ValidacaoResultado(profissional, barbearia, servico);
    }
}