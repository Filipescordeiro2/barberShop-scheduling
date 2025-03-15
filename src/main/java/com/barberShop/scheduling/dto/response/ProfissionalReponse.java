package com.barberShop.scheduling.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProfissionalReponse(String cpf,
                                  String nameProfissional,
                                  String email,
                                  String phone,
                                  String password,
                                  Boolean active,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt) {
}
