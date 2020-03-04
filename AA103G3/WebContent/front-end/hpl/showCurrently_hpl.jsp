<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hpl.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

%>
<html>
<head>
<title>showCurrently_hpl.jsp</title>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<!-- <script> -->
<!-- </script> -->
<!-- <style> -->
<!-- </style> -->
<script>
	$(document).ready(function(){
		if("${successMsg}".length != 0){
			swal(
				"${successMsg}",
				"",
				'success'
				)
		}
			
		$(".delete_button_showCurrently").click(function(e){
			e.preventDefault();
			var delete_btn=$(this);
			swal({
				title: '確定刪除此計畫?',
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
</head>
<body>
<!-- 	<div class="container container_showCurrently_hpl"> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="col-sm-1"></div> -->
<c:if test="${empty hplList}">
<div style="width:100%; text-align:center; min-height:350px;">
	<h1 style="color:#0E9EA3;font-size:36px;">目前尚無計畫，趕快去新增健康計畫吧!!</h1>
</div>	
</c:if>
			<c:forEach var="hplVO" items="${hplList}" varStatus="s">
				<div class="col-sm-12" style="border-bottom:2px solid #9cd9da;margin-bottom:10px">
					<div class="col-sm-3 text-center" style="font-size: 108px;font-weight:bold;font-style:italic; height:140px;">
						<label for="" class="" style="">${s.count}</label>
					</div>
					<div class="col-sm-9" style="padding-top: 15px;padding-right:0px;margin-top:5px;">
						<div class="" style="font-size: 22px;font-weight:bold;">
							<label for="" class="" style="">${hplVO.hpname}</label>
						</div>
						<div class="" style="font-size: 22px;font-weight:bold;margin-top:5px;">
							<label for="" class="" style="">計畫日期: ${hplVO.hpstdate} / ${hplVO.hpeddate}</label>
						</div>
						<div class="" style="font-size: 22px;font-weight:bold;margin-top:5px;">
							<label for="" class="" style="">剩餘計劃天數 ${countMap[hplVO.hpno]} 天</label>
						</div>
						<div class="text-right">
							<form method="post" action="<%=request.getContextPath()%>/hpl/HplServlet.do" style="margin: 0 0;">
								<button class="btn btn-default" style="color: #2a7efa; border: none; background: transparent; text-decoration: underline;">
									<i class="fa fa-chevron-circle-right" style="color: #2a7efa;"></i> 查看詳細內容
								</button>
								<input type="hidden" name="hpno" value="${hplVO.hpno}">
								<input type="hidden" name="action" value="hplDetail">
							</form>	
							<form method="post" action="<%=request.getContextPath()%>/hpl/HplServlet.do" style="margin: 0 0;">
								<button class="btn btn-default delete_button_showCurrently" style="color: red; border: none; background: transparent; text-decoration: underline;">
									<i class="fa fa-minus-circle" style="color: red;"></i> 刪除
								</button>
								<input type="hidden" name="hpno" value="${hplVO.hpno}">
								<input type="hidden" name="action" value="deletePlan">
								<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
							</form>	
						</div>
					</div>
				</div>
			</c:forEach>
<!-- 		</div> -->
<!-- 	</div> -->
</body>
</html>