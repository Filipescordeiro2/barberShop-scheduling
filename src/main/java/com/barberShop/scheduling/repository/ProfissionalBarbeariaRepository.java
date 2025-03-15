package com.barberShop.scheduling.repository;

import com.barberShop.scheduling.domain.ProfissionalBarbearia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalBarbeariaRepository extends JpaRepository<ProfissionalBarbearia, String> {
    boolean existsByProfissionalCpfAndBarbeariaCnpj(String cpf, String cnpj);

}
