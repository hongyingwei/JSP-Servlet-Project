<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>个人资料</title>
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
#dv{
	width: 100%;
	padding: 5px 0;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
}
</style>
<script>
	$(function(){
		$.ajax({
			type:"POST",
			url:"/store/UserServlet",
			data:{"method":"checkPersonal"},
			dataType:"json",
			async:false,//异步还是同步
			success:function(data){
			$("#userId").val(data.uid);	
			$("#username").val(data.username);
			$("#inputPassword3").val(data.password);
			$("#inputEmail3").val(data.email);
			$("#usercaption").val(data.name);
			var year = (parseInt(data.birthday.year/100)*2000)+(data.birthday.year%100);
			var month = data.birthday.month+1;
			if(month<10){
				month = "0"+month;
			}
			var day = data.birthday.date;
			if(day<10){
				day = "0"+day;
			}

			$("#birthday").val(year+"-"+month+"-"+day);
			
			var inlineRadio1 = $("#inlineRadio1").val(); 
			if(inlineRadio1 == data.sex){
				$("#inlineRadio1").attr("checked", true);
			}else{
				$("#inlineRadio2").attr("checked", true);
			}
			$("#telephone").val(data.telephone);
			$("#uid").val(data.uid);
			$("#state").val(data.state);
			$("#code").val(data.code);
		}});
		$(".change").click(function() {
			if(confirm("确定修改?")){
				$("#person").submit();
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
	<div class="container"
		style="width:100%;">
		<div class="row">
			<div class="col-md-2"></div>
			
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<div id="dv"><font style="color: black">个人资料</font>USER INFORMATION</div>
				<form class="form-horizontal" style="margin-top: 5px;"
					action="${pageContext.request.contextPath}/UserServlet?method=changeUser"
					method="post" id="person">
					<input type="hidden" name="uid" id="uid">
					<input type="hidden" name="state" id="state">
					<input type="hidden" name="code" id="code">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户Id</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="uid"
								id="userId">	
						</div>		
					</div>
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="username"
								id="username">	
						</div>		
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="password"
								id="inputPassword3">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" name="email"
								id="inputEmail3" placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">真实姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="name"
								id="usercaption">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio1" value="男">
								男
							</label> <label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio2" value="女"> 女
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" name="birthday" id="birthday">
						</div>
					</div>
					<div class="form-group">
						<label for="text" class="col-sm-2 control-label">电话</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="telephone" id="telephone">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="button" width="100" value="修改"
								style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;" class="change">
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>



	<%@include file="/jsp/footer.jsp"%>

</body>
</html>




