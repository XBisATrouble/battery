package com.bupt.battery.controller;

import com.alibaba.fastjson.JSON;
import com.bupt.battery.AO.ErrorMsgAO;
import com.bupt.battery.AO.MonitorCopyAO;
import com.bupt.battery.AO.MonitorQueryAO;
import com.bupt.battery.AO.MonitorReturnAO;
import com.bupt.battery.config.WebSocket;
import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.ModelMonitorDO;
import com.bupt.battery.form.MonitorDCform;
import com.bupt.battery.form.MonitorQueryForm;
import com.bupt.battery.form.MonitorSaveForm;
import com.bupt.battery.service.IModelDOService;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.task.CallMonitorThread;
import com.bupt.battery.task.MonitorThread;
import com.bupt.battery.task.ServerTask;
import com.bupt.battery.task.TaskThreadPoolExecutor;
//import com.sun.marlin.stats.Monitor;
//import jdk.internal.net.http.common.SSLFlowDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api/monitor",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
public class MonitorController {
    //模型监控Service
    @Autowired
    private IModelMonitorDOService modelMonitorDOService;
    //模型Service
    @Autowired
    private IModelDOService modelDOService;
    //监控线程池
    @Autowired
    private TaskThreadPoolExecutor pool;

    private DateFormat fmt_s = new SimpleDateFormat("HH:mm:ss");

    // public WebSocket webSocket = new WebSocket();

    //private Timer timer = new Timer();

    //新增监控
    @RequestMapping(value = "/save")
    public void saveMonitor(@RequestBody MonitorSaveForm form) {
        //System.out.println(form);
        ModelMonitorDO monitorDO = new ModelMonitorDO();

        monitorDO.setModelId(Long.parseLong(form.getModelId()));
        monitorDO.setCreator(form.getCreator());
        monitorDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        monitorDO.setStatus("未就绪");
        monitorDO.setStatus("进行中");
        //设置端口号
        monitorDO.setPortId(Long.parseLong(form.getPostId()));
        monitorDO.setStartTime(form.getStartTime());
        monitorDO.setEndTime(form.getEndTime());
        //save to base

        monitorDO = modelMonitorDOService.save(monitorDO);
        // return monitorDO;
//        if (monitorDO.getStatus().equals("未就绪")) {
//            String name = "Model" + monitorDO.getModelId().toString();
//            monitorDO.setStatus("已就绪");
//            modelMonitorDOService.update(monitorDO);
//            System.out.print("start run" + name + "Task" + "\n");
//            //为此监控开启线程
//            MonitorThread monitorThread = new MonitorThread(monitorDO,name);
//            pool.execute(monitorThread);
//        } else {
//            System.out.print("monitor" + monitorDO.getId() + "运行中/已完成/已失败");
//        }
        //return monitorDO;

        // 多线程启用端口，端口号来自表单中的PostID
        try {
            ServerSocket server = new ServerSocket(Integer.parseInt(form.getPostId()));
            new Thread(new ServerTask(server)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 启用python模型
        // TODO 这里需要改成多种模型可以适用的
        new Thread(new CallMonitorThread(form.getPostId())).start();
    }

    //删除监控
    @RequestMapping(value = "/delete")
    public void deleteMonitor(@RequestBody MonitorDCform form) {

        //查找要删除的monitorid
        ModelMonitorDO monitorDO =
                modelMonitorDOService.getOne(Long.parseLong(form.getMonitorId()));
        modelMonitorDOService.delete(monitorDO);
    }

    //查找监控任务
    @RequestMapping(value = "/list")
    public List<MonitorQueryAO> queryMonitor(@RequestBody MonitorQueryForm form) {
        Long modelId = Long.parseLong(form.getModelId());
        List<ModelMonitorDO> monitorDOList = modelMonitorDOService.findAllByModelId(modelId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日hh:mm");
        List<MonitorQueryAO> queryAOList = new ArrayList<>();
        //System.out.print("\n" + monitorDOList.size());
        if (monitorDOList.size() > 0) {
            for (int i = 0; i < monitorDOList.size(); i++) {
                MonitorQueryAO queryAO = new MonitorQueryAO();
                queryAO.setMonitorId(monitorDOList.get(i).getId().toString());
                queryAO.setModelId(monitorDOList.get(i).getModelId().toString());
                queryAO.setCreator(monitorDOList.get(i).getCreator());
                queryAO.setCreateTime(dateFormat.format(monitorDOList.get(i).getCreateTime()));
                queryAO.setPostId(monitorDOList.get(i).getPortId().toString());
                queryAO.setStartTime(dateFormat.format(monitorDOList.get(i).getStartTime()));
                queryAO.setEndTime(dateFormat.format(monitorDOList.get(i).getEndTime()));
                queryAO.setStatus(monitorDOList.get(i).getStatus());
                queryAOList.add(queryAO);
            }
        }
        return queryAOList;
    }

    //复制监控
    @RequestMapping(value = "/copy")
    public MonitorCopyAO copyMonitor(@RequestBody MonitorDCform form) {
        ModelMonitorDO monitorDO =
                modelMonitorDOService.getOne(Long.parseLong(form.getMonitorId()));

        //查找模型
        //ModelDO modelDO = modelDOService.getOne(monitorDO.getModelId());

        MonitorCopyAO copyAO = new MonitorCopyAO();
        //copyAO.setModelId(monitorDO.getModelId().toString());
        //copyAO.setModelName(modelDO.getModelName());
        copyAO.setPortId(monitorDO.getPortId().toString());

        List<String> timeList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        timeList.add(dateFormat.format(monitorDO.getCreateTime()));
        timeList.add(dateFormat.format(monitorDO.getEndTime()));
        copyAO.setDataContainer(timeList);
        return copyAO;
    }

    @RequestMapping(value = "/realTimeMonitor")
    public void pushToWeb(@RequestBody MonitorDCform form) {
        ModelMonitorDO monitorDO = modelMonitorDOService.getOne(Long.parseLong(form.getMonitorId()));
        if (monitorDO.getStatus().equals("进行中")) {
            String name = "Model" + monitorDO.getModelId();
            MonitorThread monitorThread = new MonitorThread(monitorDO, name);
            pool.execute(monitorThread);
        }
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
                ErrorMsgAO msgAO = new ErrorMsgAO();
                msgAO.setDataTime(fmt_s.format(new Date()));
                Random rand = new Random();
                float result = rand.nextFloat();
                msgAO.setResult(result + "");
                String json = JSON.toJSONString(msgAO);
                if (WebSocket.getOnlineCount() == 1) {
                    WebSocket.sendTextMessage("realtime" + monitorDO.getId(), json);
                } else {
                    break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        if (monitorDO.getStatus().equals("进行中")) {
//            //no data count
//            int no_data_ct = 0;
//            //循环查找数据库
//            //System.out.print("vId:" + monitorDO.getVehicleId() + "pId:"+monitorDO.getPortId() + "mId:"+monitorDO.getModelId() + "");
//            while (true) {
//                //获取新数据
//                List<MonitorResultDO> resultDOList =
//                        SpringUtil.getBean(IMonitorResultDOService.class).findAllByVehicleIdAndPortIdAndModelIdAndIsRead(
//                                monitorDO.getVehicleId(), monitorDO.getPortId().intValue(), 0, 0
//                        );
//                if (resultDOList.size() !=0 ) {
//                    System.out.print(resultDOList.size());
//                    for (MonitorResultDO resultDO : resultDOList) {
//                        try{
//                            E
    }
}