package com.Ray.dao.search;

import com.Ray.dao.BaseDao;
import com.Ray.dao.goods.GoodsDao;
import com.Ray.dao.goods.GoodsDaoImpl;
import com.Ray.pojo.Goods;
import com.Ray.pojo.Search;
import com.Ray.util.recommend.CosSimName;
import com.Ray.util.recommend.CosineSim;
import com.mysql.cj.util.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

public class SearchDaoImpl implements SearchDao{
    @Override
    public int addSearch(Connection connection, Search search) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null){
            String sql = "insert into auto_search (userCode, word, " +
                    "creationDate)" +"values(?,?,?)";
            Object[] params = {search.getUserCode(), search.getWord(), search.getCreationDate()};
            flag = BaseDao.execute(connection, sql, params, pstm);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    @Override
    public List<Goods> getColdStart(Connection connection) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Goods> goodsList = new ArrayList<Goods>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from auto_goods where name in (\n" +
                    "    select t.productName from (select * from smbms_order\n" +
                    "    group by productName order by count(1) DESC limit 5) as t);");
            List<Object> list = new ArrayList<>();
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while (rs.next()){
                Goods _goods = new Goods();
                _goods.setId(rs.getInt("id"));
                _goods.setName(rs.getString("name"));
                _goods.setType(rs.getString("type"));
                _goods.setClassCode(rs.getInt("classCode"));
                _goods.setCost(rs.getString("cost"));
                _goods.setNumber(rs.getInt("number"));
                _goods.setDescription(rs.getString("description"));
                _goods.setImg(rs.getString("img"));
                _goods.setProviderCode(rs.getString("providerCode"));
                _goods.setLikeit(rs.getInt("likeit"));
                goodsList.add(_goods);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return goodsList;
    }

    @Override
    public int getOrderCount(Connection connection, String userCode) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_order where id>0");
            ArrayList<Object> list = new ArrayList<>();
            if(!StringUtils.isNullOrEmpty(userCode)){
                sql.append(" and userCode=?");
                list.add(userCode);
            }
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            if(rs.next()){
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    @Override
    public List<Goods> getTopFourGoods(Connection connection, String userCode) throws Exception {
        List<Goods> goodsList = null;
        //最常买的东西，基于此物品推荐四种货品（1+3）；
        //最常买的剩下三种东西，按照向量相似度值大小比较，取值最大的六种货品（3+3）
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Goods _goods = null;
        Vector<String> goodsName = new Vector<>();
        if(connection != null){
            StringBuffer sql1 = new StringBuffer();
            sql1.append("select t.productName from " +
                    "(select * from smbms_order where userCode=?\n" +
                    " group by productName " +
                    "order by count(1) DESC limit 4) as t;");
            List<Object> list1 = new ArrayList<>();
            list1.add(userCode);
            Object[] param1 = list1.toArray();
            rs = BaseDao.execute(connection, sql1.toString(), param1, rs, pstm);
            while(rs.next()) {
                goodsName.add(rs.getString("productName"));
            }
            GoodsDao goodsDao = new GoodsDaoImpl();
            String maxBoughtDesc = goodsDao.getGoodsDescByName(connection, goodsName.get(0));
            String[] maxBoughtVec = maxBoughtDesc.split(" ");
            Vector<String> allDesc = goodsDao.getGoodsDesc(connection);
            Vector<String[]> allDescVec = new Vector<>();
            for (String desc :
                    allDesc) {
                allDescVec.add(desc.split(" "));
            }
            Vector<CosSimName> allCosSimOfMaxBought = new Vector<>();
            Vector<CosSimName> cosSimNames = new Vector<>();
            for (String[] vec:
                    allDescVec) {
                CosSimName cosSimName = new CosSimName();
                cosSimName.setSimilarity(CosineSim.getCosineSim(String2Vector(maxBoughtVec), String2Vector(vec)));
                cosSimName.setDescVec(vec);
                allCosSimOfMaxBought.add(cosSimName);
//                Collections.sort(allCosSimOfMaxBought, Collections.reverseOrder());
            }
            cosSimNames = sortVec(allCosSimOfMaxBought);
            //根据descVec获取货物
            goodsList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                goodsList.add(goodsDao.getGoodsByDesc(connection, cosSimNames.get(i).getDescVec()));
            }
//            for (int i = 1; i < 4; i++) {
//                String BoughtDesc = goodsDao.getGoodsDescByName(connection, goodsName.get(i));
//                String[] BoughtVec = BoughtDesc.split(" ");
//                Vector<CosSimName> allCosSimOfBought = new Vector<>();
//                Vector<CosSimName> cosSimName = new Vector<>();
//                for (String[] vec:
//                        allDescVec) {
//                    CosSimName cosSim = new CosSimName();
//                    cosSim.setSimilarity(CosineSim.getCosineSim(String2Vector(BoughtVec), String2Vector(vec)));
//                    cosSim.setDescVec(vec);
//                    allCosSimOfBought.add(cosSim);
//                }
//                cosSimName = sortVec(allCosSimOfBought);
//                //根据descVec获取货物
//                for (int j = 0; j < 2; j++) {
//                    goodsList.add(goodsDao.getGoodsByDesc(connection, cosSimName.get(j).getDescVec()));
//                }
//            }
            BaseDao.closeResource(connection, pstm, rs);
        }
        return goodsList;
    }

    public static Vector<String> String2Vector(String[] A){
        Vector<String> VecA = new Vector<>();
        for (String a:
                A) {
            VecA.add(a);
        }
        return VecA;
    }

    public static Vector<CosSimName> sortVec(Vector<CosSimName> vec){
        int len = vec.size();
        for (int i = 0; i < len; i++) {
            int index = i;
            for (int j = i; j < len; j++) {
                if(vec.get(index).getSimilarity() < vec.get(j).getSimilarity()){
                    index = j;
                }
            }
            CosSimName tmp = vec.get(i);
            vec.set(i, vec.get(index));
            vec.set(index, tmp);
        }
        return vec;
    }

//    @Test
//    public void testGetTopFourGoods(){
//        Connection connection = BaseDao.getConnection();
//        try {
//            List<Goods> topFourGoods = getTopFourGoods(connection, "admin");
//            for (Goods goods :
//                    topFourGoods) {
//                System.out.println(goods);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//    }

    //    @Test
//    public void testGetOrderCount(){
//        Connection connection = BaseDao.getConnection();
//        int count = 0;
//        try {
//            count = getOrderCount(connection, "admin");
//            System.out.println(count);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//    }
//
    //    @Test
//    public void testGetColdStart(){
//        Connection connection = BaseDao.getConnection();
//        try {
//            List<Goods> coldStart = getColdStart(connection);
//            for (Goods good :
//                    coldStart) {
//                System.out.println(good);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testAddSearch(){
//        Connection connection = BaseDao.getConnection();
//        Search search = new Search();
//        search.setUserCode("admin");
//        search.setWord("可乐");
//        Date date = new Date();
//        search.setCreationDate(date);
////        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
////        String format = formatter.format(date);
////        System.out.println(format);
//        try {
//            int i = addSearch(connection, search);
//            System.out.println(i);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
