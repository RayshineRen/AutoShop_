package com.Ray.servlet.user;

import com.Ray.pojo.User;
import com.Ray.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/jsp/goBack.do")
public class goBackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        if(user.getUserRole() > 2){
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath()+"/err/goBack.jsp");
        }
        else {
            req.getRequestDispatcher("/jsp/frame.jsp").forward(req, resp);
        }
    }
}
