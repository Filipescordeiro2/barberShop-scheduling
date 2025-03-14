package com.barberShop.scheduling.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoBarbeariaRequest {

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;
    private String complemento;
}
