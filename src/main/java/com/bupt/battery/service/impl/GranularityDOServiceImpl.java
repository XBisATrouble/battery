package com.bupt.battery.service.impl;

import com.bupt.battery.entity.GranularityDO;
import com.bupt.battery.form.GranularityForm;
import com.bupt.battery.repository.IGranularityDORepository;
import com.bupt.battery.service.IGranularityDOService;
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
public class GranularityDOServiceImpl extends BaseServiceImpl<GranularityDO,Long> implements IGranularityDOService {
    @Autowired
    private IGranularityDORepository granularityDORepository;
    public List<GranularityDO> findGranularityList(GranularityForm form) {
        Specification<GranularityDO> specification = getSpe(form);
        return granularityDORepository.findAll(specification);
    }


    public Page<GranularityDO> findGranularityPage(GranularityForm form) {
        Integer pageNum=form.getPageNum()!=null?form.getPageNum():0;
        Integer pageSize=form.getPageSize()!=null?form.getPageSize():10;
        Pageable pageable= PageRequest.of(pageNum,pageSize,new Sort(Direction.DESC,"id"));
        Specification<GranularityDO> specification=getSpe(form);
        return granularityDORepository.findAll(specification,pageable);
    }

    private Specification<GranularityDO> getSpe(GranularityForm form)
    {
        Specification<GranularityDO> specification = new Specification<GranularityDO>() {
            @Override
            public Predicate toPredicate(Root<GranularityDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(form.getVehicleId()!=null)
                {
                    predicateList.add(criteriaBuilder.equal(root.get("vehicleId"),form.getVehicleId()));
                }
                if(StringUtils.isNotBlank(form.getVin()))
                {
                    predicateList.add(criteriaBuilder.equal(root.get("vin"),form.getVin()));
                }
                if(StringUtils.isNotBlank(form.getPlateNumber()))
                {
                    predicateList.add(criteriaBuilder.equal(root.get("plateNumber"),form.getPlateNumber()));
                }
                if(StringUtils.isNotBlank(form.getCarmaker()))
                {
                    predicateList.add(criteriaBuilder.equal(root.get("carmaker"),form.getCarmaker()));
                }
                if (form.getStartTime()!=null)
                {
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("onlineDate"),form.getStartTime()));
                }
                if(form.getEndTime()!=null)
                {
                    predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("onlineDate"),form.getEndTime()));
                }
                if (StringUtils.isNotBlank(form.getTime())) {
                    predicateList.add(criteriaBuilder.equal(root.get("time"), form.getTime()));
                }
                if (StringUtils.isNotBlank(form.getArea())) {
                    predicateList.add(criteriaBuilder.like(root.get("area"), form.getArea() + "%"));
                }
                if (StringUtils.isNotBlank(form.getType())) {
                    predicateList.add(criteriaBuilder.like(root.get("type"), form.getType() + "%"));
                } return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }

        };
        return specification;
    }
}
