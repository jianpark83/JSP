package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;

@WebServlet("/productDelete.do")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String code = request.getParameter("code");
		
		//DB 연결, 정보 가져오기(pVo로 전달)
		ProductVO pVo = ProductDAO.getInstance().selectProductByCode(code);

		//가져온 정보 수정화면으로 전달
		request.setAttribute("product", pVo);
		
		request.getRequestDispatcher("product/productDelete.jsp")  //호출
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int code = Integer.parseInt(request.getParameter("code"));

		//삭제 처리
		ProductDAO.getInstance().deleteProductByCode(code);
		
		response.sendRedirect("productList.do");
	}

}
