<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">
	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		
		d.add('0102','01','商品分类管理','','','mainFrame');
		//param1: 当前节点id
		//param2: 父节点id
		//param3: 节点上的文字描述
		//param4: 跳转路径
		//param5: 提示信息
		//param6: 发生变化的frame的name属性值
								
		d.add('010201','0102','商品类别管理','${pageContext.request.contextPath}/AdminCategoryServlet?method=findAllCats','','mainFrame');
		d.add('0104','01','商品销售管理','','','mainFrame');
		d.add('010401','0104','已上架商品管理','${pageContext.request.contextPath}/AdminProductServlet?method=findAllUpProductsWithPage&currentPageNum=1','','mainFrame');
		d.add('010402','0104','已下架商品管理','${pageContext.request.contextPath}/AdminProductServlet?method=findPushDownProduct&currentPageNum=1','','mainFrame');
		d.add('0105','01','客户订单管理');
		d.add('010501','0105','已付款的订单','${pageContext.request.contextPath}/AdminOrderServlet?method=PayOrders&currentPageNum=1','','mainFrame');
		d.add('010502','0105','已发货的订单','${pageContext.request.contextPath}/AdminOrderServlet?method=filledOrders&state=4&currentPageNum=1','','mainFrame');
		d.add('010503','0105','未发货的订单','${pageContext.request.contextPath}/AdminOrderServlet?method=unfilledOrders&state=2&currentPageNum=1','','mainFrame');
		d.add('010504','0105','完成交易订单','${pageContext.request.contextPath}/AdminOrderServlet?method=completeOrders&currentPageNum=1','','mainFrame');
		d.add('0106','01','客户信息管理');
		d.add('010601','0106','客户信息列表','${pageContext.request.contextPath}/AdminUserServlet?method=findAllUserWithPath&currentPageNum=1','','mainFrame');
		d.add('0107','01','搜索');
		d.add('010701','0107','商品搜索','${pageContext.request.contextPath}/admin/search/searchProduct.jsp','','mainFrame');
		d.add('010702','0107','订单搜索','${pageContext.request.contextPath}/admin/search/searchOrder.jsp','','mainFrame');
		d.add("0108", '01', '表报');
		d.add('010801','0108','销量报表','/store/information1.html','','mainFrame');
		document.write(d);
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
