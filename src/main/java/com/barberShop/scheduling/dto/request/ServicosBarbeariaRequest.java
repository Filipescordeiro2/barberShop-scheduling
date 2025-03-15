package com.barberShop.scheduling.dto.request;

import com.barberShop.scheduling.enums.ServicosEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicosBarbeariaRequest {

    @NotNull(message = "O nome do serviço é obrigatório")
    private ServicosEnum servico;

    @NotNull(message = "O cnpj da barbearia é obrigatório")
    private String cnpjBarbearia;

    @NotNull(message = "O preco do servico é obrigatório")
    private Double price;

    @NotNull(message = "O tempo do servico é obrigatório")
    private Integer timeOfJob;
}
