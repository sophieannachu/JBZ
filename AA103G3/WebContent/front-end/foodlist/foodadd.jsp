<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodlist.model.*"%>
<%
FoodVO foodVO = (FoodVO) request.getAttribute("FoodVO");
%>

<html>
<head>
<title>�����s�W - addfood.jsp</title></head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/foodlist/js/calendar.css">

<div id="popupcalendar" class="text"></div>
<script>
function aafood(){
		
// 		window.location = 'food.do';
// 		window.close();	
		
              
                if(form1.fd_spone.value == "") 
                {
                        alert("����J���B�ڲ���");
                }
                else if(form1.fd_sptwo.value == "")
                {
                	 	alert("����J�����J����");
                }
                else if(form1.fd_spthr.value == "")
                {
                	 	alert("����J�C�רū~��");
                }
                else if(form1.fd_spfor.value == "")
                {
                	 	alert("����J������");
                }
                else if(form1.fd_spfir.value == "")
                {
                	 	alert("����J���G��");
                }
                else if(form1.fd_spsix.value == "")
                {
                	 	alert("����J�o�פΰ�G�ؤl��");
                }
                else if(form1.prot.value == "")
                {
                	 	alert("����J�J�ս�");
                }
                else if(form1.fat.value == "")
                {
                	 	alert("����J�ת�");
                }
                else if(form1.mono.value == "")
                {
                	 	alert("����J�椸�����M");
                }
                else if(form1.poly.value == "")
                {
                	 	alert("����J�h�������M");
                }
                else if(form1.sfa.value == "")
                {
                	 	alert("����J���X�תջ�");
                }
                else if(form1.trans.value == "")
                {
                	 	alert("����J�Ϧ�");
                }
                else if(form1.cho.value == "")
                {
                	 	alert("����J�x�T�J");
                }
                else if(form1.carb.value == "")
                {
                	 	alert("����J�Ҥ��ƦX��");
                }
                else if(form1.sugar.value == "")
                {
                	 	alert("����J�}");
                }
                else if(form1.df.value == "")
                {
                	 	alert("����J�����ֺ�");
                }
                else if(form1.na.value == "")
                {
                	 	alert("����J�u");
                }
                else if(form1.ca.value == "")
                {
                	 	alert("����J�t");
                }
                else if(form1.k.value == "")
                {
                	 	alert("����J�[");
                }
                else if(form1.cal.value == "")
                {
                	 	alert("����J�d����");
                }
                else {
//                 	alert("�A�w�g���\�s�W����");
//                 	swal("ya");
                	form1.submit();
                	
                }
  
	}
