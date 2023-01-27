package com.lpiv.clinic.model.entity;

import java.util.Date;

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
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;

    private String procedimento;
    private Date dataConsulta;
    private Date horario;
    private String observacao;
    private String convenio;
    private double valor;
    private double valorTotal;
}