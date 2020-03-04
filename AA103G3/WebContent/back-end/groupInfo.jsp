<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/group_info/css/wraper.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/group_info/css/bootstrap.css">

</head>
<body>
<div class="ie-fix">
		<div class="wrapper">
			<!--head-->
			
		<jsp:include page="/back-end/head.jsp"></jsp:include>
			<!--navigation-->
		<jsp:include page="/back-end/navigation.jsp"></jsp:include>

			<!-- main -->
			<main role="main" class="content">
			<div>
				<img src="<%=request.getContextPath()%>/back-end/img/pic.jpg">
				<div>
					<jsp:include page="/back-end/group_info/groupMain.jsp"/>
					
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