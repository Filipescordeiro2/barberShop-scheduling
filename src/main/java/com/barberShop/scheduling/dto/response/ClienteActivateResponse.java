package com.barberShop.scheduling.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ClienteActivateResponse(String message,
                                      LocalDateTime createdAt) {
}
