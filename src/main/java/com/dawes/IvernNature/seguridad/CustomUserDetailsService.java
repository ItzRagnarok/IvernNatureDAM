package com.dawes.IvernNature.seguridad;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dawes.IvernNature.modelo.UsuarioVO;
import com.dawes.IvernNature.repositorio.UsuarioPersistance;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioPersistance usuarioPersistance;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Intento de login para usuario: " + username);
        UsuarioVO usuario = usuarioPersistance.findByNombreUsuario(username);
        if (usuario == null) {
            System.out.println("Usuario NO encontrado: " + username);
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        System.out.println("Usuario encontrado: " + usuario.getNombreUsuario());
        System.out.println("Contraseña en DB: " + usuario.getPassword());
        
        if (usuario.getRol() == null) {
        	System.out.println("ERROR: El usuario no tiene rol asignado.");
        } else {
        	System.out.println("Rol del usuario: " + usuario.getRol().getNombre());
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRol().getNombre());
        
        return new User(
                usuario.getNombreUsuario(),
                usuario.getPassword(),
                Collections.singletonList(authority)
        );
    }
}
