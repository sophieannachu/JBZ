<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	EmpService sv = new EmpService();
	List<EmpVO> list = sv.getAllEmp();
	pageContext.setAttribute("list", list);
	session.removeAttribute("photo");
%>
<%
	double reSubmitCode=Math.random()*10;
	session.setAttribute("reSubmitCode", reSubmitCode);
%>
<c:set var="photolist" value="${list}" scope="session"></c:set>
<%@ include file="page1.file" %>
<html>
<head>
<title>listAll_emp.jsp</title>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<%-- <script src="<%=request.getContextPath()%>/back-end/emp/js/sweetalert2.min.js"></script> --%>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/emp/css/sweetalert2.min.css"> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/emp/css/emp.css">
<script>
	$(document).ready(function(){
		if("${successMsg}".length != 0){
			swal(
				"${successMsg}",
				"",
				'success'
				)
		}
			
		$(".delete_btn_listAll_emp").click(function(e){
			e.preventDefault();
			var delete_btn=$(this);
			swal({
				title: '確定刪除此員工?',
				type: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#d33',
				cancelButtonColor: '#3085d6',
				confirmButtonText: '確定',
				cancelButtonText: '取消'
				}).then(function(isConfirm){
						delete_btn.parent().submit();
					});
		});
		
	});
</script>
<style>
</style>
</head>
<body>
<!-- 	<div class="container container_listAll_emp"> -->
<!-- 		<div class="row"> -->
			<div class="well" style="margin:0 0; margin-top:2%;">
<!-- 				<table class="search_table_listAll_emp"> -->
<!-- 					<thead> -->
						<div class="col-sm-12 text-right">
<!-- 							<form class="form-inline"> -->
<!-- 								<th> -->
<!-- 									<div class="form-group form-group_listAll_emp"> -->
<!-- 										<label for="search" class="col-sm-1 control-label control-label_listAll_emp">查詢 :</label> -->
<!-- 										<div class="col-sm-1"> -->
<!-- 											<input type="text" name="search" id="search" class="form-control form-control_listAll_emp"></input> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</th> -->
<!-- 								<td> -->
<%-- 									<a class="btn btn-success insert_btn_listAll_emp" href="<%=request.getContextPath()%>/back-end/emp/addEmp.jsp?pageNumber=<%= nextPage%>&whichPage=<%= whichPage%>" role="button"><span class="glyphicon glyphicon-plus"></span> 新增</a> --%>
									<a class="btn btn-success insert_btn_listAll_emp" href="<%=request.getContextPath()%>/back-end/index_emp.jsp?action=insertView&pageNumber=<%= nextPage%>&whichPage=<%= whichPage%>&reqSourceURL=<%= request.getServletPath()%>" role="button"><span class="fa fa-plus"></span> 新增</a>
<!-- 								</td> -->
<!-- 							</form> -->
						</div>
<!-- 					</thead> -->
<!-- 				</table> -->
				<table class="table table-striped table-hover table-condensed table_listAll_emp">
					<thead>
						<tr class="head">
							<th></th>
							<th></th>
							<th>員工編號</th>
							<th>名稱</th>
							<th>帳號</th>
<!-- 							<th>密碼</th> -->
							<th>性別</th>
							<th>生日</th>
							<th>連絡電話</th>
							<th>電子郵件</th>
							<th>大頭照</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>" step="1" varStatus="s">
							<tr ${(param.empno == empVO.empno)?'style="background:#fcfeda;"':''}>
								<td>
									<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet.do" id="delete_form_listAll_emp" style="margin: 0 0;">
										<button class="btn btn-default delete_btn_listAll_emp">
											<i class="fa fa-minus-circle" style="color: red;"></i>
										</button>
			   							<input type="hidden" name="action" value="delete">
										<input type="hidden" name="empno" value="${empVO.empno}">
										<input type="hidden" name="reqSourceURL" value="<%= request.getServletPath()%>">
										<input type="hidden" name="whichPage" value="<%= whichPage%>">
										<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
									</form>
								</td>
								<td>
									<form method="post" action="<%=request.getContextPath()%>/emp/EmpServlet.do" style="margin: 0 0;">
										<button class="btn btn-default update_btn_listAll_emp">
											<i class="fa fa-pencil-square-o" style="color: #9014ec;"></i>
										</button>
				   						<input type="hidden" name="action" value="updateView">
										<input type="hidden" name="empno" value="${empVO.empno}">
										<input type="hidden" name="reqSourceURL" value="<%= request.getServletPath()%>">
										<input type="hidden" name="whichPage" value="<%= whichPage%>">
										<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
									</form>
								</td>
								<td>${empVO.empno}</td>
								<td>${empVO.name}</td>
								<td>${empVO.acc}</td>
<%-- 								<td>${empVO.pwd}</td> --%>
								<td>${(empVO.sex==1) ? "男" : "女"}</td>
								<td>${empVO.birth}</td>
								<td>${empVO.phone}</td>
								<td>${empVO.email}</td>
<%-- 								<td><img src="ShowImg_index?index=${s.index}" width="80" height="80"></img></td> --%>
								<td><img src="<%=request.getContextPath()%>/emp/ShowImg_empno?empno=${empVO.empno}" width="80" height="80"></img></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-12" style="height:20px;"></div>
			<%@ include file="page2.file" %>
</body>
</html>
<!-- 		</div> -->
<!-- 	</div> -->