package com.smartQA.operation.service.stuoperate;

import com.smartQA.operation.service.utils.DBUtil;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * 描述:
 * 学生操作接口的实现类
 *
 * @outhor didonglin
 * @create 2018-02-10 19:16
 */
@Service
public class StuOperateService {

    public StuOperateService(){}

    public boolean joinCourse(String name, String password, String stuID) throws SQLException {
        String joinfindsql = "select * from course where name = \"" + name + "\" && password = \""+ password+"\"";
        String courseupdatesql = "UPDATE course SET stunum = stunum+1 WHERE name = \""+name + "\" and password = \""+ password+"\"";
        String joinres = DBUtil.DBMonitorSQL(joinfindsql, "course");
        if(!joinres.equals("")){
            System.out.println("course表中有该课程！");
            String courseupres = DBUtil.DBMonitorSQL(courseupdatesql, "course");
            System.out.println(courseupres);
            String courseid = DBUtil.DBMonitorSQL("select id from course where name = \""+name +"\" and password = \""+password+"\"","course");
            String stuupdatesql = "UPDATE user SET joinCourse = CONCAT(joinCourse, \","+courseid+"\") WHERE id = "+stuID;
            if(courseupres.contains("失败"))
                return false;
            String stuupres = DBUtil.DBMonitorSQL(stuupdatesql,"user");
            System.out.println(stuupdatesql);
            if(stuupres.contains("失败"))
                return false;
        }
        return true;
    }

    public boolean quitCourse(String name) {
        return false;
    }

    public void listMyCourse() {

    }

    public void mySign() {

    }

    public void putforwardQues(String course) {

    }
}