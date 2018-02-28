package com.smartQA.operation.web;

import com.smartQA.operation.service.teaoperate.*;
import com.smartQA.operation.service.utils.FileUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestHandler;
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
@RequestMapping("/teaoperate")
public class TeaOperateController {

    public JSONObject returnbooljson(boolean jsonres){
        return new StuOperateController().returnbooljson(jsonres);
    }

    public JSONObject returnstringjson(String jsonres){
        return new StuOperateController().returnstringjson(jsonres);
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "teaoperate";
    }

    @RequestMapping(value = "addOutline")
    public void addOutline(String name, Integer chapters, String content, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.addOutline(name,chapters,content);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "deleteOutline")
    public void deleteOutline(String name, Integer chapters, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.deleteOutline(name,chapters);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "changeOutline")
    public void changeOutline(String name, Integer chapters, String content, HttpServletResponse response) throws IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.changeOutline(name,chapters,content);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "findOutline")
    public void findOutline(String name, Integer chapters, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        String res = teaOperateSerivce.findOutline(name,chapters);
        JSONObject jsonObject = returnstringjson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "addQues")
    public void addQues(String name, Integer chapters, String ques, String ans, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.addQues(name,chapters,ques,ans);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "deleteQues")
    public void deleteQues(String name, Integer chapters, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.deleteQues(name,chapters);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "findQues")
    public void findQues(String name, Integer chapters, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        String res = teaOperateSerivce.findQues(name,chapters);
        JSONObject jsonObject = returnstringjson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "changeQues")
    public void changeQues(String name, String chapters, String content, HttpServletResponse response) throws IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.changeQues(name,chapters,content);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "checkQues")
    public void checkQues(Integer quesid,HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        ArrayList res = teaOperateSerivce.checkQues(quesid);
        JSONObject jsonObject = returnstringjson(res.toString());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listQues")
    public void listQues(String name, String chapters, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateService = new TeaOperateService();
        ArrayList<String> queslist = teaOperateService.listQues(name, chapters);
        JSONObject jsonObject = returnstringjson(queslist.toString());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "checkSign")
    public void checkSign(String name, HttpServletResponse response) throws IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.checkSign(name);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "getStu")
    public void getStu(String name, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        String res = teaOperateSerivce.getStu(name);
        JSONObject jsonObject = returnstringjson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "getRandStu")
    public void getRandStu(String name, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        String res = teaOperateSerivce.getRandStu(name);
        JSONObject jsonObject = returnstringjson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

}