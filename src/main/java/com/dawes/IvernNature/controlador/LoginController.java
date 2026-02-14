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

		// Asignar rol por defecto ROLE_USER
		Optional<RolVO> rolOp = rolService.findByNombre("ROLE_USER");
		if (rolOp.isPresent()) {
			usuario.setRol(rolOp.get());
		} else {
			// Crear rol si no existe (opcional, para robustez)
			RolVO nuevoRol = new RolVO();
			nuevoRol.setNombre("ROLE_USER");
			rolService.save(nuevoRol);
			usuario.setRol(nuevoRol);
		}

		if (!avatarFile.isEmpty()) {
			String fileName = avatarFile.getOriginalFilename();
			String uploadDir = "src/main/resources/static/images/perfil/";
			// Nota: Guardar en src/main/resources no es ideal para producción, pero
			// funciona para dev local si se refresca Eclipse/IDE.
			// Mejor usar una ruta externa o configurar addResourceHandlers.
			// Mantengo la lógica original pero ajusto la ruta si es necesario.
			// La lógica original era "/images/perfil/" asumiendo que es una ruta absoluta o
			// relativa al sistema de archivos.
			// Vamos a usar una ruta absoluta segura o relativa al proyecto para evitar
			// errores de permisos en C:/.
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (var inputStream = avatarFile.getInputStream()) {
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
				usuario.setAvatar("/images/perfil/" + fileName);
			} catch (IOException e) {
				e.printStackTrace();
				redirectAttrs.addFlashAttribute("mensaje", "Error al subir la imagen del avatar.");
				return "redirect:/login";
			}

		} else {
			usuario.setAvatar("/images/default/avatar.jpg"); // Ruta a la imagen predeterminada
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
