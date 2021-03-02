package com.Ray.util.CSV;

import com.Ray.dao.BaseDao;
import com.Ray.pojo.Order;
import com.Ray.service.order.OrderService;
import com.Ray.service.order.OrderServiceImpl;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@WebServlet("/outputCSV")
public class OutputCSV extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();
        Connection connection = BaseDao.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<String[]> res = new ArrayList<>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_order b where b.id>0");
            List<Object> list = new ArrayList<>();
            Object[] params = list.toArray();
            try {
                rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
                while (rs.next()){
                    String id = Integer.toString(rs.getInt("id"));
                    String orderCode = rs.getString("orderCode");
                    String productName = rs.getString("productName");
                    String productDesc = rs.getString("productDesc");
                    BigDecimal big = rs.getBigDecimal("totalPrice");
                    String totalPrice = Double.toString(Double.valueOf(String.valueOf(big)));
                    SimpleDateFormat sdf = new SimpleDateFormat();
                    Date creationDate = rs.getTimestamp("creationDate");
                    String creationdate = sdf.format(creationDate);
                    String usrCode =  rs.getString("userCode");
                    String[] o = new String[] {id, orderCode, productDesc, productName, totalPrice, creationdate, usrCode};
                    res.add(o);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                BaseDao.closeResource(null, pstm, rs);
            }
        }
        BaseDao.closeResource(connection, null, null);
        try {
            CSVUtil.createCSV(res, "D:\\java\\JavaProject\\AutoShop\\output.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath()+"/jsp/order/order.do?method=query");
    }
}
