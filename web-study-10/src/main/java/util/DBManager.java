package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		String url = null;
		String uid = null;
		String pass = null;
		
		try {
			url = "jdbc:oracle:thin:@localhost:49161:xe";
			uid = "system";
			pass = "oracle";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection(url,uid,pass);
		}catch(Exception e) {  
			e.printStackTrace();	
		}
		
		return null;
	} //end getConnection
	                                         //상위클래스임 
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
