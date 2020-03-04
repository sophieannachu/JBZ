<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.foodlist.model.*"%>
<%@ page import="com.basiccheck.model.*"%>
<%@page import="java.util.*" %>

<%
FoodlistVO foodlistVO = (FoodlistVO) request.getAttribute("foodlistVO");
BasicCheckVO basicCheckVO = (BasicCheckVO) request.getAttribute("basicCheckVO");

%>  
<%
BasicCheckService basicCal = new BasicCheckService();
 	
%>
<%
	double danny=Math.random()*10;
	session.setAttribute("danny", danny);
%>
<jsp:useBean id="foodSvc" scope="page" class="com.foodlist.model.FoodService" />

<!DOCTYPE html>
<html lang="">
<head>


		 <script>
		 		var listfd = new Array();
		 		var listfd1 = new Array();
		 		var listfd2 = new Array();
		 		var listfd3 = new Array();
		 		var listfd4 = new Array();
		 		var listfd5 = new Array();
		 		var listfd6 = new Array();
		 		var listfd7 = new Array();
		 		var islistfd = new Array();
		 		
		 		
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
			  'width':1000,
			  'height':400};
			  
			  chart = new google.visualization.PieChart(document.getElementById('chart_div'));
			  google.visualization.events.addListener(chart, 'select', selectHandler);
			  chart.draw(data, options);
			  }

			  function selectHandler() {
			  var selectedItem = chart.getSelection()[0];
			  var value = data.getValue(selectedItem.row, 0);
			  swal('營養素 ' + value);
			  }
			  
			  $(document).ready(function() {
			    	$('#datePicker')
			        .datepicker({
			            format: 'yyyy-mm-dd',
			            autoclose: true,
			    		todayHighlight: true
			        });
			    	var dayCal;
			    	var dayCalAdvice = $('#dayCalAdvice').val();
			    	var dayCalAlready = $('#dayCalAlready').val();
			    	 dayCal = dayCalAdvice-dayCalAlready
			    	 	$('#dayCal').val(dayCal);
			    	 if(dayCal>0){
			    		 $('#calRemind').append("卡路里攝取不足!");
			    		 $('#calRemind').css('color','red');
			    	 }
			    	 else if(dayCal<=0){
			    		 $('#calRemind').append("已攝取足夠卡路里!");
			    		 $('#calRemind').css('color','green');
			    		 $('#dayCal').val(0);
			    	 }
			    	
				  });
			  
			  </script>
			   <c:if test="${param.action==\"getDateAndMemno\"}" >
			  		<script>
			  		$(document).ready(function() {
				  		$('#tab3').addClass("active");
				  		$('#tab1').removeClass("active");
				  		$('#tab-3').addClass("active");
				  		$('#tab-1').removeClass("active");
			  		});
			  		</script>
			  	</c:if>
			  
			  	 <c:forEach var="foodlistVO1" items="${foodlistVO1}">
            		<c:forEach var="foodVO" items="${foodSvc.all}">
             			<c:if test="${foodlistVO1.fd_no==foodVO.fd_no}">
	         				<script>
				         	var b1=0,b2=0,b3=0,b4=0,b5=0,b6=0,b7=0,b8=0;
				         	var a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0;
				         	
				         	b1="${foodVO.prot}";
				         	b2="${foodVO.fat}";
				         	b3="${foodVO.cho}";
				         	b4="${foodVO.sugar}";
				         	b5="${foodVO.df}";
				         	b6="${foodVO.na}";
				         	b7="${foodVO.ca}";
				         	b8="${foodVO.k}";
				         	
				         	listfd.push(b1);
				         	listfd1.push(b2);
				         	listfd2.push(b3);
				         	listfd3.push(b4);
				         	listfd4.push(b5);
				         	listfd5.push(b6);
				         	listfd6.push(b7);
				         	listfd7.push(b8);
				      
				         
				         	for(x=0;x<listfd.length;x++){
				         		a1+=parseInt(listfd[x]);
				         		a2+=parseInt(listfd1[x]);
				         		a3+=parseInt(listfd2[x]);
				         		a4+=parseInt(listfd3[x]);
				         		a5+=parseInt(listfd4[x]);
				         		a6+=parseInt(listfd5[x]);
				         		a7+=parseInt(listfd6[x]);
				         		a8+=parseInt(listfd7[x]);
				    
				         	}
				         	console.log(a1);
				         	console.log(a2);
				         	console.log(a3);
				         	console.log(a4);
				         	console.log(a5);
				         	console.log(a6);
				         	console.log(a7);
				         	console.log(a8);
				         	
				         	</script>
            			 </c:if>
           			</c:forEach>
           		</c:forEach>
			  	
		
