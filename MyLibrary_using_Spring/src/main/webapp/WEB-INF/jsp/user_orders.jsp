<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title" /></title>

<style>
.menu {
	text-align: center;
}

.data {
	margin: 0 auto;
	min-height: 50px;
	width: 700px;
	vertical-align: middle;
	padding: 0 auto;
}

td, th {
	text-align: center;
}

#td2{
	text-align: left;
}

</style>
</head>
<body>

	<div style="text-align: right">
		<a href="<c:url value="/logout" />">Logout </a>
	</div>

	<div class="menu">
		<a href="<c:url value="/user/main" />"> Main </a> 
			&nbsp; | &nbsp;
		<a href="<c:url value="/user/orders" />"> My orders </a> 
	</div>
	<h2 style="text-align: center">
		<spring:message code="label.userOrders.title" />
	</h2>

	<c:if test="${!empty bookList}">
		<table class="data">
			<tr>
				<th><spring:message code="label.name" /></th>
				<th><spring:message code="label.author" /></th>
				<th><spring:message code="label.year" /></th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${bookList}" var="book">
				<tr>
					<td id="td2">${book.name}</td>
					<td id="td2">${book.author}</td>
					<td>${book.year}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>