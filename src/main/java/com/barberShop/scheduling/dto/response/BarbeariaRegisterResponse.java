package com.barberShop.scheduling.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BarbeariaRegisterResponse(String message, String cnpj, LocalDateTime createdAt) {
}