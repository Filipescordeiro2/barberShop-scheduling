package com.barberShop.scheduling.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    private String cpf;

    private String name;
    private String surname;
    private String nameComplete;
    private String phone;
    private String email;
    private String login;
    private String password;
    private LocalDate dateOfBirth;
    private Integer age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;

    @PrePersist
    public void prePersist() {
        this.active = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.nameComplete = this.name + " " + this.surname;
        this.age = LocalDate.now().getYear() - this.dateOfBirth.getYear();
        this.login = this.email;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}