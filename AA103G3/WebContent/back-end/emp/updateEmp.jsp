<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.util.List"%>
<%
EmpVO empVO=(EmpVO)request.getAttribute("empVO");
List<Integer> authList=(List<Integer>)request.getAttribute("authList");
%>
<jsp:useBean id="funcSv" scope="page" class="com.func.model.FuncService" />
<html>
<head>
<title>updateEmp.jsp</title>
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" /> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script> -->
<script src="<%=request.getContextPath()%>/back-end/emp/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/emp/css/datepicker.min.css" />
<script src="<%=request.getContextPath()%>/back-end/emp/js/bootstrap-datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/emp/js/preview.js"></script>
<style type="text/css">
 .form-group>label {
	font-size: 16px;
	font-weight: bold;
 }
 .hide_img {
  	position: absolute;
  	bottom: 35px;
  	left: 600px;
   	display: none;
  	z-index: 500;
 }
</style>
<script>
	$(document).ready(function() {
		$("#datePicker").datepicker({
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayHighlight: true
		});
		
		$(".preview").hover(function(){
			$(".hide_img").css("display", "block");
			},function(){
			$(".hide_img").css("display", "none");
		});
		
		$("#submit_update").click(function(e){
			e.preventDefault();
			var errCount=0;
			
			var name=$("#name").val();
			if(name.length<=0){
				errCount++;
				$("#name_Err").html("<i class='fa fa-times'></i> <b>請輸入姓名</b>").removeClass("hidden");
			}else{
				$("#name_Err").addClass("hidden");
			}
			
			var sex=$("input[name=sex]:checked").val();
			if(sex===undefined || sex.length<=0){
				errCount++;
				$("#sex_Err").html("<i class='fa fa-times'></i> <b>請選擇性別</b>").removeClass("hidden");
			}else{
				$("#sex_Err").addClass("hidden");
			}
			
			var birth=$("#birth").val();
			if(birth.length<=0){
				errCount++;
				$("#birth_Err").html("<i class='fa fa-times'></i> <b>請選擇生日</b>").removeClass("hidden");
			}else{
				$("#birth_Err").addClass("hidden");
			}
			
			var phone=$("#phone").val();
			if(phone.length<=0){
				errCount++;
				$("#phone_Err").html("<i class='fa fa-times'></i> <b>請輸入手機</b>").removeClass("hidden");
			}else if(phone.length<10){
				errCount++;
				$("#phone_Err").html("<i class='fa fa-times'></i> <b>請重新輸入手機</b>").removeClass("hidden");
			}else{
				$("#phone_Err").addClass("hidden");
			}
			
			var email=$("#email").val();
			if(email.length<=0){
				errCount++;
				$("#email_Err").html("<i class='fa fa-times'></i> <b>請輸入信箱</b>").removeClass("hidden");
			}else if(email.indexOf('@')==-1 || email.indexOf('.')==-1){
				errCount++;
				$("#email_Err").html("<i class='fa fa-times'></i> <b>請重新輸入信箱</b>").removeClass("hidden");
			}else{
				$("#email_Err").addClass("hidden");
			}
			
			if(errCount!=0){
				return false;
			}else{
				$("form").submit();
			}
		});
		
		$("#submit_pwd").click(function(e){
			var oldPwd=$("#old_Pwd").val();
			if(oldPwd!="${empVO.pwd}"){
				$("#old_Pwd_Err").html("<i class='fa fa-times'></i> <b>密碼不符</b>").removeClass("hidden");
				return;
			}else{
				$("#old_Pwd_Err").addClass("hidden");
			}
			var pwd=$("#pwd").val();
			var pwd2=$("#pwd2").val();
			if(pwd.length<6){
				$("#pwd_Err").html("<i class='fa fa-times'></i> <b>請輸入6位數以上之密碼</b>").removeClass("hidden");
				return;
			}else{
				$("#pwd_Err").addClass("hidden");
			}
			if(pwd.length!=pwd2.length){
				$("#pwd2_Err").html("<i class='fa fa-times'></i> <b>確認密碼不符</b>").removeClass("hidden");
				return;
			}else{
				$("#pwd2_Err").addClass("hidden");
			}
			$("#pwd_Modal").modal("hide");
		});
		
		$('button[data-dismiss=modal]').click(function() {
			$("#old_Pwd").val("");
			$("#pwd").val("");
			$("#pwd2").val("");
			$("#old_Pwd_Err").addClass("hidden");
			$("#pwd_Err").addClass("hidden");
			$("#pwd2_Err").addClass("hidden");
		});
	});
