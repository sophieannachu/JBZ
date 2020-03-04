<%@ page import="com.memach.controller.AddMemAch"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.*" %>
<%@ page import="com.memach.model.*" %>
<%@ page import="com.sport.model.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="memAchSvc" scope="page" class="com.memach.model.MemAchService" />
<jsp:useBean id="sportSvc" scope="page" class="com.sport.model.SportService" />
<jsp:useBean id="pedometerSvc" scope="page" class="com.pedometer.model.PedometerService" />
<jsp:useBean id="groupListSvc" scope="page" class="com.groupList.model.GroupListService" />

<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	pageContext.setAttribute("memVO",memVO);
	Integer memno =memVO.getMemno();
	Sport sport = sportSvc.getSumByMem(memVO.getMemno());
	pageContext.setAttribute("sport", sport);
	Integer total_ped = pedometerSvc.getSumByMemno(memno);
	pageContext.setAttribute("total_ped", total_ped);
	Integer total_group = groupListSvc.getCountMyGroup(memno);
	pageContext.setAttribute("total_group", total_group);
	
	AddMemAch addAch = new AddMemAch();
	addAch.addMemAch(memno);
	
	List<MemAchVO> memAchList = memAchSvc.getAllByMemMemAch(memno);
	pageContext.setAttribute("memAchList", memAchList);
	
	
%>	


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>JBZ呷百利</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/css/sweetalert2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery_ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">

<script src="<%=request.getContextPath()%>/front-end/js/sweetalert.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_ui_1.10.3.js"></script>

<!-- header&footer&reset css檔 & js檔 -->
<script src="<%=request.getContextPath()%>/front-end/js/header.js"></script>

<link href="<%=request.getContextPath()%>/front-end/css/header.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/reset.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/footer.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/body.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/front-end/js/highchart.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/highcharts_export.js"></script>
<link href="<%=request.getContextPath()%>/front-end/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">
<script>
arrAllDate= new Array();
arrAllCal= new Array();
arrAllDuration= new Array();

	$(function(){
		
		$.ajax({
			 type:"post",
			 url:"<%=request.getContextPath()%>/sport/getSportjson.do",
			 data : {action : "getAllanyl"},
			 dataType : "json",
			 success : function(data) {
			 			getDataAll(data);
			 			calAvg(arrAllDate, arrAllCal);
			 			durationAvg(arrAllDate, arrAllDuration);
						},
			 error : function() {
			 			alert("error")
			}
	})
	});
	function getDataAll(data) {
		var n = 0;
		for (var i = (data.length - 1); i >= 0; i--) {
			arrAllCal[n] = data[i].cal;
			arrAllDuration[n] = data[i].duration/60;
			arrAllDate[n] = data[i].date.toString();
			n++;
		}
	}
	function calAvg(date, cal) {
		$('.calAvg').highcharts(
				{
					chart : {
						type : 'column'
					},
					title : {
						text : '每日燃燒卡路里平均統計'
					},
					xAxis : [ {
						categories : date,
						crosshair : true
					} ],
					yAxis : {
						min : 0,
						title : {
							text : '大卡(Kcal)'
						}
					},
					legend : {
						enabled : false
					},
					tooltip : {
						formatter : function() {
							return "<b style='font-weight:5px;'>" + this.x
									+ "</b><br>" + this.series.name + ":"
									+ this.y+"Kcal";
						}
					},
					series : [ {
						name : '燃燒卡路里',
						color: '#f1b406',
						data : cal
					} ]
				});
	}
	
	function durationAvg(date, duration) {
		$('.durationAvg').highcharts(
				{
					chart : {
						type : 'column'
					},
					title : {
						text : '每日運動時間平均統計'
					},
					xAxis : [ {
						categories : date,
						crosshair : true
					} ],
					yAxis : {
						min : 0,
						title : {
							text : '分(min)'
						}
					},
					legend : {
						enabled : false
					},
					tooltip : {
						formatter : function() {
							var min = Math.floor(this.y);
							return "<b style='font-weight:5px;'>" + this.x
									+ "</b><br>" + this.series.name + ":"
									+ min+"min";
						}
					},
					series : [ {
						name : '運動時間',
						color: '#6ba451',		
						data : duration
					} ]
				});
	}
	
	$(function(){
		<c:forEach var="memAchVO" items="${memAchList}">
		var id=${memAchVO.achno};
		console.log(id);
		console.log($('#'+id));
		$('#'+id).css("opacity","1");
		</c:forEach>
	});
	

