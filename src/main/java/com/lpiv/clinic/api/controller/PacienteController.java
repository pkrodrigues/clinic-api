package com.lpiv.clinic.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpiv.clinic.api.dto.PacienteDTO;
import com.lpiv.clinic.exception.RegraNegocioException;
import com.lpiv.clinic.model.entity.Paciente;
import com.lpiv.clinic.service.PacienteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    private Paciente converter(PacienteDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Paciente paciente = modelMapper.map(dto, Paciente.class);
        return paciente;
    }


    @GetMapping()
    public ResponseEntity get() {
        List<Paciente> pacientes = service.getPacientes();
        return ResponseEntity.ok(pacientes.stream().map(PacienteDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um Paciente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Paciente ok"),
            @ApiResponse(code = 404, message = "Paciente não encontrado")
    })
    public ResponseEntity get(@PathVariable("id") @ApiParam("ID do paciente") Long id) {
        Optional<Paciente> paciente = service.getPacienteById(id);
        if (!paciente.isPresent()) {
            return new ResponseEntity("Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(paciente.map(PacienteDTO::create));
    }

    @PostMapping()
    @ApiOperation("Cadastrar um novo Paciênte")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cadastro realizado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao cadastrar o novo Paciênte")
    })
    public ResponseEntity post(PacienteDTO dto) {
        try {
            Paciente paciente = converter(dto);
            paciente = service.salvar(paciente);
            return new ResponseEntity(paciente, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar detalhes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Alterações salvas com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao realizar a alteração")
    })
    public ResponseEntity atualizar(@PathVariable("id") Long id, PacienteDTO dto) {
        if (!service.getPacienteById(id).isPresent()) {
            return new ResponseEntity("Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Paciente paciente = converter(dto);
            paciente.setIdPaciente(id);
            service.salvar(paciente);
            return ResponseEntity.ok(paciente);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("Deletar Paciente")
    @ApiResponses({
            @ApiResponse(code = 201, message = " Paciente deletado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao deletar o paciente")
    })
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Paciente> paciente = service.getPacienteById(id);
        if (!paciente.isPresent()) {
            return new ResponseEntity("Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(paciente.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}