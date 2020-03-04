<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.foodlist.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	FoodlistService foodlistSvc = new FoodlistService();
	List<FoodlistVO> list = foodlistSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<html>
<head>
<title>�Ҧ����u��� - listAllEmp.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����u��� - ListAllEmp.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="IMAGES/sky.jpg" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>�����s��</th>
		<th>�����\�O</th>
		<th>�������</th>
		<th>���q</th>
		<th>�|���s��</th>
		<th>�����s��</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="foodlistVO" items="${list}" >
		<tr align='center' valign='middle'>
			<td>${foodlistVO.fdrecno}</td>
			<td>${foodlistVO.fddesc}</td>
			<td>${foodlistVO.fddate}</td>
			<td>${foodlistVO.fdqua}</td>
			<td>${foodlistVO.memno}</td>
			<td>${foodlistVO.fd_no}</td>			
			<%--  <td><c:forEach var="deptVO" items="${deptSvc.all}">
                    <c:if test="${empVO.deptno==deptVO.deptno}">
	                    ${deptVO.deptno}�i${deptVO.dname} - ${deptVO.loc}�j
                    </c:if>
                </c:forEach>
			</td>--%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodlist/foodlist.do">
			     <input type="submit" value="�ק�"> 
			     <input type="hidden" name="fdrecno" value="${foodlistVO.fdrecno}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--><!-- �ثe�|���Ψ�  -->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodlist/foodlist.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="fdrecno" value="${foodlistVO.fdrecno}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>