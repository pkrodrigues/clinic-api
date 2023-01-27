package com.lpiv.clinic.api.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.lpiv.clinic.model.entity.Consulta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDTO {

    private Long idConsulta;
    private  Long idMedico;
    private Long idPaciente;

    private String procedimento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataConsulta;
    private Date horario;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String Observacao;
    private String convenio;
    private double valor;
    private double valorTotal;

    public static ConsultaDTO create(Consulta consulta){
        ModelMapper modelMapper = new ModelMapper();
        ConsultaDTO dto = modelMapper.map(consulta, ConsultaDTO.class);
        dto.getIdMedico();
        dto.getIdPaciente();
        return dto;
    }
}
