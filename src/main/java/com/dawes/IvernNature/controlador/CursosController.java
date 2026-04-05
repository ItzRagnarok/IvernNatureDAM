package com.dawes.IvernNature.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dawes.IvernNature.modelo.CasoQuizzVO;
import com.dawes.IvernNature.modelo.ContenidoEducativoVO;
import com.dawes.IvernNature.modelo.CursoVO;
import com.dawes.IvernNature.modelo.QuizzVO;
import com.dawes.IvernNature.servicio.implementacion.FileTypeUtils;
import com.dawes.IvernNature.servicio.interfaces.CasoQuizzService;
import com.dawes.IvernNature.servicio.interfaces.ContenidoEducativoService;
import com.dawes.IvernNature.servicio.interfaces.CursoService;
import com.dawes.IvernNature.servicio.interfaces.QuizzService;
import com.dawes.IvernNature.servicio.interfaces.RespuestaService;

@Controller
@RequestMapping("/cursos")
public class CursosController {

	@Autowired
	private CursoService cursoService;
	@Autowired
	private ContenidoEducativoService contenidoEducativoService;
	@Autowired
	private RespuestaService respuestaService;
	@Autowired
	private FileTypeUtils fileTypeUtils;
	@Autowired
	private QuizzService quizzService;
	@Autowired
	private CasoQuizzService casoQuizzService;

	@GetMapping(value = "")
	String cursos(Model model) {
		List<CursoVO> cursos = cursoService.findAll();
		model.addAttribute("cursos", cursos);
		return "cursos";
	}

	@GetMapping(value = "/creaCurso")
	String formCurso(Model model) {
		model.addAttribute("curso", new CursoVO());
		return "creaCurso";
	}

	@PostMapping("/creaCurso")
	public String guardarCurso(@ModelAttribute CursoVO curso, @RequestParam("file") MultipartFile file,
	        RedirectAttributes redirectAttributes) throws IOException {
	    
	    if (!file.isEmpty()) {
	        String fileName = file.getOriginalFilename();
	        curso.setImagenUrl(fileName);
	        
	        // Guardamos el curso primero para tener el ID (opcional, pero recomendable)
	        cursoService.save(curso);

	        // Guardamos dentro de la carpeta uploads que configuramos
	        // No creamos subcarpeta por ID para que el HTML la encuentre fácil
	        String uploadDir = "uploads/curso-fotos/"; 
	        Path uploadPath = Paths.get(uploadDir);

	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        // Copiamos el archivo
	        try (InputStream inputStream = file.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	        }
	        
	        redirectAttributes.addFlashAttribute("message", "Curso guardado con éxito!");
	    } else {
	        // Si no suben foto, podemos poner una por defecto o avisar
	        cursoService.save(curso);
	    }
	    
	    return "redirect:/cursos";
	}

	@GetMapping(value = "/curso/{id}")
	public String verCurso(@PathVariable("id") int idCurso, Model model) {
		CursoVO curso = cursoService.findById(idCurso).orElse(null);
		if (curso != null) {
			model.addAttribute("curso", curso);
			model.addAttribute("comentarios", respuestaService.cargarComentarios());
			model.addAttribute("archivos", contenidoEducativoService.cargarContenidosEducativosPorCurso(idCurso));
			model.addAttribute("fileTypeUtils", fileTypeUtils);
			return "curso";
		} else {
			return "404";
		}
	}

	@GetMapping("/cursos/curso/{id}/contenido")
	public String verContenido(@PathVariable("id") int idCurso, Model model) {
		CursoVO curso = cursoService.findById(idCurso).orElse(null);
		if (curso == null) {
			model.addAttribute("errorMessage", "Curso no encontrado");
			return "error";
		}
		model.addAttribute("curso", curso);
		model.addAttribute("fileTypeUtils", fileTypeUtils);
		return "curso/contenido";
	}

	@PostMapping("/curso/{id}/agregarContenido")
	public String agregarContenido(@PathVariable("id") int idCurso, 
	                               @RequestParam("file") MultipartFile file,
	                               @RequestParam("titulo") String titulo, 
	                               RedirectAttributes redirectAttributes) {
	    
	    CursoVO curso = cursoService.findById(idCurso).orElse(null);
	    if (curso == null) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Curso no encontrado");
	        return "redirect:/cursos";
	    }

