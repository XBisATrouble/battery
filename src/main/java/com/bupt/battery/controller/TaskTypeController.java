package com.bupt.battery.controller;

import com.bupt.battery.entity.ResourceDO;
import com.bupt.battery.entity.TaskTypeDO;
import com.bupt.battery.form.ResourceForm;
import com.bupt.battery.service.ITaskTypeDOService;
import com.bupt.battery.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping(path = "/api/taskType",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
public class TaskTypeController {
    private final ITaskTypeDOService taskTypeDOService;
    @Value("/home/python/")
    public String filePath;

    public TaskTypeController(ITaskTypeDOService taskTypeDOService) {
        this.taskTypeDOService = taskTypeDOService;
    }

    // 获取port
    @RequestMapping(value = "/list")
    public List<TaskTypeDO> getAllPort()
    {
        return taskTypeDOService.findAll();
    }

    @RequestMapping(value = "/create")
    public TaskTypeDO createTaskType(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file)
    {
        String name=file.getOriginalFilename();
        String type = request.getParameter("type");
        String paramNameCode=request.getParameter("param_name_code");
        String paramName=request.getParameter("param_name");
        String paramType=request.getParameter("param_type");

        TaskTypeDO taskTypeDO = new TaskTypeDO();
        uploadFile(file);
        taskTypeDO.setType(type);
        taskTypeDO.setParamType(paramType);
        taskTypeDO.setParamName(paramName);
        taskTypeDO.setParamNameCode(paramNameCode);
        taskTypeDO.setPyUrl(filePath);
        taskTypeDOService.saveAndFlush(taskTypeDO);
        return taskTypeDO;
    }

    @RequestMapping(value = "/delete")
    public void deleteTaskType(@RequestBody Long id)
    {
        TaskTypeDO taskTypeDO = taskTypeDOService.findTaskTypeDoById(id);
        taskTypeDOService.delete(taskTypeDO);
        String url=filePath+taskTypeDO.getPyUrl();
        System.out.println(url);
        File file=new File(url);
        FileUtil.deleteFile(file);
    }

    private void uploadFile(MultipartFile file) {
        String contentType = file.getContentType(); // 图片文件类型
        String fileName = file.getOriginalFilename();     // 图片名字
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);//文件处理
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(filePath);
        // 返回图片的存放路径
    }
}
