<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<style>
	.checkCategory:hover{
		color:red;
		text-decoration: none;
		background-color: #fff;
	}
</style>
<script type="text/javascript">
	function addCategory() {
		window.location.href = "${pageContext.request.contextPath}/admin/category/add.jsp";
	}
</script>
<script>
	$(function(){
		$(".delete01").click(function(){
			if(confirm("确定删除?" +
					"(在删除之前请保持该类别没有商品)")){
				var cid=this.id;
				window.location.href="/store/AdminCategoryServlet?method=deleteCategory&cid=" + cid;	
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
				<td class="ta_01" align="center" bgColor="#afd1f3"><strong>分类列表</strong>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="right">
					<button type="button" id="add" name="add" value="添加"
						class="button_add" onclick="addCategory()">
						&#28155;&#21152;</button>

				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

							<td align="center" width="18%">序号</td>
							<td align="center" width="17%">分类名称</td>
							<td width="7%" align="center">编辑</td>
							<td width="7%" align="center">删除</td>
						</tr>
					<c:forEach items="${allCats }" var="category" begin="0" varStatus="vs">
						<tr onmouseover="this.style.backgroundColor = 'white'"
							onmouseout="this.style.backgroundColor = '#F5FAFE';" style="font-size: 14px;">
							<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">
								${vs.count }</td>
							<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
								<a href="javascript:void(0);" class="checkCategory">${category.cname }</a></td>
							<td align="center" style="HEIGHT: 22px"><a href="${pageContext.request.contextPath}/AdminCategoryServlet?method=editCategoryUI&cid=${category.cid}">
									<img src="${pageContext.request.contextPath}/img/admin/i_edit.gif" border="0" style="CURSOR: hand">
							</a></td>

							<td align="center" style="HEIGHT: 22px"><a href="javascript:void;" class="delete01" id="${category.cid }"> <img
									src="${pageContext.request.contextPath}/img/admin/i_del.gif" width="16"
									height="16" border="0" style="CURSOR: hand">
							</a></td>
						</tr>
					</c:forEach>	
					</table>
				</td>
			</tr>
		</TBODY>
	</table>
</body>

</HTML>

