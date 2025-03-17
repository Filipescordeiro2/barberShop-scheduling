package com.barberShop.scheduling.repository;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.enums.StatusAgenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface AgendaRepository extends JpaRepository<Agenda, UUID>{
    boolean existsByProfissionalCpfAndDateAndTime(String cpf, LocalDate date, LocalTime time);

    List<Agenda> findByStatusAgenda(StatusAgenda status);

    List<Agenda> findByProfissionalCpf(String cpfProfissional);

    List<Agenda> findByBarbeariaCnpj(String cnpjBarbearia);

    List<Agenda> findByProfissionalCpfAndStatusAgendaAndDateBetween(String cpfProfissional, StatusAgenda status,
                                                                    LocalDate startDate, LocalDate endDate);

    List<Agenda> findByStatusAgendaAndDateBetween(StatusAgenda status, LocalDate startDate, LocalDate endDate);
    List<Agenda>findByProfissionalCpfAndDateBetween(String cpfProfissional, LocalDate startDate, LocalDate endDate);
    List<Agenda> findByProfissionalCpfAndStatusAgenda(String cpfProfissional, StatusAgenda status);
    List<Agenda> findByProfissionalCpfAndStatusAgendaAndDateBefore(String cpfProfissional, StatusAgenda status, LocalDate endDate);
    List<Agenda> findByProfissionalCpfAndStatusAgendaAndDateAfter(String cpfProfissional, StatusAgenda status, LocalDate startDate);
    List<Agenda> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
