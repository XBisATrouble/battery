package com.bupt.battery.service.impl;

import com.bupt.battery.entity.DictDO;
import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.VehicleDO;
import com.bupt.battery.form.DictForm;
import com.bupt.battery.form.GranularityForm;
import com.bupt.battery.repository.IDictDORepository;
import com.bupt.battery.service.IDictDOService;
import com.bupt.battery.service.IModelDOService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.plaf.DesktopIconUI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DictDOServiceImpl extends BaseServiceImpl<DictDO,Long> implements IDictDOService {
    @Autowired
    private IDictDORepository dictDORepository;
    @Override
    public List<DictDO> getData(String type) {
        return dictDORepository.getDictDOSByType(type);
    }

    @Override
    public List<DictDO> findDictDOList(DictForm form) {
        return dictDORepository.findAll(getSpe(form));
    }

    @Override
    public void deleteByType(String type) {
        dictDORepository.deleteByType(type);
    }

    private Specification<DictDO> getSpe(DictForm form)
    {
        return new Specification<DictDO>() {
            @Override
            public Predicate toPredicate(Root<DictDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList=new ArrayList<>();
                if(StringUtils.isNotBlank(form.getType()))
                {
                    predicateList.add(criteriaBuilder.like(root.get("type"),form.getType()+"%"));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

    }
}
