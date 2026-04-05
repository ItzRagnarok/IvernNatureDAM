package com.dawes.IvernNature.servicio.interfaces;

import java.util.Optional;
import com.dawes.IvernNature.modelo.QuizzVO;

public interface QuizzService {
    QuizzVO save(QuizzVO quizz);
    Optional<QuizzVO> findById(Integer id);
    void deleteById(Integer id);
}