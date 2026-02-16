package com.dawes.IvernNature.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dawes.IvernNature.modelo.RolVO;
import com.dawes.IvernNature.modelo.UsuarioVO;
import com.dawes.IvernNature.repositorio.RolPersistance;
import com.dawes.IvernNature.repositorio.UsuarioPersistance;

@Component
public class DataInitializer implements CommandLineRunner {
	@Autowired
    private UsuarioPersistance usuarioPersistance;

    @Autowired
    private RolPersistance rolPersistance;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        // Buscamos si el rol ADMIN ya existe
        RolVO rolAdmin = rolPersistance.findByNombre("ADMIN").orElse(null);
        if (rolAdmin == null) {
            rolAdmin = new RolVO();
            rolAdmin.setNombre("ADMIN");
            rolAdmin = rolPersistance.save(rolAdmin);
            System.out.println("-> Rol ADMIN creado en la base de datos.");
        }

        // Buscamos si el rol USER ya existe
        RolVO rolUser = rolPersistance.findByNombre("ROLE_USER").orElse(null);
        if (rolUser == null) {
            rolUser = new RolVO();
            rolUser.setNombre("ROLE_USER");
            rolPersistance.save(rolUser);
            System.out.println("-> Rol ROLE_USER creado en la base de datos.");
        }

        // Buscamos si el usuario 'admin' ya existe. Si no, lo creamos.
        UsuarioVO adminUser = usuarioPersistance.findByNombreUsuario("admin");
        if (adminUser == null) {
            adminUser = new UsuarioVO();
            adminUser.setAvatar("/images/default/avatar.jpg");
            adminUser.setNombreUsuario("admin");
            adminUser.setNombreYApellidos("Administrador del Sistema");
            adminUser.setCorreoElectronico("admin@ivernnature.com");
            
            // Encriptamos la contraseña para que Spring Security la acepte
            adminUser.setPassword(passwordEncoder.encode("Admin123")); 
            
            // Le asignamos el rol que creamos
            adminUser.setRol(rolAdmin); 
            
            usuarioPersistance.save(adminUser); // Lo guardamos en Aiven
            System.out.println("-> Usuario Administrador creado con éxito (Usuario: admin / Contraseña: admin123).");
        }
    }
}