</script>
<style>
body {
	font-family: "微軟正黑體";
}
*{
	font-size:18px;
}
.logout{
	color:#9C7562;
}
.logout:focus,.logout:hover{
	color:#9C7562;
	cursor: pointer;
}
</style>
</head>
<body>


		<div style="background-color: #494949; height: 10px;"></div>
	
		<div class="container header" id="header">
			<div id="personalPage" >				
				<ul>
					<li>
						<a href="<%=request.getContextPath()%>/front-end/personalPage.jsp?memno=${memVO.memno}"> 
							<img  src="<%=request.getContextPath()%>/personal/personal.do?memno=${memVO.memno}">
							<h5>${memVO.name} 你好</h5>
						</a>
					</li>					
					<li >
					<form method="post" id="logout" action="<%=request.getContextPath()%>/mem/login.do">
						<input type="hidden" name="action" value="logout">
					</form>
						<a class="logout" onclick="document.getElementById('logout').submit();" ><i class="fa fa-sign-out" aria-hidden="true"></i>登出</a>				
					</li>
				</ul>		
			</div>
			
			<div style="padding: 15px 0;">
				<a href="<%=request.getContextPath()%>/front-end/homePage.jsp"><img src="<%=request.getContextPath()%>/front-end/image/jbz.png" id="logo"></a>
				<img src="<%=request.getContextPath()%>/front-end/image/slogan.png" id="slogan">
			</div>
			<div style="clear: both;"></div>
			<div class="fixmenu" style="width: 1000px;text-align: center;padding-top: 30px;">
				<ul style="text-align: center;">
						<li style="border-left: solid 1px #9cd9da;">
						<a href="<%=request.getContextPath()%>/foodlist/foodlist.do?action=getByDateMem">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/Food-Vegeterian-Food-icon.png">
							<div>營養日記</div>
						</a>
						</li>
						<li>
						<a href="<%=request.getContextPath()%>/front-end/sportdairy.jsp?action=sportanyl">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/Running_icon.png">
							<div>運動日記</div>
						</a>	
						</li>
						<li>
						<a href="<%=request.getContextPath()%>/front-end/healthCheck.jsp?action=basicCheck">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/az.png">
							<div>自我健檢</div>
						</a>	
						</li>
						<li>
						<a href="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do?action=main&memno=${memVO.memno}">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/users-icon.png">
							<div>揪團</div>
						</a>	
						</li>
						<li>
						<a href="<%=request.getContextPath()%>/hpl/HplServlet.do?action=main">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/plan.png">
							<div>健康計畫</div>
						</a>	
						</li>
						<li>
						<a href="<%=request.getContextPath()%>/front-end/ClockIndex.jsp?action=nextClockPage">
							<img class="imag" src="<%=request.getContextPath()%>/front-end/image/clock.png">
							<div>健康鬧鐘</div>
						</a>	
						</li>
				</ul>
			</div>
			<div class="smallmenu hidden1" style="max-width: 1000px;text-align: center;padding:0 0;">
				<ul style="text-align: center;">
						<li class="menutitle" style="border-left: solid 1px #fff;">
							<a href="<%=request.getContextPath()%>/foodlist/foodlist.do?action=getByDateMem">營養日記</a>
							<div class="menuimg" >
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/Food-Vegeterian-Food-icon.png">
							</div>
							
						</li>
						<li class="menutitle">
							<a href="<%=request.getContextPath()%>/front-end/sportdairy.jsp?action=sportanyl">運動日記</a>
							<div class="menuimg">
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/Running_icon.png">
							</div>
						</li>
						<li class="menutitle">
							<a href="<%=request.getContextPath()%>/front-end/healthCheck.jsp?action=basicCheck">自我健檢</a>
							<div class="menuimg">
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/az.png">
							</div>
						</li>
						<li class="menutitle">
							<a href="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do?action=main&memno=${memVO.memno}">揪團</a>
							<div class="menuimg" >
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/users-icon.png">
							</div>
						</li>
						<li class="menutitle">
							<a href="<%=request.getContextPath()%>/hpl/HplServlet.do?action=main">健康計畫</a>
							<div class="menuimg">
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/plan.png">
							</div>
						</li>
						<li class="menutitle">
							<a href="<%=request.getContextPath()%>/front-end/ClockIndex.jsp?action=nextClockPage">健康鬧鐘</a>
							<div class="menuimg">
								<img class="imag" src="<%=request.getContextPath()%>/front-end/image/clock.png">
							</div>
						</li>
				</ul>
			</div>
		</div>


		<div id="change"></div>
		<div class="container content">
