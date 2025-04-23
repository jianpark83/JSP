package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		//String command = request.getParameter("command"); command값도 확인 가능
		//System.out.println("num : " + num);
	
		BoardDAO bDao = BoardDAO.getInstance();
		
		//조회수 증가
		bDao.updateReadCount(num);
		
		//num(primary key) 해당하는 테이터 가져오기
		BoardVO bVo = bDao.selectOneBoardByNum(num);
		request.setAttribute("board", bVo);
		
		request.getRequestDispatcher("/board/boardView.jsp")
		.forward(request, response);
		
	}
}
