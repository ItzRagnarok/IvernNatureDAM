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

import com.dawes.IvernNature.modelo.CursoVO;
import com.dawes.IvernNature.modelo.UsuarioVO;
import com.dawes.IvernNature.repositorio.CursoPersitance;
import com.dawes.IvernNature.servicio.interfaces.CursoService;

@Service
public class CursoServiceImpl implements CursoService {
	@Autowired
	private CursoPersitance cursopersistance;
	
	

	@Override
	public <S extends CursoVO> S save(S entity) {
		return cursopersistance.save(entity);
	}

	@Override
	public <S extends CursoVO> List<S> saveAll(Iterable<S> entities) {
		return cursopersistance.saveAll(entities);
	}

	@Override
	public <S extends CursoVO> Optional<S> findOne(Example<S> example) {
		return cursopersistance.findOne(example);
	}

	@Override
	public List<CursoVO> findAll(Sort sort) {
		return cursopersistance.findAll(sort);
	}

	@Override
	public void flush() {
		cursopersistance.flush();
	}

	@Override
	public Page<CursoVO> findAll(Pageable pageable) {
		return cursopersistance.findAll(pageable);
	}

	@Override
	public <S extends CursoVO> S saveAndFlush(S entity) {
		return cursopersistance.saveAndFlush(entity);
	}

	@Override
	public <S extends CursoVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cursopersistance.saveAllAndFlush(entities);
	}

	@Override
	public List<CursoVO> findAll() {
		return cursopersistance.findAll();
	}

	@Override
	public List<CursoVO> findAllById(Iterable<Integer> ids) {
		return cursopersistance.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<CursoVO> entities) {
		cursopersistance.deleteInBatch(entities);
	}

	@Override
	public <S extends CursoVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cursopersistance.findAll(example, pageable);
	}

	@Override
	public Optional<CursoVO> findById(Integer id) {
		return cursopersistance.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<CursoVO> entities) {
		cursopersistance.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return cursopersistance.existsById(id);
	}

	@Override
	public <S extends CursoVO> long count(Example<S> example) {
		return cursopersistance.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		cursopersistance.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends CursoVO> boolean exists(Example<S> example) {
		return cursopersistance.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		cursopersistance.deleteAllInBatch();
	}

	@Override
	public CursoVO getOne(Integer id) {
		return cursopersistance.getOne(id);
	}

	@Override
	public <S extends CursoVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return cursopersistance.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return cursopersistance.count();
	}

	@Override
	public void deleteById(Integer id) {
		cursopersistance.deleteById(id);
	}

	@Override
	public CursoVO getById(Integer id) {
		return cursopersistance.getById(id);
	}

	@Override
	public void delete(CursoVO entity) {
		cursopersistance.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		cursopersistance.deleteAllById(ids);
	}

	@Override
	public CursoVO getReferenceById(Integer id) {
		return cursopersistance.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends CursoVO> entities) {
		cursopersistance.deleteAll(entities);
	}

	@Override
	public <S extends CursoVO> List<S> findAll(Example<S> example) {
		return cursopersistance.findAll(example);
	}

	@Override
	public <S extends CursoVO> List<S> findAll(Example<S> example, Sort sort) {
		return cursopersistance.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		cursopersistance.deleteAll();
	}
	
	

}
