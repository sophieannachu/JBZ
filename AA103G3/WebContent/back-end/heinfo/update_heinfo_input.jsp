<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.heinfo.model.*"%>
<%@page import="java.util.*" %>
<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
<%
	HeinfoVO heinfoVO = (HeinfoVO) request.getAttribute("heinfoVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>資料修改 - update_heinfo_input.jsp</title></head>


<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/heinfo/js/jquery-latest.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/heinfo/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/heinfo/css/datepicker.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/heinfo/js/loader.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/heinfo/css/bootstrap.css">
<script src="<%=request.getContextPath()%>/back-end/heinfo/js/previewImage.js"></script>
<script src="<%=request.getContextPath()%>/back-end/heinfo/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {
	$('#datePicker')
    .datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
		todayHighlight: true
    });
	
  });
</script>
<style>
	@import url(http://fonts.googleapis.com/css?family=Montserrat:400,700);

html{    
  background-size: cover;
/*   height:100%; */
}

#feedback-page{
	text-align:center;
}

#form-main{
	width:100%;
	float:left;
	padding-top:0px;
}

#form-div {
	background-color:rgba(72,72,72,0.4);
	padding-left:35px;
	padding-right:35px;
	padding-top:35px;
	padding-bottom:50px;
	width: 450px;
/* 	float: left; */
	left: 53%;
	position: relative;
 	margin-top: 37px;
	margin-left: -260px;
  	-moz-border-radius: 7px;
  	-webkit-border-radius: 7px;
}

.feedback-input {
	color:#3c3c3c;
	font-family: Helvetica, Arial, sans-serif;
  	font-weight:500;
	font-size: 18px;
	border-radius: 0;
	line-height: 22px;
	background-color: #fbfbfb;
	padding: 13px 13px 13px 54px;
	margin-bottom: 10px;
	width:100%;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-ms-box-sizing: border-box;
	box-sizing: border-box;
  border: 3px solid rgba(0,0,0,0);
}

.feedback-input:focus{
	background: #fff;
	box-shadow: 0;
	border: 3px solid #3498db;
	color: #3498db;
	outline: none;
  padding: 13px 13px 13px 54px;
}

.focused{
	color:#30aed6;
	border:#30aed6 solid 3px;
}

/* Icons ---------------------------------- */
#name{
	background-image: url(http://rexkirby.com/kirbyandson/images/name.svg);
	background-size: 30px 30px;
	background-position: 11px 8px;
	background-repeat: no-repeat;
}

#name:focus{
	background-image: url(http://rexkirby.com/kirbyandson/images/name.svg);
	background-size: 30px 30px;
	background-position: 8px 5px;
  background-position: 11px 8px;
	background-repeat: no-repeat;
}

#email{
	background-image: url(http://rexkirby.com/kirbyandson/images/email.svg);
	background-size: 30px 30px;
	background-position: 11px 8px;
	background-repeat: no-repeat;
}

#email:focus{
	background-image: url(http://rexkirby.com/kirbyandson/images/email.svg);
	background-size: 30px 30px;
  background-position: 11px 8px;
	background-repeat: no-repeat;
}

#comment{
	background-image: url(http://rexkirby.com/kirbyandson/images/comment.svg);
	background-size: 30px 30px;
	background-position: 11px 8px;
	background-repeat: no-repeat;
}

textarea {
    width: 100%;
    height: 150px;
    line-height: 150%;
    resize:vertical;
}

input:hover, textarea:hover,
input:focus, textarea:focus {
	background-color:white;
}

#button-blue{
	font-family: 'Montserrat', Arial, Helvetica, sans-serif;
	float:left;
	width: 100%;
	border: #fbfbfb solid 4px;
	cursor:pointer;
	background-color: #3498db;
	color:white;
	font-size:24px;
	padding-top:22px;
	padding-bottom:22px;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
  margin-top:-4px;
  font-weight:700;
}

#button-blue:hover{
	background-color: rgba(0,0,0,0);
	color: #0493bd;
}
	
.submit:hover {
	color: #3498db;
}
	
.ease {
	width: 0px;
	height: 74px;
	background-color: #fbfbfb;
	-webkit-transition: .3s ease;
	-moz-transition: .3s ease;
	-o-transition: .3s ease;
	-ms-transition: .3s ease;
	transition: .3s ease;
}

.submit:hover .ease{
  width:100%;
  background-color:white;
}

