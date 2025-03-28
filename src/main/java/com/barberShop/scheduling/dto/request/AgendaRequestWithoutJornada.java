package com.barberShop.scheduling.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequestWithoutJornada {

    @NotNull(message = "cpf is required")
    private String cpfProfissional;

    @NotNull(message = "cnpj is required")
    private String cnpjBarbearia;

    @NotNull(message = "uuid servicosBarbearia is required")
    private UUID servicosBarbeariaId;

    private LocalDate date;
}
