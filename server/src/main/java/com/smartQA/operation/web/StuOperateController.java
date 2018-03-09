package com.smartQA.operation.web;

import com.smartQA.operation.service.stuoperate.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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

    @RequestMapping(value = "index",method = RequestMethod.GET)
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

    public JSONObject returnstringjson(boolean res, String jsonres){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","success");
        jsonObject.put("success",res);
        jsonObject.put("result",jsonres);
        return jsonObject;
    }

    public JSONObject returnarrjson(boolean res, JSONArray json){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("msg","success");
        jsonObject.put("success",res);
        jsonObject.put("result",json);
        return jsonObject;
    }

    @RequestMapping(value = "joinCourse")
    public void joinCourse(String courseid, String password, String userid, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        boolean joinres = stuOperateService.joinCourse(courseid,password,userid);
        JSONObject jsonObject = returnbooljson(joinres);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "quitCourse")
    public void quitCourse(String userid, String courseid, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        boolean res = stuOperateService.quitCourse(userid,courseid);

        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listMyCourse")
    public void listMyCourse(String userid, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        ArrayList<HashMap> res = stuOperateService.listMyCourse(userid);

        JSONObject jsonObject = new JSONObject();
        if(res == null) {
            jsonObject = returnarrjson(false,new JSONArray(res));
        }else {
            jsonObject = returnarrjson(true, new JSONArray(res));
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "courseDetail")
    public void courseDetail(String userid, String courseid, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        ArrayList<HashMap> res = stuOperateService.courseDetail(userid,courseid);
        JSONObject jsonObject = new JSONObject();
        if(res == null)
            jsonObject = returnarrjson(false,new JSONArray(res));
        else
            jsonObject = returnarrjson(true,new JSONArray(res));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listOutline")
    public void listOutline(String courseid, String chapterid, HttpServletResponse response) throws SQLException, IOException{
        StuOperateService stuOperateService = new StuOperateService();
        ArrayList res = stuOperateService.listOutline(courseid,chapterid);
        JSONObject jsonObject = new JSONObject();
        if(res == null){
            jsonObject = returnarrjson(false, new JSONArray(res));
        }else {
            jsonObject = returnarrjson(true, new JSONArray(res));
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listAllOutline")
    public void listAllOutline(String courseid, HttpServletResponse response) throws SQLException, IOException{
        StuOperateService stuOperateService = new StuOperateService();
        ArrayList res = stuOperateService.listallOutline(courseid);
        JSONObject jsonObject = new JSONObject();
        if(res == null){
            jsonObject = returnarrjson(false, new JSONArray(res));
        }else {
            jsonObject = returnarrjson(true, new JSONArray(res));
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "markUnknown")
    public void markUnknown(String courseid, String chapterid, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        boolean res = stuOperateService.markUnkown(courseid,chapterid);

        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listQuiz")
    public void listQuiz(Integer courseid, HttpServletResponse response) throws SQLException, IOException{
        StuOperateService stuOperateService = new StuOperateService();
        ArrayList<HashMap> res = stuOperateService.listquiz(courseid);

        JSONObject jsonObject = new JSONObject();
        if(jsonObject == null)
            jsonObject = returnarrjson(false, new JSONArray(res));
        else
            jsonObject = returnarrjson(true, new JSONArray(res));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "ansQuiz")
    public void ansQuiz(Integer userid, Integer quesid, String answer, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        Boolean res = stuOperateService.ansQuiz(userid, quesid,answer);

        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "ansQuizList")
    public void ansQuizList(Integer userid, String stuans, HttpServletResponse response) throws SQLException, IOException {
        StuOperateService stuOperateService = new StuOperateService();
        ArrayList<HashMap> res = stuOperateService.ansQuizList(userid, StuOperateService.string2jsonarr(stuans));
        System.out.println(stuans);
        System.out.println(res);
        JSONObject jsonObject = new JSONObject();
        if(res == null)
            jsonObject = returnarrjson(false, new JSONArray(res));
        else
            jsonObject = returnarrjson(true, new JSONArray(res));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listMyAns")
    public void listQuiz(Integer userid, Integer courseid, Integer chapterid, HttpServletResponse response) throws SQLException, IOException{
        StuOperateService stuOperateService = new StuOperateService();
        ArrayList<HashMap> res = stuOperateService.listMyAns(userid, courseid, chapterid);
        JSONObject jsonObject = new JSONObject();
        if(res == null)
            jsonObject = returnarrjson(false,new JSONArray(res));
        else
            jsonObject = returnarrjson(true, new JSONArray(res));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "putforwardQues")
    public void putfowardQues(String courseid, HttpServletResponse response) throws IOException {
        StuOperateService stuOperateService = new StuOperateService();
        stuOperateService.putforwardQues(courseid);
        boolean res = false;
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "mySign")
    public void mySign(HttpServletResponse response) throws IOException {
        StuOperateService stuOperateService = new StuOperateService();
        //stuOperateService.mySign();
        boolean res = false;
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

}