package com.barberShop.scheduling.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProfissionalRegisterResponse(String message,
                                           String cpf,
                                           LocalDateTime createdAt) {
}
