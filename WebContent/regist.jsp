<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action = "RegistAction" method="post">
	<table >
		<tr>
		<td>用户名:</td>
		<td><input type="text" name="username"></td>
		</tr>
		<tr>
 		<td>密码:</td>
 		<td><input type="password" name="password"><br></td>
 		</tr>
 		<tr>
 		<td>手机号:</td>
 		<td><input type="text" name="phone"><br></td>
 		</tr>
 		<tr>
 		<td>邮箱:</td>
 		<td><input type="email" name="email"><br></td>
 		</tr>
	</table>
	<input type="submit" value="注册">
</form>
</body>
</html>