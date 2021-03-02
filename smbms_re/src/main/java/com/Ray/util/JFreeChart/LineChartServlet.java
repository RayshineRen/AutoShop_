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

public class LineChartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1000, "jdk", "jdk1.0");
        dataset.addValue(1320, "jdk", "jdk1.1");
        dataset.addValue(2100, "jdk", "jdk1.2");
        dataset.addValue(3000, "jdk", "jdk1.3");
        JFreeChart lineChart = ChartFactory.createLineChart("TestSMBMS", "version", "class", dataset);

        HttpSession session = req.getSession();
        String filename = ServletUtilities.saveChartAsJPEG(lineChart, 400, 250, session);
        String url = req.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
        req.setAttribute("url", url);
        req.setAttribute("Gname", "折线图");
        req.getRequestDispatcher("showJFC.jsp").forward(req, resp);

    }
}
