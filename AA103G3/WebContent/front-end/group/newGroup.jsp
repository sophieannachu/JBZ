<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ page import="com.groupinfo.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupList.model.*"%>
<%
	
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	System.out.print("ff"+memVO.getName()+"\n");
	pageContext.setAttribute("memVO", memVO);
	
	
%>
<script>
function chatname(group_no,group_name) {
	var chatImg = "<img id='"+group_no+"'"+"onclick=\"openChat('"+group_name+"',"+group_no+");\""
	+"style='width:90%; margin-left: 10px; outline: solid 1px;'"+ "src='<%=request.getContextPath()%>/group_info/ShowGroupInfo?group_no="+group_no+"'/>";
	
	$("#chatMain").append(chatImg);
}
function addGroup(group_no,memno,group_name){
	
	$(document).ready(function(){
		var groupId =$("#pic_"+group_no);
		$.ajax({
		    url: '<%=request.getContextPath()%>/group_info/GroupinfoServlet.do',
		    type: 'POST',
		    data: {"group_no": group_no,"memno":memno,"action":'addGroup',"requestURL":"<%=request.getServletPath()%>"},
		    success: function(response) {
		    	location.reload();
		    	$("#chatMain").show();
		    	$.each(response, function(key, value) {
		    		// Iterate over the JSON object.
		    		groupId.hide();
		    		chatname(group_no,group_name);
		        });
		    	
		 
		    },
		    error: function(xhr) {
		      alert('Ajax request 發生錯誤');
		    }
		    
		  });
		
		
		
	});

	
	
}

</script>
<jsp:useBean id="groupListSvc" scope="page"
	class="com.groupList.model.GroupListService" />


<%-- 	${groupListSvc.getAll().get(0).group_no} --%>
<%-- 	${groupListSvc.getAll().get(0).memno} --%>
<%-- 	${groupListSvc.getAll().get(1).group_no} --%>
<%-- 	${groupListSvc.getAll().get(1).memno} --%>
<%-- 	${groupListSvc.getAll().get(2).group_no} --%>
<%-- 	${groupListSvc.getAll().get(2).memno} --%>

<c:set var="hasGroup" value="flase" scope="page" />
<c:forEach var="groupinfoVO" items="${groupInfoVOList}">
	<c:if test="${groupinfoVO.group_check == 1}">
		<c:set var="hasGroup" value="true" scope="page" />
				<script>
					$(document).ready(function(){
						var groupId =$("#pic_${groupListVO.group_no}");
						groupId.hide();
					});
				</script>
		<div>
			<div id="pic_${groupinfoVO.group_no}"
				style="font-weight: bold;line-height: 33px;">
				<img style="width: 30%; margin-bottom: 80px;"
					src="<%=request.getContextPath()%>/group_info/ShowGroupInfo?group_no=${groupinfoVO.group_no}"
					alt="">
				<div style=" padding-left: 5px;box-shadow: 2px 1px 26px grey; float: right; width: 68%; font-size: 150%;">
					</p>
					揪團名稱:${groupinfoVO.group_name}
					</p>
					</p>
					活動時間:${groupinfoVO.group_time}
					</p>
					</p>
					活動人數:${groupinfoVO.group_count}
					</p>
					</p>
					活動細節:${groupinfoVO.group_detail}
					</p>
					<button style=" margin-left: 70%;"
						onclick="addGroup(${groupinfoVO.group_no},'${memVO.memno}','${groupinfoVO.group_name}')">我要加入揪團</button>
				</div>
			</div>

		</div>
	</c:if>
</c:forEach>

<c:if test="${! hasGroup}">
<div style="width:100%; text-align:center; min-height:350px;">
	<h1 style="color:#0E9EA3;font-size:36px;">目前沒有最新揪團，趕快去創一個揪團!!</h1>
</div>	
</c:if>
