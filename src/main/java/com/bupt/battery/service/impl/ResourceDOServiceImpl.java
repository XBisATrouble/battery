package com.bupt.battery.service.impl;

import com.bupt.battery.entity.ChargeDischargeStatisticsDO;
import com.bupt.battery.entity.ResourceDO;
import com.bupt.battery.form.ResourceForm;
import com.bupt.battery.repository.IResourceDORepository;
import com.bupt.battery.service.IChargeDischargeStatisticsDOService;
import com.bupt.battery.service.IResourceDOService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ResourceDOServiceImpl extends BaseServiceImpl<ResourceDO,Long> implements IResourceDOService {
    @Autowired
    private IResourceDORepository resourceDORepository;
    @Override
    public List<ResourceDO> findResoureDOByType(String type) {
        return resourceDORepository.findResourceDOSByType(type);
    }

    @Override
    public ResourceDO findResoureDOByName(String name) {
        return resourceDORepository.findResourceDOByName(name);
    }

    @Override
    public Page<ResourceDO> findResourceListByPage(ResourceForm form) {
        Integer pageNum=form.getPageNum()!=null?form.getPageNum():0;
        Integer pageSize=form.getPageSize()!=null?form.getPageSize():10;
        Pageable pageable= PageRequest.of(pageNum,pageSize,new Sort(Direction.DESC,"id"));

        Specification<ResourceDO> specification=new Specification<ResourceDO>() {
            @Override
            public Predicate toPredicate(Root<ResourceDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList=new ArrayList<>();
                if(StringUtils.isNotBlank(form.getName()))
                {
                    predicateList.add(criteriaBuilder.like(root.get("name"),form.getName()+"%"));
                }
                if(StringUtils.isNotBlank(form.getType()))
                {
                    predicateList.add(criteriaBuilder.equal(root.get("type"),form.getType()));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return resourceDORepository.findAll(specification,pageable);

    }
}
