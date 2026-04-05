package com.dawes.IvernNature.servicio.interfaces;

import java.util.List;
import java.util.Optional;
import com.dawes.IvernNature.modelo.CasoQuizzVO;
import com.dawes.IvernNature.modelo.QuizzVO;

public interface CasoQuizzService {
	public CasoQuizzVO guardar(CasoQuizzVO caso);

	public void eliminar(int id);

	public List<CasoQuizzVO> listarPorQuizz(QuizzVO quizz);

	public Optional<CasoQuizzVO> buscarPorId(int id);
}