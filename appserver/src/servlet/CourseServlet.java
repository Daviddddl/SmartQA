package servlet;

import service.dbservice.CourseService;
import service.dbservice.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet(name = "CourseServlet")
public class CourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String returnwechat = "";

        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //获取微信小程序get的参数值并打印
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Integer teacher = Integer.parseInt(request.getParameter("teacher"));
        Integer capacity = Integer.parseInt(request.getParameter("capacity"));
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");

        try {
            CourseService courseService = new CourseService();
            if(courseService.checkID(teacher)){
                courseService.addcourse(name,password,teacher,capacity,startdate,enddate);
                returnwechat = "创建课程成功！";
            }
            else
                returnwechat = "ID:"+teacher+"有误！";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //返回值给微信小程序
        Writer out = response.getWriter();
        out.write(returnwechat);
        out.flush();
    }
}
