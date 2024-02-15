<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<% 	
	// 스크립트릿	
	
	List<String> names = List.of("서창현", "김창현", "박창현");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello</h1>
	<ul>
		<c:forEach var="name" items="${names}">
            <li>${name}</li>
        </c:forEach>
		<%
		for (String name : names) {
		%>
			<li>${name}</li>
		<%
			}
		%>
	</ul>
</body>
</html>