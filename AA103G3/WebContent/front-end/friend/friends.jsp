<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.memach.model.*"%>
<%@ page import="com.friend.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="friSvc" scope="page" class="com.friend.model.FriendService" />
<jsp:useBean id="memAchSvc" scope="page" class="com.memach.model.MemAchService" />

	
<%@ include file="/front-end/friend/personalPage.file" %>

<%
List<FriendVO> list = friSvc.getAllByMemFriend(memVOO.getMemno(), "1");
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>JBZ呷百利-個人專頁</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/jquery_ui.css" />
<script src="<%=request.getContextPath()%>/front-end/js/jquery_1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery_ui_1.10.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/cal.js"></script>
<link href="<%=request.getContextPath()%>/front-end/css/cal.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/personalleft.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/front-end/js/hightstock.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/highcharts_export.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/sweetalert2.css">
<script src="<%=request.getContextPath()%>/front-end/js/sweetalert.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/personalPage.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/imgareaselect-default.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/js/jquery.imgareaselect.pack.js"></script>
<link href="<%=request.getContextPath()%>/front-end/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet">
<!-- 上傳圖片用JS -->

</head>
<body onload="connect();" onunload="disconnect();">
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">			
			<a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/homePage.jsp">
				<img src="<%=request.getContextPath()%>/front-end/image/logowhite.png">
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" oninput="search(this);" class="form-control searchbar" placeholder="Search">
					<div class="friSearch" style="z-index:10;position: fixed;min-width: 200px;padding-left:0;">
					<ul  class="friFind" style="list-style:none;padding-left:0;margin-bottom:0px;">
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
				<a href="<%=request.getContextPath()%>/front-end/personalPage.jsp?memno=${memnoThisPage}">
					<img src="<%=request.getContextPath()%>/personal/personal.do?memno=${memnoThisPage}">
				</a>	
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
			<div class="col-xs-12 col-sm-12 friendmain">
				<h4
					style="border-bottom: solid 1px #eee; padding-bottom: 10px; color: #0E9EA3; font-weight: bold; padding-left: 30px;">好友</h4>
				<ul style="padding-left: 15px;">
					<c:forEach var="friendVO" items="${list}">
						<li class="friend">
							<a
							href="<%=request.getContextPath()%>/front-end/personalPage.jsp?memno=${friendVO.frino}">
								<img
								src="<%=request.getContextPath()%>/personal/personal.do?memno=${friendVO.frino}">
								<h3>${memSvc.getOneMem(friendVO.frino).name}</h3>
							</a>
							<c:if test="${isSelf}">
							<div class="friCancle dropdown">
								<select name="one" onchange="checkCancle(this,${memnop},${friendVO.frino});" class="dropdown-select">
									<option value="1">好友</option>
									<option value="2">取消好友</option>
								</select>
							</div>
							</c:if>
						</li>
					</c:forEach>

				</ul>
			</div>
		</div>
	</div>
<!-- 上傳照片modal -->
<div id="photoModal" class="modal">
  <div class="modal-content" style="width:300px;">
    <div class="modal-header">
      <span class="closePhoto">×</span>
    </div>
    <div class="modal-body uploadBody">
		<div >
					<div style="position:relative;padding:10px;">
						<span>選擇上傳圖片</span>
						<input type="file" id="photo" name="photo" style="margin-top: 5px;opacity:0;z-index:5;position:absolute;top:0;" />						
					</div>
					<div class="uploadimage">
						<img src="" id="preview" style="width:250px;">
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
	
	function checkCancle(event,frino,memno){
		var cancle = $(event).parent().parent();
		if(event.value==2){
			swal({
				   title: '確定要刪除嗎?',
				   type: 'warning',
				   showCancelButton: true,
				   confirmButtonColor: '#3085d6',
				   cancelButtonColor: '#d33',
				   cancelButtonText:'取消',
				   confirmButtonText: '刪除'
				 }).then(function() {
					 $.ajax({
						 type:"GET",
						 url:"<%=request.getContextPath()%>/friend/friend.do",
						 data:{memno:memno,frino:frino,action:"delete_for_friend"},
						 dataType:"text",
						 success:function (data){
							 cancle.hide();
							 swal("刪除成功!!");
							 
					     },
			           error:function(){alert("error")}
			       });	
				 })
		}
		
	}
	
// 	socket連線
		var MyPoint = "/AddNotify/${memVOO.memno}/${memVO.memno}";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		
		function connect() {
			// 建立 websocket 物件
			webSocket = new WebSocket(endPointURL);
			
			webSocket.onopen = function(event) {
				
			};

			webSocket.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data);
// 				document.getElementById("getMessage").innerHTML=jsonObj.userName+ jsonObj.message ;
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
		        webSocket.send(JSON.stringify(jsonObj));
		}
		
		function disconnect () {
			webSocket.close();
		}
		$(function(){
			$(".sendRequest").click(function(){
				$(this).css("display","none");
				$(".sended").css("display","");
			})
		})
		
	</script>
<!-- 上傳照片裁切	 -->
	<script type="text/javascript">

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

		 $(function() {
				if ($(".friendmain").height() < $(".personal").height()) {
					$(".friendmain").height($(".personal").height() - 40);
				}
			})	
		
</script>
</body>
</html>