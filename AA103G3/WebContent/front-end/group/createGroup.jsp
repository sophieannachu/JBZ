<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="com.groupinfo.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%
	MemVO memVO=(MemVO)request.getAttribute("memVO");
%>
<% String number = ""+Math.random() * 10;
		System.out.print("dsd"+number);
		session.setAttribute("insertPic", number); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" /> -->
<script src="<%=request.getContextPath()%>/front-end/js/timepicker.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/loader.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/timepicker1.js"></script>
<link href="<%=request.getContextPath()%>/front-end/css/timepicker.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front-end/css/datepicker.min.css" rel="stylesheet">
<title>Insert title here</title>
<style>
.preview{
	width:150px;

}
#pac-input {
  background-color: #fff;
  font-family: Roboto;
  font-size: 21px;
  font-weight: 300;
  margin-left: -118px;
  padding: 0 11px 0 13px;
  text-overflow: ellipsis;
  width: 502px;
  z-index:5 !important; 
  height:42px;
}
#map{
	width: 100% !important;
}
</style>
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
window.addEventListener('load',doFirst,false);
</script>
<script>
// 	$(document).ready(function() {
// 		  $('#datePicker').datetimepicker();
// // 		$("#datePicker").datepicker({
// // 			format : 'yyyy-mm-dd',
// // 			autoclose : true,
// // 			todayHighlight : true
// // 		});
// 	});
</script>
</head>
<%

	GroupInfoService groupinfoSv = new GroupInfoService();
	List<GroupInfoVO> list = groupinfoSv.getAll();
	pageContext.setAttribute("list", list);

%>
<%
	
	MemVO memVO2 = (MemVO)session.getAttribute("memVO");
		System.out.print("--------------------"+"\n");
	System.out.print("ff"+memVO2.getName()+"\n");
	pageContext.setAttribute("memVO", memVO2);
	
	
%>
<body>
<table class="table table-striped table-hover" border="1">
<div class="container" >
			<div class="row">
				<div style="margin-left: 10%;margin-left: 16%;">
				<div id="gpcheck" style="position: absolute;left: 81%;top: 42%;display:none"><i class="fa fa-check" aria-hidden="true"></i></div>
				<div class="col-xs-12 col-sm-6" >

					<form action="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do" enctype="multipart/form-data" method="post">
						
						
						<div class="form-group">
							<label for="name">揪團名稱:</label>
							<input type="text"  name="groupName"  class="form-control"></input>
						</div>
						
						<div class="form-group">
							<label for="name">人數上限:</label>
							<input type="number"   value="1"  min="1" name="groupCount"  class="form-control"></input>
						</div>
						<div class="form-group">
							<label for="name">活動時間:</label>
								<input  type="text" id="datetimepicker1" name="groupTime"  class="form-control" readonly
						      value="" >
						</div>
						<div class="form-group">
							<label for="name">揪團圖片::</label>
							<input  id="upload" type="file" name="fname"   class="form-control"></input>
						</div>
						<div><img class="preview" src=""></div>
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<div class="form-group">
							<label for="addr">揪團地址</label>
							<input type="hidden"  id="addr" class="form-control" onkeypress="test();></input>
							<input type="text" name="groupLoc" id="addr" value=""><br> 
							<input id="pac-input" class="controls" type="text" value="" placeholder="Search Box" onkeypress="test();">
							<div id="map" style="width:500px;height:300px;"></div>
							<input type="hidden" name="group_long" id="group_long" value="">
							<input type="hidden" name="group_lati" id="group_lati" value="">
						</div>
						<input type="hidden" name="groupMemno" value="${memVO.memno}"><br>
						<input type="hidden" name="groupCheck" value="0"><br>
						<div class="form-group">
							<label for="msg">詳細資訊:</label>
							<textarea id="msg" rows="10" class="form-control">
							</textarea>
						</div>
							<input type="hidden" name="insertPic" value="<%=session.getAttribute("insertPic")%>"> 
							<input type="hidden" name="action" value="insert_One_group"> 
						<div class="col-sm-12 text-center">
							<input type="submit" value="新增項目" class="btn btn-primary">

						</div>

					</form>

				</div>
			</div>
		</div>



	
	</div>

<c:forEach var="groupinfoVO" items="${list}">
	
<!-- <tr> -->
<!-- 	<td> -->
<%-- 		<img src="<%=request.getContextPath()%>/group_info/ShowGroupInfo?group_no=${groupinfoVO.group_no}" --%>
			
