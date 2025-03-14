package com.barberShop.scheduling.dto.response;

import lombok.Builder;

@Builder
public record EnderecoBarbeariaResponse(
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String numero,
        String complemento
) {
}
