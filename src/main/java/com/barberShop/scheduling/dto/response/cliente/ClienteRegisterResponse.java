package com.barberShop.scheduling.dto.response.cliente;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ClienteRegisterResponse(String message,LocalDateTime createdAt) {
}