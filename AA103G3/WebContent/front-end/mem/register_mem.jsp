<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*" %>
<%
	MemVO memVO=(MemVO)request.getAttribute("memVO");
%>
<html>
<head>
<title>register_mem.jsp</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/mem/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/mem/css/datepicker.min.css" />
<script src="<%=request.getContextPath()%>/front-end/mem/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/mem/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/mem/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/mem/css/font-awesome.min.css">
<script src="<%=request.getContextPath()%>/front-end/mem/js/preview.js"></script>
<script src="<%=request.getContextPath()%>/front-end/mem/js/registerCheck.js"></script>
<jsp:include page="/front-end/mem/registerCheck.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12" style="height: 50px;"></div>
			<div class="col-sm-1"></div>
			<div class="well col-sm-10">
			<form method="post" action="<%=request.getContextPath()%>/mem/MemServlet.do" enctype="multipart/form-data" class="form-horizontal" role="form">
				<div class="form-group">
					<label for="photo" class="col-sm-2 control-label" style="margin-top: 70px;">照片</label>
					<div class="col-sm-3" style="margin-top: 70px;">
						<input type="file" id="photo" name="photo" style="margin-top: 5px;" />
					</div>
					<div class="col-sm-6">
						<img src="<%=request.getContextPath()%>/mem/ShowImg_session" class="preview" width="100" height="100" style="border: 1px solid #ccc">
