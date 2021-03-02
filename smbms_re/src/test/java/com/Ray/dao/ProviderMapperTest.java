package com.Ray.dao;

import com.Ray.dao.provider.ProviderMapper;
import com.Ray.pojo.Provider;
import com.Ray.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class ProviderMapperTest {

    @Test
    public void testGetProviderByID(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        int id = 10;
        Provider provider = mapper.getProviderByID(id);
        System.out.println(provider);
        sqlSession.close();
    }

    @Test
    public void testGetProviderList(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
//        String proName = null;
//        String proCode = null;
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("proName", proName);
//        hashMap.put("proCode", proCode);
//        List<Provider> providerList = mapper.getProviderList(hashMap);
        List<Provider> providerList = mapper.getProviderList();
        for (Provider pro :
                providerList) {
            System.out.println(pro);
        }
        sqlSession.close();
    }

    @Test
    public void testGetProviderListMap(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        String proName = "%%";
        String proCode = "%GZ%";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("proName", proName);
        hashMap.put("proCode", proCode);
        List<Provider> providerList = mapper.getProviderListMap(hashMap);
        for (Provider pro :
                providerList) {
            System.out.println(pro);
        }
        sqlSession.close();
    }

}
