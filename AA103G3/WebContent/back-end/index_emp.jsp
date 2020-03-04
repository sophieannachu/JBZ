<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/sweetalert2.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/wraper.css">
<script src="<%=request.getContextPath()%>/back-end/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/sweetalert2.min.js"></script>
</head>

<body>
	<div class="ie-fix">
		<div class="wrapper">
			<!--head-->
			<jsp:include page="head.jsp"></jsp:include>
			<!--navigation-->
			<jsp:include page="navigation.jsp"></jsp:include>
	
				<!-- main -->
				<main role="main" class="content">
					<div>
						<img src="<%=request.getContextPath()%>/back-end/img/pic.jpg" style="margin-bottom:30px;">
					</div>
					<c:if test="${param.action!=\"insertView\" && param.action!=\"updateView\"}">
						<jsp:include page="/back-end/emp/listAll_emp.jsp"></jsp:include>
					</c:if>
					
					<c:if test="${param.action==\"insertView\"}">
						<jsp:include page="/back-end/emp/addEmp.jsp"></jsp:include>
					</c:if>
					
					<c:if test="${param.action==\"updateView\"}">
						<jsp:include page="/back-end/emp/updateEmp.jsp"></jsp:include>
					</c:if>
					
<%-- 					<c:if test="${param.action==\"main\"}"> --%>
<%-- 					</c:if> --%>
				</main>
				<!--end main -->
	
			<!--footer-->
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>