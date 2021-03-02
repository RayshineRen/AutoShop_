package com.Ray.dao;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
//    private static String driver;
//    private static String url;
//    private static String username;
//    private static String password;
    private static DataSource dataSource;

    //静态代码块，类加载的时候就初始化了
//    static {
//        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
//        Properties properties = new Properties();
//        try {
//            properties.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        driver = properties.getProperty("driver");
//        url = properties.getProperty("url");
//        username = properties.getProperty("username");
//        password = properties.getProperty("password");
//    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = new Properties();
            InputStream resourceAsStream = BaseDao.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            properties.load(resourceAsStream);
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
//        try {
//            Class.forName(driver);
//            try {
//                connection = DriverManager.getConnection(url, username, password);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        try {
            connection = dataSource.getConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet execute(Connection connection, String sql, Object[] params, ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);   // 方便在外边统一close
        for (int i = 0; i < params.length; i++) {
            //setObject占位符从1开始，数组下标从0开始
            preparedStatement.setObject(i + 1, params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static int execute(Connection connection, String sql, Object[] params, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);   // 方便在外边统一close
        for (int i = 0; i < params.length; i++) {
            //setObject占位符从1开始，数组下标从0开始
            preparedStatement.setObject(i + 1, params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }

    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        boolean flag = true;
        if(resultSet != null){
            try {
                resultSet.close();
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(preparedStatement != null){
            try {
                preparedStatement.close();
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(connection != null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
