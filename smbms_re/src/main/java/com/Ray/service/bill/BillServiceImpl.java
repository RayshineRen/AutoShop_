package com.Ray.service.bill;

import com.Ray.dao.BaseDao;
import com.Ray.dao.bill.BIllDaoImpl;
import com.Ray.dao.bill.BillDao;
import com.Ray.pojo.Bill;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BillServiceImpl implements BillService{

    private BillDao billDao;

    public BillServiceImpl(){
        billDao = new BIllDaoImpl();
    }

    @Override
    public List<Bill> getBillList(String name, int currentPageNo, int pageSize) {

        Connection connection = null;
        List<Bill> billList = null;
        try {
            connection = BaseDao.getConnection();
            billList = billDao.getBillList(connection, name, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return billList;
    }

    @Override
    public int getTotalCount(String name) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            //2021.1.21 0:46 name写成了null!!!
            count = billDao.getTotalCount(connection, name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return count;
    }

    @Override
    public Bill getBillByID(Integer id) {

        Connection connection = null;
        Bill bill = null;
        try {
            connection = BaseDao.getConnection();
            bill = billDao.getBillByID(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return bill;
    }

    @Override
    public Boolean addBill(Bill bill) {
        Boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            if(billDao.addBill(connection, bill) > 0){
                flag = true;
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("rollback");
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    //    @Test
//    public void testGetBillList(){
//        String name = null;
//        int currentPageNo = 4;
//        int pageSize = 5;
//        BillService billService = new BillServiceImpl();
//        List<Bill> billList = null;
//        try {
//            billList = billService.getBillList(name, currentPageNo, pageSize);
//            for (Bill bill :
//                    billList) {
//                System.out.println(bill);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

//    @Test
//    public void testGetTotalCount(){
//        try {
//            int count = getTotalCount(null);
//            System.out.println(count);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }

}
