<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sleepcheck.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	SleepCheckService sleepCheckSvc = new SleepCheckService();
	MemVO memVO = (MemVO)session.getAttribute("memVO");
    List<SleepCheckVO> list = sleepCheckSvc.getAllByMemSleepCheck(memVO.getMemno());
    pageContext.setAttribute("list",list);
%>
<%@ include file="page1.file" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style>
.table>thead>tr>th{
text-align:center;
}
</style>
</head>
<body>
<table style="width:100%; border:solid 1px #aaa;" class="table table-hover table-striped table-condensed checktable" 
	>
	<thead>
	<tr>
		<th>�N��ɶ�</th>
		<th>�_�ɮɶ�</th>
		<th>��ںίv�ɶ�</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="sleepCheckVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sleepCheckVO.bedTime}" /></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sleepCheckVO.wakeTime}" /></td>
			<td>${sleepCheckVO.sleepTime}</td>
			<td>
			     <button  id="update" onclick="update(this);">�s��</button> 
			     <input type="hidden" name="sleepCheckno" value="${sleepCheckVO.sleepCheckno}">
			    <input type="hidden" name="whichPage"	value="<%=currentPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			 </td>
			<td>
			<button name="delete" onclick="deleteOne(this);">�R��</button><input type="hidden" name="bpCheckno" value="${sleepCheckVO.sleepCheckno}">
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="page2.file" %>
</body>
</html>