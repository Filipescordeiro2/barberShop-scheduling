package com.barberShop.scheduling.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalBarbeariaRequest {


    @CPF
    @NotNull(message = "The field cpfProfissional cannot be null")
    private String cpfProfissional;

    @CNPJ
    @NotNull(message = "The field cnpjBarbearia cannot be null")
    private String cnpjBarbearia;
}
