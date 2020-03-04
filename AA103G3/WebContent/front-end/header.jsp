<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*" %>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	pageContext.setAttribute("memVO",memVO);
%>	


<html>
<head>
<style>
#header{
	font-size:18px;
}
.logout{
	color:#9C7562;
}
.logout:focus,.logout:hover{
	color:#9C7562;
	cursor: pointer;
}	
</style>
</head>
<body>


		<div style="background-color: #494949; height: 10px;"></div>
	
		<div class="container header" id="header">
			<div id="personalPage" style="background-color:#63C2C5;padding:5px;">
				<ul>
					<li>
						<a href="<%=request.getContextPath()%>/front-end/personalPage.jsp?memno=${memVO.memno}"> 
							<img  src="<%=request.getContextPath()%>/personal/personal.do?memno=${memVO.memno}">
							<h5>${memVO.name} �A�n</h5>
						</a>
					</li>					
					<li>
					<form method="post" id="logout" action="<%=request.getContextPath()%>/mem/login.do">
						<input type="hidden" name="action" value="logout">
					</form>
						<a class="logout" onclick="document.getElementById('logout').submit();" ><i class="fa fa-sign-out" aria-hidden="true"></i>�n�X</a>				
					</li>
				</ul>
			</div>
			<div style="padding: 15px 0;">
				<a href="<%=request.getContextPath()%>/front-end/homePage.jsp"><img src="<%=request.getContextPath()%>/front-end/image/jbz.png" id="logo"></a>
				<img src="<%=request.getContextPath()%>/front-end/image/slogan.png" id="slogan">
			</div>
			<div style="clear: both;"></div>
			<div class="container fixmenu" style="max-width: 1000px;padding-top: 15px;">
				<ul style="text-align: center;">
						<li style="border-left: solid 1px #9cd9da;">
						<a href="<%=request.getContextPath()%>/foodlist/foodlist.do?action=getByDateMem">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/Food-Vegeterian-Food-icon.png">
							<div>��i��O</div>
						</a>
						</li>
						<li>
						<a href="<%=request.getContextPath()%>/front-end/sportdairy.jsp?action=sportanyl">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/Running_icon.png">
							<div>�B�ʤ�O</div>
						</a>	
						</li>
						<li>
						<a href="<%=request.getContextPath()%>/front-end/healthCheck.jsp?action=basicCheck">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/az.png">
							<div>�ۧڰ���</div>
						</a>	
						</li>
						<li>
						<a href="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do?action=main&memno=${memVO.memno}">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/users-icon.png">
							<div>����</div>
						</a>	
						</li>
						<li>
						<a href="<%=request.getContextPath()%>/hpl/HplServlet.do?action=main">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/plan.png">
							<div>���d�p�e</div>
						</a>	
						</li>
						<li>
						<a href="<%=request.getContextPath()%>/front-end/ClockIndex.jsp?action=nextClockPage">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/clock.png">
							<div>���d�x��</div>
						</a>	
						</li>
				</ul>
			</div>
			<div class="smallmenu hidden1" style="max-width: 1000px;text-align: center;padding:0 0;">
				<ul style="text-align: center;">
						<li class="menutitle" style="border-left: solid 1px #fff;">
							<a href="<%=request.getContextPath()%>/foodlist/foodlist.do?action=getByDateMem">��i��O</a>
							<div class="menuimg" >
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/Food-Vegeterian-Food-icon.png">
							</div>
							
						</li>
						<li class="menutitle">
							<a href="<%=request.getContextPath()%>/front-end/sportdairy.jsp?action=sportanyl">�B�ʤ�O</a>
							<div class="menuimg">
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/Running_icon.png">
							</div>
						</li>
						<li class="menutitle">
							<a href="<%=request.getContextPath()%>/front-end/healthCheck.jsp?action=basicCheck">�ۧڰ���</a>
							<div class="menuimg">
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/az.png">
							</div>
						</li>
						<li class="menutitle">
							<a href="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do?action=main&memno=${memVO.memno}">����</a>
							<div class="menuimg" >
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/users-icon.png">
							</div>
						</li>
						<li class="menutitle">
							<a href="<%=request.getContextPath()%>/hpl/HplServlet.do?action=main">���d�p�e</a>
							<div class="menuimg">
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/plan.png">
							</div>
						</li>
						<li class="menutitle">
							<a href="<%=request.getContextPath()%>/front-end/ClockIndex.jsp?action=nextClockPage">���d�x��</a>
							<div class="menuimg">
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/clock.png">
							</div>
						</li>
				</ul>
			</div>
		</div>


<!-- 		<div class="container-fluid title-bar"> -->
<!-- 			<div class="container" style="color:#fff; font-size:20px;padding-left: 30px;font-weight:bold;font-family: '�L�n������'";> -->
<!-- 					���d�x�� -->
<!-- 			</div> -->
<!-- 		</div> -->


		<!-- jQuery -->
		<!-- Bootstrap JavaScript -->
	</body>
</html>