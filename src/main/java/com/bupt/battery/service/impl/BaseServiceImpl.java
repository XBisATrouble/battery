package com.bupt.battery.service.impl;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import com.bupt.battery.repository.BaseRepository;
import com.bupt.battery.service.BaseService;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public abstract class BaseServiceImpl<M extends BaseEntity, ID extends Serializable> implements BaseService<M, ID> {
    protected BaseRepository<M, ID> baseRepository;

    public BaseServiceImpl() {
    }

    @Autowired
    public void setBaseRepository(BaseRepository<M, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public M save(M m) {
        return this.baseRepository.save(m);
    }

    public M saveAndFlush(M m) {
        m = this.save(m);
        this.baseRepository.flush();
        return m;
    }

    public M update(M m) {
        return this.baseRepository.save(m);
    }

    public void delete(M m) {
        this.baseRepository.delete(m);
    }

    public void delete(ID ids) {
        this.baseRepository.deleteById(ids);
    }

    public M getOne(ID id) {
        Optional<M> optional = this.baseRepository.findById(id);
        return !optional.isPresent() ? null : optional.get();
    }

    public boolean existsById(ID id) {
        return this.baseRepository.existsById(id);
    }

    public long count() {
        return this.baseRepository.count();
    }

    public List<M> findAll() {
        return this.baseRepository.findAll();
    }

    public List<M> findAll(Sort sort) {
        return this.baseRepository.findAll(sort);
    }

    public Page<M> findAll(Pageable pageable) {
        return this.baseRepository.findAll(pageable);
    }
}
