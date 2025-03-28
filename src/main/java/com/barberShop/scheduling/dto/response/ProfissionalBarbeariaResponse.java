package com.barberShop.scheduling.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProfissionalBarbeariaResponse(String message,
                                            String cpfProfissional,
                                            String cnpjBarbearia,
                                            LocalDateTime createdAt) {
}
