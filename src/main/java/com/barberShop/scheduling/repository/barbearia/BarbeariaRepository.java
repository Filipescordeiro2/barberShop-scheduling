package com.barberShop.scheduling.repository.barbearia;

import com.barberShop.scheduling.domain.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarbeariaRepository extends JpaRepository<Barbearia,String> {
    boolean existsByEmail(String email);
    Optional<Barbearia> findByLoginAndPassword(String login, String password);

}
