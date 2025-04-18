package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;

@WebServlet("/productUpdate.do")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //code값 불러오기
		String code = request.getParameter("code");

		//DB 연결, 정보 가져오기(pVo로 전달)
		ProductVO pVo = ProductDAO.getInstance().selectProductByCode(code);

		//가져온 정보 수정화면으로 전달
		request.setAttribute("product", pVo);
		
		request.getRequestDispatcher("product/productUpdate.jsp")  //호출
		.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html, charset=utf-8");
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		
		String encType = "utf-8";
		
		int sizeLimit = 20*1024*1024;  //20Mb
		
		MultipartRequest multi = new MultipartRequest(
				request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		
		int code = Integer.parseInt(multi.getParameter("code"));
		String name = multi.getParameter("name");
		int price = Integer.parseInt(multi.getParameter("price"));
		String pictureurl = multi.getFilesystemName("pictureurl");
		String description = multi.getParameter("description");
		
		//수정시 이미지 첨부를 하지 않으면, 기본 이미지 적용(noimage.gif)
		if(pictureurl == null) {
			pictureurl = multi.getParameter("nonmakeImg");
		}
		
		ProductVO pVo = new ProductVO();
		pVo.setCode(code);
		pVo.setName(name);
		pVo.setPrice(price);
		pVo.setPictureurl(pictureurl);
		pVo.setDescription(description);
		
		ProductDAO.getInstance().updateProduct(pVo); //DB 저장		

		response.sendRedirect("productList.do"); //리스트 목록으로 이동
	
	}
}
