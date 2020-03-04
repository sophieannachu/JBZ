<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sport.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.sporttype.model.*"%>
<%@ page import="java.text.*"%>

<%  
	MemVO memVO = (MemVO)session.getAttribute("memVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>JBZ呷百利-運動日記</title>



<!-- 共用外掛 css檔 & js檔-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery_ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/sweetalert.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_ui_1.10.3.js"></script>

<!-- header&footer&reset css檔 & js檔 -->
<script src="<%=request.getContextPath()%>/front-end/js/header.js"></script>

<link href="<%=request.getContextPath()%>/front-end/css/header.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/reset.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/footer.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/front-end/js/highchart.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/highcharts_export.js"></script>

<link href="<%=request.getContextPath()%>/front-end/css/sportdiary/sportdiary.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">

<%
	SportService sportSvc = new SportService();
	// 	取得會員有運動的日期
	List<String> listDate = sportSvc.groupbydateGPS(memVO.getMemno());
	List<String> newDateList = new ArrayList<String>();
	//  格式化日期
	SimpleDateFormat fom = new SimpleDateFormat("yyyy-MM");
	SimpleDateFormat fom1 = new SimpleDateFormat("MMM''yy");
	for (String date : listDate) {
		Date newDate = fom.parse(date);
		String formDate = fom1.format(newDate);
		newDateList.add(formDate);
	}
	pageContext.setAttribute("listDate", newDateList);
	//藉由日期取得會員運動紀錄
	for (String date : listDate) {
		List<Sport> list = sportSvc.getAllGPSByDate(memVO.getMemno(), date);
		Date newDate = fom.parse(date);
		String formDate = fom1.format(newDate);
		pageContext.setAttribute(formDate, list);
	}
%>

<script>
	$(document).ready(function() {
		$("input").click(function() {
			$(this).attr("checked", true);
			$(this).siblings().attr("checked", false);
			$(".month").removeClass("checkedItem");
		});
		$(".month").click(function() {
			$(this).addClass("checkedItem");
			$(this).siblings().removeClass("checkedItem");
		});
	});
</script>
<style>
body {
	font-family: "微軟正黑體";
}
</style>
</head>
<body>
	<div>
		<jsp:include page="/front-end/header.jsp" />
	</div>
		<div class="container-fluid title-bar">
			<div class="container" style="color:#fff; font-size:20px;padding-left: 30px;font-weight:bold;font-family: '微軟正黑體';">
					<h1>運 動 日 記</h1>
			</div>
	</div>
	
	<div class="container main"
		style="margin-top: 30px; margin-bottom: 80px; border-right: solid 1px #ccc; border-left: solid 1px #ccc;">
		<div class="col-xs-3 col-sm-3" style="padding: 0;">
			<dropdown> <input id="toggle1" type="checkbox"> <label
				for="toggle1" class="animate">GPS運動<i
				class="fa fa-bars float-right"></i></label>
			<ul class="animate" style="padding: 0;">
				<c:forEach var="date" items="${listDate}">
					<li class="animate month">
						<p>${date}<span class="badge"
								style="background-color: white; color: #63c2c5; position: absolute; right: 10px;">10</span>
						</P>
						<ul class="item">
							<%
								String date = (String) pageContext.getAttribute("date");
									List<Sport> listVO = (List<Sport>) pageContext.getAttribute(date);
									SportTypeService sportTypeSvc = new SportTypeService();
									List<SportTypeVO> listType = sportTypeSvc.getAll();
									SimpleDateFormat formatter = new SimpleDateFormat("M/dd");
									int count = 1;
									for (Sport sportVO : listVO) {
										String type = "";
										for (SportTypeVO sportTypeVO : listType) {
											if (sportTypeVO.getType_no() == sportVO.getType_no()) {
												type = sportTypeVO.getSport_name();
											}
										}
							%>
							<form id='sport' method="post" action="<%=request.getContextPath()%>/sport/sport.do">
								<li onclick="this.parentElement.submit();">
								<span><%=formatter.format(sportVO.getSport_date())%></span>
								<span style="margin-left: 10%"> <%=type%></span> 
								<span style="position: absolute; right: 10px;"><%=sportVO.getGps_distance() / 1000%>km</span>
								</li> 
								<input type="text" name="sportrec_no" value="<%=sportVO.getSportrec_no()%>">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="getOne_For_Display_GPS">
							</form>
							<%
								}
							%>
						</ul>
					</li>
				</c:forEach>
			</ul>
			<form id="watchSport" action="<%=request.getContextPath()%>/front-end/sportdairy.jsp?action=watchSport">
				<input id="toggle2" type="checkbox"
					onclick="document.getElementById('watchSport').submit()"
					name="action" value="watchSport"> 
				<label for="toggle2" class="animate">計時運動</label>
			</form >
			<form id="sportanyl" action="<%=request.getContextPath()%>/front-end/sportdairy.jsp">
				<input id="toggle3" type="checkbox" onclick="document.getElementById('sportanyl').submit()" 
					name="action" value="sportanyl">
				<label for="toggle3" class="animate">統計分析</label> 
			</form>
			
			</dropdown>
		</div>
		<div id="showpanel" class="col-xs-9 col-sm-9" style="border-left: solid 1px #ccc;min-height: 600px;">
			<c:if test="${param.action==\"getOne_For_Display_GPS\"}">
				<jsp:include page="/front-end/gpssport/GPSsport.jsp" />
			</c:if>
			<c:if test="${param.action==\"watchSport\"}">
				<jsp:include page="/front-end/watchsport/watchsport.jsp" />
			</c:if>
			<c:if test="${param.action==\"sportanyl\"}">
				<jsp:include page="/front-end/sportanyl/sportanyl.jsp" />
			</c:if>
			
		</div>
	</div>
	<div>
		<jsp:include page="/front-end/footer.jsp" />
	</div>

</body>
</html>