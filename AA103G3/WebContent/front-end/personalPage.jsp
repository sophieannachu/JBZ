<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.memach.model.*"%>
<%@ page import="com.sport.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="sportTypeSvc" scope="page"
	class="com.sporttype.model.SportTypeService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="friSvc" scope="page"
	class="com.friend.model.FriendService" />
<jsp:useBean id="memAchSvc" scope="page"
	class="com.memach.model.MemAchService" />
<jsp:useBean id="foodSvc" scope="page" class="com.foodlist.model.FoodService" />

<%@ include file="/front-end/friend/personalPage.file" %>

<%

SportService sportSvc = new SportService();
List<Sport> list = sportSvc.getAllByMemno(memVOO.getMemno());
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>JBZ呷百利-${memVOO.name}  </title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/css/jquery_ui.css" />
<script src="<%=request.getContextPath()%>/front-end/js/jquery_1.9.1.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/js/jquery_ui_1.10.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front-end/js/cal.js"></script>
<link href="<%=request.getContextPath()%>/front-end/css/cal.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/front-end/css/personalleft.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/front-end/js/hightstock.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/js/highcharts_export.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/css/sweetalert2.css">
<script src="<%=request.getContextPath()%>/front-end/js/sweetalert.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/personalPage.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/gpssport/gpsChart.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/imgareaselect-default.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/jquery.imgareaselect.pack.js"></script>

<link href="<%=request.getContextPath()%>/front-end/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">
<style>
.ui-dialog-titlebar {
	border: 0;
	padding: 0;
	background: #0E9EA3;
}
body {
	font-family: "微軟正黑體";
}
</style>

<script>

//營養日記設定
$(function() {
	$.ajax({
		type:"POST",
		url:"<%=request.getContextPath()%>/foodlist/foodlist.do",
		data:{action:"findDate",memno:"${memnoThisPage}"},
		dataType:"json",
		success:function (data){
			$("#my-calendar").zabuto_calendar({
				data : data,
				action: function (data) {
					var date= this.id;
					$.ajax({
						type:"POST",
						url:"<%=request.getContextPath()%>/food/getFoodjson.do",
						data:{action:"getByDateMem",memno:"${memnoThisPage}",date:date},
						dataType:"json",
						success:function (data){
							prot = data[0].prot;
							fat = data[0].fat;
							cho = data[0].cho;
							sugar = data[0].sugar;
							df = data[0].df;
							na = data[0].na;
							ca = data[0].ca;
							k = data[0].k;
							getFdChart(prot,fat,cho,sugar,df,na,ca,k);
							console.log(prot);
							console.log(data);
							openfdChart();
						}
					})
				}
			})
		},
		error:function(){alert("error")}
		    })
});	
 //營養日記End
 function openfdChart(){
	 var modal = document.getElementById('myModal');					
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	// When the user clicks the button, open the modal
	modal.style.display = "block";
	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	    modal.style.display = "none";
	}
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
 }
//google Chart For營養日記
function getFdChart(a1,a2,a3,a4,a5,a6,a7,a8){
	 var data;
	  var chart;

	  google.charts.load('current', {'packages':['corechart']});

	  google.charts.setOnLoadCallback(drawChart);

	  function drawChart() {
	
	  data = new google.visualization.DataTable();
	  data.addColumn('string', 'Topping');
	  data.addColumn('number', 'Slices');
	  data.addRows([
	  ["蛋白質攝取:"+a1+"", a1],
	  ["脂肪攝取:"+a2+"", a2],
	  ["膽固醇攝取:"+a3+"", a3],
	  ["糖攝取:"+a4+"", a4],
	  ["膳食纖維攝取:"+a5+"", a5],
	  ["鈉攝取:"+a6+"", a6],
	  ["鈣攝取:"+a7+"", a7],
	  ["鉀攝取:"+a8+"", a8],
	  ]);

	  var options = {'title':'營養素分析(公克)',
	  'width':500,
	  'height':400 };
	  
	  chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	  google.visualization.events.addListener(chart, 'select', selectHandler);
	  chart.draw(data, options);
	  }

	  function selectHandler() {
	  var selectedItem = chart.getSelection()[0];
	  var value = data.getValue(selectedItem.row, 0);
	  swal('營養素 ' + value);
	  }
 }
 		
 //Google Chart End

