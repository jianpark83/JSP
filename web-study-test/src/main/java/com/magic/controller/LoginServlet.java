package com.magic.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magic.dao.EmployeesDAO;
import com.magic.dto.EmployeesVO;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String url = "employees/login.jsp";
		
		RequestDispatcher dis = request.getRequestDispatcher("employees/login.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String lev = request.getParameter("lev");
	
		EmployeesDAO empDao = EmployeesDAO.getInstance();
		int result = empDao.userCheck(userid, pwd, lev);
		
		System.out.println("로그인 여부 : " + result);
		
		if(result == 2 || result == 3) {
	        // 로그인 성공 시 회원 정보 조회
	        EmployeesVO loginUser = empDao.getMember(userid);
	        // 세션에 저장
	        request.getSession().setAttribute("loginUser", loginUser);
	        // 회원 전용 페이지로 이동
	        response.sendRedirect("main.jsp"); // memberOnly.jsp는 회원 전용 페이지 파일명 예시
	    } else {
	        // 로그인 실패
	        request.setAttribute("message", "로그인 실패: 아이디/비밀번호/등급을 확인하세요.");
	        RequestDispatcher dis = request.getRequestDispatcher("employees/login.jsp");
	        dis.forward(request, response);
	    }
	}
}

