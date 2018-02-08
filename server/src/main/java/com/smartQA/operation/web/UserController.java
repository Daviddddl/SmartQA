package com.smartQA.operation.web;

import com.smartQA.operation.dataobject.User;
import com.smartQA.operation.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述:
 * 用户控制类
 *
 * @outhor didonglin
 * @create 2018-02-08 17:03
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public String getUserList(ModelMap modelMap){
        List<User> UserList=userService.getUserList();
        modelMap.put("userList",UserList);
        return "userList";
    }
}