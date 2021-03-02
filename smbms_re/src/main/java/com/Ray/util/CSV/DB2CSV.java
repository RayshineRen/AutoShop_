package com.Ray.util.CSV;

import com.Ray.dao.BaseDao;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DB2CSV {
    public static void readCSV(String csvPath, Connection connection) throws IOException, SQLException {
        //处理乱码问题
        //接口问题
        //修改sql_create_table
        //修改缺失数据的处理方式
        PreparedStatement pstm = null;
        Object[] params = null;
        String table_name = "smbms_user";   //可修改接口
        ArrayList<String[]> csvList = new ArrayList<String[]>();

        int headerLength = 0;
        CsvReader reader = new CsvReader(csvPath, ',', Charset.forName("utf-8"));
        headerLength = 8;
//        if(reader.readHeaders()){
//            String[] headers = reader.getHeaders();
//            System.out.println(headers.length);
//            headerLength = headers.length;
//            params = new Object[headerLength+1];
//            String sql_create_table = null;
//            sql_create_table = "create table if not exists "+table_name+" (`id` int not null primary key,";
//            for (int i = 0; i < headerLength - 1; i++) {
//                sql_create_table += "`"+headers[i]+"`"+" VARCHAR(400),";
//            }
//            sql_create_table += "`"+headers[headerLength-1]+"`" + " VARCHAR(400));";
//            System.out.println(sql_create_table);
//            Statement statement = connection.createStatement();
//            int execute = statement.executeUpdate(sql_create_table);
//            if(execute==0){
//                System.out.println("Table has been created!");
//            }
//        }
        while (reader.readRecord()){
            csvList.add(reader.getValues());
        }
        reader.close();
        int ListSize = csvList.size();
        String sql = "insert into "+table_name+ " (userCode, userName, " +
                "userPassword, gender, birthday, phone, address, userRole)"+ " values (";
        for (int i = 0; i < headerLength-1; i++) {
            sql += "?,";
        }
        sql += "?);";
//        System.out.println(sql);
        params = new Object[headerLength];
        for (int row = 0; row < ListSize; row++) {
            String[] rowList = csvList.get(row);
//            int[] int_item = {3, 4, 7, 8};
            for (int i = 0; i < headerLength; i++) {
                if(i != 3 && i != 7) {
                    params[i] = rowList[i];
                }else if(i == 3 || i == 7) {
                    params[i] = Integer.parseInt(rowList[i]);
                }
//                }else if(i == 4){
//                    try {
//                        params[i] = new SimpleDateFormat("yyyy-MM-dd").parse(rowList[i]);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
            BaseDao.execute(connection, sql, params, pstm);
        }
        BaseDao.closeResource(connection, pstm, null);
    }

//    public static void createCSV(String fileName, Connection connection, String table) throws SQLException, IOException{
//        //接口
//        //文件重复创建
//        PreparedStatement pstm = null;
//        ResultSet rs = null;
//        String sql = null;
//        Object[] params = null;
//        connection = BaseDao.getConnection();
//        sql = "select *\n" +
//                "from "+table+"\n" +
//                "into outfile 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/"+fileName+".csv'\n" +
//                "CHARACTER SET gbk\n" +
//                "fields terminated by ','\n" +
//                "lines terminated by '\\r\\n';";
//        System.out.println(sql);
//        pstm = connection.prepareStatement(sql);
//        rs = pstm.executeQuery();
//        BaseDao.closeResource(connection, pstm, null);
//    }

    public static void main(String[] args) {
        Connection connection = null;
//        String filename = "test";
//        String table = "test";
        try {
            connection = BaseDao.getConnection();
            String csvPath = "D:/java/JavaProject/AutoShop/smbms_re/test.csv";
            readCSV(csvPath, connection);
//            createCSV(filename, connection, table);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
    }

}
