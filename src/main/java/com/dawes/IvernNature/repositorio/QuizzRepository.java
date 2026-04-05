package com.dawes.IvernNature.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dawes.IvernNature.modelo.CursoVO;
import com.dawes.IvernNature.modelo.QuizzVO;

@Repository
public interface QuizzRepository extends JpaRepository<QuizzVO, Integer> {
    List<QuizzVO> findByCurso(CursoVO curso);
}