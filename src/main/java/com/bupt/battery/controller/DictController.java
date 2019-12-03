package com.bupt.battery.controller;

import com.bupt.battery.AO.DictDataAO;
import com.bupt.battery.entity.DictDO;
import com.bupt.battery.form.DictForm;
import com.bupt.battery.service.IDictDOService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/dict",method = RequestMethod.POST,produces = "application/json;charset=utf-8",consumes = "application/json")
public class DictController {
    @Autowired
    private IDictDOService dictDOService;
    @RequestMapping(path = "/getData")
    private List<String> getData(@RequestBody DictForm form)
    {
        return dictDOService.getData(form.getType()).stream().map(dictDO -> {
            return dictDO.getValue();
        }).collect(Collectors.toList());
    }
    @RequestMapping(path = "/dictData")
    private List<DictDataAO> getDictData(@RequestBody DictForm form)
    {
        List<DictDO> dictDOList=dictDOService.findDictDOList(form);
        List<DictDataAO> dictDataAOList=new ArrayList<>();
        List<String> keyList=dictDOList.stream().map(dictDO -> {
            return dictDO.getType();
        }).collect(Collectors.toList());
        Set<String> keySet=new HashSet<>(keyList);
        keyList.clear();
        keyList.addAll(keySet);
        for (int i=0;i<keyList.size();i++)
        {
            DictDataAO dictDataAO=new DictDataAO();
            List<String> valueList=new ArrayList<>();
            for (int j=0;j<dictDOList.size();j++)
            {
                if(dictDOList.get(j).getType().equals(keyList.get(i))){
                    valueList.add(dictDOList.get(j).getValue());
                }
            }
            dictDataAO.setAttributeName(keyList.get(i));
            dictDataAO.setAttributeValue(valueList);
            dictDataAOList.add(dictDataAO);
        }
        return dictDataAOList;
    }
    @RequestMapping(path = "/update")
    private void update(@RequestBody DictForm form)
    {
        System.out.println(form);
        dictDOService.deleteByType(form.getParamName());
        for(int i=0;i<form.getParamValue().size();i++)
        {
            DictDO dictDO=new DictDO();
            dictDO.setType(form.getParamName());
            dictDO.setValue(form.getParamValue().get(i));
            dictDOService.save(dictDO);
        }
    }

}
