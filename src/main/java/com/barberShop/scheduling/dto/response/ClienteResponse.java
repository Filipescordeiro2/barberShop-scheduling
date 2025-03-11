package com.barberShop.scheduling.dto.response;


import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record ClienteResponse(
        String cpf,
        String name,
        String surname,
        String nameComplete,
        String phone,
        String email,
        Integer age,
        LocalDate dateOfBirth,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean active
) {}
