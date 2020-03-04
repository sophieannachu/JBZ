<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.groupinfo.model.*"%>
<%@ page import="com.groupList.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*"%>
<script src="<%=request.getContextPath()%>/front-end/js/jquery.cookie.js"></script>
<%
	
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
	
	
%>
<%
	GroupInfoService groupinfoSv = new GroupInfoService();
	List<GroupInfoVO> list = groupinfoSv.getAll();
	
	GroupListService groupListSv = new GroupListService();
	MemVO memVO2 = (MemVO)session.getAttribute("memVO");

	List<GroupListVO> list2 = groupListSv.getOneMyGroupList(memVO2.getMemno());
	
	MemService memSv = new MemService();
	List<MemVO> memList = memSv.getAllMem();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("list2", list2);
	pageContext.setAttribute("memList", memList);
	
%>

<script>
		
		var chatMain =$("#chatMain");
		if($("#chatMain>img").length == 0){
// 				location.reload();
		//		$("#chatMain").hide();
			chatMain.css("display","none");
		}else{
// 			location.reload();
			chatMain.css("display","block");
// 			chatMain.show();
		}
		
		
		var listSt = "";
		<c:forEach var="grouplistVO" items="${list2}">
			listSt += ${grouplistVO.group_no} + ",";
		</c:forEach>
		
		var statusOutput = document.getElementById("statusOutput");
		var webSocket;

		function connect(group_no,group_name) {
			var MyPoint = "/MyEchoServer2/"+group_no;
			var host = window.location.host;
			var path = window.location.pathname;
			var webCtx = path.substring(0, path.indexOf('/', 1));
			var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
			
			
			// 建立 websocket 物件
			webSocket = new WebSocket(endPointURL);
			
			webSocket.onopen = function(event) {
				updateStatus("WebSocket 成功連線");
				document.getElementById('sendMessage').disabled = false;
				document.getElementById('connect').disabled = true;
				document.getElementById('disconnect').disabled = false;
			};
			
			webSocket.onmessage = function(event) {
		        var jsonObj = JSON.parse(event.data);
		        var message = jsonObj.message + "\r\n";
		        var group_no = jsonObj.group_no;
		       
//			        var memno = jsonObj.memno ;
				var listSt1 = listSt.split(",");

				for(var i = 0 ;i<(listSt1.length)-1;i++){
// 	 				console.log("A"+group_no);
// 	 				console.log("listSt1"+listSt1[i]);
					if(group_no == listSt1[i]){
						openChat(jsonObj.group_name,jsonObj.group_no);
		        		console.log(group_name+":"+group_no);
						var messagesArea = document.getElementById(jsonObj.messagesArea);
							console.log("AAAA"+messagesArea.scrollTop);
						var img1 = new Image();
						img1.src ="<%=request.getContextPath()%>/mem/ShowImg_memno?memno="+jsonObj.memno;
						img1.style.width = "20%";
						var pNode = document.createElement("p");
						messagesArea.appendChild(pNode);
						pNode.appendChild(img1);
						var t = document.createTextNode(jsonObj.memnoname+"::"+jsonObj.message);
						var brNode = document.createElement("br");
						pNode.appendChild(t);
						pNode.appendChild(brNode);
// 						var pNode = document.createElement("span");
// 						messagesArea.appendChild(pNode);
// 						var t = document.createTextNode("Hello World");
// 						pNode.appendChild(t);
// 						console.log(pNode);
						
						$('#messagesArea'+group_no).animate({
							  scrollTop: $('#messagesArea'+group_no)[0].scrollHeight
						}, 800);
						
//        					messagesArea.scrollTop = messagesArea.scrollHeight;  


				        var message = jsonObj.memno + ": " + jsonObj.message + "\r\n";
				        messagesArea.value = messagesArea.value + message;
// 				        var cookieArea = messagesArea.value + message;
// 				        var date = new Date();
// 		                date.setTime(date.getTime() + (5 * 24 * 60 * 60 * 1000));
		              
// 						$.cookie("\"jsonObj.messagesArea\"", cookieArea, { path:'/', expires: date });
		                	
		                
// 				        console.log("asdas=="+$.cookie('welkin_cookie'));	              	
		                 
//		 			        messagesArea.value = messagesArea.value;
				        messagesArea.scrollTop = messagesArea.scrollHeight;
						
					}
				
				}
		        if(message != ""){
		        	
// 		        		openChat(jsonObj.group_name,jsonObj.group_no);
// 		        		console.log(group_name+":"+group_no);
// 						var messagesArea = document.getElementById(jsonObj.messagesArea);
// 							console.log(messagesArea);
// 						var img1 = new Image();
<%-- 						img1.src ="<%=request.getContextPath()%>/mem/ShowImg_memno?memno="+jsonObj.memno; --%>
// 						img1.style.width = "20%";
// 						var pNode = document.createElement("p");
// 						messagesArea.appendChild(pNode);
// 						pNode.appendChild(img1);
// 						var t = document.createTextNode(jsonObj.memnoname+"::"+jsonObj.message);
// 						var brNode = document.createElement("br");
// 						pNode.appendChild(t);
// 						pNode.appendChild(brNode);
// // 						var pNode = document.createElement("span");
// // 						messagesArea.appendChild(pNode);
// // 						var t = document.createTextNode("Hello World");
// // 						pNode.appendChild(t);
// // 						console.log(pNode);
// 				        var message = jsonObj.memno + ": " + jsonObj.message + "\r\n";
// 				        messagesArea.value = messagesArea.value + message;
// 				        var cookieArea = messagesArea.value + message;
// 				        var date = new Date();
// 		                date.setTime(date.getTime() + (5 * 24 * 60 * 60 * 1000));
		              
// 						$.cookie("\"jsonObj.messagesArea\"", cookieArea, { path:'/', expires: date });
		                	
		                
// // 				        console.log("asdas=="+$.cookie('welkin_cookie'));	              	
		                 
// //		 			        messagesArea.value = messagesArea.value;
// 				        messagesArea.scrollTop = messagesArea.scrollHeight;			        	
		        }
			};
			
			
			
			webSocket.onclose = function(event) {
				updateStatus("WebSocket 已離線");
			};
		}
		function updateStatus(newStatus) {
			statusOutput.innerHTML = newStatus;
		}
		function disconnect () {
			webSocket.close();
			document.getElementById('sendMessage').disabled = true;
			document.getElementById('connect').disabled = false;
			document.getElementById('disconnect').disabled = true;
		}
		