// For統計分析圖
$(function() {
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sport/getSportjson.do",
		data : {
			action : "getGPS",
			memno : "${memnoThisPage}"
		},
		dataType : "json",
		success : function(data) {
			getData(data);
			distanceAll(arrComDistance);

		},
		error : function() {
			alert("error")
		}
	})

	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/sport/getSportjson.do",
		data : {
			action : "getAll",
			memno : "${memnoThisPage}"
		},
		dataType : "json",
		success : function(data) {
			getAllData(data);
			durationAll(arrComDuration);
			calAll(arrComCal);

		},
		error : function() {
			alert("error")
		}
	})
	
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/food/getFoodjson.do",
		data : {
			action : "getFoodCal",
			memno : "${memnoThisPage}"
		},
		dataType : "json",
		success : function(data) {
			getCalData(data);
			calTake(arrComCalTake);

		},
		error : function() {
			alert("error")
		}
	})
});

</script>

</head>
<body onload="connect();">
	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/front-end/homePage.jsp"> <img
				src="<%=request.getContextPath()%>/front-end/image/logowhite.png">
				<!-- 				<span>JIABAZE</span> -->
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" oninput="search();"
						class="form-control searchbar" placeholder="搜尋會員">
					<div class="friSearch"
						style="z-index: 10; position: fixed; min-width: 200px; padding-left: 0;">
						<ul class="friFind"
							style="list-style: none; padding-left: 0; margin-bottom: 0px;">
						</ul>
					</div>
				</div>
				<i class="fa fa-search" aria-hidden="true"></i>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a
					href="<%=request.getContextPath()%>/front-end/personalPage.jsp?memno=${memnop}"
					style="padding: 10px 0 10px 0;">
					<img src="<%=request.getContextPath()%>/personal/personal.do?memno=${memnop}"></a></li>
				<li><a
					href="<%=request.getContextPath()%>/front-end/personalPage.jsp?memno=${memnop}">${memVO.name}</a></li>
				<li><a
					href="<%=request.getContextPath()%>/mem/login.do?action=logout"><i class="fa fa-sign-out" aria-hidden="true"></i>登出</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	</nav>
	<div class="container" style="padding-top: 55px;">
		<div class="col-xs-3 col-sm-3 personal">
			<div class="personalPic">
				<div class="picDiv"  style="position:relative;">
					<img src="<%=request.getContextPath()%>/personal/personal.do?memno=${memnoThisPage}">
					<c:if test="${isSelf}">
					<div class="uploadshow" style="position:absolute; z-index:5; bottom:0px;width:100%;padding:10px;">
						<img src="<%=request.getContextPath()%>/front-end/image/photo.png" style="width:25px; margin-right:5px;">
						<span class="uploadphoto" onclick="uploadphoto();" style="color:#fff;cursor: pointer;">
							上傳照片
						</span>
					</div>
					</c:if>		
				</div>
				<h2>${memVOO.name}</h2>
				<c:if test="${!isSelf}">
					<c:if test="${!isFrid}">
						<c:if test="${!isApply}">
							<button class="sendRequest" onclick="sendRequest();">加好友</button>
							<button class="sended" style="display: none;">已送出好友申請</button>
						</c:if>
						<c:if test="${isApply}">
							<button>已送出好友申請</button>
						</c:if>
					</c:if>
					<c:if test="${isFrid}">
						<button>已好友</button>
						<button type="button" onclick="deleteFri(this,${memnop},${memnoThisPage});">取消好友</button>
					</c:if>
				</c:if>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6"
					style="padding: 0 0 0 30px; text-align: center;">
					<form method="post"
						action="<%=request.getContextPath()%>/front-end/friend/friends.jsp">
						<button class="btn btn-info" type="submit" style="width: 80%;">好友</button>
						<input type="hidden" name="memno" value="${memnoThisPage}">
					</form>

				</div>
				<div class="col-xs-12 col-sm-6"
					style="padding: 0 30px 0 0; text-align: center;">
					<div style="width: 80%;">
						<c:if test="${!isSelf}">
							<button class="btn btn-info" type="submit" style="width: 100%;"><%=Integer.parseInt(memVOO.getCpt()) == 0 ? "公開" : "好友限定"%></button>
						</c:if>
						<c:if test="${isSelf}">
							<div class="dropdown">
								<select name="one" onchange="cptUpdate(this,${memnop});" class="dropdown-select">
									<option value="0" <%="0".equals(memSvc.getOneMem(memVO.getMemno()).getCpt())?"selected":"" %>>公開</option>
									<option value="1" <%="1".equals(memSvc.getOneMem(memVO.getMemno()).getCpt())?"selected":"" %>>好友限定</option>
								</select>
							</div>
						</c:if>
					</div>
				</div>
			</div>

			<div class="about">
				<h4 style="color: #0E9EA3; font-weight: bold;">關於我</h4>
				<table class="table table-hover table-striped table-condensed ">
					<tbody>
						<tr>
							<td>性別</td>
							<td><%="0".equals(memVOO.getSex())?"女":"男" %></td>
						</tr>
						<tr>
							<td>生日</td>
							<td> <fmt:formatDate pattern="yyyy.MM.dd" value="${memVOO.bir}" /></td>
						</tr>
						<tr>
							<td>身高</td>
							<td>${memVOO.height }公分</td>
						</tr>
						<tr>
							<td>體重</td>
							<td>${memVOO.weight }公斤</td>
						</tr>
					</tbody>
				</table>
			</div>
			<c:if test="${isSelf}">
				<div class="friendask">
					<h4
						style="border-bottom: solid 1px #eee; padding: 0px 0 10px 5px; color: #0E9EA3; font-weight: bold;">好友申請</h4>
					<div class="row">
						<c:forEach var="friendVO"
							items="${friSvc.getAllByFriFriend(memnop,\"0\")}">
							<div class="col-xs-12 col-sm-12 friendrequest">
							  <div class="row">
								<div class="col-sm-5 friendimg">
									<img
										src="<%=request.getContextPath()%>/personal/personal.do?memno=${friendVO.memno}"
										style="width: 100%;">
								</div>
								<div class="col-xs-7 col-sm-7 requestinfo">
									<a
										href="<%=request.getContextPath()%>/front-end/personalPage.jsp?memno=${friendVO.memno}">${memSvc.getOneMem(friendVO.memno).name}</a><br>
									<button onclick="comfirmFri(this,${friendVO.memno},${memnop})">確認好友</button>
									<button onclick="cancleFri(this,${friendVO.memno},${memnop})">取消</button>
								</div>
								</div>
							</div>

						</c:forEach>
					</div>
				</div>
			</c:if>

			<div class="ach">
				<h4
					style="border-bottom: solid 1px #eee; padding: 10px 0 10px 5px; color: #0E9EA3; font-weight: bold;">成就勳章</h4>
				<div class="row">
					<c:forEach var="memAchVO" items="${memAchList}">
						<div class="col-xs-4 col-sm-4">
							<img src="<%=request.getContextPath()%>/ach/achImg.do?achno=${memAchVO.achno}" style="width: 100%;">
						</div>
					</c:forEach>
				</div>
			</div>

		</div>
		<div class="col-xs-9 col-sm-9 mainleft">
			<c:if test="${isOpen||isFrid||isSelf}">
				<div class="col-xs-12 col-sm-12 calendar">
					<h4
						style="border-bottom: solid 1px #eee; padding-bottom: 10px; color: #0E9EA3; font-weight: bold; margin-left: 5px;">營養日記</h4>
					<div id="my-calendar"></div>
				</div>
				<div class="col-xs-12 col-sm-12 anyl">
					<div class="row">
						<div id="tabs">
							<ul>
								<li><a href="#tabs-1">運動距離</a></li>
								<li><a href="#tabs-2">運動時間</a></li>
								<li><a href="#tabs-3">消耗卡路里</a></li>
								<li><a href="#tabs-4">卡路里攝取</a></li>
							</ul>
							<div id="tabs-1" class="distance"></div>
							<div id="tabs-2" class="duration"></div>
							<div id="tabs-3" class="cal"></div>
							<div id="tabs-4" class="caltake"></div>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 sportdiary">
					<h4
						style="border-bottom: solid 1px #eee; padding-bottom: 10px; color: #0E9EA3; font-weight: bold; margin-left: 5px;">運動日記</h4>

					<c:forEach var="sport" items="${list}">
						<div class="col-xs-12 col-sm-12 watchinfo"
							style="border-bottom: solid 1px #eee; padding: 5px 0;">
							<div class="row">
								<div class="col-xs-4 col-sm-4" style="padding: 0 0 0 30px;">
									<img
										src="<%=request.getContextPath() %>/sporttypeImg/sporttypeImg.do?type_no=${sport.type_no}"
										style="float: left; margin-top: 10px; width: 50px;">
									<div style="margin: 5px 0px 5px 60px; font-size: 18px;">
										<c:forEach var="sportTypeVO" items="${sportTypeSvc.all}">
											<c:if test="${sportTypeVO.type_no==sport.type_no}">
												${sportTypeVO.sport_name}
											</c:if>
										</c:forEach>
									</div>
									<div style="margin: 5px 0px 5px 60px;"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sport.sport_date}" /></div>
								</div>
								<div class="col-xs-3 col-sm-3">
									<p>時間</p>
									<img
										src="<%=request.getContextPath()%>/front-end/image/duration.png"
										style="float: left;">
									<h3>
										<fmt:parseNumber integerOnly="true"
											value="${sport.sport_duration/3600}" />
										:
										<fmt:parseNumber integerOnly="true"
											value="${(sport.sport_duration%3600)/60}" />
										:${sport.sport_duration%60}
									</h3>
								</div>
								<div class="col-xs-3 col-sm-3">
									<p>燃燒卡路里</p>
									<img
										src="<%=request.getContextPath()%>/front-end/image/calories-burned.png"
										style="float: left;">
									<h3 style="position: absolute; right: 30px;">${sport.sport_cal}</h3>
								</div>
								<c:if test="${sport.type_no<=53}">
									<div class="col-xs-2 col-sm-2">
										<button type="button" onclick="readMore(this);"
											class="btn btn-primary" style="margin: 20px;">詳細內容</button>
										<input type="hidden" name="sportrec_no"
											value="${sport.sportrec_no}">
									</div>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${(!isOpen)&&(!isFrid)&&(!isSelf) }">
				<div class="col-xs-12 col-sm-12 upOpen"
					style="background-color: #fff; margin: 15px 0;">
					<h2>不公開</h2>
				</div>
			</c:if>
		</div>
	</div>

