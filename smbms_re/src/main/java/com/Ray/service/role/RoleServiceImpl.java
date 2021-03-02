package com.Ray.service.role;

import com.Ray.dao.BaseDao;
import com.Ray.dao.role.RoleDao;
import com.Ray.dao.role.RoleDaoImpl;
import com.Ray.pojo.Role;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    public RoleServiceImpl(){
        roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() {
        Connection connection = null;
        List<Role> roleList = null;
        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return roleList;
    }

//    @Test
//    public void TestRoleList(){
//        RoleServiceImpl roleService = new RoleServiceImpl();
//        List<Role> roleList = roleService.getRoleList();
//        //System.out.println(roleList);
//        for(Role role : roleList){
//            System.out.println(role.getRoleName());
//        }
//    }

}
