<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
	<head>
		<title>질문 게시판 : 작성</title>
                <script src="/resources/ckeditor2/ckeditor.js"></script>
	</head>
        <body onload="init();">
                <jsp:include page="../header.jsp" flush="flase" />
		<form method="post" class="code_write" action="/qna/write">
                        <div class="write_top">
                                <div class="code_write_title" style="padding:0px">
                                        <input type="text" name="qb_title" placeholder="제목">
                                </div>	
                        </div>
			<div>	
                                <div style="margin-top: 20px;" >
                                                <textarea name="qb_text" id="writebody" style="height:200px;" placeholder="내용"></textarea>
				</div>
				<br />
			</div>
                        <div class="code_write_btn">
                                <button type="button" class="btn" onclick='checking_form()'>완료</button>
                                <button type="button" class="btn" onclick='back()'>취소</button>
                        </div>
		</form>
		<script>
			function init(){
				if("${session}"!='yes'){
					alert("비정상적 접근입니다");
					location.href="/";
				}
                        };
                        CKEDITOR.replace( 'writebody',{
                                height:"500px"
                        });
			function back() {
				href = "/qna?currentPageNo=" + ${cpa.currentPageNo} + "&maxPost=" + ${cpa.maxPost};
				href += "&search=" + "${search.search}" + "&type=" + "${search.type}";
				location.href = href;
			}
			function checking_form() {
                                var form = document.forms[0];
				var title=form.qb_title.value;
				form.qb_text.value=CKEDITOR.instances.writebody.getData()
				if(title == ""){
					alert('제목을 적어주세요');
					return
				}else if(title.length >50){
					alert("제목은 최대 50글자까지 가능합니다")
					return
				}
				if(form.qb_text.value == ""){
					alert('내용을 채워주세요');
					return
				}
				form.submit();
			}
		</script>
	</body>
</html>