<!-- 運動日記modal -->
<div id="dialog-showmore" title="GPS運動紀錄"></div>

<!-- 營養日記modal	 -->
<div id="myModal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">×</span>
      <h2>營養日記</h2>
    </div>
    <div class="modal-body">
		<div id="chart_div" style="width:400; height:400;"></div>
    </div>
  </div>
</div>

<!-- 上傳照片modal -->
<div id="photoModal" class="modal">
  <div class="modal-content" style="width:450px;">
    <div class="modal-header" style="height:40px;">
      <span class="closePhoto" >×</span>
    </div>
    <div class="modal-body uploadBody">
		<div >
					<div style="position:relative;padding:10px;">
						<span>選擇上傳圖片</span>
						<input type="file" id="photo" name="photo" style="margin-top: 5px;opacity:0;z-index:5;position:absolute;top:0;" />						
					</div>
					<div class="uploadimage">
						<img src="" id="preview" style="width:400px;">
					</div>
						<input type="hidden" name="x1" value="" />
						<input type="hidden" name="y1" value="" />
						<input type="hidden" name="x2" value="" />
						<input type="hidden" name="y2" value="" />
						<input type="button" onclick="cut();" name="submit" value="Submit" />
		</div>
    </div>
  </div>
</div>


	<script type="text/javascript">
	
	function readMore(event){
		 var sportrec_no = $(event).next().val();
		 $.ajax({
			 type:"POST",
			 url:"<%=request.getContextPath()%>/sport/sport.do",
			 data:{sportrec_no:sportrec_no,action:"getOne_For_Other_GPS"},
			 dataType:"text",
			 success:function (data){
				 document.getElementById("dialog-showmore").innerHTML=data;
				 init();
				 $(".closeSport").click(function(){
					 $( "#dialog-showmore" ).dialog( "close" );
				 })
		     },
           error:function(){alert("error")}
         });
		 $( "#dialog-showmore" ).dialog( "open" );
	}
	
	$( function() {
		$( "#dialog-showmore" ).dialog({
		  autoOpen: false,
		  resizable: false,
		  position: ["bottom",0],
		  height: "auto",
		  width: 800,
		  modal: true,
		  dialogClass: "dlg-no-close"
		});
	} );
	
