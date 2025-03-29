package com.barberShop.scheduling.dto.response.barbearia;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BarbeariaRegisterResponse(String message, LocalDateTime createdAt) {
}