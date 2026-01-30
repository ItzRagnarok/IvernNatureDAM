package com.dawes.IvernNature.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawes.IvernNature.modelo.CursoVO;

public interface CursoPersitance extends JpaRepository<CursoVO, Integer> {

}
