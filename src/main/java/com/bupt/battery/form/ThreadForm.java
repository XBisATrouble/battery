package com.bupt.battery.form;

import com.bupt.battery.entity.TaskDO;
import lombok.Data;

/**
 * @author nyq
 * @date 2019/12/8 21:46
 */
@Data
public class ThreadForm{
    private String url;
    private TaskDO taskDO;
    private String name;
    private String taskRequest;
    private String shopId;
}
