package com.barberShop.scheduling.domain;

import com.barberShop.scheduling.enums.JornadaEnum;
import com.barberShop.scheduling.enums.StatusAgenda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_profissional")
    private Profissional profissional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_barbearia", nullable = false)
    private Barbearia barbearia;

    @ManyToOne
    @JoinColumn(name = "id_servicoBarbearia")
    private ServicosBarbearia servicosBarbearia;

    private LocalTime time;

    private LocalDate date;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @Enumerated(EnumType.STRING)
    private JornadaEnum jornada;

    @Enumerated(EnumType.STRING)
    private StatusAgenda statusAgenda;

    @PrePersist
    public void prePersist() {
        this.statusAgenda = StatusAgenda.ABERTO;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated_at = LocalDateTime.now();
    }

}
