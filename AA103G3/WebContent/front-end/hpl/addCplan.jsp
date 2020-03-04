<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<html>
<head>
<title>addCplan.jsp</title>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/hpl/css/datepicker.min.css" />
<script src="<%=request.getContextPath()%>/front-end/hpl/js/bootstrap-datepicker.min.js"></script>
<link href="<%=request.getContextPath()%>/front-end/hpl/css/hpl.css" rel="stylesheet">
<script type="text/javascript">
    $(document).ready(function(){
        $('input[type="checkbox"]').click(function(){
            if($(this).prop("checked")){
                $(this).siblings('input[type="text"]').attr("disabled", false);
            }else{
            	$(this).siblings('input[type="text"]').attr("disabled", true);
            	$(this).siblings('input[type="text"]').val("");
            	$(this).parent().next().hide();
            }
        });
        $('#insertBtn_addCplan').click(function(e){
        	e.preventDefault();
        	var errCount=0;
        	var checkbox=$('input[type="checkbox"]:checked');
        	if(checkbox.length == 0){
        		swal(
	   				"請選擇計劃項目",
	   				"",
	   				'error'
       			)
       			return;
        	}
        	var format = /^([1-9]|[1-9][0-9]|[1][0-4][0-9]|15[0])?$/;
        	for(var i=checkbox.length-1;i>=0;i--){
	        	if($(checkbox[i]).siblings('input[type="text"]').val().length <= 0 || format.test($(checkbox[i]).siblings('input[type="text"]').val()) == false){
	        		console.log(format.test($(checkbox[i]).siblings('input[type="text"]').val()));
	        		$(checkbox[i]).parent().next().show();
	        		errCount++;
	        		$(checkbox[i]).siblings('input[type="text"]').focus();
	            }else{
	            	$(checkbox[i]).parent().next().hide();
	            }
        	}
        	if($("#name").val().length <= 0){
        		$("#name_error").show();
        		$("#name").focus();
        		errCount++;
        	}else{
        		$("#name").next().hide();
        	}
        	
        	if($("#hpeddate").val().length <= 0){
        		$("#hpeddate_error").show();
        		$("#hpeddate").focus();
        		errCount++;
        	}else{
        		$("#hpeddate_error").hide();
        	}
        	
        	if(errCount!=0){
        		return false;
        	}else{
        		$("form").submit();
        	}
        	
        });
        
        $("#datePicker").datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true,
			todayHighlight : true,
			startDate: '0d'
		});
    });
</script>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/hpl/HplServlet.do" role="form">
		<div class="name_pl" style="padding:0 0;border-bottom:0px;">
			<label for="name" class="col-sm-3 control-label font_pl" style="padding-top:5px;">計畫名稱</label>
			<input type="text" name="name" id="name" class="form-control col-sm-9 font_pl" value="我的自訂計畫" style="width:75%;height:40px;"/>
			<div class="col-sm-3"></div>
			<label for="" id="name_error" class="col-sm-9 error_font_cpl" style="padding:0 0;display:none;">請輸入計畫名稱</label>
		</div>
		<div class="name_pl" style="padding:0 0;">
			<label for="hpeddate" class="col-sm-3 control-label font_pl" style="padding-top:5px;">結束日期</label>
			<div class="input-group input-append date col-sm-9" id="datePicker" style="">
				<input type="text" class="form-control font_pl" name="hpeddate" id="hpeddate" style="width:100%;height:40px;" value="" /> 
				<span class="input-group-addon add-on">
					<span class="fa fa-calendar"></span>
				</span>
			</div>
			<div class="col-sm-3"></div>
			<label for="" id="hpeddate_error" class="col-sm-9 error_font_cpl" style="padding:0 0;display:none;">請輸入結束日期</label>
		</div>
		<div class="well outter_hpl" style="">
			<div class="title_hpl" style="">
				<font class="title_font_hpl" style="">體重控制</font>
			</div>
			
			<div class="suggest_cpl" style="">
				<input type="checkbox" class="col-sm-2" name="" value="" style="width:20px;height:20px;" checked/>
				<label for="hpweight" class="col-sm-3 control-label suggest_font_cpl">目標體重</label>
				<input type="text" name="hpweight" id="hpweight" class="form-control suggest_font_cpl col-sm-5" value="" style="width:55%;"/>
				<label for="" class="col-sm-2 control-label suggest_font_cpl">公斤</label>
			</div>
			
			<div class="error_cpl" style="">
				<p class="error_font_cpl" style="">請輸入資料 (1-150)</p>
			</div>
		</div>
		<div class="well outter_hpl" style="">
			<div class="title_hpl" style="">
				<font class="title_font_hpl" style="">腰圍控制</font>
			</div>
			
			<div class="suggest_cpl" style="background:;">
				<input type="checkbox" class="col-sm-2" name="" value="" style="width:20px;height:20px;" checked/>
				<label for="hpwaist" class="col-sm-3 control-label suggest_font_cpl">目標腰圍</label>
				<input type="text" name="hpwaist" id="hpwaist" class="form-control suggest_font_cpl col-sm-5" value="" style="width:55%;"/>
				<label for="" class="col-sm-2 control-label suggest_font_cpl">公分</label>
			</div>
			
			<div class="error_cpl" style="">
				<p class="error_font_cpl" style="">請輸入資料 (1-150)</p>
			</div>
		</div>
		<div class="well outter_hpl" style="">
			<div class="title_hpl" style="">
				<font class="title_font_hpl" style="">血壓控制</font>
			</div>
			
			<div class="suggest_cpl" style="background:;">
				<input type="checkbox" class="col-sm-2" name="" value="" style="width:20px;height:20px;" checked/>
				<label for="hpbp" class="col-sm-5 control-label suggest_font_cpl">每日量血壓次數</label>
				<input type="text" name="hpbp" id="hpbp" class="form-control suggest_font_cpl col-sm-4" value="" style="width:40%;"/>
				<label for="" class="col-sm-1 control-label suggest_font_cpl">次</label>
			</div>
			
			<div class="error_cpl" style="">
				<p class="error_font_cpl" style="">請輸入資料 (1-150)</p>
			</div>
		</div>
		<div class="well outter_hpl" style="">
			<div class="title_hpl" style="">
				<font class="title_font_hpl" style="">血糖控制</font>
			</div>
			
			<div class="suggest_cpl" style="background:;">
				<input type="checkbox" class="col-sm-2" name="" value="" style="width:20px;height:20px;" checked/>
				<label for="hpbg" class="col-sm-5 control-label suggest_font_cpl">每日量血糖次數</label>
				<input type="text" name="hpbg" id="hpbg" class="form-control suggest_font_cpl col-sm-4" value="" style="width:40%;"/>
				<label for="" class="col-sm-1 control-label suggest_font_cpl">次</label>
			</div>
			
			<div class="error_cpl" style="">
				<p class="error_font_cpl" style="">請輸入資料 (1-150)</p>
			</div>
		</div>
