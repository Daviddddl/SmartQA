package com.smartQA.operation.service.stuoperate;

import com.smartQA.operation.service.utils.DBUtil;
import com.smartQA.operation.service.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public boolean joinCourse(String name, String password, String nickName, String remark) throws SQLException {

        String stuID = getUserID(nickName, remark);
        if(stuID.startsWith("error!"))
            return false;
        String courseupdatesql = "UPDATE course SET stunum = stunum+1 WHERE name = \""+name + "\" and password = \""+ password+"\"";
        String getcoursessql = "select joinCourse from user where id = "+stuID;
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

    public boolean quitCourse(String nickName, String remark, String name) throws SQLException {
        String userid = getUserID(nickName,remark);
        if(userid.startsWith("error!"))
            return false;
        String courseid = getCourseID(name);
        if(courseid.startsWith("error!"))
            return false;
        String getcoursesql = "select joinCourse from user where id = " + userid;
        String getcourseres = DBUtil.DBMonitorSQL(getcoursesql,"user");
        if(getcourseres.contains(courseid+",")){
            String upquitsql = "update user set joinCourse = replace(joinCourse, \'"+courseid+","+"\',\'\') where id = "+ userid;
            DBUtil.DBMonitorSQL(upquitsql,"user");
            String downnumsql = "update course set stunum = stunum-1 WHERE name = \""+name+"\"";
            DBUtil.DBMonitorSQL(downnumsql,"course");
            return true;
        }
        return false;
    }

    public ArrayList<String> listMyCourse(String nickName, String remark) throws SQLException {
        ArrayList<String> courselistres = new ArrayList<>();
        String userid = getUserID(nickName, remark);
        if(userid.startsWith("error!")){
            courselistres.add("error!");
            return courselistres;
        }
        String listsql = "select joinCourse from user where id = "+userid;
        String listres = DBUtil.DBMonitorSQL(listsql,"user");
        if(listres.startsWith("error!") || listres.length() <= 8){
            courselistres.add("error! 没有课程！");
            return courselistres;
        }
        String course = (String)FileUtil.getQuoCon(listres).get(0);
        course = course.trim();
        String[] courselist = course.split(",");
        for(String courseid : courselist){
            String listprosql = "select id,name,teacher from course where id = "+courseid;
            String res = DBUtil.DBMonitorSQL(listprosql,course);
            courselistres.add(res);
        }
        return courselistres;
    }

    public ArrayList<String> listOutline(String coursename, String chapters) throws SQLException {
        ArrayList<String> outlineans = new ArrayList<>();
        String couseid = getCourseID(coursename);
        if(couseid.startsWith("error!")){
            outlineans.add("error!没有提纲！");
            return outlineans;
        }
        String getoutlinesql = "select * from outline where courseid = "+couseid + " and chapters = "+chapters;
        String getoutlineres = DBUtil.DBMonitorSQL(getoutlinesql,"outline");
        if(getoutlineres.startsWith("error")){
            outlineans.add("error！没有提纲");
            return outlineans;
        }
        outlineans = FileUtil.getQuoCon(getoutlineres);
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

    public ArrayList<String> ansQuiz(String nickname, String remark, String coursename, String chapters, String[] quesid, String[] answer) throws SQLException {

        String courseid = getCourseID(coursename);
        String userid = getUserID(nickname, remark);

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

    public ArrayList<String> listMyAns(String nickname, String remark, String coursename, String chapters) throws SQLException {
        String userid = getUserID(nickname,remark);
        String courseid = getCourseID(coursename);
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
        System.out.println(new StuOperateService().joinCourse("course2","23","009","004"));
    }
}