package com.Ray.util.JFreeChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BarChartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DefaultCategoryDataset Dataset = new DefaultCategoryDataset();
        Dataset.addValue(12, "number", "user");
        Dataset.addValue(6, "number", "provider");
        Dataset.addValue(36, "number", "bill");
        JFreeChart barChart = ChartFactory.createBarChart("TestSMBMS", "category", "number", Dataset);

        HttpSession session = req.getSession();
        String filename = ServletUtilities.saveChartAsPNG(barChart, 400, 250, session);
        String url = req.getContextPath() + "/servlet/DisplayChart?filename="+filename;
        req.setAttribute("url", url);
        req.setAttribute("Gname", "直方图");
        req.getRequestDispatcher("showJFC.jsp").forward(req, resp);

    }
}
