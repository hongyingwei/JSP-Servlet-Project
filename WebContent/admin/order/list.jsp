<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>

<script type="text/javascript">
	$(function() {
		$(".sendOrder").click(function() {
			var oid = this.id;
			var currentPageNum = ${page.currentPageNum};
			window.location.href = "${pageContext.request.contextPath}/AdminOrderServlet?method=editOrders&oid="+ oid + "&currentPageNum=" + currentPageNum;
			});
	});
</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单列表</strong>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="10%">序号</td>
								<td align="center" width="10%">订单编号</td>
								<td align="center" width="10%">订单金额</td>
								<c:if test="${flag==4 }">
									<td align="center" width="10%">订单时间</td>
								</c:if>
								<c:if test="${flag!=4 }">
									<td align="center" width="10%">收货人</td>
								</c:if>
								<td align="center" width="10%">订单状态</td>
							</tr>
							<c:forEach items="${page.list }" var="order" varStatus="statu"
								begin="0">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${statu.count}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${order.oid}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${order.total } 元</td>

									<c:if test="${order.state==5 }">
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">${order.ordertime}</td>
									</c:if>
									<c:if test="${order.state!=5 }">
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">${order.name}</td>
									</c:if>

									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><c:if test="${order.state>1}">
											<c:if test="${flag==1 }">已付款, 未签收</c:if>
										</c:if> <c:if test="${order.state==2}">
											<c:if test="${flag==2 }">
												<button type="button" class="sendOrder" id="${order.oid }">发货</button>
											</c:if>
										</c:if> <c:if test="${order.state==4}">
											<c:if test="${flag==3 }">待签收</c:if>
										</c:if> <c:if test="${order.state==5}">
											<c:if test="${flag==4 }">已签收</c:if>
										</c:if></td>

								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<%@ include file="/admin/pageFile.jsp"%>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

