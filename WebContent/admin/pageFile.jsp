<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<tr align="center">
		<td colspan="7">第${ page.currentPageNum }/${ page.totalPageNum }页
			&nbsp; &nbsp; &nbsp; 总记录数:${ page.totalRecords } &nbsp; 每页显示:${ page.pageSize }
			条 <c:if test="${ page.currentPageNum != 1 }">
				<a
					href="${ pageContext.request.contextPath }/${page.url}&currentPageNum=1">首页</a>|
								<a
					href="${ pageContext.request.contextPath }/${page.url}&currentPageNum=${ page.currentPageNum - 1}">上一页</a>|
							</c:if> &nbsp; &nbsp; <c:forEach var="i" begin="1"
				end="${ page.totalPageNum }">
				<c:if test="${ page.currentPageNum == i }">
					<font style="color: red">[${ i }]</font>
				</c:if>
				<c:if test="${ page.currentPageNum != i }">
					<a
						href="${ pageContext.request.contextPath }/${page.url}&currentPageNum=${ i}">[${ i }]</a>
				</c:if>
			</c:forEach> &nbsp; &nbsp; <c:if
				test="${ page.currentPageNum != page.totalPageNum }">
				<a
					href="${ pageContext.request.contextPath }/${page.url}&currentPageNum=${ page.currentPageNum + 1}">下一页</a>|
								<a
					href="${ pageContext.request.contextPath }/${page.url}&currentPageNum=${ page.totalPageNum}">尾页</a>|
							</c:if>
		</td>
	</tr>
</body>
</html>