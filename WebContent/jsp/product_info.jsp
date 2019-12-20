<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品详情信息</title>
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
			#box{
				cursor: pointer;
				width:400px;
				height: 300px;
				float: left;
				position: relative;
				overflow: hidden;
			}
			#bgmj {
				width: 170px;
				height: 170px;
				position: absolute;
				top: 60px;
				left: 125px;
			}
		</style>
		
	</head>
	<body>

		
			<!--
            	描述：菜单栏
            -->
			<%@include file="/jsp/header.jsp"%>


		<div class="container">
			<div class="row">
				<div style="border: 1px solid #e4e4e4;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;">
					<a href="${pageContext.request.contextPath}/">首页&nbsp;&nbsp;&gt;</a>
				</div>

				<div style="margin:0 auto;width:950px;">
				
					<div id="box">
						<img id = "bgmj" style="opacity: 1;" title="${product.pname}" src="${pageContext.request.contextPath}/${product.pimage}">
					</div>
					
					<div class="col-md-6">
						<div><strong>${product.pname}</strong></div>
						<div style="border-bottom: 1px dotted #dddddd;width:350px;margin:10px 0 10px 0;">
							<div>编号：${product.pid}</div>
						</div>
						<form id="myForm">
							<div style="margin:10px 0 10px 0;">商城价: <strong style="color:#ef0101;">￥：${product.shop_price}元/份</strong> 参 考 价： <del>￥${product.market_price}元/份</del>
							</div>
	
							<div style="margin:10px 0 10px 0;">促销: <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)" style="background-color: #f07373;">限时抢购</a> </div>
	
							<div style="padding:10px;border:1px solid #e7dbb1;width:330px;margin:15px 0 10px 0;;background-color: #fffee6;">
								<div style="margin:5px 0 10px 0;">白色</div>
	
								<div style="border-bottom: 1px solid #faeac7;margin-top:20px;padding-left: 10px;"><strong>购买数量:</strong>
									<input type="button" class="btn btn-default" value="-" style="font-weight: bolder;" id="btn-left"/>
									<input id="quantity" name="quantity" value="1" maxlength="4" size="10" type="text"/>
									<input type="button" class="btn btn-default" value="+" style="font-weight: bolder;" id="btn-right"/>
								</div>
									<input type="hidden" name="pid" value="${product.pid }">
								<div style="margin:20px 0 10px 0;;text-align: center;">
									<%--加入到购物车 --%>
									<a href="javascript:void(0)">
										<input id ="btnId" style="background: url('${pageContext.request.contextPath}/img/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;" value="加入购物车" type="button">
									</a></div>
							</div>
						</form>
					</div>
				</div>
				<div class="clear"></div>
				<div style="width:950px;margin:0 auto;">
					<div style="background-color:#d3d3d3;width:930px;padding:10px 10px;margin:10px 0 10px 0;">
						<h3>商品介绍</h3>
						<h4>&nbsp;&nbsp;&nbsp;&nbsp;${product.pdesc}</h4>
					</div>
									
				</div>

			</div>
		</div>

		<%@include file="/jsp/footer.jsp"%>
	
</body>
	<script>
		var box = document.getElementById("box");
		var bgmj = document.getElementById("bgmj");
		/*onmousemove 事件会在鼠标指针移动时发生*/
		box.onmousemove = function(ev) {
			var x = 3;
			bgmj.style.transform = "scale("+ x +")";
			bgmj.style.top = ev.clientY-260 + "px";
			bgmj.style.left = ev.clientX-260 + "px";
		}

		/*onmouseout 事件会在鼠标指针移出指定的对象时发生。*/
		box.onmouseout = function(){
			document.getElementById('bgmj').style.transform = "scale("+ 1 +")";
			document.getElementById('bgmj').style.top = 60 + "px";
			document.getElementById('bgmj').style.left = 125 + "px";
		}
	</script>
<script>
	$(function() {
		$("#btnId").click(function(){
			var form = document.getElementById("myForm");
			var quantity = $("#quantity").val();
			if(quantity>=1&&quantity<=200){
				$("#quantity").val(parseInt(quantity));
				form.action = "${pageContext.request.contextPath}/CartServlet?method=addCartItemToCart";
				form.method = "post";
				form.submit();
			}else{
				if(confirm("请重选商品数量")){
					$("#quantity").val(1);
				}
			}
			
		});
	});
	$("#btn-left").click(function(){
			var quantity = $("#quantity").val();
			if(quantity>1){
				$("#quantity").val(quantity-1);
			}
	});
	$("#btn-right").click(function(){
		$("#quantity").val(()=> {
			var quantity = parseInt($("#quantity").val());
			if(quantity<10000){
				return quantity+1;
			}
			else{
				return quantity;
			}
		});
	});
</script>
</html>