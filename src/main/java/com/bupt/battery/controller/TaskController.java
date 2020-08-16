package com.bupt.battery.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.battery.AO.ParamAO;
import com.bupt.battery.AO.ParamListAO;
import com.bupt.battery.Enum.StatusType;
import com.bupt.battery.Enum.TaskType;
import com.bupt.battery.config.WebSocket;
import com.bupt.battery.entity.DrivingLogDO;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.entity.TaskTypeDO;
import com.bupt.battery.entity.VehicleDO;
import com.bupt.battery.form.TaskQueryForm;
import com.bupt.battery.form.TaskSaveForm;
import com.bupt.battery.form.ThreadForm;
import com.bupt.battery.service.IDrivingLogDOService;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.service.ITaskTypeDOService;
import com.bupt.battery.service.IVehicleDOService;
import com.bupt.battery.task.TaskThread;
import com.bupt.battery.task.TaskThreadPoolExecutor;
import com.bupt.battery.util.EnumUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//@CrossOrigin
@RestController
@RequestMapping(path = "/api/task",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
public class TaskController {
//    private static Logger logger= LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private ITaskDOService taskDOService;
    @Autowired
    private ITaskTypeDOService taskTypeDOService;
    @Autowired
    private IDrivingLogDOService drivingLogDOService;
    @Autowired
    private TaskThreadPoolExecutor pool;
    @Autowired
    private IVehicleDOService vehicleDOService;
    @Autowired
    private WebSocket webSocket;
    @Value("${picFile.url}")
    private String picFile;
    @Value("${csvFile.url}")
    private String csvFile;
    @Value("${host}")
    private String host;
//    @RequestParam TaskQueryForm form
    @RequestMapping(value = "/list",consumes = "application/json")
    public Page<TaskDO> queryTaskList(@RequestBody TaskQueryForm form)
    {
        return taskDOService.findTaskListByPage(form.getPageNum()-1,form.getPageSize(),form.getTaskName(),form.getStartTime(),form.getEndTime(), form.getTaskTypeId());
    }
    @RequestMapping(value = "/taskType")
    public List<TaskTypeDO> getTaskType()
    {
        return taskTypeDOService.findAll();
    }
    @RequestMapping(value = "/watch")
    public TaskDO getTaskDO(@RequestBody TaskQueryForm form)
    {
        TaskDO taskDO=taskDOService.getOne(form.getTaskId());
        taskDO.setPicResult(host+taskDO.getPicResult());
        System.out.println(taskDO);
        return taskDO;
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
        paramAO.setExpired(taskDO.getExpired());
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
        String[] paramValue=taskDO.getParamValue().split("\\|");
        // Integer vehicleId=Integer.valueOf(paramValue[paramValue.length-1]);
        String vehicleId = paramValue[paramValue.length-1];
        // change vehicleid type to String
        List<DrivingLogDO> drivingLogDOS=drivingLogDOService.findDrivingLogDOSByVehicleId(vehicleId);
        if(drivingLogDOS.size()<1)
        {
            taskDO.setStatus("失败");
            taskDO.setReason(String.format("没有车辆编号%s的数据!",vehicleId));
            taskDOService.update(taskDO);
            webSocket.sendTextMessage(form.getShopId(),"任务执行完毕！");
            return;
        }
        String taskRequest=getTaskRequest(taskDO,taskTypeDO.getParamNameCode());
        TaskType taskType=EnumUtil.getTaskType(taskDO.getType().intValue());
        String name=taskType.name();
        ThreadForm threadForm=new ThreadForm();
        threadForm.setName(name);
        threadForm.setShopId(form.getShopId());
        threadForm.setTaskDO(taskDO);
        threadForm.setTaskRequest(taskRequest);
        threadForm.setUrl(taskType.getUrl());
        TaskThread taskThread=new TaskThread(threadForm);

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
//        TaskType taskType=TaskType.valueOf(form.getTaskType());
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
                ThreadForm threadForm=new ThreadForm();
                threadForm.setName(name);
                threadForm.setShopId(form.getShopId());
                threadForm.setTaskDO(taskDO);
                threadForm.setUrl(taskType.getUrl());
                threadForm.setTaskRequest(taskRequest);
                TaskThread taskThread=new TaskThread(threadForm);
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
            ThreadForm threadForm=new ThreadForm();
            threadForm.setName(name);
            threadForm.setShopId(form.getShopId());
            threadForm.setTaskDO(taskDO);
            threadForm.setTaskRequest(taskRequest);
            threadForm.setUrl(taskType.getUrl());
            TaskThread taskThread=new TaskThread(threadForm);
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
    @GetMapping(value="/download")
    public void download(@RequestParam String type,@RequestParam Long taskId, HttpServletRequest request,HttpServletResponse resp) throws IOException
    {
        TaskDO taskDO=taskDOService.getOne(taskId);
        String path = null;
        String fileName=null;
        if (type.equals("csv"))
        {
            fileName=taskDO.getCsvResult();
            path=csvFile+fileName;
        }
        else {
            fileName=taskDO.getPicResult();
            path=picFile+fileName;
        }
//        path="F:\\版本号.png";
        //String realPath = "D:" + File.separator + "apache-tomcat-8.5.15" + File.separator + "files";
        File file = new File(path);
        System.out.println(path);
        System.out.println("name=="+fileName);
        if(!file.exists()){
            throw new IOException("文件不存在");
        }
//        resp.reset();
        resp.setContentType("application/octet-stream");
//        resp.setContentType("application/x-png");
//        resp.setCharacterEncoding("utf-8");
        resp.setContentLength((int) file.length());
//        resp.addHeader("Content-Disposition", "attachment;filename=" + name);

//        resp.addHeader("content-disposition",
//            "attachment;filename*=UTF-8''" + URLEncoder.encode(name, "UTF-8"));
        String browser="";
        try {
            browser = request.getHeader("User-Agent");
            if (-1 < browser.indexOf("MSIE 6.0") || -1 < browser.indexOf("MSIE 7.0")) {
                // IE6, IE7 浏览器
                resp.addHeader("content-disposition", "attachment;filename="
                    + new String(fileName.getBytes(), "ISO8859-1"));
            } else if (-1 < browser.indexOf("MSIE 8.0")) {
                // IE8
                resp.addHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(fileName, "UTF-8"));
            } else if (-1 < browser.indexOf("MSIE 9.0")) {
                // IE9
                resp.addHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(fileName, "UTF-8"));
            } else if (-1 < browser.indexOf("Chrome")) {
                // 谷歌
                resp.addHeader("content-disposition",
                    "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            } else if (-1 < browser.indexOf("Safari")) {
                // 苹果
                resp.addHeader("content-disposition", "attachment;filename="
                    + new String(fileName.getBytes(), "ISO8859-1"));
            } else {
                // 火狐或者其他的浏览器
                resp.addHeader("content-disposition",
                    "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
