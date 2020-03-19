<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import ="java.sql.*, javax.sql.*, java.io.*" %>
   <%@ page import ="com.kopo.exam_hibernate.*" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>allviewDB</title>
</head>
<body>
	<h3>전체 데이터</h3>
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
		<%-- JSTL 태크안에 주석을 다니 실행이 안되서 주석은  --%>
		<%-- 그렇지 않다면 foreach문으로 list를 출력한다.  --%>
		<%-- 학번과 이름쪽엔 a태그로 링크를 걸어주는대, URL의 뒤쪽은 파라메터 이름이다.  --%>
		<c:choose>
			<%-- 만약 model에 담긴 list의 value값이 비어있다면 --%>
			<c:when test="${empty list}">
				<%-- 아래의 메시지를 출력한다. --%>
				<tr>
					<td colspan=3>
						<spring:message code="common.listEmpty"/>
					</td>
				</tr>
			</c:when>
			<%-- 그렇지 않다면 foreach문으로 list를 출력한다.  --%>
			<c:otherwise>
				<%-- model에 담긴 list갯수 만큼 반복해서 데이터를 출력한다. --%>
				<%-- item을 list로 한다는건 반복문에 list에 담긴 수만큼 반복한다는 것이고 --%>
				<%-- 이 list를 통해 값을 출력할 때 var를 'e'로 해서 e.데이터이름 으로 출력할 수 있게 한다. --%>
				<c:forEach items="${list}" var="e">
					<tr>
						<%-- 이름을 출력한다. a링크를 걸어 클릭시  'oneviewDB/파라메터 값(학번)' 형식으로 보낸다 --%>
						<td width=50><p align=center><a href="oneviewDB/${e.studentid}">${e.name}</a></p></td>
						<%-- 학번을 출력한다. a링크를 걸어 클릭시  'oneviewDB/파라메터 값(학번)' 형식으로 보낸다 --%>
						<td width=50><p align=center><a href="oneviewDB/${e.studentid}">${e.studentid}</a></p></td>
						<%-- 국어 성적 출력 --%>
						<td width=50><p align=center>${e.kor}</p></td>
						<%-- 영어 성적 출력 --%>
						<td width=50><p align=center>${e.eng}</p></td>
						<%-- 수학 성적 출력 --%>
						<td width=50><p align=center>${e.mat}</p></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>

</body>
</html>