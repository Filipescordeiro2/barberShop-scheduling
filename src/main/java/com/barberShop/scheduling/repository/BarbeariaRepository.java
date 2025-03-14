package com.barberShop.scheduling.repository;

import com.barberShop.scheduling.domain.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbeariaRepository extends JpaRepository<Barbearia,String> {
    boolean existsByEmail(String email);
}
