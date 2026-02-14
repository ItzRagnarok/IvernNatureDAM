package com.dawes.IvernNature.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dawes.IvernNature.modelo.UsuarioVO;
import com.dawes.IvernNature.servicio.interfaces.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
//	Map<String, Object> response = new HashMap<String, Object>();
	
	@GetMapping("")
    public String listarUsuarios(Model model) {
        List<UsuarioVO> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; // nombre del archivo HTML Thymeleaf
    }
	
	@PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        usuarioService.deleteById(id);
        redirectAttrs.addFlashAttribute("mensaje", "Usuario eliminado correctamente.");
        return "redirect:/usuarios";
    }
	
	@GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") int id, Model model) {
        UsuarioVO usuario = usuarioService.findById(id).orElse(null);
        if (usuario == null) {
            return "redirect:/usuarios";
        }
        model.addAttribute("usuario", usuario);
        return "editarUsuario"; // nombre del archivo HTML Thymeleaf para editar
    }
	
	@PostMapping("/actualizar")
	public String actualizarUsuario(@ModelAttribute UsuarioVO usuarioFormulario, BindingResult result, RedirectAttributes redirectAttrs) {
	    if (result.hasErrors()) {
	        return "editarUsuario"; // Regresa al formulario si hay errores
	    }

	    // Busca el usuario existente
	    UsuarioVO usuarioExistente = usuarioService.findById(usuarioFormulario.getIdusuario()).orElse(null);
	    if (usuarioExistente == null) {
	        redirectAttrs.addFlashAttribute("error", "El usuario no existe.");
	        return "redirect:/usuarios";
	    }

	    // Actualiza solo los campos necesarios
	    usuarioExistente.setNombreUsuario(usuarioFormulario.getNombreUsuario());
	    usuarioExistente.setCorreoElectronico(usuarioFormulario.getCorreoElectronico());
	    // Añadir más campos si es necesario

	    // Guarda el usuario actualizado
	    usuarioService.save(usuarioExistente);
	    redirectAttrs.addFlashAttribute("mensaje", "Usuario actualizado correctamente.");
	    return "redirect:/usuarios";
	}
	//probando

	

}
