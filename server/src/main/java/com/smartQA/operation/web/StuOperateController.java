package com.smartQA.operation.web;

import com.smartQA.operation.service.stuoperate.*;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 描述:
 * 学生操作控制类
 *
 * @outhor didonglin
 * @create 2018-02-10 20:17
 */
@Controller
@RequestMapping(value = "/stuoperate")
public class StuOperateController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "stuoperate";
    }

    @RequestMapping(value = "joinCourse")
    public void joinCourse(String name, String password, Integer stuID, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        boolean joinres = stuOperateService.joinCourse(name,password,stuID);
        JSONObject jsonObject = new JSONObject();
        if(joinres){
            jsonObject.put("code",200);
            jsonObject.put("msg","success");
            jsonObject.put("success",joinres);
            jsonObject.put("result","加入课程成功！");
        }else{
            jsonObject.put("code",500);
            jsonObject.put("msg","加入课程失败，请检查");
            jsonObject.put("success",joinres);
            jsonObject.put("result","error");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "putforwardQues")
    public void putfowardQues(){

    }
}