<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sport.model.*"%>
<%@ page import="java.text.*"%>

<%Sport sport = (Sport)request.getAttribute("sport"); %>
<jsp:useBean id="sportTypeSvc" scope="page" class="com.sporttype.model.SportTypeService"/>

<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath()%>/front-end/gpssport/gpsChart.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style>
#map {
		/*     height: 500px;*/
		width: 100%;
		height: 450px;
}
</style>
<script>
function deleteSport(item){
	   swal({
		   title: '確定要刪除嗎?',
		   type: 'warning',
		   showCancelButton: true,
		   confirmButtonColor: '#3085d6',
		   cancelButtonColor: '#d33',
		   cancelButtonText:'取消',
		   confirmButtonText: '刪除成功!'
		 }).then(function() {
		   document.getElementById("delete").submit();
		 })
}

window.addEventListener("load",init,false);
</script>
</head>
<body>
	<div class="row">
		<div class="col-xs-12 col-sm-12" style="border-bottom: solid 1px #ccc;padding-bottom:5px;">
			<div class="col-xs-6 col-sm-6">
			<input type="hidden" id="sportrec_no" value="<%=sport.getSportrec_no() %>">
			<img src="<%=request.getContextPath()%>/sporttypeImg/sporttypeImg.do?type_no=<%=sport.getType_no()%>" style="float: left; width:50px;">
			<h3 style="margin: 0 60px; font-size: 18px;">
			<c:forEach var="sportTypeVO" items="${sportTypeSvc.all}">
				<c:if test="${sport.type_no==sportTypeVO.type_no}">
					${sportTypeVO.sport_name}
				</c:if>
			</c:forEach></h3>
			<p style="margin: 10px 60px 0 60px;">
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sport.sport_date}" />
			</p>
			</div>
			<form id="delete" method="post" action="<%=request.getContextPath()%>/sport/sport.do">
				<div class="col-xs-6 col-sm-6">
					<button type="button" onclick="deleteSport(this);" class="btn btn-primary" style="position:absolute;right:40px;margin-top:10px;">刪除</button>
					<input type="hidden" name="sportrec_no" value="${sport.sportrec_no}">
					<input type="hidden"  name="action" value="delete_For_sport">
				</div>
			</form>
		</div>
		<div class="col-xs-12 col-sm-12 GPSinfo"
			style="border-bottom: solid 1px #ccc;">
			<div class="col-xs-3 col-sm-3" style="border-right: solid 1px #ccc;">
				<p>距離Km</p>
				<img src="<%=request.getContextPath()%>/front-end/image/distance.png" style="float: left;">
				<h3>${sport.gps_distance/1000}</h3>

			</div>
			<div class="col-xs-3 col-sm-3" style="border-right: solid 1px #ccc;">
				<p>時間</p>
				<img src="<%=request.getContextPath()%>/front-end/image/duration.png" style="float: left;">
				<h3><fmt:parseNumber  integerOnly="true" value="${sport.sport_duration/3600}"/>
				:<fmt:parseNumber  integerOnly="true" value="${(sport.sport_duration%3600)/60}"/>
				:${sport.sport_duration%60} </h3>
			</div>
			<div class="col-xs-3 col-sm-3">
				<p>平均時速 Km/Hr</p>
				<img src="<%=request.getContextPath()%>/front-end/image/pace_speed.png" style="float: left;">
				<h3><fmt:formatNumber type="number" 
           					 maxFractionDigits="2" value="${(sport.gps_distance/1000)/(sport.sport_duration/3600)}" /></h3>
			</div>
			<div class="col-xs-3 col-sm-3" style="border-left: solid 1px #ccc;">
				<p>燃燒卡路里 Kcal</p>
				<img src="<%=request.getContextPath()%>/front-end/image/calories-burned.png"
					style="float: left;">
				<h3 style="margin-left: 50%;">${sport.sport_cal}</h3>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="row">
				<div id="map"></div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12" style="border-bottom: solid 1px #ccc;margin:30px 0; padding:20px 0;">
			<div class="col-xs-2 col-sm-2">
				<p style="margin: 25px 0;">攀爬高度</p>
				<img src="<%=request.getContextPath()%>/front-end/image/elevation.png" style="float: left;">
				
			</div>
			<div class="col-xs-10 col-sm-10" id="climb" style="height:250px;margin-top:20px;">
				
			</div>
		</div>
		<div class="col-xs-12 col-sm-12" style="margin:30px 0;">
			<div class="col-xs-2 col-sm-2">
				<p style="margin: 25px 0;">即時速度</p>
				<img src="<%=request.getContextPath()%>/front-end/image/pace_speed.png" style="float: left;">
				<h3 style="margin-left: 50%;"></h3>
			</div>
			<div class="col-xs-10 col-sm-10" id="pace" style="height:250px;margin-top:20px;">
				
			</div>
		</div>
	</div>
	<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPHR1TAv4Orcgzrr6qKXcK62sBDLYx0Xg"></script>
</body>
</html>