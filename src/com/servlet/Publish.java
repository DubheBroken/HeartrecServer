package com.servlet;

import com.dbtools.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Publish extends HttpServlet {
	
	private static final String TABLE_NAME = "mission_info";
	/**
	 * Constructor of the object.
	 */
	public Publish() {
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
	    String title = request.getParameter("publish_title"); //���� 
	        String substance= request.getParameter("publish_substance");//����
	        String time = request.getParameter("publish_time");//��ֹʱ��
	        String place = request.getParameter("publish_place");//�ص�
	        String place_remark = request.getParameter("publish_place_remark");//���µص�
	        String publisher = request.getParameter("publish_publisher");//������
	        String reward = request.getParameter("publish_reward");//�ͽ�
	        String cost = request.getParameter("publish_cost");//�ɱ�
	        boolean type = false;//���ؽ��
	        PrintWriter out = response.getWriter();  
	        try  
	        {  
	            Connection con= DBUtil.getConnection();
	            Statement stmt=con.createStatement();  
			try {
				String sql="insert into "+TABLE_NAME+" values(null,'"+title+"','"+substance+"',"+time+",'"+place+"','"+place_remark+"',"+reward+","+cost+","+publisher+",null,0);";
				System.out.println(sql);
				int i = stmt.executeUpdate(sql);  //ִ�����
		        if (i>0) {
		        	type = true;
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
	
//	public void doFilter(ServletRequest request, ServletResponse response, 
//			FilterChain chain) throws IOException, ServletException { 
//			HttpServletRequest req=(HttpServletRequest) request; 
//			MyFilter myf =new MyFilter(req); 
//			chain.doFilter(myf, response);//�������Ǽ�ǿ�����request,���������servletʹ�� 
//			}
	
//	class MyFilter extends HttpServletRequestWrapper implements Filter{ 
//		//���ǲ��ð�װģʽ 
//		public MyFilter(HttpServletRequest request) { 
//		super(request); 
//		} 
//		@Override //��д������� 
//		public String getParameter(String name) { 
//		String words =super.getParameter(name);
//		System.out.println(words);//����ǰ������ 
//		List<String> list=WordUtils.getword(); 
//		for(String ll:list){ 
//		String xing="*";
//		words=words.replace(ll, xing);//���дʻ����**���� 
//		} 
//		return words; 
//		} 
//		
//		@Override
//		public boolean test(int arg0) {
//			// TODO Auto-generated method stub
//			return false;
//		} 
//	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
