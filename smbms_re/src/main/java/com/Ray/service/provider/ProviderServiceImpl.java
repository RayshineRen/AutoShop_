package com.Ray.service.provider;

import com.Ray.dao.provider.ProviderMapper;
import com.Ray.pojo.Provider;
import com.Ray.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ProviderServiceImpl implements ProviderService{
    @Override
    public List<Provider> getProviderList() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        List<Provider> providerList = null;
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        providerList = mapper.getProviderList();
        sqlSession.close();
        return providerList;
    }

    @Override
    public List<Provider> getProviderListMap(Map<String, String>map) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        List<Provider> providerList = null;
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        providerList = mapper.getProviderListMap(map);
        sqlSession.close();
        return providerList;
    }

    @Override
    public Provider getProviderByID(int id) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        Provider provider = mapper.getProviderByID((id));
        sqlSession.close();
        return provider;
    }

    @Test
    public void testGetProviderList() {
        List<Provider> providerList = getProviderList();
        for (Provider pro :
                providerList) {
            System.out.println(pro);
        }
    }
}
