package com.smartQA.operation.service.stuoperate;

import com.smartQA.operation.service.utils.DBUtil;
import com.smartQA.operation.service.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public String getCourseID(String name) throws SQLException {
        String joinfindsql = "select id from course where name = \"" + name + "\"";
        String joinres = DBUtil.DBMonitorSQL(joinfindsql, "course");
        //System.out.println(joinres);
        if(joinres.startsWith("error!"))
            return joinres;
        return (String)FileUtil.getQuoCon(joinres).get(0);
    }

    public String getUserID(String nickName, String remark) throws SQLException {
        String getidsql = "select id from user where nickName = \""+nickName+"\" and remark = \"" + remark + "\"";
        String getidres = DBUtil.DBMonitorSQL(getidsql,"user");
        if(getidres.startsWith("error!"))
            return getidres;
        return (String)FileUtil.getQuoCon(getidres).get(0);
    }

    public boolean joinCourse(String name, String password, Integer stuID) throws SQLException {

        String courseupdatesql = "UPDATE course SET stunum = stunum+1 WHERE name = \""+name + "\" and password = \""+ password+"\"";
        String getcoursessql = "select joinCourse from user where id = "+stuID;
        String courseID = getCourseID(name);
        if(!courseID.startsWith("error!")){
            //System.out.println("course表中有该课程！");
            String getcourses = DBUtil.DBMonitorSQL(getcoursessql,"course");
            ArrayList getcourseres = new ArrayList();
            if(getcourses.startsWith("error!"))
                return false;
            for(String res : getcourses.split(","))
                getcourseres.add(res);
            if(getcourseres.contains(courseID)){
                System.out.println("该用户已选过该课程！");
                return true;
            }
            String courseupres = DBUtil.DBMonitorSQL(courseupdatesql, "course");
            //System.out.println(courseupres);
            String stuupdatesql = "UPDATE user SET joinCourse = CONCAT(joinCourse, \""+courseID+",\") WHERE id = "+stuID;
            //System.out.println(stuupdatesql);
            if(courseupres.startsWith("error!"))
                return false;
            String stuupres = DBUtil.DBMonitorSQL(stuupdatesql,"user");
            if(stuupres.startsWith("error!"))
                return false;

        }else
            System.out.println(courseID);
        return true;
    }

    public boolean quitCourse(Integer userid, String name) throws SQLException {
        String courseid = getCourseID(name);
        if(courseid.startsWith("error!"))
            return false;
        String getcoursesql = "select joinCourse from user where id = " + userid;
        String getcourseres = DBUtil.DBMonitorSQL(getcoursesql,"user");
        if(getcourseres.contains(courseid+",")){
            String upjoinsql = "update user set joinCourse = replace(joinCourse, \'"+courseid+","+"\',\'\') where id = "+ userid;
            DBUtil.DBMonitorSQL(upjoinsql,"user");
            String downnumsql = "update course set stunum = stunum-1 WHERE name = \""+name+"\"";
            DBUtil.DBMonitorSQL(downnumsql,"course");
            return true;
        }
        return false;
    }

    public ArrayList<String> listMyCourse(Integer userid) throws SQLException {
        String listsql = "select joinCourse from user where id = "+userid;
        String listres = DBUtil.DBMonitorSQL(listsql,"user");
        ArrayList<String> courselistres = new ArrayList<>();
        if(listres.startsWith("error!")){
            courselistres.add(listres);
            return courselistres;
        }
        String course = (String)FileUtil.getQuoCon(listres).get(0);
        course = course.trim();
        course = course.substring(2,course.length()-1);
        String[] courselist = course.split(",");
        for(String courseid : courselist){
            String listprosql = "select id,name,teacher from course where id = "+courseid;
            String res = DBUtil.DBMonitorSQL(listprosql,course);
            courselistres.add(res);
        }
        return courselistres;
    }

    public void mySign() {

    }

    public void putforwardQues(String course) {

    }

    public boolean markUnkown(String name, String chapters) throws SQLException {
        String courseid = getCourseID(name);
        if(courseid.startsWith("error!"))
            return false;
        String marksql = "update outline set uknown = uknown+1 WHERE courseid = "+courseid + " and chapters = " + chapters;
        String upres = DBUtil.DBMonitorSQL(marksql,"outline");
        return !upres.startsWith("error!");
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(new StuOperateService().listMyCourse(16).get(2));
    }
}