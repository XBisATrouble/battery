package com.bupt.battery.controller;

import com.bupt.battery.entity.ResourceDO;
import com.bupt.battery.exception.MyException;
import com.bupt.battery.form.ResourceForm;
import com.bupt.battery.service.IResourceDOService;
import com.bupt.battery.util.FileUtil;
import java.io.File;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nyq
 * @date 2020/5/5 19:02
 */
@RestController
@RequestMapping(path = "/api/resource",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
public class ResourceController {
    @Autowired
    public IResourceDOService resourceDOService;
    @Value("${resourceFile.url}")
    public String filePath;
    @Value("${resource.base}")
    private String resourceBaseUrl;
    @RequestMapping(value = "/list")
    public Page<ResourceDO> findAllResource(@RequestBody ResourceForm form)
    {
         return resourceDOService.findResourceListByPage(form);
    }

    @RequestMapping(value = "/add")
    public void addResource(HttpServletRequest request,@RequestParam(value = "file", required = false) MultipartFile file) throws MyException {
        String name=file.getOriginalFilename();
        System.out.println(name);
        String type=request.getParameter("type");

        ResourceDO resourceDO=resourceDOService.findResoureDOByName(name);
        if(resourceDO!=null)
        {
            throw new MyException("文件已经存在！","505");
        }
        ResourceDO resource=new ResourceDO();
        uploadFile(file);
        resource.setName(name);
        resource.setType(type);
        resource.setUrl(name);
        resourceDOService.saveAndFlush(resource);

    }

    @RequestMapping(value = "/del")
    public void delResource(@RequestBody ResourceForm form)
    {
        ResourceDO resoureDO = resourceDOService.findResoureDOByName(form.getName());
        resourceDOService.delete(resoureDO);
        String url=filePath+resoureDO.getUrl();
        System.out.println(url);
        File file=new File(url);
        FileUtil.deleteFile(file);

    }


    private void uploadFile(MultipartFile file) {
        String contentType = file.getContentType(); // 图片文件类型
        String fileName = file.getOriginalFilename();     // 图片名字
        System.out.println(contentType);


        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);//文件处理
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(filePath);
        // 返回图片的存放路径

    }






}
