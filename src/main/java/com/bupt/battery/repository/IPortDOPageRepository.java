package com.bupt.battery.repository;

import com.bupt.battery.entity.PortDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPortDOPageRepository extends PagingAndSortingRepository<PortDO,Integer> {
    Page<PortDO> findAll(Pageable pageable);
}
