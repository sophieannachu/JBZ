<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	#empPic img{
		margin-top: 10%;
		width:10%;
	
	}

</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/group_info/css/wraper.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/group_info/css/bootstrap.css">

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
			<div >
				<img src="<%=request.getContextPath()%>/back-end/img/pic.jpg">
				<h2>JBZ員工清單</h2>
				<div id="empPic">
					<img src="<%=request.getContextPath()%>/back-end/img/emp01.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp02.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp03.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp04.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp05.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp06.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp07.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp08.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp09.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp10.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp11.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp12.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp13.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp14.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp15.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp16.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp17.png">
					<img src="<%=request.getContextPath()%>/back-end/img/emp18.png">
					
					
				</div>
			</div>
			</main>
			<!--end main -->

			<!--footer-->
			<jsp:include page="/back-end/footer.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>