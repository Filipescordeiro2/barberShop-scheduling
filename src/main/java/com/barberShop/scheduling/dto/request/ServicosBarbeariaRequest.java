package com.barberShop.scheduling.dto.request;

import com.barberShop.scheduling.enums.ServicosEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicosBarbeariaRequest {

    @NotNull(message = "Service name is required")
    private ServicosEnum servico;

    @NotNull(message = "Barber shop CNPJ is required")
    private String cnpjBarbearia;

    @NotNull(message = "Service price is required")
    private Double price;

    @NotNull(message = "Service time is required")
    private Integer timeOfJob;
}
