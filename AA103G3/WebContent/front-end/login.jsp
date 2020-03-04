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
<title>Title Page</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/css/sweetalert2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery_ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">

<script src="<%=request.getContextPath()%>/front-end/js/sweetalert.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_ui_1.10.3.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/navigator.css">
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		

<script type="text/javascript">
	$(document).ready(function() {
		$(".hamburger-menu").click(function() {
			if ($(".hamburger-menu").hasClass("hamburger-menu-close")) {
				$(".navigatorxs").addClass("open");
				$(".menuss").removeClass("hidden");
				$(this).addClass("hamburger-menu-open");
				$(this).removeClass("hamburger-menu-close");
			} else {
				$(".navigatorxs").removeClass("open");
				$(".menuss").addClass("hidden");
				$(this).removeClass("hamburger-menu-open");
				$(this).addClass("hamburger-menu-close");
			}
		});
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
	<div class="container-fluid mainHeader" id="header">
		<div class="row">
			<div class="navigatorxs visible-xs">
				<!-- 			<div class="navigator"> -->
				<!-- menu的背景 -->
				<div class="navxs visible-xs">
					<!-- logo for mobile -->
					<div class="visible-xs">
						<div class="col-xs-4 col-sm-4 logo visible-xs">
							<img src="<%=request.getContextPath()%>/front-end/image/logo.png" style="width: 40px;">
						</div>
						<!-- menu for mobile -->

						<div class="col-xs-8 col-sm-8 visible-xs">
							<div class="menu-xs">
								<div class="hamburger-menu hamburger-menu-close">
									<p>Menu</p>
									<div class="hamburger-icon-open">
										<img src="<%=request.getContextPath()%>/front-end/image/menu-alt-512.png">
									</div>
									<div class="hamburger-icon-close hidden">
										<img src="<%=request.getContextPath()%>/front-end/image/x-icon-.png.png">
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 hidden menuss">
							<a href="">關於我們</a>
						</div>
						<div class="col-xs-12 hidden menuss">
							<a href="">APP</a>
						</div>
						<div class="col-xs-12 hidden menuss">
							<a href="">健康分析</a>
						</div>
						<div class="col-xs-12 hidden menuss">
							<a href="">健康資訊</a>
						</div>
						<div class="col-xs-12 hidden menuss">
							<a href="">登入</a>
						</div>
					</div>
				</div>
			</div>
			<!-- menu for PC -->
			<div class="navigatorsm">
				<div class="navsm hidden-xs hidden-xs">
					<div class="navBack hidden-xs"></div>

					<div class="col-xs-6 col-sm-6 logo hidden-xs">
						<a href="intext_RWDver3.html"> <img src="<%=request.getContextPath()%>/front-end/image/logo.png">
							<img src="<%=request.getContextPath()%>/front-end/image/slogan.png"
							style="height: 35px; width: auto; margin-top: 40px; margin-left: 5px;">
						</a>
					</div>
					<div class="col-xs-6 col-sm-6 hidden-xs" style="">
						<div class="menu">
							<ul>
								<li><a href="" class="menuhover">關於我們</a></li>
								<li><a href="" class="menuhover">APP</a></li>
								<li><a href="" class="menuhover">健康分析</a></li>
								<li><a href="" class="menuhover">健康資訊</a></li>
								<li><a href="" class="menuhover">登入</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12" style="position:absolute;top:40%;left:40%;">
		<form method="post" action="<%=request.getContextPath()%>/mem/login.do">
			<table>
				<tr>
					<td>帳號：</td>
					<td><input type="text" name="account" value="jbz01"></td>
				</tr>
				<tr>
					<td>密碼：</td>
					<td><input type="text" name="password" value="jbz01"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;"><button type="submit" name="login">登入</button><a href="<%=request.getContextPath()%>/front-end/mem/register_mem.jsp"><button type="button" name="signup">註冊</button></a></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="login">
		</form>		
	</div>
</body>
</html>