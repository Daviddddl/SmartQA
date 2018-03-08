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

    public boolean joinCourse(String courseid, String password, String userid) throws SQLException {

        String courseupdatesql = "UPDATE course SET stunum = stunum+1 WHERE id = \""+courseid + "\" and password = \""+ password+"\"";
        String getcoursessql = "select joinCourse from user where id = "+userid;
        if(!courseid.startsWith("error!")){
            //System.out.println("course表中有该课程！");
            String getcourses = DBUtil.DBMonitorSQL(getcoursessql,"course");
            if(getcourses.startsWith("error!"))
                return false;
            String course = (String)FileUtil.getQuoCon(getcourses).get(0);
            String[] courselist = course.split(",");
            for(String s : courselist){
                if(s.equals(courseid)){
                    System.out.println("该用户已选过该课程！");
                    return false;
                }
            }
            String courseupres = DBUtil.DBMonitorSQL(courseupdatesql, "course");
            //System.out.println(courseupres);
            String stuupdatesql = "UPDATE user SET joinCourse = CONCAT(joinCourse, \""+courseid+",\") WHERE id = "+userid;
            //System.out.println(stuupdatesql);
            if(courseupres.startsWith("error!"))
                return false;
            String stuupres = DBUtil.DBMonitorSQL(stuupdatesql,"user");
            if(stuupres.startsWith("error!"))
                return false;

        }else
            System.out.println(courseid);
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
            HashMap<String, Object> map = new HashMap<>();
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
            map.put("outlines",listallOutline(courseid));

            detailres.add(map);
        }
        return detailres;
    }

    public ArrayList<HashMap> listallOutline(String courseid) throws SQLException {
        String coursenumsql = "select chapterid from outline where courseid = " + courseid + " and chapterid is not null group by chapterid";
        //System.out.println(coursenumsql);
        String chapteridres = DBUtil.DBMonitorSQL(coursenumsql,"outline");
        ArrayList chapterid = FileUtil.getQuoCon(chapteridres);
        ArrayList<HashMap> res = new ArrayList<>();
        for(int i = 0; i<chapterid.size();i++){
            ArrayList outline = listOutline(courseid,(String)chapterid.get(i));
            for(int j = 0 ; j < outline.size(); j++)
                res.add((HashMap) outline.get(j));
        }
        return res;
    }

    public ArrayList<HashMap> listOutline(String courseid, String chapterid) throws SQLException {
        ArrayList<HashMap> outlineans = new ArrayList<>();

        String getoutlinesql = "select chapterid,title,content from outline where courseid = "+courseid + " and chapterid = "+chapterid;
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

    //学生提出问题
    public void putforwardQues(String course) {

    }

    public boolean markUnkown(String courseid, String chapterid) throws SQLException {
        if(courseid.startsWith("error!"))
            return false;
        String marksql = "update outline set uknown = uknown+1 WHERE courseid = "+courseid + " and chapterid = " + chapterid;
        String upres = DBUtil.DBMonitorSQL(marksql,"outline");
        return !upres.startsWith("error!");
    }

    public void ansQuizList(){}

    public boolean ansQuiz(Integer userid, Integer quesid, String answer) throws SQLException {

        String checkquesidsql = "select ans from question where id = "+quesid;
        String checkquesidres = DBUtil.DBMonitorSQL(checkquesidsql,"question");
        if(checkquesidres.startsWith("error")){
            return false;
        }

        String getquesdetail = "select courseid,chapterid from question where id = "+ quesid;
        String res = DBUtil.DBMonitorSQL(getquesdetail,"question");
        ArrayList quesdetail = FileUtil.getQuoCon(res);
        Integer courseid = Integer.valueOf((String)quesdetail.get(0));
        Integer chapterid = Integer.valueOf((String)quesdetail.get(1));


        //向学生答案表中插入答题结果
        String addanssql = "insert into useranswer (userid, courseid, chapterid, quesid, stuanswer) values ("+ userid+","+courseid + ","+chapterid+"," +quesid + ", \"" +answer +"\")";
        String addansres = DBUtil.DBMonitorSQL(addanssql, "useranswer");

        //统计答题人数
        String addansnumsql = "update question set ansnum = ansnum+1 where id =" +quesid;
        String addansnumres = DBUtil.DBMonitorSQL(addansnumsql, "question");

        //之后判断答题结果是否正确
        String getanssql = "select ans from question where id = " + quesid;
        String getans = DBUtil.DBMonitorSQL(getanssql, "question");
        if(!getans.startsWith("error")) {
            ArrayList ans = FileUtil.getQuoCon(getans);
            boolean ansres = ans != null && answer.equals(ans.get(0));
            if(ansres){
                String addcorrectsql = "update question set correct = correct+1 where id = " + quesid;
                String addcorrectres = DBUtil.DBMonitorSQL(addcorrectsql, "question");
                return true;
            }
            return false;
        }
        else
            return false;
    }


    public ArrayList<String> listquiz(String courseid) throws SQLException {
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

    public ArrayList<String> listMyAns(String userid, String courseid, String chapterid) throws SQLException {

        String listmyanssql = "select quesid,stuanswer from useranswer where userid = "+userid +" and courseid = "+courseid+" and chapterid = "+ chapterid;
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

        Boolean res = stuOperateService.ansQuiz(18, 3,"阿斯达");

        System.out.println(res);
        /*JSONObject jsonObject = new JSONObject();

        if(arrayList == null){
            jsonObject = new StuOperateController().returnarrjson(false,new JSONArray(arrayList));
            System.out.println(jsonObject);
        }else{
            System.out.println(arrayList);
        }*/
    }


}