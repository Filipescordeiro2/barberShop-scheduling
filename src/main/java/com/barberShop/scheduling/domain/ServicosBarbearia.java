package com.barberShop.scheduling.domain;

import com.barberShop.scheduling.enums.ServicosEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicosBarbearia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name_service")
    @Enumerated(EnumType.STRING)
    private ServicosEnum services;

    @ManyToOne
    @JoinColumn(name = "id_barbearia")
    private Barbearia barbearia;

    private Double price;
    private Integer timeOfJob;
    private String descricaoEspecialidade;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean active;

    @PrePersist
    private void prePersist() {
        this.descricaoEspecialidade = this.services.getDescricaoDetalhada();
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.active = true;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

}
