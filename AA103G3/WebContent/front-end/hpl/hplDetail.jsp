<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>hplDetail.jsp</title>
<link href="<%=request.getContextPath()%>/front-end/hpl/css/style.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/front-end/hpl/js/snap.svg-min.js"></script>
<link href="<%=request.getContextPath()%>/front-end/hpl/css/hpl.css" rel="stylesheet">

</head>
<body>
	<div class="name_detail" style="">
<!-- 		<div class="col-sm-4"></div> -->
		<label for="" class="col-sm-7 control-label font_detail text-left" style="font-size:30px;margin-top:25px;">�p�e�W�� ${hplVO.hpname}</label>
		<div class="col-sm-5 text-right" style="padding:0 0;">
			<label for="" class="control-label font_detail">�}�l��� ${hplVO.hpstdate}</label>
			<label for="" class="control-label font_detail">������� ${hplVO.hpeddate}</label>
		</div>
	</div>
<!-- 	          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;"></i>123</dt> -->
<!-- 	          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;"></i>123</dt> -->
	<c:if test="${hplVO.hpbmi != -1 && hplVO.hpbmi != 0}">
		<div class="well outter_hpl col-sm-12" style="">

			<div class="col-sm-8" style="padding: 0 0;">
				<div class="title_detail" style="">
					<font class="title_font_detail" style="">�魫����</font>
				</div>
				
				<div class="suggest_detail" style="">
					<p class="suggest_font_detail" style="">�ؼ��魫�� ${hplVO.hpbmi} ����</p>
				</div>
				
				<div class="record_detail" style="">
					<dl>
			          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;font-size:20px;"></i>�ثe�魫�� ${hplMap['now_weight']} ����</dt>
<!-- 			          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;"></i>123456</dt> -->
			        </dl>
		        </div>
			</div>
			
			<div class="col-sm-4 div_percent text-center" style="">
   				<div id="hpbmi_percent" class="percent"></div>
   				<svg id="hpbmi_svg" class="svg"></svg>
			</div>
		</div>
	</c:if>
	
	<c:if test="${hplVO.hpwaist != -1 && hplVO.hpwaist != 0}">
		<div class="well outter_hpl col-sm-12" style="">
		
			<div class="col-sm-8" style="padding: 0 0;">
				<div class="title_detail" style="">
					<font class="title_font_detail" style="">�y�򱱨�</font>
				</div>
				
				<div class="suggest_detail" style="">
					<p class="suggest_font_detail" style="">�ؼиy�� ${hplVO.hpwaist} ����</p>
				</div>
				
				<div class="record_detail" style="">
					<dl>
			          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;font-size:20px;"></i>�ثe�y�� ${hplMap['now_waist']} ����</dt>
<!-- 			          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;"></i>123456</dt> -->
			        </dl>
		        </div>
			</div>
			
			<div class="col-sm-4 div_percent text-center" style="">
  					<div id="hpwaist_percent" class="percent"></div>
  					<svg id="hpwaist_svg" class="svg"></svg>
			</div>
		</div>
	</c:if>
	
	<c:if test="${hplVO.hpbp != -1 && hplVO.hpbp != 0}">
		<div class="well outter_hpl col-sm-12" style="">
			<div class="col-sm-8" style="padding: 0 0;">
				<div class="title_detail" style="">
					<font class="title_font_detail" style="">��������</font>
				</div>
				
				<div class="suggest_detail" style="">
					<p class="suggest_font_detail" style="">�ؼЦ��Ƭ� ${hplVO.hpbp} ��/��</p>
				</div>
				
				<div class="record_detail" style="">
					<dl>
			          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;font-size:20px;"></i>����������Ƭ� ${hplMap['hpbp_times']} ��</dt>
			          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;font-size:20px;"></i>�ؼйF���Ʋ֭p ${hplMap['hpbp_goalTimes']} ��</dt>
			        </dl>
		        </div>
	        </div>
	        
	        <div class="col-sm-4 div_percent text-center" style="">
  				<div id="hpbp_percent" class="percent"></div>
  				<svg id="hpbp_svg" class="svg"></svg>
			</div>
		</div>
	</c:if>
	
	<c:if test="${hplVO.hpbg != -1 && hplVO.hpbg != 0}">
		<div class="well outter_hpl col-sm-12" style="">
			<div class="col-sm-8" style="padding: 0 0;">
				<div class="title_detail" style="">
					<font class="title_font_detail" style="">��}����</font>
				</div>
				
				<div class="suggest_detail" style="">
					<p class="suggest_font_detail" style="">�ؼЦ��Ƭ� ${hplVO.hpbg} ��/��</p>
				</div>
				
				<div class="record_detail" style="">
					<dl>
			          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;font-size:20px;"></i>����������Ƭ� ${hplMap['hpbg_times']} ��</dt>
			          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;font-size:20px;"></i>�ؼйF���Ʋ֭p ${hplMap['hpbg_goalTimes']} ��</dt>
			        </dl>
		        </div>
	        </div>
	        
	         <div class="col-sm-4 div_percent text-center" style="">
  				<div id="hpbg_percent" class="percent"></div>
  				<svg id="hpbg_svg" class="svg"></svg>
			</div>
		</div>
	</c:if>
	
	<c:if test="${hplVO.hpexer != -1 && hplVO.hpexer != 0}">
		<div class="well outter_hpl col-sm-12" style="">
			<div class="col-sm-8" style="padding: 0 0;">
				<div class="title_detail" style="">
					<font class="title_font_detail" style="">�C�P�B�ʶq</font>
				</div>
				
				<div class="suggest_detail" style="">
					<p class="suggest_font_detail" style="">�ؼйB�ʦ��Ƭ� ${hplVO.hpexer} ��/�P</p>
				</div>
				
				<div class="record_detail" style="">
					<dl>