<!-- 						session.removeAttribute("photo") -->
					</div>
				</div>
				
				<div class="form-group">
					<label for="acc" class="col-sm-2 control-label" style="color:black">帳號</label>
					<div class="col-sm-10">
						<input type="text" name="acc" id="acc" class="form-control" value="<%=(memVO == null) ? "" : memVO.getAcc()%>"  style="" oninput="acc_check();"/>
					</div>
					<div class="col-sm-2"></div>
					<div class="col-sm-10">
						<label class="control-label hidden" id="acc_Err" style="color: red;font-weight:bold;"><i class="fa fa-times"></i>請輸入5位數以上之帳號</label>
						<label class="control-label hidden" id="acc_Corr" style="color: green;font-weight:bold;"><i class="fa fa-check"></i>此帳號可以使用</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="pwd" class="col-sm-2 control-label" style="color:black">密碼</label>
					<div class="col-sm-10">
						<input type="password" name="pwd" id="pwd" class="form-control" value="<%=(memVO == null) ? "" : memVO.getPwd()%>" />
					</div>
					<div class="col-sm-2"></div>
					<div class="col-sm-10">
						<label class="control-label hidden" id="pwd_Err" style="color: red;"><i class="fa fa-times"></i>請輸入5位數以上之密碼</label>
						<label class="control-label hidden" id="pwd_Corr" style="color: green;"><i class="fa fa-check"></i>密碼格式正確</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="pwd2" class="col-sm-2 control-label" style="color:black">確認密碼</label>
					<div class="col-sm-10">
						<input type="password" name="pwd2" id="pwd2" class="form-control" value="<%=(memVO == null) ? "" : memVO.getPwd()%>" />
					</div>
					<div class="col-sm-2"></div>
					<div class="col-sm-10">
						<label class="control-label hidden" id="pwd2_Err" style="color: red;"><i class="fa fa-times"></i>請輸入確認密碼</label>
						<label class="control-label hidden" id="pwd2_Corr" style="color: green;"><i class="fa fa-check"></i>確認密碼符合</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="id" class="col-sm-2 control-label" style="color:black">姓名</label>
					<div class="col-sm-3">
						<input type="text" name="name" id="name" class="form-control" style="width: 200px;" value="<%=(memVO == null) ? "" : memVO.getName()%>" />
					</div>
					<div class="col-sm-7">
						<label class="control-label hidden" id="name_Err" style="color: red;"><i class="fa fa-times"></i>請輸入姓名</label>
						<label class="control-label hidden" id="name_Corr" style="color: green;"><i class="fa fa-check"></i></label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label" style="color:black">暱稱</label>
					<div class="col-sm-3">
						<input type="text" name="id" id="id" class="form-control" style="width: 200px;" value="<%=(memVO == null) ? "" : memVO.getId()%>" />
					</div>
					<div class="col-sm-7">
						<label class="control-label hidden" id="id_Err" style="color: red;"><i class="fa fa-times"></i>請輸入暱稱</label>
						<label class="control-label hidden" id="id_Corr" style="color: green;"><i class="fa fa-check"></i></label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label" style="color:black">性別</label>
					<div class="col-sm-3">
						<label class="radio-inline"> <input type="radio"
							name="sex" value="1" id="male" ${memVO.sex == '1' ? "checked" : "" } />男
							</label> <label class="radio-inline"> <input type="radio"
							name="sex" value="0" id="female" ${memVO.sex == '0' ? "checked" : "" } />女
						</label>
					</div>
					<div class="col-sm-7">
						<label class="control-label hidden" id="sex_Err" style="color: red;"><i class="fa fa-times"></i>請選擇性別</label>
						<label class="control-label hidden" id="sex_Corr" style="color: green;"><i class="fa fa-check"></i></label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="bir" class="col-sm-2 control-label" style="color:black">生日</label>
					<div class="col-sm-3">
						<div class="input-group input-append date" id="datePicker">
							<input type="text" class="form-control" name="bir" id="bir"
								value="<%=(memVO == null) ? "" : memVO.getBir()%>" /> <span
								class="input-group-addon add-on"><span
								class="fa fa-calendar"></span></span>
						</div>
					</div>
					<div class="col-sm-7">
						<label class="control-label hidden" id="bir_Err" style="color: red;"><i class="fa fa-times"></i>請選擇生日</label>
						<label class="control-label hidden" id="bir_Corr" style="color: green;"><i class="fa fa-check"></i></label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="phone" class="col-sm-2 control-label" style="color:black">手機</label>
					<div class="col-sm-3">
						<input type="text" name="phone" id="phone" class="form-control"
							style="width: 200px;" value="<%=(memVO == null) ? "" : memVO.getPhone()%>" />
					</div>
					<div class="col-sm-7">
						<label class="control-label hidden" id="phone_Err" style="color: red;"><i class="fa fa-times"></i>請輸入手機</label>
						<label class="control-label hidden" id="phone_Corr" style="color: green;"><i class="fa fa-check"></i></label>
					</div>
				</div>

				<div class="form-group">
					<label for="email" class="col-sm-2 control-label" style="color:black">信箱</label>
					<div class="col-sm-3">
						<input type="email" name="email" id="email" class="form-control" style="width: 200px;"
							value="<%=(memVO == null) ? "" : memVO.getEmail()%>" />
					</div>
					<div class="col-sm-7">
						<label class="control-label hidden" id="email_Err" style="color: red;"><i class="fa fa-times"></i>請輸入信箱</label>
						<label class="control-label hidden" id="email_Corr" style="color: green;"><i class="fa fa-check"></i></label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="act_type" class="col-sm-2 control-label" style="color:black">活動類型</label>
					<div class="col-sm-3">
						<select name="act_type" id="act_type" class="form-control" style="width: 200px;">
						    <option value="">請選擇</option>
						    <option value="1" id="low" ${memVO.act_type == '1' ? "selected" : "" } >輕度運動(一周1~3次)</option>
						    <option value="2" id="mid" ${memVO.act_type == '2' ? "selected" : "" } >中度運動(一周3~5次)</option>
						    <option value="3" id="high" ${memVO.act_type == '3' ? "selected" : "" } >重度運動(一周5次以上)</option>
  						</select>
					</div>
					<div class="col-sm-7">
						<label class="control-label hidden" id="act_type_Err" style="color: red;"><i class="fa fa-times"></i>請選擇活動類型</label>
						<label class="control-label hidden" id="act_type_Corr" style="color: green;"><i class="fa fa-check"></i></label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="height" class="col-sm-2 control-label" style="color:black">身高</label>
					<div class="col-sm-3">
						<input type="text" name="height" id="height" class="form-control" style=""
							value="<%=(memVO == null) ? "" : memVO.getHeight()%>" />
					</div>
					<div class="col-sm-7">
						<label class="control-label hidden" id="height_Err" style="color: red;"><i class="fa fa-times"></i>請輸入身高</label>
						<label class="control-label hidden" id="height_Corr" style="color: green;"><i class="fa fa-check"></i></label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="weight" class="col-sm-2 control-label" style="color:black">體重</label>
					<div class="col-sm-3">
						<input type="text" name="weight" id="weight" class="form-control" style=""
							value="<%=(memVO == null) ? "" : memVO.getWeight()%>" />
					</div>
					<div class="col-sm-7">
						<label class="control-label hidden" id="weight_Err" style="color: red;"><i class="fa fa-times"></i>請輸入體重</label>
						<label class="control-label hidden" id="weight_Corr" style="color: green;"><i class="fa fa-check"></i></label>
					</div>
				</div>
