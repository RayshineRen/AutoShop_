package com.Ray.dao.goods;

import com.Ray.dao.BaseDao;
import com.Ray.pojo.Goods;
import com.mysql.cj.util.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GoodsDaoImpl implements GoodsDao{
    @Override
    public List<Goods> getGoodsList(Connection connection, String name, int classCode, int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Goods> goodsList = new ArrayList<Goods>();

        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from auto_goods where id > 0");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(name)){
                sql.append(" and name like ?");
                list.add("%"+name+"%");
            }
            if(classCode > 0){
                sql.append(" and classCode = ?");
                list.add(classCode);
            }
            sql.append(" order by likeit DESC limit ?,?");
            currentPageNo = (currentPageNo - 1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);
            System.out.println("sql----->"+sql.toString());

            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while(rs.next()){
                Goods _goods = new Goods();
                _goods.setId(rs.getInt("id"));
                _goods.setName(rs.getString("name"));
                _goods.setType(rs.getString("type"));
                _goods.setClassCode(rs.getInt("classCode"));
                _goods.setCost(rs.getString("cost"));
                _goods.setNumber(rs.getInt("number"));
                _goods.setDescription(rs.getString("description"));
                _goods.setImg(rs.getString("img"));
                _goods.setLikeit(rs.getInt("likeit"));
                goodsList.add(_goods);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return goodsList;
    }

    @Override
    public int getGoodsCount(Connection connection, String name, int classCode) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;

        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from auto_goods where id > 0");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(name)){
                sql.append(" and name like ?");
                list.add("%"+name+"%");
            }
            if(classCode > 0){
                sql.append(" and classCode = ?");
                list.add(classCode);
            }
            System.out.println("sql----->"+sql.toString());

            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);

            if(rs.next()){
                count = rs.getInt("count");
            }

            BaseDao.closeResource(null, pstm, rs);
        }
        System.out.println("GoodsDao--->count="+count);
        return count;
    }

    @Override
    public Goods getGoodsByID(Connection connection, Integer goodsid) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Goods _goods = null;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from auto_goods where id=?");
            List<Object> list = new ArrayList<>();
            list.add(goodsid);
            System.out.println("sql----->"+sql.toString());
            Object[] param = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), param, rs, pstm);
            while(rs.next()){
                _goods = new Goods();
                _goods.setId(rs.getInt("id"));
                _goods.setName(rs.getString("name"));
                _goods.setType(rs.getString("type"));
                _goods.setClassCode(rs.getInt("classCode"));
                _goods.setCost(rs.getString("cost"));
                _goods.setNumber(rs.getInt("number"));
                _goods.setDescription(rs.getString("description"));
                _goods.setProviderCode(rs.getString("providerCode"));
                _goods.setImg(rs.getString("img"));
                _goods.setLikeit(rs.getInt("likeit"));
            }
            BaseDao.closeResource(connection, pstm, rs);
        }
        return _goods;
    }

    @Override
    public int addLike(Connection connection, Integer likeit, Integer goodsid) throws SQLException {
        int flag = 0;
        PreparedStatement pstm = null;
        if(connection!=null){
            String sql = "update auto_goods set likeit=? where id = ?";
            Object[] params = {likeit+1, goodsid};
            flag = BaseDao.execute(connection, sql, params, pstm);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    @Override
    public String getGoodsDescByName(Connection connection, String name) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String goodsDesc = null;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from auto_goods where name=?");
            List<Object> list = new ArrayList<>();
            list.add(name);
            Object[] param = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), param, rs, pstm);
            while(rs.next()){
                goodsDesc = new String();
                goodsDesc = rs.getString("description");
            }
        }
        return goodsDesc;
    }

    @Override
    public Vector<String> getGoodsDesc(Connection connection) throws Exception {
        Vector<String> descList = new Vector<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String goodsDesc = null;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select description from auto_goods");
            List<Object> list = new ArrayList<>();
            Object[] param = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), param, rs, pstm);
            while(rs.next()){
                goodsDesc = new String();
                goodsDesc = rs.getString("description");
                descList.add(goodsDesc);
            }
        }
        return descList;
    }

    @Override
    public Goods getGoodsByDesc(Connection connection, String[] desc) throws Exception {
        StringBuffer description = new StringBuffer();
        for (String word:
                desc) {
            description.append(word);
            description.append(" ");
        }
        String goodsDesc = description.toString();
        String GoodsDesc = goodsDesc.substring(0, goodsDesc.length() - 1);
//        System.out.println(GoodsDesc);
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Goods _goods = null;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from auto_goods where description=?");
            List<Object> list = new ArrayList<>();
            list.add(GoodsDesc);
            Object[] param = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), param, rs, pstm);
            while(rs.next()){
                _goods = new Goods();
                _goods.setId(rs.getInt("id"));
                _goods.setName(rs.getString("name"));
                _goods.setType(rs.getString("type"));
                _goods.setClassCode(rs.getInt("classCode"));
                _goods.setCost(rs.getString("cost"));
                _goods.setNumber(rs.getInt("number"));
                _goods.setDescription(rs.getString("description"));
                _goods.setProviderCode(rs.getString("providerCode"));
                _goods.setImg(rs.getString("img"));
                _goods.setLikeit(rs.getInt("likeit"));
            }
        }
        return _goods;
    }

//    @Test
//    public void testGetGoodsByDesc(){
//        Connection connection = BaseDao.getConnection();
//        String[] s = new String[]{"汽水", "好喝", "贵", "量大", "甜"};
//        try {
//            Goods goodsByDesc = getGoodsByDesc(connection, s);
//            System.out.println(goodsByDesc);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//        @Test
//    public void testGetGoodsDesc(){
//        Connection connection = null;
//        try {
//            connection = BaseDao.getConnection();
//            Vector<String> desc = getGoodsDesc(connection);
//            for (String d:
//                    desc) {
//                System.out.println(d);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//    }
//
    //    @Test
//    public void testGetGoodsByName(){
//        Connection connection = null;
//        try {
//            connection = BaseDao.getConnection();
//            String name = "百事可乐";
//            String desc = getGoodsDescByName(connection, name);
//            System.out.println(desc);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //    @Test
//    public void testGetGoodsCount(){
//        Connection connection = null;
//        try {
//            connection = BaseDao.getConnection();
//            System.out.println(getGoodsCount(connection, "可乐", 0));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//    }
//
//    @Test
//    public void testGetGoodsList(){
//        Connection connection = null;
//        List<Goods> list = null;
//        try {
//            connection = BaseDao.getConnection();
//            int currentPageNo = 1;
//            int pageSize = 5;
//            list = getGoodsList(connection, null, 1, currentPageNo, pageSize);
//            for (Goods item:
//                 list) {
//                System.out.println(item);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetGoodsByID(){
//        Connection connection = null;
//        try {
//            connection = BaseDao.getConnection();
//            Integer goodsid = new Integer(3);
//            Goods good = getGoodsByID(connection, goodsid);
//            System.out.println(good);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
