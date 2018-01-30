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

    private String nickname;
    private String remark;

    public UserService(){}

    public UserService(String nickname, String remark) throws SQLException {
        this.nickname = nickname;
        this.remark = remark;
        new DBUtil().adduser(nickname,remark);
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
                int id = resultSet.getInt(1);
                String nickname = resultSet.getString(2);
                String remark = resultSet.getString(3);
                resultdata += "\n" + id+":"+nickname+":"+remark;
                System.out.println(id+":"+nickname+":"+remark);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }finally{
            util.closeConnection(conn);
        }
        return resultdata;
    }

    public static void main(String[] args) throws SQLException {
        new UserService("test04","test04").listuser();
    }

}