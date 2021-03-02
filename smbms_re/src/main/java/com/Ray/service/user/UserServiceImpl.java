package com.Ray.service.user;

import com.Ray.dao.BaseDao;
import com.Ray.dao.user.UserDao;
import com.Ray.dao.user.UserDaoImpl;
import com.Ray.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;

        try {
            //System.out.println("运行到UserServiceImpl.login");
            connection = BaseDao.getConnection();
            //System.out.println("创建数据库连接");
            //通过业务层调用对应的DAO层
            user = userDao.getLoginUser(connection, userCode, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int updateRows = userDao.addUser(connection, user);
            connection.commit();
            if(updateRows>0){
                flag = true;
                System.out.println("Add success");
            }else{
                System.out.println("Add failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("Roll back-------------");
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public User selectUserByUserCode(String userCode) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getUserByUserCode(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }

    @Override
    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = null;
        System.out.println("userName ---- > " + userName);
        System.out.println("userRole ---- > " + userRole);
        System.out.println("currentPageNo ---- > " + currentPageNo);
        System.out.println("pageSize ---- > " + pageSize);
        try {
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection, userName, userRole, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        //System.out.println(userList);
        return userList;
    }

    @Override
    public User getUserById(String id) {
        User user = null;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getUserById(connection, id);
        } catch (SQLException e) {
            e.printStackTrace();
            user = null;
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }

    @Override
    public int modify(User user) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            if(userDao.modify(connection, user)>0){
                flag = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public int deleteUserById(Integer id) {
        Connection connection = null;
        int flag = 0;
        try {
            connection = BaseDao.getConnection();
            if(userDao.deleteUserById(connection, id)>0){
                flag = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public int getUserCount(String userName, int userRole) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection, userName, userRole);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }

    @Override
    public List<Integer> getSexuality() {
        Connection connection = BaseDao.getConnection();
        List<Integer> list = new ArrayList<>();
        try {
            list = userDao.getSexuality(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return list;
    }


    //    @Test
//    public void test(){
//        UserServiceImpl userService = new UserServiceImpl();
//        User user = userService.login("admin", "1213as234567");
//        System.out.println(user.getUserPassword());
//    }
//
//    @Test
//    public void testUserList(){
//        UserServiceImpl userService = new UserServiceImpl();
//        List<User> userList = userService.getUserList(null, 0, 1, 5);
//        System.out.println(userList);
//    }
//
//    @Test
//    public void testCount(){
//        UserServiceImpl userService = new UserServiceImpl();
//        int count = userService.getUserCount(null, 0);
//        System.out.println(count);
//    }

//    @Test
//    public void testById(){
//        try {
//            String id = "11";
//            UserServiceImpl userService = new UserServiceImpl();
//            User user = userService.getUserById(id);
//            System.out.println(user.getUserName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
