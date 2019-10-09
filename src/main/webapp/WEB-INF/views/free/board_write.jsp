<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<html>
<head>
	<title>자유게시판</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
        <jsp:include page="../header.jsp" flush="flase" />
        
        <form method="post" class="code_write" action="/free/write" enctype="multipart/form-data">
                        <div class="write_top">
                                <div class="write_select">
                                        <select class="lang" id="langsel" name="fb_type" style="border:1px solid #e5e5e5;">
                                                <c:if test="${ 'root' eq sessionScope.id}">
													<option value="공지">공지</option>
												</c:if>
												<option value="수다">수다</option>
												<option value="건의">건의</option>
                                        </select>
                                </div>
                                <div class="code_write_title">
                                        <input type="text" class="writeTitle2" name="fb_title" placeholder="제목">
                                </div>	
                        </div>
						<div>	
                             	<div style="margin-top: 20px;" >
                                	<textarea name="fb_text" id="writebody" style="height:200px;" placeholder="내용"></textarea>
								</div>
						<br />
						</div>
						
						<div class="writeBot" style="">
							<div class="file-upload btn btn-primary">
							    <span>FILE</span>
							    <input type="file" name="file" id="FileAttachment" class="upload" multiple />
							</div>
							<input type="text" id="fileuploadurl" readonly placeholder="파일을 선택해주세요.">
						</div>
						
                        <div class="code_write_btn">
                                <button class="btn" type="submit">완료</button>
                                <button type="button" class="btn" onclick='javascript:history.back();'>취소</button>
                        </div>
		</form>
</body>
<script type="text/javascript" src="../../../resources/ckeditor2/ckeditor.js"></script>
<script>
	CKEDITOR.replace( 'writebody',{
	    height:'450px'
	});
	
	document.getElementById("FileAttachment").onchange = function () {
		if(this.files.length > 1) {
			document.getElementById("fileuploadurl").value = this.files[0].name;
			
			for(var i = 1 ; i < this.files.length; i++){
				document.getElementById("fileuploadurl").value += ", " + this.files[i].name;
			}
		} else {
			document.getElementById("fileuploadurl").value = this.files[0].name;
		}
	    
	};
</script>
</html>