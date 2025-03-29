package com.barberShop.scheduling.enums;

import java.time.LocalTime;

public enum JornadaPeriod {
    MANHA(LocalTime.of(9, 0), LocalTime.of(12, 0), JornadaEnum.MANHA),
    TARDE(LocalTime.of(13, 0), LocalTime.of(19, 0), JornadaEnum.TARDE),
    NOITE(LocalTime.of(19, 0), LocalTime.of(22, 0), JornadaEnum.NOITE);

    private final LocalTime startTime;
    private final LocalTime endTime;
    private final JornadaEnum jornadaEnum;

    JornadaPeriod(LocalTime startTime, LocalTime endTime, JornadaEnum jornadaEnum) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.jornadaEnum = jornadaEnum;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public JornadaEnum getJornadaEnum() {
        return jornadaEnum;
    }


    public static JornadaPeriod fromJornada(JornadaEnum jornada) {
        for (JornadaPeriod period : values()) {
            if (period.getJornadaEnum() == jornada) {
                return period;
            }
        }
        throw new IllegalArgumentException("Jornada inválida: " + jornada);
    }
}