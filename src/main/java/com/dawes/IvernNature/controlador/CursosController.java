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

import com.dawes.IvernNature.modelo.ContenidoEducativoVO;
import com.dawes.IvernNature.modelo.CursoVO;
import com.dawes.IvernNature.servicio.implementacion.FileTypeUtils;
import com.dawes.IvernNature.servicio.interfaces.ContenidoEducativoService;
import com.dawes.IvernNature.servicio.interfaces.CursoService;
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
        String fileName = file.getOriginalFilename();
        curso.setImagenUrl(fileName);
        cursoService.save(curso);
        String uploadDir = "curso-fotos/" + curso.getIdcurso();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        redirectAttributes.addFlashAttribute("message", "Curso guardado con éxito!");
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

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = Paths.get("src", "main", "resources", "static", "contenido-educativo", String.valueOf(idCurso)).toString();
        Path uploadPath = Paths.get(System.getProperty("user.dir"), uploadDir);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Error al crear el directorio de carga");
                return "redirect:/cursos/curso/" + idCurso;
            }
        }

        Path filePath = uploadPath.resolve(fileName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al cargar el archivo");
            return "redirect:/cursos/curso/" + idCurso;
        }

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
            Optional<ContenidoEducativoVO> contenidoOptional = contenidoEducativoService.findById(idContenido);
            if (contenidoOptional.isEmpty()) {
                throw new RuntimeException("Contenido no encontrado");
            }

            ContenidoEducativoVO contenido = contenidoOptional.get();
            String fileName = contenido.getUrl();
            Path filePath = Paths.get("C:\\Users\\Propietario\\Documents\\IvernNature\\IvernNature.zip_expanded\\IvernNature\\src\\main\\resources\\static\\contenido-educativo", fileName);
            System.out.println("Ruta del archivo: " + filePath.toString());
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("Archivo no encontrado o no es legible: " + filePath.toString());
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException ex) {
            throw new RuntimeException("Error al leer el archivo", ex);
        }
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
    public String actualizarCurso(@PathVariable int id,
                                  @RequestParam("nombreCurso") String nombre,
                                  @RequestParam("file") MultipartFile file,
                                  RedirectAttributes redirectAttributes) {
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
}
