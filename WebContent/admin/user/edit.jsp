<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/userAdmin_update.action" method="post" >
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>用户基本信息</STRONG>
						</strong>
					</td>
				</tr>

				<tr>

					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						用户名称：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<div id="userAction_save_do_logonName" class="bg">${user.username}</div>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						密码：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<div id="userAction_save_do_logonName" class="bg">${user.password}</div>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						真实姓名：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<div id="userAction_save_do_logonName" class="bg">${user.name}</div>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						邮箱：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<div id="userAction_save_do_logonName" class="bg">${user.email}</div>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						电话：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<div id="userAction_save_do_logonName" class="bg">${user.telephone }</div>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						性别
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<div id="userAction_save_do_logonName" class="bg">${user.sex}</div>
					</td>
				</tr>
			
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>