// 	 socket連線 
	var MyPoint = "/AddNotify/${memVOO.memno}/${memVO.memno}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		
		function connect() {
			// 建立 websocket 物件
			webSocket = new WebSocket(endPointURL);
			console.log("${memVOO.memno}");
			console.log("${memVO.memno}");
			
			webSocket.onopen = function(event) {
				
			};

			webSocket.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data);
				swal({
					  title: '好友申請通知',
					  text: jsonObj.name+'想加你好友',
					  imageUrl:"<%=request.getContextPath()%>/personal/personal.do?memno="+jsonObj.memno,
					  imageWidth: 300,
					  imageHeight: 300,
					  animation: false
				}).then(function() {
						window.location.reload();
				})
			};

			webSocket.onclose = function(event) {

			};
		}
		
		function sendRequest() {
		        var jsonObj = {"userName" : "${memVO.name}", "message" : "想加你好友"};
		        console.log(jsonObj);
		        webSocket.send(JSON.stringify(jsonObj));
		}
		
		function disconnect () {
			webSocket.close();
		}

// socket End
		
		$(function(){
			$(".sendRequest").click(function(){
				$(this).css("display","none");
				$(".sended").css("display","");
			})
		})
		

	</script>
	

	<script type="text/javascript">
	
	 $(function(){
		 if($(".upOpen").height()<$(".personal").height()){
			 $(".upOpen").height($(".personal").height()-30);
		 } 
	 })
	 
