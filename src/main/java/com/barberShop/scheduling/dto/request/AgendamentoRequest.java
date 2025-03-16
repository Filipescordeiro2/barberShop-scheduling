package com.barberShop.scheduling.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoRequest {

    private UUID agendaId;
    private String clienteId;
}
