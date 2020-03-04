<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bpcheck.model.*"%>

<%
BPCheckVO bpCheckVO=null;
try{
	Integer bpcheckno = Integer.parseInt(request.getParameter("bpCheckno")); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
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
					<td>�������(DATE)�G</td>
					<td><input type="text" value="<%= bpCheckVO.getCheckDate() %>" id="datetimepicker3" name="checkDate1"
						class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker3"><i class="fa fa-calendar" aria-hidden="true"></i></label></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>���Y��(SYS)�G</td>
					<td><input type="text" class="form-control" name="sPressure1"
						value="<%= (bpCheckVO.getsPressure()==null)? "":bpCheckVO.getsPressure()%>"
						style="width: 140px; display: inline-table;">
						<br><span class="error sPressure1"></span>
					</td>
					<td>(mmHg)</td>
					<td>���Y���зǭȡG100-140mmHg</td>
				</tr>
				<tr>
					<td>�αi��(DIA)�G</td>
					<td><input type="text" class="form-control" name="dPressure1"
						value="<%= (bpCheckVO.getdPressure()==null)? "" : bpCheckVO.getdPressure()%>"
						style="width: 140px; display: inline-table;">(mmHg)
						<br><span class="error dPressure1"></span>
					</td>
					<td>(mmHg)</td>
					<td>�αi���зǭȡG60-90mmHg</td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: center;">
			<input type="hidden" name="bpCheckno1" value="<%=bpCheckVO.getBpCheckno()%>">
			<input type="hidden" name="memno" value="${memVO.memno}">
			<button onclick="check2(this);" class="btn btn-primary">
				<i class="fa fa-check" aria-hidden="true"></i>�x�s����
			</button>
		</div>
</body>
</html>