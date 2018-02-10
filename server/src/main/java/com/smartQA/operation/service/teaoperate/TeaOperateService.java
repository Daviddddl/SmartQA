package com.smartQA.operation.service.teaoperate;

import com.smartQA.operation.service.utils.DBUtil;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * 描述:
 * 教师操作接口的实现类
 *
 * @outhor didonglin
 * @create 2018-02-10 19:16
 */

@Service
public class TeaOperateService {

    public boolean addOutline(Integer courseid, Integer chapters, String content) throws SQLException {
        String addfindsql = "select * from course where id = " + courseid;
        String addOutline = "insert into outline(`courseid`, `chapters`, `content`, `uknown`) values("+courseid+","+chapters+","+content+",0);";
        String findres = DBUtil.DBMonitorSQL(addfindsql, "course");
        if(!findres.equals("")){
            System.out.println("course表中有该课程！");
            String addres = DBUtil.DBMonitorSQL(addOutline, "outline");
            System.out.println(addres);
            if(addres.contains("失败"))
                return false;
        }
        return true;
    }

    public void deleteOutline(String name, String chapters) {

    }

    public void findOutline(String name, String chapters) {

    }

    public void changeOutline(String name, String chapters, String content) {

    }

    public void addQues(String name, String chapters, String content) {

    }

    public void deleteQues(String name, String chapters) {

    }

    public void findQues(String name, String chapters) {

    }

    public void changeQues(String name, String chapters, String content) {

    }

    public void checkQues(String name) {

    }

    public void checksign(String name) {

    }

    public void getRandStu(String name) {

    }

    public void getStu(String name, String nickName, String remark) {

    }
}