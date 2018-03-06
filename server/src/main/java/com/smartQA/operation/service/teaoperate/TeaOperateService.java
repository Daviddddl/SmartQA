package com.smartQA.operation.service.teaoperate;

import com.smartQA.operation.service.stuoperate.StuOperateService;
import com.smartQA.operation.service.utils.DBUtil;
import com.smartQA.operation.service.utils.FileUtil;
import org.apache.commons.collections.map.AbstractReferenceMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.print.attribute.HashDocAttributeSet;
import javax.xml.ws.soap.Addressing;
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

    public ArrayList<HashMap> listMyOwnCourse(String userid) throws SQLException {

        ArrayList<HashMap> maps = new ArrayList<>();
        if(userid.startsWith("error!"))
            return null;
        String listsql = "select id,name,isactive from course where teacher = "+userid;
        String listres = DBUtil.DBMonitorSQL(listsql,"user");
        if(listres.startsWith("error!") || listres.length() <= 8)
            return null;
        ArrayList listarr = FileUtil.getQuoCon(listres);
        for(int i = 0; i < listarr.size(); i++){
            String courseid = (String)listarr.get(i);
            String coursename = (String) listarr.get(++i);
            String isactive = (String) listarr.get(++i);
            HashMap<String, String> map = new HashMap<>();
            map.put("id",courseid);
            map.put("name",coursename);
            map.put("isactive", isactive);
            maps.add(map);
            //System.out.println(courseid+"---"+coursename +"---" + isactive);
        }
        return maps;
    }


    public boolean addOutline(String name, Integer chapters, String content) throws SQLException {
        String courseid = getCourseID(name);
        if(courseid.startsWith("error!"))
            return false;
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

    public boolean deleteOutline(String name, Integer chapters) throws SQLException {
        String courseid = getCourseID(name);
        if(courseid.startsWith("error!"))
            return false;
        String defindsql = "delete from outline where courseid = "+courseid+" and chapters = " + chapters;
        String deres = DBUtil.DBMonitorSQL(defindsql, "outline");
        if(deres.startsWith("error!"))
            return false;
        else
            return true;
    }

    public String findOutline(String name, Integer chapters) throws SQLException {
        String courseid = getCourseID(name);
        if(courseid.startsWith("error!"))
            return "error!";
        String listsql = "select * from outline where courseid = "+courseid + " and chapters = "+chapters;
        //System.out.println(listsql);
        String listres = DBUtil.DBMonitorSQL(listsql,"outline");
        return listres;
    }

    public boolean changeOutline(String name, Integer chapters, String content) {

        return false;
    }

    public boolean addQues(String name, Integer chapters, String ques, String ans) throws SQLException {
        String courseid = getCourseID(name);
        if(courseid.startsWith("error!"))
            return false;
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

    public boolean deleteQues(String name, Integer chapters) throws SQLException {
        String courseid = getCourseID(name);
        if(courseid.startsWith("error!"))
            return false;
        String defindsql = "delete from question where courseid = "+courseid+" and chapters = " + chapters;
        String deres = DBUtil.DBMonitorSQL(defindsql, "outline");
        if(deres.startsWith("error!"))
            return false;
        else
            return true;
    }

    public String findQues(String name, Integer chapters) throws SQLException {
        String courseid = getCourseID(name);
        if(courseid.startsWith("error!"))
            return "error!";
        String listsql = "select * from question where courseid = "+courseid + " and chapters = "+chapters;
        //System.out.println(listsql);
        return DBUtil.DBMonitorSQL(listsql,"question");
    }

    public boolean changeQues(String name, String chapters, String content) {

        return false;
    }

    public ArrayList<String> checkQues(Integer quesid) throws SQLException {
        String getressql = "select chapters, ques, ans, ansnum, correct from question where id = " + quesid;
        String getres = DBUtil.DBMonitorSQL(getressql, "question");
        ArrayList ans = FileUtil.getQuoCon(getres);
        return ans;
    }

    public boolean quizQues(Integer[] quesid) throws SQLException {
        String quizsql = "update question set isquiz = 0";
        DBUtil.DBMonitorSQL(quizsql, "question");
        for(int i : quesid) {
            String setactivesql = "update question set isquiz = 1 where id = " + i;
            String res = DBUtil.DBMonitorSQL(setactivesql, "question");
            if (res.startsWith("error!"))
                return false;
        }
        return true;
    }

    public ArrayList<String> listQues(String courseid, String chapters) throws SQLException {
        //String courseid = getCourseID(name);
        ArrayList ans = new ArrayList();
        if(courseid.startsWith("error!")) {
            ans.add("error!没有题目！");
            return ans;
        }
        String listquessql = "select ques from question where courseid = "+courseid + " and chapters = " + chapters;
        String listquesans = DBUtil.DBMonitorSQL(listquessql, "question");
        if(listquesans.startsWith("error!")){
            ans.add("error!没有题目！");
            return ans;
        }
        ans = FileUtil.getQuoCon(listquesans);
        return ans;
    }

    public boolean checkSign(String name) {

        return false;
    }

    public String getRandStu(String name) throws SQLException {
        Random random = new Random();
        Integer rand = Math.abs(random.nextInt());
        String stulist = getStu(name);
        if(stulist.startsWith("error!"))
            return "error!";
        String[] stul = FileUtil.replaceBlank(stulist).split(";");
        /*for(int i = 0; i< stul.length;i++)
            System.out.println("---"+stul[i]);*/
        return stul[rand%stul.length];
    }

    public String getStu(String name) throws SQLException {
        String courseid = getCourseID(name);
        if(courseid.startsWith("error!"))
            return "error!";
        String listsql = "select id,nickname,remark from user where joinCourse like '"+courseid+",%';";
        String getStures = DBUtil.DBMonitorSQL(listsql,"user");

        return getStures;
    }

    public static void main(String[] args) throws SQLException {

    }
}