package com.bupt.battery.init;

import com.bupt.battery.entity.PortDO;
import com.bupt.battery.repository.IPortDORepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 用于初始化端口数据，优先级为1
 */
@Component
@Order(value = 1)
public class InitPortData implements ApplicationRunner {

    final
    IPortDORepository iPortDORepository;

    public InitPortData(IPortDORepository iPortDORepository) {
        this.iPortDORepository = iPortDORepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<PortDO> ports = iPortDORepository.findAll();
        for (PortDO port:ports){
            port.setStatus(0);
            iPortDORepository.save(port);
        }
    }
}
