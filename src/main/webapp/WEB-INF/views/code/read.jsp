<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
	<head>
		<title>코드 공유 게시판 : 게시글</title>
                <script src="//code.jquery.com/jquery.min.js"></script>
                <script src="/resources/ckeditor/ckeditor.js"></script>
                <!-- <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.7.0/styles/default.min.css"> -->
                <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.7.0/highlight.min.js"></script>
                <script>hljs.initHighlightingOnLoad();</script>
                <link rel="stylesheet" href="/resources/ckeditor/plugins/codesnippet/lib/highlight/styles/monokai_sublime.css" type="text/css">
	</head>
	<body>
                <jsp:include page="../header.jsp" flush="flase" />
                <div id="detailBoard">
                        <div class="info">
                                <h2>
                                        <span class="cate">코드게시판</span>
                                        <span class="title">[${post.cb_lang}] ${post.cb_title}</span>
                                </h2>
                                <div class="writer">
                                        <strong>${post.mem_id}</strong><span>님</span>
                                </div>
                        </div>
                        <div class="detailContent">
                                <div class="header">
                                        <span class="date"><fmt:formatDate value="${post.cb_reg}" pattern="yyyy-MM-dd"/></span>
                                        <span class="view">조회수 : ${post.cb_view}</span>
                                        <span class="space">space</span>
                                </div>
                                <div class="cont ck">
                                        <p><c:out value="${post.cb_text}" escapeXml="false"></c:out></p>
                                </div>
                                <div class="foot" style="border-top:1px solid #337ab7">
                                        <span><a href="javascript:page_locate(${cpa.currentPageNo}, ${cpa.maxPost})">목록</a></span>
                                        <c:if test="${id==post.mem_id}">
                                                <span><a href="/code/modify?cb_no=${post.cb_no}">수정</a></span>
                                                <span><a href="/code/delete?cb_no=${post.cb_no}">삭제</a></span>
                                        </c:if>
                                </div>
                        </div>        
                        <div id="comment">
                                <div class="header">
                                        <h5 class="header1">
                                                <span>댓글</span>
                                                <span class="tot_com">${totCom}</span>
                                                <span>개</span>
                                        </h5>
                                </div>
                                <div class="cont" id="com_list">
                                        <c:choose>
                                                <c:when test='${!empty comment}'>
                                                        <c:forEach items="${comment}" var="cmm">
                                                                <div class="comm" id="${cmm.cbc_no}" data-id="${cmm.cbc_no}">
                                                                        <div class="commHeader">
                                                                                <div class="commId">${cmm.mem_id}</div>
                                                                                <div class="commDate"><fmt:formatDate value="${cmm.cc_reg}" pattern="yyyy-MM-dd"/></div>
                                                                                <c:if test="${id==cmm.mem_id}">
                                                                                        <div class="commUpdate"><span><a data-id="${cmm.cbc_no}" onclick="update_comment(this)">수정</a></span></div>
                                                                                        <div class="commDelete"><span><a data-id="${cmm.cbc_no}" onclick="delete_comment(this)">삭제</a></span></div>
                                                                                </c:if>
                                                                                <span class="space">space</span>
                                                                        </div>
                                                                        <div class="commContent" >
                                                                                <div id="commentCotent2" data-id="${cmm.cbc_no}" class="ck">
                                                                                        ${cmm.cc_text}
                                                                                </div>
                                                                                <div id="commentCotent3" style="display:none" data-id="${cmm.cbc_no}">
                                                                                </div>	
                                                                                <!-- <textarea id="updateCommentText${cmm.cbc_no}" name="board_content" cols="108" rows="15" style="display:none;" data-id="${cmm.cbc_no}">${cmm.cc_text }</textarea> -->
                                                                        </div>
                                                                </div>
                                                        </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                        <div class="nocomment">
                                                                <label>댓글이 없습니다.</label>
                                                        </div>					
                                                </c:otherwise>
                                        </c:choose>
                                </div>
                                <c:choose>
                                        <c:when test='${session == "yes"}'>
                                                <div class="writeMid">
                                                        <div class="writeContent">
                                                                <textarea id="write_comment" name="board_content" cols="108" rows="15"></textarea>
                                                        </div>
                                                </div>
                                                <div class="writeFinal">
                                                        <button id="commBtn" class="writeButton" type="button" onclick="write_comment()">댓글 쓰기</button>
                                                </div>     
                                        </c:when>
                                </c:choose>  
                        </div>
                        <!-- <div class="com_bot"></div> -->
                </div>
		<script>
                        cb_no="${post.cb_no}"
                        check=1
                        up=0
                        if("${session}"=="yes"){
                                CKEDITOR.replace( 'write_comment',{
                                        height:'100px'
                                });
                        }
                        function page_locate(page, perpagenum) {
                                if(page!=0){
                                        href = location.protocol+"/?currentPageNo=" + page + "&maxPost=" + perpagenum;
                                        href += "&search=" + "${search.search}" + "&type="+ "${search.type}";
                                        href += "&language=" +"${search.language}"
                                }else{
                                        href = location.protocol+"/?currentPageNo=1&maxPost=10";
                                        href += "&search=" + "${search.search}" + "&type="+ "${search.type}";
                                        href += "&language=none"
                                }
                                location.href = href;
                        }
                        function write_comment(){
                                cc_text=CKEDITOR.instances.write_comment.getData()
                                $.ajax({
                                        url:'/code/commentInput',
                                        dataType:'json',
                                        type:'post',
                                        data:{cb_no:cb_no,cc_text:cc_text},
                                        success:function(result){
                                                if(result){
                                                        CKEDITOR.instances.write_comment.setData('')
                                                        get_comment()
                                                        alert("댓글이 작성되었습니다")
                                                        
                                                }else{
                                                        alert("댓글 작성이 실패하였습니다")
                                                }
                                        }
                                })
                        }
                        function update_comment(btn){
                                text=$(btn).text()
                                cbc_no=$(btn).data('id')
                                if(!check){
                                        if(up!=cbc_no){
                                                alert("수정은 동시에 할 수 없습니다.")
                                                return
                                        }
                                }
                                if(text=='수정'){
                                        $(btn).text('완료')
                                        $(btn).parent().parent().next().children().children().text('취소')
                                        body=$(btn).parent().parent().parent().next().children()
                                        text=$(body).eq(0).html()
                                        html='<textarea id="comup">'+text+'</textarea>'
                                        $(body).eq(0).css('display','none')
                                        $(body).eq(1).css('display','block')
                                        $(body).eq(1).append(html)
                                        // CKEDITOR.replace('comup');
                                        CKEDITOR.replace( 'comup', {
                                                on: {
                                                        instanceReady: function( evt ) {
                                                                console.log(CKEDITOR.instances.comup.getData())
                                                                highlight()
                                                        }
                                                }
                                        } );
                                        check=0
                                        up=cbc_no
                                }else{
                                        body=CKEDITOR.instances.comup.getData()
                                        $.ajax({
                                                url:'/code/commentModify',
                                                dataType:'json',
                                                type:'post',
                                                data:{cbc_no:cbc_no,cc_text:body},
                                                success:(result)=>{
                                                        if(result){
                                                                alert('댓글이 수정되었습니다')
                                                                delete_comment($(btn).parent().parent().next().children().children())
                                                                get_comment()
                                                                check=1
                                                        }else{
                                                                alert('댓글이 수정되지않았습니다')
                                                                delete_comment($(btn).parent().parent().next().children().children())
                                                                get_comment()
                                                                check=1
                                                        }
                                                }
                                        })
                                }
                        }
                        function delete_comment(btn){
                                text=$(btn).text()
                                cbc_no=$(btn).data("id")
                                check=1
                                if(text=="삭제"){
                                        $.ajax({
                                                url:'/code/commentDelete',
                                                dataType:'json',
                                                type:'post',
                                                data:{cbc_no:cbc_no},
                                                success:function(result){
                                                        if(result){
                                                                get_comment()
                                                                alert("댓글이 삭제되었습니다")
                                                        }else{
                                                                alert("댓글 작성이 실패하였습니다")
                                                        }
                                                }
                                        })
                                }else{
                                        $(btn).text('삭제')
                                        $(btn).parent().parent().prev().children().children().text('수정')
                                        body=$(btn).parent().parent().parent().next().children()
                                        body.eq(0).css('display','block')
                                        body.eq(1).css('display','none')
                                        body.eq(1).empty()
                                }
                        }
                        function get_comment(){
                                $("#com_list").empty()
                                var data=[]
                                $.ajax({
                                        url:"/code/commentRead",
                                        dataType:"json",
                                        type:"post",
                                        data:{cb_no:cb_no},
                                        success:function(result){
                                                $(".tot_com").text(result.result.length)
                                                if(result.result.length>0){
                                                        for(i=0;i<result.result.length;i++){
                                                                html='<div class="comm" id="'+result.result[i].cbc_no+'" data-id="'+result.result[i].cbc_no+'">'
                                                                html+='<div class="commHeader">'
                                                                html+='<div class="commId">'+result.result[i].mem_id+'</div>'
                                                                html+='<div class="commDate">'+getFormatDate(new Date(result.result[i].cc_reg))+'</div>'
                                                                if("${mem_no}"==result.result[i].mem_no){
                                                                        html+='<div class="commUpdate"><span><a data-id="'+result.result[i].cbc_no+'" onclick="update_comment(this)">수정</a></span></div>'
                                                                        html+='<div class="commDelete"><span><a data-id="'+result.result[i].cbc_no+'" onclick="delete_comment(this)">삭제</a></span></div>'
                                                                }
                                                                html+='<span class="space">space</span></div>'
                                                                html+='<div class="commContent" >'
                                                                html+='<div id="commentCotent2" data-id="'+result.result[i].cbc_no+'" class="ck">'
                                                                html+=result.result[i].cc_text+'</div>'
                                                                html+='<div id="commentCotent3" data-id="'+result.result[i].cbc_no+'" style="display:none"></div>'
                                                                // html+='<textarea id="updateCommentText'+result.result[i].cbc_no+'" name="board_content" cols="108" rows="15" style="display:none;" data-id="'+result.result[i].cbc_no+'">'+result.result[i].cc_text+'</textarea>'
                                                                html+='</div></div>'
                                                                $("#com_list").append(html)
                                                                highlight()
                                                        }
                                                }else{
                                                        html='<div class="nocomment"><label>댓글이 없습니다.</label></div>'
                                                        $("#com_list").append(html)
                                                }
                                        }
                                })
                        }
                        function highlight(){
                                document.querySelectorAll('code').forEach((block) => {
                                        console.log(block)
                                        hljs.highlightBlock(block);
                                });
                        }
                        function getFormatDate(date){ 
                                var year = date.getFullYear(); 
                                var month = (1 + date.getMonth()); 
                                month = month >= 10 ? month : '0' + month; 
                                var day = date.getDate();  
                                day = day >= 10 ? day : '0' + day; 
                                return year + '-' + month + '-' + day; 
                        }            
		</script>
	</body>
</html>
