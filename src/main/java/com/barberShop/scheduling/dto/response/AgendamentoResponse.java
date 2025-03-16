package com.barberShop.scheduling.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AgendamentoResponse(String mesage,
                                  LocalDateTime createdAt) {
}
