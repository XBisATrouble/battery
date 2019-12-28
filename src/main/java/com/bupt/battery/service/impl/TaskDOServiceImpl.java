package com.bupt.battery.service.impl;

import com.bupt.battery.Enum.TaskType;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.repository.ITaskDORepository;
import com.bupt.battery.service.ITaskDOService;
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
public class TaskDOServiceImpl extends BaseServiceImpl<TaskDO,Long> implements ITaskDOService {
    @Autowired
    private ITaskDORepository taskDORepository;
    @Override
    public Page<TaskDO> findTaskListByPage(Integer pageNum,Integer pageSize,String taskName, Date startTime,Date endTime,TaskType taskType)
    {   pageNum=pageNum!=null?pageNum:0;
        pageSize=pageSize!=null?pageSize:10;
        Pageable pageable= PageRequest.of(pageNum,pageSize,new Sort(Direction.DESC,"id"));
        Specification<TaskDO> specification=new Specification<TaskDO>() {
            @Override
            public Predicate toPredicate(Root<TaskDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList=new ArrayList<>();
                if(StringUtils.isNotBlank(taskName))
                {
                    predicateList.add(criteriaBuilder.like(root.get("taskName"),taskName+"%"));
                }
                if(taskType!=null)
                {
                    predicateList.add(criteriaBuilder.equal(root.get("type"),taskType.getIndex()));
                }
                if (startTime!=null)
                {
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"),startTime));
                }
                if(endTime!=null)
                {
                    predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"),endTime));
                }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return taskDORepository.findAll(specification,pageable);
    }
}
