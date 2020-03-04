<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<html>
<head>
<title>login.jsp</title>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<script src="<%=request.getContextPath()%>/back-end/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		var winH=$(window).height();
		$(".container").css("margin-top",(winH*0.2) + "px");
	});
	
	function magic(){
		var acc="a123456a", pwd="a123456a";
		$("#acc").val(acc);
		$("#pwd").val(pwd);
	}
	function magic2(){
		var acc="aa103g3", pwd="aa103g3";
		$("#acc").val(acc);
	}
</script>
</head>
<body style="background:;">
	<div class="container" style="padding:0 0;">
<!-- 		<div class="row"> -->
			<div class="col-sm-4"></div>
			
			<div class="well col-sm-4" style="height:;width:;">
				<img src="<%=request.getContextPath()%>/back-end/img/people.png" style="width: 99%;">
				<div class="col-sm-12" style="margin-top:50px;">
					<form method="post" action="<%=request.getContextPath()%>/login/LoginServlet.do">
					<div class="form-group">
<!-- 						<label for="acc" class="col-sm-2 control-label" style="margin-top:8px;">帳號</label> -->
						<div class="">
							<input type="text" name="acc" id="acc" class="form-control" placeholder="帳號" value="${acc}"/>
						</div>
					</div>
					<div class="form-group">
<!-- 						<label for="pwd" class="col-sm-2 control-label" style="margin-top:8px;">密碼</label> -->
						<div class="">
							<input type="password" name="pwd" id="pwd" class="form-control" placeholder="密碼" value="${pwd}"/>
						</div>
					</div>
					
<!-- 					<div class="col-sm-12" style="margin-top: 30px;"> -->
<!-- 						<a class="btn btn-default" href="" role="button" id="reset"> 重填</a> -->
						<button class="btn btn-default col-sm-12" style="background:#3897f0;color:white;font-weight:bold;font-size:16px;">
							登入
	<!-- 						<i class="glyphicon glyphicon-ok"></i> 登入 -->
						</button>
						<a class="btn btn-default col-sm-12" href="#" role="button" onclick="magic();" style="margin-top:5px;">按我(boss)</a>
						<a class="btn btn-default col-sm-12" href="#" role="button" onclick="magic2();" style="margin-top:5px;">按我(emp)</a>
						<input type="hidden" name="action" value="login">
<!-- 					</div> -->
<!-- 					<div class="form-group col-sm-12"> -->
<!-- 						<div class="col-sm-2"></div> -->
						<label class="col-sm-12 control-label text-center" style="height:10px;margin-top:20px;margin-bottom:30px; color:red; font-size:18px;">${error}</label>
						<% session.removeAttribute("error");%>
						<% session.removeAttribute("acc");%>
						<% session.removeAttribute("pwd");%>
<!-- 					</div> -->
				</div>
				</form>
			</div>
<!-- 		</div> -->
	</div>
</body>
</html>