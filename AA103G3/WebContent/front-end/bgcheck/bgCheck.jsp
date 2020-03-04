<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.bgcheck.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<%
BGCheckVO bgCheckVO = (BGCheckVO) request.getAttribute("bgCheckVO");
%>

<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath() %>/front-end/bgcheck/page.js"></script>
<script src="<%=request.getContextPath() %>/front-end/bgcheck/chartBG.js"></script>

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
					 type:"POST",
					 url:"<%=request.getContextPath()%>/bgCheck/getBGCheckJson.do",
					 data:{action:"getBGcheck",memno:"${memVO.memno}"},
					 dataType:"json",
					 success:function (data){
						 getData(data);
						 highcharAC(arrACDate,arrAC);
						 highcharPC(arrPCDate,arrPC);
						 highcharAH(arrAHDate,arrAH);					 
				     },
		             error:function(){alert("error")}
	             })
});

</script>
</head>
<body>
	<h2 style="margin-top: 0; font-size: 25px; margin-bottom: 15px;">血糖紀錄</h2>
	<form method="post" action="<%=request.getContextPath() %>/bgCheck/bgCheck.do" id="bgCheck">
		<table class="table table-hover table-striped table-condensed ">
			<tbody>
				<tr>
					<td>紀錄日期(DATE)：</td>
					<%
						SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Timestamp nowTime = new Timestamp(System.currentTimeMillis());
						String nowFormat = fmat.format(nowTime);
					%>
					<td colspan="3"><input type="text" readonly id="datetimepicker1" name="checkDate"
						value="<%= (bgCheckVO==null)? nowFormat :fmat.format(bgCheckVO.getCheckDate())%>" class="form-control" style="width: 170px; display: inline-table;">
						<label for="datetimepicker1"><i class="fa fa-calendar" aria-hidden="true"></i></label></td>
					<td></td>
				</tr>
				<tr>
					<td>飯前血糖值(glucose,AC)：</td>
					<td><input type="text" class="form-control" name="bgBfMeat"
						value="<%= (bgCheckVO==null)? "" : ((bgCheckVO.getBgBfMeat()==null)? "":bgCheckVO.getBgBfMeat())%>"
						style="width: 140px; display: inline-table;">(mg/dl)
						<br><span class="error bgBfMeat"></span>
					</td>
					<td></td>
					<td>飯前血糖值標準：<100(mg/dl)</td>
				</tr>
				<tr>
					<td>飯後2小時血糖值(glucose,2h PC)：</td>
					<td><input type="text" class="form-control" name="bgAfMeat"
						value="<%= (bgCheckVO==null)? "" :((bgCheckVO.getBgAfMeat()==null)? "" : bgCheckVO.getBgAfMeat())%>"
						style="width: 140px; display: inline-table;">(mg/dl)
						<br><span class="error bgAfMeat"></span>
					</td>
					<td></td>
					<td>飯後2小時血糖值標準：<140(mg/dl)</td>
				</tr>
				<tr>
					<td>睡前血糖值(glucose,AH)：</td>
					<td><input type="text" class="form-control" name="bgBfSleep"
						value="<%= (bgCheckVO==null)? "" : ((bgCheckVO.getBgBfSleep()==null)? "" :bgCheckVO.getBgBfSleep())%>"
						style="width: 140px; display: inline-table;">(mg/dl)
						<br><span class="error bgBfSleep"></span>
					</td>
					<td></td>
					<td>飯前血糖值標準：<100(mg/dl)</td>
				</tr>
			</tbody>
		</table>
			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<input type="hidden" name="memno" value="${memVO.memno}">
			<input type="hidden" name="action" value="insert_BG">

		</form>
		
		<div style="text-align: center;">
			
			<button type="button" id="btnSend"class="btn btn-primary">
				<i class="fa fa-check" aria-hidden="true"></i></i>儲存紀錄
			</button>
			<button type="submit" name="action" value="cancle" 
				class="btn btn-primary">
				<i class="fa fa-times" aria-hidden="true"></i>取消
			</button>
		</div>

	<div class="container-fluid" style="margin-top: 50px;">
		<div class="row">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">飯前血糖</a></li>
					<li><a href="#tabs-2">飯後2小時血糖</a></li>
					<li><a href="#tabs-3">睡前血糖</a></li>
					<li><a href="#tabs-4">歷史資料</a></li>
				</ul>
				<div id="tabs-1">
					
				</div>
				<div id="tabs-2">
					
				</div>
				<div id="tabs-3">
					
				</div>
				<div id="tabs-4">
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