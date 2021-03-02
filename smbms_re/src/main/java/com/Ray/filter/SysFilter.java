package com.Ray.filter;

import com.Ray.pojo.User;
import com.Ray.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        if(user==null){
            //已经被移除（注销）或者未登录
            response.sendRedirect(request.getContextPath()+"/err/getback.jsp");
        }
        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }
}
