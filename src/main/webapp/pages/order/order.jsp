<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--静态包含base标签，CSS样式，jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
		<%@include file="/pages/common/login_sucess_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="item">
				<tr>
					<td>${item.createTime}</td>
					<td>${item.price}</td>
					<td>${item.status==0?"未发货":(item.status==1?"已发货":"已签收")}</td>
					<td><a href="#">查看详情</a></td>
				</tr>
			</c:forEach>

		</table>
		<%--静态包含分页条--%>
		<%@include file="/pages/common/page_nav.jsp"%>
		<%--静态包含分页条--%>
		
	
	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>