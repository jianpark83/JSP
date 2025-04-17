package com.magic.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.magic.dao.EmployeesDAO;
import com.magic.dto.EmployeesVO;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String url = "employees/login.jsp";
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") != null) {
			
			url = "main.jsp";
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		System.out.println("입력받은 id: [" + id + "]");
		System.out.println("입력받은 pass: [" + pass + "]");
		
		String url = "employees/login.jsp";
		//DB연결해서 userid, pwd 해당하는 사람이 있는지 확인!
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		
		int result = eDao.userCheck(id, pass);
		
		
		switch(result) {
		case 1:
			System.out.println("로그인 여부 : 성공");
			break;
		case 0:
			System.out.println("로그인 여부 : 비번 틀림");
			break;
		case -1:
			System.out.println("로그인 여부 : 아이디 틀림");
			break;
		}
		System.out.println("로그인 여부 : " + result );
		
		
		if(result == 1) {
			
			EmployeesVO eVo = eDao.getEmployees(id);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("loginUser", eVo);
			
			request.setAttribute("message", "회원 가입에 성공했습니다.");
			url = "main.jsp";
			
		}else if(result == 0){
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		}else if(result == -1) {
			request.setAttribute("message", "존재하지 않는 회원입니다.");   
		}
		
		//message 출력 후 다시 로그인 화면으로 이동(url)
		request.getRequestDispatcher(url).forward(request, response);
		
/*		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		String url = "employees/login.jsp";
		
		//DB연결해서 id, pass 해당하는 사람이 있는지 확인!
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		int result = eDao.userCheck(id, pass);
		
		if(result == 1) {
			
			EmployeesVO eVo = eDao.getEmployees(id);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", eVo);
			response.sendRedirect("main.jsp");
			//url = "main.jsp"; // 로그인 성공 시 main.jsp로 이동
		}else {
			// 로그인 실패 -> 다시 로그인 폼으로
			String message = (result == 0) ? "비밀번호가 맞지 않습니다." : "존재하지 않는 회원입니다.";
			request.setAttribute("message", message);
			RequestDispatcher dis = request.getRequestDispatcher("employees/login.jsp");
			dis.forward(request, response);
		}
		*/
	}

}