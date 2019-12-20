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
</script>
<script>
	$(function() {
		$("#search").click(function() {
			var value = $(".form-control").val();
			window.location.href="/store/AdminSearchServlet?method=searchOrder&value="+value;
		});
		$(".sendOrder2").click(function() {
			var oid = this.id;
			var m;
			$.ajax({
				type:"post",
				url: "/store/AdminSearchServlet",
				data: {"method":"editOrderStateByOid", "oid":oid},
				async: false, //同步
				dataType: "json",
				success: function(data) {
					m = data;
				}
			});
			if(m==1){
				$(this).next().text("待签收");
				$(this).remove();
			}
		});
	});
</script>
</HEAD>
<body>
	<br>
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单查询列表</strong>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input-group col-md-3"
							style="margin-top: 0px positon:relative">
							<input type="text" class="form-control"
								placeholder="请输入订单编号或者收货人" /> <span class="input-group-btn">
								<button class="btn btn-info btn-search" id="search">查找</button>
							</span>
						</div>
					</td>
				</tr>
				
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="10%">订单编号</td>
								<td align="center" width="10%">订单时间</td>
								<td align="center" width="10%">收货人</td>
								<td align="center" width="10%">收货地址</td>
								<td align="center" width="10%">电话</td>
								<td align="center" width="10%">订单状态</td>
							</tr>
							<c:forEach items="${allOrder }" var="order">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="17%">${order.oid}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${order.ordertime}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${order.name}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${order.address}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${order.telephone}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="17%">
									<c:if test="${order.state==2}">
											<input type="button" class="sendOrder2" id="${order.oid }" value="发货">
											<span></span>
									</c:if>
									<c:if test="${order.state==4}">
										待签收
									</c:if>
								</td>
							</tr>
						</c:forEach>
						</table>
					</td>
				</tr>
				<c:forEach items="${allOrder }" var="order">
				<tr>
					<div style="text-align: right; margin-right: 120px;">
					<table class="table table-bordered">
						<tbody>

							<tr class="warning">
								<th colspan="5">订单详情
									<div style="text-align: right; margin-right: 120px; float:right;">
										订单总金额: <strong style="color: #ff6600;">￥${order.total}元</strong>
								</div>
								</th>
								
							</tr>
							<tr class="warning">
								<td>图片</td>
								<td>商品</td>
								<td>价格</td>
								<td>数量</td>
								<td>小计</td>
							</tr>
						
							<c:forEach items="${order.list}" var="item">
								<tr class="active">
									<td width="60" width="40%"><input type="hidden" name="id"
										value="22"> <img
										src="${pageContext.request.contextPath}/${item.product.pimage}"
										width="70" height="60"></td>
									<td width="30%"><a
										href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${item.product.pid}">
											${item.product.pname}</a></td>
									<td width="20%">￥ ${item.product.shop_price}</td>
									<td width="10%">${item.quantity}</td>
									<td width="15%"><span class="subtotal">￥
											${item.total}元</span></td>
								</tr>
							</c:forEach>
						
						</tbody>
					</table>
				</div>
				
			</tr>
			</c:forEach>
			</TBODY>
		</table>
</body>
</HTML>