package com.dawes.IvernNature.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dawes.IvernNature.modelo.ContenidoEducativoVO;

public interface ContenidoEducativoPersistance extends JpaRepository<ContenidoEducativoVO, Integer> {
	@Query("SELECT c FROM ContenidoEducativoVO c JOIN c.cursos curso WHERE curso.idcurso = :idCurso")
    List<ContenidoEducativoVO> findByCursoId(@Param("idCurso") int idCurso);

}
