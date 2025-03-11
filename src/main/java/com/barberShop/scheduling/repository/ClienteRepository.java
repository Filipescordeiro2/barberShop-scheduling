package com.barberShop.scheduling.repository;

import com.barberShop.scheduling.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,String> {
}
