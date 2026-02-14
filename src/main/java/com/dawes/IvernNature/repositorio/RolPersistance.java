package com.dawes.IvernNature.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dawes.IvernNature.modelo.RolVO;

public interface RolPersistance extends JpaRepository<RolVO, Integer> {
	Optional<RolVO> findByNombre(String nombre);
}
