<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

.container .row div {
	/* position:relative;
			 float:left; */
	
}

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}

.span01 {
	margin-top: 4px;
}

.p {
	font-size: 14px;
	font-weight: 700;
}
</style>
<script>
	function checkUserName() {
		var username = $("#username").val();
		if (username == '') {
			$(".span01").html(
					"<font style='color:red' class='p'>用户名不能为空</font>");
		} else {
			$.post("${pageContext.request.contextPath}/UserServlet?method=checkUserName",
							{
								username : username
							},
							function(data, statu) {
								if (data == 2) {
									$(".span01")
											.html(
													"<font style='color:red' class='p'>用户名已存在</font>");
								} else {
									$(".span01")
											.html(
													"<font style='color:green' class='p'>用户名正确</font>");
								}
							});
		}
	};
	function checkPassword() {
		var password = $("#inputPassword3").val();
		var repassword = $("#confirmpwd").val();
		if (password == '' || repassword == '') {
			$(".span02").html("<font style='color:red' class='p'>密码不能为空</font>");
		} else if (password != repassword) {
			$(".span02").html("<font style='color:red' class='p'>密码不正确</font>");
		} else if (password == repassword) {
			$(".span02").html("<font style='color:green' class='p'>密码正确</font>");
		}
	};
	function checkTelephone() {
		var telephone = $("#telephone").val();
		if(telephone==''){
			$(".span04")
			.html("<font style='color:red' class='p'>电话不能为空</font>");
			return;
		}
		var telephoneReg = /^[1][3,4,5,7,8][0-9]{9}$/; //正则判断电话号码的正确性
		if (!telephoneReg.test(telephone)) {
			$(".span04")
					.html("<font style='color:red' class='p'>电话填写有误</font>");
		} else {
			$(".span04").html(
					"<font style='color:green' class='p'>电话填写正确</font>");
		}
	};
	function checkEmail(){
		var email = $("#inputEmail3").val();
		if(email==''){
			$(".span03")
			.html("<font style='color:red' class='p'>邮箱不能为空</font>");
			return;
		}
		var emailReg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
		if(!emailReg.test(email)){
			$(".span03")
			.html("<font style='color:red' class='p'>邮箱填写有误</font>");
		} else {
			$(".span03").html("<font style='color:green' class='p'>邮箱填写正确</font>");
		}
	};
	function buttonInformation(){
		for(var i = 1; i<=4; i++){
			var $span = $(".span0"+i).text();
			if($span.indexOf("正确")==-1 || $span.indexOf("不正确")!=-1){
				$(".span05").html("<font style='color:red' class='p'>信息填写有误或者不完整</font>");
				return;
			}
		};
		$(".span05").html("<font style='color:green' class='p'>信息填写正确</font>");
		$("#submitInformation").submit();
	};
</script>
</head>
<body>
	<!--
            	描述：菜单栏
            -->
	<%@include file="/jsp/header.jsp"%>
	<div class="container"
		style="width:100%;background:url('${pageContext.request.contextPath}/img/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<form class="form-horizontal" style="margin-top: 5px;"
					action="${pageContext.request.contextPath}/UserServlet?method=userRegist"
					method="post" id="submitInformation">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="username"
								id="username" placeholder="请输入用户名" onblur="checkUserName()">
						</div>
						<div class="span01"></div>

					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" name="password"
								id="inputPassword3" placeholder="请输入密码" onblur="checkPassword()">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd"
								placeholder="请输入确认密码" onblur="checkPassword()">
						</div>
						<div class="span02"></div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" name="email"
								id="inputEmail3" placeholder="Email" onblur="checkEmail()">
						</div>
						<div class="span03"></div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="name"
								id="usercaption" placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio1" value="男" checked="checked">
								男
							</label> <label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio2" value="女"> 女
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" name="birthday">
						</div>
					</div>
					<div class="form-group">
						<label for="text" class="col-sm-2 control-label">电话</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="telephone"
								id="telephone" onblur="checkTelephone()">
						</div>
						<div class="span04"></div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="button" width="100" value="注册" 
								border="0"
								style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;" onclick="buttonInformation()" />
				    	<span class="span05"></span>
						</div>
						
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>
	<%@include file="/jsp/footer.jsp"%>

</body>
</html>