//			var memnoSession = ${sessionScope.memno};
//			var memno = memnoSession;
		//會員姓名
		var memnoname="${memVO.name}" ;
		var memno="${memVO.memno}" ;
		function sendMessage(ev,group_name,group_no) {
		    var messagesArea = ev.id.split("_");
		    console.log(messagesArea[1]);
		    var inputMessage = document.getElementById(ev.id);
		    console.log(inputMessage);
		    var message = inputMessage.value.trim();
		    if (message === ""){
		        alert ("訊息請勿空白!");
		        inputMessage.focus();	
		    }else{
		        var jsonObj = { "group_name":group_name, "group_no":group_no,"messagesArea":messagesArea[1],"memnoname":memnoname,"memno":memno,"message" : message};
		        console.log("fff"+jsonObj);
		        webSocket.send(JSON.stringify(jsonObj));
		        inputMessage.value = "";
		        inputMessage.focus();
		    }
		}
		
		var show ="";
		var group_total ="";
		var left = 72;

		var chatContent= document.getElementsByClassName("chatContent");
		
		console.log("***************************");
		
// 		if($.cookie('cookieArea') !="" && $.cookie('group_no')!=null){
// 			console.log("這是"+$.cookie('cookieArea'));
// 			var group_no = $.cookie('group_no');
// 			var group_name = $.cookie('group_name');
// 			var group_noSplit = group_no.split(",");
// 			var group_nameSplit = group_name.split(",");
// 			console.log(group_noSplit.length);
// 			for(var i = 0 ;i<(group_noSplit.length)-1;i++){
// // 				console.log(group_noSplit[i]);
				
// 				openChat(group_nameSplit[i],group_noSplit[i]);
				
// 			}
				
// 		}
// 		for(var i = 0 ;i<Object.keys($.cookie()).length;i++){
// 			alert("r");
// 			var messagesArea;
// 			console.log(Object.keys($.cookie())[i]);
// 			var messagesArea = document.getElementById(Object.keys($.cookie())[i]).id;
// 			console.log("dddd"+messagesArea);
// //				messagesArea.value =$.cookie(messagesArea);
	
		
// 		}
		
		function openChat(group_name,group_no){
				console.log(left);
			
				var imgSrc = "<%=request.getContextPath()%>/group_info/ShowGroupInfo?group_no="+group_no;
				if($("div[id="+group_no+"]").length<=0){
					if($(".chatContent").length == 0){
						left = 72;
					}
// 					 show +=group_no+",";
// 					 group_total +=group_name+",";
// 					 console.log(group_total);
// 					 console.log("show"+show);
// 					 var date = new Date();
// 		             date.setTime(date.getTime() + (5 * 24 * 60 * 60 * 1000));
// 		             if(show != ""){
// 					 	$.cookie('group_no', show, { path:'/', expires: date });
// 					 	$.cookie('group_name', group_total, { path:'/', expires: date });
// 		             }
					
// 		            <div id="XX" style="
//     width: 12%;
//     position: absolute;
//     left: 81%;
//     display: inline-block;
//     text-align: center;
//     background-color: gainsboro;
// ">X</div>
		             

					var chatContent = 	"<div id='"+group_no+"'class='chatContent' style='left:"+ left +"%;display: block;'><span style='display: inherit;margin-left: 16%;'>"
										+group_name+"聊天室!!<img style='margin-top: -5%;width: 15%;position: absolute;display: initial;' src='<%=request.getContextPath()%>/front-end/image/removeChat.png'></span>"+"<div>"+"<div style='width:100%;height: 100%;background-color: white;margin-top: 10px;margin-bottom: 0%;overflow:auto;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);' id='messagesArea"+group_no+"' class='panel message-area'"+
										"readonly></div></div>"+"<div>"+
										"<input id='message"+group_no+"_messagesArea"+group_no+"' class='text-field' type='text' placeholder='訊息' onkeydown=\"if (event.keyCode == 13) sendMessage(this,'"+group_name+"',"+group_no+");\"/></div></div>";
						    $("#chatContentMen").append(chatContent);
 				    console.log(chatContent);
						
				}
			left =left-16;
			console.log(left);
			
// 			var d = document.getElementById("messagesArea"+group_no);
			
// 			var d = $('#messagesArea'+group_no);

// 			console.log("d.scrollHeight:"+d.scrollHeight);
// 			 d.animate({ scrollTop: d.scrollHeight}, 1000);
// 			d.scrollTop(d.prop("scrollHeight"));
		}
		
		
		<c:forEach var="groupinfoVO" items="${list}">
			$(function(){
				connect("${groupinfoVO.group_no}","${groupinfoVO.group_name}");
			});
		</c:forEach>
		
</script>