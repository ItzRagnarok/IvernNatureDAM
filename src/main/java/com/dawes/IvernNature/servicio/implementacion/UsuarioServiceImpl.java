package com.dawes.IvernNature.servicio.implementacion;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dawes.IvernNature.dto.RegistroUsuarioDTO;
import com.dawes.IvernNature.modelo.UsuarioVO;
import com.dawes.IvernNature.repositorio.UsuarioPersistance;
import com.dawes.IvernNature.servicio.interfaces.UsuarioService;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UsuarioServiceImpl implements UsuarioService  {
	@Autowired
	private UsuarioPersistance usuarioPersitance;
	
	
	@Override
	public <S extends UsuarioVO> S save(S entity) {
		return usuarioPersitance.save(entity);
	}
	
	@Override
    public UsuarioVO findByNombreUsuario(String nombreUsuario) {
        return usuarioPersitance.findByNombreUsuario(nombreUsuario);
    }
	
	

	@Override
	public <S extends UsuarioVO> List<S> saveAll(Iterable<S> entities) {
		return usuarioPersitance.saveAll(entities);
	}

	@Override
	public <S extends UsuarioVO> Optional<S> findOne(Example<S> example) {
		return usuarioPersitance.findOne(example);
	}

	@Override
	public List<UsuarioVO> findAll(Sort sort) {
		return usuarioPersitance.findAll(sort);
	}

	@Override
	public void flush() {
		usuarioPersitance.flush();
	}

	@Override
	public Page<UsuarioVO> findAll(Pageable pageable) {
		return usuarioPersitance.findAll(pageable);
	}

	@Override
	public <S extends UsuarioVO> S saveAndFlush(S entity) {
		return usuarioPersitance.saveAndFlush(entity);
	}

	@Override
	public <S extends UsuarioVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return usuarioPersitance.saveAllAndFlush(entities);
	}

	@Override
	public List<UsuarioVO> findAll() {
		return usuarioPersitance.findAll();
	}

	@Override
	public List<UsuarioVO> findAllById(Iterable<Integer> ids) {
		return usuarioPersitance.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<UsuarioVO> entities) {
		usuarioPersitance.deleteInBatch(entities);
	}

	@Override
	public <S extends UsuarioVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return usuarioPersitance.findAll(example, pageable);
	}

	@Override
	public Optional<UsuarioVO> findById(Integer id) {
		return usuarioPersitance.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<UsuarioVO> entities) {
		usuarioPersitance.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return usuarioPersitance.existsById(id);
	}

	@Override
	public <S extends UsuarioVO> long count(Example<S> example) {
		return usuarioPersitance.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		usuarioPersitance.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends UsuarioVO> boolean exists(Example<S> example) {
		return usuarioPersitance.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		usuarioPersitance.deleteAllInBatch();
	}

	@Override
	public UsuarioVO getOne(Integer id) {
		return usuarioPersitance.getOne(id);
	}

	@Override
	public <S extends UsuarioVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return usuarioPersitance.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return usuarioPersitance.count();
	}

	@Override
	public void deleteById(Integer id) {
		usuarioPersitance.deleteById(id);
	}

	@Override
	public UsuarioVO getById(Integer id) {
		return usuarioPersitance.getById(id);
	}

	@Override
	public void delete(UsuarioVO entity) {
		usuarioPersitance.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		usuarioPersitance.deleteAllById(ids);
	}

	@Override
	public UsuarioVO getReferenceById(Integer id) {
		return usuarioPersitance.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends UsuarioVO> entities) {
		usuarioPersitance.deleteAll(entities);
	}

	@Override
	public <S extends UsuarioVO> List<S> findAll(Example<S> example) {
		return usuarioPersitance.findAll(example);
	}

	@Override
	public <S extends UsuarioVO> List<S> findAll(Example<S> example, Sort sort) {
		return usuarioPersitance.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		usuarioPersitance.deleteAll();
	}

	

	
}
