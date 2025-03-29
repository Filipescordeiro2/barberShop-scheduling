package com.barberShop.scheduling.dto.response.barbearia;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BarbeariaResponse(
        String cnpj,
        String nameBarber,
        String phone,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean active,
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String numero,
        String complemento) {
}