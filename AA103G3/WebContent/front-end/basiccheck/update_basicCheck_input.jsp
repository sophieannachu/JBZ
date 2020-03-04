<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.basiccheck.model.*"%>
<%@ page import="java.text.*"%>
<%
	BasicCheckVO basicCheckVO=null;
	try{
		Integer basiccheckno = Integer.parseInt(request.getParameter("basicCheckno")); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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
					<td>體重(WEIGHT)：</td>
					<td><input type="text" oninput="calculate1(this);" value="<%= (basicCheckVO.getWeight()==null||basicCheckVO.getWeight()==0)? "":basicCheckVO.getWeight()%>" class="form-control" name="weight1"
						style="width: 140px; display: inline-table;">(公斤/kg)
						<br><span class="error weight1"></span>
					</td>
				<script>				
				</script>
					<td>身體質量指數(BMI)：</td>
					<td><input type="text"  readonly value="<%= (basicCheckVO.getBmi()==null||basicCheckVO.getBmi()==0)? "":basicCheckVO.getBmi()%>" class="form-control" name="bmi1"
						style="width: 140px; display: inline-table;"></td>
				</tr>
				<tr>
					<td>基礎代謝率(BMR)：</td>
					<td><input type="text"  readonly value="<%= (basicCheckVO.getBmr()==null||basicCheckVO.getBmr()==0)? "":basicCheckVO.getBmr()%>" class="form-control" name="bmr1"
						style="width: 140px; display: inline-table;">(大卡/Kcal)</td>
					<td>BMR計算公式：</td>
					<td></td>
				</tr>
				<tr>
					<td>腰圍(WAISYLINE)：</td>
					<td><input type="text"  value="<%= (basicCheckVO.getWaisyline()==null||basicCheckVO.getWaisyline()==0)? "" :basicCheckVO.getWaisyline()%>" class="form-control" name="waisyline1"
						style="width: 140px; display: inline-table;">(公分/cm)
						<br><span class="error waisyline1"></span>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>體脂(FAT)：</td>
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
				<i class="fa fa-check" aria-hidden="true"></i>儲存紀錄
			</button>
		</div>
<script>

</script>

</body>
</html>