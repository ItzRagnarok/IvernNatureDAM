package com.dawes.IvernNature.controlador;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

public class CustomErrorController implements ErrorController {
	
	@GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Obtener el estado del error (opcional, por si quieres hacer logs)
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            System.out.println("--> [ERROR DE SISTEMA] Código HTTP: " + statusCode);
        }

        // Rediriges directamente a tu mapeo del index
        return "redirect:/index"; 
    }

}
