package com.smartQA.operation.service.course;

import com.smartQA.operation.dataobject.Course;

import java.util.List;

public interface CourseService {
    public boolean addCourse(Course course);
    public Course getCourse(String courseid);
    public boolean deleteCourse(String courseid, String password);
    public List<Course> getCourseList();
}
