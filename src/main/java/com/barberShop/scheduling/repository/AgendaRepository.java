package com.barberShop.scheduling.repository;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.enums.StatusAgenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface AgendaRepository extends JpaRepository<Agenda, UUID> {
    boolean existsByProfissionalCpfAndDateAndTime(String cpf, LocalDate date, LocalTime time);
    List<Agenda> findByStatusAgenda(StatusAgenda status);
    List<Agenda> findByProfissionalCpf(String cpfProfissional);

}