<!-- 		<div class="well outter_hpl" style=""> -->
<!-- 			<div class="title_hpl" style=""> -->
<!-- 				<font class="title_font_hpl" style="">每日步數</font> -->
<!-- 			</div> -->
			
<!-- 			<div class="suggest_cpl" style="background:;"> -->
<!-- 				<input type="checkbox" class="col-sm-1" name="" value="" style="width:20px;height:20px;" checked/> -->
<!-- 				<label for="hpstep" class="col-sm-4 control-label suggest_font_cpl">每日計畫步數</label> -->
<!-- 				<input type="text" name="hpstep" id="hpstep" class="form-control suggest_font_cpl col-sm-5" value="" style="width:45%;"/> -->
<!-- 				<label for="" class="col-sm-2 control-label suggest_font_cpl">步</label> -->
<!-- 			</div> -->
			
<!-- 			<div class="error_cpl" style=""> -->
<!-- 				<p class="error_font_cpl" style="">請輸入資料</p> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="well outter_hpl" style="">
			<div class="title_hpl" style="">
				<font class="title_font_hpl" style="">每周運動量</font>
			</div>
			
			<div class="suggest_cpl" style="background:;">
				<input type="checkbox" class="col-sm-1" name="" value="" style="width:20px;height:20px;" checked/>
				<label for="hpsp" class="col-sm-4 control-label suggest_font_cpl">計畫運動次數</label>
				<input type="text" name="hpsp" id="hpsp" class="form-control suggest_font_cpl col-sm-5" value="" style="width:45%;"/>
				<label for="" class="col-sm-2 control-label suggest_font_cpl">次/周</label>
			</div>
			
			<div class="error_cpl" style="">
				<p class="error_font_cpl" style="">請輸入資料 (1-150)</p>
			</div>
		</div>
		<div class="well outter_button" style="">
			<button class="btn btn-default col-sm-12" id="insertBtn_addCplan" style="margin-bottom:10px;font-size:20px;color:#0e9ea3;font-weight:bold;">
			 	新增
			</button>
			<input type="hidden" name="reSubmitCode" value="<%=session.getAttribute("reSubmitCode")%>">
			<input type="hidden" name="action" value="insertCplan">
			<a class="btn btn-default col-sm-12" id="" href="<%=request.getContextPath()%>/hpl/HplServlet.do?action=main&requestURL=<%=request.getServletPath()%>" role="button" style="margin-bottom:20px;font-size:20px;color:#4b76b5;font-weight:bold;">
		 		返回
			</a>
		</div>
	</form>
</body>
</html>