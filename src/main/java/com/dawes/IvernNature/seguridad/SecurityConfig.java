package com.dawes.IvernNature.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dawes.IvernNature.servicio.interfaces.UsuarioService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
    private UsuarioService usuarioService;	
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/css/**", "/js/**", "/images/**", "/parciales/**", "/contenido-educativo/**").permitAll() // Permitir acceso a recursos estáticos
                    .requestMatchers("/", "/index", "/cursos/**", "/login/**", "/login/**", "/usuarios/**", "/editar/**").permitAll() // Permitir acceso a estas rutas sin autenticación
                    .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login") // Página de inicio de sesión personalizada
                    .permitAll()
            )
            .logout(logout ->
                logout
                    .permitAll()
            );
        return http.build();
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
