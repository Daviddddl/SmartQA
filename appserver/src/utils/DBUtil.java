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

    public static String DBMonitorSQL(String sql, String[] columnLables) {

        ResultSet result;// 创建一个结果集对象

        String resultdata = ""; //
        //1. 连接数据库
        DBUtil dbUtil = new DBUtil();

        Connection connection = dbUtil.openConnection();

        //2. 执行sql查询语句,得到结果后关闭连接
        try {
            PreparedStatement preparedstatement = connection.prepareStatement(sql); //实例化预编译语句

            result = preparedstatement.executeQuery();// 执行查询，注意括号中不需要再加参数

            while (result.next()){
                for(String s : columnLables)
                    resultdata += result.getString(s);
                //System.out.println("学号:" + result.getInt("db_id") + "姓名:" + result.getString("instance_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbUtil.closeConnection(connection);
        }


        //3. 返回查询结果
        return resultdata;
    }
}