</script>
</head>
<body>

<!-- 	<div class="container"> -->
<!-- 		<div class="row"> -->
			<div class="page-header text-center">
				<h1>
					<b>員工資料修改</b>
				</h1>
			</div>
<!-- 			<div class="col-sm-1"></div> -->
<!-- 			<div class="col-sm-12"> -->
				<div class="well col-sm-12 text-left">
					<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet.do"
						enctype="multipart/form-data" class="form-horizontal" role="form">
						
						<div class="form-group text-right">
							<label style="font-size:20px; font-weight:bold; color:black; margin-right:20px;">員工編號 <%= empVO.getEmpno()%></label>
						</div>

						<div class="form-group">
							<label for="acc" class="col-sm-2 control-label">帳號</label>
							<div class="col-sm-10">
								<input type="text" name="acc" id="acc" class="form-control"
									value="<%= empVO.getAcc()%>" readonly="true"/>
							</div>
						</div>

						<div class="form-group">
							<label for="pwd" class="col-sm-2 control-label">密碼</label>
							<div class="col-sm-10 text-left">
								<button type="button" class="btn btn-default" data-toggle="modal" data-target="#pwd_Modal" data-backdrop="false" data-keyboard="false">
									更改密碼
								</button>
							</div>
						</div>

						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-3">
								<input type="text" name="name" id="name" class="form-control"
									style="width: 200px;"
									value="<%= empVO.getName()%>" />
								<%-- ${empVO.name} --%>
							</div>
							<div class="col-sm-7">
								<label class="control-label hidden" id="name_Err" style="color: red;"></label>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">性別</label>
							<div class="col-sm-3 text-left">
								<label class="radio-inline"> <input type="radio"
									name="sex" value="1" ${empVO.sex == '1' ? "checked" : "" } />男
								</label> <label class="radio-inline"> <input type="radio"
									name="sex" value="0" ${empVO.sex == '0' ? "checked" : "" } />女
								</label>
							</div>
							<div class="col-sm-7">
								<label class="control-label hidden" id="sex_Err" style="color: red;"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="birth" class="col-sm-2 control-label">生日</label>
							<div class="col-sm-3">
								<div class="input-group input-append date" id="datePicker">
									<input type="text" class="form-control" name="birth" id="birth"
										style=""
										value="<%= empVO.getBirth()%>" /> <span
										class="input-group-addon add-on"><span
										class="fa fa-calendar"></span></span>
								</div>
							</div>
							<div class="col-sm-7">
								<label class="control-label hidden" id="birth_Err" style="color: red;"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="phone" class="col-sm-2 control-label">手機</label>
							<div class="col-sm-3">
								<input type="text" name="phone" id="phone" class="form-control"
									style="width: 200px;"
									value="<%= empVO.getPhone()%>" />
							</div>
							<div class="col-sm-7">
								<label class="control-label hidden" id="phone_Err" style="color: red;"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="col-sm-2 control-label">電子信箱</label>
							<div class="col-sm-3">
								<input type="email" name="email" id="email" class="form-control"
									style="width: 200px;"
									value="<%= empVO.getEmail()%>" />
							</div>
							<div class="col-sm-7">
								<label class="control-label hidden" id="email_Err" style="color: red;"></label>
							</div>
						</div>

						<div class="form-group">
							<label for="photo" class="col-sm-2 control-label">大頭照</label>
							<div class="col-sm-3">
								<input type="file" id="photo" name="photo" style="margin-top: 6px; width: 200px;">
							</div>
							<div class="col-sm-1">
								<img src="<%=request.getContextPath()%>/emp/ShowImg_session" class="preview" style="width:30px;height:30px;">
							</div>
							<img src="<%=request.getContextPath()%>/emp/ShowImg_session" width="250" height="250" class="hide_img" style="width:250px;height:250px;">
<%-- 							<img src="ShowImg_empno?empno=${empVO.empno}" width="250" height="250" class="hide_img"> --%>
						</div>

<!-- 						<div class="form-group"> -->
<!-- 							<label class="col-sm-2 control-label">權限</label> -->
<!-- 							<div class="col-sm-10"> -->
<%-- 								<c:forEach var="funcVO" items="${funcSv.allFunc}"> --%>
<!-- 								<label class="checkbox-inline">  -->
<%-- 									<input type="checkbox" name="auth" value="${funcVO.funcno}" ${authList.contains(funcVO.funcno) ? "checked" : "" }/>${funcVO.name} --%>
									
