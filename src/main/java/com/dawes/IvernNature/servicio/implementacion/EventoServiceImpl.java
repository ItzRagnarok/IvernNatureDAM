package com.dawes.IvernNature.servicio.implementacion;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.dawes.IvernNature.modelo.EventoVO;
import com.dawes.IvernNature.repositorio.EventoPersistance;
import com.dawes.IvernNature.servicio.interfaces.EventoService;

@Service
public class EventoServiceImpl implements EventoService {
	@Autowired
	private EventoPersistance eventoPersiatence;

	@Override
	public <S extends EventoVO> S save(S entity) {
		return eventoPersiatence.save(entity);
	}

	@Override
	public <S extends EventoVO> List<S> saveAll(Iterable<S> entities) {
		return eventoPersiatence.saveAll(entities);
	}

	@Override
	public <S extends EventoVO> Optional<S> findOne(Example<S> example) {
		return eventoPersiatence.findOne(example);
	}

	@Override
	public List<EventoVO> findAll(Sort sort) {
		return eventoPersiatence.findAll(sort);
	}

	@Override
	public void flush() {
		eventoPersiatence.flush();
	}

	@Override
	public Page<EventoVO> findAll(Pageable pageable) {
		return eventoPersiatence.findAll(pageable);
	}

	@Override
	public <S extends EventoVO> S saveAndFlush(S entity) {
		return eventoPersiatence.saveAndFlush(entity);
	}

	@Override
	public <S extends EventoVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return eventoPersiatence.saveAllAndFlush(entities);
	}

	@Override
	public List<EventoVO> findAll() {
		return eventoPersiatence.findAll();
	}

	@Override
	public List<EventoVO> findAllById(Iterable<Integer> ids) {
		return eventoPersiatence.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<EventoVO> entities) {
		eventoPersiatence.deleteInBatch(entities);
	}

	@Override
	public <S extends EventoVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return eventoPersiatence.findAll(example, pageable);
	}

	@Override
	public Optional<EventoVO> findById(Integer id) {
		return eventoPersiatence.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<EventoVO> entities) {
		eventoPersiatence.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return eventoPersiatence.existsById(id);
	}

	@Override
	public <S extends EventoVO> long count(Example<S> example) {
		return eventoPersiatence.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		eventoPersiatence.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends EventoVO> boolean exists(Example<S> example) {
		return eventoPersiatence.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		eventoPersiatence.deleteAllInBatch();
	}

	@Override
	public EventoVO getOne(Integer id) {
		return eventoPersiatence.getOne(id);
	}

	@Override
	public <S extends EventoVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return eventoPersiatence.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return eventoPersiatence.count();
	}

	@Override
	public void deleteById(Integer id) {
		eventoPersiatence.deleteById(id);
	}

	@Override
	public EventoVO getById(Integer id) {
		return eventoPersiatence.getById(id);
	}

	@Override
	public void delete(EventoVO entity) {
		eventoPersiatence.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		eventoPersiatence.deleteAllById(ids);
	}

	@Override
	public EventoVO getReferenceById(Integer id) {
		return eventoPersiatence.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends EventoVO> entities) {
		eventoPersiatence.deleteAll(entities);
	}

	@Override
	public <S extends EventoVO> List<S> findAll(Example<S> example) {
		return eventoPersiatence.findAll(example);
	}

	@Override
	public <S extends EventoVO> List<S> findAll(Example<S> example, Sort sort) {
		return eventoPersiatence.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		eventoPersiatence.deleteAll();
	}
	
	

}
