package com.smartQA.operation.web;

import com.smartQA.operation.service.teaoperate.*;
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
@RequestMapping("/teaoperate")
public class TeaOperateController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "teaoperate";
    }

    @RequestMapping(value = "addOutline")
    public void addOutline(Integer courseid, Integer chapters, String content, HttpServletResponse response) throws SQLException, IOException {
        TeaOperateService teaOperateSerivce = new TeaOperateService();
        boolean addoutlineres = teaOperateSerivce.addOutline(courseid,chapters,content);
        JSONObject jsonObject = new JSONObject();
        if(addoutlineres){
            jsonObject.put("code",200);
            jsonObject.put("msg","success");
            jsonObject.put("success",addoutlineres);
            jsonObject.put("result","添加提纲成功！");
        }else{
            jsonObject.put("code",500);
            jsonObject.put("msg","添加提纲失败，请检查");
            jsonObject.put("success",addoutlineres);
            jsonObject.put("result","error");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }
}