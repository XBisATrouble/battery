package com.bupt.battery.controller;


import com.bupt.battery.entity.PortDO;
import com.bupt.battery.form.PortForm;
import com.bupt.battery.form.PortQueryForm;
import com.bupt.battery.service.IPortDOService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(path = "/api/port",method = RequestMethod.POST,produces = "application/json; charset=UTF-8",consumes="application/json")
public class PortController {
    private final IPortDOService portDOService;

    public PortController(IPortDOService portDOService) {
        this.portDOService = portDOService;
    }

    // 获取port
    @RequestMapping(path = "/get")
    public List<PortDO> getPort(@RequestBody PortQueryForm form)
    {
        if (form.getStatus().equals(-1)) {
            if (form.getPortName().equals("")) {
                return portDOService.findAll();
            } else {
                return Collections.singletonList(portDOService.findByName(form.getPortName()));
            }
        }

        if (form.getPortName().equals("")) {
            return portDOService.findByStatus(form.getStatus());
        }
        return portDOService.findByNameAndStatus(form.getPortName(),form.getStatus());
    }

    // 获取port
    @RequestMapping(path = "/list")
    public List<PortDO> getAllPort()
    {
        return portDOService.findAll();
    }

//    // 获取所有port切分页
//    @RequestMapping(path = "/list")
//    public Page<PortDO> getPortPage(@RequestBody PortQueryForm form)
//    {
//        // 实例化Page查询参数
//        PageRequest pr = new PageRequest(form.getPageNum(),form.getPageSize());
//        return portDOService.findPortPage(pr);
//    }

    @RequestMapping(path = "/create")
    public PortDO createPort(@RequestBody PortForm form)
    {
        // TODO 重复代码
        PortDO portDO = new PortDO();
        portDO.setIp(form.getIp());
        portDO.setPortDescription(form.getPortDescription());
        portDO.setPortName(form.getPortName());
        portDO.setPortNum(form.getPortNum());
        portDO.setStatus(form.getStatus());
        return portDOService.save(portDO);
    }

    @RequestMapping(path = "/update")
    public PortDO updatePort(@RequestBody PortForm form)
    {
        PortDO portDO = portDOService.getOne(form.getId());
        portDO.setIp(form.getIp());
        portDO.setPortDescription(form.getPortDescription());
        portDO.setPortName(form.getPortName());
        portDO.setPortNum(form.getPortNum());
        portDO.setStatus(form.getStatus());
        return portDOService.update(portDO);
    }

    @RequestMapping(path = "/delete")
    public void deletePort(@RequestBody PortForm form)
    {
        portDOService.delete(form.getId());
    }
}
