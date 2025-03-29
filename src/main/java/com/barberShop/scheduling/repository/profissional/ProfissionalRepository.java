package com.barberShop.scheduling.repository.profissional;

import com.barberShop.scheduling.domain.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<Profissional,String> {
    boolean existsByEmail(String email);
    Optional<Profissional> findByLoginAndPassword(String login, String password);

}
