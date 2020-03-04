<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="BIG5"%>
<%@ page import="com.groupinfo.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<%
		GroupInfoService groupinfoSv = new GroupInfoService();
		List<GroupInfoVO> list = groupinfoSv.getAll();
// 		System.out.println(groupinfoSv);
// 		System.out.println(list);
		pageContext.setAttribute("list", list);
		List<GroupInfoVO> list2 = (List<GroupInfoVO>) pageContext.getAttribute("list");
// 		System.out.println(list2);
		MemService memSvc = new MemService();
		List<MemVO> listMem = memSvc.getAllMem();
		
		pageContext.setAttribute("listMem", listMem);
		
		
	%>
	<% String number = ""+Math.random() * 10;
		System.out.print("dsd"+number);
		session.setAttribute("insertPic", number); %>
	<%-- <jsp:useBean id="groupinfoSv" scope="page" class="com.groupinfo.model.GroupInfoService" /> --%>
	<!-- table-striped(條紋), table-bordered(框線), table-condensed(padding)-->
	<div class="container">
		<div class="row">
			<!-- <div class="col-sm-1">
				</div> -->
			<div class="col-sm-9">
				<div class="col-sm-12 search" style="padding-top: 30px;">
					<!-- 1.套用form-horizontal -->
					<form class="form-horizontal">
						<div class="form-group">
							<!-- 2.label 套用格線& control-label -->
							<label for="name" class="col-sm-1 control-label">查詢:</label>
							<!-- 3.input用格線包圍 -->
							<div class="col-sm-3">
								<input type="text" name="" id="name" class="form-control"></input>
							</div>
						</div>
					</form>
				</div>
				<div class="col-sm-12 space"></div>
				<table class="table table-striped table-hover" border="1">


					<c:forEach var="groupinfoVO" items="${list}">
						
						<tr>
							<td>
							<img src="<%=request.getContextPath()%>/group_info/ShowGroupInfo?group_no=${groupinfoVO.group_no}"
								
								style="width: 20%;"><br>${groupinfoVO.group_name}${groupinfoVO.group_no}
							</td>
							<td>${groupinfoVO.memno}</td>
							<td>${groupinfoVO.group_loc}</td>
							<td>${groupinfoVO.group_detail}</td>

								<td>
									<button onclick="deleteFd(${groupinfoVO.group_no});">
										<img src="img/15.png">
									</button>
								</td>
								<td>
								<button>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do">
								     <input type="submit" value="編輯">
								     
								     <input type="hidden" name="group_no" value="${groupinfoVO.group_no}">
								     <input type="hidden" name="action"	value="getOne_For_Update">
								     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
								     </FORM>
								
								
								</button>
								</td>

						</tr>



					</c:forEach>
					</tr>
					<a href="#one"> 新增食物項目 asdas</a>
					<a href="#_" class="lb" id="one">
						<div>
							
							<form>
								action="<%=request.getContextPath()%>/back-front/GroupinfoServlet.do"
								enctype="multipart/form-data" method="post">
								揪團名稱: <input type="text" name="groupName"><br>
								團主名稱: <input type="text" name="memno"><br>
								揪團圖片: <input type="file" name="fname"><br>
									
								 <input
									type="hidden" name="action" value="${deptVO.deptno}"> <input
									type="submit" value="Submit">
							</form>
						</div>
					</a>


					
						<a href="#">新增項目asdasdssss</a>
						<div>
							<a rel="lightbox">asdasd</a>
						</div>
					
						<div>
							<form
								action="<%=request.getContextPath()%>/back-front/GroupinfoServlet.do"
								enctype="multipart/form-data" method="post">
		
								揪團名稱: <input type="text" name="groupName"><br> 
								揪團地址: <input type="text" name="groupLoc"><br> 
								詳細資訊: <input type="text" name="groupDetial"><br> 
								人數上限: <input type="text" name="groupCount"><br> 
										<input type="hidden" name="groupMemno" value="${MemVO.memno}"><br>
								<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
						 揪團圖片:  <input type="file" name="fname"><br> 
								 <input type="hidden" name="insertPic" value="<%=session.getAttribute("insertPic") %>">
								 <input type="hidden" name="deptno" value="${deptVO.deptno}">
			    				 <input type="hidden" name="action" value="insert_One_group">
								 <input type="submit" value="新增項目">
		
		
							</form>
						</div>
						<div>
							<span>Base64圖片上傳</span>
							<form
								action="<%=request.getContextPath()%>/back-front/GroupinfoServlet.do"
								enctype="multipart/form-data" method="post">
		
<!-- 								揪團名asd稱: <input type="text" name="groupName"><br>  -->
										<textarea  name="groupName" ></textarea>
								團主名稱: <input type="text" name="groupMemno" value="${MemVO.memno}"><br>
								 揪團圖片: <input multiple="multiple" type="file" name="fname"><br> 
								 <input type="hidden" name="insertPic" value="<%=session.getAttribute("insertPic") %>">
										<input type="hidden" name="deptno" value="${deptVO.deptno}">
					    				<input type="hidden" name="action" value="insert_One64_Pic">
										<input type="submit" value="Submit">
		
		
							</form>
						</div>
			
				</table>

<!-- 				<div class="col-sm-12 text-center"> -->
<!-- 					<ul class="pagination pagination-lg"> -->
<!-- 						<li><a href="#">&laquo;</a></li> -->
<!-- 						<li><a href="#">1</a></li> -->
<!-- 						<li class="active"><a href="#">2</a></li> -->
<!-- 						<li><a href="#">3</a></li> -->
<!-- 						<li><a href="#">&raquo;</a></li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
			</div>
		
		</div>
	</div>


	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>