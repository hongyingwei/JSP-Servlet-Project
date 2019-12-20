<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>关于我们</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
	</head>
	<script>
		$(function() {
			$("#check").click(function() {
				$.ajax({
					type:"POST",
					url:"/store/UserServlet",
					data:{"method":"active", "code":$("#code").val()},
					dataType:"json",
					async:true,//异步还是同步
					success: function(data) {
						if(data==1){
							$(".success").html("<font>激活成功</font>");
						}else{
							$(".success").html("<font>激活失败</font>");
						}
					}
				});
			});
		});
	</script>
	<body>
		<%@include file="/jsp/header.jsp"%>
		<div class="container">

			<%--包含导航条 --%>
			<%-- <%@include file="/jsp/header.jsp" %> --%>

			<div class="container">
				<h1>${msg}</h1><br/>
				<label for="username" class="col-sm-2 control-label">激活码：</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="username"
								id="code" placeholder="请输入校验码">
						<div class="col-sm-offset-2 col-sm-10">
								<input type="button" width="100" value="效验" id="check" name="submit"
								style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
						</div>
						</div>
						<div class="success"></div>
			</div>

		</div>
		<!--
            	描述：页脚部分
            -->
		<%@include file="/jsp/footer.jsp"%>

	</body>
</html>