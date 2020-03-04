<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bpcheck.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
BPCheckService bpCheckSvc = new BPCheckService();
MemVO memVO = (MemVO)session.getAttribute("memVO");
List<BPCheckVO> list = bpCheckSvc.getAllByMemBPCheck(memVO.getMemno());
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
		<th>��Ƥ��</th>
		<th>���Y��</th>
		<th>�αi��</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="bpCheckVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${bpCheckVO.checkDate}" /></td>
			<td>${bpCheckVO.sPressure==0? "":bpCheckVO.sPressure}</td>
			<td>${bpCheckVO.dPressure==0? "":bpCheckVO.dPressure}</td>
			<td>
			     <button  id="update" onclick="update(this);">�s��</button> 
			     <input type="hidden" name="bpCheckno" value="${bpCheckVO.bpCheckno}">
			    <input type="hidden" name="whichPage"	value="<%=currentPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			 </td>
			<td>
			<button name="delete" onclick="deleteOne(this);">�R��</button><input type="hidden" name="bpCheckno" value="${bpCheckVO.bpCheckno}">  
			
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="page2.file" %>
</body>
</html>