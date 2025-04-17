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
		
		String url = "login.jsp";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			url = "main.jsp";
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String lev = request.getParameter("lev");
		String url=null;
		
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		int result = eDao.userCheck(id, pass, lev);

		if(result == 2 || result == 3){
			EmployeesVO eVo = new EmployeesVO();
			eVo = eDao.getMember(id);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", eVo);			
			session.setAttribute("result", result);			
			url = "main.jsp";				
		}else{
			url = "login.jsp";
			if(result == 1){
				request.setAttribute("message", ".");
			}else if(result == 0){			
				request.setAttribute("message", ".");
			}else{			
				request.setAttribute("message", ".");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
