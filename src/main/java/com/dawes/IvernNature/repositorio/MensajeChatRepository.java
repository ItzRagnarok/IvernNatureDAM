package com.dawes.IvernNature.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawes.IvernNature.modelo.CursoVO;
import com.dawes.IvernNature.modelo.MensajeChatVO;

public interface MensajeChatRepository extends JpaRepository<MensajeChatVO, Integer> {
    List<MensajeChatVO> findByCursoOrderByFechaAsc(CursoVO curso);
}