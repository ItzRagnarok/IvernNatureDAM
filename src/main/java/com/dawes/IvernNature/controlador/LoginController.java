package com.dawes.IvernNature.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dawes.IvernNature.modelo.RolVO;
import com.dawes.IvernNature.modelo.UsuarioVO;
import com.dawes.IvernNature.servicio.interfaces.RolService;
import com.dawes.IvernNature.servicio.interfaces.UsuarioService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RolService rolService;

	@PostMapping("/registro")
	public String registrarUsuario(@ModelAttribute UsuarioVO usuario,
			@RequestParam("file_avatar") MultipartFile avatarFile, RedirectAttributes redirectAttrs)
			throws IOException {

		// Verificar que las contraseñas coincidan
		if (!usuario.getPassword().equals(usuario.getPassword2())) {
			redirectAttrs.addFlashAttribute("mensaje", "Las contraseñas no coinciden.");
			return "redirect:/login";
		}

		// Cifrar la contraseña antes de guardarla
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		// Asignar rol dependiendo de si marcó el checkbox de profesor
		String nombreRol = usuario.isEsProfesor() ? "ROLE_TEACHER" : "ROLE_USER";

		Optional<RolVO> rolOp = rolService.findByNombre(nombreRol);
		if (rolOp.isPresent()) {
			usuario.setRol(rolOp.get());
		} else {
			// Crear rol si no existe
			RolVO nuevoRol = new RolVO();
			nuevoRol.setNombre(nombreRol);
			rolService.save(nuevoRol);
			usuario.setRol(nuevoRol);
		}

		// --- CAMBIOS DE LA IMAGEN ---
		if (!avatarFile.isEmpty()) {
			String fileName = avatarFile.getOriginalFilename();

			// NUEVA RUTA FÍSICA: Guardamos en la carpeta unificada 'uploads'
			String uploadDir = "uploads/usuario-fotos/";
			Path uploadPath = Paths.get(uploadDir);

			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			try (var inputStream = avatarFile.getInputStream()) {
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

				// NUEVA RUTA URL: Tiene que coincidir con la regla de nuestro WebConfig
				usuario.setAvatar("/usuario-fotos/" + fileName);

			} catch (IOException e) {
				e.printStackTrace();
				redirectAttrs.addFlashAttribute("mensaje", "Error al subir la imagen del avatar.");
				return "redirect:/login";
			}

		} else {
			// Si no sube nada, le dejamos la imagen por defecto.
			// Como esta imagen no cambia nunca, sí puede quedarse en la carpeta 'static'
			// del proyecto
			usuario.setAvatar("/images/default/avatar.jpg");
		}

		usuarioService.save(usuario);
		redirectAttrs.addFlashAttribute("mensaje", "Usuario registrado exitosamente!");
		return "redirect:/login";
	}

	@GetMapping(value = { "" })
	String mostrarLogin(Model model) {
		model.addAttribute("usuario", new UsuarioVO());
		return "login";
	}

}
