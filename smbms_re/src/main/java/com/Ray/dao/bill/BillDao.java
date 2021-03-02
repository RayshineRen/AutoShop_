package com.Ray.dao.bill;

import com.Ray.pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BillDao {

    public List<Bill> getBillList(Connection connection, String name, int currentPageNo, int pageSize) throws SQLException;

    public int getTotalCount(Connection connection, String name) throws SQLException;

    public Bill getBillByID(Connection connection, Integer id) throws SQLException;

    public int addBill(Connection connection, Bill bill) throws Exception;

}
