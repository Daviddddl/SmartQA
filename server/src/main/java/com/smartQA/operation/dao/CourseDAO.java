package com.smartQA.operation.dao;

import com.smartQA.operation.dataobject.Course;

import java.util.List;

public interface CourseDAO {
    public boolean addCourse(String name, String password, Integer teacher, Integer capacity, Integer stunum, String startdate, String enddate, Integer isactive);
    public Course getCourse(String name);
    public boolean deleteCourse(String name, String password);
    public List<Course> getCourseList();
}