	    // Limpiar el nombre del archivo
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	    // NUEVA RUTA: Apuntamos a la carpeta externa 'uploads'
	    // Esto crea una ruta tipo: uploads/contenido-educativo/5
	    Path uploadPath = Paths.get("uploads", "contenido-educativo", String.valueOf(idCurso));

	    // Crear los directorios si no existen
	    if (!Files.exists(uploadPath)) {
	        try {
	            Files.createDirectories(uploadPath);
	        } catch (IOException e) {
	            redirectAttributes.addFlashAttribute("errorMessage", "Error al crear el directorio de carga");
	            return "redirect:/cursos/curso/" + idCurso;
	        }
	    }

	    // Guardar el archivo físicamente
	    try (InputStream inputStream = file.getInputStream()) {
	        Path filePath = uploadPath.resolve(fileName);
	        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	    } catch (IOException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Error al cargar el archivo");
	        return "redirect:/cursos/curso/" + idCurso;
	    }

	    // Guardar en Base de Datos (Se queda igual, pero ahora la URL es consistente)
	    ContenidoEducativoVO contenido = new ContenidoEducativoVO(titulo, file.getContentType(), fileName);
	    contenido.getCursos().add(curso);
	    contenidoEducativoService.save(contenido);

	    curso.getContenidos().add(contenido);
	    cursoService.save(curso);

