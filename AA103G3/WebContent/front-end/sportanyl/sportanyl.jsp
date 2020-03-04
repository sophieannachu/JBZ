<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style>
h3{
	font-size:28px;
	font-weight:900;
}
*{
	font-size:18px;
}
</style>
<script src="<%=request.getContextPath()%>/front-end/sportanyl/sportanyl.js"></script>
<script>
$(document).ready(
		 function test(){

			$.ajax({
					 type:"post",
					 url:"<%=request.getContextPath()%>/sport/getSportjson.do",
					 data : {action : "getGPSinfo",memno : "${memVO.memno}"},
					 dataType : "json",
					 success : function(data) {
					 			getData(data);
					 			distanceAll(arrDate, arrDistance);
					 			durationGPS(arrDate, arrDuration);
					 			calGPS(arrDate, arrCal);
								},
					 error : function() {
					 			alert("error")
					}
			})
			
			$.ajax({
					 type:"post",
					 url:"<%=request.getContextPath()%>/sport/getSportjson.do",
					 data : {action : "getWatchinfo",memno : "${memVO.memno}"},
					 dataType : "json",
					 success : function(data) {
					 			getDataWatch(data);
					 			durationWatch(arrWDate, arrWDuration);
					 			calWatch(arrWDate, arrWCal);
								},
					 error : function() {
					 			alert("error")
					}
			})
			
			$.ajax({
					 type:"post",
					 url:"<%=request.getContextPath()%>/sport/getSportjson.do",
					 data : {action : "getAllinfo",memno : "${memVO.memno}"},
					 dataType : "json",
					 success : function(data) {
					 			getDataAll(data);
					 			durationAll(arrAllDate, arrAllDuration);
					 			calAll(arrAllDate, arrAllCal);
								},
					 error : function() {
					 			alert("error")
					}
			})
			
			<c:if test="${not empty errorMsgs}">
			var str="";
			<c:forEach var="message" items="${errorMsgs}">
				 str +="${message}\n";
			</c:forEach>
			swal(str);
			</c:if>
	});
</script>
</head>
<body>
	<div class="col-xs-12 col-sm-12 anylitem">
		<h3>GPS運動距離統計</h3>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 anylcontain">
		<div class="row">
			<div id="tabs" style="margin: 0 20px;">
				<ul>
					<li><a href="#tabs-1">GPS運動</a></li>
				</ul>
				<div id="tabs-1" class="distanceAll"></div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 anylitem">
		<h3>運動時間統計</h3>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 anylcontain">
		<div class="row">
			<div id="tab2" style="margin: 0 20px;">
				<ul>
					<li><a href="#tabs-1">GPS運動</a></li>
					<li><a href="#tabs-2">計時運動</a></li>
					<li><a href="#tabs-3">整體統計</a></li>
				</ul>
				<div id="tabs-1" class="durationGPS"></div>
				<div id="tabs-2" class="durationWatch"></div>
				<div id="tabs-3" class="durationAll"></div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 anylitem">
		<h3>燃燒卡路里統計</h3>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 anylcontain">
		<div class="row">
			<div id="tab3" style="margin: 0 20px;">
				<ul>
					<li><a href="#tabs-1">GPS運動</a></li>
					<li><a href="#tabs-2">計時運動</a></li>
					<li><a href="#tabs-3">整體統計</a></li>
				</ul>
				<div id="tabs-1" class="calGPS"></div>
				<div id="tabs-2" class="calWatch"></div>
				<div id="tabs-3" class="calAll"></div>
			</div>
		</div>
	</div>
</body>
</html>