function foodMagic(){
	$('#fd_name').val("����");
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
		<td>�����W:</td>
		<td><input type="TEXT" name="fd_name" size="45" id="fd_name"
			value="<%= (foodVO==null)? "" : foodVO.getFd_name()%>" /></td>
	</tr>
	<tr>
		<td>���B�ڲ���(�J):</td>
		<td><input type="TEXT" name="fd_spone" size="45" id="fd_spone"
			value="<%= (foodVO==null)? "" : foodVO.getFd_spone()%>" /></td>
	</tr>
	<tr>
		<td>�����J����(��):</td>
		<td><input type="TEXT" name="fd_sptwo" size="45" id="fd_sptwo"
			value="<%= (foodVO==null)? "" : foodVO.getFd_sptwo()%>" /></td>
	</tr>
	<tr>
		<td>�C�רū~��(�M):</td>
		<td><input type="TEXT" name="fd_spthr" size="45" id="fd_spthr"
			value="<%= (foodVO==null)? "" : foodVO.getFd_spthr()%>" /></td>
	</tr>
	<tr>
		<td>������(��):</td>
		<td><input type="TEXT" name="fd_spfor" size="45" id="fd_spfor"
			value="<%= (foodVO==null)? "" : foodVO.getFd_spfor()%>" /></td>
	</tr>
	<tr>
		<td>���G��(��):</td>
		<td><input type="TEXT" name="fd_spfir" size="45" id="fd_spfir"
			value="<%= (foodVO==null)? "" : foodVO.getFd_spfir()%>" /></td>
	</tr>
	<tr>
		<td>�o�פΰ�G�ؤl��(����):</td>
		<td><input type="TEXT" name="fd_spsix" size="45" id="fd_spsix"
			value="<%= (foodVO==null)? "" : foodVO.getFd_spsix()%>" /></td>
	</tr>
	<tr>
		<td>�J�ս�(���J):</td>
		<td><input type="TEXT" name="prot" size="45" id="prot"
			value="<%= (foodVO==null)? "" : foodVO.getProt()%>" /></td>
	</tr>
	<tr>
		<td>�ת�(���J):</td>
		<td><input type="TEXT" name="fat" size="45" id="fat"
			value="<%= (foodVO==null)? "" : foodVO.getFat()%>" /></td>
	</tr>
	<tr>
		<td>�椸�����M(���J):</td>
		<td><input type="TEXT" name="mono" size="45" id="mono"
			value="<%= (foodVO==null)? "" : foodVO.getMono()%>" /></td>
	</tr>
	<tr>
		<td>�h�������M(���J):</td>
		<td><input type="TEXT" name="poly" size="45" id="poly"
			value="<%= (foodVO==null)? "" : foodVO.getPoly()%>" /></td>
	</tr>
	<tr>
		<td>���X�תջ�(���J):</td>
		<td><input type="TEXT" name="sfa" size="45" id="sfa"
			value="<%= (foodVO==null)? "" : foodVO.getSfa()%>" /></td>
	</tr>
	<tr>
		<td>�Ϧ�(���J):</td>
		<td><input type="TEXT" name="trans" size="45" id="trans"
			value="<%= (foodVO==null)? "" : foodVO.getTrans()%>" /></td>
	</tr>
	<tr>
		<td>�x�T�J(�@�J):</td>
		<td><input type="TEXT" name="cho" size="45" id="cho"
			value="<%= (foodVO==null)? "" : foodVO.getCho()%>" /></td>
	</tr>
	<tr>
		<td>�Ҥ��ƦX��(���J):</td>
		<td><input type="TEXT" name="carb" size="45" id="carb"
			value="<%= (foodVO==null)? "" : foodVO.getCarb()%>" /></td>
	</tr>
	<tr>
		<td>�}(���J):</td>
		<td><input type="TEXT" name="sugar" size="45" id="sugar"
			value="<%= (foodVO==null)? "" : foodVO.getSugar()%>" /></td>
	</tr>
	<tr>
		<td>�����ֺ�(���J):</td>
		<td><input type="TEXT" name="df" size="45" id="df"
			value="<%= (foodVO==null)? "" : foodVO.getDf()%>" /></td>
	</tr>
	<tr>
		<td>�u(�@�J):</td> 
		<td><input type="TEXT" name="na" size="45" id="na"
			value="<%= (foodVO==null)? "" : foodVO.getNa()%>" /></td>
	</tr>
	<tr>
		<td>�t(�@�J):</td>
		<td><input type="TEXT" name="ca" size="45" id="ca"
			value="<%= (foodVO==null)? "" : foodVO.getCa()%>" /></td>
	</tr>
	<tr>
		<td>�[(�@�J):</td>
		<td><input type="TEXT" name="k" size="45" id="k"
			value="<%= (foodVO==null)? "" : foodVO.getK()%>" /></td>
	</tr>
	<tr>
		<td>�d����(�j�d):</td>
		<td><input type="TEXT" name="cal" size="45" id="cal"
			value="<%= (foodVO==null)? "" : foodVO.getCal()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="danny" value="<%=session.getAttribute("danny")%>">
<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
<button type="button" value="" class="btn btn-success" onclick="aafood()"><i class="fa fa-check" aria-hidden="true"></i>�e�X�s�W</button>
<button type="button" value="" class="btn" onclick="foodMagic()"><i class="fa fa-magic" aria-hidden="true"></i>���_</button>
</FORM>
</body>

</html>
