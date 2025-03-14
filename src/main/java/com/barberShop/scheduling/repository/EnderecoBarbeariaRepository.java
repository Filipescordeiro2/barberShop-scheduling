package com.barberShop.scheduling.repository;

import com.barberShop.scheduling.domain.EnderecoBarbearia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnderecoBarbeariaRepository extends JpaRepository<EnderecoBarbearia, UUID> {
}
