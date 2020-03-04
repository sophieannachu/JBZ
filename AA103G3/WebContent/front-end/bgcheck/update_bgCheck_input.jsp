<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bgcheck.model.*"%>
<%
BGCheckVO bgCheckVO=null;
try{
	Integer bgcheckno = Integer.parseInt(request.getParameter("bgCheckno")); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	BGCheckService bgCheckSvc = new BGCheckService();
	bgCheckVO = bgCheckSvc.getOneBGCheck(bgcheckno);
	pageContext.setAttribute("bgCheckVO", bgCheckVO);
}catch(Exception e){
	bgCheckVO = (BGCheckVO)request.getAttribute("bgCheckVO");
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
					<td>紀錄日期(DATE)：</td>
					<td><input type="text" value="<%= bgCheckVO.getCheckDate() %>" id="datetimepicker3" name="checkDate1"
						class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker3"><i class="fa fa-calendar" aria-hidden="true"></i></label></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>飯前血糖值(glucose,AC)：</td>
					<td><input type="text" class="form-control" name="bgBfMeat1"
						value="<%=(bgCheckVO.getBgBfMeat()==null)? "":bgCheckVO.getBgBfMeat()%>"
						style="width: 140px; display: inline-table;">
						<br><span class="error bgBfMeat1"></span>
					</td>
					<td>(mg/dl)</td>
					<td>飯前血糖值標準：<100(mg/dl)</td>
				</tr>
				<tr>
					<td>飯後2小時血糖值(glucose,2h PC)：</td>
					<td><input type="text" class="form-control" name="bgAfMeat1"
						value="<%= (bgCheckVO.getBgAfMeat()==null)? "" : bgCheckVO.getBgAfMeat()%>"
						style="width: 140px; display: inline-table;">
						<br><span class="error bgAfMeat1"></span>
					</td>
					<td>(mg/dl)</td>
					<td>飯後2小時血糖值標準：<140(mg/dl)</td>
				</tr>
				<tr>
					<td>睡前血糖值(glucose,AH)：</td>
					<td><input type="text" class="form-control" name="bgBfSleep1"
						value="<%= (bgCheckVO.getBgBfSleep()==null)? "" :bgCheckVO.getBgBfSleep()%>"
						style="width: 140px; display: inline-table;">
						<br><span class="error bgBfSleep1"></span>
					</td>
					<td>(mg/dl)</td>
					<td>飯前血糖值標準：<100(mg/dl)</td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: center;">
			<input type="hidden" name="bgCheckno1" value="<%=bgCheckVO.getBgCheckno()%>">
			<input type="hidden" name="memno" value="${memVO.memno}">
			<button name="action" value="update" onclick="check2(this);" class="btn btn-primary">
				<i class="fa fa-check" aria-hidden="true"></i>儲存紀錄
			</button>
		</div>
</body>
</html>