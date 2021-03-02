package com.Ray.dao.bill;

import com.Ray.dao.BaseDao;
import com.Ray.pojo.Bill;
import com.mysql.cj.util.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BIllDaoImpl implements BillDao {

    @Override
    public List<Bill> getBillList(Connection connection, String name, int currentPageNo, int pageSize) throws SQLException {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Bill> billList = new ArrayList<Bill>();

        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_bill b where b.id>0");
            List<Object> list = new ArrayList<>();

            if(!StringUtils.isNullOrEmpty(name)){
                sql.append(" and productName like ?");
                list.add("%"+name+"%");
            }

            sql.append(" order by b.creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo - 1)*pageSize; //否则只能翻下一个值
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while (rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setCreationDate(rs.getTimestamp("creationDate"));
                bill.setProviderId(rs.getInt("providerId"));
                billList.add(bill);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return billList;
    }

    @Override
    public int getTotalCount(Connection connection, String name) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_bill where id>0");
            ArrayList<Object> list = new ArrayList<>();

            if(!StringUtils.isNullOrEmpty(name)){
                sql.append(" and productName like ?");
                list.add("%"+name+"%");
            }
            Object[] params = list.toArray();

            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            if(rs.next()){
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return count;
    }

    @Override
    public Bill getBillByID(Connection connection, Integer id) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Bill bill = null;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_bill b where b.id=?");
            List<Object> list = new ArrayList<>();
            list.add(id);

            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while (rs.next()){
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setCreationDate(rs.getTimestamp("creationDate"));
                bill.setProviderId(rs.getInt("providerId"));
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return bill;
    }

    @Override
    public int addBill(Connection connection, Bill bill) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null){
            String sql = "insert into smbms_bill (billCode,productName,productDesc," +
                    "productCount,totalPrice,providerId,creationDate) " +
                    "values(?,?,?,?,?,?,?)";
            Object[] params = {bill.getBillCode(), bill.getProductName(),
                    bill.getProductDesc(), bill.getProductCount(),
                    bill.getTotalPrice(), bill.getProviderId(), bill.getCreationDate()};
            flag = BaseDao.execute(connection, sql, params, pstm);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    //    @Test
//    public void testGetBillList(){
//        Connection connection = null;
//        try {
//            connection = BaseDao.getConnection();
//            String name = null;
//            int currentPageNo = 1;
//            int pageSize = 5;
//            List<Bill> billList = getBillList(connection, name, currentPageNo, pageSize);
//            for (Bill bill:
//                    billList
//                 ) {
//                System.out.println(bill);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            BaseDao.closeResource(connection, null, null);
//        }
//    }

//    @Test
//    public void testGetTotalCount(){
//        try {
//            Connection connection = BaseDao.getConnection();
//            int count = getTotalCount(connection, "可乐");
//            System.out.println(count);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void testGetBillByID(){
//        try {
//            Connection connection = BaseDao.getConnection();
//            Bill bill = getBillByID(connection, 2);
//            System.out.println(bill);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
