<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.foodlist.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�Ҧ�������� </title>
</head>
<body>
	<div style="width:1000px;height:450px;overflow:auto;">
	<table border='1' bordercolor='#CCCCFF' width='1000' class="table table-striped table-bordered table-hover">
	<tr style="background-color:#63C2C5; color:#fff;">
		<th>�����s��</th>
		<th>�����W��</th>
		<th>�J�ս�</th>
		<th>�ת�</th>
		<th>�Ҥ��ƦX��</th>
		<th>�d����</th>
		<th>�u</th>
		<th>�t</th>
		<th>�[</th>
		<th>�s�W</th>
	</tr>
	
	<c:forEach var="foodVO" items="${fdlist}" >
		<tr align='center' valign='middle'>
			
			<td>${foodVO.fd_no}</td> 	
			<td>${foodVO.fd_name}</td>
			<td>${foodVO.prot}</td>
			<td>${foodVO.fat}</td>
			<td>${foodVO.carb}</td>
			<td>${foodVO.cal}</td>						
			<td>${foodVO.na}</td>			
			<td>${foodVO.ca}</td>			
			<td>${foodVO.k}</td>
			<td> 
				<form name="shoppingForm" action="<%=request.getContextPath()%>/foodlist/foodlist.do" method="POST">
					<button type="submit" name="Submit" value="�s�W" class="fa fa-plus"></button>
					<input type="hidden" name="getFd_no" value="${foodVO.fd_no}">
					<input type="hidden" name="getFd_name" value="${foodVO.fd_name}">
					<input type="hidden" name="getCal" value="${foodVO.cal}">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="action" value="AddFd">
				</form>
			</td>

		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>