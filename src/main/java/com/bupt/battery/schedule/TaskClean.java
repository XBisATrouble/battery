package com.bupt.battery.schedule;

import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.service.ITaskDOService;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskClean {

    @Autowired
    private ITaskDOService taskDOService;
    @Value("${picFile.url}")
    private String picUrl;
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
                if(StringUtils.isNotBlank(taskDO.getPicResult()))
                {
                    String path = picUrl + taskDO.getPicResult();
                    File file = new File(path);
                    deleteFile(file);
                }
                if(StringUtils.isNotBlank(taskDO.getCsvResult()))
                {
                    String path2 = picUrl + taskDO.getCsvResult();
                    File file2=new File(path2);
                    deleteFile(file2);
                }

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
