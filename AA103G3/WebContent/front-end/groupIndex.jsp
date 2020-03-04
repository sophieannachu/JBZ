<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>JBZ呷百利-揪團</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/css/sweetalert2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery_ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/sweetalert.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_ui_1.10.3.js"></script>
<!-- header&footer&reset css檔 & js檔 -->
<script src="<%=request.getContextPath()%>/front-end/js/header.js"></script>
<link href="<%=request.getContextPath()%>/front-end/css/groupInfo.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/header.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/reset.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/footer.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/card.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">

<%@ page import="com.groupinfo.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*"%>

<style>
.main{
	border-left: solid 1px #c4d4c4; 
	border-right: solid 1px #c4d4c4;
	margin-top: 30px; 
	margin-bottom: 40px; 
}
.list-group{
	 font-size:18px;
}
* {
	font-family: "微軟正黑體";
}
*{
	font-size:18px;
}
</style>



</head>
<%
	GroupInfoService groupinfoSv = new GroupInfoService();
	List<GroupInfoVO> list = groupinfoSv.getAll();
	System.out.println(list.size());
	MemService memSv = new MemService();
	List<MemVO> memList = memSv.getAllMem();
	for(GroupInfoVO GroupInfoVO:list){
		System.out.print("getGroup_no="+GroupInfoVO.getGroup_no()+"..");
	}
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("memList", memList);
	
%>
<%
	
	MemVO memVO = (MemVO)session.getAttribute("memVO");
		System.out.print("--------------------"+"\n");
	System.out.print("ff"+memVO.getName()+"\n");
	pageContext.setAttribute("memVO", memVO);
	System.out.print(",,,,,,,,,,,"+memVO);
	
	
%>
<body onunload="disconnect();">	
  <div>
  	<jsp:include page="/front-end/header.jsp"></jsp:include>
  </div>
 <div class="container-fluid title-bar">
			<div class="container" style="color:#fff; font-size:20px;padding-left: 30px;font-weight:bold;font-family: '微軟正黑體';">
					<h1>揪  團</h1>
			</div>
		</div>
<jsp:useBean id="groupinfoSvc" scope="page" class="com.groupinfo.model.GroupInfoService" />
<jsp:useBean id="groupListSvc" scope="page" class="com.groupList.model.GroupListService" />
   <div id="chatMain2" style="outline: solid 1px #63C2C5; bottom: -98%;"></div>
  <div id="chatMain" style="outline: solid 1px #63C2C5;display:none; ">
	<div id="chatmenu" style="background-color:#63C2C5; color:blue; ">MENU</div>
	<br>
	<c:forEach var="groupinfoVO" items="${list}">
	<%
		GroupInfoVO groupinfoVO = (GroupInfoVO)pageContext.getAttribute("groupinfoVO");
		session.setAttribute("group_no", groupinfoVO.getGroup_no());
		session.setAttribute("group_name", groupinfoVO.getGroup_name());
		System.out.println("session="+session.getAttribute("group_no"));
	
	%>
		
	<!-- 打開聊天區塊 -->
		<c:forEach var="groupListVO" items="${groupListSvc.getAll()}">
			<c:if test="${groupListVO.group_no == groupinfoVO.group_no}">
				<c:if test="${memVO.memno == groupListVO.memno}">
					<script>
// 						$(document).ready(function(){
// 							var chatMain =$("#chatMain");
// 							if($("#chatMain>img").length == 0){
// //		 			    		$("#chatMain").hide();
// 					    		chatMain.hide();
// 					    	}else{
								
// 					    		chatMain.show();
// 					    	}
					    		
							
							
// 						});
					</script>
					<img id="${groupinfoVO.group_no}" onclick="openChat('${groupinfoVO.group_name}','${groupinfoVO.group_no}');" style="width:54%; outline: solid 1px; margin-left: 10px;" src="<%=request.getContextPath()%>/group_info/ShowGroupInfo?group_no=${groupinfoVO.group_no}"/>
		
				</c:if>	
			</c:if>	
		 </c:forEach>
	</c:forEach>
	</div>
	
  <div class="container main" style="min-height: 43%;">
  		<div id="chat">
			<div id="chatmenu2" style="background-color:#63C2C5;  display:block; color:blue;">MENUdfgg</div>
			
		</div>
		<div id="chatContentMen"></div>
				
