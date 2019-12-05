package com.bupt.battery.service;

import com.bupt.battery.entity.DictDO;
import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.VehicleDO;
import com.bupt.battery.form.DictForm;
import com.bupt.battery.form.GranularityForm;
import java.util.List;

public interface IDictDOService extends BaseService<DictDO,Long> {
    List<DictDO> getData(String type);

    List<DictDO> findDictDOList(DictForm form);

    void deleteByType(String type);
}
