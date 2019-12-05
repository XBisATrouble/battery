package com.bupt.battery.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.battery.AO.ParamAO;
import com.bupt.battery.AO.ParamListAO;
import com.bupt.battery.Enum.StatusType;
import com.bupt.battery.Enum.TaskType;
import com.bupt.battery.config.WebSocket;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.entity.TaskTypeDO;
import com.bupt.battery.form.TaskQueryForm;
import com.bupt.battery.form.TaskSaveForm;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.service.ITaskTypeDOService;
import com.bupt.battery.task.TaskThread;
import com.bupt.battery.task.TaskThreadPoolExecutor;
import com.bupt.battery.util.EnumUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//@CrossOrigin
@RestController
@RequestMapping(path = "/api/task",method = RequestMethod.POST,produces = "application/json; charset=UTF-8",consumes="application/json")
public class TaskController {
//    private static Logger logger= LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private ITaskDOService taskDOService;
    @Autowired
    private ITaskTypeDOService taskTypeDOService;
    @Autowired
    private TaskThreadPoolExecutor pool;
    @Autowired
    private WebSocket webSocket;
//    @RequestParam TaskQueryForm form
    @RequestMapping(value = "/list",consumes = "application/json")
    public Page<TaskDO> queryTaskList(@RequestBody TaskQueryForm form)
    {
//        TaskQueryForm form=new TaskQueryForm();
        TaskType taskType=null;
        System.out.println(form.toString());
        if(StringUtils.isNotBlank(form.getTaskType()))
        {
            taskType=TaskType.valueOf(form.getTaskType());
        }
        return taskDOService.findTaskListByPage(form.getPageNum()-1,form.getPageSize(),form.getTaskName(),form.getStartTime(),form.getEndTime(), taskType);
    }
    @RequestMapping(value = "/taskType")
    public List<TaskTypeDO> getTaskType()
    {
        return taskTypeDOService.findAll();
    }
    @RequestMapping(value = "/watch")
    public TaskDO getTaskDO(@RequestBody TaskQueryForm form)
    {
        return taskDOService.getOne(form.getTaskId());
    }
    @RequestMapping(value = "/param")
    public ParamListAO getParams(@RequestBody TaskQueryForm form)
    {
        TaskDO taskDO=taskDOService.getOne(form.getTaskId());
        TaskTypeDO taskTypeDO=taskTypeDOService.getOne(taskDO.getType());
        ParamListAO paramAO=new ParamListAO();
//        paramAO.setVehicleId(taskDO.getVehicleId());
//        paramAO.setStartTime(taskDO.getStartTime());
//        paramAO.setEndTime(taskDO.getEndTime());
        paramAO.setTaskName(taskDO.getTaskName());
        paramAO.setTaskType(taskTypeDO.getType());
        List<ParamAO> paramAOList=new ArrayList<>();
        if (StringUtils.isBlank(taskDO.getParamValue())||StringUtils.isBlank(taskTypeDO.getParamName())||StringUtils.isBlank(taskTypeDO.getParamType()))
        {
            return null;
        }
        String[] paramValues=taskDO.getParamValue().split("\\|");
        String[] paramNames=taskTypeDO.getParamName().split(",");
        String[] paramsTypes=taskTypeDO.getParamType().split(",");
//        Integer nameLength=paramNames.length;
//        Integer valueLength=paramValues.length;
//        int dis=valueLength-nameLength+1;

        for (int i=0;i<paramNames.length;i++)
        {
            ParamAO paramAO1=new ParamAO();
            paramAO1.setParamName(paramNames[i]);
            paramAO1.setParamType(paramsTypes[i]);
            System.out.println(paramValues[i]);
            System.out.println(paramNames[i]);
            paramAO1.setParamValue(paramValues[i]);
            paramAOList.add(paramAO1);
        }
        paramAO.setParamAOList(paramAOList);
        return paramAO;
    }

