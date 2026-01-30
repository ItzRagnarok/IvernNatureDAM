package com.dawes.IvernNature.servicio.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.dawes.IvernNature.modelo.ContenidoEducativoVO;

public interface ContenidoEducativoService {

	List<ContenidoEducativoVO> cargarContenidosEducativos();
	
	List<ContenidoEducativoVO> cargarContenidosEducativosPorCurso(int idCurso);

	<S extends ContenidoEducativoVO> S save(S entity);

	<S extends ContenidoEducativoVO> List<S> saveAll(Iterable<S> entities);

	<S extends ContenidoEducativoVO> Optional<S> findOne(Example<S> example);

	List<ContenidoEducativoVO> findAll(Sort sort);

	void flush();

	Page<ContenidoEducativoVO> findAll(Pageable pageable);

	<S extends ContenidoEducativoVO> S saveAndFlush(S entity);

	<S extends ContenidoEducativoVO> List<S> saveAllAndFlush(Iterable<S> entities);

	List<ContenidoEducativoVO> findAll();

	List<ContenidoEducativoVO> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<ContenidoEducativoVO> entities);

	<S extends ContenidoEducativoVO> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<ContenidoEducativoVO> findById(Integer id);

	void deleteAllInBatch(Iterable<ContenidoEducativoVO> entities);

	boolean existsById(Integer id);

	<S extends ContenidoEducativoVO> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends ContenidoEducativoVO> boolean exists(Example<S> example);

	void deleteAllInBatch();

	ContenidoEducativoVO getOne(Integer id);

	<S extends ContenidoEducativoVO, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	ContenidoEducativoVO getById(Integer id);

	void delete(ContenidoEducativoVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	ContenidoEducativoVO getReferenceById(Integer id);

	void deleteAll(Iterable<? extends ContenidoEducativoVO> entities);

	<S extends ContenidoEducativoVO> List<S> findAll(Example<S> example);

	<S extends ContenidoEducativoVO> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}