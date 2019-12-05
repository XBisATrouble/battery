package com.bupt.battery.schedule;

import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.service.ITaskDOService;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskClean {

    @Autowired
    private ITaskDOService taskDOService;
    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "*/5 * * * * ?")
    public void scheduled(){
//        System.out.println("使用cron: {}");
        List<TaskDO> taskDOList=taskDOService.findAll();
        for(int i=0;i<taskDOList.size();i++)
        {
            TaskDO taskDO=taskDOList.get(i);
            Calendar c = Calendar.getInstance();
            c.setTime(taskDO.getCreateTime());
            c.add(Calendar.DAY_OF_MONTH, taskDO.getExpired());

            Date date=new Date();
            if(c.getTime().before(date))
            {
                taskDOService.delete(taskDO.getId());
//                String path = "/home/picture/" + taskDO.getResult();
//                File file = new File(path);
//                deleteFile(file);
//                String path2 = "/home/picture/" + taskDO.getCsvResult();
//                File file2=new File(path2);
//                deleteFile(file2);
            }
        }



    }

    private void deleteFile(File file) {
        try {
            if (file.isFile()) {
                // 删除符合条件的文件
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
