package service.dbservice;

import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 描述:
 * 在数据库中对用户进行增删改查
 *
 * @outhor didonglin
 * @create 2018-01-30 12:04
 */
public class UserService {

    public UserService(){}

    public UserService(String nickname, String remark, Integer gender, String lang, String city, String province, String country, String avatarUrl) throws SQLException {
        this.adduser(nickname,remark,gender,lang,city,province,country,avatarUrl);
    }

    public String listuser(){
        DBUtil util = new DBUtil();
        Connection conn = util.openConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String resultdata = "";
        String listsql = "select * from user";
        try{
            preparedStatement = conn.prepareStatement(listsql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Integer id = resultSet.getInt(1);
                String nickname = resultSet.getString(2);
                String remark = resultSet.getString(3);
                String gender = resultSet.getInt(4) == 1 ? "male": "female";
                String lang = resultSet.getString(5);
                String city = resultSet.getString(6);
                String province = resultSet.getString(7);
                String country = resultSet.getString(8);
                String avatarUrl = resultSet.getString(9);
                resultdata += "\n" + id+" : "+nickname+" : "+remark + " : "+ gender + " : " + lang +" : "+city+" : "+province+" : "+country+" : "+avatarUrl;
                System.out.println(id+" : "+nickname+" : "+remark + " : "+ gender + " : " + lang +" : "+city+" : "+province+" : "+country+" : "+avatarUrl);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }finally{
            util.closeConnection(conn);
        }
        return resultdata;
    }

    public boolean adduser(String nickname, String remark, Integer gender, String lang, String city, String province, String country, String avatarUrl) throws SQLException {
        DBUtil util = new DBUtil();
        Connection conn = util.openConnection();
        PreparedStatement preparedStatement;
        boolean result = false;// 创建一个结果集对象

        String sql = "insert into user(nickname, remark, gender, lang, city, province, country, avatarUrl, joinCourse) " +
                "value (\""+nickname + "\","
                + "\"" + remark + "\","
                + gender +", "
                + "\"" + lang +"\","
                + "\"" + city +"\","
                + "\"" + province +"\","
                + "\"" + country +"\","
                + "\"" + avatarUrl + "\","
                + "\"" + "0" + "\""
                +")";
        System.out.println(sql);
        try
        {
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
}