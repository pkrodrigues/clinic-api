package com.lpiv.clinic.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpiv.clinic.model.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
