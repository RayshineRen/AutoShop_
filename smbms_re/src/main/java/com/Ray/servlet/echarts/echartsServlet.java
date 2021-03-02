package com.Ray.servlet.echarts;

import com.Ray.pojo.Goods;
import com.Ray.service.goods.GoodsServiceImpl;
import com.Ray.service.order.OrderService;
import com.Ray.service.order.OrderServiceImpl;
import com.Ray.service.user.UserService;
import com.Ray.service.user.UserServiceImpl;
import com.Ray.util.Constants;
import com.Ray.util.PageSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

//@WebServlet("/jsp/echars/echarts.do")
public class echartsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method!=null && method.equals("showLikeit")){
            this.showLikeit(req, resp);
        }else if(method!=null && method.equals("showClass")){
            this.showClass(req, resp);
        }else if(method!=null && method.equals("showTimeOrder")){
            this.showTimeOrder(req, resp);
        }else if(method!=null && method.equals("showSexuality")){
            this.showSexuality(req, resp);
        }else if(method!=null && method.equals("showSearchWord")){
            this.showSearchWord(req, resp);
        }
    }

    private void showLikeit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("Jinru ShowLikeit");
        String pageIndex = req.getParameter("pageIndex");
        int queryclassCode = 0;
        GoodsServiceImpl goodsService = new GoodsServiceImpl();
        List<Goods> goodsList = null;
        int pageSize = Constants.pageSize;
        int currentPageNo = 1;
        if(pageIndex != null){
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        int totalCount = goodsService.getGoodsCount(null, queryclassCode);
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        int totalPageCount = pageSupport.getTotalPageCount();
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        goodsList = goodsService.getGoodsList(null, queryclassCode, currentPageNo, pageSize);
        req.setAttribute("goodsList", goodsList);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);
        req.getRequestDispatcher("show_Likeit.jsp").forward(req, resp);
    }

    private void showClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        OrderService orderService = new OrderServiceImpl();
        List<Integer> classNum = orderService.getClassNum();
        req.setAttribute("list", classNum);
        req.getRequestDispatcher("show_classNum.jsp").forward(req, resp);
    }

    private void showTimeOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<String> theseDays = new ArrayList<>();
        for (int i = Constants.dayNum-1; i > -1; i--) {
            Date date=new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(calendar.DATE, -i);
            date = calendar.getTime();
            SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(date);
            theseDays.add(dateString);
        }
        OrderService orderService = new OrderServiceImpl();
        List<ArrayList> dayOrderList = orderService.getDayOrder(Constants.dayNum);
        ArrayList<Integer> drinkOrder = dayOrderList.get(0);
        ArrayList<Integer> dailyOrder = dayOrderList.get(1);
        ArrayList<Integer> foodOrder = dayOrderList.get(2);
        List<Integer> totalOrder = new ArrayList<>();
        for (int i = 0; i < drinkOrder.size(); i++) {
            totalOrder.add(drinkOrder.get(i)+dailyOrder.get(i)+foodOrder.get(i));
        }
        req.setAttribute("totalOrder", totalOrder);
        req.setAttribute("drinkOrder", drinkOrder);
        req.setAttribute("dailyOrder", dailyOrder);
        req.setAttribute("foodOrder", foodOrder);
        req.setAttribute("days", theseDays);
        req.getRequestDispatcher("show_timeOrder.jsp").forward(req, resp);
    }

    private void showSexuality(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        UserService userService = new UserServiceImpl();
        List<Integer> sexuality = userService.getSexuality();
        req.setAttribute("sex", sexuality);
        req.getRequestDispatcher("show_sexuality.jsp").forward(req, resp);
    }

    private void showSearchWord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        OrderService orderService = new OrderServiceImpl();
        String pageIndex = req.getParameter("pageIndex");
        int pageSize = Constants.pageSize;
        int currentPageNo = 1;
        if(pageIndex != null){
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        int totalCount = orderService.getSearchNum();
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        int totalPageCount = pageSupport.getTotalPageCount();
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        Map<String, Integer> hotWords = orderService.getHotWords(currentPageNo, pageSize);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);
        req.setAttribute("map", hotWords);
        req.getRequestDispatcher("show_hotWords.jsp").forward(req, resp);
    }
}
