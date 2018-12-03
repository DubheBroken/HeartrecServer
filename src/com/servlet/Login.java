package com.servlet;

import com.dbtools.DBUtil;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登录
 */
public class Login extends HttpServlet {

    private static final String TABLE_NAME = "user_info";

    /**
     * Constructor of the object.
     */
    public Login() {
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

        response.setContentType("text/html");
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
        String id = request.getParameter("phone"); //ID
        String password = request.getParameter("password");//密码
        String type = "0";//返回结果码（1表示成功）
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            try {
                String sql = String.format("select * from %s where id=%s;", TABLE_NAME, id);
                rs = stmt.executeQuery(sql);
                rs.next();
//					String user_device_id_db = rs.getString("user_device_id");
//					int user_state_db = rs.getInt("user_state");
//					long user_last_login_time_db = rs.getLong("user_last_login_time");
                String user_password_db = rs.getString("password");
//					System.out.println(user_device_id_db+","+Integer.toString(user_state_db)+","+Long.toString(user_last_login_time_db)+","+user_password_db);
//					if(device_id.equals(user_device_id_db)&&user_state_db==1){
//						if((now_time-user_last_login_time_db)<604800000&&user_last_login_time_db!=0)
//						{
//							type = 1;
//						}
//						else{
//							type =5;//长时间未登录，重新登录
                //						String sqlString = "update "+TABLE_NAME+" set user_state=0 where user_id="+id+";";
                //		        		stmt.executeUpdate(sqlString);
//						}
//					}
//					else{
                if (!user_password_db.equals(password)) {
                    type = "-1";//密码错误
                } else {
//			        		   if(user_state_db==1&&!user_device_id_db.equals(device_id))
//			        		   {
//			        			   type=4;//错误代码4用户在异地已登录
//			        		   }
//			        		   else{
                    type = "1";//1登录成功
                    sql = String.format("select user_name from %s where id=%s;", TABLE_NAME, id);
                    rs = stmt.executeQuery(sql);
                    rs.next();
                    User user =(User) rs.getObject(0);

                    //			        		   try {
                    ////			        		   String sqlString = "update "+TABLE_NAME+" set user_state=1,user_device_id='"+device_id+"',user_last_login_time="+now_time+" where user_id="+id+";";
                    ////			        		   stmt.executeUpdate(sqlString);
                    //							} catch (Exception e) {
                    //								// TODO Auto-generated catch block
                    //								e.printStackTrace();
                    //							}

                }
//			        	   }
//					}
            } catch (SQLException e) {
                type = "-2";//用户不存在
            }

        } catch (Exception ex) {
//	        	查询数据库出错
            ex.printStackTrace();
        } finally {
            DBUtil.Close();
            out.print(type);
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
