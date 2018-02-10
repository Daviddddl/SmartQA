package com.smartQA.operation.dao;

import com.smartQA.operation.dataobject.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDAO {
    public boolean addCourse(Course course);
    public Course getCourse(String name);
    public boolean deleteCourse(@Param(value = "name") String name, @Param(value = "password") String password);
    public List<Course> getCourseList();
}
