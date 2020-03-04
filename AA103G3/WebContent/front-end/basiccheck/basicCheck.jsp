<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.basiccheck.model.*"%>
<%@ page import="com.mem.model.*"%>


<%
BasicCheckVO basicCheckVO = (BasicCheckVO) request.getAttribute("basicCheckVO");


%>

<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath()%>/front-end/basiccheck/page.js"></script>
<script src="<%=request.getContextPath()%>/front-end/basiccheck/chartBasic.js"></script>

<%-- ���~��C --%>
<script type="text/javascript">
<c:if test="${not empty errorMsgs}">
var str="";
<c:forEach var="message" items="${errorMsgs}">
	 str +="${message}\n";
</c:forEach>
swal(str);
</c:if>

//���R��
$(document).ready(
	 function test(){

		   $.ajax({
				 type:"POST",
				 url:"<%=request.getContextPath()%>/basicCheck/getWeightJson.do",
				 data:{action:"getWeight",memno:"${memVO.memno}"},
				 dataType:"json",
				 success:function (data){
					 getData(data);
					 highcharWeight(arrWeightDate,arrWeight);
					 highcharBmi(arrBmiDate,arrBmi);
					 highcharBmr(arrBmrDate,arrBmr);
					 highcharWaisyline(arrWaisylineDate,arrWaisyline);
					 highcharBfat(arrBfatDate,arrBfat);						 
			     },
	             error:function(){alert("error")}
             })
});

//FOR��s���p��bmi&bmr
function calculate1(event){
	var weight =$("input[name='weight1']").val();
	if(weight!=""&&isNaN(parseInt(weight))==false){
		var height =${memVO.height/100};
		var bir = "${memVO.getBir()}";
		var year = bir.split("-")[0];
		var now = new Date();
		var years = now.getFullYear();				
		var order = years-year;
		console.log(years);
		$("input[name='bmi1']").val(Math.floor(weight/(height*height)*10)/10);
		var sex = ${memVO.sex};
		if(sex==0){
			var hh=${1.8*memVO.height};		
			var bmr = Math.floor(((9.6*weight)+hh-(4.7*order)+655)*10)/10;
			$("input[name='bmr1']").val(bmr);
		}else{
			var hh=${5*memVO.height};	
			var bmr =Math.floor(((13.7*weight)+hh-(6.8*order)+66)*10)/10;
			$("input[name='bmr1']").val(bmr);
		}
	}else{
		$("input[name='bmr1']").val("");
		$("input[name='bmi1']").val("");
	}	
}
//FOR�s�W���p��bmi&bmr
function calculate(event){
	var weight =$("input[name='weight']").val();
	if(weight!=""&&isNaN(parseInt(weight))==false){
		var height =${memVO.height/100};
		var bir = "${memVO.getBir()}";
		var year = bir.split("-")[0];
		var now = new Date();
		var years = now.getFullYear();				
		var order = years-year;
		console.log(years);
		$("input[name='bmi']").val(Math.floor(weight/(height*height)*10)/10);
		var sex = ${memVO.height};
		if(sex==0){
			var bmr = (9.6*weight)+${1.8*memVO.height}-(4.7*order)+655;
			$("input[name='bmr']").val(bmr);
		}else{
			var bmr = (13.7*weight)+${5*memVO.height}-(6.8*order)+66;
			$("input[name='bmr']").val(bmr);
		}
	}else{
		$("input[name='bmr']").val("");
		$("input[name='bmi']").val("");
	}	
}
</script>

</head>
<body>
	<h2 style="margin-top: 0; margin-bottom: 15px;">�����魫�y�����</h2>
	<form method="post" action="<%=request.getContextPath()%>/basicCheck/basicCheck.do" id="basicCheck" >
		<table class="table table-hover table-striped table-condensed ">
			<tbody>
				<tr>
					<td>�������(DATE)�G</td>
					<%
						SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						java.sql.Timestamp nowTime = new java.sql.Timestamp(System.currentTimeMillis());
						String nowFormat = fmat.format(nowTime);
					%>
					<td><input type="text" id="datetimepicker1" name="checkDate" readonly
						value="<%= (basicCheckVO==null)? nowFormat : fmat.format(basicCheckVO.getCheckDate()) %>" class="form-control" style="width: 180px; display: inline-table;">
						<label for="datetimepicker1"><i class="fa fa-calendar" aria-hidden="true"></i></label></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>�魫(WEIGHT)�G</td>
					<td><input type="text" oninput="calculate(this);" class="form-control" name="weight"
						value="<%= (basicCheckVO==null)? "" : ((basicCheckVO.getWeight()==null)? "":basicCheckVO.getWeight())%>" style="width: 120px; display: inline-table;">(����/kg)
						<br><span class="error weight"></span>
					</td>
					<td>�����q����(BMI)�G</td>
					<td><input type="text" readonly class="form-control" name="bmi"
						value="<%= (basicCheckVO==null)? "" :((basicCheckVO.getBmi()==null)? "": basicCheckVO.getBmi())%>" style="width: 120px; display: inline-table;">
					</td>
				</tr>
				<tr>
					<td>��¦�N�²v(BMR)�G</td>
					<td><input type="text" readonly class="form-control" name="bmr"
						style="width: 120px; display: inline-table;">(�j�d/Kcal)</td>
					<td>BMR�p�⤽���G</td>
					<td></td>
				</tr>
				<tr>
					<td>�y��(WAISYLINE)�G</td>
					<td><input type="text" class="form-control" name="waisyline"
						value="<%= (basicCheckVO==null)? "" : ((basicCheckVO.getWaisyline()==null)? "" :basicCheckVO.getWaisyline())%>" style="width: 120px; display: inline-table;">(����/cm)
						<br><span class="error waisyline"></span>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>���(FAT)�G</td>
					<td><input type="text" class="form-control" name="bFat"
						value="<%= (basicCheckVO==null)? "" :((basicCheckVO.getbFat()==null)? "" : basicCheckVO.getbFat())%>"
						style="width: 120px; display: inline-table;">(%)
						<br><span class="error bFat"></span>
					</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
			<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
			<input type="hidden" name="memno" value="${memVO.memno}">
			<input type="hidden" name="action" value="insert">
		</form>
		<div style="text-align: center;">

			<button type="button" id="btnSend"class="btn btn-primary">
				<i class="fa fa-check" aria-hidden="true"></i>�x�s����
			</button>		
			<button type="submit" class="btn btn-primary">
				<i class="fa fa-times" aria-hidden="true"></i>����
			</button>
		</div>
	

	<div class="container-fluid" style="margin-top: 50px;">
		<div class="row">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">�魫</a></li>
					<li><a href="#tabs-2">BMI</a></li>
					<li><a href="#tabs-3">BMR</a></li>
					<li><a href="#tabs-4">�y��</a></li>
					<li><a href="#tabs-5">���</a></li>
					<li><a href="#tabs-6">���v���</a></li>
				</ul>
				<div id="tabs-1"></div>
				<div id="tabs-2"></div>
				<div id="tabs-3"></div>
				<div id="tabs-4"></div>
				<div id="tabs-5"></div>
				<div id="tabs-6">
					<div id="showPanel">
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="dialog-update" title="�קﰷ�ˬ���">
	</div>

</body>
</html>