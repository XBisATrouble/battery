package com.bupt.battery.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bupt.battery.AO.ErrorMsgAO;
import com.bupt.battery.AO.MonitorCopyAO;
import com.bupt.battery.AO.MonitorQueryAO;
import com.bupt.battery.AO.PlayBackAO;
import com.bupt.battery.config.WebSocket;
import com.bupt.battery.entity.ModelMonitorDO;
import com.bupt.battery.entity.MonitorResultDO;
import com.bupt.battery.entity.PortDO;
import com.bupt.battery.form.MonitorDCform;
import com.bupt.battery.form.MonitorQueryForm;
import com.bupt.battery.form.MonitorSaveForm;
import com.bupt.battery.service.IModelDOService;
import com.bupt.battery.service.IModelMonitorDOService;
import com.bupt.battery.service.IMonitorResultDOService;
import com.bupt.battery.service.IPortDOService;
import com.bupt.battery.task.CallMonitorThread;
import com.bupt.battery.task.ServerTask;
import com.bupt.battery.task.TaskThreadPoolExecutor;
import com.bupt.battery.util.SpringUtil;
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
    private final IModelMonitorDOService modelMonitorDOService;
    private Thread callModel;

    private DateFormat fmt_s = new SimpleDateFormat("HH:mm:ss");
    private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public MonitorController(IModelMonitorDOService modelMonitorDOService) {
        this.modelMonitorDOService = modelMonitorDOService;
    }

    //新增监控
    @RequestMapping(value = "/save")
    public JSONObject saveMonitor(@RequestBody MonitorSaveForm form) {
        JSONObject object = new JSONObject();
        object.put("error_code", 500);
        object.put("msg", "端口无效");
        ModelMonitorDO monitorDO = new ModelMonitorDO();
        List<PortDO> list = SpringUtil.getBean(IPortDOService.class).findAll();
        for (PortDO port : list) {
            if (Integer.parseInt(form.getPostId()) == port.getPortNum()) {
                monitorDO.setModelId(Long.parseLong(form.getModelId()));
                monitorDO.setCreator(form.getCreator());
                monitorDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
                monitorDO.setStatus("已就绪");
                monitorDO.setPortId(Long.parseLong(form.getPostId()));
                //更新port信息
                port.setStatus(0);
                SpringUtil.getBean(IPortDOService.class).update(port);
                monitorDO.setStartTime(form.getStartTime());
                monitorDO.setEndTime(form.getEndTime());
                monitorDO.setVehicleId(Integer.parseInt(form.getVehicleId()));
                //save to base
                monitorDO = modelMonitorDOService.save(monitorDO);

                // 多线程启用端口，端口号来自表单中的PostID
                try {
                    ServerSocket server = new ServerSocket(Integer.parseInt(form.getPostId()));
                    new Thread(new ServerTask(server)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                object.put("error_code", 200);
                object.put("msg", "新增监控成功！");
                // 启用python模型
                // TODO 这里需要改成多种模型可以适用的
                callModel = new Thread(new CallMonitorThread(form.getPostId(),Integer.parseInt(form.getModelId())));
                callModel.start();
                break;
            }
        }
        return object;
    }

    //删除监控
    @RequestMapping(value = "/delete")
    public void deleteMonitor(@RequestBody MonitorDCform form) {
        //查找要删除的monitorid
        callModel.interrupt();
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
                queryAO.setVehicleId(monitorDOList.get(i).getVehicleId()+"");
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
        MonitorCopyAO copyAO = new MonitorCopyAO();
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
        ErrorMsgAO msg0 = new ErrorMsgAO();
        msg0.setDataTime("");
        msg0.setResult("");
        String json0 = JSON.toJSONString(msg0);
        WebSocket.sendTextMessage("realtime" + monitorDO.getId(), json0);
        if (monitorDO.getStatus().equals("进行中")) {
            //循环查找数据库
            //System.out.print("vId:" + monitorDO.getVehicleId() + "pId:"+monitorDO.getPortId() + "mId:"+monitorDO.getModelId() + "");
            while (true) {
                //获取新数据
                List<MonitorResultDO> resultDOList =
                        SpringUtil.getBean(IMonitorResultDOService.class).findAllByVehicleIdAndPortIdAndModelIdAndIsRead(
                                monitorDO.getVehicleId(), monitorDO.getPortId().intValue(), 0, 0
                        );
                if (resultDOList.size() !=0 ) {
                    System.out.print(resultDOList.size());
                    for (MonitorResultDO resultDO : resultDOList) {
                        try{
                            ErrorMsgAO msgAO = new ErrorMsgAO();
                            //给前端短时间戳格式
                            msgAO.setDataTime(fmt_s.format(resultDO.getDataTime()));
                            //给前端结果（float）
                            msgAO.setResult(resultDO.getResult().toString());
                            String json = JSON.toJSONString(msgAO);
                            if (WebSocket.getOnlineCount() != 0) {
                                TimeUnit.MILLISECONDS.sleep(3000);
                                System.out.println("rdy for send msg");
                                WebSocket.sendTextMessage("realtime" + monitorDO.getId(), json);
                            } else {
                                //前端实时监控关闭
                                break;
                            }
                            //更新数据为已读状态
                            resultDO.setIsRead(1);
                            SpringUtil.getBean(IMonitorResultDOService.class).update(resultDO);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (WebSocket.getOnlineCount() == 0) {
                        break;
                    }
                }
            }
        }
    }

    @RequestMapping(value = "/playBackMonitor")
    public PlayBackAO monitorPlayBack(@RequestBody MonitorDCform form) {
        ModelMonitorDO monitorDO = modelMonitorDOService.getOne(Long.parseLong(form.getMonitorId()));
        List<String> data_time_list = new ArrayList<>();
        List<String> result_list = new ArrayList<>();
        PlayBackAO playBackAO = new PlayBackAO();
        if (monitorDO.getStatus().equals("已完成")) {
        List<MonitorResultDO> list = SpringUtil.getBean(IMonitorResultDOService.class).
                findAllByVehicleIdAndPortIdAndModelIdAndIsRead(
                        monitorDO.getVehicleId(), monitorDO.getPortId().intValue(), monitorDO.getModelId().intValue(), 1
                );
            if (list.size() > 0) {
                for (MonitorResultDO resultDO : list) {
                    data_time_list.add(fmt_s.format(resultDO.getDataTime()));
                    result_list.add(resultDO.getResult().toString());
                }
                playBackAO.setLen(list.size());
                playBackAO.setDataTime(data_time_list);
                playBackAO.setResult(result_list);
            }
        }
        return playBackAO;
    }
}