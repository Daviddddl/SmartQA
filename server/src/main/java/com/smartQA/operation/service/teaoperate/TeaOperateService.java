package com.smartQA.operation.service.teaoperate;

import com.smartQA.operation.service.utils.DBUtil;
import com.smartQA.operation.service.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 描述:
 * 教师操作接口的实现类
 *
 * @outhor didonglin
 * @create 2018-02-10 19:16
 */

@Service
public class TeaOperateService {

    public String getCourseID(String name) throws SQLException {
        String joinfindsql = "select id from course where name = \"" + name + "\"";
        String joinres = DBUtil.DBMonitorSQL(joinfindsql, "course");
        //System.out.println(joinres);
        if(joinres.startsWith("error!"))
            return joinres;
        return joinres.split("\"")[1];
    }

    public boolean addOutline(Integer courseid, Integer chapters, String content) throws SQLException {
        String addfindsql = "select * from course where id = " + courseid;
        String addOutline = "insert into outline(`courseid`, `chapters`, `content`, `uknown`) values("+courseid+","+chapters+",\""+content+"\",0);";
        //System.out.println(addOutline);
        String findres = DBUtil.DBMonitorSQL(addfindsql, "course");
        //System.out.println(findres);
        if(!findres.startsWith("error!")){
            //System.out.println("course表中有该课程！");
            String addres = DBUtil.DBMonitorSQL(addOutline, "outline");
            //System.out.println(addres);
            if(addres.startsWith("error!"))
                return false;
            return true;
        }
        return false;
    }

    public boolean deleteOutline(Integer courseid, Integer chapters) throws SQLException {
        String defindsql = "delete from outline where courseid = "+courseid+" and chapters = " + chapters;
        String deres = DBUtil.DBMonitorSQL(defindsql, "outline");
        if(deres.startsWith("error!"))
            return false;
        else
            return true;
    }

    public String findOutline(Integer courseid, Integer chapters) throws SQLException {
        String listsql = "select * from outline where courseid = "+courseid + " and chapters = "+chapters;
        //System.out.println(listsql);
        String listres = DBUtil.DBMonitorSQL(listsql,"outline");
        return listres;
    }

    public void changeOutline(String name, String chapters, String content) {

    }

    public boolean addQues(Integer courseid, Integer chapters, String ques, String ans) throws SQLException {
        String addfindsql = "select * from course where id = " + courseid;
        String addQuessql = "insert into question(`courseid`, `chapters`, `ques`, `ans`) values("+courseid+","+chapters+",\""+ques+"\",\"" + ans + "\")";
        //System.out.println(addQuessql);
        String findres = DBUtil.DBMonitorSQL(addfindsql, "course");
        if(!findres.startsWith("error!")){
            //System.out.println("course表中有该课程！");
            String addres = DBUtil.DBMonitorSQL(addQuessql, "outline");
            //System.out.println(addres);
            if(addres.startsWith("error!"))
                return false;
            return true;
        }
        return false;
    }

    public boolean deleteQues(Integer courseid, Integer chapters) throws SQLException {
        String defindsql = "delete from question where courseid = "+courseid+" and chapters = " + chapters;
        String deres = DBUtil.DBMonitorSQL(defindsql, "outline");
        if(deres.startsWith("error!"))
            return false;
        else
            return true;
    }

    public String findQues(Integer courseid, Integer chapters) throws SQLException {
        String listsql = "select * from question where courseid = "+courseid + " and chapters = "+chapters;
        //System.out.println(listsql);
        return DBUtil.DBMonitorSQL(listsql,"question");
    }

    public void changeQues(String name, String chapters, String content) {

    }

    public boolean checkQues(Integer quesid, String stuans) throws SQLException {
        String getanssql = "select ans from question where id = " + quesid;
        String getans = DBUtil.DBMonitorSQL(getanssql, "question");
        ArrayList ans = FileUtil.getQuoCon(getans);
        return ans != null && stuans.equals(ans.get(0));
    }

    public void checksign(String name) {

    }

    public String getRandStu(String name) throws SQLException {
        Random random = new Random();
        Integer rand = Math.abs(random.nextInt());
        String stulist = getStu(name);
        String[] stul = FileUtil.replaceBlank(stulist).split(";");
        /*for(int i = 0; i< stul.length;i++)
            System.out.println("---"+stul[i]);*/
        return stul[rand%stul.length];
    }

    public String getStu(String name) throws SQLException {
        String courseid = getCourseID(name);
        String listsql = "select id,nickname,remark from user where joinCourse like '%,"+courseid+",%';";
        String getStures = DBUtil.DBMonitorSQL(listsql,"user");

        return getStures;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(new TeaOperateService().checkQues(6,"123"));

    }
}