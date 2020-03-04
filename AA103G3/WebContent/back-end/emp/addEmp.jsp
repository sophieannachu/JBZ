<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.auth.model.*"%>
<%@ page import="java.util.*"%>
<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
	List<AuthVO> authList= (List<AuthVO>) request.getAttribute("authList");
	Map<String, String> authMap= (HashMap<String, String>) request.getAttribute("authMap");

%>
<jsp:useBean id="funcSv" scope="page" class="com.func.model.FuncService" />
<html>
<head>
<title>addEmp.jsp</title>
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/emp/css/datepicker.min.css" />
<script src="<%=request.getContextPath()%>/back-end/emp/js/bootstrap-datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/emp/js/preview.js"></script>
<style type="text/css">
 .form-group>label{ 
 	font-size: 16px; 
 	font-weight: bold; 
 } 
 .hide_img{
  	position: absolute;
  	bottom: 75px;
  	left: 650px;
    display: none;
  	z-index: 500;
 }

</style>
<jsp:include page="/back-end/emp/registerCheck.jsp"></jsp:include>
</head>
<body>

<!-- 	<div class="container"> -->
<!-- 		<div class="row"> -->
			<div class="col-sm-12">
				<div class="page-header text-center">
					<h1>
						<b>員工資料新增</b>
					</h1>
				</div>
			</div>
<!-- 			<div class="col-sm-1"></div> -->
<!-- 			<div class="col-sm-12"> -->
				<div class="well col-sm-12 text-left" style="padding-top:40px;">
					<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet.do"
						enctype="multipart/form-data" class="form-horizontal" role="form">

						<div class="form-group">
							<label for="acc" class="col-sm-2 control-label" style="color:black;">帳號</label>
							<div class="col-sm-10">
								<input type="text" name="acc" id="acc" class="form-control"
									value="<%=(empVO == null) ? "" : empVO.getAcc()%>" oninput="acc_check();"/>
							</div>
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<label class="control-label hidden" id="acc_Err" style="color: red;font-weight:bold;"><i class='fa fa-times'></i> 請輸入5位數以上之帳號</label>
								<label class="control-label hidden" id="acc_Corr" style="color: green;font-weight:bold;"><i class="fa fa-check"></i> 此帳號可以使用</label>
							</div>
						</div>