    @RequestMapping(value = "/run")
    public void run(@RequestBody TaskQueryForm form)
    {
        System.out.println(form);

//        webSocket.sendTextMessage(form.getShopId(),"任务执行完毕");
        TaskDO taskDO=taskDOService.getOne(form.getTaskId());
        TaskTypeDO taskTypeDO=taskTypeDOService.getOne(taskDO.getType());

        String taskRequest=getTaskRequest(taskDO,taskTypeDO.getParamNameCode());

        String name=EnumUtil.getTaskType(taskDO.getType().intValue()).name();

        TaskThread taskThread=new TaskThread(taskDO,name,taskRequest);

        taskDO.setStatus(StatusType.Process.getName());
        taskDOService.update(taskDO);

        pool.execute(taskThread);
    }
    @RequestMapping(value = "/getParam")
    public ParamListAO getParamsByType(@RequestBody  TaskQueryForm form)
    {
        if(StringUtils.isBlank(form.getTaskType()))
        {
           return null;
        }

        String type=TaskType.valueOf(form.getTaskType()).getName();
        TaskTypeDO taskTypeDO=taskTypeDOService.findTaskTypeDOByType(type);
        List<ParamAO> paramAOList=new ArrayList<>();
        String[] paramNames=taskTypeDO.getParamName().split(",");
        String[] paramsTypes=taskTypeDO.getParamType().split(",");
        for (int i=0;i<paramNames.length;i++)
        {
            ParamAO paramAO1=new ParamAO();
            paramAO1.setParamName(paramNames[i]);
            paramAO1.setParamType(paramsTypes[i]);
            paramAOList.add(paramAO1);
        }
        ParamListAO paramListAO=new ParamListAO();
        paramListAO.setParamAOList(paramAOList);
        return paramListAO;
    }
    @RequestMapping(value = "/save")
    public void saveTask(@RequestBody TaskSaveForm form)
    {
        System.out.println(form);
        String vehicles=null;
        for(int i=0;i<form.getParamAOList().size();i++)
        {
            if(form.getParamAOList().get(i).getParamName().equals("车号"))
            {
                vehicles=form.getParamAOList().get(i).getParamValue();
                System.out.println(vehicles);
            }
        }
        String[] vehicleIdList=vehicles.split(",");
        if(vehicleIdList.length>1)
        {
            for (int i=0;i<vehicleIdList.length;i++)
            {
                System.out.println(vehicleIdList.length);
                System.out.println(i);
                TaskDO taskDO=new TaskDO();
                taskDO.setCreator(form.getToken()!=null?form.getToken():"匿名");
                taskDO.setCreateTime(new Date());
                taskDO.setTaskName(form.getTaskName()+"-"+vehicleIdList[i]);
                taskDO.setExpired(form.getExpired());
                //        taskDO.setStartTime(form.getStartTime());
                //        taskDO.setEndTime(form.getEndTime());
                TaskType taskType=TaskType.valueOf(form.getTaskType());
                TaskTypeDO taskTypeDO=taskTypeDOService.findTaskTypeDOByType(taskType.getName());
                taskDO.setType(taskTypeDO.getId());
                taskDO.setStatus(StatusType.Ready.getName());
                //        taskDO.setVehicleId(form.getVehicleId());
                List<String> paramValueList=new ArrayList<>();
                for(int j=0;j<form.getParamAOList().size();j++)
                {
                    if(!form.getParamAOList().get(j).getParamName().equals("车号"))
                    {
                        paramValueList.add(form.getParamAOList().get(j).getParamValue());
                    }
                }
//                System.out.println(paramValueList.toString());
                paramValueList.add(vehicleIdList[i]);
                String paramValue=StringUtils.join(paramValueList,"|");
                taskDO.setParamValue(paramValue);
                taskDO=taskDOService.save(taskDO);

                String taskRequest=getTaskRequest(taskDO,taskTypeDO.getParamNameCode());
                String name=EnumUtil.getTaskType(taskDO.getType().intValue()).name();
                TaskThread taskThread=new TaskThread(taskDO,name,taskRequest);
                taskDO.setStatus(StatusType.Process.getName());
                taskDOService.update(taskDO);
                pool.execute(taskThread);
            }
        }
        else {
            TaskDO taskDO=new TaskDO();
            taskDO.setCreator(form.getToken()!=null?form.getToken():"匿名");
            taskDO.setCreateTime(new Date());
            taskDO.setTaskName(form.getTaskName());
            //        taskDO.setStartTime(form.getStartTime());
            //        taskDO.setEndTime(form.getEndTime());
            TaskType taskType=TaskType.valueOf(form.getTaskType());
            TaskTypeDO taskTypeDO=taskTypeDOService.findTaskTypeDOByType(taskType.getName());
            taskDO.setType(taskTypeDO.getId());
            taskDO.setStatus(StatusType.Ready.getName());

            taskDO.setExpired(form.getExpired());
            //        taskDO.setVehicleId(form.getVehicleId());
            List<String> paramValueList=form.getParamAOList().stream().map(paramAO -> {
                return paramAO.getParamValue();
            }).collect(Collectors.toList());
            String paramValue=StringUtils.join(paramValueList,"|");
            taskDO.setParamValue(paramValue);
            taskDO=taskDOService.save(taskDO);

            String taskRequest=getTaskRequest(taskDO,taskTypeDO.getParamNameCode());
            String name=EnumUtil.getTaskType(taskDO.getType().intValue()).name();
            TaskThread taskThread=new TaskThread(taskDO,name,taskRequest);
            taskDO.setStatus(StatusType.Process.getName());
            taskDOService.update(taskDO);
            pool.execute(taskThread);
        }

    }

    @RequestMapping(value = "/delete")
    public void deleteTask(@RequestBody TaskQueryForm form)
    {
        taskDOService.delete(form.getTaskId());
    }

    private String getTaskRequest(TaskDO taskDO,String paramName)
    {
        JSONObject jsonObject=new JSONObject();


//        String[] paramNames=paramName.split(",");
//        String[] paramValues=taskDO.getParamValue().split("\\|");
//        for (int i=0;i<paramNames.length;i++)
//        {
//            paramValues[i]=paramValues[i].replace(",","|");
//            String key="\""+paramNames[i]+"\"";
//            String value="\""+paramValues[i]+"\"";
//            jsonObject.put(key,value);
//        }

        String[] paramNames=paramName.split(",");
        String[] paramValues=taskDO.getParamValue().split("\\|");
        for (int i=0;i<paramNames.length;i++)
        {
            paramValues[i]=paramValues[i].replace(",","|");
            String key=paramNames[i];
            String value=paramValues[i];
            jsonObject.put(key,value);
        }
        return jsonObject.toJSONString();
    }

}
