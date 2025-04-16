package com.magic.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;

public class EmployeesDAO {

	private EmployeesDAO() {
		
	}
	
	private static EmployeesDAO instance = new EmployeesDAO();
	
	public static EmployeesDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		
		String url = "jdbc:oracle:thin:@localhost:49161:xe";
		String uid = "system";
		String pass = "oracle";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection(url,uid,pass);

	}
	
}
