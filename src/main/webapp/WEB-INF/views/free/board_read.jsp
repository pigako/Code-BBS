<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session = "true" %>
<html>
<head>
	<title>자유게시판</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
        <jsp:include page="../header.jsp" flush="flase" />
        
        <div id="detailBoard">
			<div class="info">
				<h2>
					<span class="cate">자유게시판</span>
					<sapn class="title">[${board.fb_type}] ${board.fb_title}</sapn>
				</h2>
				<div class="writer">
					<strong>${board.mem_id}</strong><span>님</span>
				</div>
				
			</div>
			<div class="detailContent">
				<div class="header">
					<span class="date"><fmt:formatDate value="${board.fb_reg}" pattern="yyyy-MM-dd"/></span>
					<span class="view">조회 : ${board.fb_view}</span>
					<span class="space">space</span>
				</div>
				<div class="cont">
					<p>${board.fb_text}</p>
				</div>
				<div class="foot" style="border-top:1px solid #337ab7">
					<span>파일</span>
					<c:choose>
						<c:when test='${!empty file }'>
							<c:forEach items="${file}" var="file">
								<span><a href="../../../resources/upload_file/${file.ff_file_name }" download="${file.ff_origin_file_name}">${file.ff_origin_file_name}   </a></span>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<span></span>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="foot" style="border-top:1px solid #337ab7">
					<span><a href="javascript:history.back()">목록</a></span>
					<c:if test="${board.mem_id eq sessionScope.id}">
						<span><a href="/free/update?fb_no=${board.fb_no}">수정</a></span>
						<span><a href="/free/delete?fb_no=${board.fb_no}">삭제</a></span>
					</c:if>
				</div>
			</div>
			<div id="comment">
				<div class="header">
					<h5 class="header1">
						<span>댓글</span>
						<span>${totCom}</span>
						<span>개</span>
					</h5>
				</div>
				<div class="cont">
				<c:choose>
					<c:when test='${!empty comment}'>
						<c:forEach items="${comment}" var="cmm">
							<div class="comm" id="${cmm.fbc_no}" data-id="${cmm.fbc_no}">
								<div class="commHeader">
									<div class="commId">${cmm.mem_id}</div>
									<div class="commDate"><fmt:formatDate value="${cmm.fbc_reg}" pattern="yyyy-MM-dd"/></div>
									<c:if test="${cmm.mem_id eq sessionScope.id}">
										<div class="commUpdate"><span><a id="updateCom" data-id="${cmm.fbc_no}">수정</a></span></div>
										<div class="commDelete"><span><a id="deleteCom" data-id="${cmm.fbc_no}">삭제</a></span></div>
									</c:if>
									<span class="space">space</span>
								</div>
								<div class="commContent" >
									<div id="commentCotent2" data-id="${cmm.fbc_no}">
										<p  class="commContent2">${cmm.fbc_text }</p>
									</div>	
									<textarea id="updateCommentText${cmm.fbc_no}" name="board_content" cols="108" rows="15" style="display:none;" data-id="${cmm.fbc_no}">${cmm.fbc_text }</textarea>
								</div>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
				</div>
			</div>
			<c:if test="${not empty sessionScope.id}">
				<div class="writeMid">
					<div class="writeContent">
						<textarea id="writeContent2" name="board_content" cols="108" rows="15"></textarea>
					</div>
				</div>
				<div class="writeFinal">
					<button id="commBtn" class="writeButton" type="submit">댓글 쓰기</button>
				</div>
			</c:if>
		</div>
        
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js">></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.2/ace.js"></script>
<scirpt src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.2/ext-language_tools.js"></scirpt>
<script type="text/javascript" src="../../../resources/ckeditor2/ckeditor.js"></script>

<script>
	CKEDITOR.replace( 'writeContent2',{
	    height:'100px'
	});
	
	$('#commBtn').click(function(){
		if(CKEDITOR.instances["writeContent2"].getData().replace(/^\s+|\s+$/g, "") == "" ){
			alert('댓글을 입력해주세요')
			return
		}
		var mem_no = "${sessionScope.mem_no}"
		var fbc_text = CKEDITOR.instances["writeContent2"].getData();
		var fb_no = ${board.fb_no}
	
		$.ajax({
			url : '/free/commentInsert',
			dataType:'json',
			type:'POST',
			data:{mem_no:mem_no, fbc_text:fbc_text, fb_no:fb_no},
			success:function(result) {
				location.reload()
			}
	
		})
	})
	
	$('#deleteCom[data-id]').click(function(){
		var fbc_no = $(this).data('id')
		if($(this).text() == '삭제'){
			$.ajax({
				url:'/free/commentDelete',
				dataType: 'json',
				type: 'POST',
				data : {fbc_no : fbc_no},
				success:function(result){
					location.reload()
				}
			})
		} else {
			$(this).text('삭제')
			$('#commentCotent2[data-id="'+fbc_no+'"]').css("display", "block");
			$('#cke_updateCommentText'+fbc_no).css("display", "none");
		}
		
	
	})
	
	$('#updateCom[data-id]').click(function(){
		var fbc_no = $(this).data('id')
		if($('#cke_updateCommentText'+fbc_no).css("display") == "none"){
			$('#cke_updateCommentText'+fbc_no).css("display", "block");
		}
		if($('#deleteCom[data-id="'+fbc_no+'"]').text() == '삭제'){
			$('#deleteCom[data-id="'+fbc_no+'"]').text('취소')
			$('#commentCotent2[data-id="'+fbc_no+'"]').css("display", "none");
			CKEDITOR.replace( 'updateCommentText'+fbc_no,{
			    height:'100px'
			}); 
		} else {
			var fbc_text = CKEDITOR.instances["updateCommentText"+fbc_no].getData();
			$.ajax({
				url:'/free/commentUpdate',
				dataType: 'json',
				type: 'POST',
				data : {fbc_no:fbc_no, fbc_text:fbc_text},
				success:function(result){
					location.reload()
				}
			})
		}
		
	})

</script>
</html>