<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
	<head>
		<title>코드 공유 게시판 : 작성</title>
		<!-- <link rel="stylesheet" href="/resources/design.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="/resources/read.css" type="text/css" media="screen" /> -->
                <script src="/resources/autosize.js"></script>
                <script src="/resources/ckeditor/ckeditor.js"></script>
	</head>
        <body onload="init();">
                <jsp:include page="../header.jsp" flush="flase" />
		<form method="post" class="code_write" action="/code/write">
                        <div class="write_top">
                                <div class="write_select">
                                        <select class="lang" id="langsel" name="cb_lang" style="border:1px solid #e5e5e5;">
                                                <option value="Apache">Apache</option>
                                                <option value="Bash">Bash</option>
                                                <option value="CoffeeScript">CoffeeScript</option>
                                                <option value="C++">C++</option>
                                                <option value="C#">C#</option>
                                                <option value="CSS">CSS</option>
                                                <option value="Diff">Diff</option>
                                                <option value="HTML">HTML</option>
                                                <option value="HTTP">HTTP</option>
                                                <option value="INI">INI</option>
                                                <option value="Java">Java</option>
                                                <option value="JavaScript" selected>JavaScript</option>
                                                <option value="Json">Json</option>
                                                <option value="Makefile">Makefile</option>
                                                <option value="Markdown">Markdown</option>
                                                <option value="Nginx">Nginx</option>
                                                <option value="Objective-C">Objective-C</option>
                                                <option value="Perl">Perl</option>
                                                <option value="PHP">PHP</option>
                                                <option value="Python">Python</option>
                                                <option value="Ruby">Ruby</option>
                                                <option value="SQL">SQL</option>
                                                <option value="VBScript">VBScript</option>
                                                <option value="XHTML">XHTML</option>
                                                <option value="XML">XML</option>
                                        </select>
                                </div>
                                <div class="code_write_title">
                                        <input type="text" name="cb_title" placeholder="제목">
                                </div>	
                        </div>
			<div>	
                                <div style="margin-top: 20px;" >
                                                <textarea name="cb_text" id="writebody" style="height:200px;" placeholder="내용"></textarea>
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
				href = "/?currentPageNo=" + ${cpa.currentPageNo} + "&maxPost=" + ${cpa.maxPost};
				href += "&search=" + "${search.search}" + "&type=" + "${search.type}";
				location.href = href;
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