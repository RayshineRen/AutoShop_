package com.Ray.util.JFreeChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;

public class TSChartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TimeSeries timeSeries = new TimeSeries("org", "date", "sale");
        timeSeries.add(new Month(1, 2020), 123.4);
        timeSeries.add(new Month(4, 2020), 163.4);
        timeSeries.add(new Month(7, 2020), 323.4);
        timeSeries.add(new Month(11, 2020), 223.4);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(timeSeries);
        StandardChartTheme chartTheme = new StandardChartTheme("cn");
        Font font = new Font("楷体", Font.PLAIN, 10);
        chartTheme.setExtraLargeFont(font);
        chartTheme.setLargeFont(font);
        chartTheme.setRegularFont(font);
        chartTheme.setSmallFont(font);
        ChartFactory.setChartTheme(chartTheme);
        JFreeChart timeSeriesChart = ChartFactory.createTimeSeriesChart("TestSMBMS", "Date", "sale", dataset);

        HttpSession session = req.getSession();
        String filename = ServletUtilities.saveChartAsJPEG(timeSeriesChart, 400, 250, session);
        String url = req.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
        req.setAttribute("url", url);
        req.setAttribute("Gname", "时序图");
        req.getRequestDispatcher("showJFC.jsp").forward(req, resp);

    }
}
