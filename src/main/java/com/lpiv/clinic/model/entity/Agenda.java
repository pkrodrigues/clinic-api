package com.lpiv.clinic.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idAgenda;
    private LocalDate data;
    private LocalDate horario;
    private String Observacao;

    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Medico medico;

}