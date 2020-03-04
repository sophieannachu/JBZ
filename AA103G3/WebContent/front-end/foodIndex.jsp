<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/css/sweetalert2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/sweetalert.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_1.9.1.js"></script>

<!-- header&footer&reset css檔 & js檔 -->
<script src="<%=request.getContextPath()%>/front-end/js/header.js"></script>

<link href="<%=request.getContextPath()%>/front-end/css/header.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/reset.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/footer.css" rel="stylesheet">
<!-- bsicon css檔 & js檔 -->
<link href="<%=request.getContextPath()%>/front-end/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">


<script src="<%=request.getContextPath()%>/front-end/foodlist/js/loader.js"></script>


<!-- 分析圖跟日期 css檔 & js檔 -->
<script src="<%=request.getContextPath()%>/front-end/foodlist/js/bootstrap-datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/foodlist/js/bootstrap.min.js"></script>

<title>JBZ呷百利-營養日記</title>
<style>
body {
	font-family: "微軟正黑體";
}
*{
	font-size:18px;
}
</style>
</head>
<body>
<div>
<jsp:include page="header.jsp"></jsp:include>

</div>
		<div class="container-fluid title-bar">
			<div class="container" style="color:#fff; font-size:20px;padding-left: 30px;font-weight:bold;font-family: '微軟正黑體';">
					<h1 >營 養 日 記</h1>
			</div>
	</div>
<div class="container">
	<jsp:include page="/front-end/foodlist/foodindex.jsp"></jsp:include>
</div>
<div><jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>