package com.barberShop.scheduling.dto.response.agendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AgendamentoDetalhadaResponse(UUID idAgenda,
                                           LocalDate data,
                                           LocalTime hora,
                                           String periodoAgenda,
                                           String statusAgenda,
                                           String namebarbearia,
                                           String emailBarbearia,
                                           String celularBarbearia,
                                           String nomeProfissional,
                                           String nomeServico,
                                           String descricaoServico,
                                           String cpfCliente,
                                           String nomeCliente,
                                           String emailCliente,
                                           String celularCliente,
                                           LocalDate dataDeNascimentoCliente,
                                           Integer idade) {
}