<style type="text/css">
.table>thead>tr>td{
	width:auto;
}
</style>
<script>
	$(function() {
	if("${foodsweat}".length!=0){
		swal("Good job!", "你已經成功新增食物!", "success");
	}
	
  });
</script>
	</head>
	
	
	<body style="margin:0px auto;">
		<div role="tabpanel" style="margin-top:10px;">
		    <!-- 標籤面板：標籤區 -->
		    <ul class="nav nav-tabs" role="tablist" style="font-size:20px;">
		        <li role="presentation" id="tab-1" class="active" >
		            <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">飲食紀錄</a>
		        </li>
		        <li role="presentation" id="tab-3">
		            <a href="#tab3"  aria-controls="tab3" role="tab" data-toggle="tab">營養素分析</a>
		        </li>
		    </ul>
		
		    <!-- 標籤面板：內容區 -->
		    <div class="tab-content">
		        <div role="tabpanel" class="tab-pane active" id="tab1" style="min-height:600px;">
					<div class="h1"></div>

					<div style="">
						<table class="" style="width:100%;">
							<tr>
								<td class="" style="">每日建議卡路里:</td>
								<td ><input style="width:100px;" type="text" id="dayCalAdvice" name="" value="<%=session.getAttribute("bmrrr")%>" class="textinput" style="" disabled> </td>
								<td class="" style="">  -  已攝取卡路里:</td>
								<td ><input style="width:100px;" type="text" id="dayCalAlready" name="" value="<%=(session.getAttribute("calSubtatal")==null)? 0 :session.getAttribute("calSubtatal")%>" class="textinput" style="" disabled> </td>
								<td class="" style="">  =  剩餘卡路里:</td>
								<td > <input style="width:100px;" type="text" id="dayCal" name="" value="" class="textinput" style="" disabled> </td>
								<td style="padding:15px;"><div id="calRemind"></div></td>
							</tr>
							<tr>
								<%java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());%>
							 
							</tr>
	
						</table>
					</div>	
					<hr>
<!-- 				<div class="h1"></div> -->
					<div class="opt">
<!-- 					  <div class="row"> -->
						<div class="container col-md-9">
						
						  <button type="button" class="btn-warning btn" data-toggle="modal" data-target="#myModal" style="float:left"><i class="fa fa-plus"></i> 自訂食物</button>
						
						  <!-- Modal -->
						  <div class="modal fade" id="myModal" role="dialog">
						    <div class="modal-dialog">
						    
						      <!-- Modal content-->
						      <div class="modal-content">
						        <div class="modal-header" style="background-color:#63C2C5; color:#fff;">
						          <button type="button" class="close" data-dismiss="modal">&times;</button>
						          <h4 class="modal-title fa fa-apple">  新增食物表</h4>
						        </div>
						        <div class="modal-body">
						          <jsp:include page="foodadd.jsp"/>
						        </div>
						       
						      </div>
						      
						    </div>
						  </div>
						  
						</div>
						<div class="form-group col-md-3">
						<div style="float:right;">
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodlist/foodlist.do" >
							<input type="text" style="width:100px;" name="fd_name" placeholder="搜尋食物">
							<input value="getKeyWord"  type="hidden" name="action">
							<button value="搜尋食物"  type="submit" id="sfd" class="fa fa-search"></button>
							</FORM>
						</div>						
				
					
	
						
<!-- 						<form class="navbar-form navbar-left" role="search"> -->
<!-- 						<div class="form-group"> -->
<!-- 							<input  style="width: 80px" type="text" id="sfd" oninput="search(this);"> -->
<!-- 							<div class="friSearch" style="z-index:10;position: fixed;min-width: 200px;padding-left:0;"> -->
<!-- 								<ul  class="Findfood" style="list-style:none;padding-left:0;margin-bottom:0px;"> -->
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 							<button type="submit" class="btn btn-default">Submit</button> -->
<!-- 						</form> -->
						</div>
