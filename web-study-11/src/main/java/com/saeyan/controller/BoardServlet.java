package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.controller.action.Action;
import com.saeyan.controller.action.ActionFactory;

//Spring --> DispatcherServlet(Front Controller)
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		//2.board_write
		//1.BoardServlet?command=board_write_form
		String command = request.getParameter("command");
		System.out.println("BoardServlet에서 요청을 받음 확인 : " + command);
	
		ActionFactory af = ActionFactory.getInstance(); //handler mapping 전달
		Action action = af.getAction(command); //요청 받으면 af.getAction(command)
		
		//HandlerAdapter
		if(action != null) {
			action.execute(request, response); //참조하고 있는 대상을 실행시켜준다
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		doGet(request, response);
	}

}
