package service.dbservice;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
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
        new DBUtil().adduser(nickname,remark,gender,lang,city,province,country,avatarUrl);
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
}