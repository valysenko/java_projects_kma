<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login page</title>
<style>
.error {
	color: red;
}

.text {
	text-align: center;
	font-size: 25px;
}

.mainBlock{
    width:250px;
	height:200px;
	padding-top:10px;
	padding-bottom:10px;
	margin: 0 auto;
	margin-top:15%;
	vertical-align: middle;
}

.input{
	height:25px;
	
}

</style>
</head>
<body>
	<div class="mainBlock">
		<div class="text">Welcome to Library!</div>

		<p>
			<c:if test="${error == true}">
				<b class="error">Invalid login or password.</b>
			</c:if>
		</p>

		<form method="post" action="<c:url value='j_spring_security_check'/>">
			<table>
				<tbody>
					<tr>
						<td><input class="input" type="text" name="j_username" id="j_username"
							size="30"  maxlength="40" placeholder=" Login:"/></td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td><input class="input" type="password" name="j_password" id="j_password"
							size="30" maxlength="32" placeholder=" Password:"/></td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td><input type="submit" value="Sign in" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>