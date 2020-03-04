<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/wraper.css">

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
				<img src="<%=request.getContextPath()%>/back-end/img/pic.jpg">
				<div>
					<c:if test="${param.action==\"update_heinfo_input\"}">
						<div>
							 <jsp:include page="/back-end/heinfo/update_heinfo_input.jsp" />
						</div>
					</c:if>
					<c:if test="${param.action==\"heinfoIndex\"}">
						<div>
							<jsp:include page="/back-end/heinfo/heinfoadd.jsp"></jsp:include>
							<jsp:include page="/back-end/heinfo/listAllHeinfo.jsp"></jsp:include>
						</div>
					</c:if>
				</div>
			</div>
			</main>
			
			<!--end main -->

			<!--footer-->
		<jsp:include page="footer.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>