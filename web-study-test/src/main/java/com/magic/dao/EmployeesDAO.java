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
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection(url,uid,pass);
		
		/*try {
	        // DB 연결 시도
	        Connection conn = DriverManager.getConnection(url, uid, pass);
	        System.out.println("DB 연결 성공!");
	        return conn;
	    } catch (Exception e) {
	        System.out.println("DB 연결 실패: " + e.getMessage());
	        throw e; */
	    
		}	
	
	//id, pwd 전달받아서, DB랑 연동해서 데이타가 있는지 조회
	public int userCheck(String id, String pass, String lev) {
		
		/*
		 * 1  : id, pwd 일치
		 * 0  : id 일치, pwd 불일치
		 * -1 : id 불일치 
		 */
		
		int result=1;
		Connection conn = null;
		String sql="select * from employees where id =?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				
				if(pass.equals(rs.getString("pass"))){
					
					if(lev.equals(rs.getString("lev"))){
						result = 2; 
						if(lev.equals("B")){
							result=3;
						}
					}else{
						result = 1; //레벨 불일치
					}
				}else{  
					result = 0; //비밀번호 불일치
				}
			}else{ 
				result = -1; //아이디 없음
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public EmployeesVO getMember(String id){
		EmployeesVO employees = null;
		
		Connection conn = null;
		String sql="select * from employees where id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				employees = new EmployeesVO();
				employees.setId(rs.getString("id"));
				employees.setPass(rs.getString("pass"));
				employees.setName(rs.getString("name"));
				employees.setLev(rs.getString("lev"));				
				employees.setEnter(rs.getDate("enter"));
				employees.setGender(rs.getString("gender"));
				employees.setPhone(rs.getString("phone"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return employees;
	}
	
	public void insertMember(EmployeesVO member) {
		String sql = "insert into employees values(?,?,?,?,SYSDATE,?,?)";		
		Connection conn = null;

		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getLev());			
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getPhone());
			System.out.println(pstmt.executeUpdate());			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int updateMember(EmployeesVO eVo) {
		int result = -1;
		String sql = "update employees set gender=?, pass=?, name=?, lev=?, phone=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eVo.getGender());
			pstmt.setString(2, eVo.getPass());
			pstmt.setString(3, eVo.getName());
			pstmt.setString(4, eVo.getLev());
			pstmt.setString(5, eVo.getPhone());
			pstmt.setString(6, eVo.getId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}	
}
