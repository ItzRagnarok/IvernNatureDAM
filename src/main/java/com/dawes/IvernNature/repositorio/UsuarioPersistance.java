package com.dawes.IvernNature.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dawes.IvernNature.modelo.UsuarioVO;

public interface UsuarioPersistance extends JpaRepository<UsuarioVO, Integer> {
    UsuarioVO findByNombreUsuario(String nombreUsuario);

}
