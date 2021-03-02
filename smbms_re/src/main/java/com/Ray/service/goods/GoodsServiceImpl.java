package com.Ray.service.goods;

import com.Ray.dao.BaseDao;
import com.Ray.dao.goods.GoodsDao;
import com.Ray.dao.goods.GoodsDaoImpl;
import com.Ray.pojo.Goods;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class GoodsServiceImpl implements GoodsService{

    private GoodsDao goodsDao;

    public GoodsServiceImpl(){
        goodsDao = new GoodsDaoImpl();
    }

    @Override
    public List<Goods> getGoodsList(String name, int classCode, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<Goods> goodsList = null;
        try {
            connection = BaseDao.getConnection();
            goodsList = goodsDao.getGoodsList(connection, name, classCode, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return goodsList;
    }

    @Override
    public int getGoodsCount(String name, int classCode) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            count = goodsDao.getGoodsCount(connection, name, classCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }

    @Override
    public Goods getGoodsByID(Integer goodsid) {

        Connection connection = null;
        Goods goods = null;
        try {
            connection = BaseDao.getConnection();
            goods = goodsDao.getGoodsByID(connection, goodsid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null,null);
        }

        return goods;
    }

    @Override
    public int addLike(Integer likeit, Integer goodsid) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            flag = goodsDao.addLike(connection, likeit, goodsid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null,null);
        }
        return flag;
    }

    //    @Test
//    public void testGetGoodsCount(){
//        GoodsServiceImpl goodsService = new GoodsServiceImpl();
//        int goodsCount = goodsService.getGoodsCount(null, 1);
//        System.out.println(goodsCount);
//    }
//
//    @Test
//    public void testGetGoodsList(){
//        List<Goods> goodsList = null;
//        goodsList = getGoodsList(null, 0, 1, 5);
//        for (Goods goods :
//                goodsList) {
//            System.out.println(goods.getName());
//        }
//    }
//
//    @Test
//    public void testGetGoodsByID(){
//        Integer gid = 3;
//        GoodsService goodsService = new GoodsServiceImpl();
//        Goods goods = goodsService.getGoodsByID(gid);
//        System.out.println(goods);
//    }

}
