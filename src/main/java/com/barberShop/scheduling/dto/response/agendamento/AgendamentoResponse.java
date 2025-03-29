package com.barberShop.scheduling.dto.response.agendamento;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AgendamentoResponse(String mesage,
                                  LocalDateTime createdAt) {
}
