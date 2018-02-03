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

        String returnwechat = "";

        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //获取微信小程序get的参数值并打印
        String funcID = request.getParameter("funcID");

        switch (funcID){
            case "createcourse":{
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
            case "joincourse":{
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                Integer stuid = Integer.parseInt(request.getParameter("stuid"));
                CourseService courseService = new CourseService();
                try {
                    if(courseService.checkID(stuid)){
                        if(courseService.joincourse(name,password,stuid))
                            returnwechat = "加入课程成功！";
                        else
                            returnwechat = "加入课程失败！" + "课程名称或密码有误！";
                    }else
                        returnwechat = "userID:"+stuid+"有误！";
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "listallcourse":{
                Integer userid = Integer.parseInt(request.getParameter("userid"));
                String nickname = request.getParameter("nickname");
                String remark = request.getParameter("remark");
                CourseService courseService = new CourseService();
                try {
                    String listallcourseres = courseService.listallcourse(userid,nickname,remark);
                    if(!listallcourseres.equals(""))
                        returnwechat = listallcourseres;
                    else
                        returnwechat = "没有查询到课程列表！";
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "listmyowncourse":{
                Integer userid = Integer.parseInt(request.getParameter("userid"));
                CourseService courseService = new CourseService();
                try {
                    String listmyowncourseres = courseService.listmyowncourse(userid);
                    if(!listmyowncourseres.equals(""))
                        returnwechat = listmyowncourseres;
                    else
                        returnwechat = "没有查询到您创建的课程！";
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "listactivecourse":{
                Integer userid = Integer.parseInt(request.getParameter("userid"));
                String nickname = request.getParameter("nickname");
                String remark = request.getParameter("remark");
                CourseService courseService = new CourseService();
                try {
                    String listactivecourseres = courseService.listactivecourse(userid,nickname,remark);
                    if(!listactivecourseres.equals(""))
                        returnwechat = listactivecourseres;
                    else
                        returnwechat = "没有查询到正在进行的课程！";
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "searchcourse":{
                String courseid = request.getParameter("courseid");
                String coursename = request.getParameter("coursename");
                String teacherid = request.getParameter("teacherid");
                CourseService courseService = new CourseService();
                try {
                    if(!courseid.equals("")){
                        String seachcoursebyidres = courseService.searchcoursebyid(courseid);
                        if(!seachcoursebyidres.equals(""))
                            returnwechat = seachcoursebyidres;
                        else
                            returnwechat = "没有查询该ID的课程信息！";
                    }
                    if(!coursename.equals("")){
                        String searchcoursebynameres = courseService.searchcoursebyname(coursename);
                        if(!searchcoursebynameres.equals(""))
                            returnwechat = searchcoursebynameres;
                        else
                            returnwechat = "没有查询到该课程名的课程信息！";
                    }
                    if(!teacherid.equals("")){
                        String searchcoursebyteacherres = courseService.searchcoursebyteacher(teacherid);
                        if(!searchcoursebyteacherres.equals(""))
                            returnwechat = searchcoursebyteacherres;
                        else
                            returnwechat = "没有查询到该老师开设的课程信息！";
                    }

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
