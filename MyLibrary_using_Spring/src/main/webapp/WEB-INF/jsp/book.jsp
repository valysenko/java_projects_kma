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
</head>

<style>
.menu {
	text-align: center;
}
</style>

<body>

	<div style="text-align: right">
		<a href="<c:url value="/logout" />">Logout </a>
	</div>
	<div class="menu">
		<a href="main">Main</a> 
		&nbsp; | &nbsp;
		<a href="management">Management</a>
	</div>
	<h2>
		<spring:message code="label.title" />
	</h2>

	<form:form method="post" action="add" commandName="book">

		<table>
			<tr>
				<td><form:label path="name">
						<spring:message code="label.name" />
					</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="author">
						<spring:message code="label.author" />
					</form:label></td>
				<td><form:input path="author" /></td>
			</tr>
			<tr>
				<td><form:label path="year">
						<spring:message code="label.year" />
					</form:label></td>
				<td><form:input path="year" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message code="label.addbook"/>" /></td>
			</tr>
		</table>
	</form:form>

	<h3>
		<spring:message code="label.books" />
	</h3>
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
					<td>${book.name}</td>
					<td>${book.author}</td>
					<td>${book.year}</td>
					<td><a href="delete/${book.id}"><spring:message
								code="label.delete" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>