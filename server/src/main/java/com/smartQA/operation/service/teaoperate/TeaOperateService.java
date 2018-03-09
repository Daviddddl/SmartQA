package com.smartQA.operation.service.teaoperate;

import com.smartQA.operation.service.stuoperate.StuOperateService;
import com.smartQA.operation.service.utils.DBUtil;
import com.smartQA.operation.service.utils.FileUtil;
import com.smartQA.operation.web.TeaOperateController;
import com.sun.corba.se.spi.ior.ObjectKey;
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

    public ArrayList<HashMap> listCourseDetail(Integer courseid) throws SQLException {
        String listDetailsql = "select * from course where id = "+  courseid;
        String listDetailres = DBUtil.DBMonitorSQL(listDetailsql,"course");
        ArrayList detail = FileUtil.getQuoCon(listDetailres);
        ArrayList<HashMap> maps = new ArrayList<>();

        for(int i = 0; i <detail.size(); i++){

            HashMap<String, String> map = new HashMap<>();
            map.put("id", (String)detail.get(i));
            map.put("name", (String)detail.get(++i));
            map.put("password", (String)detail.get(++i));
            map.put("teacher",(String)detail.get(++i));
            map.put("capacity",(String)detail.get(++i));
            map.put("stunum",(String)detail.get(++i));
            map.put("starttime",(String)detail.get(++i));
            map.put("endtime",(String)detail.get(++i));
            map.put("isactive",(String)detail.get(++i));

            maps.add(map);
        }
        return maps;
    }

    public ArrayList listAllOutline(Integer courseid) throws SQLException {
        ArrayList maps = new ArrayList<>();
        String listchapteridsql = "select chapterid from outline where courseid="+courseid + " and chapterid is not null group by chapterid";
        String chapteridres = DBUtil.DBMonitorSQL(listchapteridsql, "outline");
        if(chapteridres.startsWith("error!") ||chapteridres.length() <= 8)
            return null;
        ArrayList chapterid = FileUtil.getQuoCon(chapteridres);

        for(int i = 0; i< chapterid.size();i++){
             maps.add(listOutline(courseid,Integer.valueOf((String)chapterid.get(i))));
        }

        return maps;
    }


    public ArrayList<HashMap> listOutline(Integer courseid, Integer chapterid) throws SQLException {
        ArrayList<HashMap> maps = new ArrayList<>();

        String listoutlinesql = "select * from outline where courseid="+courseid + " and chapterid="+chapterid;
        String listres = DBUtil.DBMonitorSQL(listoutlinesql, "outline");
        if(listres.startsWith("error!") || listres.length() <= 8)
            return null;
        ArrayList listarr = FileUtil.getQuoCon(listres);
        for(int i = 0; i< listarr.size(); i++){
            String id = (String)listarr.get(i);
            courseid = Integer.valueOf((String) listarr.get(++i));
            chapterid = Integer.valueOf((String) listarr.get(++i));
            String title = (String)listarr.get(++i);
            String content = (String)listarr.get(++i);
            String unknown = (String)listarr.get(++i);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("courseid", courseid);
            map.put("chapterid", chapterid);
            map.put("title", title);
            map.put("content",content);
            map.put("unknown",unknown);
            maps.add(map);
        }
        return maps;
    }


    public boolean addOutline(Integer courseid, Integer chapterid, String title, String content, Integer outlineid, Integer update) throws SQLException {

        if(update == 0) {
            //新增提纲
            String addfindsql = "select * from course where id = " + courseid;
            String addOutline = "insert into outline(`courseid`, `chapterid`, `title`, `content`, `uknown`) values(" + courseid + "," + chapterid + ",\"" + title + "\",\"" + content + "\",0);";
            //System.out.println(addOutline);
            String findres = DBUtil.DBMonitorSQL(addfindsql, "course");
            //System.out.println(findres);
            if (!findres.startsWith("error!")) {
                //System.out.println("course表中有该课程！");
                String addres = DBUtil.DBMonitorSQL(addOutline, "outline");
                //System.out.println(addres);
                if (addres.startsWith("error!"))
                    return false;
                return true;
            }
            return false;
        } else{
            //更新提纲
            String updateoutlinesql = "update outline set title = "+ "\""+title + "\"," +" content = " + "\"" + content +"\" where id = "+outlineid;
            String res = DBUtil.DBMonitorSQL(updateoutlinesql, "outline");
            if(res.startsWith("更新成功"))
                return true;
            return false;
        }
    }

    public boolean deleteOutline(Integer courseid, Integer chapterid, String title) throws SQLException {

        String defindsql = "delete from outline where courseid = "+courseid+" and chapterid = " + chapterid + " and title = "+ "\""+title+"\"";
        System.out.println(defindsql);
        String deres = DBUtil.DBMonitorSQL(defindsql, "outline");
        if(deres.startsWith("error!"))
            return false;
        else
            return true;
    }

    public String findOutline(Integer courseid, Integer chapterid) throws SQLException {

        String listsql = "select * from outline where courseid = "+courseid + " and chapterid = "+chapterid;
        //System.out.println(listsql);
        String listres = DBUtil.DBMonitorSQL(listsql,"outline");
        return listres;
    }

    public boolean changeOutline(Integer courseid, Integer chapterid, String content) {

        return false;
    }

    public boolean addQues(Integer courseid, Integer chapterid, String ques, String options, String ans) throws SQLException {

        String addfindsql = "select * from course where id = " + courseid;
        String addQuessql = "insert into question(`courseid`, `chapterid`, `ques`, `options`, `ans`) values("+courseid+","+chapterid+",\""+ques+ "\"," + "\"" + options + "\"," + "\"" + ans + "\")";
        //System.out.println(addQuessql);
        String findres = DBUtil.DBMonitorSQL(addfindsql, "course");
        if(!findres.startsWith("error!")){
            //System.out.println("course表中有该课程！");
            String addres = DBUtil.DBMonitorSQL(addQuessql, "question");
            //System.out.println(addres);
            if(addres.startsWith("error!"))
                return false;
            return true;
        }
        return false;
    }

    public boolean updateQues(Integer quesid, String ques, String options, String ans) throws SQLException {
        String upsql = "update question set ques = "+ "\""+ques + "\"," +" options = " + "\"" + options + "\"," +" ans = \"" +ans+"\" where id = "+quesid;
        System.out.println(upsql);
        String res = DBUtil.DBMonitorSQL(upsql,"question");
        if(res.startsWith("更新成功"))
            return true;
        else
            return false;
    }

    public boolean deleteQues(Integer courseid, Integer chapterid) throws SQLException {
        String defindsql = "delete from question where courseid = "+courseid+" and chapterid = " + chapterid;
        String deres = DBUtil.DBMonitorSQL(defindsql, "outline");
        if(deres.startsWith("error!"))
            return false;
        else
            return true;
    }

    public boolean deleteQuesByID(Integer quesid) throws SQLException {

        String defindsql = "delete from question where id = "+quesid;
        System.out.println(defindsql);
        String deres = DBUtil.DBMonitorSQL(defindsql, "question");
        if(deres.startsWith("error!"))
            return false;
        else
            return true;
    }

    public String findQues(Integer courseid, Integer chapterid) throws SQLException {

        String listsql = "select * from question where courseid = "+courseid + " and chapterid = "+chapterid;
        //System.out.println(listsql);
        return DBUtil.DBMonitorSQL(listsql,"question");
    }

    public boolean changeQues(Integer courseid, Integer chapterid, String content) {

        return false;
    }

    public ArrayList<String> checkQues(Integer quesid) throws SQLException {
        String getressql = "select chapterid, ques, ans, ansnum, correct from question where id = " + quesid;
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

    public ArrayList<HashMap> listAllQues(Integer courseid) throws SQLException {
        String getcchapterssql = "select chapterid from question where courseid = "+courseid +" group by chapterid";
        String res = DBUtil.DBMonitorSQL(getcchapterssql, "chapterid");
        ArrayList chapters = FileUtil.getQuoCon(res);
        ArrayList<HashMap> listallques = new ArrayList<>();
        for(int i = 0 ;i <chapters.size(); i++){
            listallques.addAll(listQues(courseid, Integer.valueOf((String)chapters.get(i))));
        }
        return listallques;
    }

    public ArrayList<HashMap> optionsanalyz(String options){
        ArrayList<HashMap> map = new ArrayList<>();
        if(!options.equals("")){
            options = FileUtil.replaceBlank(options);
            String[] optionsarr = options.split("<EOF>");
            ArrayList<HashMap> optionarr = new ArrayList<>();
            for(String s : optionsarr){
                HashMap<String, String> op = new HashMap<>();
                op.put("name", s);
                op.put("value", s.substring(0, 1));
                optionarr.add(op);
            }
            return optionarr;
        }else
            return null;
    }

    public ArrayList<HashMap> listQues(Integer courseid, Integer chapterid) throws SQLException {
        //String courseid = getCourseID(name);
        ArrayList<HashMap> maps = new ArrayList();
        /*if(courseid.startsWith("error!")) {
            ans.add("error!没有题目！");
            return ans;
        }*/
        String listquessql = "select * from question where courseid = "+courseid + " and chapterid = " + chapterid;
        String listquesans = DBUtil.DBMonitorSQL(listquessql, "question");
        if(listquesans.startsWith("error!")){
            return null;
        }
        ArrayList listques = FileUtil.getQuoCon(listquesans);
        for(int i = 0; i< listques.size(); i++){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", (String) listques.get(i));
            map.put("courseid", (String)listques.get(++i));
            map.put("chapterid", (String)listques.get(++i));
            map.put("ques",(String)listques.get(++i));
            //获取选项
            String options = (String)listques.get(++i);
            map.put("options",optionsanalyz(options) == null ? "没有选项！" : optionsanalyz(options));
            map.put("ans", (String)listques.get(++i));
            map.put("ansnum", (String)listques.get(++i));
            map.put("correct", (String)listques.get(++i));
            map.put("isactive", (String)listques.get(++i));
            map.put("isquiz", (String)listques.get(++i));

            maps.add(map);
        }

        return maps;
    }

    public boolean checkSign(Integer courseid) {

        return false;
    }

    public ArrayList<HashMap> getRandStu(Integer courseid) throws SQLException {
        if(!new StuOperateService().checkCourseID(courseid))
            return null;
        Random random = new Random();
        Integer rand = Math.abs(random.nextInt());
        ArrayList stulist = getStu(courseid);
        ArrayList<HashMap> res = new ArrayList<>();
        res.add((HashMap)stulist.get(rand));
        return res;
    }

    public ArrayList<HashMap> getStu(Integer courseid) throws SQLException {
        if(!new StuOperateService().checkCourseID(courseid))
            return null;
        String listsql = "select id,nickname,remark from user where joinCourse like '%#"+courseid+",%';";
        System.out.println(listsql);
        String getStures = DBUtil.DBMonitorSQL(listsql,"user");
        ArrayList stulist = FileUtil.getQuoCon(getStures);
        ArrayList<HashMap> maps = new ArrayList<>();
        for(int i = 0 ;i <stulist.size(); i++){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",stulist.get(i));
            map.put("nickname",stulist.get(++i));
            map.put("remark",stulist.get(++i));
            maps.add(map);
        }
        return maps;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(new TeaOperateService().getStu(9));
    }

}