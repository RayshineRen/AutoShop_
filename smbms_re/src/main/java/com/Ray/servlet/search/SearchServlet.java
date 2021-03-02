package com.Ray.servlet.search;

import com.Ray.pojo.Goods;
import com.Ray.pojo.Order;
import com.Ray.pojo.Search;
import com.Ray.pojo.User;
import com.Ray.service.goods.GoodsService;
import com.Ray.service.goods.GoodsServiceImpl;
import com.Ray.service.order.OrderService;
import com.Ray.service.order.OrderServiceImpl;
import com.Ray.service.search.SearchService;
import com.Ray.service.search.SearchServiceImpl;
import com.Ray.util.Constants;
import com.Ray.util.PageSupport;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/jsp/FrontEnd/search.do")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method != null && method.equals("search")){
            this.addSearch(req, resp);
        }else if(method!=null && method.equals("query")){
            this.query(req, resp);
        }else if(method!=null && method.equals("guessLike")){
            this.guessLike(req, resp);
        }
    }

    private void addSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Search search = new Search();
        String word = req.getParameter("word");
        search.setCreationDate(new Date());
        search.setWord(word);
        User user = (User)req.getSession().getAttribute(Constants.USER_SESSION);
        String userCode = user.getUserCode();
        search.setUserCode(userCode);
        SearchService searchService = new SearchServiceImpl();
        int i = searchService.addSearch(search);
        if (i>0){
            System.out.println("Success");
        }
        GoodsService goodsService = new GoodsServiceImpl();
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
        int totalCount = goodsService.getGoodsCount(word, 0);
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
        List<Goods> goodsList = goodsService.getGoodsList(word, 0, currentPageNo, pageSize);
        if (goodsList.size()==0){
            req.getRequestDispatcher("fail.jsp").forward(req, resp);
        }
        req.setAttribute("word", word);
        req.setAttribute("goodsList", goodsList);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);
        req.getRequestDispatcher("showSearch.jsp").forward(req, resp);
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String pageIndex = req.getParameter("pageIndex");
        String word = req.getParameter("word");
        GoodsService goodsService = new GoodsServiceImpl();
        int pageSize = Constants.pageSize;
        int currentPageNo = 1;
        if(pageIndex != null){
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        int totalCount = goodsService.getGoodsCount(word, 0);
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
        List<Goods> goodsList = goodsService.getGoodsList(word, 0, currentPageNo, pageSize);
        req.setAttribute("goodsList", goodsList);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("currentPageNo", currentPageNo);
        req.getRequestDispatcher("showSearch.jsp").forward(req, resp);
    }

    private void guessLike(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
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
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        // 冷启动
        // 推荐后，返回一个list
        // 先拿到用户
        User user = (User)req.getSession().getAttribute(Constants.USER_SESSION);
        String userCode = user.getUserCode();
        SearchService searchService = new SearchServiceImpl();
        int orderCount = searchService.getOrderCount(userCode);
        if(orderCount == 0){
            List<Goods> coldStart = searchService.getColdStart();
            req.setAttribute("goodsList", coldStart);
            pageSupport.setTotalCount(Constants.pageSize);
            int totalPageCount = pageSupport.getTotalPageCount();
            //控制首页和尾页
            if(currentPageNo < 1){
                currentPageNo = 1;
            }else if(currentPageNo > totalPageCount){
                currentPageNo = totalPageCount;
            }
            req.setAttribute("totalPageCount", totalPageCount);
            req.setAttribute("totalCount", Constants.pageSize);
            req.setAttribute("currentPageNo", currentPageNo);
            req.getRequestDispatcher("showSearch.jsp").forward(req, resp);
        }else{
            List<Goods> topFourGoods = searchService.getTopFourGoods(userCode);
            req.setAttribute("goodsList", topFourGoods);
            req.getRequestDispatcher("showSearch.jsp").forward(req, resp);
        }
    }
}
