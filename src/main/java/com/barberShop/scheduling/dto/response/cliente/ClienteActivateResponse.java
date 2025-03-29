package com.barberShop.scheduling.dto.response.cliente;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ClienteActivateResponse(String message,
                                      LocalDateTime createdAt) {
}
