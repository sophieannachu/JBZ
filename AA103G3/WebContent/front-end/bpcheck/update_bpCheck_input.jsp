<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bpcheck.model.*"%>

<%
BPCheckVO bpCheckVO=null;
try{
	Integer bpcheckno = Integer.parseInt(request.getParameter("bpCheckno")); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	BPCheckService bpCheckSvc = new BPCheckService();
	bpCheckVO = bpCheckSvc.getOneBPCheck(bpcheckno);
	pageContext.setAttribute("bpCheckVO", bpCheckVO);
}catch(Exception e){
	bpCheckVO = (BPCheckVO)request.getAttribute("bpCheckVO");
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
					<td><input type="text" value="<%= bpCheckVO.getCheckDate() %>" id="datetimepicker3" name="checkDate1"
						class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker3"><i class="fa fa-calendar" aria-hidden="true"></i></label></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>收縮壓(SYS)：</td>
					<td><input type="text" class="form-control" name="sPressure1"
						value="<%= (bpCheckVO.getsPressure()==null)? "":bpCheckVO.getsPressure()%>"
						style="width: 140px; display: inline-table;">
						<br><span class="error sPressure1"></span>
					</td>
					<td>(mmHg)</td>
					<td>收縮壓標準值：100-140mmHg</td>
				</tr>
				<tr>
					<td>舒張壓(DIA)：</td>
					<td><input type="text" class="form-control" name="dPressure1"
						value="<%= (bpCheckVO.getdPressure()==null)? "" : bpCheckVO.getdPressure()%>"
						style="width: 140px; display: inline-table;">(mmHg)
						<br><span class="error dPressure1"></span>
					</td>
					<td>(mmHg)</td>
					<td>舒張壓標準值：60-90mmHg</td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: center;">
			<input type="hidden" name="bpCheckno1" value="<%=bpCheckVO.getBpCheckno()%>">
			<input type="hidden" name="memno" value="${memVO.memno}">
			<button onclick="check2(this);" class="btn btn-primary">
				<i class="fa fa-check" aria-hidden="true"></i>儲存紀錄
			</button>
		</div>
</body>
</html>