package com.barberShop.scheduling.repository;

import com.barberShop.scheduling.domain.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional,String> {
}
