package com.bupt.battery.service;


import com.bupt.battery.entity.ResourceDO;
import com.bupt.battery.form.DictForm;
import com.bupt.battery.form.ResourceForm;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IResourceDOService extends BaseService<ResourceDO,Long> {
     List<ResourceDO> findResoureDOByType(String type);
     ResourceDO findResoureDOByName(String name);
     Page<ResourceDO> findResourceListByPage(ResourceForm form);
}
