package com.dawes.IvernNature.servicio.implementacion;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dawes.IvernNature.modelo.QuizzVO;
import com.dawes.IvernNature.repositorio.QuizzRepository;
import com.dawes.IvernNature.servicio.interfaces.QuizzService;

@Service
public class QuizzServiceImpl implements QuizzService {

    @Autowired
    private QuizzRepository repo;

    @Override
    public QuizzVO save(QuizzVO quizz) {
        return repo.save(quizz);
    }

    @Override
    public Optional<QuizzVO> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}