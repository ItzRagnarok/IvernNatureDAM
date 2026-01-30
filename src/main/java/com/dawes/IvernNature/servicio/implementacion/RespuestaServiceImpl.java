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

import com.dawes.IvernNature.modelo.RespuestaVO;
import com.dawes.IvernNature.repositorio.RespuestaPersistance;
import com.dawes.IvernNature.servicio.interfaces.RespuestaService;

@Service
public class RespuestaServiceImpl implements RespuestaService  {
	@Autowired
	private RespuestaPersistance respuestaPersistance;
	
	 @Override
	public List<RespuestaVO> cargarComentarios() {
	        return respuestaPersistance.findAll();
	    }

	    @Override
		public void guardarComentario(String texto) {
	    	RespuestaVO nuevoComentario = new RespuestaVO(texto);
	    	respuestaPersistance.save(nuevoComentario);
	    }

	@Override
	public <S extends RespuestaVO> S save(S entity) {
		return respuestaPersistance.save(entity);
	}

	@Override
	public <S extends RespuestaVO> List<S> saveAll(Iterable<S> entities) {
		return respuestaPersistance.saveAll(entities);
	}

	@Override
	public <S extends RespuestaVO> Optional<S> findOne(Example<S> example) {
		return respuestaPersistance.findOne(example);
	}

	@Override
	public List<RespuestaVO> findAll(Sort sort) {
		return respuestaPersistance.findAll(sort);
	}

	@Override
	public void flush() {
		respuestaPersistance.flush();
	}

	@Override
	public Page<RespuestaVO> findAll(Pageable pageable) {
		return respuestaPersistance.findAll(pageable);
	}

	@Override
	public <S extends RespuestaVO> S saveAndFlush(S entity) {
		return respuestaPersistance.saveAndFlush(entity);
	}

	@Override
	public <S extends RespuestaVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return respuestaPersistance.saveAllAndFlush(entities);
	}

	@Override
	public List<RespuestaVO> findAll() {
		return respuestaPersistance.findAll();
	}

	@Override
	public List<RespuestaVO> findAllById(Iterable<Integer> ids) {
		return respuestaPersistance.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<RespuestaVO> entities) {
		respuestaPersistance.deleteInBatch(entities);
	}

	@Override
	public <S extends RespuestaVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return respuestaPersistance.findAll(example, pageable);
	}

	@Override
	public Optional<RespuestaVO> findById(Integer id) {
		return respuestaPersistance.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<RespuestaVO> entities) {
		respuestaPersistance.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return respuestaPersistance.existsById(id);
	}

	@Override
	public <S extends RespuestaVO> long count(Example<S> example) {
		return respuestaPersistance.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		respuestaPersistance.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends RespuestaVO> boolean exists(Example<S> example) {
		return respuestaPersistance.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		respuestaPersistance.deleteAllInBatch();
	}

	@Override
	public RespuestaVO getOne(Integer id) {
		return respuestaPersistance.getOne(id);
	}

	@Override
	public <S extends RespuestaVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return respuestaPersistance.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return respuestaPersistance.count();
	}

	@Override
	public void deleteById(Integer id) {
		respuestaPersistance.deleteById(id);
	}

	@Override
	public RespuestaVO getById(Integer id) {
		return respuestaPersistance.getById(id);
	}

	@Override
	public void delete(RespuestaVO entity) {
		respuestaPersistance.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		respuestaPersistance.deleteAllById(ids);
	}

	@Override
	public RespuestaVO getReferenceById(Integer id) {
		return respuestaPersistance.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends RespuestaVO> entities) {
		respuestaPersistance.deleteAll(entities);
	}

	@Override
	public <S extends RespuestaVO> List<S> findAll(Example<S> example) {
		return respuestaPersistance.findAll(example);
	}

	@Override
	public <S extends RespuestaVO> List<S> findAll(Example<S> example, Sort sort) {
		return respuestaPersistance.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		respuestaPersistance.deleteAll();
	}
	
	

}
