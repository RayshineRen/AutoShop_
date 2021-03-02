package com.Ray.service.search;

import com.Ray.dao.BaseDao;
import com.Ray.dao.search.SearchDao;
import com.Ray.dao.search.SearchDaoImpl;
import com.Ray.pojo.Goods;
import com.Ray.pojo.Search;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl implements SearchService{
    private SearchDao searchDao;
    public SearchServiceImpl(){
        searchDao = new SearchDaoImpl();
    }
    @Override
    public int addSearch(Search search) {
        int flag=0;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            if(searchDao.addSearch(connection, search)>0){
                flag = 1;
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public List<Goods> getColdStart() {
        Connection connection = BaseDao.getConnection();
        List<Goods> goodsList = new ArrayList<>();
        try {
            goodsList = searchDao.getColdStart(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return goodsList;
    }

    @Override
    public int getOrderCount(String userCode) {
        Connection connection = BaseDao.getConnection();
        int orderCount = 0;
        try {
            orderCount = searchDao.getOrderCount(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return orderCount;
    }

    @Override
    public List<Goods> getTopFourGoods(String userCode) {
        Connection connection = BaseDao.getConnection();
        List<Goods> topFourGoods = new ArrayList<>();
        try {
            topFourGoods = searchDao.getTopFourGoods(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return topFourGoods;
    }

//    @Test
//    public void testGetTopFourGoods(){
//        List<Goods> topFourGoods = getTopFourGoods("admin");
//        for (Goods goods:
//                topFourGoods) {
//            System.out.println(goods);
//        }
//    }
//
    //    @Test
//    public void testGetOrderCount(){
//        int admin = getOrderCount("admin");
//        System.out.println(admin);
//    }

    //    @Test
//    public void testGetColdStart(){
//        List<Goods> coldStart = getColdStart();
//        for (Goods goods :
//                coldStart) {
//            System.out.println(goods);
//        }
//    }
}
