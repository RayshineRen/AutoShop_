package com.Ray.dao.provider;

import com.Ray.pojo.Provider;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface ProviderMapper {
    public List<Provider> getProviderList();

    public List<Provider> getProviderListMap(Map<String, String> map);

    public Provider getProviderByID(int id);
}
