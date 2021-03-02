package com.Ray.dao.search;

import com.Ray.pojo.Goods;
import com.Ray.pojo.Search;
import sun.plugin2.gluegen.runtime.CPU;

import java.sql.Connection;
import java.util.List;

public interface SearchDao {
    public int addSearch(Connection connection, Search search) throws Exception;

    public List<Goods> getColdStart(Connection connection) throws Exception;

    public int getOrderCount(Connection connection, String userCode) throws Exception;

    public List<Goods> getTopFourGoods(Connection connection, String userCode) throws Exception;
}
