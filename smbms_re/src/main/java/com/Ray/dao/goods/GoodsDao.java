package com.Ray.dao.goods;

import com.Ray.pojo.Goods;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public interface GoodsDao {
    public List<Goods> getGoodsList(Connection connection, String name, int classCode, int currentPageNo, int pageSize) throws SQLException;

    public int getGoodsCount(Connection connection, String name, int classCode) throws SQLException;

    public Goods getGoodsByID(Connection connection, Integer goodsid) throws SQLException;

    public int addLike(Connection connection, Integer likeit, Integer goodsid) throws SQLException;

    public String getGoodsDescByName(Connection connection, String name) throws Exception;

    public Vector<String> getGoodsDesc(Connection connection) throws Exception;

    public Goods getGoodsByDesc(Connection connection, String[] desc) throws Exception;
}
