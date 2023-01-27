package com.lpiv.clinic.model.entity;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data

public abstract class Pessoa {
    private String nome;
    private Date dataNasc;
    private String rg;
    private String cpf;
    private String sexo;
    private String telefone;
    private String email;
    private Long cep;
    private String uf;
    private String rua;
    private Long numeroResidencia;
    private String complemento;
    private String bairro;
    private String cidade;
}
