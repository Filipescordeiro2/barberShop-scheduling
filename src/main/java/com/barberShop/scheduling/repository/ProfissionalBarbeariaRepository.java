package com.barberShop.scheduling.repository;

import com.barberShop.scheduling.domain.ProfissionalBarbearia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfissionalBarbeariaRepository extends JpaRepository<ProfissionalBarbearia, String> {
}
