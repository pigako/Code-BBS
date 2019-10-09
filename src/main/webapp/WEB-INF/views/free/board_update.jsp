<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
	<title>자유게시판</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
        <jsp:include page="../header.jsp" flush="flase" />
        
        <div id="frame">
			<form action="/free/update" method="post" class="writeForm">
				<input name="fb_no" style="display:none" value="${board.fb_no}">
				<div class="writeTop">
					<div class="writeType">
						<select class="writeType2" name="fb_type">
							<c:if test="${ 'root' eq sessionScope.id}">
								<option value="공지" <c:if test="${board.fb_type == '공지'}">selected</c:if> >공지</option>
							</c:if>
							<option value="수다" <c:if test="${board.fb_type == '수다'}">selected</c:if>>수다</option>
							<option value="건의" <c:if test="${board.fb_type == '건의'}">selected</c:if>>건의</option>
						</select>
					</div>
					<div class="writeTitle">
						<input type="text" class="writeTitle2" name="fb_title" placeholder="제목" value="${board.fb_title }">
					</div>
				</div>
				<div class="writeMid">
					<div class="writeContent">
						<textarea id="writeContent2" name="fb_text" cols="108" rows="15">${board.fb_text }</textarea>
					</div>
				</div>
				<div class="writeBot" style="">
					<div class="writeFile">
						
					</div>
				</div>
				<div class="code_write_btn">
					<button class="btn" type="submit">완료</button>
                    <button type="button" class="btn" onclick='javascript:history.back();'>취소</button>
				</div>
			</form>
		</div>
</body>
<script type="text/javascript" src="../../../resources/ckeditor2/ckeditor.js"></script>
<script>
	CKEDITOR.replace( 'writeContent2',{
		toolbar:'Basic',
	    filebrowserUploadUrl: '/board/imgupload',
	    height:'450px'
	});
	
	
</script>
</html>