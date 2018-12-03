package com.dbtools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {  
    private static String url="jdbc:mysql://localhost:3306/heartrec";  //数据库名
    private static String driverClass="com.mysql.jdbc.Driver";
    private static String username="root";  //登录数据库用户名
    private static String password="dubhe";  //登录数据库密码
    private static Connection conn;  
    //装载驱动  
    static{  
        try{  
            Class.forName(driverClass);  
            System.out.println("装载驱动成功");
        }  
        catch(ClassNotFoundException e){  
            e.printStackTrace();  
        }  
    }  
    //获取数据库连接  
    public static Connection getConnection(){  
        try{  
            conn=DriverManager.getConnection(url,username,password);  
            System.out.println("数据库连接成功");
        }  
        catch(SQLException e){  
            e.printStackTrace();  
        }  
        return conn;  
    }  
    //建立数据库连接  
    public static void main(String[] args){  
        Connection conn= DBUtil.getConnection();
        if(conn==null){  
            System.out.println("数据库连接失败！");  
        }  
        else{
        	System.out.println("数据库连接成功");
        }
        }  
    //关闭数据库连接  
    public static void Close(){  
        if(conn!=null){  
            try{  
                conn.close();  
            }  
            catch(SQLException e){  
                e.printStackTrace();  
            }  
        }  
    }  
    }  