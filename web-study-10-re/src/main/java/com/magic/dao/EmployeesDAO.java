package com.magic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.magic.dto.EmployeesVO;

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
		
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//return DriverManager.getConnection(url,uid,pass);
		try {
	        // DB 연결 시도
	        Connection conn = DriverManager.getConnection(url, uid, pass);
	        System.out.println("DB 연결 성공!");
	        return conn;
	    } catch (Exception e) {
	        System.out.println("DB 연결 실패: " + e.getMessage());
	        throw e;
	    }
	}
	//id, pwd 전달받아서, DB랑 연동해서 데이타가 있는지 조회
	public int userCheck(String id, String pass) {
		
		/*
		 * 1  : id, pwd 일치
		 * 0  : id 일치, pwd 불일치
		 * -1 : id 불일치 
		 */
		
		int result = -1;
		
		String sql = "select pass from employees where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;  //sql구문이 select일때만 기입!
		
		try {
			//1. DB연결
			conn = getConnection();
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			//3. sql 맵핑
			pstmt.setString(1, id);  //전달받은 값으로 맵핑
			//4. sql 구문 실행
			rs = pstmt.executeQuery();  //sql구문이 select일때만 
			
			if(rs.next()) {
				//회원 ID 존재!
				if(rs.getString("pass") != null &&    //입력 들어온 암호와 내가 전달받은 암호가 같은지
					/*	rs.getString("pass").equals(pass)) {
					result = 1;  //id, pwd 일치  */
						
						rs.getString("pass").equals(pass) &&
					    rs.getString("id").toLowerCase().equals(id.toLowerCase())) {	
				}else {
					result = 0; //pwd만 불일치
				}
			}else {
				//이런 회원 id는 없다!
				result = -1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if ( rs != null) rs.close();
				if ( pstmt != null) pstmt.close();
				if ( conn != null) conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
	
	public EmployeesVO getEmployees(String id) {
		
		EmployeesVO eVo = null;
		
		String sql = "select * from employees where id = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String emid = rs.getString("id"); 
				String pass = rs.getString("pass"); 
				String name = rs.getString("name"); 
				String lev = rs.getString("lev");
				Date enter = rs.getDate("enter");
				String gender = rs.getString("gender"); 
				String phone = rs.getString("phone"); 

				
				eVo = new EmployeesVO();
				eVo.setId(emid);
				eVo.setPass(pass);
				eVo.setName(name);
				eVo.setLev(lev);
				eVo.setEnter(enter);
				eVo.setGender(gender);
				eVo.setPhone(phone);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null ) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return eVo;
	}

}