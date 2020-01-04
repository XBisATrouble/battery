package com.bupt.battery.task;

import com.alibaba.fastjson.JSONObject;
import com.bupt.battery.Enum.StatusType;
import com.bupt.battery.config.WebSocket;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.util.SpringUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class CleanTask{

    public void excute(String request, String shopId) {

        System.out.println("request:-------"+request);
        try {
            System.out.println("start");
            String[] args1 = new String[] {
                    "python",
                    "D:\\model\\Clean\\pre1.py",
                    request
            };
            Process pr = Runtime.getRuntime().exec(args1);
            InputStreamReader ir = new InputStreamReader(pr.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String result = "0";

            result = input.readLine();
            System.out.println("返回值:");
            System.out.println(result);
            input.close();
            System.out.println("end");
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            WebSocket.sendTextMessage(shopId, "清洗任务完成");
        }
    }
}

