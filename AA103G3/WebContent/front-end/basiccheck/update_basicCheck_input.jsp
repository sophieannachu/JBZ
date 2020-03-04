<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.basiccheck.model.*"%>
<%@ page import="java.text.*"%>
<%
	BasicCheckVO basicCheckVO=null;
	try{
		Integer basiccheckno = Integer.parseInt(request.getParameter("basicCheckno")); //EmpServlet.java (Concroller), �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
		BasicCheckService basicCheckSvc = new BasicCheckService();
		basicCheckVO = basicCheckSvc.getOneBasicCheck(basiccheckno);
		pageContext.setAttribute("basicCheckVO", basicCheckVO);
	}catch(Exception e){
		basicCheckVO = (BasicCheckVO)request.getAttribute("basicCheckVO");
	}
	

%>
<html>
<head></head>

<body bgcolor='white'>


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
					<%
						SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					%>
					<td><input type="text" readonly value="<%= fmat.format(basicCheckVO.getCheckDate()) %>" id="datetimepicker3" name="checkDate1"
						class="form-control" style="width: 200px; display: inline-table;">
						<label for="datetimepicker3"><i class="fa fa-calendar" aria-hidden="true"></i></label></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>�魫(WEIGHT)�G</td>
					<td><input type="text" oninput="calculate1(this);" value="<%= (basicCheckVO.getWeight()==null||basicCheckVO.getWeight()==0)? "":basicCheckVO.getWeight()%>" class="form-control" name="weight1"
						style="width: 140px; display: inline-table;">(����/kg)
						<br><span class="error weight1"></span>
					</td>
				<script>				
				</script>
					<td>�����q����(BMI)�G</td>
					<td><input type="text"  readonly value="<%= (basicCheckVO.getBmi()==null||basicCheckVO.getBmi()==0)? "":basicCheckVO.getBmi()%>" class="form-control" name="bmi1"
						style="width: 140px; display: inline-table;"></td>
				</tr>
				<tr>
					<td>��¦�N�²v(BMR)�G</td>
					<td><input type="text"  readonly value="<%= (basicCheckVO.getBmr()==null||basicCheckVO.getBmr()==0)? "":basicCheckVO.getBmr()%>" class="form-control" name="bmr1"
						style="width: 140px; display: inline-table;">(�j�d/Kcal)</td>
					<td>BMR�p�⤽���G</td>
					<td></td>
				</tr>
				<tr>
					<td>�y��(WAISYLINE)�G</td>
					<td><input type="text"  value="<%= (basicCheckVO.getWaisyline()==null||basicCheckVO.getWaisyline()==0)? "" :basicCheckVO.getWaisyline()%>" class="form-control" name="waisyline1"
						style="width: 140px; display: inline-table;">(����/cm)
						<br><span class="error waisyline1"></span>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>���(FAT)�G</td>
					<td><input type="text" value="<%= (basicCheckVO.getbFat()==null||basicCheckVO.getbFat()==0)? "" : basicCheckVO.getbFat()%>" class="form-control" name="bFat1"
						style="width: 140px; display: inline-table;">(%)
						<br><span class="error bFat1"></span>
					</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: center;">
			<input type="hidden" name="basicCheckpno1" value="<%=basicCheckVO.getBasicCheckno()%>">
			<input type="hidden" name="memno" value="${memVO.memno}">
			<button name="action" value="update" onclick="check2(this);" class="btn btn-primary">
				<i class="fa fa-check" aria-hidden="true"></i>�x�s����
			</button>
		</div>
<script>

</script>

</body>
</html>