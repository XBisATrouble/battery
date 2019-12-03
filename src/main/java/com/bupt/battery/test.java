package com.bupt.battery;


import com.alibaba.fastjson.JSONObject;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) {

         JSONObject map=new JSONObject();
         map.put("startTime","2019-08-11");
         map.put("endTime","2019-08-12");
         map.put("vehicleId","1|2");

        System.out.println(map.toJSONString());
//        try {
//            System.out.println("start");
//            Map<String, String> params = new HashMap<>();
//            params.put("vehicleId", "3102,3103,3140");
//            params.put("startTime", "2019-11-05");
//            params.put("endTime", "2019-11-06");
//            String[] args1 = new String[] {"python", "D:\\demo.py", params.toString()};
//            Process pr = Runtime.getRuntime().exec(args1);
//
//            InputStreamReader ir = new InputStreamReader(pr.getInputStream());
//            LineNumberReader input = new LineNumberReader(ir);
//            String result = "0";
//            System.out.println("接收参数:");
//            System.out.println(input.readLine());
//            result = input.readLine();
//            System.out.println("返回参数:");
//            System.out.println(result);
//            input.close();
//            ir.close();
//            System.out.println("end");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


}