package com.Ray.servlet.user;

import com.Ray.pojo.User;
import com.Ray.service.user.UserService;
import com.Ray.service.user.UserServiceImpl;
import com.Ray.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    //Servlet:控制层，调用业务层代码

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet-start......");
        //1.接收用户参数
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        //2.调用业务层
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);
        //3.转发视图
        if(user != null){
            //将用户的信息放在Session中，后续注销用户即可直接移除Session
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            //跳转到主页
            int userRole = user.getUserRole();
            req.getSession().setAttribute("userRole", userRole);
            if(userRole < 3) {
                resp.sendRedirect(req.getContextPath() + "/jsp/frame.jsp");
            }
            else{
                resp.sendRedirect(req.getContextPath() + "/jsp/FrontEnd/index.jsp");
            }
        }else{
            //转发回登录页面，提示用户名或密码错误
            req.setAttribute("error", "用户名或密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
