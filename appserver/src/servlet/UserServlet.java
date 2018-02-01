package servlet;

import service.dbservice.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String result = "";

        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //获取微信小程序get的参数值并打印
        String nickname = request.getParameter("nickname");
        String remark = request.getParameter("remark");
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        String lang = request.getParameter("lang");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String country = request.getParameter("country");
        String avatarUrl = request.getParameter("avatarUrl");
        try {
            UserService userService = new UserService();
            result = userService.adduser(nickname,remark,gender,lang,city,province,country,avatarUrl);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //返回值给微信小程序
        Writer out = response.getWriter();
        out.write(result);
        out.flush();
    }
}
