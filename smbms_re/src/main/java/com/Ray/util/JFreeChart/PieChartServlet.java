package com.Ray.util.JFreeChart;

import com.Ray.dao.BaseDao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PieChartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        DefaultPieDataset dataset = new DefaultPieDataset();
//        dataset.setValue("user", 66.7);
//        dataset.setValue("provider", 15.7);
//        dataset.setValue("bill", 25.6);
//
//        JFreeChart testSMBMS = ChartFactory.createPieChart("testSMBMS", dataset);
//
//
//        //1.直接显示在网页
////        ServletOutputStream out = resp.getOutputStream();
////        resp.setContentType("image/png");
////        ChartUtils.writeChartAsPNG(out, testSMBMS, 400, 260);
////        out.close();
//
//        //2.保存在本地磁盘
////        File file = new File("./pieChart.jpg");
////        ChartUtils.saveChartAsJPEG(file, testSMBMS, 400, 250);
//
//        //3.在JSP网页显示出来
////        if(testSMBMS!=null){
////            resp.sendRedirect(req.getContextPath()+"/jsp/showJFC.jsp");
////        }
//        //在jsp页面使用img标签，将本地磁盘的图片显示即可
//
//        HttpSession session = req.getSession();
//        String filename = ServletUtilities.saveChartAsPNG(testSMBMS, 400, 250, session);
//        String url = req.getContextPath() + "/servlet/DisplayChart?filename="+filename;
//        req.setAttribute("url", url);
//        req.setAttribute("Gname", "饼图");
//        req.getRequestDispatcher("showJFC.jsp").forward(req, resp);
        Connection connection = BaseDao.getConnection();
        try {
            // 查询结构仅仅支持两列,否则报错,第一列是key,第二列为value
            JDBCPieDataset jdbcPieDataset = new JDBCPieDataset(connection, "select userName,gender from smbms_user");
            JFreeChart sexuality = ChartFactory.createPieChart("Sexuality", jdbcPieDataset);
            // 显示中文
            {
                Font titleFont = new Font("黑体", Font.BOLD, 20);
                TextTitle textTitle = sexuality.getTitle();
                textTitle.setFont(titleFont);// 为标题设置上字体

                Font plotFont = new Font("宋体", Font.PLAIN, 16);
                PiePlot plot = (PiePlot) sexuality.getPlot();
                plot.setLabelFont(plotFont); // 为饼图元素设置上字体

                Font LegendFont = new Font("楷体", Font.PLAIN, 18);
                LegendTitle legend = sexuality.getLegend(0);
                legend.setItemFont(LegendFont);// 为图例说明设置字体
            }
            HttpSession session = req.getSession();
            String filename = ServletUtilities.saveChartAsJPEG(sexuality, 400, 250, session);
            String url = req.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
            req.setAttribute("url", url);
            req.setAttribute("Gname", "饼图");
            req.getRequestDispatcher("showJFC.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