<!-- 						<div class="form-group"> -->
<!-- 							<label for="pwd" class="col-sm-2 control-label">密碼</label> -->
<!-- 							<div class="col-sm-10"> -->
<!-- 								<input type="password" name="pwd" id="pwd" class="form-control" -->
<%-- 									value="<%=(empVO == null) ? "" : empVO.getPwd()%>" /> --%>
<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 						<div class="form-group"> -->
<!-- 							<label for="pwd2" class="col-sm-2 control-label">確認密碼</label> -->
<!-- 							<div class="col-sm-10"> -->
<!-- 								<input type="password" name="pwd2" id="pwd2" class="form-control" -->
<%-- 									value="<%=(empVO == null) ? "" : empVO.getPwd()%>" /> --%>
<!-- 							</div> -->
<!-- 						</div> -->

						<div class="form-group">
							<label for="name" class="col-sm-2 control-label" style="color:black;">姓名</label>
							<div class="col-sm-2">
								<input type="text" name="name" id="name" class="form-control"
									style="width: 180px;"
									value="<%=(empVO == null) ? "" : empVO.getName()%>" />
								<%-- ${empVO.name} --%>
							</div>
							<div class="col-sm-7">
								<label class="control-label hidden" id="name_Err" style="color: red;font-weight:bold;"><i class='fa fa-times'></i> 請輸入姓名</label>
								<label class="control-label hidden" id="name_Corr" style="color: green;font-weight:bold;"><i class="fa fa-check"></i></label>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label" style="color:black;">性別</label>
							<div class="col-sm-2 text-left">
								<label class="radio-inline"> 
									<input type="radio" name="sex" value="1" id="male" ${empVO.sex == '1' ? "checked" : "" } />男
								</label> 
								<label class="radio-inline"> 
									<input type="radio" name="sex" value="0" id="female" ${empVO.sex == '0' ? "checked" : "" } />女
								</label>
							</div>
							<div class="col-sm-7 text-left">
								<label class="control-label hidden" id="sex_Err" style="color: red;font-weight:bold;"><i class='fa fa-times'></i> 請選擇性別</label>
								<label class="control-label hidden" id="sex_Corr" style="color: green;font-weight:bold;"><i class="fa fa-check"></i></label>
							</div>
						</div>

						<div class="form-group">
							<label for="birth" class="col-sm-2 control-label" style="color:black;">生日</label>
							<div class="col-sm-3">
								<div class="input-group input-append date" id="datePicker">
									<input type="text" class="form-control" name="birth" id="birth"
										style=""
										value="<%=(empVO == null) ? "" : empVO.getBirth()%>" /> <span
										class="input-group-addon add-on"><span
										class="fa fa-calendar"></span></span>
								</div>
							</div>
							<div class="col-sm-7">
								<label class="control-label hidden" id="birth_Err" style="color: red;font-weight:bold;"><i class='fa fa-times'></i> 請選擇生日</label>
								<label class="control-label hidden" id="birth_Corr" style="color: green;font-weight:bold;"><i class="fa fa-check"></i></label>
							</div>
						</div>

						<div class="form-group">
							<label for="phone" class="col-sm-2 control-label" style="color:black;">手機</label>
							<div class="col-sm-2">
								<input type="text" name="phone" id="phone" class="form-control"
									style="width: 180px;"
									value="<%=(empVO == null) ? "" : empVO.getPhone()%>" />
							</div>
							<div class="col-sm-7">
								<label class="control-label hidden" id="phone_Err" style="color: red;font-weight:bold;"><i class='fa fa-times'></i> 請輸入手機</label>
								<label class="control-label hidden" id="phone_Corr" style="color: green;font-weight:bold;"><i class="fa fa-check"></i></label>
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="col-sm-2 control-label" style="color:black;">信箱</label>
							<div class="col-sm-2">
								<input type="email" name="email" id="email" class="form-control"
									style="width: 180px;"
									value="<%=(empVO == null) ? "" : empVO.getEmail()%>" />
							</div>
							<div class="col-sm-7">
								<label class="control-label hidden" id="email_Err" style="color: red;font-weight:bold;"><i class='fa fa-times'></i> 請輸入信箱</label>
								<label class="control-label hidden" id="email_Corr" style="color: green;font-weight:bold;"><i class="fa fa-check"></i></label>
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
							<img src="<%=request.getContextPath()%>/emp/ShowImg_session" class="hide_img" style="width:250px;height:250px;">
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">權限</label>
							<div class="col-sm-10 text-left">
							
							<c:forEach var="funcVO" items="${funcSv.allFunc}">
								<label class="checkbox-inline">
									<input type="checkbox" name="auth" value="${funcVO.funcno}" ${authMap[funcVO.funcno] == '1' ? "checked" : "" } />${funcVO.name}
								</label>
							</c:forEach>
							
							</div>
						</div>
				</div>
				<div class="page_clear" style="height: 20px;"></div>
				<div class="col-sm-12 text-center" style="margin-bottom: 5%;">
					<a class="btn btn-primary" href="<%=request.getContextPath()%>/back-end/index_emp.jsp?whichPage=<%=request.getParameter("whichPage")%>" role="button"><span
						class="fa fa-times"></span> 取消</a>
					<button class="btn btn-danger" id="submit_insert">
						<i class="fa fa-check"></i> 確認送出
					</button>
					<a class="btn btn-default" href="#" role="button" onclick="magic();">按我</a>
					<input type="hidden" name="action" value="insert"> 
					<input type="hidden" name="whichPage" value="<%=request.getParameter("pageNumber")%>"> 
					<input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>"> 
					<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>"> 
					<input type="hidden" name="reqSourceURL" value="<%= request.getParameter("reqSourceURL")%>"> 
				</div>
				</form>

<!-- 			</div> -->
<!-- 			<div class="col-sm-4" width="500"> -->
<%-- 				<c:if test="${not empty errMsgs}"> --%>
<!-- 					<ul> -->
<%-- 						<font color="red" size="6" face="標楷體"> <c:forEach var="msg" --%>
<%-- 								items="${errMsgs}"> --%>
<%-- 								<li>${msg}</li> --%>
<%-- 							</c:forEach> --%>
<!-- 					</ul> -->
<!-- 					</font> -->
<%-- 				</c:if> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<!-- 					<div class="form-group"> -->
	<!-- 						<label for="birth">生日</label> <input type="date" name="birth" -->
	<%-- 							id="birth" class="form-control" value="<%= (empVO == null) ? "" : empVO.getBirth()%>"/> --%>
	<!-- 					</div> -->

	<!-- 						<button class="btn btn-primary"> -->
	<!-- 							<i class="glyphicon glyphicon-remove"></i>取消 -->
	<!-- 						</button> -->

</body>
</html>