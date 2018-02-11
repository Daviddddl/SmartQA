package com.smartQA.operation.web;

import com.smartQA.operation.service.stuoperate.*;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public JSONObject returnbooljson(boolean jsonres){
        JSONObject jsonObject = new JSONObject();
        if(jsonres){
            jsonObject.put("code",200);
            jsonObject.put("msg","success");
            jsonObject.put("success",jsonres);
            jsonObject.put("result","操作成功！");
        }else{
            jsonObject.put("code",500);
            jsonObject.put("msg","操作失败，请检查");
            jsonObject.put("success",jsonres);
            jsonObject.put("result","error");
        }
        return jsonObject;
    }

    public JSONObject returnstringjson(String jsonres){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","success");
        jsonObject.put("success",true);
        jsonObject.put("result",jsonres);
        return jsonObject;
    }

    @RequestMapping(value = "joinCourse")
    public void joinCourse(String name, String password, String nickName, String remark, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        boolean joinres = stuOperateService.joinCourse(name,password,nickName, remark);
        JSONObject jsonObject = returnbooljson(joinres);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "quitCourse")
    public void quitCourse(String nickName, String remark, String name, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        boolean res = stuOperateService.quitCourse(nickName, remark,name);

        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listMyCourse")
    public void listMyCourse(String nickName, String remark, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        ArrayList res = stuOperateService.listMyCourse(nickName, remark);
        JSONObject jsonObject = returnstringjson(res.toString());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "putforwardQues")
    public void putfowardQues(String course, HttpServletResponse response) throws IOException {
        StuOperateService stuOperateService = new StuOperateService();
        stuOperateService.putforwardQues(course);
        boolean res = false;
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "mySign")
    public void mySign(HttpServletResponse response) throws IOException {
        StuOperateService stuOperateService = new StuOperateService();
        stuOperateService.mySign();
        boolean res = false;
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "markUnknown")
    public void markUnknown(String name, String chapters, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        boolean res = stuOperateService.markUnkown(name,chapters);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }
}