<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.clock.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!-- <script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js"></script> -->


<!--     <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->

<html>
<head>
<title>鬧鐘列表</title>
<script>
$(function() {
	<c:if test="${param.action==\"nextClockPage\"}" >
	$(".addClock").addClass("active");
	</c:if>
	
	<c:if  test="${param.action!=\"nextClockPage\"}">
	$(".getAll").addClass("active");
	</c:if>
});
</script>
<script>
$(function() {
	if("${clocksweat}".length!=0){
		swal("Good job!", "你已經成功新增鬧鐘!", "success");
	}
	
  });
</script>
</head>
<body>


<div class="container main" style="width:1000px">
   			 <div class="col-xs-12 col-sm-3" style="padding:0 10px;">
    			
<!--  				 <h3 style="background-color:#0E9EA3; color:#fff;" class="text-center">健康鬧鐘列表</h3>  -->
 				 <div class="list-group">   
 					 <a href="<%=request.getContextPath()%>/clock/clock.do?action=nextClockPage" class="list-group-item addClock" >新增一筆鬧鐘</a>
 				 	 <a href="<%=request.getContextPath()%>/clock/clock.do?action=nextClockPage1" class="list-group-item getAll">查看鬧鐘詳細</a>
 				 </div>
   			 </div>
   			<div class="col-xs-12 col-sm-9" style="border-left: solid 1px #c4d4c4; " >
    			
				<div>	
					<c:if test="${param.action==\"nextClockPage\"}" >
						<jsp:include page="addClock.jsp"/>
					</c:if>
					<c:if test="${param.action!=\"nextClockPage\"}" >
						<jsp:include page="listAllClock.jsp" />
					</c:if>
					
										
				</div>
    		</div>
    	</div>




   
</body>
</html>
