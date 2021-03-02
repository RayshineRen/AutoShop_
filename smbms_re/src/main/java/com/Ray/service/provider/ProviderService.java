package com.Ray.service.provider;

import com.Ray.pojo.Provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProviderService {
    public List<Provider> getProviderList();

    public List<Provider> getProviderListMap(Map<String, String> map);

    public Provider getProviderByID(int id);
}
