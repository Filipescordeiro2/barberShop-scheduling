package com.barberShop.scheduling.repository;

import com.barberShop.scheduling.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,String> {
    boolean existsByEmail(String email);
    Optional<Cliente> findByLoginAndPassword(String login, String password);
}
