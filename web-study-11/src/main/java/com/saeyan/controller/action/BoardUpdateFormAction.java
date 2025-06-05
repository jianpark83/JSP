package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		                                          //"BoardServlet?command=board_update&num=<%=request.getParameter("num")%>"
		String num = request.getParameter("num"); //checkSueecss.jsp 에서 받아온 num값
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = bDao.selectOneBoardByNum(Integer.parseInt(num));
		
		request.setAttribute("board", bVo);  //boardUpdate에서 보여주기 위해 DB자료를 bVo로 받아서 board에 담아서 boardUpdate에 전달
		
		request.getRequestDispatcher("/board/boardUpdate.jsp")
		.forward(request, response);
	}

}
