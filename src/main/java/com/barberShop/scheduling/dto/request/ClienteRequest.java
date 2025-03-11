package com.barberShop.scheduling.dto.request;

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

    @CPF
    @NotNull(message = "O campo cpf é obrigatório")
    private String cpf;

    @NotNull(message = "O campo nome é obrigatório")
    private String name;

    @NotNull(message = "O campo sobrenome é obrigatório")
    private String surname;

    @NotNull(message = "O campo telefone é obrigatório")
    private String phone;

    @Email
    @NotNull(message = "O campo email é obrigatório")
    private String email;

    @NotNull(message = "O campo data de nascimento é obrigatório")
    private LocalDate dateOfBirth;

}
