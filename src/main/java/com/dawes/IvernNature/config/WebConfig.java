package com.dawes.IvernNature.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Minijuego (Plagas)
		// URL: /images/quizz/** -> Carpeta: uploads/quizz/
		registry.addResourceHandler("/images/quizz/**").addResourceLocations("file:uploads/quizz/");

		// Cursos
		// URL: /curso-fotos/** -> Carpeta: uploads/curso-fotos/
		registry.addResourceHandler("/images/cursos/**").addResourceLocations("file:uploads/curso-fotos/");

		// Usuarios (Avatares)
		// URL: /usuario-fotos/** -> Carpeta: uploads/usuario-fotos/
		registry.addResourceHandler("/usuario-fotos/**").addResourceLocations("file:uploads/usuario-fotos/");

		// Contenido Educativo (PDFs, Imágenes de apuntes, etc.)
		// URL: /contenido-educativo/** -> Carpeta: uploads/contenido-educativo/
		registry.addResourceHandler("/contenido-educativo/**")
				.addResourceLocations("file:uploads/contenido-educativo/");
	}
}