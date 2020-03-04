<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hpl.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

%>
<html>
<head>
<title>showCurrently_hpl.jsp</title>
<link href="<%=request.getContextPath()%>/front-end/hpl/css/style2.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/front-end/hpl/js/snap.svg-min.js"></script>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<!-- <script> -->
<!-- </script> -->
<!-- <style> -->

<!-- </style> -->
<script>
	$(document).ready(function(){
		if("${successMsg}".length != 0){
			swal(
				"${successMsg}",
				"",
				'success'
				)
		}
			
		$(".delete_button_showPast").click(function(e){
			e.preventDefault();
			var delete_btn=$(this);
			swal({
				title: '確定刪除此計畫?',
				type: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#d33',
				cancelButtonColor: '#3085d6',
				confirmButtonText: '確定',
				cancelButtonText: '取消'
				}).then(function(isConfirm){
						delete_btn.parent().submit();
					});
		});
		
	});
</script>
</head>
<body>
<!-- 	<div class="container container_showCurrently_hpl"> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="col-sm-1"></div> -->
			<c:forEach var="hplVO" items="${hplList}" varStatus="s">
				<div class="col-sm-12" style="border-bottom:2px solid #9cd9da;">
<!-- 					<div class="col-sm-3 text-center" style="font-size: 108px;font-weight:bold;font-style:italic; height:140px;"> -->
<%-- 						<label for="" class="" style="font-weight:bold;">${s.count}</label> --%>
<!-- 					</div> -->
					<div class="col-sm-3 div_percent text-center" style="padding:10 10;">
		  				<div id="percent_${hplVO.hpno}" class="percent"></div>
		  				<svg id="svg_${hplVO.hpno}" class="svg"></svg>
					</div>
					
					<div class="col-sm-9" style="padding-top: 30px;padding-right:0px;padding-left:50px;">
						<div class="" style="font-size: 22px;font-weight:bold;margin-top:5px;">
							<label for="" class="" style="">${hplVO.hpname}</label>
						</div>
						<div class="" style="font-size: 22px;font-weight:bold;margin-top:5px;">
							<label for="" class="" style="">計畫日期: ${hplVO.hpstdate} / ${hplVO.hpeddate}</label>
						</div>
						<div class="" style="font-size: 22px;font-weight:bold;margin-top:5px;">
							<label for="" class="" style="">共 ${daysMap[hplVO.hpno]} 天</label>
						</div>
						<div class="text-right">
							<form method="post" action="<%=request.getContextPath()%>/hpl/HplServlet.do" style="margin: 0 0;">
								<button class="btn btn-default" style="color: #2a7efa; border: none; background: transparent; text-decoration: underline;">
									<i class="fa fa-chevron-circle-right" style="color: #2a7efa;"></i> 查看詳細內容
								</button>
								<input type="hidden" name="hpno" value="${hplVO.hpno}">
								<input type="hidden" name="action" value="pastDetail">
							</form>	
							<form method="post" action="<%=request.getContextPath()%>/hpl/HplServlet.do" style="margin: 0 0;">
								<button class="btn btn-default delete_button_showPast" style="color: red; border: none; background: transparent; text-decoration: underline;">
									<i class="fa fa-minus-circle" style="color: red;"></i> 刪除
								</button>
								<input type="hidden" name="hpno" value="${hplVO.hpno}">
								<input type="hidden" name="action" value="deletePast">
								<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
							</form>	
						</div>
					</div>
				</div>
			</c:forEach>

<!-- 		</div> -->
<!-- 	</div> -->
	<script>
    $(document).ready(function(){
    	
		if("${successMsg}".length != 0){
			swal(
				"${successMsg}",
				"",
				'success'
				)
		}
		
		$("#delete_button_hplDetail").click(function(e){
			e.preventDefault();
			var delete_btn=$(this);
			swal({
				title: '確定刪除此計畫?',
				type: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#d33',
				cancelButtonColor: '#3085d6',
				confirmButtonText: '確定',
				cancelButtonText: '取消'
				}).then(function(isConfirm){
						delete_btn.parent().submit();
					});
		});
		
	    var canvasSize = 175,
	    centre = canvasSize/2,
	    radius = canvasSize*0.8/2,
	    startY = centre-radius;
	    
	    <c:forEach var="hplVO" items="${hplList}" varStatus="s">
	    	var path${s.count} = "",
		    s${s.count} = Snap('#svg_${hplVO.hpno}'),
		    arc${s.count} = s${s.count}.path(path${s.count}),    
		    percDiv${s.count} = document.getElementById('percent_${hplVO.hpno}');
	    	run("${hplMap[hplVO.hpno]}", percDiv${s.count}, path${s.count}, s${s.count}, arc${s.count});
	    </c:forEach>
		function run(percent, div, path, s, arc) {
		    var endpoint = percent*360;
		    $(function(){
			    Snap.animate(0, endpoint,   function (val) {
			        arc.remove();
			        var d = val,
			            dr = d-90;
			            radians = Math.PI*(dr)/180,
			            endx = centre + radius*Math.cos(radians),
			            endy = centre + radius * Math.sin(radians),
			            largeArc = d>180 ? 1 : 0;  
			            path = "M"+centre+","+startY+" A"+radius+","+radius+" 0 "+largeArc+",1 "+endx+","+endy;
			        arc = s.path(path);
			        arc.attr({
			          stroke: 'red',
			          fill: 'none',
			          strokeWidth: 0
			        });
			        div.innerHTML =    Math.round(val/360*100) +'%';
			    }, 2000, mina.easeinout);  
		    })
		}
    });
	</script>
</body>
</html>