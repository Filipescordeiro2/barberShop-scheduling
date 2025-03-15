package com.barberShop.scheduling.utils;


import com.barberShop.scheduling.domain.Barbearia;
import com.barberShop.scheduling.domain.Profissional;
import com.barberShop.scheduling.domain.ServicosBarbearia;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidacaoResultado {

    private final Profissional profissional;
    private final Barbearia barbearia;
    private final ServicosBarbearia servicosBarbearia;
}
