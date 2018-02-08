package com.smartQA.operation.dataobject;

/**
 * 描述:
 * 课程类
 *
 * @outhor didonglin
 * @create 2018-02-08 20:50
 */
public class Course {

    private int id;
    private String name;
    private String password;
    private Integer teacher;
    private Integer capacity;
    private Integer stunum;
    private String startdate;
    private String enddate;
    private Integer isactive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStunum() {
        return stunum;
    }

    public void setStunum(Integer stunum) {
        this.stunum = stunum;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    @Override
    public String toString() {
        return "Course [id=" + id
                + ", name=" + name
                + ", password=" + password
                + ", teacher=" + teacher
                + ", capacity=" + capacity
                + ", stunum=" + stunum
                + ", startdate=" + startdate
                + ", enddate=" + enddate
                + ", isactive=" + isactive
                + "]";
    }
}