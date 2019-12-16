package com.bupt.battery.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bupt.battery.Enum.StatusType;
import com.bupt.battery.config.WebSocket;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.form.ThreadForm;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.util.SpringUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeUtil {
    @Autowired
    private WebSocket webSocket;

    public void run(ThreadForm form){
//        System.out.println(form);
//        webSocket.sendTextMessage(form.getShopId(),form.getTaskDO().getTaskName()+"任务执行完毕");
        try {
            System.out.println("start");
            TaskDO taskDO=form.getTaskDO();
            String[] args1 = new String[] {
                "python",
                //                "/home/python/tmat.py",
                form.getUrl(),
                form.getTaskRequest()
            };
            Process pr = Runtime.getRuntime().exec(args1);
            InputStreamReader ir = new InputStreamReader(pr.getInputStream(), StandardCharsets.UTF_8);
            LineNumberReader input = new LineNumberReader(ir);
            String result = "0";
            result = input.readLine();
            System.out.println("返回值:");
            System.out.println(result);
            input.close();
            JSONObject resultJson= JSON.parseObject(result);
//            System.out.println(resultJson.toJSONString());
            System.out.println("end");
            if(resultJson!=null&&Integer.valueOf(resultJson.get("code").toString())==0)
            {
                System.out.println(resultJson.toJSONString());
                taskDO.setStatus(StatusType.Success.getName());
//                taskDO.setResultType(0);
                if(StringUtils.isNotBlank(resultJson.get("picurl").toString()))
                {
                    System.out.println(resultJson.get("picurl"));
                    taskDO.setPicResult(""+resultJson.get("picurl"));

                }
                else {
                    taskDO.setPicResult("");
                }
                if(StringUtils.isNotBlank(resultJson.get("csvurl").toString()))
                {
                    taskDO.setCsvResult(""+resultJson.get("csvurl"));
                }else {
                    taskDO.setCsvResult("");
                }
//                taskDO.setResult("http://10.103.244.129/"+result);
                taskDO.setReason(resultJson.getString("message"));
            }
//            else if(result.equals("NoData"))
//            {
//                taskDO.setStatus(StatusType.Fail.getName());
//                taskDO.setResultType(0);
//                taskDO.setResult("NoData");
//            }
            else {
                taskDO.setStatus(StatusType.Fail.getName());
//                taskDO.setResultType(0);
                if(resultJson!=null)
                {
                    taskDO.setReason(resultJson.getString("message")!=null?resultJson.getString("message"):"数据为空");
                }
                else {
                    taskDO.setReason("系统异常，请联系管理员!");
                }
//                taskDO.setResult("失败");
            }
            SpringUtil.getBean(ITaskDOService.class).update(taskDO);
            webSocket.sendTextMessage(form.getShopId(),"任务执行完毕");

        }catch (IOException e) {
//            e.printStackTrace();
            TaskDO taskDO=form.getTaskDO();
            taskDO.setStatus(StatusType.Fail.getName());
            //                taskDO.setResultType(0);
            taskDO.setReason("系统异常，请联系管理员!");
        }

    }

}
