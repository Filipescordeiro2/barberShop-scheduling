package com.barberShop.scheduling.dto.response.profissional;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProfissionalRegisterResponse(String message,
                                           LocalDateTime createdAt) {
}
