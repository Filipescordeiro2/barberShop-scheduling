package com.barberShop.scheduling.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalRequest {

    @CPF(message = "Invalid CPF")
    @NotNull(message = "CPF is required")
    private String cpf;

    @NotNull(message = "Name is required")
    private String nameProfissional;

    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Phone is required")
    private String phone;

    @NotNull(message = "Password is required")
    private String password;
}
