package com.Ray.service.order;

import com.Ray.dao.BaseDao;
import com.Ray.dao.order.OrderDaoImpl;
import com.Ray.dao.order.OrderDao;
import com.Ray.dao.order.OrderDaoImpl;
import com.Ray.pojo.Goods;
import com.Ray.pojo.Order;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService{

    private OrderDao orderDao;

    public OrderServiceImpl(){
        orderDao = new OrderDaoImpl();
    }

    @Override
    public List<Order> getOrderList(String name, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<Order> orderList = null;
        try {
            connection = BaseDao.getConnection();
            orderList = orderDao.getOrderList(connection, name, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return orderList;
    }

    @Override
    public int getTotalCount(String name) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            //2021.1.21 0:46 name写成了null!!!
            count = orderDao.getTotalCount(connection, name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }

    @Override
    public Order getOrderByID(Integer id) {
        Connection connection = null;
        Order order = null;
        try {
            connection = BaseDao.getConnection();
            order = orderDao.getOrderByID(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return order;
    }

    @Override
    public Boolean addOrder(Order order) {
        Boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            if(orderDao.addOrder(connection, order) > 0){
                flag = true;
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("rollback");
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
    public List<Integer> getClassNum() {
        List<Integer> list = new ArrayList<>();
        Connection connection = BaseDao.getConnection();
        try {
            list = orderDao.getClassNum(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return list;
    }

    @Override
    public List<ArrayList> getDayOrder(Integer dayNum) {
        Connection connection = BaseDao.getConnection();
        List<ArrayList> res = new ArrayList<>();
        try {
            List<ArrayList> dayOrderList = orderDao.getDayOrder(connection, dayNum);
            ArrayList<Integer> drinkOrder = new ArrayList<>();
            ArrayList<Integer> dailyOrder = new ArrayList<>();
            ArrayList<Integer> foodOrder = new ArrayList<>();
            for (ArrayList list :
                    dayOrderList) {
                drinkOrder.add((Integer) list.get(0));
                dailyOrder.add((Integer) list.get(1));
                foodOrder.add( (Integer) list.get(2));
            }
            res.add(drinkOrder);
            res.add(dailyOrder);
            res.add(foodOrder);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return res;
    }

    @Override
    public Map<String, Integer> getHotWords(int currentPageNo, int pageSize) {
        Connection connection = BaseDao.getConnection();
        Map<String, Integer> hotWords = null;
        try {
            hotWords = orderDao.getHotWords(connection, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotWords;
    }

    @Override
    public int getSearchNum() {
        Connection connection = BaseDao.getConnection();
        int searchNum = 0;
        try {
            searchNum = orderDao.getSearchNum(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return searchNum;
    }

//    @Test
//    public void testGetSearchNum(){
//        int searchNum = getSearchNum();
//        System.out.println(searchNum);
//    }
//
    //    @Test
//    public void testGetHotWords(){
//        Map<String, Integer> hotWords = getHotWords(1, 5);
//        for (Map.Entry<String, Integer> entry:
//                hotWords.entrySet()) {
//            System.out.println(entry.getKey()+" "+entry.getValue());
//        }
//    }

    //    @Test
//    public void testGetDayOrder(){
//        List<ArrayList> dayOrderList = getDayOrder(5);
//        for (List list :
//                dayOrderList) {
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i));
//            }
//            System.out.println("next");
//        }
//    }

    //    @Test
//    public void testGetClassNum(){
//        List<Integer> classNum = getClassNum();
//        for (Integer num :
//                classNum) {
//            System.out.println(num);
//        }
//    }
//    @Test
//    public void testGetOrderList(){
//        String name = null;
//        int currentPageNo = 1;
//        int pageSize = 2;
//        OrderService orderService = new OrderServiceImpl();
//        List<Order> orderList = null;
//        orderList = orderService.getOrderList(name, currentPageNo, pageSize);
//        for (Order order :
//                orderList) {
//            System.out.println(order);
//        }
//    }
//
//    @Test
//    public void testGetOrderByID(){
//        Integer id = 1;
//        Order order = getOrderByID(id);
//        System.out.println(order);
//    }
//
//    @Test
//    public void testGetTotalCount(){
//        int count = getTotalCount(null);
//        System.out.println(count);
//    }

}
