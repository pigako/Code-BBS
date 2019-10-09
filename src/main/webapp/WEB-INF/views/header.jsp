<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session = "true" %>
<!DOCTYPE html>
<html>
<head>
        <link rel="stylesheet" href="/resources/detailBoard.css" type="text/css">
        <link rel="stylesheet" href="/resources/reset.css" type="text/css">
        <link rel="stylesheet" href="/resources/style.css" type="text/css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<title>코드 공유 게시판</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
        <div>
                <div class="menu">
                        <ul class="PrimaryNav with-indicator">
                                <li class="Nav-item" onclick="click_menu(this)" data-num="0"><a href="/">코드게시판</a></li>
                                <li class="Nav-item" onclick="click_menu(this)" data-num="1"><a href="/free">자유게시판</a></li>
                                <li class="Nav-item" onclick="click_menu(this)" data-num="2"><a href="/qna">Q&A</a></li>
                        </ul>
                </div>
                <div class="logindiv">
                        <c:choose>
                                <c:when test='${not empty sessionScope.id}'>
                                        <div class="user_name">
                                                <span><c:out
                                                        value="${id}님"></c:out></span>
                                        </div>
                                        <div class="login_btn">
                                                <button class="btn" onclick="location.href='/logout'">로그아웃</button>
                                        </div>
                                </c:when>
                                <c:otherwise>
                                        <button class="btn" onclick="location.href='/login/login'">로그인</button>
                                </c:otherwise>
                        </c:choose>
                </div>
        </div>
</body>
<script>
        href=location.href.split("/")[3].split("?")[0]
        if(href=="qna"){
                $('.PrimaryNav').children().eq(2).addClass("is-active")
        }else if(href=="free"){
                $('.PrimaryNav').children().eq(1).addClass("is-active")
        }else{
                $('.PrimaryNav').children().eq(0).addClass("is-active")
        }
</script>
</html>