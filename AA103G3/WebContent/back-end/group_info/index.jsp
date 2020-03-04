<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="BIG5"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="css/wraper.css">
<link rel="stylesheet" href="emp.css">
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
				<img src="img/pic.jpg">
				<div>
					<jsp:include page="emp.html"></jsp:include>
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