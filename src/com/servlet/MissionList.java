package com.servlet;

import com.dbtools.DBUtil;

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

public class MissionList extends HttpServlet {
	
	private static final String TABLE_NAME = "mission_info";
	/**
	 * Constructor of the object.
	 */
	public MissionList() {
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
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
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
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
	        PrintWriter out = response.getWriter();  
	        ResultSet rs = null;
	        String type_str = request.getParameter("type");
	        boolean type = Boolean.parseBoolean(type_str);
	        String result=null;
	        if(type){
		        try  
		        {  
		            Connection con= DBUtil.getConnection();
		            Statement stmt=con.createStatement();  
				try {
					String sql="select count(*) count from "+TABLE_NAME+" where mission_state=0;";//ֻ��ѯδ���ܵ�����
					rs = stmt.executeQuery(sql);  //ִ�����
					rs.next();
					int count = rs.getInt("count");
					result = Integer.toString(count);
					sql="select * from "+TABLE_NAME+";";
					rs = stmt.executeQuery(sql);  //ִ�����
					while(rs.next()){
						result += ","+ rs.getString("mission_title");
						result += ","+ rs.getString("mission_substance");
						result += ","+ rs.getString("mission_time");
						result += ","+ rs.getString("mission_place_remark");
						result += ","+ rs.getString("mission_reward");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}  
		        	   
		        }  
		        catch(Exception ex)  
		        {  
	//	        	�������ݳ���
		            ex.printStackTrace();  
		        }  
		        finally  
		        {  
		            DBUtil.Close();
		            out.print(result);  
		            out.flush();  
		            out.close();  
		        }  
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
