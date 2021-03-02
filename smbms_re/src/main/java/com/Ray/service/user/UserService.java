package com.Ray.service.user;

import com.Ray.pojo.User;

import java.util.List;

public interface UserService {
    //用户登录
    public User login(String userCode, String userPassword);

    //增加用户
    public boolean addUser(User user);

    //根据UserCode查询User
    public User selectUserByUserCode(String userCode);

    //查询用户表
    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize);


    //查询记录数
    public int getUserCount(String userName, int userRole);


    //getUserById
    public User getUserById(String id);


    //modify User
    public int modify(User user);


    //delete User
    public int deleteUserById(Integer id);

    public List<Integer> getSexuality();
}
