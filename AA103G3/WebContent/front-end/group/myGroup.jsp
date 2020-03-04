<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groupinfo.model.*"%>
<%@ page import="com.groupList.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">


<jsp:useBean id="groupInfoSvc" scope="page" class="com.groupinfo.model.GroupInfoService" />
	
	
<c:forEach var="groupListVO" items="${groupVOlist}">
	<c:forEach var="groupinfoVO" items="${groupInfoSvc.getAll()}">
		<c:if test="${groupListVO.group_no == groupinfoVO.group_no}">
			<c:if test="${memVO.memno == groupListVO.memno}">
			<script>
				$(document).ready(function(){
					var groupId =$("#outGp_${groupListVO.group_no}");
					groupId.hide();
				});
			</script>
			</c:if>	
		</c:if>	
	
	
	<c:if test="${groupListVO.group_no == groupinfoVO.group_no}">
	<div class="wrapper1 cards" style="margin-top:0;" id="outGp_${groupinfoVO.group_no}">
	  <div class="container2">
	    <div class="card1">
	      <div class="front">
	      <img style="width:100%; height:100%; margin-bottom: 20px;" src="<%=request.getContextPath()%>/group_info/ShowGroupInfo?group_no=${groupListVO.group_no} "
			                 alt="">
	      </div>
	      <div class="back">
	        <div class="content">
	          <h3 class="cardTitle">${groupinfoVO.group_name}</h3>
	          <p>活動時間:2016-07-10 09:00:00.0</p>
	          <p>活動人數:${groupinfoVO.group_count}</p>
	        </div>
	          <button class="OutGp"  onclick="outGroup(${groupinfoVO.group_no},'${memVO.memno}','${groupinfoVO.group_name}')">我要退出揪團</button>
	      </div>
	    </div>
	  </div>
	 
	</div>
	</c:if>
	</c:forEach>
 </c:forEach>
</div>





<style>
@import url(http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700);
.gpName{
    height: 20%;
    font-size: xx-large;
	outline: solid;
}
.gpName text{
    height: 20%;
    font-size: xx-large;
	outline: solid;
}
.OutGp{
	position: absolute;
    z-index: 33;
    margin-top: 72px;
    background-color: #ad4747;
    color: #f5f5f5;
}
body {
  font-family: 'Roboto Slab', serif;
  font-weight: 300;
  font-size: 16px;
  line-height: 1.4em;
  color: #333;
  background: #eee;
}

input,
button {
  font-family: 'Roboto Slab', serif;
  font-weight: 300;
  font-size: 16px;
  border: 0;
  padding: 3px 5px;
  border-radius: 3px;
}

/* h1 { */
/*   margin: 0.5em 0 1em 0; */
/*   font-size: 1.8em; */
/*   font-weight: 700; */
/*   color: #096AA3; */
/* } */

h2, h3 {
  font-weight: bold;
}

p {
  margin-bottom: 1em;
}

.animation {
  -webkit-transition: all 0.3s ease;
  -moz-transition: all 0.3s ease;
  -ms-transition: all 0.3s ease;
  -o-transition: all 0.3s ease;
  transition: all 0.3s ease;
}

.wrapper1 {
  width: 80%;
  padding: 0%;
  margin: 0 auto;
}

.wrapper.cards {
  background: 0;
  width: 88%;
  padding: 20px 0 0 0;
}

.container2 {
  position: relative;
  float: left;
  width: 48%;
  height: 260px;
  margin: 10px 0 10px 4%;
  
  /* Set the depth of the elements */
  -webkit-perspective: 800px;
  -moz-perspective: 800px;
  -o-perspective: 800px;
  perspective: 800px;
}

.container2:first-child {
  margin-left: 0;
}

.card1 {
  width: 100%;
  height: 100%;
  position: absolute;
  cursor: pointer;
  
  /* Set the transition effects */
  -webkit-transition: -webkit-transform 0.4s;
  -moz-transition: -moz-transform 0.4s;
  -o-transition: -o-transform 0.4s;
  transition: transform 0.4s;
  -webkit-transform-style: preserve-3d;
  -moz-transform-style: preserve-3d;
  -o-transform-style: preserve-3d;
  transform-style: preserve-3d;
}

.card1.flipped {
  -webkit-transform: rotateY( 180deg );
  -moz-transform: rotateY( 180deg );
  -o-transform: rotateY( 180deg );
  transform: rotateY( 180deg );
}

.card1 .front,
.card1 .back {
  display: block;
  height: 100%;
  width: 100%;
  line-height: 260px;
  color: white;
  text-align: center;
  font-size: 4em;
  position: absolute;
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -o-backface-visibility: hidden;
  backface-visibility: hidden;
  
  box-shadow: 3px 5px 20px 2px rgba(0, 0, 0, 0.25);
}

.card1 .back {
  width: 94%;
  padding-left: 3%;
  padding-right: 3%;
  font-size: 16px;
  text-align: left;
  line-height: 25px;
}

.formItem:first-child {
  margin-top: 20px;
}

.card1 .back label {
  display: inline-block;
  width: 70px;
  text-align: left;
}

.card1 .front {
  background: #096AA3;
}

.card1 .back {
  background: #03446A;
  -webkit-transform: rotateY( 180deg );
  -moz-transform: rotateY( 180deg );
  -o-transform: rotateY( 180deg );
  transform: rotateY( 180deg );
}

.container2:first-child .card1 .front {
  background: #ffffff;
}

.container2:first-child .card1 .back {
  background: #939393;
}

.cardTitle {
  font-size: 1.4em;
  line-height: 1.2em;
  margin: 0;
}

.content {
  padding: 4%;
  font-weight: 100;
  text-align: left;
}

button.btnSend {
  display: inline-block;
  min-width: 100px;
  padding: 3px 5px;
  margin-top: 10px;
  font-weight: bold;
  text-transform: uppercase;
  text-align: center;
  color: #03446A;
  background: #fff;
  border: 0;
  border-radius: 0;
}
</style>

<script>


$(document).ready(function(){
	$('.card1').click(function(){
		  $(this).toggleClass('flipped');
		});

});
function outGroup(group_no,memno,group_name){

	$(document).ready(function(){
		event.stopPropagation();
		var groupId =$("#outGp_"+group_no);
		var groupIdMenu =$("#"+group_no);
		$(".OutGp").click(function(event){
			$.ajax({
			    url: '<%=request.getContextPath()%>/group_info/GroupinfoServlet.do',
			    type: 'POST',
			    data: {"group_no": group_no,"memno":memno,"action":'outGroup',"requestURL":"<%=request.getServletPath()%>"},
			    success: function(response) {
			    	groupIdMenu.remove();
			    	$.each(response, function(key, value) {
			    		
			    		// Iterate over the JSON object.
			    		console.log(key+":"+value);
			    		groupId.hide();
			    		groupIdMenu.hide();
			    		location.reload();
			    		//退出糾團後右側聊天收起
// 			    		checkGroupOut();
			        });
			    	
//	 		        $('#msg_user_name').html(response);
//	 		        $('#msg_user_name').fadeIn();
			 
			    },
			    error: function(xhr) {
			      alert('Ajax request 發生錯誤');
			    }
			    
			  });
		 });
	});
}
</script>