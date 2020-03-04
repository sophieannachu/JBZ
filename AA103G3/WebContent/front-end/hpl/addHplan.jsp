<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String[] color={"#dbffdc", "#fcf8e3", "#f2dede"};
	String[] fontColor={"#006c03", "#8a6d3b", "#ab4842"};
	request.setAttribute("color", color);
	request.setAttribute("fontColor", fontColor);
%>
<html>
<head>
<title>addHplan.jsp</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/hpl/css/datepicker.min.css" />
<script src="<%=request.getContextPath()%>/front-end/hpl/js/bootstrap-datepicker.min.js"></script>
<link href="<%=request.getContextPath()%>/front-end/hpl/css/hpl.css" rel="stylesheet">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<script>
	$(document).ready(function() {
		
		$('#insertBtn_addHplan').click(function(e){
			var name=false, hpeddate=false;
        	e.preventDefault();
        	if($("#hpeddate").val().length <= 0){
        		$("#hpeddate_error").show();
        		$("#hpeddate").focus();
        	}else{
        		$("#hpeddate_error").hide();
        		name=true;
        	}
        	
        	if($("#name").val().length <= 0){
        		$("#name_error").show();
        		$("#name").focus();
        	}else{
        		$("#name_error").hide();
        		hpeddate=true;
        	}
        	
        	if(name && hpeddate){
        		$("form").submit();
        	}
        	
        });
		
		$("#datePicker").datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true,
			todayHighlight : true,
			startDate: '0d'
		});
	});
</script>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/hpl/HplServlet.do" role="form">
		<div class="name_pl" style="padding:0 0;border-bottom:0px;">
			<label for="name" class="col-sm-3 control-label font_pl" style="padding-top:5px;">計畫名稱</label>
			<input type="text" name="name" id="name" class="form-control col-sm-9 font_pl" value="我的健康計畫" style="width:75%;height:40px;"/>
			<div class="col-sm-3"></div>
			<label for="" id="name_error" class="col-sm-9 error_font_cpl" style="padding:0 0;display:none;">請輸入計畫名稱</label>
		</div>
		<div class="name_pl" style="padding:0 0;">
			<label for="hpeddate" class="col-sm-3 control-label font_pl" style="padding-top:5px;">結束日期</label>
			<div class="input-group input-append date col-sm-9" id="datePicker" style="">
				<input type="text" class="form-control font_pl" name="hpeddate" id="hpeddate" style="width:100%;height:40px;" value="" /> 
				<span class="input-group-addon add-on">
					<span class="fa fa-calendar"></span>
				</span>
			</div>
			<div class="col-sm-3"></div>
			<label for="" id="hpeddate_error" class="col-sm-9 error_font_cpl" style="padding:0 0;display:none;">請輸入結束日期</label>
		</div>
		<c:forEach var="hplItem" items="${hplList}">
			<c:if test="${hplItem.show}">
				<div class="well outter_hpl" style="">
					<div class="title_hpl" style="">
						<font class="title_font_hpl" style="">${hplItem.name}</font>
					</div>
					
					<c:if test="${hplItem.name=='體重控制'}">
						<div class="content_hpl" style="background:${color[hplItem.level]};">
							<p class="content_font_hpl" style="color:${fontColor[hplItem.level]};">您目前的體重為 ${bcVO.weight} 公斤 BMI為 ${bcVO.bmi} ${hplItem.status}</p>
							<p class="content_font_hpl" style="color:${fontColor[hplItem.level]};">標準體重值為 ${hplItem.value} 公斤</p>
						</div>
						<div class="suggest_hpl" style="">
							<p class="suggest_font_hpl" style="">建議您 將體重維持在 ${hplItem.value} 公斤</p>
							<input type="hidden" name="hpweight" value="${hplItem.value}">
						</div>
					</c:if>
					
					<c:if test="${hplItem.name=='腰圍控制'}">
						<div class="content_hpl" style="background:${color[hplItem.level]};">
							<p class="content_font_hpl" style="color:${fontColor[hplItem.level]};">您目前的腰圍為 ${waistline} 公分 ${hplItem.status}</p>
							<p class="content_font_hpl" style="color:${fontColor[hplItem.level]};">標準腰圍值為 ${hplItem.value} 公分以下</p>
						</div>
						<div class="suggest_hpl" style="">
							<p class="suggest_font_hpl" style="">建議您 將腰圍維持在 ${hplItem.value} 公分以下</p>
							<input type="hidden" name="hpwaist" value="${hplItem.value}">
						</div>
					</c:if>
					
					<c:if test="${hplItem.name=='血壓控制'}">
						<div class="content_hpl" style="background:${color[hplItem.level]};">
							<p class="content_font_hpl" style="color:${fontColor[hplItem.level]};">您上次所填寫的的血壓值為 ${bpVO.sPressure}/${bpVO.dPressure} mmHg ${hplItem.status}</p>
							<p class="content_font_hpl" style="color:${fontColor[hplItem.level]};">標準血壓值為 120/80 mmHg</p>
						</div>
						<div class="suggest_hpl" style="">
							<p class="suggest_font_hpl" style="">建議您 每日測量血壓並記錄  ${hplItem.value} 次以上</p>
							<input type="hidden" name="hpbp" value="${hplItem.value}">
						</div>
					</c:if>
					
					<c:if test="${hplItem.name=='血糖控制'}">
						<div class="content_hpl" style="background:${color[hplItem.level]};">
							<p class="content_font_hpl" style="color:${fontColor[hplItem.level]};">您上次所填寫的的血糖值平均為 ${bgVO.bgBfMeat}/${bgVO.bgAfMeat}/${bgVO.bgBfSleep} mg/dl ${hplItem.status}</p>
							<p class="content_font_hpl" style="color:${fontColor[hplItem.level]};">標準血糖值為 100/140/110 mmHg</p>
						</div>
						<div class="suggest_hpl" style="">
							<p class="suggest_font_hpl" style="">建議您 每日測量血糖並記錄  ${hplItem.value} 次以上</p>
							<input type="hidden" name="hpbg" value="${hplItem.value}">
						</div>
					</c:if>
					
					<c:if test="${hplItem.name=='每周運動量'}">
						<c:set var="sp" value="本周運動 ${sportDur} 分鐘" />
						<div class="content_hpl" style="background:${color[hplItem.level]};">
							<p class="content_font_hpl" style="color:${fontColor[hplItem.level]};">${sportDur==0?'本周無運動紀錄':sp} ${hplItem.status}</p>
							<p class="content_font_hpl" style="color:${fontColor[hplItem.level]};">每周運動次數應達 ${hplItem.value} 次</p>
						</div>
						<div class="suggest_hpl" style="">
							<p class="suggest_font_hpl" style="">建議您 每周運動次數達  ${hplItem.value} 次, 每次 30 分鐘以上</p>
							<input type="hidden" name="hpsp" value="${hplItem.value}">
						</div>
					</c:if>
				</div>
			</c:if>
		</c:forEach>
		<div class="well outter_button" style="">
			<button class="btn btn-default col-sm-12" id="insertBtn_addHplan" style="margin-bottom:10px;font-size:20px;color:#0e9ea3;font-weight:bold;">
			 	新增
			</button>
			<input type="hidden" name="action" value="insertHplan">
			<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
			<a class="btn btn-default col-sm-12" id="" href="<%=request.getContextPath()%>/hpl/HplServlet.do?action=main&requestURL=<%=request.getServletPath()%>" role="button" style="margin-bottom:20px;font-size:20px;color:#4b76b5;font-weight:bold;">
		 		返回
			</a>
		</div>
	</form>

</body>

</html>