<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
	<style type="text/css">
		h2{color:red;text-align:center;maigin-bottom:5px; margin-top:10px;}
		#d1{height:50px;padding-top: 20px; text-align: center}
	</style>
<body>
	<h1 align="center">信息表</h1>
	<c:if test="${clearFlag == 1||addFlag==1}">
		<script>
		//alert('删除成功');
		var tab = document.getElementById('empTable');
		tab.remove(true);
		</script>
	</c:if>
	<table border='1' id="empTable" align="center" margin: 100px 10px 20px 30px>
		 
		 <c:forEach var="emp" items="${sessionScope.userLogs}">
		 	<tr>
		 	<th><c:out value="${emp._id}"></c:out></th>
		 	<th><c:out value="${emp.name}"></c:out></th>
		 	<th><c:out value="${emp.department}"></c:out></th>
		 	<th><c:out value="${emp.salary}"></c:out></th>
		 	<th><c:out value="${emp.city}"></c:out></th>
		 	<th>
		 		<form action="/EMS/DeleteAction">
					<button type="submit" name="userID" value="${emp.id}">删除</button>	
		 		</form>
		 	</th>
		 	</tr>
		 </c:forEach>
 	</table>
 	<p style="text-align: center"><a href="/EMS/registEmployee.jsp">添加一个新的员工</a></p>
	 	<!-- 分页操作-->
		<c:if test="${empty param.pageNum}">
			<h2>这是第1页</h2>
		</c:if>
		<c:if test="${!empty param.pageNum}">
			<h2>这是第${param.pageNum}页</h2>
		</c:if>
		<div id="d1">
			<c:if test="${param.pageNum>1}">
				<a href="${pageContext.request.contextPath}/Success.jsp?pageNum=${param.pageNum-1}">上一页</a>
			</c:if>
			<c:forEach var="i" begin="1" end="10">
				<a href="${pageContext.request.contextPath}/Success.jsp?pageNum=${i}">${i}</a>
			</c:forEach>
			<c:if test="${param.pageNum<10||empty param.pageNum}">
				<a href="${pageContext.request.contextPath}/Success.jsp?pageNum=${param.pageNum+1}">下一页</a>
			</c:if>
		</div>
	</body>
</html>