<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sleepcheck.model.*"%>

<%
SleepCheckVO sleepCheckVO =null;
try{
	Integer sleepcheckno = Integer.parseInt(request.getParameter("sleepCheckno")); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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


<h3>資料修改:</h3>
<%-- 錯誤表列 --%>
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
					<td>就寢時間：</td>
					<td><input type="text" readonly id="datetimepicker3" name="bedTime1"
						value="<%= sleepCheckVO.getBedTime() %>" class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker3"><i class="fa fa-calendar" aria-hidden="true"></i></label>
						<br><span class="error bedTime"></span>		
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>起床時間：</td>
					<td><input type="text" readonly id="datetimepicker4" name="wakeTime1"
						value="<%=sleepCheckVO.getWakeTime()%>" class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker4"><i class="fa fa-calendar" aria-hidden="true"></i></label></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>實際睡眠時間：</td>
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
				<i class="fa fa-check" aria-hidden="true"></i>儲存紀錄
			</button>
		</div>

</body>
</html>