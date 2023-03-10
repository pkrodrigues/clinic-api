package com.lpiv.clinic.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lpiv.clinic.exception.RegraNegocioException;
import com.lpiv.clinic.model.entity.Consulta;
import com.lpiv.clinic.model.repository.ConsultaRepository;

import jakarta.transaction.Transactional;

@Service
public class ConsultaService {

    private ConsultaRepository repository;

    public ConsultaService (ConsultaRepository repository){
        this.repository = repository;
    }

    public List<Consulta> getConsulta() {
        return repository.findAll();
    }

    public Optional<Consulta> getConsultaById(Long idConsulta) {
        return repository.findById(idConsulta);
    }

    public void validar(Consulta consulta) {
    //   if (consulta.getPaciente().getNome() == null || consulta.getPaciente().getNome().trim().equals("")){
    //       throw new RegraNegocioException("Nome inválido");
    //   }
    //   if (consulta.getMedico().getNome() == null || consulta.getMedico().getNome().trim().equals("")){
    //         throw new RegraNegocioException("Nome inválido");
    //   }
      if (consulta.getDataConsulta() == null){
            throw new RegraNegocioException("Data inválida");
      }
      if (consulta.getHorario() == null){
            throw new RegraNegocioException("Hora inválida");
      }
    }

    @Transactional
    public Consulta salvar(Consulta consulta){
        validar(consulta);
        return repository.save(consulta);
    }
    @Transactional
    public void excluir(Consulta consulta) {
        Objects.requireNonNull(consulta.getIdConsulta());
        repository.delete(consulta);
    }
}
