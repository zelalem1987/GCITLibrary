package com.gcit.training.library.data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionUtils {
	
	public static Connection getConnection() throws SQLException{
		Connection conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library2", "root", "mysql");
			conn.setAutoCommit(false);
		}
		catch(Exception ex){
			
		}
		return conn;
		
	}
	
	public ConnectionUtils(){
		super();
	}

}
