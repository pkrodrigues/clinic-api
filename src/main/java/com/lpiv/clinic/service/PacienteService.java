package com.lpiv.clinic.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lpiv.clinic.exception.RegraNegocioException;
import com.lpiv.clinic.model.entity.Paciente;
import com.lpiv.clinic.model.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class PacienteService {

    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<Paciente> getPacientes() {
        return repository.findAll();
    }

    public Optional<Paciente> getPacienteById(Long idPaciente) {
        return repository.findById(idPaciente);
    }

    // public List<Paciente> getPacientesByReceitaMedica(Optional<ReceitaMedica> receitaMedica) {
    //     return repository.findByReceitaMedica(receitaMedica);
    // }


    public void validar(Paciente paciente) {
        if (paciente.getNome() == null || paciente.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido"+paciente.getNome()+" "+paciente.getTelefone());
        }
    }

    @Transactional
    public Paciente salvar(Paciente paciente) {
        validar(paciente);
        return repository.save(paciente);
    }
    @Transactional
    public void excluir(Paciente paciente) {
        Objects.requireNonNull(paciente.getIdPaciente());
        repository.delete(paciente);
    }


}
