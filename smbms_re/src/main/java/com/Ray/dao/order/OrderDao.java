package com.Ray.dao.order;

import com.Ray.pojo.Goods;
import com.Ray.pojo.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderDao {

    public List<Order> getOrderList(Connection connection, String name, int currentPageNo, int pageSize) throws SQLException;

    public int getTotalCount(Connection connection, String name) throws SQLException;

    public Order getOrderByID(Connection connection, Integer id) throws SQLException;

    public int addOrder(Connection connection, Order order) throws Exception;

    public List<Integer> getClassNum(Connection connection) throws Exception;

    public List<ArrayList> getDayOrder(Connection connection, Integer dayNum) throws Exception;

    public Map<String, Integer> getHotWords(Connection connection, int currentPageNo, int pageSize) throws Exception;

    public int getSearchNum(Connection connection) throws Exception;
}
