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
	<!-- table-striped(����), table-bordered(�ؽu), table-condensed(padding)-->
	<div class="container">
		<div class="row">
			<!-- <div class="col-sm-1">
				</div> -->
			<div class="col-sm-9">
				<div class="col-sm-12 search" style="padding-top: 30px;">
					<!-- 1.�M��form-horizontal -->
					<form class="form-horizontal">
						<div class="form-group">
							<!-- 2.label �M�ή�u& control-label -->
							<label for="name" class="col-sm-1 control-label">�d��:</label>
							<!-- 3.input�ή�u�]�� -->
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
								     <input type="submit" value="�s��">
								     
								     <input type="hidden" name="group_no" value="${groupinfoVO.group_no}">
								     <input type="hidden" name="action"	value="getOne_For_Update">
								     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
								     </FORM>
								
								
								</button>
								</td>

						</tr>



					</c:forEach>
					</tr>
					<a href="#one"> �s�W�������� asdas</a>
					<a href="#_" class="lb" id="one">
						<div>
							
							<form>
								action="<%=request.getContextPath()%>/back-front/GroupinfoServlet.do"
								enctype="multipart/form-data" method="post">
								���ΦW��: <input type="text" name="groupName"><br>
								�ΥD�W��: <input type="text" name="memno"><br>
								���ιϤ�: <input type="file" name="fname"><br>
									
								 <input
									type="hidden" name="action" value="${deptVO.deptno}"> <input
									type="submit" value="Submit">
							</form>
						</div>
					</a>


					
						<a href="#">�s�W����asdasdssss</a>
						<div>
							<a rel="lightbox">asdasd</a>
						</div>
					
						<div>
							<form
								action="<%=request.getContextPath()%>/back-front/GroupinfoServlet.do"
								enctype="multipart/form-data" method="post">
		
								���ΦW��: <input type="text" name="groupName"><br> 
								���Φa�}: <input type="text" name="groupLoc"><br> 
								�ԲӸ�T: <input type="text" name="groupDetial"><br> 
								�H�ƤW��: <input type="text" name="groupCount"><br> 
										<input type="hidden" name="groupMemno" value="${MemVO.memno}"><br>
								<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
						 ���ιϤ�:  <input type="file" name="fname"><br> 
								 <input type="hidden" name="insertPic" value="<%=session.getAttribute("insertPic") %>">
								 <input type="hidden" name="deptno" value="${deptVO.deptno}">
			    				 <input type="hidden" name="action" value="insert_One_group">
								 <input type="submit" value="�s�W����">
		
		
							</form>
						</div>
						<div>
							<span>Base64�Ϥ��W��</span>
							<form
								action="<%=request.getContextPath()%>/back-front/GroupinfoServlet.do"
								enctype="multipart/form-data" method="post">
		
<!-- 								���ΦWasd��: <input type="text" name="groupName"><br>  -->
										<textarea  name="groupName" ></textarea>
								�ΥD�W��: <input type="text" name="groupMemno" value="${MemVO.memno}"><br>
								 ���ιϤ�: <input multiple="multiple" type="file" name="fname"><br> 
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