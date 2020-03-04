<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemService memSv=new MemService();
	List<MemVO> memList=memSv.getAllMem();
	request.setAttribute("memList", memList);
%>
<%@ include file="page1.file" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>listAll_mem.jsp</title>
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/mem/css/mem.css">
<script>

function getMemView(memno){
	$(document).ready(function() {
			$.ajax({
				url: "<%=request.getContextPath()%>/mem/MemServlet.do",
				type: "post",
				data: {"memno":memno, "action":"detail", "whichPage":"<%= whichPage%>", "reqSourceURL":"<%= request.getServletPath()%>"},
				datetype: "text",
				success: function(data){
					document.getElementById("modal-body").innerHTML =data;
				},
				error: function(){
					alert("error");
				}
			});
	});
}

</script>
</head>
<body>
<!-- 	<div class="container container_listAll_mem"> -->
<!-- 		<div class="row"> -->

<!-- 		<div class="col-sm-1"></div> -->
			<div class="well" style="margin-top:2%;">
<!-- 				<form class="form-inline text-left" method="post" action=""> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label for="search" class="col-sm-4 control-label control-label_listAll_mem">查詢 :</label> -->
<!-- 						<div class="col-sm-8"> -->
<!-- 							<input type="text" name="search" id="search" class="form-control"></input> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</form> -->
				
				<table class="table table-striped table-hover table-condensed table_listAll_mem">
					<thead>
						<tr>
							<th></th>
							<th>大頭照</th>
							<th>會員編號</th>
<!-- 							<th>帳號</th> -->
<!-- 							<th>密碼</th> -->
							<th>名稱</th>
							<th>性別</th>
							<th>生日</th>
							<th>連絡電話</th>
							<th>電子郵件</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="memVO" items="${memList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr ${param.memno == memVO.memno ? 'style="background:#fcfeda;"' : ''}>
							<td>
<%-- 								<form method="post" action="<%=request.getContextPath()%>/mem/MemServlet.do"> --%>
									<button class="btn btn-warning detail_btn_listAll_mem" data-toggle="modal" data-target="#pwd_Modal" onclick="getMemView(${memVO.memno});" >
										<i class="fa fa-search-plus"></i> 查看
									</button>
<%-- 									<input type="hidden" name="memno" value="${memVO.memno}"> --%>
<!-- 									<input type="hidden" name="action" value="detail"> -->
<%-- 									<input type="hidden" name="whichPage" value="<%= whichPage%>"> --%>
<%-- 									<input type="hidden" name="reqSourceURL" value="<%= request.getServletPath()%>"> --%>
<!-- 								</form> -->
							</td>
							<td><img src="<%=request.getContextPath()%>/mem/ShowImg_memno?memno=${memVO.memno}" width="20" height="20"></td>
							<td>${memVO.memno}</td>
<%-- 							<td>${memVO.acc}</td> --%>
<%-- 							<td>${memVO.pwd}</td> --%>
							<td>${memVO.name}</td>
							<td>${memVO.sex == 1 ? '男' : '女'}</td>
							<td>${memVO.bir}</td>
							<td>${memVO.phone}</td>
							<td>${memVO.email}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="modal fade" id="pwd_Modal" tabindex="-1" role="dialog" aria-labelledby="pwd_ModalLabel">
				<div class="modal-dialog" role="document" style="width:1000px;">
					<div class="modal-content">
						<div class="modal-header" style="border:0;">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						    	<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body" id="modal-body">
		<%-- 					<jsp:include page="/back-end/mem/showOne_mem2.jsp"></jsp:include> --%>
						</div>
						<div class="modal-footer" style="border:0;text-align:center;">
							<button type="button" class="btn btn-default" id="cancell_pwd" data-dismiss="modal">關閉</button>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="page2.file" %>

</body>
</html>