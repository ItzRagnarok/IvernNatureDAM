package com.dawes.IvernNature.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dawes.IvernNature.dto.RegistroUsuarioDTO;
import com.dawes.IvernNature.modelo.UsuarioVO;
import com.dawes.IvernNature.servicio.interfaces.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistroController {
	 @Autowired
	    private UsuarioService usuarioService;

//	 @GetMapping("/register")
//	 public String showRegistrationForm(Model model) {
//	     model.addAttribute("user", new UsuarioVO());  // Añadir un nuevo objeto User para recoger los datos del formulario
//	     return "register";
//	 }
//
//	    
//	    @PostMapping
//	    public String registerUserAccount(@ModelAttribute("user") @Valid RegistroUsuarioDTO userDto,
//	                                      BindingResult result,
//	                                      RedirectAttributes redirectAttributes) {
//	        if (result.hasErrors()) {
//	            // Si hay errores de validación, volver a mostrar el formulario
//	            return "register";
//	        }
//	        usuarioService.registerNewUser(userDto); // Guardar el nuevo usuario si no hay errores
//	        redirectAttributes.addFlashAttribute("success", "Registration successful");
//	        return "redirect:/login"; // Redirigir al login tras un registro exitoso
//	    }
//	    @PostMapping("/register")
//		 public String processRegistration(@ModelAttribute("user") UsuarioVO user, BindingResult result) {
//		     if (result.hasErrors()) {
//		         return "register";
//		     }
//		     // Aquí se incluiría la lógica para guardar el usuario en la base de datos
//		     usuarioService.save(user);
//		     return "redirect:/login";  // Redirigir al login después de un registro exitoso
//		 }
	
	    
	    
	


}
