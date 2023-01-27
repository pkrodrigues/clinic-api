package com.lpiv.clinic.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lpiv.clinic.model.entity.Agenda;
import com.lpiv.clinic.model.repository.AgendaRepository;

import jakarta.transaction.Transactional;

@Service
public class AgendaService {
    private AgendaRepository repository;

    public AgendaService(AgendaRepository repository) {
        this.repository = repository;
    }

    public List<Agenda> getAgenda() {
        return repository.findAll();
    }

    public Optional<Agenda> getAgendaById(Long idAgenda) {
        return repository.findById(idAgenda);
    }

    @Transactional
    public Agenda salvar(Agenda agenda){
        return repository.save(agenda);
    }

    @Transactional
    public void excluir(Agenda agenda){
        Objects.requireNonNull(agenda.getIdAgenda());
        repository.delete(agenda);
    }
}
