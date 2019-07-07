<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">请输入员工信息</h1>
<form action = "AddOneEmployee" method="post">
	<table align="center">
		<tr>
		<td>用户名:</td>
		<td><input type="text" name="id"></td>
		</tr>
		<tr>
 		<td>密码:</td>
 		<td><input type="text" name="name"><br></td>
 		</tr>
 		<tr>
 		<td>手机号:</td>
 		<td><input type="text" name="department"><br></td>
 		</tr>
 		<tr>
 		<td>邮箱:</td>
 		<td><input type="text" name="salary"><br></td>
 		</tr>
 		<tr>
 		<td>城市:</td>
 		<td><input type="text" name="city"><br></td>
 		</tr>
	</table>
	<p align="center"><input type="submit" value="添加"></p>
</form>
</body>
</html>