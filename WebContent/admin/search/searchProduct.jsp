<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script language="javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
		rel="stylesheet" type="text/css" />
		<script>
			$(function(){
				$.ajax({
					type: "post",
					url: "/store/CategoryServlet",
					data: {"method": "findAllCats"},
					dataType:"json",
					async:false,//异步还是同步
					success: function(data){ 
						$(data).each(function() {
							var option = "<option value='"+this.cid+"'>"+this.cname;
							console.log(option);
							$("select[name='cid']").append(option);//通过标签名追加标签
							/* $("#gory").append(option); */
						});
					}
				});
			});
		</script>
	</HEAD>
	<body>
		<br>
		<form action="${pageContext.request.contextPath}/AdminSearchServlet?method=searchProduct" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>商品查询列表</strong>
						</TD>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;按类别查询：<select name="cid" id="gory">
				 							<option value="">--请选择--
				 							
										</select>
										<br/><br/>
								按是否热门查询：<select name="is_hot">
					 							<option value="">--请选择--
					 							<option value="1">是
												<option value="0">否
										</select>
										<br/><br/>
								按是否上架查询：<select name="pflag">
					 							<option value="">--请选择--
					 							<option value="0">是
												<option value="1">否
										</select>
										<br/><br/>
							<div class="input-group col-md-3" style="margin-top: 0px positon:relative">
								<input type="text" class="form-control" placeholder="请输入商品关键字" name="pname"/>
								<span class="input-group-btn">
									<button type="submit" class="btn btn-info btn-search">查找</button>
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

									<td align="center" width="18%">
										商品编号
									</td>
									<td align="center" width="17%">
										商品图片
									</td>
									<td align="center" width="17%">
										商品名称
									</td>
									
									<td align="center" width="17%">
										商品价格
									</td>
									<td align="center" width="17%">
										是否热门
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										是否下架
									</td>
								</tr>
								<c:forEach items="${product }" var="p">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												${ p.pid }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<img width="40" height="45" src="${pageContext.request.contextPath }/${p.pimage}">
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${ p.pname }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${ p.shop_price } 元
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
													<c:if test="${p.is_hot == 1}">
														是
													</c:if>
													<c:if test="${p.is_hot == 0}">
														否
													</c:if>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/AdminProductServlet?method=editProductUI&pid=${p.pid}">
													<img src="${pageContext.request.contextPath}/img/admin/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<%--下架 pushdown --%>
												<c:if test="${p.pflag==1}">
												<a href="javascript:void(0);" class="deleteProduct" id = "${p.pid}">
													已下架
												</a>
												</c:if>
												<!--  上架-->
												<c:if test="${p.pflag==0}">
												<a href="javascript:void(0);" class="addProduct" id = "${p.pid}">
													已上架
												</a>
												</c:if>
											</td>
										</tr>
								</c:forEach>		
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>