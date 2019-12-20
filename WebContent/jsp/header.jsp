<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>微雨电商服务平台</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<style>
			.main a {
				font-size: 14px;
				color: #337AB7;
			}
			.col-md-3{
				width: 550px;
			}
			.gategory {
				height: 50px;
			}
			.gategory:hover {
				border: 1px solid #fff;
				border-radius: 10px;
			}
			.navbar-nav {
				padding: 8px 0;
			}
		</style>
	<script>
		$(function(){
			/* 向服务端CategoryServlet->findAllCats发起ajax请求，服务端经过处理
			将所有分类信息以json格式的数据返回，获取到返回的所有分类绑定在页面的显示分类区域 */
			var url = "/store/CategoryServlet";
			var obj = {"method":"findAllCats"};
			$.post(url, obj, function(data){
				//获取到服务器响应回来的数据，经过观察data中存放的是一个json数组，遍历数组，动态的显示分类区域代码
				$(data).each(function(){
					var li = "<li style='font-size:15px;'><a href='/store/ProductServlet?method=findProductByCidWithPage&num=1&cid="+ this.cid +"' class='gategory'>"+ this.cname +"</a></li>";
					$("#myCats").append(li);
				});
			}, "json");
			$(".Search").click(function() {
				var result = $(".form-control").val();
				window.location.href="${pageContext.request.contextPath}/ProductServlet?method=searchProducts&result="+result;
			});
		});
	</script>
	</head>
<body style="background-color: #fff">
			<!--
            	描述：菜单栏
            -->
			<div class="main container-fluid">
				<div class="col-md-4">
					<img src="${pageContext.request.contextPath}/img/logo2.png" />
				</div>
				
				<div class="col-md-3" style="padding-top:20px; font-size: 14px; color: blue; float:right;">
					<ol class="list-inline">
					<c:if test="${empty loginUser}">
						<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
						<li><a href="${pageContext.request.contextPath}/UserServlet?method=registUI">注册</a></li>
						<li><a href="javascipt:void(0);" style="text-decoration: none">购物车</a></li>
						<li><a href="javascipt:void(0);" style="text-decoration: none">待付款</a></li>
						<li><a href="javascipt:void(0);" style="text-decoration: none">待收货</a></li>
						<li><a href="javascipt:void(0);" style="text-decoration: none">个人中心</a></li>
						<li><a href="javascipt:void(0);" style="text-decoration: none">联系客服</a></li>
					</c:if>
					<c:if test="${not empty loginUser}"> 
						<li style="color:#337ab7">欢迎 <font style="font-weight: 700; font-size:16px">${loginUser.username}</font></li>
						<li><a href="${pageContext.request.contextPath}/UserServlet?method=logOut">退出登录</a></li>
						<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
						<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrdersWithPage&num=1">待付款</a></li>
						<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findPayOrdersWithPage&num=1">待收货</a></li>
						<li><a href="${pageContext.request.contextPath}/jsp/personal.jsp">个人中心</a></li>
						<li><a href="${pageContext.request.contextPath}/">联系客服</a></li>
					</c:if>		
					</ol>
				</div>
			</div>
			<!--
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav" id="myCats">
							    <a class="navbar-brand product" href="/store/index.jsp" style="width: 90px">首页</a>
							</ul>
							<form class="navbar-form navbar-right" role="search">
							<div class="form-group" style="margin-top:8px;">
								<input type="text" class="form-control" placeholder="请输入搜索商品关键字" >
								<button type="button" class="btn btn-default Search">搜索</button>
							</div>
							</form>
						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>
</body>

</html>