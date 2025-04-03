package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BasketServlet1")
public class BasketServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BasketServlet1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        // 상품 ID 가져오기
        String productId = request.getParameter("id");

        out.println("<!DOCTYPE html>");
        out.println("<html lang='ko'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>상품 선택</title>");
        out.println("<style>");
        out.println("body { font-family: '맑은 고딕', sans-serif; text-align: center; margin-top: 50px; }");
        out.println("h2 { color: Black; }");
        out.println("img { width: 200px; height: auto; margin-top: 20px; border: 2px solid #000; box-shadow: 4px 4px 10px rgba(0,0,0,0.3); }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        String imagePath = request.getContextPath() + "/img/" + Integer.parseInt(productId.substring(1)) + ".jpg";
        out.println("<h2>선택된 상품 : " + productId + "</h2>");
    	out.println("<img src='" + imagePath + "' alt='선택된 상품 이미지'>");
        
        out.println("<br><br>");
        out.println("<a href='productlog1.jsp'>상품 목록으로 돌아가기</a>");
        out.println("</body>");
        out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
