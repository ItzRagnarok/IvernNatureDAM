package com.dawes.IvernNature.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import com.dawes.IvernNature.dto.RegistroUsuarioDTO;
import com.dawes.IvernNature.modelo.UsuarioVO;
import com.dawes.IvernNature.servicio.interfaces.UsuarioService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@PostMapping("/auth")
    public String manejarLogin(@ModelAttribute UsuarioVO usuario, RedirectAttributes redirectAttrs) {
        UsuarioVO usuarioExistente = usuarioService.findByNombreUsuario(usuario.getNombreUsuario());
        if (usuarioExistente != null && passwordEncoder.matches(usuario.getPassword(), usuarioExistente.getPassword())) {
            // La contraseña coincide, proceder con la autenticación
            return "redirect:/";
        } else {
            redirectAttrs.addFlashAttribute("mensaje", "Nombre de usuario o contraseña incorrectos.");
            return "redirect:/login";
        }
    }


	@PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute UsuarioVO usuario, @RequestParam("file_avatar") MultipartFile avatarFile, RedirectAttributes redirectAttrs) throws IOException {
        // Verificar que las contraseñas coincidan
        if (!usuario.getPassword().equals(usuario.getPassword2())) {
            redirectAttrs.addFlashAttribute("mensaje", "Las contraseñas no coinciden.");
            return "redirect:/login";
        }

        // Cifrar la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        if (!avatarFile.isEmpty()) {
            String fileName = avatarFile.getOriginalFilename();
            String uploadDir = "/images/perfil/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (var inputStream = avatarFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                usuario.setAvatar(uploadDir + fileName);
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

	@GetMapping("/logout")
	public String out2() {
		return "index";
	}

}
