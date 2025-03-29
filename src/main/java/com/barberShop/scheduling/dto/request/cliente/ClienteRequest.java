package com.barberShop.scheduling.dto.request.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    @CPF(message = "Invalid CPF")
    @NotNull(message = "CPF is required")
    private String cpf;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Surname is required")
    private String surname;

    @NotNull(message = "Phone is required")
    private String phone;

    @Email(message = "Invalid email")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotNull(message = "Password is required")
    private String password;

}