<!-- 			style="width: 20%;"> -->
<%-- 		<br>${groupinfoVO.group_name}${groupinfoVO.group_no} --%>
<!-- 	</td> -->
<%-- 	<td>${groupinfoVO.memno}</td> --%>
<%-- 	<td>${groupinfoVO.group_loc}</td> --%>
<%-- 	<td>${groupinfoVO.group_detail}</td> --%>

<!-- 	<td> -->
<%-- 		<button onclick="deleteFd(${groupinfoVO.group_no});"> --%>
<!-- 			<img src="img/15.png"> -->
<!-- 		</button> -->
<!-- 	</td> -->
<!-- 	<td> -->
<!-- <button> -->
<%-- 	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_info/GroupinfoServlet.do"> --%>
<!-- 	     <input type="submit" value="編輯"> -->
<%-- 	     <input type="hidden" name="group_no" value="${groupinfoVO.group_no}"> --%>
<!-- 	     <input type="hidden" name="action"	value="getOne_For_Update"> -->
<%-- 	     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"> --%>
	     
<!-- 	</FORM> -->
<!-- </button> -->
<!-- </td> -->

<!-- </tr> -->



</c:forEach>
</table>
</body>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAYIZq0MhSXjdnegUsc0ical9rA8ULTxA8&libraries=places"></script>
<script>

//google地圖code
function initAutocomplete() {
	  var map = new google.maps.Map(document.getElementById('map'), {
	    center: {lat: -33.8688, lng: 151.2195},
	    zoom: 13,
	    mapTypeId: google.maps.MapTypeId.ROADMAP
	  });

	  // Create the search box and link it to the UI element.
	  var input = document.getElementById('pac-input');
	  var searchBox = new google.maps.places.SearchBox(input);
	  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
// 	  searchBox.style.width='900';
	  // Bias the SearchBox results towards current map's viewport.
	  map.addListener('bounds_changed', function() {
	    searchBox.setBounds(map.getBounds());
	  });

	  var markers = [];
	  // [START region_getplaces]
	  // Listen for the event fired when the user selects a prediction and retrieve
	  // more details for that place.
	  searchBox.addListener('places_changed', function() {
	    var places = searchBox.getPlaces();

	    if (places.length == 0) {
	      return;
	    }

	    // Clear out the old markers.
	    markers.forEach(function(marker) {
	      marker.setMap(null);
	    });
	    markers = [];

	    // For each place, get the icon, name and location.
	    var bounds = new google.maps.LatLngBounds();
	    places.forEach(function(place) {
	      var icon = {
	        url: place.icon,
	        size: new google.maps.Size(71, 71),
	        origin: new google.maps.Point(0, 0),
	        anchor: new google.maps.Point(17, 34),
	        scaledSize: new google.maps.Size(25, 25)
	      };

	      // Create a marker for each place.
	      markers.push(new google.maps.Marker({
	        map: map,
	        icon: icon,
	        title: place.name,
	        position: place.geometry.location
	      }));

	      if (place.geometry.viewport) {
	        // Only geocodes have viewport.
	        bounds.union(place.geometry.viewport);
	      } else {
	        bounds.extend(place.geometry.location);
	      }
	    });
	    map.fitBounds(bounds);
	    
	  });
	  // [END region_getplaces]
	}
var address;
var str;
function test(){
   if (event.keyCode == 13) {   // 13 為 enter 的鍵盤碼
    var group_long = $("#group_long").val();
    var group_lati = $("#group_lati").val();
    var gpcheck = $("#gpcheck");
    if(group_long.length !=0 && group_lati.length !=0 ){
    	gpcheck.css("display","block");
    }
    
	address = document.getElementById("pac-input").value
	document.getElementById("addr").value = address;
	
	 var add = address; //丟地址給變數add 
	 geocoder = new google.maps.Geocoder();
	 geocoder.geocode({                  //把東西丟給google，取得經緯度
	        'address': add 
	    }, function(results, status) {

	        if (status == google.maps.GeocoderStatus.OK) {
// 	        	alert(address);
	        	str=results[0].geometry.location;
// 	            console.log(typeof(str));
	            var keys = Object.keys(str);
// 	            console.log("lat:"+str.lat());
// 	            console.log("lng:"+str.lng());
				
	            document.getElementById("group_lati").value=str.lat();//放經度緯度至hidden傳資料
	        	document.getElementById("group_long").value=str.lng();
	           
	        
	           
	        } else {
// 	            alert("Geocode was not successful for the following reason: " + status);
	        }
	    });
}
}
window.onload=initAutocomplete;

</script>
</html>