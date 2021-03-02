package com.Ray.servlet.user;

import com.Ray.dao.BaseDao;
import com.Ray.util.CSV.DB2CSV;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/importCSV")
public class importCSVServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        DB2CSV db2CSV = new DB2CSV();
        try {
            connection = BaseDao.getConnection();
            String csvPath = "D:/java/JavaProject/AutoShop/smbms_re/test.csv";
            db2CSV.readCSV(csvPath, connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        req.setAttribute("operation", "success");
        req.getRequestDispatcher("/jsp/user/user.do?method=query").forward(req, resp);
    }
}
