package com.servlet;

import com.dbtools.DBUtil;
import com.utils.TimeCompare;

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

public class Accept extends HttpServlet {
	
	private static final String TABLE_NAME = "mission_info";
	/**
	 * Constructor of the object.
	 */
	public Accept() {
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
		    String title = request.getParameter("accept_title"); //���� 
	        String substance= request.getParameter("accept_substance");//����
	        String time = request.getParameter("accept_time");//��ֹʱ��
	        String reward = request.getParameter("accept_reward");//�ͽ�
	        String deposit = request.getParameter("accept_deposit");//Ѻ��
	        String place_remark = request.getParameter("accept_place_remark");//���µص�
	        String accpter_id = request.getParameter("accpt_id");//������
	        int type = 0;//���ؽ��
	        PrintWriter out = response.getWriter();  
	        try  
	        {  
	            Connection con= DBUtil.getConnection();
	            Statement stmt=con.createStatement();  
			try {
				String sql="select * from "+TABLE_NAME+" where mission_title='"+title+"' and mission_substance='"+substance+"' and mission_time="+time+" and mission_place_remark='"+place_remark+"' and mission_reward="+reward+" and mission_deposit="+deposit+";";
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);//ִ�����
				rs.next();
				int i=rs.getInt("mission_state");
				String time_str=rs.getString("mission_time");
				String mission_id = rs.getString("mission_id");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		        Date curtime = new Date(System.currentTimeMillis());//��ȡ��ǰʱ��
		        String now_time_str = formatter.format(curtime);
				boolean timeout = TimeCompare.compareTime(now_time_str,time_str);//��ʱ
				if(timeout){
					type = 2;//������ֹʱ��
			        
				}else{
					if (i==0) {
			        	sql = "update "+TABLE_NAME+" set mission_state=1,mission_accepter='"+accpter_id+"' where mission_id="+mission_id+";";
			        	System.out.println(sql);
			        	int rs_int = stmt.executeUpdate(sql);
			        	if(rs_int>0){
			        		type = 1;//���ܳɹ�
			        	}else {
							type = 3;//������������
						}
					}else{
						type = 3;//������������
					}
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
