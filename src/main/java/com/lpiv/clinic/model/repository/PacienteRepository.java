package com.lpiv.clinic.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpiv.clinic.model.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
 
}