//上傳照片裁切
		 function cut(){
		 var x,  y, width, height;
		 x=$('input[name="x1"]').val()*scale;
		 y=$('input[name="y1"]').val()*scale;
		 width=$('input[name="x2"]').val()-$('input[name="x1"]').val();
		 height=$('input[name="y2"]').val()-$('input[name="y1"]').val();

		 var canvas=$('<canvas width="'+width+'" height="'+height+'"></canvas>')[0],
		 ctx=canvas.getContext('2d');

		 ctx.drawImage(preview,x,y,width*scale,height*scale,0,0,width,height);//重绘
		 
		 var data=canvas.toDataURL();

		 // dataURL 的格式为 “data:image/png;base64,****”,逗号之前都是一些说明性的文字，我们只需要逗号之后的就行了
		 data=data.split(',')[1];
		 data=window.atob(data);
		 var ia = new Uint8Array(data.length);
		 for (var i = 0; i < data.length; i++) {
		     ia[i] = data.charCodeAt(i);
		 };

		 // canvas.toDataURL 返回的默认格式就是 image/png
		 var blob=new Blob([ia], {type:"image/png"});
		 
		 var fd = new FormData();
		 var memno = ${memnop};
		 fd.append('data', blob);
		 fd.append('memno', memno);
		 fd.append('action',"updatePhoto");
		 $.ajax({
		     type: 'POST',
		     url: "<%=request.getContextPath()%>/mem/memjson.do",
		     data: fd,
		     processData: false,
		     contentType: false,
		 }).done(function(data) {
			 window.location.reload();
		 });

 }
</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPHR1TAv4Orcgzrr6qKXcK62sBDLYx0Xg"></script>
</body>
</html>