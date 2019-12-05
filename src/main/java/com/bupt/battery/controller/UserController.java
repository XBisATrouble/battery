package com.bupt.battery.controller;

import com.bupt.battery.AO.LoginAO;
import com.bupt.battery.entity.UserDO;
import com.bupt.battery.form.UserForm;
import com.bupt.battery.service.IUserDOService;
import com.bupt.battery.service.impl.UserDOServiceImpl;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
public class UserController {
    @Autowired
    private IUserDOService userDOService;

    @RequestMapping(value = "/login")
    public LoginAO login(@RequestBody UserForm form) {
        UserDO userDO=userDOService.findUserDOByUsername(form.getUsername());
        LoginAO loginAO=new LoginAO();
        if(userDO==null)
        {
            loginAO.setMsg("用户名不存在");
            loginAO.setFlag(false);
            return loginAO;
        }else if(!form.getPassword().equals(userDO.getPassword()))
        {
            loginAO.setFlag(false);
            loginAO.setMsg("密码不正确");
            return loginAO;
        }
        loginAO.setFlag(true);
        loginAO.setMsg("登录成功");
        return loginAO;
    }
    @RequestMapping(value = "/query")
    public List<UserDO> query()
    {
        return userDOService.findAll();
    }
}
