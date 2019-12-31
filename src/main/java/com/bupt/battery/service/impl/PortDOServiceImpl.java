package com.bupt.battery.service.impl;

import com.bupt.battery.entity.PortDO;
import com.bupt.battery.repository.IPortDOPageRepository;
import com.bupt.battery.repository.IPortDORepository;
import com.bupt.battery.service.IPortDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortDOServiceImpl extends BaseServiceImpl<PortDO,Long> implements IPortDOService {
    private final IPortDORepository portDORepository;
    private final IPortDOPageRepository portDOPageRepository;

    public PortDOServiceImpl(IPortDOPageRepository portDOPageRepository, IPortDORepository portDORepository) {
        this.portDOPageRepository = portDOPageRepository;
        this.portDORepository = portDORepository;
    }

    @Override
    public List<PortDO> findByNameAndStatus(String portName, Integer status) {
        return portDORepository.findPortDOByPortNameAndAndStatus(portName,status);
    }

    @Override
    public Page<PortDO> findPortPage(Pageable pageable) {
        return portDOPageRepository.findAll(pageable);
    }

    @Override
    public List<PortDO> findByStatus(Integer status) {
        return portDORepository.findPortDOSByStatus(status);
    }

    @Override
    public PortDO findPortDOByPortNum(Integer portNum) {
        return portDORepository.findPortDOByPortNum(portNum);
    }

    @Override
    public PortDO findByName(String portName) {
        return portDORepository.findPortDOByPortName(portName);
    }
}
