package service.dbservice;

import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 描述:
 * 数据库中课程表的增删改查
 *
 * @outhor didonglin
 * @create 2018-01-31 21:45
 */
public class CourseService {

    public CourseService(){}

    /**
     * 教师创建课程
     * @param name
     * @param password
     * @param teacher
     * @param capacity
     * @param startdate
     * @param enddate
     * @return
     * @throws SQLException
     */
    public String addcourse(String name, String password, Integer teacher, Integer capacity, String startdate, String enddate) throws SQLException {
        DBUtil util = new DBUtil();
        Connection conn = util.openConnection();
        PreparedStatement preparedStatement;
        String result = "0";

        String sql = "insert into course(name, password, teacher, capacity, startdate, enddate ) " +
                "value (\""+name + "\","
                + "\"" + password + "\","
                + teacher +", "
                + capacity +","
                + "\"" + startdate +"\","
                + "\"" + enddate + "\")";
        System.out.println(sql);

        try {
            conn.setAutoCommit(false);//加入这个语句，表示不自动提交
            preparedStatement = conn.prepareStatement(sql);// 实例化预编译语句
            int res = preparedStatement.executeUpdate();// 执行查询，注意括号中不需要再加参数
            conn.commit(); //必须加入这句，才会将数据插入库中
            String courseIDsql = "select id from course where name = \"" + name + "\" and password = \"" + password + "\"";
            result = DBUtil.DBMonitorSQL(courseIDsql, "course");
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();//若抛出异常，则回滚，即上述try语句块无效
        }finally{
            util.closeConnection(conn);
        }
        return result;
    }

    /**
     * 创建课程时需要检验教师ID是否在user中
     * @param teacher
     * @return
     */
    public boolean checkID(Integer teacher) throws SQLException {
        String sql = "select * from user where id = "+teacher;
        String sqlres = DBUtil.DBMonitorSQL(sql, "user");
        if(sqlres.equals(""))
            return false;
        else
            return true;
    }

    public boolean joincourse(String name, String password, Integer stuID) throws SQLException {
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
}