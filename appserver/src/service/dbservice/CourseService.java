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

    public CourseService(String name, String password, Integer teacher, Integer capacity, String startdate, String enddate) throws SQLException {
        this.addcourse(name,password,teacher,capacity,startdate,enddate);
    }

    public boolean addcourse(String name, String password, Integer teacher, Integer capacity, String startdate, String enddate) throws SQLException {
        DBUtil util = new DBUtil();
        Connection conn = util.openConnection();
        PreparedStatement preparedStatement;
        boolean result = false;// 创建一个结果集对象

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
            if(res != 0)
                result = true;
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();//若抛出异常，则回滚，即上述try语句块无效
        }finally{
            util.closeConnection(conn);
        }
        return result;
    }

    public boolean checkID(Integer teacher) {
        String sql = "select * from user where id = "+teacher;
        String sqlres = DBUtil.DBMonitorSQL(sql,new String[]{"id"});
        if(sqlres.equals(""))
            return false;
        else
            return true;
    }
}