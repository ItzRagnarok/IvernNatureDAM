package com.dawes.IvernNature.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawes.IvernNature.modelo.TemaVO;

public interface TemaPersistance extends JpaRepository<TemaVO, Integer> {

}
