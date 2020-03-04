<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.basiccheck.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	BasicCheckService basicCheckSvc = new BasicCheckService();
	MemVO memVO = (MemVO)session.getAttribute("memVO");
    List<BasicCheckVO> list = basicCheckSvc.getAllByMemBasicCheck(memVO.getMemno());
    pageContext.setAttribute("list",list);
%>
<%@ include file="page1.file" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

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
		<th>�魫</th>
		<th>�����q����</th>
		<th>��¦�N�²v</th>
		<th>�y��</th>
		<th>���</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="basicCheckVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' id="${basicCheckVO.basicCheckno}">
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${basicCheckVO.checkDate}" /></td>
			<td>${basicCheckVO.weight==0? "":basicCheckVO.weight}</td>
			<td>${basicCheckVO.bmi==0? "":basicCheckVO.bmi}</td>
			<td>${basicCheckVO.bmr==0? "":basicCheckVO.bmr}</td>
			<td>${basicCheckVO.waisyline==0? "":basicCheckVO.waisyline}</td>
			<td>${basicCheckVO.bFat==0? "":basicCheckVO.bFat}</td>
			<td>
			     <button  id="update" onclick="update(this);">�s��</button> 
			     <input type="hidden" name="basicCheckno" value="${basicCheckVO.basicCheckno}">
			    <input type="hidden" name="whichPage"	value="<%=currentPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			 </td>
			<td>
			<button name="delete" onclick="deleteOne(this);">�R��</button><input type="hidden" name="basicCheckno" value="${basicCheckVO.basicCheckno}">  
			
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<%@ include file="page2.file" %>

</body>
</html>