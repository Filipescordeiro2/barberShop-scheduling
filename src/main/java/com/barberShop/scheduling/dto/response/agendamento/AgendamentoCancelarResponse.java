package com.barberShop.scheduling.dto.response.agendamento;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AgendamentoCancelarResponse(String message,
                                          String cpfCliente,
                                          UUID idAgendamento) {
}
