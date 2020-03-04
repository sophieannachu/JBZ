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
	pageContext.setAttribute("list", list);
	List<GroupInfoVO> list2 = (List<GroupInfoVO>) pageContext.getAttribute("list");
	MemService memSvc = new MemService();
	List<MemVO> listMem = memSvc.getAllMem();
	pageContext.setAttribute("listMem", listMem);
%>
<% 
	String number = ""+Math.random() * 10;
	System.out.print("dsd"+number);
	session.setAttribute("insertPic", number);
%>
<!-- <div class="container2"> -->
<!-- 	<div class="modal fade" id="myModal" role="dialog"> -->
<!--     <div class="modal-dialog"> -->
    
<!--       <div class="modal-content"> -->
<!--         <div class="modal-header"> -->
<!--           <button type="button" class="close" data-dismiss="modal">&times;</button> -->
<!--           <h4 class="modal-title">Modal Header</h4> -->
<!--         </div> -->
<!--         <div class="modal-body" id="editForm"> -->
        	
<!--         </div> -->
<!--         <div class="modal-footer"> -->
<!--           <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
<!--         </div> -->
<!--       </div> -->
      
<!--     </div> -->
<!--   </div> -->
  
<!-- </div> -->
	
	
	
<div class="container">
	<div class="row">
	<div class="col-sm-12 space"></div>
	<table class="table table-striped table-hover" border="1">
		<tr>
			<td>糾團名稱</td>
			<td>團長</td>
			<td>活動地點</td>
			<td>活動時間</td>
			<td>審核通過</td>
			<td>審核不通過</td>
		</tr>
<jsp:useBean id="MemSvc" scope="page" class="com.mem.model.MemService" />
					
						<c:forEach var="groupinfoVO" items="${list}">
							<c:if test="${groupinfoVO.group_check == 0}">
								<tr id="groupNo${groupinfoVO.group_no}">
									<td>
									<img src="<%=request.getContextPath()%>/group_info/ShowGroupInfo?group_no=${groupinfoVO.group_no}"
										
										style="width: 52%;"><br>${groupinfoVO.group_name}
									</td>
									<c:forEach var="MemVO" items="${MemSvc.getAllMem()}">
										<c:if test="${MemVO.memno == groupinfoVO.memno}">
											<td>${MemVO.name}</td>
										</c:if>
									</c:forEach>
									<td>${groupinfoVO.group_loc}</td>
									<td>${groupinfoVO.group_time}</td>
		<%-- 							<td>${groupinfoVO.group_detail}</td> --%>
		
		<!-- 								<td> -->
		<%-- 									<button onclick="deleteFd(${groupinfoVO.group_no},'<%=request.getContextPath()%>','<%=request.getServletPath()%>');"> --%>
		<%-- 										<img src="<%=request.getContextPath()%>/back-front/group_info/img/15.png"> --%>
		<!-- 									</button> -->
		<!-- 								</td> -->
										<td>
										<button>
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do">
			<%-- 								 <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
											     <input type="hidden" name="group_no" value="${groupinfoVO.group_no}">
											     <input type="hidden" name="action"	value="getOne_For_Update">
											     <button  onclick="editGroupOK(${groupinfoVO.group_no});"  type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">YES</button>
											    
										     </FORM>
										</button>
										</td>
										<td>
											 <button  onclick="editGroupNO(${groupinfoVO.group_no});"  type="button" class="btn btn-info btn-lg" data-toggle="modal" style="    background-color: #c9104f;" data-target="#myModal">NO</button>
										</td>
		
								</tr>
	
	
							</c:if>
					</c:forEach>
					</tr>
					<a href="#_" class="lb" id="one">
						<div>
							
							<form action="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do" enctype="multipart/form-data" method="post">
								揪團名稱: <input type="text" name="groupName"><br>
								團主名稱: <input type="text" name="memno"><br>
								揪團圖片: <input type="file" name="fname"><br>
									
								 <input
									type="hidden" name="action" value="${deptVO.deptno}"> <input
									type="submit" value="Submit">
							</form>
						</div>
					</a>


					
					
				</table>

				<div class="col-sm-12 text-center">
					<ul class="pagination pagination-lg">
						<li><a href="#">&laquo;</a></li>
						<li><a href="#">1</a></li>
						<li class="active"><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script>
		function editGroupOK(group_no){
			$.ajax({
			    url: '<%=request.getContextPath()%>/group_info/GroupinfoServlet.do',
			    type: 'POST',
			    data: {"group_no": group_no,"action":'editGroupOK'},
			    success: function(response) {
			    	$("#groupNo"+group_no).hide(1000);
			    	sendMessageWS(group_no);
			    },
			    error: function(xhr) {
			      alert('Ajax request 發生錯誤');
			    }
			    
			  });
		}
		function editGroupNO(group_no){
			$.ajax({
			    url: '<%=request.getContextPath()%>/group_info/GroupinfoServlet.do',
			    type: 'POST',
			    data: {"group_no": group_no,"action":'editGroupNO'},
			    success: function(response) {
			    	$("#groupNo"+group_no).hide(1000);
			    },
			    error: function(xhr) {
			      alert('Ajax request 發生錯誤');
			    }
			    
			  });
		}
	</script>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>