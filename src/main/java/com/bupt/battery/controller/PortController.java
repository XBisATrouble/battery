package com.bupt.battery.controller;


import com.bupt.battery.entity.PortDO;
import com.bupt.battery.form.PortForm;
import com.bupt.battery.form.PortQueryForm;
import com.bupt.battery.service.IPortDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/port",method = RequestMethod.POST,produces = "application/json; charset=UTF-8",consumes="application/json")
public class PortController {
    @Autowired
    private IPortDOService portDOService;


    @RequestMapping(path = "/getPort")
    public PortDO getPort(@RequestBody PortQueryForm form)
    {
        return portDOService.findByNameAndStatus(form.getPortName(),form.getStatus());
    }

    @RequestMapping(path = "/list")
    public PortDO getPortList(@RequestBody PortForm form)
    {
        System.out.println(form.getPortName());
        return portDOService.findByNameAndStatus(form.getPortName(),form.getStatus());
    }
}
