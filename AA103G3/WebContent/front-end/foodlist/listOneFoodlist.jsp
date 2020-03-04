<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.foodlist.model.*"%>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%-- <%List<FoodlistVO> foodlistVO =  (List<FoodlistVO>)request.getAttribute("foodlistVO");%> --%>
<%FoodlistVO foodlistVO = (FoodlistVO) request.getAttribute("foodlistVO");%>

<html>
<head>
<title>飲食資料 - listOneFoodlist.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 Script 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>飲食資料 - ListOneFoodlist.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="IMAGES/sky.jpg" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>飲食編號</th>
		<th>餐別</th>
		<th>飲食日期</th>
		<th>份量</th>
		<th>會員編號</th>
		<th>食物編號</th>
	</tr>
	
	<tr align='center' valign='middle'>
		<td><%=foodlistVO.getFdrecno()%></td>
		<td><%=foodlistVO.getFddesc()%></td>
		<td><%=foodlistVO.getFddate()%></td>
		<td><%=foodlistVO.getFdqua()%></td>
		<td><%=foodlistVO.getMemno()%></td>
		<td><%=foodlistVO.getFd_no()%></td>
	</tr>

<%-- 	<c:forEach var="foodlistVO" items="${list}"> --%>
<!-- 	<tr align='center' valign='middle'> -->
<%-- 			<td>${foodlistVO.fdrecno}</td> --%>
<%-- 			<td>${foodlistVO.fddesc}</td> --%>
<%-- 			<td>${foodlistVO.fddate}</td> --%>
<%-- 			<td>${foodlistVO.memno}</td> --%>
<%-- 			<td>${foodlistVO.fd_no}</td> --%>
<!-- 	</tr> -->
<%-- 	</c:forEach> --%>
</table>

</body>
</html>