<!-- 								</label> -->
<%-- 							</c:forEach> --%>
<!-- 							</div> -->
<!-- 						</div> -->
				</div>
				<div class="page_clear" style="height: 20px;"></div>
				<div class="col-sm-12 text-center" style="margin-bottom: 5%;">
					<a class="btn btn-primary" href="<%=request.getContextPath()%>/back-end/index_emp.jsp?whichPage=<%= request.getParameter("whichPage")%>" role="button"><span
						class="fa fa-times"></span> 取消</a>
					<button class="btn btn-danger" id="submit_update">
						<i class="fa fa-check"></i> 確認送出
					</button>
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="empno" value="<%=empVO.getEmpno()%>"> 
					<input type="hidden" name="reqSourceURL" value="<%= request.getParameter("reqSourceURL")%>"> 
					<input type="hidden" name="whichPage" value="<%= request.getParameter("whichPage")%>"> 
					<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
				</div>
				<div class="modal fade" id="pwd_Modal" tabindex="-1" role="dialog" aria-labelledby="pwd_ModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							    	<span aria-hidden="true">&times;</span>
								</button>
							    <h4 class="modal-title text-center" id="pwd_ModalLabel"><b>更改員工密碼</b></h4>
							</div>
							<div class="modal-body text-left">
								<div class="row" style="margin-bottom:10px;margin-right:10px;">
									<div class="form-group">
										<label for="old_Pwd" class="col-sm-2 control-label">舊密碼</label>
										<div class="col-sm-10">
											<input type="password" name="old_Pwd" id="old_Pwd" class="form-control" value="" />
										</div>
										<div class="col-sm-2"></div>
										<div class="col-sm-10">
											<label class="control-label hidden" id="old_Pwd_Err" style="color: red;font-size:16px;"></label>
										</div>
									</div>
								</div>
					
								<div class="row" style="margin-bottom:10px;margin-right:10px;">
									<div class="form-group">
										<label for="pwd" class="col-sm-2 control-label">新密碼</label>
											<div class="col-sm-10">
												<input type="password" name="pwd" id="pwd" class="form-control" value="" />
											</div>
											<div class="col-sm-2"></div>
											<div class="col-sm-10">
												<label class="control-label hidden" id="pwd_Err" style="color: red;font-size:16px;"></label>
											</div>
									</div>
								</div>
					
								<div class="row" style="margin-bottom:10px;margin-right:10px;">
									<div class="form-group">
										<label for="pwd2" class="col-sm-2 control-label">確認密碼</label>
										<div class="col-sm-10">
											<input type="password" name="pwd2" id="pwd2" class="form-control" value="" />
										</div>
										<div class="col-sm-2"></div>
										<div class="col-sm-10">
											<label class="control-label hidden" id="pwd2_Err" style="color: red;font-size:16px;"></label>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" id="cancell_pwd" data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-danger" id="submit_pwd">儲存</button>
							</div>
						</div>
					</div>
				</div>
				</form>
<!-- 			</div> -->
<!-- 			<div class="col-sm-4" width="500"> -->
<%-- 				<c:if test="${not empty errMsgs}"> --%>
<!-- 					<ul><font color="red" size="6" face="標楷體"> -->
<%-- 					<c:forEach var="msg" items="${errMsgs}"> --%>
<%-- 						<li>${msg}</li> --%>
<%-- 					</c:forEach> --%>
<!-- 					</ul></font> -->
<%-- 				</c:if> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->

<!-- 						<div class="form-group"> -->
<!-- 							<label for="old_Pwd" class="col-sm-2 control-label">舊密碼</label> -->
<!-- 							<div class="col-sm-10"> -->
<!-- 								<input type="password" name="old_Pwd" id="old_Pwd" class="form-control" -->
<%-- 									value="<%= empVO.getPwd()%>" /> --%>
<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 						<div class="form-group"> -->
<!-- 							<label for="pwd" class="col-sm-2 control-label">新密碼</label> -->
<!-- 							<div class="col-sm-10"> -->
<!-- 								<input type="password" name="pwd" id="pwd" class="form-control" -->
<%-- 									value="<%= empVO.getPwd()%>" /> --%>
<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 						<div class="form-group"> -->
<!-- 							<label for="pwd2" class="col-sm-2 control-label">確認密碼</label> -->
<!-- 							<div class="col-sm-10"> -->
<!-- 								<input type="password" name="pwd2" id="pwd2" class="form-control" -->
<%-- 									value="<%= empVO.getPwd()%>" /> --%>
<!-- 							</div> -->
<!-- 						</div> -->

</body>
</html>