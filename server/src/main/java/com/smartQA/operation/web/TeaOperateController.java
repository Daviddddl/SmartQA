package com.smartQA.operation.web;

import com.smartQA.operation.service.teaoperate.*;
import com.smartQA.operation.service.utils.FileUtil;
import org.json.JSONArray;
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
import java.util.HashMap;

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

    public JSONObject returnstringjson(boolean res, String jsonres){
        return new StuOperateController().returnstringjson(res, jsonres);
    }

    public JSONObject returnarrjson(boolean res, JSONArray json){
        return new StuOperateController().returnarrjson(res, json);
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "teaoperate";
    }

    @RequestMapping(value = "listMyOwnCourse")
    public void listMyOwnCourse(String userid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateService = new TeaOperateService();
        ArrayList<HashMap> courselist= teaOperateService.listMyOwnCourse(userid);
        JSONObject jsonObject = new JSONObject();
        if(courselist == null){
            jsonObject = returnarrjson(false,new JSONArray(courselist));
        }else {
            jsonObject = returnarrjson(true,new JSONArray(courselist));
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listOutline")
    public void listOutline(Integer courseid, Integer chapterid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateService = new TeaOperateService();
        ArrayList<HashMap> outlinelist= teaOperateService.listOutline(courseid,chapterid);
        JSONObject jsonObject = new JSONObject();
        if(outlinelist == null){
            jsonObject = returnarrjson(false,new JSONArray(outlinelist));
        }else {
            jsonObject = returnarrjson(true,new JSONArray(outlinelist));
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listAllOutline")
    public void listAllOutline(Integer courseid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateService = new TeaOperateService();
        ArrayList<HashMap> outlinelist= teaOperateService.listAllOutline(courseid);
        JSONObject jsonObject = new JSONObject();
        if(outlinelist == null){
            jsonObject = returnarrjson(false,new JSONArray(outlinelist));
        }else {
            jsonObject = returnarrjson(true,new JSONArray(outlinelist));
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listCourseDetail")
    public void listCourseDetail(Integer courseid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateService = new TeaOperateService();
        ArrayList<HashMap> listDetailres= teaOperateService.listCourseDetail(courseid);
        JSONObject jsonObject = new JSONObject();
        if(listDetailres == null){
            jsonObject = returnarrjson(false,new JSONArray(listDetailres));
        }else {
            jsonObject = returnarrjson(true,new JSONArray(listDetailres));
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "addOutline")
    public void addOutline(Integer courseid, Integer chapterid, String title, String content, Integer outlineid, Integer update, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.addOutline(courseid,chapterid,title, content, outlineid, update);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "deleteOutline")
    public void deleteOutline(Integer courseid, Integer chapterid, String title, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.deleteOutline(courseid,chapterid, title);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "changeOutline")
    public void changeOutline(Integer courseid, Integer chapterid, String content, HttpServletResponse response) throws IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.changeOutline(courseid,chapterid,content);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "findOutline")
    public void findOutline(Integer courseid, Integer chapterid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        String res = teaOperateSerivce.findOutline(courseid,chapterid);
        JSONObject jsonObject = returnstringjson(true,res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "addQues")
    public void addQues(Integer courseid, Integer chapterid, String ques, String options, String ans, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.addQues(courseid,chapterid,ques,options,ans);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "updateQues")
    public void updateQues(Integer quesid, String ques, String options, String ans, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.updateQues(quesid,ques,options,ans);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "deleteQues")
    public void deleteQues(Integer courseid, Integer chapterid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.deleteQues(courseid,chapterid);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "deleteQuesByID")
    public void deleteQues(Integer quesid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.deleteQuesByID(quesid);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "findQues")
    public void findQues(Integer courseid, Integer chapterid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        String res = teaOperateSerivce.findQues(courseid,chapterid);
        JSONObject jsonObject = returnstringjson(true,res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "changeQues")
    public void changeQues(Integer courseid, Integer chapterid, String content, HttpServletResponse response) throws IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.changeQues(courseid,chapterid,content);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "checkQues")
    public void checkQues(Integer quesid,HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        ArrayList res = teaOperateSerivce.checkQues(quesid);
        JSONObject jsonObject = returnstringjson(true, res.toString());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listQues")
    public void listQues(Integer courseid, Integer chapterid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateService = new TeaOperateService();
        ArrayList<HashMap> queslist = teaOperateService.listQues(courseid, chapterid);
        JSONObject jsonObject = returnarrjson(true, new JSONArray(queslist));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "listAllQues")
    public void listQues(Integer courseid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateService = new TeaOperateService();
        ArrayList<HashMap> queslist = teaOperateService.listAllQues(courseid);
        JSONObject jsonObject = returnarrjson(true, new JSONArray(queslist));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "quizQues")
    public void quizQues(String quesid, HttpServletResponse response) throws IOException, SQLException {
        TeaOperateService teaOperateService = new TeaOperateService();
        String[] quesids = quesid.split(",");
        Integer[] quesidint = new Integer[quesids.length];
        for(int i = 0 ; i < quesids.length; i++)
            quesidint[i] = Integer.valueOf(quesids[i]);
        boolean quizres = teaOperateService.quizQues(quesidint);
        JSONObject jsonObject = returnbooljson(quizres);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "checkSign")
    public void checkSign(Integer courseid, HttpServletResponse response) throws IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean res = teaOperateSerivce.checkSign(courseid);
        JSONObject jsonObject = returnbooljson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "getStu")
    public void getStu(Integer courseid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        ArrayList res = teaOperateSerivce.getStu(courseid);
        JSONObject jsonObject = new JSONObject();
        if(res == null)
            jsonObject = returnarrjson(false,new JSONArray(res));
        else
            jsonObject = returnarrjson(true, new JSONArray(res));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

    @RequestMapping(value = "getRandStu")
    public void getRandStu(Integer courseid, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        ArrayList res = teaOperateSerivce.getRandStu(courseid);
        JSONObject jsonObject = new JSONObject();
        if(res == null)
            jsonObject = returnarrjson(false, new JSONArray(res));
        else
            jsonObject = returnarrjson(true, new JSONArray(res));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }

}