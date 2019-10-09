<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
	<head>
		<title>코드 공유 게시판 : 수정</title>
		<!-- <link rel="stylesheet" href="/resources/design.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="/resources/read.css" type="text/css" media="screen" /> -->
                <script src="/resources/autosize.js"></script>
                <script src="/resources/ckeditor/ckeditor.js"></script>
	</head>
        <body onload="init();">
                <jsp:include page="../header.jsp" flush="flase" />
                <form method="post" class="code_write" action="/code/modify">
                        <input name="cb_no" value="${post.cb_no}" style="display: none">
                        <div class="write_top">
                                <div class="write_select">
                                        <select class="lang" id="langsel" name="cb_lang" style="border:1px solid #e5e5e5;">
                                                <option value="Apache" <c:if test="${post.cb_lang == 'Apache'}">selected</c:if> >Apache</option>
                                                <option value="Bash" <c:if test="${post.cb_lang == 'Bash'}">selected</c:if>>Bash</option>
                                                <option value="CoffeeScript" <c:if test="${post.cb_lang == 'CoffeeScript'}">selected</c:if>>CoffeeScript</option>
                                                <option value="C++" <c:if test="${post.cb_lang == 'C++'}">selected</c:if>>C++</option>
                                                <option value="C#" <c:if test="${post.cb_lang == 'C#'}">selected</c:if>>C#</option>
                                                <option value="CSS" <c:if test="${post.cb_lang == 'CSS'}">selected</c:if>>CSS</option>
                                                <option value="Diff" <c:if test="${post.cb_lang == 'Diff'}">selected</c:if>>Diff</option>
                                                <option value="HTML" <c:if test="${post.cb_lang == 'HTML'}">selected</c:if>>HTML</option>
                                                <option value="HTTP" <c:if test="${post.cb_lang == 'HTTP'}">selected</c:if>>HTTP</option>
                                                <option value="INI" <c:if test="${post.cb_lang == 'INI'}">selected</c:if>>INI</option>
                                                <option value="Java" <c:if test="${post.cb_lang == 'Java'}">selected</c:if>>Java</option>
                                                <option value="JavaScript" <c:if test="${post.cb_lang == 'JavaScript'}">selected</c:if>>JavaScript</option>
                                                <option value="Json" <c:if test="${post.cb_lang == 'Json'}">selected</c:if>>Json</option>
                                                <option value="Makefile" <c:if test="${post.cb_lang == 'Makefile'}">selected</c:if>>Makefile</option>
                                                <option value="Markdown" <c:if test="${post.cb_lang == 'Markdown'}">selected</c:if>>Markdown</option>
                                                <option value="Nginx" <c:if test="${post.cb_lang == 'Nginx'}">selected</c:if>>Nginx</option>
                                                <option value="Objective-C" <c:if test="${post.cb_lang == 'Objective-C'}">selected</c:if>>Objective-C</option>
                                                <option value="Perl" <c:if test="${post.cb_lang == 'Perl'}">selected</c:if>>Perl</option>
                                                <option value="PHP" <c:if test="${post.cb_lang == 'PHP'}">selected</c:if>>PHP</option>
                                                <option value="Python" <c:if test="${post.cb_lang == 'Python'}">selected</c:if>>Python</option>
                                                <option value="Ruby" <c:if test="${post.cb_lang == 'Ruby'}">selected</c:if>>Ruby</option>
                                                <option value="SQL" <c:if test="${post.cb_lang == 'SQL'}">selected</c:if>>SQL</option>
                                                <option value="VBScript" <c:if test="${post.cb_lang == 'VBScript'}">selected</c:if>>VBScript</option>
                                                <option value="XHTML" <c:if test="${post.cb_lang == 'XHTML'}">selected</c:if>>XHTML</option>
                                                <option value="XML" <c:if test="${post.cb_lang == 'XML'}">selected</c:if>>XML</option>
                                        </select>
                                </div>
                                <div class="code_write_title">
                                        <input type="text" name="cb_title" placeholder="제목" value="${post.cb_title}">
                                </div>	
                        </div>
			<div>	
                                <div style="margin-top: 20px;" >
                                        <textarea name="cb_text" id="writebody" style="height:200px;" placeholder="내용" >${post.cb_text}</textarea>
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
				history.back()
			}
			function checking_form() {
                                var form = document.forms[0];
				var title=form.cb_title.value;
				form.cb_text.value=CKEDITOR.instances.writebody.getData()
				if(title == ""){
					alert('제목을 적어주세요');
					return
				}else if(title.length >50){
					alert("제목은 최대 50글자까지 가능합니다")
					return
				}
				if(form.cb_text.value == ""){
					alert('내용을 채워주세요');
					return
				}
				form.submit();
			}
		</script>
	</body>
</html>