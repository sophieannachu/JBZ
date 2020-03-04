<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.sleepcheck.model.*"%>

<%
SleepCheckVO sleepCheckVO = (SleepCheckVO) request.getAttribute("sleepCheckVO");
%>

<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath()%>/front-end/sleepcheck/page.js"></script>
<script src="<%=request.getContextPath()%>/front-end/sleepcheck/sleepChart.js"></script>

<%-- 錯誤表列 --%>
<script type="text/javascript">

<c:if test="${not empty errorMsgs}">
	var str="";
	<c:forEach var="message" items="${errorMsgs}">
		 str +="${message}\n";
	</c:forEach>
	swal(str);
</c:if>

$(document).ready(
		 function test(){

			   $.ajax({
					 type:"GET",
					 url:"<%=request.getContextPath()%>/sleepCheck/getSleepCheckJson.do",
					 data:{action:"getSleepcheck",memno:"${memVO.memno}"},
					 dataType:"json",
					 success:function (data){
						 getData(data);
						 highcharSleep(arrWakeDate,arrSleepTime);					 
				     },
		             error:function(){alert("error")}
	             })
});
</script>
</head>
<body>
	<h2 style="margin-top: 0; font-size: 25px; margin-bottom: 15px;">睡眠紀錄</h2>
	<form method="post" action="<%=request.getContextPath()%>/sleepCheck/sleepCheck.do" id="sleepCheck">
		<table class="table table-hover table-striped table-condensed ">
			<tbody>
				<tr>
					<td>就寢時間：</td>
					<%
						SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Timestamp nowTime = new Timestamp(System.currentTimeMillis());
						String nowFormat = fmat.format(nowTime);
					%>
					<td><input type="text" readonly id="datetimepicker1" name="bedTime" onchange="calculate(this);" 
						value="<%= (sleepCheckVO==null)? "" :fmat.format(sleepCheckVO.getBedTime()) %>" class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker1"><i class="fa fa-calendar" aria-hidden="true"></i></label>
						<br><span class="error bedTime"></span>		
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>起床時間：</td>
					<td><input type="text" readonly id="datetimepicker2" name="wakeTime" onchange="calculate(this);" 
						value="<%= (sleepCheckVO==null)? nowFormat : fmat.format(sleepCheckVO.getWakeTime())%>" class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker2"><i class="fa fa-calendar" aria-hidden="true"></i></label></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>實際睡眠時間：</td>
					<td><input type="text" readonly class="form-control" name="sleepTime"
						value="<%= (sleepCheckVO==null)? "" : sleepCheckVO.getSleepTime()%>"
						style="width: 140px; display: inline-table;"></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<input type="hidden" name="memno" value="${memVO.memno}">
		<input type="hidden" name="action" value="insert_Sleep">
	</form>		
			
		<div style="text-align: center;">

			<button type="button" id="btnSend"class="btn btn-primary">
				<i class="fa fa-check" aria-hidden="true"></i></i>儲存紀錄
			</button>		
			<button type="submit" class="btn btn-primary">
				<i class="fa fa-times" aria-hidden="true"></i>取消
			</button>
		</div>


	<div class="container-fluid" style="margin-top: 50px;">
		<div class="row">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">睡眠時間</a></li>
					<li><a href="#tabs-2">歷史資料</a></li>
				</ul>
				<div id="tabs-1">
					
				</div>
				<div id="tabs-2">
					<div id="showPanel">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="dialog-update" title="修改健檢紀錄">
	</div>
</body>
</html>