<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Admin window</title>

<style type="text/css">
.menu {
	text-align: center;
	height: 50px;
	margin-top: 25px;
}

.data {
	margin: 0 auto;
	margin-top: 25px;
	min-height: 50px;
	width: 800px;
	vertical-align: middle;
	padding: 0 auto;
}

td, th {
	text-align: center;
}
</style>

</head>
<body>
	<div style="text-align: right">
		<a href="<c:url value="/logout" />">Logout </a>
	</div>
	<div class="menu">
		<a href="main">Main</a>
		&nbsp; | &nbsp;
		 <a href="management">Management</a>
	</div>

	<c:if test="${!empty ordersList}">
		<table class="data">
			<tr>
				<th><spring:message code="label.name" /></th>
				<th><spring:message code="label.author" /></th>
				<th><spring:message code="label.year" /></th>
				<th>User</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${ordersList}" var="order">
				<tr>
					<td>${order.book.name}</td>
					<td>${order.book.author}</td>
					<td>${order.book.year}</td>
					<td>${order.user.login}</td>
					<td><a href="delete/order/${order.id}"><spring:message
								code="label.delete" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>