package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//HandlerAdapter
public interface Action {
	//추상메소드
	public void execute(HttpServletRequest request, HttpServletResponse response) //client에 전달
				throws ServletException, IOException;
}