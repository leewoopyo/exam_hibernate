<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import ="java.sql.*, javax.sql.*, java.io.*" %>
   <%@ page import ="com.kopo.exam_hibernate.*" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>oneviewDB</title>
</head>
<body>
	<h3>조회</h3>
	<hr>
	<%-- 전체 리스트가 출력되는 테이블 생성   --%>
	<table cellspacing=1 width=600 border=1>
		<tr>
			<td width=50><p align=center>이름</p></td>
			<td width=50><p align=center>학번</p></td>
			<td width=50><p align=center>국어</p></td>
			<td width=50><p align=center>영어</p></td>
			<td width=50><p align=center>수학</p></td>
		</tr>
		<%-- JSTL의 choose태그 로 조건문 실행 --%>
		
		<%-- 코드를 출력한다????? --%>
		<%-- list를 출력한다.  --%>
		<c:choose>
			<%-- 만약 model데 담긴 list의 값이 비어있다면 --%>
			<c:when test="${empty list}">
				<%-- 아래의 메시지를 출력한다. --%>
				<tr>
					<td colspan=3>
						<spring:message code="common.listEmpty"/>
					</td>
				</tr>
			</c:when>
			<%-- 그렇지 않으면  --%>
			<c:otherwise>
					<%-- 아래 내용을 출력한다. --%>
					<tr>
						<%-- model에 담긴 list의 값을 출력하는데 해당 list는 ExamSIO형태를 가지고 있다. --%>
						<%-- 그래서 list안에 데이터를 출력 할 때 'list.데이터이름' 이렇게 데이터출력을 한다 --%>
						<%-- 이름 출력 --%>
						<td width=50><p align=center>${list.name}</p></td>
						<%-- 학번 출력 --%>
						<td width=50><p align=center>${list.studentid}</p></td>
						<%-- 국어 성적 출력 --%>
						<td width=50><p align=center>${list.kor}</p></td>
						<%-- 영어 성적 출력 --%>
						<td width=50><p align=center>${list.eng}</p></td>
						<%-- 수학 성적 출력 --%>
						<td width=50><p align=center>${list.mat}</p></td>
					</tr>
			</c:otherwise>
		</c:choose>
	</table>

</body>
</html>