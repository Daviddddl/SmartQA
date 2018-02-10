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


    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }

    public Course getCourse(String name) {
        Course course = courseDAO.getCourse(name);
        return course;
    }

    public boolean deleteCourse(String name, String password) {
        return courseDAO.deleteCourse(name,password);
    }

    public List<Course> getCourseList() {
        List<Course> courses = courseDAO.getCourseList();
        return courses;
    }
}