<!-- 			          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;"></i>123456</dt> -->
			          <dt class="record_font_detail" style=""><i class="fa fa-circle" style="color:#4b76b5;margin-right:5px;font-size:20px;"></i>�֭p�B�ʬ������� ${hplMap['hpsp_times']} ��</dt>
			        </dl>
		        </div>
			</div>
			
			<div class="col-sm-4 div_percent text-center" style="">
  				<div id="hpexer_percent" class="percent"></div>
  				<svg id="hpexer_svg" class="svg"></svg>
			</div>
		</div>
	</c:if>
	
	<div class="well outter_button" style="">
		<form method="post" action="<%=request.getContextPath()%>/hpl/HplServlet.do" class="" role="form">
			<button class="btn btn-default col-sm-12" id="delete_button_hplDetail" style="margin-bottom:10px;font-size:20px;color:red;font-weight:bold;">
			 	�R��
			</button>
		<input type="hidden" name="action" value="deletePlan">
		<input type="hidden" name="hpno" value="${hplVO.hpno}">
		<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
		</form>
		<a class="btn btn-default col-sm-12" id="" href="<%=request.getContextPath()%>/hpl/HplServlet.do?action=main&requestURL=<%=request.getServletPath()%>" role="button" style="margin-bottom:20px;font-size:20px;color:#4b76b5;font-weight:bold;">
		 	��^
		</a>
	</div>
	
	
	
    <script>
    $(document).ready(function(){
    	
		$("#delete_button_hplDetail").click(function(e){
			e.preventDefault();
			var delete_btn=$(this);
			swal({
				title: '�T�w�R�����p�e?',
				type: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#d33',
				cancelButtonColor: '#3085d6',
				confirmButtonText: '�T�w',
				cancelButtonText: '����'
				}).then(function(isConfirm){
						delete_btn.parent().submit();
					});
		});
		
	    var canvasSize = 175,
	    centre = canvasSize/2,
	    radius = canvasSize*0.8/2,
	    startY = centre-radius;
	    
	    if("${hplVO.hpbmi}" != -1 && "${hplVO.hpbmi}" != 0){
	    	var path1 = "",
		    s1 = Snap('#hpbmi_svg'),
		    arc1 = s1.path(path1),    
		    percDiv1 = document.getElementById('hpbmi_percent');
	    	run("${hplMap['weight']}", percDiv1, path1, s1, arc1);
	    }
	    
	    if("${hplVO.hpwaist}" != -1 && "${hplVO.hpwaist}" != 0){
	    	var path2 = "",
		    s2 = Snap('#hpwaist_svg'),
		    arc2 = s2.path(path2),    
		    percDiv2 = document.getElementById('hpwaist_percent');
	    	run("${hplMap['waist']}", percDiv2, path2, s2, arc2);
	    }
	    
	    if("${hplVO.hpbp}" != -1 && "${hplVO.hpbp}" != 0){
	    	var path3 = "",
		    s3 = Snap('#hpbp_svg'),
		    arc3 = s3.path(path3),    
		    percDiv3 = document.getElementById('hpbp_percent');
	    	run("${hplMap['bp']}", percDiv3, path3, s3, arc3);
   		}
	    
	    if("${hplVO.hpbg}" != -1 && "${hplVO.hpbg}" != 0){
	    	var path4 = "",
		    s4 = Snap('#hpbg_svg'),
		    arc4 = s4.path(path4),    
		    percDiv4 = document.getElementById('hpbg_percent');
	    	run("${hplMap['bg']}", percDiv4, path4, s4, arc4);
	    }
	    
		if("${hplVO.hpexer}" != -1 && "${hplVO.hpexer}" != 0){
	    	var path5 = "",
		    s5 = Snap('#hpexer_svg'),
		    arc5 = s5.path(path5),    
		    percDiv5 = document.getElementById('hpexer_percent');
	    	run("${hplMap['sp']}", percDiv5, path5, s5, arc5);
	    }

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
			          stroke: '#3da08d',
			          fill: 'none',
			          strokeWidth: 12
			        });
			        div.innerHTML =    Math.round(val/360*100) +'%';
			    }, 2000, mina.easeinout);  
		    })
		}
    });
    </script>
</body>
</html>