package com.Ray.dao.role;

import com.Ray.dao.BaseDao;
import com.Ray.pojo.Role;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{
    @Override
    public List<Role> getRoleList(Connection connection) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Role> roleList = new ArrayList<Role>();

        if(connection != null){
            String sql = "select * from smbms_role";
            Object[] params = {};
            rs = BaseDao.execute(connection, sql, params, rs, pstm);
            while(rs.next()){
                Role _role = new Role();
                _role.setId(rs.getInt("id"));
                _role.setRoleCode(rs.getString("roleCode"));
                _role.setRoleName(rs.getString("roleName"));
                roleList.add(_role);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return roleList;
    }
//    @Test
//    public void Test(){
//        Connection connection = BaseDao.getConnection();
//        RoleDaoImpl roleDao = new RoleDaoImpl();
//        List<Role> roleList = null;
//        try {
//            roleList = roleDao.getRoleList(connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//
//        System.out.println(roleList);
//    }
}