<!-- 		<div class="row"> -->
			<div id="stastext">全體會員統計分析</div>
				<div class="col-xs-12 col-sm-12 stascontainer" style="text-align: center;">
							<ul>
								<li class="stasimage calAvg" >
									
								</li>
								<li class="durationAvg">
								
								</li>
							</ul>
				</div>


						<div class="col-xs-12 col-sm-12">
							<div id="goodtext">成就</div>
						</div>
						
						<!-- <div class="row"> -->
						<div class="col-sm-9 goodandsupmecontainer">
							
						
							<div id="goodjob">
								<ul>
									<li class="ach">
									<div class="calculate">
									累積步數:<span>${total_ped}</span>
									</div>
									<img id="10" src="<%=request.getContextPath()%>/front-end/ach/image/30000.png">
									<img id="20" src="<%=request.getContextPath()%>/front-end/ach/image/50000.png">
									<img id="30" src="<%=request.getContextPath()%>/front-end/ach/image/70000.png">
									<img id="40" src="<%=request.getContextPath()%>/front-end/ach/image/100000.png">
									<img id="50" src="<%=request.getContextPath()%>/front-end/ach/image/150000.png">
									</li>
									<li class="ach">
									<div class="calculate">
									累積揪團數:<span>${total_group}</span>團
									</div>
									<img id="60" src="<%=request.getContextPath()%>/front-end/ach/image/group5.png">
									<img id="70" src="<%=request.getContextPath()%>/front-end/ach/image/group10.png">
									<img id="80" src="<%=request.getContextPath()%>/front-end/ach/image/group20.png">
									<img id="90" src="<%=request.getContextPath()%>/front-end/ach/image/group30.png">
									<img id="100" src="<%=request.getContextPath()%>/front-end/ach/image/group50.png">
									</li>
									<li class="ach">
									<div class="calculate">
									累積運動時數:<span><fmt:parseNumber  integerOnly="true" value="${sport.sport_duration/3600}"/></span>小時
									</div>									
									<img id="110" src="<%=request.getContextPath()%>/front-end/ach/image/exercise10.png">
									<img id="120" src="<%=request.getContextPath()%>/front-end/ach/image/exercise20.png">
									<img id="130" src="<%=request.getContextPath()%>/front-end/ach/image/exercise30.png">
									<img id="140" src="<%=request.getContextPath()%>/front-end/ach/image/exercise50.png">
									<img id="150" src="<%=request.getContextPath()%>/front-end/ach/image/exercise100.png">
									</li>
									<li class="ach">
									<div class="calculate">
									累積消耗卡路里:<span>${sport.sport_cal}</span>Kcal
									</div>									
									<img id="160" src="<%=request.getContextPath()%>/front-end/ach/image/fire5000.png">
									<img id="170" src="<%=request.getContextPath()%>/front-end/ach/image/fire10000.png">
									<img id="180" src="<%=request.getContextPath()%>/front-end/ach/image/fire15000.png">
									<img id="190" src="<%=request.getContextPath()%>/front-end/ach/image/fire25000.png">
									<img id="200" src="<%=request.getContextPath()%>/front-end/ach/image/fire35000.png">
									</li>
								</ul>
							</div>
						</div>
						<div class="col-sm-3 ">
							<div class="superman">
								<span><p>陳展榮</p><br><p>朱學君</p><br><p>柯志翰</p></span>
							</div>	
			<!-- 			</div> -->
						</div>
								
			<!-- </div> -->
		</div>

        <div class="container-fluid footer" >
					<div class="container">
						<div class="row"><!--row-->
							<div class="col-xs-12 col-sm-6">
							<h2>COPYRIGHT</h2>
							<div style="float:left;margin-top:-15px;padding-right: 8px;">
							<img src="<%=request.getContextPath()%>/front-end/image/logowhite.png" width="80px;" >
							</div>
							<p>呷百利股份有限公司</p>
							<p>地　　址：803 中壢市中大路300號資策會</p>
							<p>客服信箱：nml4564@gmail.com</p>
							
								
							</div>
							<div class="col-xs-12 col-sm-6">
							<h2>QUICKLINK</h2>
							<div>
								<div style="margin-top:-10px;">
								<div class="row">
									<div class="col-xs-12 col-sm-6" style="padding-right: 0;">
										<ul class="downmenu clearfix">
											<li>
												<a href="" class="hover">關於我們</a>
											</li>
											<li>
												<a href="" class="hover">APP</a>
											</li>
											<li>
												<a href="" class="hover">健康分析</a>
											</li>
											<li>
												<a href="" class="hover">健康資訊</a>
											</li>
											<li>
												<a href="" class="hover">登入註冊</a>
											</li>
										</ul>
									</div>
									<div class="col-xs-12 col-sm-6" style="text-align:left;">
										<img src="<%=request.getContextPath()%>/front-end/image/jbz_qrcode.jpg" width="80px;" >
									</div>
								</div>
								</div>
							</div>
							</div>
						</div>
					</div>
					  	
					<div class="copy">
						<h5 style="margin:0;color: #fff;">Copyright©2016 呷百利JIABAZE All Rights Reserved</h5>
					</div>	
				</div>
				<a style="display:scroll;position:fixed;bottom:0px;right:5px;" href="#header" class="hover">
				<img src="<%=request.getContextPath()%>/front-end/image/btn_pt.png">
				</a>
</body>
</html>