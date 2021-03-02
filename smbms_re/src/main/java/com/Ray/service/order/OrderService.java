package com.Ray.service.order;

import com.Ray.pojo.Goods;
import com.Ray.pojo.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface OrderService {

    public List<Order> getOrderList(String name, int currentPageNo, int pageSize);

    public int getTotalCount(String name);

    public Order getOrderByID(Integer id);

    public Boolean addOrder(Order order);

    public List<Integer> getClassNum();

    public List<ArrayList> getDayOrder(Integer dayNum);

    public Map<String, Integer> getHotWords(int currentPageNo, int pageSize);

    public int getSearchNum();
}