<%--   	<jsp:include page="/front-end/group/group.jsp"></jsp:include> --%>
 
	<div class="col-sm-3"  style="padding:0 10px;">
			<div class="list-group"> 
			    <a class="list-group-item createGroup" href="<%=request.getContextPath()%>/front-end/groupIndex.jsp?action=createGroup">發起揪團</a>
			    <a class="list-group-item newGroup" href="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do?action=main&memno=${memVO.memno}&requestURL=<%=request.getServletPath()%>">最新揪團</a>
			    <% System.out.println(request.getServletPath()); %>
<!-- 			    <li class="list-group-item"><a href="">我的揪團</a></li>  -->
			    <a class="list-group-item myGroup" href="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do?action=myGroup&requestURL=<%=request.getServletPath()%>">我參加的揪團</a>
			    <a class="list-group-item myBossGroup" href="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do?action=myCreateGroup&requestURL=<%=request.getServletPath()%>">我發起的揪團</a>
			</div>
		
	
<script>
	$(function() {
		<c:if test="${param.action==\"getOne_For_Update\"||param.action==\"myCreateGroup\"||param.action==\"editForm\"}">
		$(".myBossGroup").addClass("active");
		</c:if>
		<c:if test="${param.action==\"main\" || param.memno==\"${memVO.memno}\"||param.action==\"newGroup\"}">
		$(".newGroup").addClass("active");
		</c:if>
		<c:if test="${param.action==\"createGroup\"}">
		$(".createGroup").addClass("active");
		</c:if>
		<c:if test="${param.action==\"myGroup\"}">
		$(".myGroup").addClass("active");
		</c:if>
	});
</script>	
<%-- 		<jsp:include page="/front-end/group/mainMunu.jsp"></jsp:include> --%>
	</div>
		<c:if test="${param.action==\"getOne_For_Update\"}">
			<div class="col-sm-9" style="border-left: solid 1px #c4d4c4;">
				 <jsp:include page="/front-end/group/groupItemEdit.jsp" />
			</div>
		</c:if>
	<c:if test="${param.action==\"main\" || param.memno==\"${memVO.memno}\"}">
		<div class="col-sm-9" style="min-height:50%;border-left: solid 1px #c4d4c4;">
			<jsp:include page="/front-end/group/newGroup.jsp"></jsp:include> 
		</div>	
	</c:if> 
	<c:if test="${param.action==\"newGroup\" }">
		<div class="col-sm-9" style="border-left: solid 1px #c4d4c4;">
			<jsp:include page="/front-end/group/newGroup.jsp"></jsp:include> 
		</div>	
	</c:if> 	
	<c:if test="${param.action==\"createGroup\"}">
		<div class="col-sm-9" style="border-left: solid 1px #c4d4c4;">
			<jsp:include page="/front-end/group/createGroup.jsp"></jsp:include> 
		</div>	
	</c:if>
	<c:if test="${param.action==\"myCreateGroup\" || param.action==\"editForm\"}">
		<div class="col-sm-9" style="border-left: solid 1px #c4d4c4;">
			<jsp:include page="/front-end/group/myCreateGroup.jsp"></jsp:include> 
		</div>	
	</c:if>	
	<c:if test="${param.action==\"myGroup\"}">
		<div class="col-sm-9" style="border-left: solid 1px #c4d4c4;">
			<jsp:include page="/front-end/group/myGroup.jsp"></jsp:include> 
		</div>	
	</c:if>	
  </div>
  <div>
  	<jsp:include page="/front-end/footer.jsp"></jsp:include>
  </div>
  
  
 
</body>
</html>
 <!-- 此為聊天室的JS檔 ----->
 	<jsp:include page="js/ChatFile.jsp"></jsp:include>
 <!--END  -->	