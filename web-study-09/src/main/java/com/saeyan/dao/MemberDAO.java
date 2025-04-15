package com.saeyan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.saeyan.dto.MemberVO;

public class MemberDAO {
	
	private static MemberDAO instance  = new MemberDAO();
	
	private MemberDAO() {
	}
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	
	//1. DB 연결
	public Connection getConnection() throws Exception{	
		
		String url = "jdbc:oracle:thin:@localhost:49161:xe";
		String uid = "system";
		String pass = "oracle";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2. DB연결	    
		
		return DriverManager.getConnection(url,uid,pass);
	}

	//userid, pwd 전달받아서, DB랑 연동해서 데이타가 있는지 조회
	public int userCheck(String userid, String pwd) {
		
		/*
		 * 1  : userid, pwd 일치
		 * 0  : userid 일치, pwd 불일치
		 * -1 : userid 불일치 
		 */
		
		int result = -1;
		
		String sql = "select pwd from member where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;  //sql구문이 select일때만 기입!
		
		try {
			//1. DB연결
			conn = getConnection();
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			//3. sql 맵핑
			pstmt.setString(1, userid);
			//4. sql 구문 실행
			rs = pstmt.executeQuery(); //sql구문이 select일때만 
			
			if(rs.next()) {
				//회원 ID 존재!
				if(rs.getString("pwd") != null && 
						rs.getString("pwd").equals(pwd)) {
					result = 1;  //userid, pwd 일치
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

	public MemberVO getMember(String userid) {
		
		MemberVO mVo = null;
		
		String sql = "select * from member where userid = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name"); 
				String id = rs.getString("userid"); 
				String pwd = rs.getString("pwd"); 
				String email = rs.getString("email"); 
				String phone = rs.getString("phone"); 
				int admin = rs.getInt("admin"); 
				
				mVo = new MemberVO();
				mVo.setName(name);
				mVo.setUserid(id);
				mVo.setPwd(pwd);
				mVo.setEmail(email);
				mVo.setPhone(phone);
				mVo.setAdmin(admin);				
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
		
		return mVo;
	}

	public int confirmID(String userid) {
		
		int result = 1;
		
		String sql = "select userid from member where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();   //rs에 값이 있는지 확인 후 if문으로 
			
			if(rs.next()) {
				result = 1;  //id 존재 => 사용불가 id
			}else {
				result = -1; //사용가능한 id
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}