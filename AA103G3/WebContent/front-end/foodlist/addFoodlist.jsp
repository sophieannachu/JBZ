<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodlist.model.*"%>
<%
FoodlistVO foodlistVO = (FoodlistVO) request.getAttribute("foodlistVO");
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>������Ʒs�W - addFoodlist.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>������Ʒs�W - addFoodlist.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/select_page.jsp"><img src="IMAGES/sky.jpg" width="100" height="100" border="1">�^����</a>
	    </td>
	</tr>
</table>

<h3>�������:</h3>
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

<FORM METHOD="post" ACTION="foodlist.do" name="form1">
<table border="0">

	<tr>
		<td>�\�O:</td>
<!-- 		<td> -->
		<td>
			<input type="radio" id="periodId_0" name="fddesc" value="���\" ><label for="periodId_0" style="font-weight:normal;">���\</label>
			<input type="radio" id="periodId_1" name="fddesc" value="���\" ><label for="periodId_1" style="font-weight:normal;">���\</label>
			<input type="radio" id="periodId_2" name="fddesc" value="���\" ><label for="periodId_2" style="font-weight:normal;">���\</label>
									
		</td>
<!-- 		<input type="TEXT" name="fddesc" size="45"  -->
<%-- 			value="<%= (foodlistVO==null)? "���\" : foodlistVO.getFddesc()%>" /></td> --%>
	</tr>
	<tr>
		<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
		<td>�������:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="fddate" value="<%= (foodlistVO==null)? date_SQL : foodlistVO.getFddate()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','fddate','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���"></a>
		</td>
	</tr>
	<tr>
		<td>���q:</td>
		<td><input type="TEXT" name="fdqua" size="45"
			value="<%= (foodlistVO==null)? "1" : foodlistVO.getFdqua()%>" /></td>
	</tr>
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="memno" size="45"
			value="<%= (foodlistVO==null)? "1" : foodlistVO.getMemno()%>" /></td>
	</tr>
	<tr>
		<td>�����s��:</td>
		<td><input type="TEXT" name="fd_no" size="45"
			value="<%= (foodlistVO==null)? "3" : foodlistVO.getFd_no()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="reset" value="�M��">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>
