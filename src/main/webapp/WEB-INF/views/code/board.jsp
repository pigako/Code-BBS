<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
	<title>코드 공유 게시판</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
        <jsp:include page="../header.jsp" flush="flase" />
        <div id="board">
                <div class="searcher" style="margin-bottom:10px;">
                        <div class="searchForm">
                                <form method="get" action="/" id="search">
                                        <div class="searchType">
                                                <select class="writeType2" id="langsel" name="language" style="border:1px solid #e5e5e5;">
                                                        <option value="none" >Language</option>
                                                        <c:forEach items="${langs}" var="lang">
                                                                <c:choose>
                                                                        <c:when test="${search.language==lang.cb_lang}">
                                                                                <option value="${lang.cb_lang}" selected>${lang.cb_lang}</option>
                                                                                <!-- <script>$("#langname")[0].innerText="${lang.cb_lang}"</script> -->
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                                <option value="${lang.cb_lang}">${lang.cb_lang}</option>
                                                                        </c:otherwise>
                                                                </c:choose>
                                                        </c:forEach>
                                                </select>
                                        </div>
                                        <div class="searchType">
                                                <select class="writeType2" name="type" style="border:1px solid #e5e5e5;">
                                                        <option value="title" <c:if test="${search.type == 'title'}">selected</c:if>>제목</option>
                                                        <option value="id" <c:if test="${search.type == 'id'}">selected</c:if>>작성자</option>
                                                </select>
                                        </div>
                                        <div class="searchKey">
                                                <input type="text" class="searchKey2" name="search" value="${search.search}">
                                        </div>
                                        <div class="writeFinal" style="float:left;">
                                                <button class="writeButton">검색</button>
                                        </div>
                                </form>
                                <c:if test='${session == "yes"}'>
                                        <div class="writeFinal" style="float:right;">
                                                <button class="writeButton"  onclick="location.href='code/write'">글쓰기</button>
                                        </div>
                                </c:if>
                        </div>
                </div>
                <table class="tbl">
                        <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일자</th>
                                <th>조회수</th>
                        </tr>
                        <c:choose>
                                <c:when test="${!empty page}">
                                        <c:forEach items="${page}" var="brd">
                                                <tr class="tbl_body">
                                                        <td>${brd.cb_no}</td>
                                                        <td class="tbl_title"><a href="javascript:read_page_process(${brd.cb_no})"><strong>[${brd.cb_lang}]</strong>${brd.cb_title}</a></td>
                                                        <td>${brd.mem_id}</td>
                                                        <td><fmt:formatDate value="${brd.cb_reg}" pattern="yyyy-MM-dd"/></td>
                                                        <td>${brd.cb_view}</td>
                                                </tr>
                                        </c:forEach>
                                </c:when>
                                <c:otherwise>
                                        <tr style="text-align: center; height: 35px;">
                                                <td colspan="6">게시글이 없습니다.</td>
                                        </tr>
				</c:otherwise>
                        </c:choose>
                </table>
                <div class="mobile">
                        <c:choose>
                                <c:when test="${!empty page}">
                                        <c:forEach items="${page}" var="brd">
                                                <ul>
                                                        <li><a href="javascript:read_page_process(${brd.cb_no})"><strong>[${brd.cb_lang}]</strong><span>${brd.cb_title}</span></a></li>
                                                        <li>
                                                                <span>작성자 : ${brd.mem_id}</span>
                                                                 | 
                                                                <span>조회수 : ${brd.cb_view }</span>
                                                                 | 
                                                                <span><fmt:formatDate value="${brd.cb_reg}" pattern="yyyy-MM-dd"/></span>
                                                        </li>
                                                </ul>
                                        </c:forEach>
                                </c:when>
                        </c:choose>
                        
                </div>
                <div class="paging">
                        <c:if test="${pager.numberOfRecords != 0}">
                                <div style="width:50%;margin:0 auto;text-align:center;">
                                        <c:if test="${pager.currentPageNo != 1}">
                                                <a class="direction prev" href="javascript:page_locate(${pager.prevPageNo}, ${pager.maxPost})"></a>
                                        </c:if>&nbsp;
                                        <c:forEach var="i" begin="${pager.startPageNo}" end="${pager.endPageNo}" step="1">
                                                <c:choose>
                                                        <c:when test="${i != pager.currentPageNo}">
                                                                <a class="${i}" href="javascript:page_locate(${i}, ${pager.maxPost})">
                                                                        <c:out value="${i}"></c:out>
                                                                </a>&nbsp;
                                                        </c:when>
                                                        <c:otherwise>
                                                                <a class="${i}" href="javascript:page_locate(${i}, ${pager.maxPost})">
                                                                        <c:out value="${i}"></c:out></a>
                                                                &nbsp;
                                                        </c:otherwise>
                                                </c:choose>
                                        </c:forEach>
                                        <c:if test="${pager.currentPageNo < pager.nextPageNo}">
                                                <a class="direction next" href="javascript:page_locate(${pager.nextPageNo}, ${pager.maxPost})"></a>
                                        </c:if>
                                </div>
                        </c:if>
                </div>
        </div>
	<script>
                target="."+${pager.currentPageNo}
		$(target).css("color","#fff");
		$(target).css("background","#1278ed");
                $(target).css("border","#1px solid #4c8500");
		function page_locate(page, perpagenum) {
			href = "?currentPageNo=" + page + "&maxPost=" + perpagenum;
                        href += "&search=" + "${search.search}" + "&type="+ "${search.type}";
                        if("${search.language}"){
                                href += "&language=" +"${search.language}"
                        }else{
                                href += "&language=" +"none"
                        }
			location.href = href;
		}
		function read_page_process(bno) {
			href = "code/read?cb_no=" + bno;
			href += "&currentPageNo=" + ${pager.currentPageNo}+"&maxPost=" + ${pager.maxPost};
                        href += "&search=" + "${search.search}" + "&type="+ "${search.type}";
                        if("${search.language}"){
                                href += "&language=" +"${search.language}"
                        }else{
                                href += "&language=" +"none"
                        }
			location.href = href;
                }
                var langsel = $("select#langsel");
		langsel.change(function(){
		        var select_name = $(this).children("option:selected").text();
			$(this).siblings("label").text(select_name);
			console.log(select_name);
			$("#search").submit();
		});
	</script>
</body>
</html>