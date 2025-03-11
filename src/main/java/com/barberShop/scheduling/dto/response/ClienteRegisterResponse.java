package com.barberShop.scheduling.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ClienteRegisterResponse(String message,String cpf,LocalDateTime createdAt) {
}
