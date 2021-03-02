package com.Ray.service.search;

import com.Ray.pojo.Goods;
import com.Ray.pojo.Search;

import java.util.List;

public interface SearchService {
    public int addSearch(Search search);

    public List<Goods> getColdStart();

    public int getOrderCount(String userCode);

    public List<Goods> getTopFourGoods(String userCode);
}
