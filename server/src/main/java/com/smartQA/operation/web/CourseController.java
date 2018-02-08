package com.smartQA.operation.web;

import com.smartQA.operation.dataobject.Course;
import com.smartQA.operation.service.course.CourseService;
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
 * 课程对象的控制类
 *
 * @outhor didonglin
 * @create 2018-02-08 21:04
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @RequestMapping(value = "/getCourseList",method = RequestMethod.GET)
    public String getCourseList(ModelMap modelMap){
        List<Course> courseList=courseService.getCourseList();
        modelMap.put("courseList",courseList);
        return "courseList";
    }

    @RequestMapping(value = "/addCourse")
    public void addCourse(String name, String password, Integer teacher, Integer capacity, Integer stunum, String startdate, String enddate, Integer isactive, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        boolean res = courseService.addCourse(name, password, teacher, capacity, stunum, startdate, enddate, isactive);
        if(res){
            jsonObject.put("code",200);
            jsonObject.put("msg","success");
            jsonObject.put("success",res);
            jsonObject.put("result",new JSONObject());
        }else{
            jsonObject.put("code",500);
            jsonObject.put("msg","添加课程失败，请检查");
            jsonObject.put("success",res);
            jsonObject.put("result",new JSONObject());
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(jsonObject);
    }
}