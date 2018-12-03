package com.servlet;

import com.dbtools.DBUtil;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 注册
 */

public class Signin extends HttpServlet {
    private static final String TABLE_NAME = "user_info";

    /**
     * Constructor of the object.
     */
    public Signin() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the GET method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String phone = request.getParameter("phone"); //手机号
        String name = request.getParameter("name");//昵称
        String password = request.getParameter("password");//密码
        String result = "0";
        PrintWriter out = response.getWriter();
        try {
            Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            String sql = String.format("insert into %s values(null,'%s','%s','%s',null,null,null);", TABLE_NAME,
                    phone, name, password);
            System.out.println(sql);

            int i = stmt.executeUpdate(sql);  //执行语句
            if (i > 0) {
                result = "0";
            }
            sql = String.format("select * from %s where phone=%s;", TABLE_NAME, phone);
            rs = stmt.executeQuery(sql);
            rs.next();
            User user = (User) rs.getObject(0);
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.Close();
            out.print(result);
            out.flush();
            out.close();
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}
