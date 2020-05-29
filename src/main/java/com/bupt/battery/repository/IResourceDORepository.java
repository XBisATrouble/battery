package com.bupt.battery.repository;


import com.bupt.battery.entity.ResourceDO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IResourceDORepository extends BaseRepository<ResourceDO,Long>, JpaSpecificationExecutor<ResourceDO> {

    List<ResourceDO> findResourceDOSByType(String type);
    ResourceDO findResourceDOByName(String name);


}
