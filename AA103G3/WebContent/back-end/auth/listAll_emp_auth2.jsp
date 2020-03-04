<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.func.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	EmpService empSv = new EmpService();
	List<EmpVO> empList = empSv.getAllEmp();
	pageContext.setAttribute("empList", empList);
%>
<%
	double reSubmitCode=Math.random()*10;
	session.setAttribute("reSubmitCode", reSubmitCode);
%>
<jsp:useBean id="funcSv" scope="page" class="com.func.model.FuncService" />
<jsp:useBean id="authSv" scope="page" class="com.auth.model.AuthService" />
<%@ include file="page1.file" %>
<html>
<head>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<title>listAll_emp_auth.jsp</title>
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<%-- <script src="<%=request.getContextPath()%>/back-end/auth/js/sweetalert2.min.js"></script> --%>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/auth/css/sweetalert2.min.css"> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/auth/css/auth.css">
<script>
	$(document).ready(function() {
// 		$("#datePicker").datepicker({
// 			format : 'yyyy-mm-dd',
// 			autoclose : true,
// 			todayHighlight : true
// 		});
	<c:forEach var="empVO" items="${empList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		$("#modify_btn_${empVO.empno}").click(function(){
			$(".hidden_${empVO.empno}").removeClass("hidden");
			$("#cancell_btn_${empVO.empno}").removeClass("hidden");
			$("#modify_btn_${empVO.empno}").addClass("hidden");
			$(".show_${empVO.empno}").addClass("hidden");
		});
		
		$("#cancell_btn_${empVO.empno}").click(function(){
			$("#modify_btn_${empVO.empno}").removeClass("hidden");
			$(".show_${empVO.empno}").removeClass("hidden");
			$("#cancell_btn_${empVO.empno}").addClass("hidden");
			$(".hidden_${empVO.empno}").addClass("hidden");
		});
	</c:forEach>
	
	if("${successMsg}".length != 0){
		swal(
			"${successMsg}",
			"",
			'success'
			)
	}
	
	if("${failureMsg}".length != 0){
		swal(
			"${failureMsg}",
			"",
			'error'
			)
	}
	});
</script>
</head>
<body>
<!-- 	<div class="container container_listAll_emp_auth"> -->
<!-- 		<div class="row"> -->
			<div class="well" style="margin-top:2%;">
				<table class="search_table_listAll_emp_auth">
					<thead>
						<tr>
							<th>
<!-- 								<form class="form-inline" method="post" action="EmpServlet.do"> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label for="search" class="col-sm-1 control-label_listAll_emp_auth">查詢 :</label> -->
<!-- 										<div class="col-sm-1"> -->
<!-- 											<input type="text" name="search" id="search" class="form-control"></input> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</form> -->
							</th>
						</tr>
					</thead>
				</table>
				<table class="table table-striped table-hover table-condensed table_listAll_emp_auth">
					<thead>
						<tr>
							<th></th>
							<th></th>
<!-- 							<th>大頭照</th> -->
							<th>員工編號</th>
							<th>帳號</th>
							<th>名稱</th>
							<c:forEach var="funcVO" items="${funcSv.allFunc}">
								<th>${funcVO.name}</th>
							</c:forEach>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="empVO" items="${empList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr ${(param.empno == empVO.empno)?'style="background:#fcfeda;"':''}>
								<td>
									<form method="post" action="<%=request.getContextPath()%>/auth/AuthServlet2.do" style="margin: 0 0 ;">
										<button class="btn btn-success" style="margin-bottom:5px;">
											<i class="fa fa-check-square-o"></i> 全部授權
										</button>
										<input type="hidden" name="empno" value="${empVO.empno}" class="hidden_input">
										<input type="hidden" name="action" value="allOpen" class="hidden_input">
										<input type="hidden" name="whichPage" value="<%= whichPage%>" class="hidden_input">
										<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
										<input type="hidden" name="reqSourceURL" value="<%= request.getServletPath()%>">
									</form>
										
									<form method="post" action="<%=request.getContextPath()%>/auth/AuthServlet2.do" style="margin: 0 0 ;">
										<button class="btn btn-danger">
											<i class="fa fa-ban"></i> 全部停權
										</button>
										<input type="hidden" name="empno" value="${empVO.empno}" class="hidden_input">
										<input type="hidden" name="action" value="allClose" class="hidden_input">
										<input type="hidden" name="whichPage" value="<%= whichPage%>" class="hidden_input">
										<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
										<input type="hidden" name="reqSourceURL" value="<%= request.getServletPath()%>">
									</form>
								</td>
								<td>
									<button class="btn btn-warning" id="modify_btn_${empVO.empno}" style="height: 73px;">
										<i class="fa fa-pencil"></i> 修改
									</button>
<%-- 								<input type="hidden" name="empno" value="${empVO.empno}" class="hidden_input"> --%>
								</td>
<%-- 								<td><img src="<%= request.getContextPath()%>/emp/ShowImg_empno?empno=${empVO.empno}" width="40" height="40"></img></td> --%>
								<td>${empVO.empno}</td>
								<td>${empVO.acc}</td>
								<td>${empVO.name}</td>
								<c:set var="open" value="<span class='fa fa-check show_${empVO.empno}'></span>" />
								<c:set var="close" value="<span class='fa fa-times show_${empVO.empno}'></span>" />
								<form method="post" action="<%=request.getContextPath()%>/auth/AuthServlet2.do">
								<c:forEach var="funcVO" items="${funcSv.allFunc}">
									<td>
										${authSv.getOne(empVO.empno).contains(funcVO.funcno) ? open : close }
										<input type="checkbox" class="auth hidden hidden_${empVO.empno}" name="auth" value="${funcVO.funcno}" ${authSv.getOne(empVO.empno).contains(funcVO.funcno) ? "checked" : "" } />
									</td>
								</c:forEach>
								<td>
									<button class="btn btn-danger hidden hidden_${empVO.empno}" style="margin-bottom:5px;">
										<i class="fa fa-floppy-o"></i> 儲存
									</button><br>
									<input type="hidden" name="empno" value="${empVO.empno}" class="hidden_input">
									<input type="hidden" name="action" value="update" class="hidden_input">
									<input type="hidden" name="whichPage" value="<%= whichPage%>" class="hidden_input">
									<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
									<input type="hidden" name="reqSourceURL" value="<%= request.getServletPath()%>">
								</form>
									<button class="btn btn-primary hidden" id="cancell_btn_${empVO.empno}">
										<i class="fa fa-times" style="color:white"></i> 取消
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<%@ include file="page2.file" %>
</body>
</html>
<!-- 			</div> -->
<!-- 		</div> -->