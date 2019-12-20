<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>搜索结果</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			.col-md-2{
				width:202px;
				height: 230px;
			}
			.product:hover {
				box-shadow:2px 2px 10px #909090;
			}
		</style>
	</head>

	<body>
		
			<!--
            	描述：菜单栏
            -->
		<%@include file="/jsp/header.jsp"%>
		<c:if test="${empty product}">
			<div class="row" style="width:1210px;margin:0 auto;">
				<div class="col-md-12">
					<h3>暂无您要的商品信息</h3>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty product }">
			<div class="row" style="width:1210px;margin:0 auto;">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><h3>查找商品信息</h3></li>
				</ol>
			</div>
			<c:forEach items="${product }" var="product">
				<div class="col-md-2 product" style="margin-top: 20px;">
					<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${product.pid}">
						<img src="${pageContext.request.contextPath}/${product.pimage}" width="170" height="170" style="display: inline-block;">
					</a>
					<p><a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${product.pid}" style='color:green'>${product.pname }</a></p>
					<p><font color="#FF0000">商城价：&yen;${product.shop_price }</font></p>
				</div>
			</c:forEach>
		</div>
		</c:if>
		<%@include file="/jsp/footer.jsp"%>
	</body>

</html>