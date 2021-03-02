package com.Ray.service.bill;

import com.Ray.pojo.Bill;

import java.sql.SQLException;
import java.util.List;

public interface BillService {

    public List<Bill> getBillList(String name, int currentPageNo, int pageSize);

    public int getTotalCount(String name);

    public Bill getBillByID(Integer id);

    public Boolean addBill(Bill bill);

}

