package com.smartQA.operation.service.course;

import com.smartQA.operation.dao.CourseDAO;
import com.smartQA.operation.dataobject.Course;
import com.smartQA.operation.service.utils.DBUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
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

    public Course getCourse(String courseid) {
        Course course = courseDAO.getCourse(courseid);
        return course;
    }

    public boolean deleteCourse(String courseid, String password) {
        return courseDAO.deleteCourse(courseid,password);
    }

    public List<Course> getCourseList() {
        List<Course> courses = courseDAO.getCourseList();
        return courses;
    }

    /**
     * 创建课程时需要检验教师ID是否在user中
     * @param teacher
     * @return
     */
    public boolean checkID(Integer teacher) throws SQLException {
        String sql = "select * from user where id = "+teacher;
        String sqlres = DBUtil.DBMonitorSQL(sql, "user");
        if(sqlres.equals(""))
            return false;
        else
            return true;
    }
}