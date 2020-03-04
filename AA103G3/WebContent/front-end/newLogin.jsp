<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*" %>

<c:if test="${! empty memVO.memno}">
	<jsp:forward page="/front-end/homePage.jsp" />
</c:if>

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>呷百利-登入</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/css/sweetalert2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery_ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
<script src="<%=request.getContextPath()%>/front-end/js/sweetalert.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_ui_1.10.3.js"></script>

<link href="<%=request.getContextPath()%>/front-end/css/loginMain.css" rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {
		<c:if test="${not empty errorMsgs}">
		var str="";
		<c:forEach var="message" items="${errorMsgs}">
			 str +="${message}\n";
		</c:forEach>
		swal(str);
		</c:if>
	});
	
// 	console.log("${memVO}");


</script>
</head>
<body>
	<div id="title" class="col-xs-12 col-sm-12">
	<a href="<%=request.getContextPath()%>/front-end/index.jsp">
		<img src="<%=request.getContextPath()%>/front-end/image/logo.png" class="jbz1">
		<img src="<%=request.getContextPath()%>/front-end/image/fontlogo.png" class="jbz2">
	</a>
	</div>
	<div class="col-xs-4 col-sm-4"></div>
	<div id="skin" class="col-xs-4 col-sm-4">
			<form class="form-horizontal" role="form" method="post" action="<%=request.getContextPath()%>/mem/login.do">
				<div class="form-group ">
					 
					<label for="inputName3" class="col-xs-2 col-sm-2 control-label">
						帳號 
					</label>
					<div class="col-xs-10 col-sm-10" >
						 <input type="text" class="form-control" id="inputName3" name="account">
					</div>
				</div>
				<div class="form-group">
					 
					<label for="inputPassword3" class="col-xs-2 col-sm-2 control-label">
						密碼  
					</label>
					<div class="col-xs-10 col-sm-10" >
						<input type="password" class="form-control" id="inputPassword3" name="password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-xs-10 col-sm-10">
						<div class="checkbox">
							<label>
								<p><input type="checkbox" value="on">記住我</p>
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-2 col-sm-2" >
					</div>
					<div class="col-xs-5 col-sm-5">
						<input type="hidden" name="action" value="login">
						<button type="submit" class="btn btn-default">
							登入
						</button>
						</div>
						<div class="col-xs-5 col-sm-5" class="item">
						<p>還沒註冊?<a href="<%=request.getContextPath()%>/front-end/mem/register_mem.jsp">註冊</a></p>
						<p><a href="">忘記密碼</a></p>

					</div>
				</div>
			</form>
 
</div>
<div class="col-xs-4 col-sm-4"></div>   
</body>
</html>