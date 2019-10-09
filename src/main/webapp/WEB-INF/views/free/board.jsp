<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session = "true" %>
<html>
<head>
	<title>자유게시판</title>
	<meta name="viewport" content="width=device-width, initial-scale=2">
</head>
<body>
        <jsp:include page="../header.jsp" flush="flase" />
        <div id="board">
                <div class="searcher" style="margin-bottom:10px;">
                        <div class="searchForm">
                                <form method="post" id="search">
                                        <div class="searchType">
                                                <select class="writeType2" id="langsel" name="searchType" style="border:1px solid #e5e5e5;">
                                                        <option value="null" <c:if test="${key.searchType == 'null'}">selected</c:if>>분류</option>
														<option value="title" <c:if test="${key.searchType == 'title'}">selected</c:if>>제목</option>
														<option value="writer" <c:if test="${key.searchType == 'writer'}">selected</c:if>>작성자</option>
                                                </select>
                                        </div>
                                        <div class="searchKey">
                                                <input type="text" class="searchKey2" name="search" value="${key.search}">
                                        </div>
                                        <div class="writeFinal" style="float:left;">
                                                <button class="writeButton">검색</button>
                                        </div>
                                </form>
                                <c:if test="${!empty sessionScope.id }">
									<div class="writeFinal" style="float:right;">
										<button class="writeButton"  onclick="location.href='free/write'">글쓰기</button>
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
							<c:when test="${!empty notice}">
								<c:forEach items="${notice}" var="nt">
									<tr class="tbl_body">
										<td><strong style="color:#000000">[${nt.fb_type}]</strong></td>
										<td class="tbl_title"><a href="javascript:read_page_process(${nt.fb_no})"><strong>${nt.fb_title}</strong></a></td>
										<td>관리자</td>
										<td><fmt:formatDate value="${nt.fb_reg}" pattern="yyyy-MM-dd"/></td>
										<td>${nt.fb_view}</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
                        <c:choose>
                                <c:when test="${!empty board}">
                                        <c:forEach items="${board}" var="brd">
                                                <tr class="tbl_body">
                                                        <td>${brd.fb_no}</td>
                                                        <td class="tbl_title"><a href="javascript:read_page_process(${brd.fb_no})"><strong>[${brd.fb_type}]</strong>${brd.fb_title}</a></td>
                                                        <td>${brd.mem_id}</td>
                                                        <td><fmt:formatDate value="${brd.fb_reg}" pattern="yyyy-MM-dd"/></td>
                                                        <td>${brd.fb_view}</td>
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
						<c:when test="${!empty notice}">
							<c:forEach items="${notice}" var="nt">
								<ul>
									<li style="margin-top:5px; margin-bottom:5px;"><a href="javascript:read_page_process(${nt.fb_no})"><strong>[${nt.fb_type}] <span>${nt.fb_title}</span></strong></a>
								</ul>
							</c:forEach>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${!empty board}">
							<c:forEach items="${board}" var="brd">
								<ul>
									<li><a href="javascript:read_page_process(${brd.fb_no})"><strong>[${brd.fb_type}]</strong><span>${brd.fb_title}</span></a></li>
									<li>
										<span>작성자 : ${brd.mem_id}</span>
										 | 
										<span>조회수 : ${brd.fb_view }</span>
										 | 
										<span><fmt:formatDate value="${brd.fb_reg}" pattern="yyyy-MM-dd"/></span>
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
</body>
<script>
	target="."+${pager.currentPageNo}
	$(target).css("color","#fff");
	$(target).css("background","#1278ed");
	$(target).css("border","#1px solid #4c8500");
	
	function page_locate(page, perpagenum) {
		href = "?currentPageNo=" + page + "&maxPost=" + perpagenum;
		href += "&search=" + "${key.search}" + "&searchType="
				+ "${key.searchType}";
		location.href = href;
	}
	
	function read_page_process(board_no) {
		href = "free/read?board_no=" + board_no;
		location.href = href;
	}
</script>
</html>