package com.dawes.IvernNature.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dawes.IvernNature.modelo.CasoQuizzVO;
import com.dawes.IvernNature.modelo.QuizzVO;

@Repository
public interface CasoQuizzRepository extends JpaRepository<CasoQuizzVO, Integer> {
    List<CasoQuizzVO> findByQuizz(QuizzVO quizz);
}