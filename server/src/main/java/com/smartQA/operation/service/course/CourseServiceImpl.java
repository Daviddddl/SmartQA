package com.smartQA.operation.service.course;

import com.smartQA.operation.dao.CourseDAO;
import com.smartQA.operation.dataobject.Course;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述:
 * 课程对象的接口实现类
 *
 * @outhor didonglin
 * @create 2018-02-08 20:59
 */
@Service
public class CourseServiceImpl implements CourseService{

    @Resource
    private CourseDAO courseDAO;


    public boolean addCourse(String name, String password, Integer teacher, Integer capacity, Integer stunum, String startdate, String enddate, Integer isactive) {
        courseDAO.addCourse(name,password,teacher,capacity,stunum,startdate,enddate,isactive);
        return false;
    }

    public Course getCourse(String name) {
        Course course = courseDAO.getCourse(name);
        return course;
    }

    public boolean deleteCourse(String name, String password) {
        courseDAO.deleteCourse(name,password);
        return false;
    }

    public List<Course> getCourseList() {
        List<Course> courses = courseDAO.getCourseList();
        return courses;
    }
}