<!-- 				<div class="form-group"> -->
<!-- 					<label for="sos1" class="col-sm-2 control-label">緊急聯絡人1</label> -->
<!-- 					<div class="col-sm-2"> -->
<!-- 						<input type="text" name="sos1" id="sos1" class="form-control" style="width: 130px;" -->
<%-- 							value="<%=(memVO == null) ? "" : memVO.getSos1()%>" /> --%>
<!-- 					</div> -->
<!-- 					<label for="sos2" class="col-sm-2 control-label">緊急聯絡人2</label> -->
<!-- 					<div class="col-sm-2"> -->
<!-- 						<input type="text" name="sos2" id="sos2" class="form-control" style="width: 130px;" -->
<%-- 							value="<%=(memVO == null) ? "" : memVO.getSos2()%>" /> --%>
<!-- 					</div> -->
<!-- 					<label for="sos3" class="col-sm-2 control-label">緊急聯絡人3</label> -->
<!-- 					<div class="col-sm-2"> -->
<!-- 						<input type="text" name="sos3" id="sos3" class="form-control" style="width: 130px;" -->
<%-- 							value="<%=(memVO == null) ? "" : memVO.getSos3()%>" /> --%>
<!-- 					</div> -->
<!-- 				</div> -->
				
				<div class="form-group">
					<label class="col-sm-2 control-label">健康狀況</label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
							<input type="checkbox" name="disease" value="blood" ${memVO.blood == '1' ? "checked" : "" } />高血壓
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="disease" value="sugar" ${memVO.sugar == '1' ? "checked" : "" } />糖尿病
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="disease" value="oil" ${memVO.oil == '1' ? "checked" : "" } />膽固醇過高
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="disease" value="bone" ${memVO.bone == '1' ? "checked" : "" } />骨質疏鬆
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="disease" value="breathe" ${memVO.breathe == '1' ? "checked" : "" } />氣喘
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="disease" value="heart" ${memVO.heart == '1' ? "checked" : "" } />心臟病
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" name="disease" value="stom" ${memVO.stom == '1' ? "checked" : "" } />腸胃障礙
						</label>
					</div>
				</div>
			</div>
			<div class="page_clear" style="height: 20px;"></div>
				<div class="col-sm-12 text-center" style="margin-bottom: 50px;">
					<a class="btn btn-primary" href="" role="button"><span class="fa fa-times"></span> 取消</a>
					<button class="btn btn-danger" id="submit_register">
						<i class="fa fa-check"></i> 確認送出
					</button>
					<input type="hidden" name="action" value="register"> 
					<input type="hidden" name="reSubmitCode" value=""> 
					<a class="btn btn-default" href="#" role="" onclick="magic1();">按我</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>