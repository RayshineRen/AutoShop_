package com.Ray.servlet.bill;

import com.Ray.pojo.Bill;
import com.Ray.service.bill.BillService;
import com.Ray.service.bill.BillServiceImpl;
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

@WebServlet("/jsp/bill/bill.do")
public class BillServlet extends HttpServlet {
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
            this.viewSingleBill(req, resp);
        } else if (method != null && method.equals("add")){
            this.addBill(req, resp);
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bill> billList = null;
        String queryProductName = request.getParameter("queryProductName");
        String pageIndex = request.getParameter("pageIndex");
        BillService billService = new BillServiceImpl();
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
        int totalCount = billService.getTotalCount(queryProductName);
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

        billList = billService.getBillList(queryProductName, currentPageNo, pageSize);
        request.setAttribute("billList", billList);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        request.getRequestDispatcher("billlist.jsp").forward(request, response);

    }

    private void viewSingleBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String billid = request.getParameter("billid");
        if(!StringUtils.isNullOrEmpty(billid)) {
            Integer id = Integer.parseInt(billid);
            BillService billService = new BillServiceImpl();
            Bill bill = billService.getBillByID(id);
            request.setAttribute("bill", bill);
            request.getRequestDispatcher("billview.jsp").forward(request, response);
        }
    }

    private void addBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String billCode = request.getParameter("billCode");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productCount = request.getParameter("productCount");
        String totalPrice = request.getParameter("totalPrice");
        String providerId = request.getParameter("providerId");

        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductCount(new BigDecimal(productCount).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setCreationDate(new Date());

        boolean flag = false;
        BillService billService = new BillServiceImpl();
        flag = billService.addBill(bill);
        if(flag) {
            response.sendRedirect(request.getContextPath()+"/jsp/bill/bill.do?method=query");;
        }else {
            request.getRequestDispatcher("billadd.jsp").forward(request, response);
        }

    }

}
