package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.BoardVO;

import util.DBManager;

public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {
	}
	
	public static BoardDAO getInstance() {
		return instance;
	}

	//전체 데이타 가져오기
	public List<BoardVO> selectAllBoards() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String sql = "select * from board order by num desc";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			//1. DB연결
			conn = DBManager.getConnection();
			//2. sql전송
			pstmt = conn.prepareStatement(sql);
			//3. sql 맵핑
			//4. sql 실행
			rs =  pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setPass(rs.getString("pass"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadCount(rs.getInt("readcount"));
				bVo.setWriteDate(rs.getTimestamp("writedate"));
				
				list.add(bVo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	} // end selectAllBoards

	//데이타 추가하기
	public void insertBoard(BoardVO bVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	
		String sql = "insert into board(num, name, pass, email, title, content) "
				+ "values(board_seq.nextval, ?, ?, ?, ?, ?)";
		
		try {
			//1. DB연결
			conn = DBManager.getConnection();
			//2. sql전송
			pstmt = conn.prepareStatement(sql);
			//3. sql 맵핑
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getPass());
			pstmt.setString(3, bVo.getEmail());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			//4. sql 실행
			pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}		
	} //end insertBoard
	
	
	//단건 데이타 가져오기
	//데이타 수정하기

	//데이타 삭제하기
}
