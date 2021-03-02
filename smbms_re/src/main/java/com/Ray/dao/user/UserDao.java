package com.Ray.dao.user;

import com.Ray.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //得到要登录的用户
    public User getLoginUser(Connection connection, String userCode, String userPassword) throws SQLException;

    //增加用户
    public int addUser(Connection connection, User user) throws Exception;

    //判断是否存在userCode
    public User getUserByUserCode(Connection connection, String userCode) throws Exception;

    //获取用户列表
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws SQLException;


    //查询用户总数
    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException;


    //获取用户
    public User getUserById(Connection connection, String id) throws SQLException;


    //修改用户信息
    public int modify(Connection connection, User user) throws Exception;


    //删除用户
    public int deleteUserById(Connection connection, Integer delId) throws Exception;


    public List<Integer> getSexuality(Connection connection) throws Exception;
}
