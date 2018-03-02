package com.smartQA.operation.service.stuoperate;

import com.smartQA.operation.service.utils.DBUtil;
import com.smartQA.operation.service.utils.FileUtil;
import com.smartQA.operation.web.StuOperateController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Service;

import javax.management.ObjectName;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Adler32;

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

    public boolean joinCourse(String name, String password, String userid) throws SQLException {

        String courseupdatesql = "UPDATE course SET stunum = stunum+1 WHERE name = \""+name + "\" and password = \""+ password+"\"";
        String getcoursessql = "select joinCourse from user where id = "+userid;
        String courseID = getCourseID(name);
        if(!courseID.startsWith("error!")){
            //System.out.println("course表中有该课程！");
            String getcourses = DBUtil.DBMonitorSQL(getcoursessql,"course");
            if(getcourses.startsWith("error!"))
                return false;
            String course = (String)FileUtil.getQuoCon(getcourses).get(0);
            String[] courselist = course.split(",");
            for(String s : courselist){
                if(s.equals(courseID)){
                    System.out.println("该用户已选过该课程！");
                    return false;
                }
            }
            String courseupres = DBUtil.DBMonitorSQL(courseupdatesql, "course");
            //System.out.println(courseupres);
            String stuupdatesql = "UPDATE user SET joinCourse = CONCAT(joinCourse, \""+courseID+",\") WHERE id = "+userid;
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

    public boolean quitCourse(String userid, String courseid) throws SQLException {

        String getcoursesql = "select joinCourse from user where id = " + userid;
        String getcourseres = DBUtil.DBMonitorSQL(getcoursesql,"user");
        if(getcourseres.contains(courseid+",")){
            String upquitsql = "update user set joinCourse = replace(joinCourse, \'"+courseid+","+"\',\'\') where id = "+ userid;
            DBUtil.DBMonitorSQL(upquitsql,"user");
            String downnumsql = "update course set stunum = stunum-1 WHERE id = \""+courseid+"\"";
            DBUtil.DBMonitorSQL(downnumsql,"course");
            return true;
        }
        return false;
    }

    public ArrayList<HashMap> listMyCourse(String userid) throws SQLException {
        ArrayList<String> courselistres = new ArrayList<>();
        String listsql = "select joinCourse from user where id = "+userid;
        String listres = DBUtil.DBMonitorSQL(listsql,"user");
        if(listres.startsWith("error!") || listres.length() <= 8)
            return null;
        String course = (String)FileUtil.getQuoCon(listres).get(0);
        course = course.trim();
        String[] courselist = course.split(",");
        for(String courseid : courselist){
            String listprosql = "select id,name,isactive from course where id = "+courseid;
            String res = DBUtil.DBMonitorSQL(listprosql,course);
            courselistres.add(res);
        }

        ArrayList arrayList = FileUtil.getQuoCon(courselistres.toString());
        ArrayList<HashMap> courses = new ArrayList<>();
        for(int i = 0 ; i<arrayList.size();i++){
            HashMap<String, String> map = new HashMap<>();
            map.put("id", (String)arrayList.get(i));
            map.put("name", (String)arrayList.get(++i));
            map.put("isactive", (String)arrayList.get(++i));
            //map.put("teacherid", (String)arrayList.get(++i));
            courses.add(map);
        }
        return courses;
    }

    public ArrayList<HashMap> courseDetail(String userid, String courseid) throws SQLException {
        ArrayList<HashMap> detailres = new ArrayList<>();
        String detailsql = "select * from course where id = "+courseid;
        String getdetailres = DBUtil.DBMonitorSQL(detailsql,"course");
        if(getdetailres.startsWith("error"))
            return null;
        ArrayList res = FileUtil.getQuoCon(getdetailres);
        for(int i = 0 ;i < res.size(); i++){
            HashMap<String, String> map = new HashMap<>();
            String id = (String)res.get(i);
            //map.put("courseid", id);
            map.put("coursename", (String)res.get(++i));
            String coursepwd = (String)res.get(++i);
            String teacherid = (String)res.get(++i);
            String teachername = DBUtil.DBMonitorSQL("select nickname,remark from user where id = "+teacherid,"user");
            ArrayList teaname = FileUtil.getQuoCon(teachername);
            map.put("teacher_nickname",(String)teaname.get(0));
            map.put("teacher_remark",(String)teaname.get(1));
            String capacity = (String)res.get(++i);
            map.put("capacity",capacity);
            String stunum = (String)res.get(++i);
            //map.put("stunum", stunum);
            String starttime = (String)res.get(++i);
            String endtime = (String)res.get(++i);
            map.put("starttime",starttime);
            map.put("endtime",endtime);
            String isactive = (String)res.get(++i);
            map.put("outline",listallOutline(courseid).toString());

            detailres.add(map);
        }
        return detailres;
    }

    public ArrayList<HashMap> listallOutline(String courseid) throws SQLException {
        String coursenumsql = "select chapters from outline where courseid = " + courseid;
        String chaptersres = DBUtil.DBMonitorSQL(coursenumsql,"outline");
        ArrayList chapters = FileUtil.getQuoCon(chaptersres);
        ArrayList<HashMap> res = new ArrayList<>();
        for(int i = 0; i<chapters.size();i++){
            ArrayList outline = listOutline(courseid,(String)chapters.get(i));
            for(int j = 0 ; j < outline.size(); j++)
                res.add((HashMap) outline.get(j));
        }
        return res;
    }

    public ArrayList<HashMap> listOutline(String courseid, String chapters) throws SQLException {
        ArrayList<HashMap> outlineans = new ArrayList<>();

        String getoutlinesql = "select chapters,title,content from outline where courseid = "+courseid + " and chapters = "+chapters;
        String getoutlineres = DBUtil.DBMonitorSQL(getoutlinesql,"outline");
        if(getoutlineres.startsWith("error"))
            return null;
        ArrayList res = FileUtil.getQuoCon(getoutlineres);
        for(int i = 0 ;i <res.size();i++){
            HashMap<String, String> map = new HashMap<>();
            map.put("chapterid", (String)res.get(i));
            map.put("title", (String)res.get(++i));
            map.put("content", (String)res.get(++i));
            outlineans.add(map);
        }
        return outlineans;
    }

    public void mySign(String tealocation, String stulocation, String standtime, String signtime) {
        //计算学生和老师之间的距离
        //判断是否在给定的范围内

        //判断学生签到时间是否超时


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

    public ArrayList<String> ansQuiz(String userid, String courseid, String chapters, String[] quesid, String[] answer) throws SQLException {

        ArrayList<String> res = new ArrayList<>();
        if(courseid.startsWith("error!") || userid.startsWith("error!"))
            res.add("error! 没有该用户ID或课程ID!");
        if(quesid.length != answer.length) {
            res.add("error! 问题个数与回答个数不符合!");
        }else{
            for(int i = 0; i< quesid.length;i++){

                String checkquesidsql = "select * from question where id = "+i;
                String checkquesidres = DBUtil.DBMonitorSQL(checkquesidsql,"question");
                if(checkquesidres.startsWith("error")){
                    res.add("error! 没有该题号: "+i);
                    return res;
                }

                //向学生答案表中插入答题结果
                String addanssql = "insert into useranswer (userid, courseid, chapters, quesid, stuanswer) values ("+ userid+","+courseid + ","+chapters+"," +quesid[i] + ", \"" +answer[i] +"\")";
                String addansres = DBUtil.DBMonitorSQL(addanssql, "useranswer");

                //统计答题人数
                String addansnumsql = "update question set ansnum = ansnum+1 where id =" +quesid[i];
                String addansnumres = DBUtil.DBMonitorSQL(addansnumsql, "question");

                //之后判断答题结果是否正确
                String getanssql = "select ans from question where id = " + quesid[i];
                String getans = DBUtil.DBMonitorSQL(getanssql, "question");
                if(!getans.startsWith("error")) {
                    ArrayList ans = FileUtil.getQuoCon(getans);
                    boolean ansres = ans != null && answer[i].equals(ans.get(0));
                    if(ansres){
                        String addcorrectsql = "update question set correct = correct+1 where id = " + quesid[i];
                        String addcorrectres = DBUtil.DBMonitorSQL(addcorrectsql, "question");
                    }
                    res.add(Boolean.toString(ansres));
                }
                else
                    res.add("false");
            }
        }
        return res;
    }


    public ArrayList<String> listquiz(String coursename) throws SQLException {
        String courseid = getCourseID(coursename);
        String listquizsql = "select id,ques from question where courseid = "+courseid +" and isquiz = 1";
        String listquizres = DBUtil.DBMonitorSQL(listquizsql, "question");
        ArrayList<String> res = new ArrayList<>();
        if(listquizres.startsWith("error")){
            res.add("error!没有课堂测试题！");
            return res;
        }
        res = FileUtil.getQuoCon(listquizres);
        return res;
    }

    public ArrayList<String> listMyAns(String userid, String courseid, String chapters) throws SQLException {

        String listmyanssql = "select quesid,stuanswer from useranswer where userid = "+userid +" and courseid = "+courseid+" and chapters = "+ chapters;
        String listmyansres = DBUtil.DBMonitorSQL(listmyanssql, "useranswer");
        ArrayList<String> res = new ArrayList<>();
        if(listmyansres.startsWith("error")){
            res.add("error! 没有答案");
            return res;
        }
        res = FileUtil.getQuoCon(listmyansres);
        return res;
    }

    public static void main(String[] args) throws SQLException {
        StuOperateService stuOperateService = new StuOperateService();

        ArrayList arrayList = stuOperateService.courseDetail("17","71");

        System.out.println(arrayList);
        /*JSONObject jsonObject = new JSONObject();

        if(arrayList == null){
            jsonObject = new StuOperateController().returnarrjson(false,new JSONArray(arrayList));
            System.out.println(jsonObject);
        }else{
            System.out.println(arrayList);
        }*/
    }


}