//package com.dawes.IvernNature.servicio.implementacion;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
//import org.springframework.stereotype.Service;
//
//import com.dawes.IvernNature.modelo.RolVO;
//import com.dawes.IvernNature.repositorio.RolPersistance;
//import com.dawes.IvernNature.servicio.interfaces.RolService;
//
//@Service
//public class RolServiceImpl implements RolService {
//	@Autowired
//	private RolPersistance rolPersistance;
//
//	@Override
//	public <S extends RolVO> S save(S entity) {
//		return rolPersistance.save(entity);
//	}
//
//	@Override
//	public <S extends RolVO> List<S> saveAll(Iterable<S> entities) {
//		return rolPersistance.saveAll(entities);
//	}
//
//	@Override
//	public <S extends RolVO> Optional<S> findOne(Example<S> example) {
//		return rolPersistance.findOne(example);
//	}
//
//	@Override
//	public List<RolVO> findAll(Sort sort) {
//		return rolPersistance.findAll(sort);
//	}
//
//	@Override
//	public void flush() {
//		rolPersistance.flush();
//	}
//
//	@Override
//	public Page<RolVO> findAll(Pageable pageable) {
//		return rolPersistance.findAll(pageable);
//	}
//
//	@Override
//	public <S extends RolVO> S saveAndFlush(S entity) {
//		return rolPersistance.saveAndFlush(entity);
//	}
//
//	@Override
//	public <S extends RolVO> List<S> saveAllAndFlush(Iterable<S> entities) {
//		return rolPersistance.saveAllAndFlush(entities);
//	}
//
//	@Override
//	public List<RolVO> findAll() {
//		return rolPersistance.findAll();
//	}
//
//	@Override
//	public List<RolVO> findAllById(Iterable<Integer> ids) {
//		return rolPersistance.findAllById(ids);
//	}
//
//	@Override
//	public void deleteInBatch(Iterable<RolVO> entities) {
//		rolPersistance.deleteInBatch(entities);
//	}
//
//	@Override
//	public <S extends RolVO> Page<S> findAll(Example<S> example, Pageable pageable) {
//		return rolPersistance.findAll(example, pageable);
//	}
//
//	@Override
//	public Optional<RolVO> findById(Integer id) {
//		return rolPersistance.findById(id);
//	}
//
//	@Override
//	public void deleteAllInBatch(Iterable<RolVO> entities) {
//		rolPersistance.deleteAllInBatch(entities);
//	}
//
//	@Override
//	public boolean existsById(Integer id) {
//		return rolPersistance.existsById(id);
//	}
//
//	@Override
//	public <S extends RolVO> long count(Example<S> example) {
//		return rolPersistance.count(example);
//	}
//
//	@Override
//	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
//		rolPersistance.deleteAllByIdInBatch(ids);
//	}
//
//	@Override
//	public <S extends RolVO> boolean exists(Example<S> example) {
//		return rolPersistance.exists(example);
//	}
//
//	@Override
//	public void deleteAllInBatch() {
//		rolPersistance.deleteAllInBatch();
//	}
//
//	@Override
//	public RolVO getOne(Integer id) {
//		return rolPersistance.getOne(id);
//	}
//
//	@Override
//	public <S extends RolVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
//		return rolPersistance.findBy(example, queryFunction);
//	}
//
//	@Override
//	public long count() {
//		return rolPersistance.count();
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		rolPersistance.deleteById(id);
//	}
//
//	@Override
//	public RolVO getById(Integer id) {
//		return rolPersistance.getById(id);
//	}
//
//	@Override
//	public void delete(RolVO entity) {
//		rolPersistance.delete(entity);
//	}
//
//	@Override
//	public void deleteAllById(Iterable<? extends Integer> ids) {
//		rolPersistance.deleteAllById(ids);
//	}
//
//	@Override
//	public RolVO getReferenceById(Integer id) {
//		return rolPersistance.getReferenceById(id);
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends RolVO> entities) {
//		rolPersistance.deleteAll(entities);
//	}
//
//	@Override
//	public <S extends RolVO> List<S> findAll(Example<S> example) {
//		return rolPersistance.findAll(example);
//	}
//
//	@Override
//	public <S extends RolVO> List<S> findAll(Example<S> example, Sort sort) {
//		return rolPersistance.findAll(example, sort);
//	}
//
//	@Override
//	public void deleteAll() {
//		rolPersistance.deleteAll();
//	}
//	
//	
//
//}