	    redirectAttributes.addFlashAttribute("message", "Contenido agregado con éxito!");
	    return "redirect:/cursos/curso/" + idCurso;
	}

	@GetMapping("/descargar/{idContenido}")
	public ResponseEntity<Resource> descargarContenido(@PathVariable("idContenido") int idContenido) {
	    try {
	        Optional<ContenidoEducativoVO> conOpt = contenidoEducativoService.findById(idContenido);
	        if (conOpt.isEmpty()) return ResponseEntity.notFound().build();

	        ContenidoEducativoVO contenido = conOpt.get();
	        String nombreFichero = contenido.getUrl(); // El nombre exacto guardado en BD

	        // Obtenemos el ID del curso (del Set de cursos)
	        int idCurso = contenido.getCursos().iterator().next().getIdcurso();

	        // CONSTRUIMOS LA RUTA: uploads/contenido-educativo/{idCurso}/{nombreFichero}
	        Path path = Paths.get("uploads", "contenido-educativo", String.valueOf(idCurso), nombreFichero);
	        
	        // --- CHIVATO PARA CONSOLA ---
	        System.out.println("DEBUG: Buscando archivo en -> " + path.toAbsolutePath());
	        
	        Resource resource = new UrlResource(path.toUri());

	        if (resource.exists() && resource.isReadable()) {
	            return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	        } else {
	            System.out.println("ERROR: El archivo físico no existe o el nombre no coincide.");
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError().build();
	    }
	}

	@PostMapping("/curso/{idCurso}/contenido/{idContenido}/eliminar")
	public String eliminarContenido(@PathVariable("idCurso") int idCurso, @PathVariable("idContenido") int idContenido, RedirectAttributes redirect) {
	    try {
	        CursoVO curso = cursoService.findById(idCurso).orElse(null);
	        ContenidoEducativoVO contenido = contenidoEducativoService.findById(idContenido).orElse(null);
	        
	        if (curso != null && contenido != null) {
	            
	            // ROMPER LA RELACIÓN (La clave para evitar el error SQL 1451)
	            curso.getContenidos().remove(contenido);
	            contenido.getCursos().remove(curso);
	            
	            // Guardamos el curso para que Hibernate borre la fila de la tabla intermedia 'curso_contenido'
	            cursoService.save(curso); 
	            
	            // Borramos el archivo físico del disco duro
	            Path filePath = Paths.get("uploads", "contenido-educativo", String.valueOf(idCurso), contenido.getUrl());
	            Files.deleteIfExists(filePath);
	            
	            // Ahora sí, borramos el contenido de la base de datos sin que MySQL se queje
	            contenidoEducativoService.deleteById(idContenido); 
	            
	            redirect.addFlashAttribute("message", "Contenido y archivo eliminados correctamente.");
	        } else {
	            redirect.addFlashAttribute("errorMessage", "No se encontró el curso o el contenido.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error al borrar: " + e.getMessage());
	        redirect.addFlashAttribute("errorMessage", "Error al eliminar el contenido.");
	    }
	    
	    return "redirect:/cursos/curso/" + idCurso;
	}

	@PostMapping("/comentar")
	public String comentar(@RequestParam String comentario, Model model) {
		respuestaService.guardarComentario(comentario);
		return "redirect:/";
	}

	@PostMapping("/eliminar/{id}")
	public String eliminarCurso(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
		cursoService.deleteById(id);
		redirectAttrs.addFlashAttribute("mensaje", "Curso eliminado correctamente.");
		return "redirect:/cursos";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioDeEdicion(@PathVariable("id") int id, Model model) {
		CursoVO curso = cursoService.findById(id).orElse(null);
		if (curso == null) {
			return "redirect:/cursos";
		}
		model.addAttribute("curso", curso);
		return "editarCurso";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizarCurso(@PathVariable int id, @RequestParam("nombreCurso") String nombre,
			@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		CursoVO curso = cursoService.findById(id).orElse(null);
		if (curso != null) {
			try {
				curso.setNombreGrupo(nombre);

				if (!file.isEmpty()) {
					String fileName = file.getOriginalFilename();
					curso.setImagenUrl(fileName);
					String uploadDir = "curso-fotos/" + curso.getIdcurso();
					Path uploadPath = Paths.get(uploadDir);
					if (!Files.exists(uploadPath)) {
						Files.createDirectories(uploadPath);
					}
					try (InputStream inputStream = file.getInputStream()) {
						Path filePath = uploadPath.resolve(fileName);
						Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
					}
				}

				cursoService.save(curso);
				redirectAttributes.addFlashAttribute("message", "Curso actualizado con éxito!");
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("errorMessage", "Error al subir el archivo");
			}
			return "redirect:/cursos/curso/" + curso.getIdcurso();
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Curso no encontrado");
			return "redirect:/cursos";
		}
	}

	// --- MÉTODOS PARA GESTIONAR QUIZZES (MINIJUEGOS) ---

	@PostMapping("/curso/{id}/crearQuizz")
	public String crearQuizz(@PathVariable("id") int idCurso, @RequestParam("titulo") String titulo,
			RedirectAttributes redirectAttributes) {
		CursoVO curso = cursoService.findById(idCurso).orElse(null);
		if (curso != null) {
			QuizzVO nuevoQuizz = new QuizzVO();
			nuevoQuizz.setTitulo(titulo);
			nuevoQuizz.setCurso(curso);
			quizzService.save(nuevoQuizz);
			redirectAttributes.addFlashAttribute("message", "¡Minijuego creado con éxito!");
		}
		return "redirect:/cursos/curso/" + idCurso;
	}

	@PostMapping("/quizz/{idQuizz}/eliminar")
	public String eliminarQuizz(@PathVariable("idQuizz") int idQuizz, RedirectAttributes redirectAttributes) {
		QuizzVO quizz = quizzService.findById(idQuizz).orElse(null);
		if (quizz != null) {
			int idCurso = quizz.getCurso().getIdcurso(); // Guardamos el ID del curso para volver a él
			quizzService.deleteById(idQuizz);
			redirectAttributes.addFlashAttribute("mensaje", "Minijuego eliminado correctamente.");
			return "redirect:/cursos/curso/" + idCurso;
		}
		return "redirect:/cursos";
	}
	
	@PostMapping("/{idQuizz}/casos/{idCaso}/eliminar")
	public String eliminarCaso(@PathVariable("idQuizz") int idQuizz, @PathVariable("idCaso") int idCaso, RedirectAttributes redirect) {
	    try {
	        // Usamos el servicio CORRECTO para buscar el caso
	        CasoQuizzVO caso = casoQuizzService.buscarPorId(idCaso).orElse(null); 
	        
	        if (caso != null && caso.getImg() != null) {
	            // Construimos la ruta exacta a la imagen
	            Path imagePath = Paths.get("uploads", "quizz", caso.getImg());
	            
	            // Borramos la imagen del disco duro
	            Files.deleteIfExists(imagePath);
	        }
	        
	        // Borramos la pregunta de la base de datos
	        casoQuizzService.eliminar(idCaso); 
	        
	        redirect.addFlashAttribute("mensaje", "Pregunta e imagen eliminadas correctamente.");
	        
	    } catch (Exception e) {
	        System.out.println("Error al borrar la imagen del quizz: " + e.getMessage());
	        redirect.addFlashAttribute("error", "Hubo un problema al intentar borrar la imagen física.");
	    }
	    
	    return "redirect:/cursos/quizz/" + idQuizz + "/casos";
	}
}
