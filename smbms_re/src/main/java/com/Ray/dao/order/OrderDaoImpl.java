package com.Ray.dao.order;

import com.Ray.dao.BaseDao;
import com.Ray.pojo.Goods;
import com.Ray.pojo.Order;
import com.mysql.cj.util.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> getOrderList(Connection connection, String name, int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<Order>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_order b where b.id>0");
            List<Object> list = new ArrayList<>();
            if(!StringUtils.isNullOrEmpty(name)){
                sql.append(" and productName like ?");
                list.add("%"+name+"%");
            }
            sql.append(" order by b.creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo - 1)*pageSize; //否则只能翻下一个值
            list.add(currentPageNo);
            list.add(pageSize);
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while (rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderCode(rs.getString("orderCode"));
                order.setProductName(rs.getString("productName"));
                order.setProductDesc(rs.getString("productDesc"));
                order.setTotalPrice(rs.getBigDecimal("totalPrice"));
                order.setCreationDate(rs.getTimestamp("creationDate"));
                order.setUserCode(rs.getString("userCode"));
                orderList.add(order);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return orderList;
    }

    @Override
    public int getTotalCount(Connection connection, String name) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_order where id>0");
            ArrayList<Object> list = new ArrayList<>();

            if(!StringUtils.isNullOrEmpty(name)){
                sql.append(" and productName like ?");
                list.add("%"+name+"%");
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
    public Order getOrderByID(Connection connection, Integer id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Order order = null;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_order b where b.id=?");
            List<Object> list = new ArrayList<>();
            list.add(id);
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while (rs.next()){
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderCode(rs.getString("orderCode"));
                order.setProductName(rs.getString("productName"));
                order.setProductDesc(rs.getString("productDesc"));
                order.setTotalPrice(rs.getBigDecimal("totalPrice"));
                order.setCreationDate(rs.getTimestamp("creationDate"));
                order.setUserCode(rs.getString("userCode"));
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return order;
    }

    @Override
    public int addOrder(Connection connection, Order order) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null){
            String sql = "insert into smbms_order (orderCode,productName,productDesc," +
                    "totalPrice,creationDate,userCode) " +
                    "values(?,?,?,?,?,?)";
            Object[] params = {order.getOrderCode(), order.getProductName(),
                    order.getProductDesc(), order.getTotalPrice(),
                    order.getCreationDate(), order.getUserCode()};
            flag = BaseDao.execute(connection, sql, params, pstm);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    @Override
    public List<Integer> getClassNum(Connection connection) throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Object> param = new ArrayList<>();
        Object[] params = param.toArray();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as num from smbms_order o,auto_goods b\n" +
                    "where o.productName=b.name\n" +
                    "group by classCode");
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while (rs.next()){
                int num = rs.getInt("num");
                list.add(num);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return list;
    }

    @Override
    public List<ArrayList> getDayOrder(Connection connection, Integer dayNum) throws Exception {
        List<String> theseDays = new ArrayList<>();
        for (int i = dayNum-1; i > -1; i--) {
            Date date=new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(calendar.DATE, -i);
            date = calendar.getTime();
            SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(date);
            theseDays.add(dateString);
        }
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ArrayList> dayOrderList = new ArrayList<>();
        for (int i = 0; i < theseDays.size(); i++) {
            ArrayList<Integer> dayOrder = new ArrayList<>();
            for (int j = 1; j < 4; j++) {
                if(connection != null){
                    StringBuffer sql = new StringBuffer();
                    sql.append("select count(1) as num from auto_goods g, smbms_order o\n" +
                            "where g.name=o.productName");
                    List<Object> list = new ArrayList<>();
                    sql.append(" and o.creationDate like ?");
                    list.add(theseDays.get(i)+"%");
                    sql.append(" and g.classCode=?");
                    list.add(j);
                    Object[] params = list.toArray();
                    rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
                    while (rs.next()){
                        Integer num = rs.getInt("num");
                        dayOrder.add(num);
                    }
                }
            }
            dayOrderList.add(dayOrder);
        }
        BaseDao.closeResource(null, pstm, rs);
        return dayOrderList;
    }

    @Override
    public Map<String, Integer> getHotWords(Connection connection, int currentPageNo, int pageSize) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Map<String, Integer> map = new LinkedHashMap<>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as num, word from auto_search s group by word");
            List<Object> list = new ArrayList<>();
            sql.append(" order by num DESC limit ?,?");
            currentPageNo = (currentPageNo - 1)*pageSize; //否则只能翻下一个值
            list.add(currentPageNo);
            list.add(pageSize);
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while (rs.next()){
                map.put(rs.getString("word"), rs.getInt("num"));
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return map;
    }

    @Override
    public int getSearchNum(Connection connection) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int num = 0;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select distinct word from auto_search where word\n" +
                    "in (select word from auto_search s group by word);");
            ArrayList<Object> list = new ArrayList<>();
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while(rs.next()){
                num += 1;
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return num;
    }

    //    @Test
//    public void testGetSearchNum(){
//        Connection connection = BaseDao.getConnection();
//        int searchNum = 0;
//        try {
//            searchNum = getSearchNum(connection);
//            System.out.println(searchNum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//    }

    //    @Test
//    public void testGetHotWords(){
//        Connection connection = BaseDao.getConnection();
//        Map<String, Integer> hotWords = null;
//        try {
//            hotWords = getHotWords(connection, 1, 5);
//            for (Map.Entry<String, Integer> entry:
//                    hotWords.entrySet()){
//                System.out.println(entry.getKey()+" "+entry.getValue());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//    }

    //    @Test
//    public void testGetDayOrder(){
//        Connection connection = BaseDao.getConnection();
//        try {
//            List<ArrayList> dayOrder = getDayOrder(connection, 5);
//            for (ArrayList list :
//                    dayOrder) {
//                for (Object num :
//                        list) {
//                    System.out.println(num);
//                }
//                System.out.println("123");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//    }

    //    @Test
//    public void testGetClassCount(){
//        Connection connection = BaseDao.getConnection();
//        if(connection != null){
//            List<Integer> num = null;
//            try {
//                num = getClassNum(connection);
//                for (Integer n :
//                        num) {
//                    System.out.println(n);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    @Test
//    public void testGetOrderList(){
//        Connection connection = null;
//        try {
//            connection = BaseDao.getConnection();
//            String name = null;
//            int currentPageNo = 1;
//            int pageSize = 5;
//            List<Order> orderList = getOrderList(connection, name, currentPageNo, pageSize);
//            for (Order order:
//                    orderList
//                 ) {
//                System.out.println(order);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//    }
//
//    @Test
//    public void testGetTotalCount(){
//        try {
//            Connection connection = BaseDao.getConnection();
//            int count = getTotalCount(connection, "泉");
//            System.out.println(count);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testGetOrderByID(){
//        try {
//            Connection connection = BaseDao.getConnection();
//            Order order = getOrderByID(connection, 2);
//            System.out.println(order);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
