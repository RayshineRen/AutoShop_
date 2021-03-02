package com.Ray.servlet.order;

import com.Ray.pojo.Order;
import com.Ray.service.order.OrderService;
import com.Ray.service.order.OrderServiceImpl;
import com.Ray.util.Constants;
import com.Ray.util.PageSupport;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/jsp/order/order.do")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method != null && method.equals("query")){
            this.query(req, resp);
        } else if (method != null && method.equals("view")){
            this.viewSingleOrder(req, resp);
        } else if (method != null && method.equals("add")){
            this.addOrder(req, resp);
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderList = null;
        String queryProductName = request.getParameter("queryProductName");
        String pageIndex = request.getParameter("pageIndex");
        OrderService orderService = new OrderServiceImpl();
        int pageSize = Constants.pageSize;
        int currentPageNo = 1;
        if(pageIndex != null){
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if(StringUtils.isNullOrEmpty(queryProductName)){
            queryProductName = "";
        }
        int totalCount = orderService.getTotalCount(queryProductName);
        System.out.println(totalCount);
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        int totalPageCount = pageSupport.getTotalPageCount();
        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        orderList = orderService.getOrderList(queryProductName, currentPageNo, pageSize);
        request.setAttribute("orderList", orderList);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        request.getRequestDispatcher("orderlist.jsp").forward(request, response);
    }

    private void viewSingleOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String orderid = request.getParameter("orderid");
        if(!StringUtils.isNullOrEmpty(orderid)) {
            Integer id = Integer.parseInt(orderid);
            OrderService orderService = new OrderServiceImpl();
            Order order = orderService.getOrderByID(id);
            request.setAttribute("order", order);
            request.getRequestDispatcher("orderview.jsp").forward(request, response);
        }
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String orderCode = request.getParameter("orderCode");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String totalPrice = request.getParameter("totalPrice");
        String userCode = request.getParameter("userCode");
        Order order = new Order();
        order.setOrderCode(orderCode);
        order.setProductName(productName);
        order.setProductDesc(productDesc);
        order.setTotalPrice(new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN));
        order.setCreationDate(new Date());
        order.setUserCode(userCode);
        boolean flag = false;
        OrderService orderService = new OrderServiceImpl();
        flag = orderService.addOrder(order);
        if(flag) {
            response.sendRedirect(request.getContextPath()+"/jsp/order/order.do?method=query");;
        }else {
            request.getRequestDispatcher("orderadd.jsp").forward(request, response);
        }
    }
}
