package com.dawes.IvernNature.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.IvernNature.modelo.CasoQuizzVO;
import com.dawes.IvernNature.modelo.QuizzVO;
import com.dawes.IvernNature.servicio.interfaces.CasoQuizzService;
import com.dawes.IvernNature.servicio.interfaces.QuizzService;

@RestController
@RequestMapping("/api/quizz")
public class QuizzRestController {

    @Autowired
    private CasoQuizzService casoQuizzService;

    @Autowired
    private QuizzService quizzService;

    // AHORA BUSCAMOS POR ID DE QUIZZ
    @GetMapping("/{idquizz}/datos")
    public ResponseEntity<List<CasoQuizzVO>> obtenerCasos(@PathVariable Integer idquizz) {
        
        // Buscamos el Quizz (el contenedor)
        QuizzVO quizz = quizzService.findById(idquizz).orElse(null);
        
        if (quizz == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Traemos los casos asociados a ese Quizz específico
        List<CasoQuizzVO> casos = casoQuizzService.listarPorQuizz(quizz);
        
        return ResponseEntity.ok(casos); 
    }
}