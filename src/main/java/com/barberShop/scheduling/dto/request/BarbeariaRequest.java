package com.barberShop.scheduling.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarbeariaRequest {

    @CNPJ(message = "Invalid CNPJ")
    private String cnpj;

    @NotNull(message = "Barber shop name cannot be null")
    private String nameBarber;

    private String phone;

    @Email(message = "Invalid email")
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Address cannot be null")
    private EnderecoBarbeariaRequest enderecoBarbearia;
}
