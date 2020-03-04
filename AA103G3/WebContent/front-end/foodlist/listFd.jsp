<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.foodlist.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 	FoodService foodSvc = new FoodService();
 	List<FoodVO> fdlist = foodSvc.getAll();
 	pageContext.setAttribute("fdlist",fdlist);
%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�Ҧ�������� </title>
</head>
<body>
	<div style="width:1000px;height:450px;overflow:auto;">


	
	<table border='1' bordercolor='#CCCCFF' style="width:100%" class="table table-striped table-bordered table-hover">
	<tr align='center' valign='middle' style="background-color:#63C2C5; color:#fff;">
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
					<button type="submit" name="Submit" value="�s�W" style="background-color:#fff"><i class="fa fa-plus" ></i> </button>
				
					<input type="hidden" name="getFd_no" value="${foodVO.fd_no}">
					<input type="hidden" name="getFd_name" value="${foodVO.fd_name}">
					<input type="hidden" name="getCal" value="${foodVO.cal}">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="action" value="AddFd">
				</form>
			</td>
<%-- 			<td><a href="foodlist.do?fd_no=${foodVO.fd_no}&action=getOneFd">�s�W</a></td> --%>
             
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodlist/foodlist.do"> --%>
<!-- 			     <input type="submit" value="�s�W">  -->
<%-- 			     <input type="hidden" name="fdrecno" value="${foodlistVO.fdrecno}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--><!-- �ثe�|���Ψ�  --> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>