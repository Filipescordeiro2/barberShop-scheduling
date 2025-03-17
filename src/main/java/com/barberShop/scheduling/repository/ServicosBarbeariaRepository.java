package com.barberShop.scheduling.repository;

import com.barberShop.scheduling.domain.ServicosBarbearia;
import com.barberShop.scheduling.enums.ServicosEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ServicosBarbeariaRepository extends JpaRepository<ServicosBarbearia, UUID> {
    Optional<ServicosBarbearia> findByServicesAndBarbeariaCnpjAndActiveTrue(ServicosEnum services, String cnpjBarbearia);
    Optional<ServicosBarbearia> findByServicesAndBarbeariaCnpj(ServicosEnum services, String cnpjBarbearia);
}
