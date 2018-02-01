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
        Integer funcID = Integer.parseInt(request.getParameter("funcID"));

        switch (funcID){
            case 1:{
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                Integer teacher = Integer.parseInt(request.getParameter("teacher"));
                Integer capacity = Integer.parseInt(request.getParameter("capacity"));
                String startdate = request.getParameter("startdate");
                if(startdate.equals(""))
                    startdate = "2018-01-01";
                String enddate = request.getParameter("enddate");
                if(enddate.equals(""))
                    enddate = "2099-12-31";

                try {
                    CourseService courseService = new CourseService();
                    if(courseService.checkID(teacher)){
                        String courseid = courseService.addcourse(name,password,teacher,capacity,startdate,enddate);
                        returnwechat = "创建课程成功！课程唯一ID:"+courseid;
                        System.out.println(returnwechat);
                    }
                    else
                        returnwechat = "userID:"+teacher+"有误！";
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 2:{
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                Integer stuID = Integer.parseInt(request.getParameter("stuID"));
                CourseService courseService = new CourseService();
                try {
                    if(courseService.checkID(stuID)){
                        if(courseService.joincourse(name,password,stuID))
                            returnwechat = "加入课程成功！";
                        else
                            returnwechat = "加入课程失败！" + "课程名称或密码有误！";
                    }else
                        returnwechat = "userID:"+stuID+"有误！";
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }

        }

        //返回值给微信小程序
        Writer out = response.getWriter();
        out.write(returnwechat);
        out.flush();
    }
}
