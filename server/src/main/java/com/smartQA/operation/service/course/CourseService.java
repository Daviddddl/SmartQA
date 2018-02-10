package com.smartQA.operation.service.course;

import com.smartQA.operation.dataobject.Course;

import java.util.List;

public interface CourseService {
    public boolean addCourse(Course course);
    public Course getCourse(String name);
    public boolean deleteCourse(String name, String password);
    public List<Course> getCourseList();
}
