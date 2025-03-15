package com.barberShop.scheduling.enums;

import com.barberShop.scheduling.exception.InvalidServicoException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@JsonDeserialize(using = ServicosEnum.ServicosEnumDeserializer.class)
@Getter
@RequiredArgsConstructor
public enum ServicosEnum {
    CORTE_SIMPLES("Um corte básico e rápido, ideal para manutenção do visual."),
    CORTE_NAVALHADO("Corte de cabelo com acabamento à navalha, proporcionando um visual mais definido."),
    CORTE_SOCIAL("Corte clássico e elegante, perfeito para ambientes formais."),
    CORTE_INFANTIL("Corte de cabelo para crianças, com atenção especial ao conforto e segurança."),
    BARBA("Aparar e modelar a barba, com técnicas de barbearia tradicionais."),
    SOBANCELHA("Design de sobrancelhas para um visual harmonioso e bem definido."),
    PENTEADO("Penteados para ocasiões especiais, com estilo e sofisticação."),
    LUZES("Técnica de coloração para iluminar e dar destaque aos cabelos.");

    private final String descricaoDetalhada;

    public static class ServicosEnumDeserializer extends JsonDeserializer<ServicosEnum> {
        @Override
        public ServicosEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String value = p.getText().toUpperCase();
            try {
                return ServicosEnum.valueOf(value);
            } catch (IllegalArgumentException e) {
                throw new InvalidServicoException("servico invalido");
            }
        }
    }
}