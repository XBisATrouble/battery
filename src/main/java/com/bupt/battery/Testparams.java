package com.bupt.battery;

import com.alibaba.fastjson.JSONObject;
import java.util.*;
import java.io.*;

public class Testparams {
    public void test() {
        JSONObject map=new JSONObject();
        map.put("\"startTime\"","\"2019-08-13\"");
        map.put("\"endTime\"","\"2019-08-13\"");
        map.put("\"vehicleId\"","\"1\"");
        String request=map.toJSONString();
        try {
            System.out.println("start");
            String[] args1 = new String[] {
                "python",
                "C:\\Users\\Administrator\\Desktop\\task11-30\\type5.py",
                request
            };
            Process pr = Runtime.getRuntime().exec(args1);
            InputStreamReader ir = new InputStreamReader(pr.getInputStream(),"GB2312");
            LineNumberReader input = new LineNumberReader(ir);
            String result=input.readLine();
            System.out.println(result);
            JSONObject resultJson=JSONObject.parseObject(result);
            System.out.println(resultJson.getString("code"));
//            System.out.println(input.readLine());
//            System.out.println(input.readLine());
            input.close();
            System.out.println("end");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Testparams test = new Testparams();
        test.test();
//        String name="Type1Task";
//        Class<?> factoryClass = Class.forName(name).newInstance();
    }
}
