package com.lpiv.clinic.api.dto;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.lpiv.clinic.model.entity.Paciente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {
    private Long idPaciente;
    private String nome;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date dataNasc;
    private String rg;
    private String cpf;
    private String sexo;
    private String telefone;
    private String email;
    private String uf;
    private Long cep;
    private String rua;
    private Long numeroResidencia;
    private String complemento;
    private String bairro;
    private String cidade;
    private Long idConvenio;

    public static PacienteDTO create(Paciente paciente){
        ModelMapper modelMapper = new ModelMapper();
        PacienteDTO dto = modelMapper.map(paciente, PacienteDTO.class);
        dto.getIdConvenio();
        return dto;
    }
}