<!-- 					</div> -->
											<script>
// 															 function search() {
// 														var query = $("#sfd").val();
// 													  	$.ajax({
// 															type : "post",
<%-- 															url : "<%=request.getContextPath()%>/foodlist/food.do", --%>
// 															data : {
// 																action : "getKeyWord",
// 																query : query
// 															},
// 															dataType : "json",
// 															success : function(data) {
// 																console.log(data);
// 																var str="";
// 																for(var i=0;i<data.length;i++){
// 																	var name=data[i].fd_name;
// 																	str+='<li>'+name+'</li>'
// 																}
// 																$(".Findfood").html(str);
// 															},
// 															error : function(data) {
// 																console.log(data);
// 																$(".Findfood").html("");
// 															}
// 														})
														
// 													 if(keyCode==40){
														 
// 													 }
// 													}
											 </script>	
						
						
						
						<div style="margin-top:20px">	
						<c:if test="${param.action==\"getKeyWord\"}" >
							<jsp:include page="KeyWordFd.jsp"/>
						</c:if>
						<c:if test="${param.action!=\"getKeyWord\"}" >
							<jsp:include page="listFd.jsp" />
						</c:if>
					
										
						</div>
<!-- 					 -->
					</div>
						
<%-- 				<%if (request.getAttribute("foodVO")!=null){%> --%>
<%-- 				<jsp:include page="onefood.jsp" /> --%>
<%-- 				<%} %> --%>
						<div style="margin-top:20px;">
							<jsp:include page="onefood.jsp" flush="true" />
		
				        </div>
		        </div>
		        
		        <div role="tabpanel" class="tab-pane" id="tab3" style="min-height:350px;">
					<div style="width:250px;"> 
					  
					  <div class="date">
					  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodlist/foodlist.do" >	
					  			<p style="margin-top:10px;">選擇日期   <button type="submit" value="查詢日期" class="fa fa-search"></button></p>
			            	<div class="input-group input-append date" id="datePicker">
			                	<input type="text" class="form-control" name="fddate" value="<%= (foodlistVO==null)? date_SQL : foodlistVO.getFddate()%>"/>
				                <span class="input-group-addon add-on">
				                	<span class="fa fa-calendar"></span>
				                </span>
				                
			            	</div>
			            	
        					<input type="hidden" name="action" value="getDateAndMemno">
        					<input type="hidden" name="memno" value="${memVO.memno}">
        				</FORM>
        			  </div>
        			  	
        		      </div>
		
		
					 <div id="chart_div" style="width:400; height:400;float:left;"></div>
					 	<div class="col-sm-12">
					 		<table border=1  style="width:100% ; margin-top:10px;" class="table table-striped table-bordered table-hover">
									<tr align='center' valign='middle' style="background-color:#63C2C5; color:#fff;">
										
										<th>餐別</th>
										<th>飲食日期</th>
										<th>份量</th>
										<th>卡路里</th>
										<th>食物名稱</th>
										
									</tr>
									
									<c:forEach var="foodlistVO1" items="${foodlistVO1}">
									<tr align='center' valign='middle'>
											
											<td>${foodlistVO1.fddesc}</td>
											<td>${foodlistVO1.fddate}</td>
											<td>${foodlistVO1.fdqua}</td>
											<td>
												<c:forEach var="foodVO" items="${foodSvc.all}">
								                    <c:if test="${foodlistVO1.fd_no==foodVO.fd_no}">
									                   ${(foodVO.cal)*(foodlistVO1.fdqua)}
									                   
								                    </c:if>
								                </c:forEach>
											
											</td>
											<td><c:forEach var="foodVO" items="${foodSvc.all}">
								                    <c:if test="${foodlistVO1.fd_no==foodVO.fd_no}">
									                    ${foodVO.fd_name}
								                    </c:if>
								                </c:forEach>
											</td>
											
											
									</tr>
									</c:forEach>
							</table>
						</div>
		        </div>
		    </div>
		</div>
		
		
		
		

		
<!-- 		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
	</body>
</html>