<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 목록</title>
<style>
    *{margin: 0; padding: 0;}
    #box {width: 100%; max-width: 1100px; height: 55px; margin: 0 auto;
        border-bottom: 1px solid gray; justify-content: center;}
    #box > h2 {margin-top: 25px; font-family: "맑은고딕"; text-align: center;}
    #container {
        width: 1100px;
        margin: 20px auto;
        display: grid;
        grid-template-columns: repeat(5, 1fr);
        gap: 25px;
        max-width: 600px;
        justify-content: center;
    }
    .doll a {
        text-align: center;
        display: block;
        text-decoration: none;
        border: 1px solid #000;
        padding: 10px;
        background: white;
        box-shadow: 2px 2px 5px rgba(0,0,0,0.2);
        transition: transform 0.2s, box-shadow 0.2s;
        box-shadow: 10px 10px 5px #666;
    }
    .doll a:hover {
        transform: scale(1.1);
        box-shadow: 4px 4px 10px rgba(0,0,0,0.3);
    }
    .doll img {
        width: 150px;
        height: auto;
    }
</style>
</head>
<body>
    <div id="box">
        <h2>원하는 상품을 클릭해 주세요!! 마구마구^^</h2>
    </div>
    <div id="container">
        <div class="doll"><a href="BasketServlet1?id=p001"><img src="img/1.jpg" alt="doll1"></a></div>
        <div class="doll"><a href="BasketServlet1?id=p002"><img src="img/2.jpg" alt="doll2"></a></div>
        <div class="doll"><a href="BasketServlet1?id=p003"><img src="img/3.jpg" alt="doll3"></a></div>
        <div class="doll"><a href="BasketServlet1?id=p004"><img src="img/4.jpg" alt="doll4"></a></div>
        <div class="doll"><a href="BasketServlet1?id=p005"><img src="img/5.jpg" alt="doll5"></a></div>
        <div class="doll"><a href="BasketServlet1?id=p006"><img src="img/6.jpg" alt="doll6"></a></div>
        <div class="doll"><a href="BasketServlet1?id=p007"><img src="img/7.jpg" alt="doll7"></a></div>
        <div class="doll"><a href="BasketServlet1?id=p008"><img src="img/8.jpg" alt="doll8"></a></div>
        <div class="doll"><a href="BasketServlet1?id=p009"><img src="img/9.jpg" alt="doll9"></a></div>
        <div class="doll"><a href="BasketServlet1?id=p010"><img src="img/10.jpg" alt="doll10"></a></div>
    </div>
</body>
</html>