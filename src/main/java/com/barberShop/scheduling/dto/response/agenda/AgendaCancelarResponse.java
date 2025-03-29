package com.barberShop.scheduling.dto.response.agenda;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AgendaCancelarResponse(String message,
                                     String cpfProfissional,
                                     UUID idAgenda) {
}
