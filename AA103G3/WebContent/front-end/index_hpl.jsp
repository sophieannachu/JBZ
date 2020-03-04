<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	double reSubmitCode=Math.random()*10;
	session.setAttribute("reSubmitCode", reSubmitCode);
%>
<html>
<head>
<title>JBZ呷百利-健康計畫</title>
<!-- 共用外掛 css檔 & js檔-->
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
<script>
	$(function() {
		<c:if test="${param.action==\"main\"||param.action==\"hplDetail\"}">
		$(".main_hpl").addClass("active");
		</c:if>
		<c:if test="${param.action==\"addHplan\"}">
		$(".addHplen_hpl").addClass("active");
		</c:if>
		<c:if test="${param.action==\"addCplan\"}">
		$(".addCplen_hpl").addClass("active");
		</c:if>
		<c:if test="${param.action==\"past\"||param.action==\"pastDetail\"}">
		$(".past_hpl").addClass("active");
		</c:if>
	});
</script>
<style>
.main{
	border-left: solid 1px #c4d4c4; 
	border-right: solid 1px #c4d4c4;
	margin-top: 30px; 
	margin-bottom: 40px; 
}
.list-group{
	 font-size:18px;
}
body {
	font-family: "微軟正黑體";
}
</style>

</head>
<body>
	<div>
		<jsp:include page="/front-end/header.jsp" />
	</div>
	<div class="container-fluid title-bar">
			<div class="container" style="color:#fff; font-size:20px;padding-left: 30px;font-weight:bold;font-family: '微軟正黑體';">
					<h1>健 康 計 畫</h1>
			</div>
		</div>
</div>
	
	
	<div class="container main">
		<div class="col-sm-3" style="padding:0 10px;">
			<div class="list-group">
				<a href="<%=request.getContextPath()%>/hpl/HplServlet.do?action=main&requestURL=<%=request.getServletPath()%>" class="list-group-item main_hpl" style="border-radius: 0;">目前計畫</a>
				<a href="<%=request.getContextPath()%>/hpl/HplServlet.do?action=addHplan&requestURL=<%=request.getServletPath()%>" class="list-group-item addHplen_hpl" style="border-radius: 0;">新增推薦計畫</a>
				<a href="<%=request.getContextPath()%>/front-end/index_hpl.jsp?action=addCplan&requestURL=<%=request.getServletPath()%>" class="list-group-item addCplen_hpl" style="border-radius: 0;">新增自訂計畫</a>
				<a href="<%=request.getContextPath()%>/hpl/HplServlet.do?action=past&requestURL=<%=request.getServletPath()%>" class="list-group-item past_hpl" style="border-radius: 0;">歷史計畫</a>
			</div>
		</div>
		
		<div class="col-sm-9" style="border-left: solid 1px #c4d4c4;">
			<c:if test="${param.action==\"main\"}">
				<jsp:include page="/front-end/hpl/showCurrently_hpl.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.action==\"addHplan\"}">
				<jsp:include page="/front-end/hpl/addHplan.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.action==\"addCplan\"}">
				<jsp:include page="/front-end/hpl/addCplan.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.action==\"hplDetail\"}">
				<jsp:include page="/front-end/hpl/hplDetail.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.action==\"past\"}">
				<jsp:include page="/front-end/hpl/showPast_hpl.jsp"></jsp:include>
			</c:if>
			<c:if test="${param.action==\"pastDetail\"}">
				<jsp:include page="/front-end/hpl/pastDetail.jsp"></jsp:include>
			</c:if>
		</div>
		
	</div>
		
		
		
	<jsp:include page="/front-end/footer.jsp"></jsp:include>
</body>
</html>