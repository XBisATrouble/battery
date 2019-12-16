package com.bupt.battery;

import com.bupt.battery.service.IDictDOService;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@SpringBootTest(classes = BatteryApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BatteryApplicationTests {

    //    @Test
    //    void contextLoads() {
    //    }
    @Autowired
    private IDictDOService dictDOService;
    @Test
    public void dictTest() {
        String type="整车厂";
        System.out.println(dictDOService.getData(type).stream().map(dictDO -> {
            return dictDO.getValue();
        }).collect(Collectors.toList()).toString());
    }


    //Task在这里测试
    //    @Test
    //    public void taskTest()
    //    {
    //        Integer vehicleId=3102;
    //        try {
    //            Date startDate = DateUtils.parseDate("20190712 00:00:00", Locale.TRADITIONAL_CHINESE, "yyyyMMdd hh:mm:ss");
    //            Date endDate = DateUtils.parseDate("20191101 10:00:00", Locale.TRADITIONAL_CHINESE, "yyyyMMdd hh:mm:ss");
    //            TaskRequest taskRequest=new TaskRequest();
    //            taskRequest.setStartTime(startDate);
    //            taskRequest.setEndTime(endDate);
    //            taskRequest.setVehicleId(vehicleId);
    //            System.out.println(taskRequest.toString());
    //
    //            BaseTask task=SpringUtil.getBean(Type8Task.class);
    ////            BaseTask task1=new Type1Task();
    //            task.excute(taskRequest);
    //            System.out.println("执行完毕~~~");
    //        }catch (Exception e)
    //        {
    //            e.printStackTrace();
    //        }
    //
    //    }



}
