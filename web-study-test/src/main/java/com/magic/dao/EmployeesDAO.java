package com.magic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.magic.dto.EmployeesVO;

public class EmployeesDAO {

	private EmployeesDAO() {}
	
	private static EmployeesDAO instance = new EmployeesDAO();
	
	public static EmployeesDAO getInstance() {
		return instance;
	}
	
	//DB 연결
	public Connection getConnection() throws Exception{
		
		String url = "jdbc:oracle:thin:@localhost:49161:xe";
		String uid = "system";
		String pass = "oracle";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection(url,uid,pass);
	}

	//id, pwd, lev 전달받아서 DB랑 연동해서 데이터 있는지 조회
	public int userCheck(String userid, String pwd, String lev) {
		
		int result = -1;
		String sql = "select * from employees where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;  //sql구문이 select일때만 기입!
		
		try {
			//1. DB연결
			conn = getConnection();
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			//3. sql 맵핑
			pstmt.setString(1, userid);  //전달받은 값으로 맵핑
			//4. sql 구문 실행
			rs = pstmt.executeQuery();  //sql구문이 select일때만 
			
			if(rs.next()){
			    //비밀번호가 일치하고 
				if(pwd.equals(rs.getString("pass"))){
					//회원등급이 일치하면
					if(lev.equals(rs.getString("lev"))){
						result = 2; //관리자 로그인 성공
						if(lev.equals("B")){
							result=3; //일반 회원 로그인 성공
						}
					}else{  //레벨 틀림
						result = 1;
					}
				}else{  //비밀번호 틀림
					result = 0;
				}
			}else{  //아이디 없음
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
	
	public EmployeesVO getMember(String id) {
		
	    EmployeesVO member = null;
	    String sql = "select * from employees where id=?";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            member = new EmployeesVO();
	            member.setId(rs.getString("id"));
	            member.setPass(rs.getString("pass"));
	            member.setName(rs.getString("name"));
	            member.setLev(rs.getString("lev"));
	            member.setEnter(rs.getDate("enter"));
	            member.setGender(rs.getInt("gender")); // String 타입으로 변경
	            member.setPhone(rs.getString("phone"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return member;
	}
}

