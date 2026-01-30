package com.dawes.IvernNature.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawes.IvernNature.modelo.EventoVO;

public interface EventoPersistance extends JpaRepository<EventoVO, Integer> {

}
