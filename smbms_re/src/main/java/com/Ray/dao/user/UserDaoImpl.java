package com.Ray.dao.user;

import com.Ray.dao.BaseDao;
import com.Ray.pojo.User;
import com.mysql.cj.util.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByUserCode(Connection connection, String userCode) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        String sql = "select * from smbms_user where userCode=?";
        Object[] params = {userCode};
        if(connection != null) {
            try {
                rs = BaseDao.execute(connection, sql, params, rs, pstm);
                //System.out.println(password.equals(rs.getString("userPassword")));
                if(rs.next()){
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                }
                BaseDao.closeResource(null, pstm, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User getLoginUser(Connection connection, String userCode, String userPassword) throws SQLException{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        String sql = "select * from smbms_user where userCode=?";
        Object[] params = {userCode};
        if(connection != null) {
            try {
                rs = BaseDao.execute(connection, sql, params, rs, pstm);
                //System.out.println(password.equals(rs.getString("userPassword")));
                if(rs.next() && userPassword.equals(rs.getString("userPassword"))){
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                }
                BaseDao.closeResource(null, pstm, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws SQLException{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*, r.roleName as userRoleName from smbms_user u, smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and userName like ?");
                list.add("%"+userName+"%");
            }
            if(userRole>0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            sql.append(" order by u.phone DESC limit ?,?");
            currentPageNo = (currentPageNo - 1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            //System.out.println(list);
            Object[] params = list.toArray();
            //System.out.println(params);
            System.out.println("sql---->"+sql.toString());
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while(rs.next()){
                //System.out.println(rs);
                User _user = new User();
                _user.setId(rs.getInt("id"));
                _user.setUserCode(rs.getString("userCode"));
                _user.setUserName(rs.getString("userName"));
                _user.setGender(rs.getInt("gender"));
                _user.setBirthday(rs.getDate("birthday"));
                _user.setPhone(rs.getString("phone"));
                _user.setUserRole(rs.getInt("userRole"));
                _user.setUserRoleName(rs.getString("userRoleName"));
                _user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(_user);
            }
            //System.out.println(userList);
            BaseDao.closeResource(null, pstm, rs);
        }
        //System.out.println("UserDaoImpl---->"+userList);
        return userList;
    }

    @Override
    public int deleteUserById(Connection connection, Integer id) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if(connection!=null){
            String sql = "delete from smbms_user where id=?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, sql, params, pstm);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    @Override
    public int addUser(Connection connection, User user) throws Exception {
        PreparedStatement pstm = null;
        int updateRows = 0;
        if(connection != null){
            String sql = "insert into smbms_user (userCode,userName,userPassword," +
                    "userRole,gender,birthday,phone,address) " +
                    "values(?,?,?,?,?,?,?,?)";
            Object[] params = {user.getUserCode(),user.getUserName(),user.getUserPassword(),
                    user.getUserRole(),user.getGender(),user.getBirthday(),
                    user.getPhone(),user.getAddress()};
            updateRows = BaseDao.execute(connection, sql, params, pstm);
            BaseDao.closeResource(null, pstm, null);
        }
        return updateRows;
    }

//    @Test
//    public void Test(){
//        Connection connection = BaseDao.getConnection();
//        UserDaoImpl userDao = new UserDaoImpl();
//        try {
//            List<User> userList = userDao.getUserList(connection, null, 0, 1, 5);
//            for (User user :
//                    userList) {
//                System.out.println(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;

        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u, smbms_role r where u.userRole = r.id");
            ArrayList<Object> list = new ArrayList<>();

            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }

            if(userRole>0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            Object[] objects = list.toArray();
            System.out.println("UserDaoImpl->getUserCount:"+sql.toString());

            rs = BaseDao.execute(connection, sql.toString(), objects, rs, pstm);

            if(rs.next()){
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    @Override
    public User getUserById(Connection connection, String id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        Object[] params = new Object[1];
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*, r.roleName as userRoleName from smbms_user u, smbms_role r where u.userRole = r.id");
            if(!StringUtils.isNullOrEmpty(id)){
                params[0] = id;
                sql.append(" and u.id = ?");
            }
            //System.out.println(list);
            //System.out.println(params);
            System.out.println("sql---->"+sql.toString());
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setUserRoleName(rs.getString("userRoleName"));
            }
            //System.out.println(userList);
            BaseDao.closeResource(null, pstm, rs);
        }
        //System.out.println("UserDaoImpl---->"+userList);
        return user;
    }

    @Override
    public int modify(Connection connection, User user) throws Exception {
        int flag = 0;
        PreparedStatement pstm = null;
        if(connection!=null){
            String sql = "update smbms_user set userName=?,"+
                    "gender=?,birthday=?,phone=?,address=?,userRole=? where id = ? ";
            Object[] params = {user.getUserName(),user.getGender(),user.getBirthday(),
                    user.getPhone(),user.getAddress(),user.getUserRole(),user.getId()};
            flag = BaseDao.execute(connection, sql, params, pstm);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    @Override
    public List<Integer> getSexuality(Connection connection) throws Exception {
        List<Integer> res = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Object> list = new ArrayList<>();
        if(connection!=null){
            String sql = "select count(1) as num from smbms_user group by gender";
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql, params, rs, pstm);
            while (rs.next()){
                res.add(rs.getInt("num"));
            }
        }
        BaseDao.closeResource(null, pstm, rs);
        return res;
    }

//    @Test
//    public void testGetSexuality(){
//        Connection connection = BaseDao.getConnection();
//        List<Integer> list = null;
//        try {
//            list = getSexuality(connection);
//            for (Integer i :
//                    list) {
//                System.out.println(i);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//    }
//
    //    @Test
//    public void TestById(){
//        Connection connection = BaseDao.getConnection();
//        String id = "11";
//        UserDao userDao = new UserDaoImpl();
//        User userById = null;
//        try {
//            userById = userDao.getUserById(connection, id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//        System.out.println(userById);
//    }
}
