package com.lpiv.clinic.api.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.lpiv.clinic.model.entity.Medico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {

    private Long idMedico;
    private String rg;
    private String sexo;
    private String telefone;
    @DateTimeFormat (pattern="dd/MM/yyyy")
    private  Date dataAdmissao;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private  Date dataSaida;
    private double salario;
    private int crm;
    private String cpf;
    private String especialidade;
    private String cidade;
    @DateTimeFormat (pattern="dd/MM/yyyy")
    private  Date dataNasc;
    private String email;
    private String nome;
    private String complemento;
    private String bairro;
    private Long cep;
    private Long numeroResidencia;
    private String rua;
    private String uf;
    private Long idConvenio;



    public static MedicoDTO create(Medico medico){
        ModelMapper modelMapper = new ModelMapper();
        MedicoDTO dto = modelMapper.map(medico, MedicoDTO.class);
        dto.getIdConvenio();
        return dto;
    }
}

