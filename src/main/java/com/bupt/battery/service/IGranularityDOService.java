package com.bupt.battery.service;

import com.bupt.battery.entity.GranularityDO;
import com.bupt.battery.entity.VehicleDO;
import com.bupt.battery.form.GranularityForm;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;

public interface IGranularityDOService extends BaseService<GranularityDO,Long> {

    List<GranularityDO> findGranularityList(GranularityForm form);

    GranularityDO getGranularityDOSByAreaAndTimeAndType(String area,String time,String type);
//    Page<GranularityDO> findGranularityPage(GranularityForm form);
}