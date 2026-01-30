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

import com.dawes.IvernNature.modelo.ContenidoEducativoVO;
import com.dawes.IvernNature.repositorio.ContenidoEducativoPersistance;
import com.dawes.IvernNature.servicio.interfaces.ContenidoEducativoService;

@Service
public class ContenidoEducativoServiceImpl implements ContenidoEducativoService  {
	@Autowired
	private ContenidoEducativoPersistance contenidoEducativoPersistance;
	
	@Override
	public List<ContenidoEducativoVO> cargarContenidosEducativos() {
        return contenidoEducativoPersistance.findAll();
    }
	
	@Override
    public List<ContenidoEducativoVO> cargarContenidosEducativosPorCurso(int idCurso) {
        return contenidoEducativoPersistance.findByCursoId(idCurso);
    }

	@Override
	public <S extends ContenidoEducativoVO> S save(S entity) {
		return contenidoEducativoPersistance.save(entity);
	}

	@Override
	public <S extends ContenidoEducativoVO> List<S> saveAll(Iterable<S> entities) {
		return contenidoEducativoPersistance.saveAll(entities);
	}

	@Override
	public <S extends ContenidoEducativoVO> Optional<S> findOne(Example<S> example) {
		return contenidoEducativoPersistance.findOne(example);
	}

	@Override
	public List<ContenidoEducativoVO> findAll(Sort sort) {
		return contenidoEducativoPersistance.findAll(sort);
	}

	@Override
	public void flush() {
		contenidoEducativoPersistance.flush();
	}

	@Override
	public Page<ContenidoEducativoVO> findAll(Pageable pageable) {
		return contenidoEducativoPersistance.findAll(pageable);
	}

	@Override
	public <S extends ContenidoEducativoVO> S saveAndFlush(S entity) {
		return contenidoEducativoPersistance.saveAndFlush(entity);
	}

	@Override
	public <S extends ContenidoEducativoVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return contenidoEducativoPersistance.saveAllAndFlush(entities);
	}

	@Override
	public List<ContenidoEducativoVO> findAll() {
		return contenidoEducativoPersistance.findAll();
	}

	@Override
	public List<ContenidoEducativoVO> findAllById(Iterable<Integer> ids) {
		return contenidoEducativoPersistance.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<ContenidoEducativoVO> entities) {
		contenidoEducativoPersistance.deleteInBatch(entities);
	}

	@Override
	public <S extends ContenidoEducativoVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return contenidoEducativoPersistance.findAll(example, pageable);
	}

	@Override
	public Optional<ContenidoEducativoVO> findById(Integer id) {
		return contenidoEducativoPersistance.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<ContenidoEducativoVO> entities) {
		contenidoEducativoPersistance.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return contenidoEducativoPersistance.existsById(id);
	}

	@Override
	public <S extends ContenidoEducativoVO> long count(Example<S> example) {
		return contenidoEducativoPersistance.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		contenidoEducativoPersistance.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends ContenidoEducativoVO> boolean exists(Example<S> example) {
		return contenidoEducativoPersistance.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		contenidoEducativoPersistance.deleteAllInBatch();
	}

	@Override
	public ContenidoEducativoVO getOne(Integer id) {
		return contenidoEducativoPersistance.getOne(id);
	}

	@Override
	public <S extends ContenidoEducativoVO, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return contenidoEducativoPersistance.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return contenidoEducativoPersistance.count();
	}

	@Override
	public void deleteById(Integer id) {
		contenidoEducativoPersistance.deleteById(id);
	}

	@Override
	public ContenidoEducativoVO getById(Integer id) {
		return contenidoEducativoPersistance.getById(id);
	}

	@Override
	public void delete(ContenidoEducativoVO entity) {
		contenidoEducativoPersistance.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		contenidoEducativoPersistance.deleteAllById(ids);
	}

	@Override
	public ContenidoEducativoVO getReferenceById(Integer id) {
		return contenidoEducativoPersistance.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends ContenidoEducativoVO> entities) {
		contenidoEducativoPersistance.deleteAll(entities);
	}

	@Override
	public <S extends ContenidoEducativoVO> List<S> findAll(Example<S> example) {
		return contenidoEducativoPersistance.findAll(example);
	}

	@Override
	public <S extends ContenidoEducativoVO> List<S> findAll(Example<S> example, Sort sort) {
		return contenidoEducativoPersistance.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		contenidoEducativoPersistance.deleteAll();
	}
	
	

}
