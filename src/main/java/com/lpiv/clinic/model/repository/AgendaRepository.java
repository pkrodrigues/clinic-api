package com.lpiv.clinic.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpiv.clinic.model.entity.Agenda;

public interface AgendaRepository extends JpaRepository <Agenda, Long> {

}
