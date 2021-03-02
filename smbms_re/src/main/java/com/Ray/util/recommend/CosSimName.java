package com.Ray.util.recommend;

import com.Ray.pojo.Goods;

import java.util.Arrays;

public class CosSimName {
    private Goods goods;
    private String[] descVec;
    private Double similarity;

    public CosSimName() {
    }

    public CosSimName(Goods goods, String[] descVec, Double similarity) {
        this.goods = goods;
        this.descVec = descVec;
        this.similarity = similarity;
    }

    public String[] getDescVec() {
        return descVec;
    }

    public void setDescVec(String[] descVec) {
        this.descVec = descVec;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    @Override
    public String toString() {
        return "CosSimName{" +
                "goods=" + goods +
                ", descVec=" + Arrays.toString(descVec) +
                ", similarity=" + similarity +
                '}';
    }
}
