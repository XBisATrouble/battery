package com.bupt.battery.repository;

import com.bupt.battery.entity.DictDO;
import com.bupt.battery.entity.TaskDO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IDictDORepository extends BaseRepository<DictDO,Long>, JpaSpecificationExecutor<DictDO> {
    List<DictDO> getDictDOSByType(String type);
    @Transactional
    void deleteByType(String type);
}
