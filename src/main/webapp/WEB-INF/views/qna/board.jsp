<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<head>
	<title>질문 게시판</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
        <jsp:include page="../header.jsp" flush="flase" />
        <div id="board">
                <div class="searcher" style="margin-bottom:10px;">
                        <div class="searchForm">
                                <form method="get" action="/qna" id="search">
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
                                                <button class="writeButton"  onclick="location.href='qna/write'">글쓰기</button>
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
                                                        <td>${brd.qb_no}</td>
                                                        <td class="tbl_title"><a href="javascript:read_page_process(${brd.qb_no})">${brd.qb_title}</a></td>
                                                        <td>${brd.mem_id}</td>
                                                        <td><fmt:formatDate value="${brd.qb_reg}" pattern="yyyy-MM-dd"/></td>
                                                        <td>${brd.qb_view}</td>
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
                                                        <li><a href="javascript:read_page_process(${brd.qb_no})"><span>${brd.qb_title}</span></a></li>
                                                        <li>
                                                                <span>작성자 : ${brd.mem_id}</span>
                                                                 | 
                                                                <span>조회수 : ${brd.qb_view }</span>
                                                                 | 
                                                                <span><fmt:formatDate value="${brd.qb_reg}" pattern="yyyy-MM-dd"/></span>
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
			href = "/qna?currentPageNo=" + page + "&maxPost=" + perpagenum;
                        href += "&search=" + "${search.search}" + "&type="+ "${search.type}";
                        location.href=href;
		}
		function read_page_process(bno) {
			href = "/qna/read?qb_no=" + bno;
			href += "&currentPageNo=" + ${pager.currentPageNo}+"&maxPost=" + ${pager.maxPost};
                        href += "&search=" + "${search.search}" + "&type="+ "${search.type}";
			location.href = href;
                }

	</script>
</body>
</html>