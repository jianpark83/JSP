package com.saeyan.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MemberDAO {

	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {
	}
	
	public static MemberDAO getinstance() {
		return instance;
	}
	
	//1. DB연결
	public Connection getConnection() throws Exception{
		
		String url = "jdbc:oracle:thin:@localhost:49161:xe";
		String uid = "system";
		String pass = "oracle";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection(url,uid,pass);
	}
}
