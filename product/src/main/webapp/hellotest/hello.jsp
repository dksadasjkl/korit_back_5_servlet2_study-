<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<% 	
	// 서버 사이드 렌더링(SSR)
	// model1
	// model2
	// mvc(model, view, controller)
	// model => 데이터 		-> DTO
	// view => html(화면)	-> HTML, JSP
	// controller => model, view를 제어, 요청, 응답 처리 -> servlet
	// WEB-INF(서브렛) -> private 내부에서만 접근 가능 
		String name = "서창현";
	 	String inputValue = "test";
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
		<li><%=name %></li>
	</ul>
	<div>
	 	<input value="<%=inputValue %>">
	</div>
</body>
</html>