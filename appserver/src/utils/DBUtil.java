package utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 描述:
 * 数据库连接工具类
 *
 * @outhor didonglin
 * @create 2018-01-30 11:59
 */
public class DBUtil {

    public DBUtil(){}

    public Connection openConnection(){
        Properties prop = new Properties();
        String username;
        String url;
        String driver;
        String password;
        try{
            prop.load(this.getClass().getClassLoader().getResourceAsStream("db.properties"));
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void closeConnection(Connection conn){
        try{
            conn.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }


    public boolean adduser(String nickname, String remark, Integer gender, String lang, String city, String province, String country, String avatarUrl) throws SQLException {
        DBUtil util = new DBUtil();
        Connection conn = util.openConnection();
        PreparedStatement preparedStatement;
        boolean result = false;// 创建一个结果集对象

        String sql = "insert into user(nickname, remark, gender, lang, city, province, country, avatarUrl ) " +
                "value (\""+nickname + "\","
                + "\"" + remark + "\","
                + gender +", "
                + "\"" + lang +"\","
                + "\"" + city +"\","
                + "\"" + province +"\","
                + "\"" + country +"\","
                + "\"" + avatarUrl +"\")";
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