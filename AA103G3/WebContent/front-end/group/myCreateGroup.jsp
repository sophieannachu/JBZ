<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupinfo.model.*"%>
<%@ page import="com.groupList.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>Insert title here</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container2">
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog">
	    
<!-- 	      Modal content -->
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        <div class="modal-body" id="editForm">
<%-- 	          <p><jsp:include page="editForm.jsp"></jsp:include></p> --%>
	          <p></p>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>
	      
	    </div>
	  </div>
	  
	</div>

<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	System.out.print("ff"+memVO.getName()+"\n");
	pageContext.setAttribute("memVO", memVO);
%>

<c:forEach var="groupInfoVO" items="${groupInfoVOlist}">
		<c:if test="${groupInfoVO.memno == memVO.memno}">
			<div>
				<div id="pic2_${groupInfoVO.group_no}" style="margin-bottom: 7%;">
				      <img style="width:30%; margin-bottom: 20px; padding-top: 5%;" src="<%=request.getContextPath()%>/group_info/ShowGroupInfo?group_no=${groupInfoVO.group_no}"
				                 alt="">
			          <div  style="padding-left: 23px;border-radius: 25px;background-color: #dfdfdf;font-weight: bold; float: right; width:68%; font-size: 150%; margin-top:22px;">
						</p>揪團名稱:${groupInfoVO.group_name}</p>
				     	 </p>活動時間:${groupInfoVO.group_time}</p>
				     	 </p>活動人數:${groupInfoVO.group_count}</p>
				     	 </p>活動細節:${groupInfoVO.group_detail}</p>
				     	 <button  onclick="editForm(${groupInfoVO.group_no},${memVO.memno})"  style="margin-left: 69%;" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">編輯我的糾團</button>
<%-- 				     	 <button onclick="ff(${groupInfoVO.group_no},'${memVO.memno}','${groupInfoVO.group_name}')">編輯我的揪團</button> --%>
				      </div>
		         </div>
				
			</div>
		</c:if>
</c:forEach>
</body>

<script>
function editForm (group_no ,memno){
// 	alert(memno);
	
	$.ajax({
	    url: '<%=request.getContextPath()%>/group_info/GroupinfoServlet.do',
	    type: 'POST',
	    data: {"group_no": group_no,"memno":memno,"action":'editForm'},
	    success: function(response) {
	    	console.log(response.groupno);
	    	$("#editForm").html(response);
	    	doFirst();
	    },
	    error: function(xhr) {
	      alert('Ajax request 發生錯誤');
	    }
	    
	  });
	
	
<%-- 		window.location.href="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do?action=editForm&requestURL=<%=request.getServletPath()%>&group_no="+group_no; --%>
// 		swal({
// 			  title: "An input!",
// 			  text: "Write something interesting:",
// 			  type: "input",
// 			  showCancelButton: true,
// 			  closeOnConfirm: false,
// 			  inputPlaceholder: "Write something"
// 			}, function (inputValue) {
// 			  if (inputValue === false) return false;
// 			  if (inputValue === "") {
// 			    swal.showInputError("You need to write something!");
// 			    return false
// 			  }
// 			  swal("Nice!", "You wrote: " + inputValue, "success");
// 			});
}
</script>
</html>