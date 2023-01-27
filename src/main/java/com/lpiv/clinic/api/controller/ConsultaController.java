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

import com.lpiv.clinic.api.dto.ConsultaDTO;
import com.lpiv.clinic.exception.RegraNegocioException;
import com.lpiv.clinic.model.entity.Consulta;
import com.lpiv.clinic.service.ConsultaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/consultas")

public class ConsultaController {
    private final ConsultaService service;

    private Consulta converter(ConsultaDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Consulta consulta = modelMapper.map(dto, Consulta.class);
        return consulta;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Consulta> consultas =service.getConsulta();
        return ResponseEntity.ok(consultas.stream().map(ConsultaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um consulta")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Convênio ok"),
            @ApiResponse(code = 404, message = "Convênio não encontrado")
    })
    public ResponseEntity get(@PathVariable("id") @ApiParam("ID do consulta") Long id) {
        Optional<Consulta> consulta = service.getConsultaById(id);
        if (!consulta.isPresent()) {
            return new ResponseEntity("Consulta não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(consulta.map(ConsultaDTO::create));
    }


    @PostMapping()
    @ApiOperation("Salva um novo consulta")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Convênio salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar o consulta")
    })
    public ResponseEntity post(ConsultaDTO dto) {
        try {
            Consulta consulta = converter(dto);
            consulta = service.salvar(consulta);
            return new ResponseEntity(consulta, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("{id}")
    @ApiOperation("Alterar detalhes do consulta")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Alterações salvas com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao alterar o consulta")
    })
    public ResponseEntity atualizar(@PathVariable("id") Long id, ConsultaDTO dto) {
        if (!service.getConsultaById(id).isPresent()) {
            return new ResponseEntity("Consulta não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Consulta consulta = converter(dto);
            consulta.setIdConsulta(id);
            service.salvar(consulta);
            return ResponseEntity.ok(consulta);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation("Deletar o consulta")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Convênio deletado com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao deletar o consulta")
    })
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Consulta> consulta = service.getConsultaById(id);
        if (!consulta.isPresent()) {
            return new ResponseEntity("Consulta não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(consulta.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}