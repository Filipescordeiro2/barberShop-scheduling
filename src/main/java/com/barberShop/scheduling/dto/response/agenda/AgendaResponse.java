package com.barberShop.scheduling.dto.response.agenda;

import com.barberShop.scheduling.enums.JornadaEnum;
import com.barberShop.scheduling.enums.StatusAgenda;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public record AgendaResponse(String message,
                             LocalTime time,
                             LocalDate date,
                             JornadaEnum jornada,
                             LocalDateTime created_at,
                             LocalDateTime updated_at,
                             StatusAgenda statusAgenda) {
}
