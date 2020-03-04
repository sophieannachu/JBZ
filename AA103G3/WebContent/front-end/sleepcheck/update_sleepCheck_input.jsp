<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sleepcheck.model.*"%>

<%
SleepCheckVO sleepCheckVO =null;
try{
	Integer sleepcheckno = Integer.parseInt(request.getParameter("sleepCheckno")); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
	SleepCheckService sleepCheckSvc = new SleepCheckService();
	sleepCheckVO = sleepCheckSvc.getOneSleepCheck(sleepcheckno);
	pageContext.setAttribute("sleepCheckVO", sleepCheckVO);
}catch(Exception e){
	sleepCheckVO = (SleepCheckVO)request.getAttribute("sleepCheckVO");
}

%>
<html>
<head>
</head>

<body bgcolor='white'>


<h3>��ƭק�:</h3>
<%-- ���~��C --%>
<script type="text/javascript">
<c:if test="${not empty errorMsgs}">
	var str="";
	<c:forEach var="message" items="${errorMsgs}">
		 str +="${message}\n";
	</c:forEach>
	swal(str);
</c:if>
</script>

		<table class="table table-hover table-striped table-condensed ">
			<tbody>
				<tr>
					<td>�N��ɶ��G</td>
					<td><input type="text" readonly id="datetimepicker3" name="bedTime1"
						value="<%= sleepCheckVO.getBedTime() %>" class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker3"><i class="fa fa-calendar" aria-hidden="true"></i></label>
						<br><span class="error bedTime"></span>		
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>�_�ɮɶ��G</td>
					<td><input type="text" readonly id="datetimepicker4" name="wakeTime1"
						value="<%=sleepCheckVO.getWakeTime()%>" class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker4"><i class="fa fa-calendar" aria-hidden="true"></i></label></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>��ںίv�ɶ��G</td>
					<td><input type="text" readonly class="form-control" name="sleepTime1"
						value="<%=sleepCheckVO.getSleepTime()%>"
						style="width: 140px; display: inline-table;"></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: center;">
			<input type="hidden" name="sleepCheckno1" value="<%=sleepCheckVO.getSleepCheckno()%>">
			<input type="hidden" name="memno" value="${memVO.memno}">
			<button onclick="check2(this);" class="btn btn-primary">
				<i class="fa fa-check" aria-hidden="true"></i>�x�s����
			</button>
		</div>

</body>
</html>