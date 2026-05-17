package com.dawes.IvernNature.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebApplication {

    @GetMapping({"/", "/index"})
    public String home(Model model) {
        return "index";
    }
    
    @GetMapping("/acercaDe")
    public String acercaDe() {
        return "index";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "index";
    }

}
