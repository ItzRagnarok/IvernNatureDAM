package com.dawes.IvernNature.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawes.IvernNature.modelo.RespuestaVO;

public interface RespuestaPersistance extends JpaRepository<RespuestaVO, Integer> {

}
