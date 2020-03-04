<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="BIG5"%>
<%@ page import="com.groupinfo.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/wraper.css">

</head>
<body>
<%
	GroupInfoVO groupInfoVO = (GroupInfoVO)request.getAttribute("GroupInfoVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<div class="ie-fix">
		<div class="wrapper">
			

			<!-- main -->
			<main role="main" class="content">
			<div>
				<div>
					<jsp:include page="groupEdit.jsp"/>
					
				</div>
			</div>
			</main>
			<!--end main -->

		</div>
	</div>

</body>
</html>