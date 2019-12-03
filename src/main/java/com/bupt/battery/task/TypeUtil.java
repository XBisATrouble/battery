package com.bupt.battery.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bupt.battery.Enum.StatusType;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.service.ITaskDOService;
import com.bupt.battery.util.SpringUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.StringUtils;

public class TypeUtil {
    public static void run(TaskDO taskDO,String request,String url){
        try {
            System.out.println("start");

            String[] args1 = new String[] {
                "python",
                //                "/home/python/tmat.py",
                url,
                request
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
                    taskDO.setPicResult("http://10.103.244.129/"+resultJson.get("picurl"));

                }
                else {
                    taskDO.setPicResult("");
                }
                if(StringUtils.isNotBlank(resultJson.get("csvurl").toString()))
                {
                    taskDO.setCsvResult("http://10.103.244.129/home/csv/"+resultJson.get("csvurl"));
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

                taskDO.setReason(resultJson.getString("message")!=null?resultJson.getString("message"):"数据为空");
//                taskDO.setResult("失败");
            }
            SpringUtil.getBean(ITaskDOService.class).update(taskDO);
            System.out.println(request);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
