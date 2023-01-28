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

import com.lpiv.clinic.api.dto.AgendaDTO;
import com.lpiv.clinic.exception.RegraNegocioException;
import com.lpiv.clinic.model.entity.Agenda;
import com.lpiv.clinic.service.AgendaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/agendas")

public class AgendaController {
    private final AgendaService service;

    private Agenda converter(AgendaDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Agenda agenda = modelMapper.map(dto, Agenda.class);
        return agenda;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Agenda> agendas =service.getAgenda();
        return ResponseEntity.ok(agendas.stream().map(AgendaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um agenda")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Agendamento ok"),
            @ApiResponse(code = 404, message = "Agendamento não encontrado")
    })
    public ResponseEntity get(@PathVariable("id") @ApiParam("ID do agenda") Long id) {
        Optional<Agenda> agenda = service.getAgendaById(id);
        if (!agenda.isPresent()) {
            return new ResponseEntity("Agenda não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(agenda.map(AgendaDTO::create));
    }


    @PostMapping()
    @ApiOperation("Salva um novo agenda")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agendamento realizado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao realizar o agendamento")
    })
    public ResponseEntity post(AgendaDTO dto) {
        try {
            Agenda agenda = converter(dto);
            agenda = service.salvar(agenda);
            return new ResponseEntity(agenda, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar detalhes do agenda")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Alterações salvas com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao alterar o agendamento")
    })
    public ResponseEntity atualizar(@PathVariable("id") Long id, AgendaDTO dto) {
        if (!service.getAgendaById(id).isPresent()) {
            return new ResponseEntity("Agenda não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Agenda agenda = converter(dto);
            agenda.setIdAgenda(id);
            service.salvar(agenda);
            return ResponseEntity.ok(agenda);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("Cancelar agendamento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agendamento deletado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao deletar o agenda")
    })
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Agenda> agenda = service.getAgendaById(id);
        if (!agenda.isPresent()) {
            return new ResponseEntity("Agenda não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(agenda.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}