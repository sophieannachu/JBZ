<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bgcheck.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	BGCheckService bgCheckSvc = new BGCheckService();
    List<BGCheckVO> list = bgCheckSvc.getAllByMemBGCheck(memVO.getMemno());
    pageContext.setAttribute("list",list);
%>
<%@ include file="pages/page1.file" %>
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
		<th>資料日期</th>
		<th>飯前血糖值</th>
		<th>飯後2小血糖值</th>
		<th>睡前血糖值</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="bgCheckVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${bgCheckVO.checkDate}" /></td>
			<td>${bgCheckVO.bgBfMeat==0? "":bgCheckVO.bgBfMeat}</td>
			<td>${bgCheckVO.bgAfMeat==0? "":bgCheckVO.bgAfMeat}</td>
			<td>${bgCheckVO.bgBfSleep==0? "":bgCheckVO.bgBfSleep}</td>
			<td>
			     <button  id="update" onclick="update(this);">編輯</button> 
			     <input type="hidden" name="bgCheckno" value="${bgCheckVO.bgCheckno}">
			    <input type="hidden" name="whichPage"	value="<%=currentPage%>">               <!--送出當前是第幾頁給Controller-->
			 </td>
			<td>
			<button name="delete" onclick="deleteOne(this);">刪除</button><input type="hidden" name="bgCheckno" value="${bgCheckVO.bgCheckno}">  
			
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="pages/page2.file" %>
</body>
</html>