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

import com.dawes.IvernNature.modelo.TemaVO;
import com.dawes.IvernNature.repositorio.TemaPersistance;
import com.dawes.IvernNature.servicio.interfaces.TemaService;

@Service
public class TemaServiceImpl implements TemaService {
	@Autowired
	private TemaPersistance temaPersiatance;

	@Override
	public <S extends TemaVO> S save(S entity) {
		return temaPersiatance.save(entity);
	}

	@Override
	public <S extends TemaVO> List<S> saveAll(Iterable<S> entities) {
		return temaPersiatance.saveAll(entities);
	}

	@Override
	public <S extends TemaVO> Optional<S> findOne(Example<S> example) {
		return temaPersiatance.findOne(example);
	}

	@Override
	public List<TemaVO> findAll(Sort sort) {
		return temaPersiatance.findAll(sort);
	}

	@Override
	public void flush() {
		temaPersiatance.flush();
	}

	@Override
	public Page<TemaVO> findAll(Pageable pageable) {
		return temaPersiatance.findAll(pageable);
	}

	@Override
	public <S extends TemaVO> S saveAndFlush(S entity) {
		return temaPersiatance.saveAndFlush(entity);
	}

	@Override
	public <S extends TemaVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return temaPersiatance.saveAllAndFlush(entities);
	}

	@Override
	public List<TemaVO> findAll() {
		return temaPersiatance.findAll();
	}

	@Override
	public List<TemaVO> findAllById(Iterable<Integer> ids) {
		return temaPersiatance.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<TemaVO> entities) {
		temaPersiatance.deleteInBatch(entities);
	}

	@Override
	public <S extends TemaVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return temaPersiatance.findAll(example, pageable);
	}

	@Override
	public Optional<TemaVO> findById(Integer id) {
		return temaPersiatance.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<TemaVO> entities) {
		temaPersiatance.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return temaPersiatance.existsById(id);
	}

	@Override
	public <S extends TemaVO> long count(Example<S> example) {
		return temaPersiatance.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		temaPersiatance.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends TemaVO> boolean exists(Example<S> example) {
		return temaPersiatance.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		temaPersiatance.deleteAllInBatch();
	}

	@Override
	public TemaVO getOne(Integer id) {
		return temaPersiatance.getOne(id);
	}

	@Override
	public <S extends TemaVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return temaPersiatance.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return temaPersiatance.count();
	}

	@Override
	public void deleteById(Integer id) {
		temaPersiatance.deleteById(id);
	}

	@Override
	public TemaVO getById(Integer id) {
		return temaPersiatance.getById(id);
	}

	@Override
	public void delete(TemaVO entity) {
		temaPersiatance.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		temaPersiatance.deleteAllById(ids);
	}

	@Override
	public TemaVO getReferenceById(Integer id) {
		return temaPersiatance.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends TemaVO> entities) {
		temaPersiatance.deleteAll(entities);
	}

	@Override
	public <S extends TemaVO> List<S> findAll(Example<S> example) {
		return temaPersiatance.findAll(example);
	}

	@Override
	public <S extends TemaVO> List<S> findAll(Example<S> example, Sort sort) {
		return temaPersiatance.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		temaPersiatance.deleteAll();
	}
	
	

}
