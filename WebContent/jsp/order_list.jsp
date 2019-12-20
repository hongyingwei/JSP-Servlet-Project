<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>我的订单</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			.infomation {
				width: 60px;
				height: 30px;
				font-size: 15px;
				float: right;
				/* border:1px solid black; */
				
				border-radius: 15px; 
				background-color: #FFF;
				line-height: 30px;
				text-align: center;
				
			}
			.infomation:hover {
				color: red;
				text-decoration: none;
				background-color: #dff0d8;
			}
			.money {
				font-size: 15px;
				height: 30px;
				position: absolute;
				right: 200px;
				bottom: 3px;
			}
			.active td a{
				color: black;
			}
			
			.active td a:hover{
				text-decoration: none;
				color:red;
			}
		</style>
		<script>
			$(function() {
				$(".complete01").click(function() {
					if(confirm("确定签收？")){
						var oid = this.id;
						var currentPageNum = ${page.currentPageNum};
						window.location.href="${pageContext.request.contextPath}/OrderServlet?method=completeOrder&oid="+oid +"&num="+currentPageNum;
					}
				});
			});
		</script>
	</head>
<body>
			<!--
            	描述：菜单栏
            -->
		<%@include file="/jsp/header.jsp"%>
		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
					<c:forEach items="${page.list}" var="order">
						<tbody>
							<tr class="success">
								<th colspan="5" style="position: relative;">
									订单编号:${order.oid } 
									<div class="money">总金额:${order.total}元</div>
									<c:if test="${order.state==1 }">
										<a href="${pageContext.request.contextPath}/OrderServlet?method=deleteOrderByOid&oid=${order.oid}&num=${page.currentPageNum}" class="infomation" style="margin-left: 10px; width: 70px;"> 删除订单 </a>
									</c:if>
									<c:if test="${order.state==1 }">
										<a href="${pageContext.request.contextPath}/OrderServlet?method=findOrderByOid&oid=${order.oid}" class="infomation"> 请付款 </a>
									</c:if>
									<c:if test="${order.state==2 }">
										<a href="" class="infomation" style="margin-left: 10px; width: 70px;"> 联系客服 </a>
										<div class="infomation" style="color:#337AB7"> 未发货 </div>
									</c:if>
									<c:if test="${order.state>2 }">
										<a href="javascript:void(0);" class="infomation complete01" id="${order.oid }" style="margin-left: 10px; width: 70px;">确定签收 </a>  <!-- 确定签收之后，同时交易完成 -->
										<div class="infomation" style="color:#337AB7;"> 已发货 </div>
									</c:if>
								</th>
							</tr>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
							<c:forEach items="${order.list }" var="item">
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
								</td>
								<td width="30%">
									<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${item.product.pid}" > ${item.product.pname}</a>
								</td>
								<td width="20%">
									￥ ${item.product.shop_price}
								</td>
								<td width="10%">
									${item.quantity}
								</td>
								<td width="15%">
									<span class="subtotal">￥ ${item.total}</span>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</c:forEach>
					</table>
				</div>
			</div>
			<%@include file="/jsp/pageFile.jsp"%>
		</div>

		<%@include file="/jsp/footer.jsp"%>
	</body>

</html>