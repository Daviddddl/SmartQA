package com.smartQA.operation.web;

import com.smartQA.operation.dataobject.Course;
import com.smartQA.operation.service.course.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
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
}