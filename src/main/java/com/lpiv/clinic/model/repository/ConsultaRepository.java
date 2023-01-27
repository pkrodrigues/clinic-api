package com.lpiv.clinic.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lpiv.clinic.model.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}

