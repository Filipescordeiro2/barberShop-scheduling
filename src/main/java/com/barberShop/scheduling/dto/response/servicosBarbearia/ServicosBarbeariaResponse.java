package com.barberShop.scheduling.dto.response.servicosBarbearia;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ServicosBarbeariaResponse(String cnpjBarbearia,
                                        String message,
                                        LocalDateTime createdDate) {
}
