package com.barberShop.scheduling.dto.request.agenda;

import com.barberShop.scheduling.enums.JornadaEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {

    @NotNull(message = "cpf is required")
    private String cpfProfissional;

    @NotNull(message = "cnpj is required")
    private String cnpjBarbearia;

    @NotNull(message = "uuid servicosBarbearia is required")
    private UUID servicosBarbeariaId;

    @Schema(example = "14:30")
    private LocalTime time;

    private LocalDate date;

    @Schema(example = "string")
    private JornadaEnum jornada;
}
