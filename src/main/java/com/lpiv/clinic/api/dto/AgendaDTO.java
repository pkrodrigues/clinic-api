package com.lpiv.clinic.api.dto;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import com.lpiv.clinic.model.entity.Agenda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDTO {
    private Long idAgenda;
    private LocalDate data;
    private LocalDate horario;
    private String Observacao;
    private Long idPaciente;
    private Long idMedico;

    public static AgendaDTO create(Agenda agenda) {
        ModelMapper modelMapper = new ModelMapper();
        AgendaDTO dto = modelMapper.map(agenda, AgendaDTO.class);
        dto.getIdPaciente();
        dto.getIdMedico();
        return dto;
    }
}
