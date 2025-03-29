package com.barberShop.scheduling.utils.agenda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduleGenerator {

    public List<LocalTime> generateTimes(LocalTime startTime, LocalTime endTime) {
        log.info("Inicializado o metodo de [generateTimes] - StartTime: {}, EndTime: {}", startTime, endTime);
        List<LocalTime> times = new ArrayList<>();
        LocalTime currentTime = startTime;

        while (currentTime.isBefore(endTime)) {
            times.add(currentTime);
            currentTime = currentTime.plusMinutes(30);
        }

        log.info("Finalizado o metodo de [generateTimes] - Times Gerados: {}", times);
        return times;
    }
}