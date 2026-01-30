package com.dawes.IvernNature.servicio.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.dawes.IvernNature.modelo.RespuestaVO;

public interface RespuestaService {

	List<RespuestaVO> cargarComentarios();

	void guardarComentario(String texto);

	<S extends RespuestaVO> S save(S entity);

	<S extends RespuestaVO> List<S> saveAll(Iterable<S> entities);

	<S extends RespuestaVO> Optional<S> findOne(Example<S> example);

	List<RespuestaVO> findAll(Sort sort);

	void flush();

	Page<RespuestaVO> findAll(Pageable pageable);

	<S extends RespuestaVO> S saveAndFlush(S entity);

	<S extends RespuestaVO> List<S> saveAllAndFlush(Iterable<S> entities);

	List<RespuestaVO> findAll();

	List<RespuestaVO> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<RespuestaVO> entities);

	<S extends RespuestaVO> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<RespuestaVO> findById(Integer id);

	void deleteAllInBatch(Iterable<RespuestaVO> entities);

	boolean existsById(Integer id);

	<S extends RespuestaVO> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends RespuestaVO> boolean exists(Example<S> example);

	void deleteAllInBatch();

	RespuestaVO getOne(Integer id);

	<S extends RespuestaVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	RespuestaVO getById(Integer id);

	void delete(RespuestaVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	RespuestaVO getReferenceById(Integer id);

	void deleteAll(Iterable<? extends RespuestaVO> entities);

	<S extends RespuestaVO> List<S> findAll(Example<S> example);

	<S extends RespuestaVO> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}