@media only screen and (max-width: 580px) {
	#form-div{
		left: 3%;
		margin-right: 3%;
		width: 88%;
		margin-left: 0;
		padding-left: 3%;
		padding-right: 3%;
	}
}

</style>
<body>
<div>
<div id="form-main">
	<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
	</c:if>
  <div id="form-div">
    <form class="form" id="form1" METHOD="post" enctype="multipart/form-data" ACTION="<%=request.getContextPath()%>/heinfo/heinfo.do" >
      
      <p class="name">
        <input name="heinfono" value="<%=heinfoVO.getHeinfono()%>" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="Name" id="name" />
      </p>
      
      <p class="email">
      	<input  placeholder="Email" type="TEXT" name="heinfoname" class="validate[required,custom[email]] feedback-input"  size="45" value="<%=heinfoVO.getHeinfoname()%>" />
      </p>
      
      <p class="text">
<%--       <textarea  class="form-control" rows="7" id="heinfodetail" name="heinfodetail" ><%=heinfoVO.getHeinfodetail()%></textarea> --%>
        <textarea name="heinfodetail" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="Comment"><%=heinfoVO.getHeinfodetail()%></textarea>
      </p>
      
      <p>
      	<div class="date">
		  	<div class="input-group input-append date" id="datePicker">
		     	<input type="text" class="form-control" name="heinfodate" value="<%= (heinfoVO==null)? date_SQL : heinfoVO.getHeinfodate()%>" style="width:100%;"/>
			 	<span class="input-group-addon add-on">
			    <span class="glyphicon glyphicon-calendar">
			    </span>
			 	</span>
			  </div>
	 	</div>
      </p>
      <p>
      	<div>
<%-- 			<img src="<%=request.getContextPath()%>/heinfo/Showimg?heinfono=${heinfoVO.heinfono}" style="width:300px;height:300px;"> --%>
			<input id="file" type="file"  class="file" name="heinfophoto" >
		</div>
      </p>
      <p>
      	<input type="hidden" name="action" value="update">
		<input type="hidden" name="heinfono" value="<%=heinfoVO.getHeinfono()%>">
      </p>
      <div class="submit">
      	<input type="submit" value="SEND" id="button-blue" value="送出修改">
        <div class="ease"></div>
      </div>
    </form>
  </div>
  
  

<div class="container" style="border:1px; border-style:dashed; width:500px; border-color:#aaaaee;" >
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<!-- <div class="row"> -->
<%-- <FORM METHOD="post" enctype="multipart/form-data" ACTION="<%=request.getContextPath()%>/heinfo/heinfo.do" > --%>
<!-- <table class="table-striped table-hover"> -->
	
<!-- 	<tr> -->
<!-- 		<td>編號:</td> -->
<%-- 		<td><%=heinfoVO.getHeinfono()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>資訊名稱:</td> -->
<%-- 		<td><input type="TEXT" name="heinfoname" class="form-control" size="45" value="<%=heinfoVO.getHeinfoname()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>資訊詳細:</td> -->
<%-- 		<td><textarea class="form-control" rows="7" id="heinfodetail" name="heinfodetail" ><%=heinfoVO.getHeinfodetail()%></textarea></td> --%>
		
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>資訊日期:</td> -->
<!-- 		<td> <div class="date"> -->
<!-- 			  	<div class="input-group input-append date" id="datePicker"> -->
<%-- 			     	<input type="text" class="form-control" name="heinfodate" value="<%= (heinfoVO==null)? date_SQL : heinfoVO.getHeinfodate()%>"/> --%>
<!-- 				 	<span class="input-group-addon add-on"> -->
<!-- 				    <span class="glyphicon glyphicon-calendar"> -->
<!-- 				    </span> -->
<!-- 				 	</span> -->
<!-- 				  </div> -->
<!-- 			 </div> -->
<!-- 		</td> -->
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td>圖片:</td> -->
		
<!-- 		<td> -->
<!-- 			<div> -->
<%-- 				<img src="Showimg?heinfono=${heinfoVO.heinfono}" style="width:150;"> --%>
				
<!-- 				<input id="file" type="file"  class="file" name="heinfophoto" > -->
<!-- 			</div> -->
<!-- 		</td> -->
<!-- 	</tr> -->

	
	

<!-- </table> -->

<!-- <br> -->
<!-- <input type="hidden" name="action" value="update"> -->
<%-- <input type="hidden" name="heinfono" value="<%=heinfoVO.getHeinfono()%>"> --%>
<!-- <input type="submit" value="送出修改"></FORM> -->
<!-- </div> -->
</div>
</body>
</html>
