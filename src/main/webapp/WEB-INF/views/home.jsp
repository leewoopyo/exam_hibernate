<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<!-- 현제 날짜와 시간이 출력된다. -->
<!-- serverTime 이라는 model의 key값을 찾아서 그 value값을 출력한다. -->
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
