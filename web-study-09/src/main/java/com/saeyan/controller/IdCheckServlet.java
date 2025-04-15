package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.MemberDAO;

@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IdCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userid = request.getParameter("userid");
		
		MemberDAO mDao = MemberDAO.getInstance();
		int result = mDao.confirmID(userid);  //DB에서 조회 result로 값을 전달
		
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		
		request.getRequestDispatcher("member/idCheck.jsp").forward(request, response); //만든 객체를 공유(forward)
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
