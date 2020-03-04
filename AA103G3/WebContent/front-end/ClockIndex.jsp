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

<!-- header&footer&reset cssÀÉ & jsÀÉ -->
<script src="<%=request.getContextPath()%>/front-end/js/header.js"></script>

<link href="<%=request.getContextPath()%>/front-end/css/header.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/reset.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/footer.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/front-end/clock/Cjs/bootstrap.min.js"></script>

<!-- bsicon cssÀÉ & jsÀÉ -->
<link href="<%=request.getContextPath()%>/front-end/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">

<!-- ¾xÄÁ cssÀÉ & jsÀÉ -->
<link href="<%=request.getContextPath()%>/front-end/css/clock/material.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/front-end/clock/Cjs/moment-with-locales.min.js"></script>
<link href="<%=request.getContextPath()%>/front-end/clock/Ccss/bootstrap-material-datetimepicker.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/front-end/clock/Cjs/bootstrap-material-datetimepicker.min.js"></script>

<title>JBZ©E¦Ê§Q-°·±d¾xÄÁ</title>

</head>
<style>
body {
	font-family: "·L³n¥¿¶ÂÅé";
}
.list-group-item:first-child {
	border-radius: 0;
}

.list-group-item:last-child {
	border-radius: 0;
}
.main{
	border-left: solid 1px #c4d4c4; 
	border-right: solid 1px #c4d4c4;
	margin-top: 30px; 
	margin-bottom: 80px; 
}
.list-group{
	 font-size:18px;
}
*{
	font-size:18px;
}
h3{
	font-size:28px;
	font-weight:900;
	margin-bottom:10px;
}
</style>

<body>

<div>
<jsp:include page="header.jsp"></jsp:include>
<div class="container-fluid title-bar">
			<div class="container" style="color:#fff; font-size:20px;padding-left: 30px;font-weight:bold;font-family: '·L³n¥¿¶ÂÅé';">
					<h1>°· ±d ¾x ÄÁ</h1>
			</div>
		</div>
</div>
<div class="container">
	<jsp:include page="/front-end/clock/clockindex.jsp"></jsp:include>
</div>
<div><jsp:include page="footer.jsp"></jsp:include>
</div>
</body>

</html>