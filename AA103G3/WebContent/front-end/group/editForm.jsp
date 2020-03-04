<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style>
	@import url(http://fonts.googleapis.com/css?family=Montserrat:400,700);


form { max-width:420px; margin:50px auto; }

.feedback-input {
  color:#370d0d;
  font-family: Helvetica, Arial, sans-serif;
  font-weight:500;
  font-size: 18px;
  border-radius: 5px;
  line-height: 22px;
  background-color: transparent;
  border:2px solid #CC6666;
  transition: all 0.3s;
  padding: 13px;
  margin-bottom: 15px;
  width:100%;
  box-sizing: border-box;
  outline:0;
}

.feedback-input:focus { border:2px solid #CC4949; }

textarea {
  height: 150px;
  line-height: 150%;
  resize:vertical;
}

[type="submit"] {
  font-family: 'Montserrat', Arial, Helvetica, sans-serif;
  width: 100%;
  background:#CC6666;
  border-radius:5px;
  border:0;
  cursor:pointer;
  color:white;
  font-size:24px;
  padding-top:10px;
  padding-bottom:10px;
  transition: all 0.3s;
  margin-top:-4px;
  font-weight:700;
}
[type="submit"]:hover { background:#CC4949; }

</style>

</head>

<body>
	<c:forEach var="groupInfoVO" items="${groupInfoVOlist}" >
		 <img  class="preview" style="width:63%; margin-left: 20%;margin-bottom: 20px;" src="<%=request.getContextPath()%>/group_info/ShowGroupInfo?group_no=${groupInfoVO.group_no}" alt="">
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do"  enctype="multipart/form-data">      
			<input name="action" type="hidden" value="editForm2" />
			<input name="group_no" type="hidden" value="${groupInfoVO.group_no}" />
			<input  id="upload" type="file" name="fname"   class="form-control"></input>
			<input name="requestURL" type="hidden" value="<%=request.getServletPath()%>" />
			  揪團名稱:<input name="group_name" type="text" value="${groupInfoVO.group_name}" class="feedback-input" placeholder="Name" />   
			  活動時間:<input name="group_time" type="text" value="${groupInfoVO.group_time}" class="feedback-input" placeholder="Email" />
			  活動細節:<textarea name="group_detail"  class="feedback-input" placeholder="Comment">${groupInfoVO.group_detail}</textarea>
			  <input class="btn btn-default" type="submit" value="SUBMIT"/>
		</form>
	</c:forEach>

<!-- 	<from action="" method="POST"> -->
<!-- 		sad:<input type="text">dffd</input> -->
<!-- 		<input type="button" class="btn btn-default" data-dismiss="modal">送出</input> -->
		
<!-- 	</from> -->
</body>
<script>
var file;
var fileReader;
var photo;
var preview;
// var hide_img;
function doFirst(){
	
	photo = document.getElementById('upload');
	photo.onchange = fileChange;
	preview = document.getElementsByClassName('preview')[0];
// 	hide_img= document.getElementsByClassName('hide_img')[0];
	
}
function fileChange(e){

	file=e.srcElement.files[0];

	fileReader = new FileReader();
	fileReader.readAsDataURL(file);
	
	fileReader.onload = function(){
		preview.src=fileReader.result;
// 		hide_img.src=fileReader.result;
	};
}
</script>
</html>