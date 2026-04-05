package com.dawes.IvernNature.servicio.implementacion;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dawes.IvernNature.modelo.CasoQuizzVO;
import com.dawes.IvernNature.modelo.CursoVO;
import com.dawes.IvernNature.modelo.QuizzVO;
import com.dawes.IvernNature.repositorio.CasoQuizzRepository;
import com.dawes.IvernNature.servicio.interfaces.CasoQuizzService;

@Service
public class CasoQuizzServiceImpl implements CasoQuizzService {

    @Autowired
    private CasoQuizzRepository repo;

    @Override
    public CasoQuizzVO guardar(CasoQuizzVO caso) {
        return repo.save(caso);
    }

    @Override
    public void eliminar(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<CasoQuizzVO> listarPorQuizz(QuizzVO quizz) {
        return repo.findByQuizz(quizz);
    }

    @Override
    public Optional<CasoQuizzVO> buscarPorId(int id) {
        return repo.findById(id);
    }
}