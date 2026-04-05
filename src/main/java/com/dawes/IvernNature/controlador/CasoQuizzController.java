package com.dawes.IvernNature.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dawes.IvernNature.modelo.CasoQuizzVO;
import com.dawes.IvernNature.modelo.QuizzVO;
import com.dawes.IvernNature.servicio.interfaces.CasoQuizzService;
import com.dawes.IvernNature.servicio.interfaces.QuizzService;

@Controller
@RequestMapping("/cursos/quizz")
public class CasoQuizzController {

	@Autowired
	private CasoQuizzService casoQuizzService;

	@Autowired
	private QuizzService quizzService;

	// Mostrar la vista para gestionar/añadir casos a un quizz específico
	@GetMapping("/{idQuizz}/casos")
	public String gestionarCasos(@PathVariable("idQuizz") int idQuizz, Model model) {
		QuizzVO quizz = quizzService.findById(idQuizz).orElse(null);

		model.addAttribute("quizz", quizz);
		model.addAttribute("casoQuizz", new CasoQuizzVO()); // Objeto vacío para el formulario

		return "gestionar_casos"; // Necesitaremos crear esta vista HTML
	}

	// Guardar un nuevo caso
	@PostMapping("/{idQuizz}/casos/guardar")
	public String guardarCasoQuizz(@PathVariable("idQuizz") int idQuizz, @ModelAttribute CasoQuizzVO casoQuizz,
			@RequestParam("archivoImagen") MultipartFile imagen, RedirectAttributes redirect) {

		try {
			if (!imagen.isEmpty()) {
				String nombreImagen = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
				Path directorioImagenes = Paths.get("uploads/quizz");
				if (!Files.exists(directorioImagenes)) {
					Files.createDirectories(directorioImagenes);
				}
				Path rutaCompleta = directorioImagenes.resolve(nombreImagen);
				Files.write(rutaCompleta, imagen.getBytes());
				casoQuizz.setImg(nombreImagen);
			}

			// Buscamos el Quizz y se lo asignamos al caso
			QuizzVO quizz = quizzService.findById(idQuizz).orElse(null);
			casoQuizz.setQuizz(quizz);

			casoQuizzService.guardar(casoQuizz);
			redirect.addFlashAttribute("mensaje", "¡Pregunta guardada con éxito!");

		} catch (IOException e) {
			e.printStackTrace();
			redirect.addFlashAttribute("error", "Error al guardar la imagen");
		}

		return "redirect:/cursos/quizz/" + idQuizz + "/casos";
	}

	// Jugar al minijuego
	@GetMapping("/{idQuizz}/jugar")
	public String jugarMinijuego(@PathVariable("idQuizz") int idQuizz, Model model) {
		// Le pasamos al HTML el ID del Quizz, no del curso
		model.addAttribute("idQuizzActual", idQuizz);
		return "minijuego_plagas";
	}

	// Eliminar caso
	@PostMapping("/{idQuizz}/casos/{idCaso}/eliminar") 
	public String eliminarCaso(@PathVariable("idQuizz") int idQuizz, @PathVariable("idCaso") int idCaso,
			RedirectAttributes redirect) {
		try {
			// Buscamos el caso en la base de datos
			CasoQuizzVO caso = casoQuizzService.buscarPorId(idCaso).orElse(null);

			if (caso != null) {
				// Si tiene una imagen asociada, la borramos del disco duro
				if (caso.getImg() != null && !caso.getImg().isEmpty()) {
					Path imagePath = Paths.get("uploads", "quizz", caso.getImg());
					Files.deleteIfExists(imagePath);
				}

				// Borramos el caso de la base de datos
				casoQuizzService.eliminar(idCaso);

				redirect.addFlashAttribute("mensaje", "Plaga e imagen eliminadas correctamente.");
			} else {
				redirect.addFlashAttribute("error", "No se encontró el caso a eliminar.");
			}

		} catch (Exception e) {
			System.out.println("Error al borrar la plaga: " + e.getMessage());
			redirect.addFlashAttribute("error", "Hubo un problema al intentar borrar el caso.");
		}

		// Recargamos la misma página
		return "redirect:/cursos/quizz/" + idQuizz + "/casos";
	}
}