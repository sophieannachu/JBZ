<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sport.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.text.*"%>

<jsp:useBean id="sportTypeSvc" scope="page" class="com.sporttype.model.SportTypeService" />

<%  
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	SportService sportSvc = new SportService();
	List<Sport> list = sportSvc.getAllWatchByMemno(memVO.getMemno());
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">

<script>
function deleteOne(item){
	   deleteCheck = item;
	   sportrec_no = item.nextSibling.value;
	   swal({
		   title: '確定要刪除嗎?',
		   type: 'warning',
		   showCancelButton: true,
		   confirmButtonColor: '#3085d6',
		   cancelButtonColor: '#d33',
		   cancelButtonText:'取消',
		   confirmButtonText: '刪除成功!'
		 }).then(function() {
			$.ajax({
					 type:"POST",
					 url:"<%=request.getContextPath() %>/sport/sport.do",
					 data:{sportrec_no:sportrec_no,action:"delete_For_watch"},
					 dataType:"text",
					 success:function (data){
						   $.ajax({
								 type:"GET",
								 url:"<%=request.getContextPath() %>/front-end/watchsport/watchsport.jsp",
								 dataType:"text",
								 success:function (data){
									 document.getElementById("showpanel").innerHTML =data;
							     },
					           error:function(){alert("error")}
					       })
				     },
		error:function(){alert("error")}
		})
		   swal(
		     'Deleted!',
		     'Your file has been deleted.',
		     'success'
		   );
		 })
}
</script>

</head>
<body>
	<div class="row">
	  <c:forEach var="watchSport" items="${list}">
	  	<div class="watchinfo" style="border-bottom: solid 1px #ccc;">
			<div class="row">
			<div class="col-xs-4 col-sm-4" style="padding:0 0 0 30px;">
				<img src="<%=request.getContextPath() %>/sporttypeImg/sporttypeImg.do?type_no=${watchSport.type_no}" style="float: left;margin-top:10px;width:50px;">
				<div style="margin: 5px 0px 5px 60px; font-size: 18px;">
				<c:forEach var="watchTypeVO" items="${sportTypeSvc.all}">
					<c:if test="${watchTypeVO.type_no==watchSport.type_no}">
						${watchTypeVO.sport_name}
					</c:if>
				</c:forEach>
				</div>
				<div style="margin: 5px 0px 5px 60px;">
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${watchSport.sport_date}" />
				</div>
			</div>
			<div class="col-xs-3 col-sm-3">
				<p>時間</p>
				<img src="<%=request.getContextPath()%>/front-end/image/duration.png" style="float: left;">
				<h3>
				<fmt:parseNumber  integerOnly="true" value="${watchSport.sport_duration/3600}"/>
				:<fmt:parseNumber  integerOnly="true" value="${(watchSport.sport_duration%3600)/60}"/>
				:${watchSport.sport_duration%60}
				</h3>
			</div>
			<div class="col-xs-3 col-sm-3" >
				<p>燃燒卡路里</p>
				<img src="<%=request.getContextPath()%>/front-end/image/calories-burned.png"
					style="float: left;">
				<h3 style="position:absolute;right:30px;">${watchSport.sport_cal}</h3>
			</div>
			<div class="col-xs-2 col-sm-2" >
				<button type="button" onclick="deleteOne(this);" name="action" value="delete" class="btn btn-primary" style="margin:20px;">刪除
				</button><input type="hidden" name="sportrec_no" value="${watchSport.sportrec_no}">

			</div>
			</div>
		</div>
	  </c:forEach>		
	</div>	
</body>
</html>