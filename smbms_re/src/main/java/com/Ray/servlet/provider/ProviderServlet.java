package com.Ray.servlet.provider;

import com.Ray.dao.provider.ProviderMapper;
import com.Ray.pojo.Provider;
import com.Ray.service.provider.ProviderService;
import com.Ray.service.provider.ProviderServiceImpl;
import com.Ray.util.MyBatisUtils;
import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/jsp/provider/provider.do")
public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if(method != null && method.equals("query")) {
            this.query(req, resp);
        }else if(method != null && method.equals("view")) {
            this.view(req, resp);
        }

    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String queryProName = request.getParameter("queryProName");
        String queryProCode = request.getParameter("queryProCode");
        List<Provider> providerList = new ArrayList<>();
        if(!StringUtils.isNullOrEmpty(queryProName) || !StringUtils.isNullOrEmpty(queryProCode)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("proName", "%"+queryProName+"%");
            map.put("proCode", "%"+queryProCode+"%");
            ProviderService providerService = new ProviderServiceImpl();
            providerList = providerService.getProviderListMap(map);
        } else{
            ProviderService providerService = new ProviderServiceImpl();
            providerList = providerService.getProviderList();
        }
        request.setAttribute("providerList", providerList);
        request.getRequestDispatcher("providerlist.jsp").forward(request, response);
    }

    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String proid = request.getParameter("proid");
        ProviderServiceImpl providerService = new ProviderServiceImpl();
        Provider provider = providerService.getProviderByID(Integer.parseInt(proid));
        request.setAttribute("provider", provider);
        request.getRequestDispatcher("providerview.jsp").forward(request, response);
    }

}
