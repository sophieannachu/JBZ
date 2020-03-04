<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodlist.model.*"%>
<%
FoodVO foodVO = (FoodVO) request.getAttribute("FoodVO");
%>

<html>
<head>
<title>食物新增 - addfood.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/foodlist/js/calendar.css">

<div id="popupcalendar" class="text"></div>
<script>
function aafood(){
		
// 		window.location = 'food.do';
// 		window.close();	
		
              
                if(form1.fd_spone.value == "") 
                {
                        alert("未輸入全榖根莖類");
                }
                else if(form1.fd_sptwo.value == "")
                {
                	 	alert("未輸入豆魚蛋肉類");
                }
                else if(form1.fd_spthr.value == "")
                {
                	 	alert("未輸入低脂乳品類");
                }
                else if(form1.fd_spfor.value == "")
                {
                	 	alert("未輸入蔬菜類");
                }
                else if(form1.fd_spfir.value == "")
                {
                	 	alert("未輸入水果類");
                }
                else if(form1.fd_spsix.value == "")
                {
                	 	alert("未輸入油脂及堅果種子類");
                }
                else if(form1.prot.value == "")
                {
                	 	alert("未輸入蛋白質");
                }
                else if(form1.fat.value == "")
                {
                	 	alert("未輸入脂肪");
                }
                else if(form1.mono.value == "")
                {
                	 	alert("未輸入單元不飽和");
                }
                else if(form1.poly.value == "")
                {
                	 	alert("未輸入多元不飽和");
                }
                else if(form1.sfa.value == "")
                {
                	 	alert("未輸入飽合脂肪酸");
                }
                else if(form1.trans.value == "")
                {
                	 	alert("未輸入反式");
                }
                else if(form1.cho.value == "")
                {
                	 	alert("未輸入膽固醇");
                }
                else if(form1.carb.value == "")
                {
                	 	alert("未輸入碳水化合物");
                }
                else if(form1.sugar.value == "")
                {
                	 	alert("未輸入糖");
                }
                else if(form1.df.value == "")
                {
                	 	alert("未輸入膳食纖維");
                }
                else if(form1.na.value == "")
                {
                	 	alert("未輸入鈉");
                }
                else if(form1.ca.value == "")
                {
                	 	alert("未輸入鈣");
                }
                else if(form1.k.value == "")
                {
                	 	alert("未輸入鉀");
                }
                else if(form1.cal.value == "")
                {
                	 	alert("未輸入卡路里");
                }
                else {
//                 	alert("你已經成功新增食物");
//                 	swal("ya");
                	form1.submit();
                	
                }
  
	}
function foodMagic(){
	$('#fd_name').val("雞排");
	$('#fd_spone').val("0");
	$('#fd_sptwo').val("1");
	$('#fd_spthr').val("0");
	$('#fd_spfor').val("0");
	$('#fd_spfir').val("0");
	$('#fd_spsix').val("0");
	$('#prot').val("90");
	$('#fat').val("48");
	$('#mono').val("16");
	$('#poly').val("21");
	$('#sfa').val("27");
	$('#trans').val("16");
	$('#cho').val("23");
	$('#carb').val("13");
	$('#sugar').val("38");
	$('#df').val("11");
	$('#na').val("19");
	$('#ca').val("26");
	$('#k').val("36");
	$('#cal').val("600");
	
}
</script>
<script>
$(document).ready(function() {
	<c:if test="${not empty errorMsgs}">
	var strrrr="";
	<c:forEach var="message" items="${errorMsgs}">
		 strrrr +="${message}\n";
	</c:forEach>	
	swal(strrrr);
	</c:if>

});

</script>
<body bgcolor='white'>



