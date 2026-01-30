package com.dawes.IvernNature.servicio.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.dawes.IvernNature.modelo.EventoVO;

public interface EventoService {

	<S extends EventoVO> S save(S entity);

	<S extends EventoVO> List<S> saveAll(Iterable<S> entities);

	<S extends EventoVO> Optional<S> findOne(Example<S> example);

	List<EventoVO> findAll(Sort sort);

	void flush();

	Page<EventoVO> findAll(Pageable pageable);

	<S extends EventoVO> S saveAndFlush(S entity);

	<S extends EventoVO> List<S> saveAllAndFlush(Iterable<S> entities);

	List<EventoVO> findAll();

	List<EventoVO> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<EventoVO> entities);

	<S extends EventoVO> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<EventoVO> findById(Integer id);

	void deleteAllInBatch(Iterable<EventoVO> entities);

	boolean existsById(Integer id);

	<S extends EventoVO> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends EventoVO> boolean exists(Example<S> example);

	void deleteAllInBatch();

	EventoVO getOne(Integer id);

	<S extends EventoVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	EventoVO getById(Integer id);

	void delete(EventoVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	EventoVO getReferenceById(Integer id);

	void deleteAll(Iterable<? extends EventoVO> entities);

	<S extends EventoVO> List<S> findAll(Example<S> example);

	<S extends EventoVO> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}