package com.smartQA.operation.web;

import com.smartQA.operation.dataobject.User;
import com.smartQA.operation.service.user.UserService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        List<User> userList=userService.getUserList();
        modelMap.put("userList",userList);
        return "userList";
    }

    @RequestMapping(value = "/addUser")
    public void addUser(User user, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        boolean res = userService.addUser(user);
        if(res){
            jsonObject.put("code",200);
            jsonObject.put("msg","success");
            jsonObject.put("success",res);
            jsonObject.put("result",user.getId()+user.getnickName());
        }else{
            jsonObject.put("code",500);
            jsonObject.put("msg","添加用户失败，请检查");
            jsonObject.put("success",res);
            jsonObject.put("result","error!");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }
}