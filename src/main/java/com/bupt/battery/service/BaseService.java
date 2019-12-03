package com.bupt.battery.service;

import com.bupt.battery.entity.baseEntity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface BaseService<M extends BaseEntity, ID extends Serializable> {
    M save(M var1);

    M saveAndFlush(M var1);

    M update(M var1);

    void delete(M var1);

    void delete(ID var1);

    M getOne(ID var1);

    boolean existsById(ID var1);

    long count();

    List<M> findAll();

    List<M> findAll(Sort var1);

    Page<M> findAll(Pageable var1);
}
