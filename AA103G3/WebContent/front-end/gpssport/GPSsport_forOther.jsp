<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sport.model.*"%>
<%@ page import="java.text.*"%>

<%
	Sport sport = (Sport) request.getAttribute("sport");
%>
<jsp:useBean id="sportTypeSvc" scope="page"
	class="com.sporttype.model.SportTypeService" />

<!DOCTYPE html>
<html>
<head>
<script
	src="<%=request.getContextPath()%>/front-end/gpssport/gpsChart.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<style>
#map {
	/*     height: 500px;*/
	width: 100%;
	height: 305px;
}
/* The Close Button */
.closeSport {
	color: #0E9EA3;
	float: right;
	font-size: 35px;
	font-weight: bold;
}

.closeSport:hover, .closeSport:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

#gpsTittle {
	border-bottom: solid 1px #ccc;
	padding: 5px 0;
}

#gpsTittle h3 {
	margin: 0 60px;
	font-size: 18px;
}

#gpsTittle p {
	margin: 10px 60px 0 60px;
}

.GPSinfo {
	border-bottom: solid 1px #ccc;
}
.GPSinfo div{
	padding:0;
}
#mainMap{
	border-bottom: solid 1px #ccc;
}
</style>
</head>
<body>

	<div id="gpsTittle" class="col-xs-12 col-sm-12">
		<div class="col-xs-6 col-sm-6">
			<input type="hidden" id="sportrec_no"
				value="<%=sport.getSportrec_no()%>"> <img
				src="<%=request.getContextPath()%>/sporttypeImg/sporttypeImg.do?type_no=<%=sport.getType_no()%>"
				style="float: left; width: 50px;">
			<h3>
				<c:forEach var="sportTypeVO" items="${sportTypeSvc.all}">
					<c:if test="${sport.type_no==sportTypeVO.type_no}">
					${sportTypeVO.sport_name}
				</c:if>
				</c:forEach>
			</h3>
			<p><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sport.sport_date}" /></p>
		</div>

		<span class="closeSport">×</span>
	</div>
	<div id="mainMap" class="col-xs-12 col-sm-12 ">
		<div class="row">
			<div class="col-xs-3 col-sm-3" style="border-right: solid 1px #ccc;">
				<div class="col-xs-12 col-sm-12 GPSinfo">
					<p>距離Km</p>
					<div class="col-xs-6 col-sm-6 ">
						<img
							src="<%=request.getContextPath()%>/front-end/image/distance.png">
					</div>
					<div class="col-xs-6 col-sm-6 ">
						<h4>${sport.gps_distance/1000}</h4>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 GPSinfo " style="padding-right:0;">
					<p>時間</p>
					<div class="col-xs-4 col-sm-4 ">
						<img src="<%=request.getContextPath()%>/front-end/image/duration.png">
					</div>
					<div class="col-xs-8 col-sm-8 ">
										<h4><fmt:parseNumber  integerOnly="true" value="${sport.sport_duration/3600}"/>
				:<fmt:parseNumber  integerOnly="true" value="${(sport.sport_duration%3600)/60}"/>
				:${sport.sport_duration%60} </h4>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 GPSinfo">
					<p>平均時速 Km/Hr</p>
					<div class="col-xs-6 col-sm-6 ">
					<img
						src="<%=request.getContextPath()%>/front-end/image/pace_speed.png">
					</div>
					<div class="col-xs-6 col-sm-6 ">
					<h4><fmt:formatNumber type="number" 
           					 maxFractionDigits="2" value="${(sport.gps_distance/1000)/(sport.sport_duration/3600)}" /></h4>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 GPSinfo" style="border:0;">
					<p>燃燒卡路里 Kcal</p>
					<div class="col-xs-6 col-sm-6 ">
					<img
						src="<%=request.getContextPath()%>/front-end/image/calories-burned.png">
					</div>
					<div class="col-xs-6 col-sm-6 ">
					<h4>${sport.sport_cal}</h4>
					</div>
				</div>
			</div>
			<div class="col-xs-9 col-sm-9">
				<div class="row">
					<div id="map"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12" style="border-bottom: solid 1px #ccc; margin: 10px 0; padding: 10px 0;">
		<div class="col-xs-2 col-sm-2">
			<p style="margin: 25px 0;">攀爬高度</p>
			<img src="<%=request.getContextPath()%>/front-end/image/elevation.png">

		</div>
		<div class="col-xs-10 col-sm-10" id="climb"
			style="height: 200px; margin-top: 20px;"></div>
	</div>
	<div class="col-xs-12 col-sm-12" style="margin: 10px 0; padding: 10px 0;">
		<div class="col-xs-2 col-sm-2">
			<p style="margin: 25px 0;">即時速度</p>
			<img src="<%=request.getContextPath()%>/front-end/image/pace_speed.png">
		</div>
		<div class="col-xs-10 col-sm-10" id="pace"
			style="height: 200px; margin-top: 20px;">
		</div>
	</div>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPHR1TAv4Orcgzrr6qKXcK62sBDLYx0Xg"></script>
</body>
</html>