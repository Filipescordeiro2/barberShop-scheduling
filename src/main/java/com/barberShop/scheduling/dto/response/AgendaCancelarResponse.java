package com.barberShop.scheduling.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record AgendaCancelarResponse(String message,
                                     String cpfProfissional,
                                     UUID idAgenda) {
}
