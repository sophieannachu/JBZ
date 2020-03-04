<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JBZ呷百利-自我健檢</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery_ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/sweetalert.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_ui_1.10.3.js"></script>

<!-- header&footer&reset css檔 & js檔 -->
<script src="<%=request.getContextPath()%>/front-end/js/header.js"></script>

<link href="<%=request.getContextPath()%>/front-end/css/header.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/reset.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/footer.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">


<script src="<%=request.getContextPath()%>/front-end/js/healthcheck/timepicker.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/healthcheck/timepicker1.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/highchart.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/highcharts_export.js"></script>

<link href="<%=request.getContextPath()%>/front-end/css/healthcheck/timepicker.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/healthcheck/healthCheck.css" rel="stylesheet">


<script>
	$(function() {
		$("#tabs").tabs();
		<c:if test="${param.action==\"basicCheck\"||param.action==\"getOne_For_Update\"||param.action==\"insert\"||param.action==\"update\"}">
		$(".basicCheck").addClass("active");
		</c:if>
		<c:if test="${param.action==\"bgCheck\"||param.action==\"insert_BG\"||param.action==\"getOne_For_Update_BG\"||param.action==\"update_BG\"}">
		$(".bgCheck").addClass("active");
		</c:if>
		<c:if test="${param.action==\"bpCheck\"||param.action==\"getOne_For_Update_BP\"||param.action==\"insert_BP\"||param.action==\"update_BP\"}">
		$(".bpCheck").addClass("active");
		</c:if>
		<c:if test="${param.action==\"sleepCheck\"||param.action==\"getOne_For_Update_Sleep\"||param.action==\"insert_Sleep\"||param.action==\"update_Sleep\"}">
		$(".sleepCheck").addClass("active");
		</c:if>
	});
</script>
<style>
body {
	font-family: "微軟正黑體";
}
*{
	font-size:18px;
}
h2{
	font-size:28px;
	font-weight:900;
}
</style>
</head>
<body>
	<div>
		<jsp:include page="/front-end/header.jsp" />
	</div>
	<div class="container-fluid title-bar">
			<div class="container" style="color:#fff; font-size:20px;padding-left: 30px;font-weight:bold;font-family: '微軟正黑體';">
					<h1>自 我 健 檢</h1>
			</div>
	</div>

	<div class="container main">
		<div class="col-xs-3 col-sm-3 nav-wrap">
			<div class="list-group sub-nav-wrap">
				<a href="<%=request.getContextPath()%>/front-end/healthCheck.jsp?action=basicCheck" class="list-group-item basicCheck">身高體重腰圍體脂</a>
				<a href="<%=request.getContextPath()%>/front-end/healthCheck.jsp?action=bpCheck" class="list-group-item bpCheck">血壓</a>
				<a href="<%=request.getContextPath()%>/front-end/healthCheck.jsp?action=bgCheck" class="list-group-item bgCheck">血糖</a>
				<a href="<%=request.getContextPath()%>/front-end/healthCheck.jsp?action=sleepCheck" class="list-group-item sleepCheck">睡眠紀錄</a>
			</div>
		</div>
		<div class="col-xs-9 col-sm-9 context">
			<!-- 			action條件符合時include基本健檢頁面 -->
			<c:if test="${param.action==\"basicCheck\"||param.action==\"update\"||param.action==\"insert\"}">
				<jsp:include page="/front-end/basiccheck/basicCheck.jsp" />
			</c:if>
			<!-- 			include基本健檢更新資料頁面 -->
			<c:if test="${param.action==\"getOne_For_Update\"}">
				<jsp:include page="/front-end/basiccheck/update_basicCheck_input.jsp" />
			</c:if>
			<!-- 			action條件符合時include血糖健檢頁面 -->
			<c:if test="${param.action==\"bgCheck\"||param.action==\"insert_BG\"||param.action==\"update_BG\"}">
				<jsp:include page="/front-end/bgcheck/bgCheck.jsp" />
			</c:if>
			<!-- 			include血糖健檢更新資料頁面 -->
			<c:if test="${param.action==\"getOne_For_Update_BG\"}">
				<jsp:include page="/front-end/bgcheck/update_bgCheck_input.jsp" />
			</c:if>
			<!-- 			action條件符合時include血壓健檢頁面 -->
			<c:if test="${param.action==\"bpCheck\"||param.action==\"update_BP\"||param.action==\"insert_BP\"}">
				<jsp:include page="/front-end/bpcheck/bpCheck.jsp" />
			</c:if>
			<!-- 			include血壓健檢更新資料頁面 -->
			<c:if test="${param.action==\"getOne_For_Update_BP\"}">
				<jsp:include page="/front-end/bpcheck/update_bpCheck_input.jsp" />
			</c:if>
			<!-- 			action條件符合時include睡眠健檢頁面 -->
			<c:if test="${param.action==\"sleepCheck\"||param.action==\"update_Sleep\"||param.action==\"insert_Sleep\"}">
				<jsp:include page="/front-end/sleepcheck/sleepCheck.jsp" />
			</c:if>
			<!-- 			include睡眠健檢更新資料頁面 -->
			<c:if test="${param.action==\"getOne_For_Update_Sleep\"}">
				<jsp:include page="/front-end/sleepcheck/update_sleepCheck_input.jsp" />
			</c:if>
		</div>
	</div>

	<div>
		<jsp:include page="/front-end/footer.jsp" />
	</div>

</body>
</html>