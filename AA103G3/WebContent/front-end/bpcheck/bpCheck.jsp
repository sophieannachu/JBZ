<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.bpcheck.model.*"%>

<%
BPCheckVO bpCheckVO = (BPCheckVO) request.getAttribute("bpCheckVO");
%>

<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath() %>//front-end/bpcheck/page.js"></script>
<script src="<%=request.getContextPath() %>//front-end/bpcheck/bpChart.js"></script>

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
					 url:"<%=request.getContextPath()%>/bpCheck/getBPCheckJson.do",
					 data:{action:"getBPcheck",memno:"${memVO.memno}"},
					 dataType:"json",
					 success:function (data){
						 getData(data);
						 highcharPressure(arrSPDate,arrSpressure,arrDpressure);					 
				     },
		             error:function(){alert("error")}
	             })
});

</script>
</head>
<body>
	<h2 style="margin-top: 0; font-size: 25px; margin-bottom: 15px;">血壓</h2>
	<form method="post" action="<%=request.getContextPath()%>/bpCheck/bpCheck.do" id="bpCheck">
		<table class="table table-hover table-striped table-condensed ">
			<tbody>
				<tr>
					<td>紀錄日期(DATE)：</td>
					<%
						SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Timestamp nowTime = new Timestamp(System.currentTimeMillis());
						String nowFormat = fmat.format(nowTime);
					%>
					<td><input type="text" readonly id="datetimepicker1" name="checkDate"
						value="<%= (bpCheckVO==null)? nowFormat : fmat.format(bpCheckVO.getCheckDate())%>" class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker1"><i class="fa fa-calendar" aria-hidden="true"></i></label></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>收縮壓(SYS)：</td>
					<td><input type="text" class="form-control" name="sPressure"
						value="<%= (bpCheckVO==null)? "" : ((bpCheckVO.getsPressure()==null)? "":bpCheckVO.getsPressure())%>"
						style="width: 140px; display: inline-table;">(mmHg)
						<br><span class="error sPressure"></span>
					</td>
					<td></td>
					<td>收縮壓標準值：100-140mmHg</td>
				</tr>
				<tr>
					<td>舒張壓(DIA)：</td>
					<td><input type="text" class="form-control" name="dPressure"
						value="<%= (bpCheckVO==null)? "" :((bpCheckVO.getdPressure()==null)? "" : bpCheckVO.getdPressure())%>"
						style="width: 140px; display: inline-table;">(mmHg)
						<br><span class="error dPressure"></span>
					</td>
					<td></td>
					<td>舒張壓標準值：60-90mmHg</td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<input type="hidden" name="memno" value="${memVO.memno}">
		<input type="hidden" name="action" value="insert_BP">		
	
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
					<li><a href="#tabs-1">收縮壓、舒張壓</a></li>
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