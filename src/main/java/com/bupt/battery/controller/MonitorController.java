package com.bupt.battery.controller;

import com.alibaba.fastjson.JSON;
import com.bupt.battery.AO.*;
import com.bupt.battery.config.WebSocket;
import com.bupt.battery.entity.ModelDO;
import com.bupt.battery.entity.ModelMonitorDO;
import com.bupt.battery.entity.MonitorResultDO;
import com.bupt.battery.form.MonitorDCform;
import com.bupt.battery.form.MonitorQueryForm;
import com.bupt.battery.form.MonitorSaveForm;
import com.bupt.battery.service.IModelDOService;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.service.IMonitorResultDOService;
import com.bupt.battery.task.MonitorThread;
import com.bupt.battery.task.TaskThreadPoolExecutor;
//import com.sun.marlin.stats.Monitor;
//import jdk.internal.net.http.common.SSLFlowDelegate;
import com.bupt.battery.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IMonitorResultDOService monitorResultDOService;
    //监控线程池
    @Autowired
    private TaskThreadPoolExecutor pool;

    private DateFormat fmt_s = new SimpleDateFormat("HH:mm:ss");
    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //新增监控
    @RequestMapping(value = "/save")
    public void saveMonitor(@RequestBody MonitorSaveForm form)
    {
//        List<MonitorResultDO> list =
//                monitorResultDOService.findAllByVehicleIdAndPortIdAndModelIdAndIsRead(
//                        Integer.parseInt(form.getVehicleId()), Integer.parseInt(form.getPostId()),
//                                Integer.parseInt(form.getModelId()), 0
//                );
//        if (list.size() != 0) {
//            //返回该监控已存在
//        } else {
//            //System.out.println(form);
//            ModelMonitorDO monitorDO = new ModelMonitorDO();
//
//            monitorDO.setModelId(Long.parseLong(form.getModelId()));
//            monitorDO.setCreator(form.getCreator());
//            monitorDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
//            monitorDO.setStatus("进行中");
//            //monitorDO.setStatus("进行中");
//            //设置端口号
//            monitorDO.setPortId(Long.parseLong(form.getPostId()));
//            monitorDO.setStartTime(form.getStartTime());
//            monitorDO.setEndTime(form.getEndTime());
//            monitorDO.setVehicleId(Integer.parseInt(form.getVehicleId()));
//            //save to base
//            monitorDO = modelMonitorDOService.save(monitorDO);
//            if (monitorDO.getStatus().equals("未就绪")) {
//                String name = "ModelStateTask";
//                //为此监控开启线程
//                MonitorThread monitorThread = new MonitorThread(monitorDO,name);
//                pool.execute(monitorThread);
//            } else {
//                System.out.print("monitor" + monitorDO.getId() + "运行中/已完成/已失败");
//            }
//        }
        //System.out.println(form);
        ModelMonitorDO monitorDO = new ModelMonitorDO();

        monitorDO.setModelId(Long.parseLong(form.getModelId()));
        monitorDO.setCreator(form.getCreator());
        monitorDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //monitorDO.setStatus("已完成");
        monitorDO.setStatus("进行中");
        //设置端口号
        monitorDO.setPortId(Long.parseLong(form.getPostId()));
        monitorDO.setStartTime(form.getStartTime());
        monitorDO.setEndTime(form.getEndTime());
        monitorDO.setVehicleId(Integer.parseInt(form.getVehicleId()));
        //save to base
        monitorDO = modelMonitorDOService.save(monitorDO);
        if (monitorDO.getStatus().equals("未就绪")) {
            String name = "ModelStateTask";
            //为此监控开启线程
            //MonitorThread monitorThread = new MonitorThread(monitorDO,name);
            //pool.execute(monitorThread);
        } else {
            System.out.print("monitor" + monitorDO.getId() + "运行中/已完成/已失败");
        }
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
                queryAO.setVehicleId(monitorDOList.get(i).getVehicleId() + "");
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
        copyAO.setVehicleId(monitorDO.getVehicleId() + "");
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
                msgAO.setResult(result+"");
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
//                            ErrorMsgAO msgAO = new ErrorMsgAO();
//                            //给前端短时间戳格式
//                            msgAO.setDataTime(fmt_s.format(resultDO.getDataTime()));
//                            //给前端结果（float）
//                            msgAO.setResult(resultDO.getResult().toString());
//                            String json = JSON.toJSONString(msgAO);
//                            if (WebSocket.getOnlineCount() == 1) {
//                                TimeUnit.MILLISECONDS.sleep(3000);
//                                WebSocket.sendTextMessage("realtime" + monitorDO.getId(), json);
//                            } else {
//                                //前端实时监控关闭
//                                break;
//                            }
//                            //更新数据为已读状态
//                            resultDO.setIsRead(1);
//                            SpringUtil.getBean(IMonitorResultDOService.class).update(resultDO);
//
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } else {
//                    no_data_ct = no_data_ct+1;
//                    //当获取无数据次数达到10次
//                    if (no_data_ct == 10000) {
//                        monitorDO.setStatus("已失败");
//                        SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
//                        break;
//                    }
//                }
//                //系统时间达到设定时间 跳出循环
//                if (fmt.format(monitorDO.getStartTime()).equals(fmt.format(new Date()))) {
//                    monitorDO.setStatus("已完成");
//                    SpringUtil.getBean(IModelMonitorDOService.class).update(monitorDO);
//                    break;
//                }
//            }
//        }
    }

    @RequestMapping(value = "/playBackMonitor")
    public PlayBackAO monitorPlayBack(@RequestBody MonitorDCform form) {
        ModelMonitorDO monitorDO = modelMonitorDOService.getOne(Long.parseLong(form.getMonitorId()));
        List<String> data_time_list = new ArrayList<>();
        List<String> result_list = new ArrayList<>();
        PlayBackAO playBackAO = new PlayBackAO();
        //if (monitorDO.getStatus().equals("已完成")) {
            List<MonitorResultDO> list = SpringUtil.getBean(IMonitorResultDOService.class).
                    findAllByVehicleIdAndPortIdAndModelIdAndIsRead(
                            monitorDO.getVehicleId(), monitorDO.getPortId().intValue(), monitorDO.getModelId().intValue(), 1
                    );
            if (list.size() > 0) {
                for (MonitorResultDO resultDO : list) {
                    data_time_list.add(fmt_s.format(resultDO.getDataTime()));
                    result_list.add(resultDO.getResult().toString());
                }
                playBackAO.setDataTime(data_time_list);
                playBackAO.setResult(result_list);
            }
        //}
        return playBackAO;
    }
}
