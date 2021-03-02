package com.Ray.servlet.goods;

import com.Ray.dao.BaseDao;
import com.Ray.pojo.Goods;
import com.Ray.pojo.Order;
import com.Ray.pojo.User;
import com.Ray.service.goods.GoodsService;
import com.Ray.service.goods.GoodsServiceImpl;
import com.Ray.service.order.OrderService;
import com.Ray.service.order.OrderServiceImpl;
import com.Ray.service.user.UserServiceImpl;
import com.Ray.util.Constants;
import com.Ray.util.PageSupport;
import com.mysql.cj.util.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@WebServlet("/jsp/FrontEnd/showGoods.do")
public class GoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println("method---->"+method);
        if(method!=null && method.equals("query")){
            this.query(req, resp);
        }else if(method!=null && method.equals("food")){
            this.query(req, resp);
        }else if(method!=null && method.equals("view")){
            this.viewGoodsByID(req, resp);
        }else if(method!=null && method.equals("buy")){
            this.buyGoods(req, resp);
        }else if(method!=null && method.equals("commit")){
            this.commitBuy(req, resp);
        }else if(method!=null && method.equals("like")){
            this.addLikeNum(req, resp);
        }
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String queryName = req.getParameter("queryname");
        String temp = req.getParameter("queryclassCode");
        String pageIndex = req.getParameter("pageIndex");
        int queryclassCode = 0;

        GoodsServiceImpl goodsService = new GoodsServiceImpl();
        List<Goods> goodsList = null;
        int pageSize = Constants.pageSize;
        int currentPageNo = 1;

        if(queryName == null){
            queryName = "";
        }

        if(temp != null && !temp.equals("")){
            queryclassCode = Integer.parseInt(temp);
        }

        if(pageIndex != null){
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        System.out.println("queryName servlet--------"+queryName);
        System.out.println("queryclassCode servlet--------"+queryclassCode);
        System.out.println("query pageIndex--------- > " + pageIndex);

        int totalCount = goodsService.getGoodsCount(queryName, queryclassCode);
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

        goodsList = goodsService.getGoodsList(queryName, queryclassCode, currentPageNo, pageSize);
        HttpSession session = req.getSession();
        session.setAttribute("queryclassCode", queryclassCode);
        req.setAttribute("goodsList", goodsList);

        req.setAttribute("queryName", queryName);
        req.setAttribute("queryclassCode", queryclassCode);

        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);
        if(queryclassCode==0) {
            req.getRequestDispatcher("showAllGoods.jsp").forward(req, resp);
        }else if(queryclassCode==1){
            req.getRequestDispatcher("showDrinks.jsp").forward(req, resp);
        }else if(queryclassCode==2){
            req.getRequestDispatcher("showDaily.jsp").forward(req, resp);
        }else if(queryclassCode==3){
            req.getRequestDispatcher("showFood.jsp").forward(req, resp);
        }
    }

    private void viewGoodsByID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Goods goods = getGoodsByID(req, resp);
        req.setAttribute("goods", goods);
        req.getRequestDispatcher("goodsView.jsp").forward(req, resp);
    }

    private Goods getGoodsByID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String goodsid = req.getParameter("goodsid");
        Goods goods = null;
        System.out.println(goodsid);
        if(!StringUtils.isNullOrEmpty(goodsid)){
            Integer gid = Integer.parseInt(goodsid);
            GoodsService goodsService = new GoodsServiceImpl();
            goods = goodsService.getGoodsByID(gid);
            System.out.println(goods);
        }
        return goods;
    }

    private User getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        return user;
    }

    private void buyGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Date date = new Date();
        req.setAttribute("date", date);
        User user = getUser(req, resp);
        req.setAttribute("user", user);
        Goods goods = getGoodsByID(req, resp);
        req.setAttribute("goods", goods);
        req.getRequestDispatcher("buyPage.jsp").forward(req, resp);
    }

    private void commitBuy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String userCode = req.getParameter("userCode");
        Goods goods = getGoodsByID(req, resp);
        Connection connection = BaseDao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Order order = new Order();
        if (connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select orderCode from smbms_order order by id desc limit 0,1;");
            List<Object> list = new ArrayList<Object>();
            Object[] params = list.toArray();
            try {
                rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
                if (rs.next()){
                    String orderCode = rs.getString("orderCode");
                    int index = orderCode.indexOf("E");
                    String number = orderCode.substring(index+2);
                    Integer No = Integer.parseInt(number) + 1;
                    StringBuffer newOrderCode = new StringBuffer();
                    newOrderCode.append("ORDER");
                    newOrderCode.append(No.toString());
                    order.setOrderCode(newOrderCode.toString());
                    Date date = new Date();
                    order.setCreationDate(date);
                    order.setUserCode(userCode);
                    order.setProductName(goods.getName());
                    order.setProductDesc(goods.getDescription());
                    Double price = Double.parseDouble(trimBothChars(goods.getCost(), "元"));
                    BigDecimal totalPrice = BigDecimal.valueOf(price);
                    order.setTotalPrice(totalPrice);
                    OrderService orderService = new OrderServiceImpl();
                    Boolean flag = orderService.addOrder(order);
                    if (flag){
                        req.getRequestDispatcher("success.jsp").forward(req, resp);
                    }else {
                        System.out.println("添加订单记录出错");
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        BaseDao.closeResource(connection, pstm, rs);
    }

    private void addLikeNum(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Goods goods = getGoodsByID(req, resp);
        Integer likeit = goods.getLikeit();
        Integer goodsid = goods.getId();
//        System.out.println(goods.getLikeit());
        GoodsService goodsService = new GoodsServiceImpl();
        int flag = goodsService.addLike(likeit, goodsid);
        if(flag > 0){
            resp.sendRedirect(req.getContextPath()+"/jsp/FrontEnd/showGoods.do?method=view&goodsid="+goodsid);
        }else {
            System.out.println("修改likeit失败");
        }
    }

    /**
     *去掉字符串前后的指定字符
     */
    public static String trimBothChars(String str, String splitter) {
        String regex = "^" + splitter + "*|" + splitter + "*$";
        return str.replaceAll(regex, "");
    }

}