<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodlist/food.do" name="form1" onsubmit="return validate_form(this)">
<table border="0">

	<tr>
		<td>食物名:</td>
		<td><input type="TEXT" name="fd_name" size="45" id="fd_name"
			value="<%= (foodVO==null)? "" : foodVO.getFd_name()%>" /></td>
	</tr>
	<tr>
		<td>全榖根莖類(碗):</td>
		<td><input type="TEXT" name="fd_spone" size="45" id="fd_spone"
			value="<%= (foodVO==null)? "" : foodVO.getFd_spone()%>" /></td>
	</tr>
	<tr>
		<td>豆魚蛋肉類(份):</td>
		<td><input type="TEXT" name="fd_sptwo" size="45" id="fd_sptwo"
			value="<%= (foodVO==null)? "" : foodVO.getFd_sptwo()%>" /></td>
	</tr>
	<tr>
		<td>低脂乳品類(杯):</td>
		<td><input type="TEXT" name="fd_spthr" size="45" id="fd_spthr"
			value="<%= (foodVO==null)? "" : foodVO.getFd_spthr()%>" /></td>
	</tr>
	<tr>
		<td>蔬菜類(碟):</td>
		<td><input type="TEXT" name="fd_spfor" size="45" id="fd_spfor"
			value="<%= (foodVO==null)? "" : foodVO.getFd_spfor()%>" /></td>
	</tr>
	<tr>
		<td>水果類(份):</td>
		<td><input type="TEXT" name="fd_spfir" size="45" id="fd_spfir"
			value="<%= (foodVO==null)? "" : foodVO.getFd_spfir()%>" /></td>
	</tr>
	<tr>
		<td>油脂及堅果種子類(茶匙):</td>
		<td><input type="TEXT" name="fd_spsix" size="45" id="fd_spsix"
			value="<%= (foodVO==null)? "" : foodVO.getFd_spsix()%>" /></td>
	</tr>
	<tr>
		<td>蛋白質(公克):</td>
		<td><input type="TEXT" name="prot" size="45" id="prot"
			value="<%= (foodVO==null)? "" : foodVO.getProt()%>" /></td>
	</tr>
	<tr>
		<td>脂肪(公克):</td>
		<td><input type="TEXT" name="fat" size="45" id="fat"
			value="<%= (foodVO==null)? "" : foodVO.getFat()%>" /></td>
	</tr>
	<tr>
		<td>單元不飽和(公克):</td>
		<td><input type="TEXT" name="mono" size="45" id="mono"
			value="<%= (foodVO==null)? "" : foodVO.getMono()%>" /></td>
	</tr>
	<tr>
		<td>多元不飽和(公克):</td>
		<td><input type="TEXT" name="poly" size="45" id="poly"
			value="<%= (foodVO==null)? "" : foodVO.getPoly()%>" /></td>
	</tr>
	<tr>
		<td>飽合脂肪酸(公克):</td>
		<td><input type="TEXT" name="sfa" size="45" id="sfa"
			value="<%= (foodVO==null)? "" : foodVO.getSfa()%>" /></td>
	</tr>
	<tr>
		<td>反式(公克):</td>
		<td><input type="TEXT" name="trans" size="45" id="trans"
			value="<%= (foodVO==null)? "" : foodVO.getTrans()%>" /></td>
	</tr>
	<tr>
		<td>膽固醇(毫克):</td>
		<td><input type="TEXT" name="cho" size="45" id="cho"
			value="<%= (foodVO==null)? "" : foodVO.getCho()%>" /></td>
	</tr>
	<tr>
		<td>碳水化合物(公克):</td>
		<td><input type="TEXT" name="carb" size="45" id="carb"
			value="<%= (foodVO==null)? "" : foodVO.getCarb()%>" /></td>
	</tr>
	<tr>
		<td>糖(公克):</td>
		<td><input type="TEXT" name="sugar" size="45" id="sugar"
			value="<%= (foodVO==null)? "" : foodVO.getSugar()%>" /></td>
	</tr>
	<tr>
		<td>膳食纖維(公克):</td>
		<td><input type="TEXT" name="df" size="45" id="df"
			value="<%= (foodVO==null)? "" : foodVO.getDf()%>" /></td>
	</tr>
	<tr>
		<td>鈉(毫克):</td> 
		<td><input type="TEXT" name="na" size="45" id="na"
			value="<%= (foodVO==null)? "" : foodVO.getNa()%>" /></td>
	</tr>
	<tr>
		<td>鈣(毫克):</td>
		<td><input type="TEXT" name="ca" size="45" id="ca"
			value="<%= (foodVO==null)? "" : foodVO.getCa()%>" /></td>
	</tr>
	<tr>
		<td>鉀(毫克):</td>
		<td><input type="TEXT" name="k" size="45" id="k"
			value="<%= (foodVO==null)? "" : foodVO.getK()%>" /></td>
	</tr>
	<tr>
		<td>卡路里(大卡):</td>
		<td><input type="TEXT" name="cal" size="45" id="cal"
			value="<%= (foodVO==null)? "" : foodVO.getCal()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="danny" value="<%=session.getAttribute("danny")%>">
<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
<button type="button" value="" class="btn btn-success" onclick="aafood()"><i class="fa fa-check" aria-hidden="true"></i>送出新增</button>
<button type="button" value="" class="btn" onclick="foodMagic()"><i class="fa fa-magic" aria-hidden="true"></i>神奇</button>
</FORM>
</body>

</html>
