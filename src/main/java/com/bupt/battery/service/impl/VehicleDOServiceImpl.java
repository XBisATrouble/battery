package com.bupt.battery.service.impl;

import com.bupt.battery.entity.VehicleDO;
import com.bupt.battery.form.GranularityForm;
import com.bupt.battery.repository.IVehicleDORepository;
import com.bupt.battery.service.IVehicleDOService;
import java.util.ArrayList;
import java.util.Date;
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
public class VehicleDOServiceImpl extends BaseServiceImpl<VehicleDO,Long> implements IVehicleDOService {
    @Autowired
    private IVehicleDORepository vehicleDORepository;
    @Override
    public List<VehicleDO> findVehicleDOS(String drivingArea, String batteryType, Date startDate, Date endDate) {
        return vehicleDORepository.findVehicleDOSByDrivingAreaAndBatteryTypeAndOnlineDateBetween(drivingArea,batteryType,startDate,endDate);
    }
    @Override
    public List<VehicleDO> findVehicleDOList(GranularityForm form) {
        Specification<VehicleDO> specification = getSpe(form);
        return vehicleDORepository.findAll(specification);
    }

    @Override
    public List<VehicleDO> findVehicleDOSByVehicleId(Integer vehicleId) {
        return vehicleDORepository.findVehicleDOSByVehicleId(vehicleId);
    }

    @Override
    public Page<VehicleDO> findVehicleDOPage(GranularityForm form) {
        Integer pageNum=form.getPageNum()!=null?form.getPageNum():0;
        Integer pageSize=form.getPageSize()!=null?form.getPageSize():10;
        Pageable pageable= PageRequest.of(pageNum,pageSize,new Sort(Direction.DESC,"id"));
        Specification<VehicleDO> specification = getSpe(form);
        return vehicleDORepository.findAll(specification,pageable);
    }

    private Specification<VehicleDO> getSpe(GranularityForm form)
    {
        Specification<VehicleDO> specification = new Specification<VehicleDO>() {
            @Override
            public Predicate toPredicate(Root<VehicleDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(form.getVehicleId()!=null)
                {
                    predicateList.add(criteriaBuilder.equal(root.get("vehicleId"),form.getVehicleId()));
                }
                if(form.getVin()!=null)
                {
                    predicateList.add(criteriaBuilder.like(root.get("vin"),form.getVin()+"%"));
                }
                if(form.getPlateNumber()!=null)
                {
                    predicateList.add(criteriaBuilder.like(root.get("plateNumber"),form.getPlateNumber()+"%"));
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
                    predicateList.add(criteriaBuilder.equal(root.get("onlineDate"), form.getTime()));
                }
                if (StringUtils.isNotBlank(form.getArea())) {
                    predicateList.add(criteriaBuilder.like(root.get("drivingArea"), form.getArea() + "%"));
                }
                if (StringUtils.isNotBlank(form.getType())) {
                    predicateList.add(criteriaBuilder.like(root.get("batteryType"), form.getType() + "%"));
                } return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }

        };
        return specification;
    }

}
