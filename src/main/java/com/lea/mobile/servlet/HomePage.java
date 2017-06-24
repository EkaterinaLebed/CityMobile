package com.lea.mobile.servlet;

import com.lea.mobile.dao.api.ServiceDao;
import com.lea.mobile.dao.daoimpl.ServiceDaoImpl;
import com.lea.mobile.entity.Service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class HomePage extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        InitialContext ctx = null;
        DataSource pool = null;
        try {
            ctx = new InitialContext();
            pool = (DataSource) ctx.lookup("java:comp/env/jdbc/myDataSource");
            if (pool != null){
                throw new ServletException("Unknown DataSource 'jdbc/myDataSource'");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

//        try {
//            Connection conn = pool.getConnection();
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate("INSERT INTO Status( name ) VALUES ('my name')");
//            stmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        List<Service> serviceList = new LinkedList<>();
        for (int id=1; id<=10; id++){
            Service srv = new Service();
            srv.setId(id);
            srv.setName("Name &amp; name");
            srv.setDescription("Description and description and description and about a description");
            serviceList.add(srv);
        }


//        ServiceDao dao = new ServiceDaoImpl();
//        List<Service> serviceList = dao.selectAll();
        request.setAttribute("serviceList", serviceList);

        try {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
