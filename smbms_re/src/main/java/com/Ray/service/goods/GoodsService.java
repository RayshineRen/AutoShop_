package com.Ray.service.goods;

import com.Ray.pojo.Goods;

import java.util.List;

public interface GoodsService {

    //查询货物表
    public List<Goods> getGoodsList(String name, int classCode, int currentPageNo, int pageSize);

    //获取查询结果的行数
    public int getGoodsCount(String name, int classCode);

    //获取单个货物的全部信息
    public Goods getGoodsByID(Integer goodsid);

    public int addLike(Integer likeit, Integer goodsid);
}
