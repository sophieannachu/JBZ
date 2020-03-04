<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.clock.model.*"%>
<%
ClockVO clockVO = (ClockVO) request.getAttribute("clockVO");
%>

<html>
<head>
<title>新增鬧鐘</title></head>

        
<script type="text/javascript"> 
function check_all(obj,clocksche) 
{ 
    var checkboxs = document.getElementsByName("clocksche"); 
    for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;} 
} 
</script> 
<script type="text/javascript">
        $(document).ready(function()
        {
            $('#date').bootstrapMaterialDatePicker
            ({
                time: false,
                clearButton: true
            });
            $('#time').bootstrapMaterialDatePicker
            ({
                date: false,
                shortTime: false,
                format: 'HH:mm'
            });
           
        });
        function  clocksub(){
        	  if(clockform.clockinfo.value == "") 
              {
                      alert("未輸入鬧鐘資訊");
              }
        	  else if(clockform.clocktime.value == "")
              {
              	 	alert("未輸入時間");
              }
        	  else {
              	clockform.submit();
              }
        }
        </script>
<body>


<div style="width:1000px;min-height:350px;">
<h3 style="font-weight:bold;font-family:'微軟正黑體'"><i class="fa fa-clock-o" aria-hidden="true"></i> 新增鬧鐘</h3>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/clock/clock.do" name="clockform" >

    
  
<!-- <table class="hover" style="padding:15px;"> -->

<!-- 	<tr> -->
		
<!-- 		<td>資訊:<input type="TEXT" name="clockinfo" size="45" placeholder="請輸入詳細資訊" -->
<!-- 			value="" /></td> -->
<!-- 	</tr> -->
	<div class="form-group">
      <label for="clockinfo">資訊:</label>
      <input type="TEXT" name="clockinfo" size="45" placeholder="請輸入詳細資訊"
			value="" />
    </div>
<!-- 	<tr> -->
		
<!-- 		<td> -->
<!-- 		星期幾: -->
<!-- 		<input name="clocksche" type="checkbox" value="一"/>一 -->
<!-- 		<input name="clocksche" type="checkbox" value="二"/>二 -->
<!-- 		<input name="clocksche" type="checkbox" value="三"/>三 -->
<!-- 		<input name="clocksche" type="checkbox" value="四"/>四 -->
<!-- 		<input name="clocksche" type="checkbox" value="五"/>五 -->
<!-- 		<input name="clocksche" type="checkbox" value="六"/>六 -->
<!-- 		<input name="clocksche" type="checkbox" value="日"/>日 -->
<!-- 		<input type="checkbox" name="all" onclick="check_all(this,'clocksche')" />每天 -->
<!-- 	</tr> -->
	<div class="form-group">
      <label for="clocksche">星期幾:</label>
      <input name="clocksche" type="checkbox" value="一"/>一
		<input name="clocksche" type="checkbox" value="二"/>二
		<input name="clocksche" type="checkbox" value="三"/>三
		<input name="clocksche" type="checkbox" value="四"/>四
		<input name="clocksche" type="checkbox" value="五"/>五
		<input name="clocksche" type="checkbox" value="六"/>六
		<input name="clocksche" type="checkbox" value="日"/>日
		<input type="checkbox" name="all" onclick="check_all(this,'clocksche')" />每天
    </div>
	
<!-- 	<tr> -->
		
<!-- 			<td> -->
<!-- 			鈴聲: -->
<!-- 			<select size="1" name="clockring"> -->
<!-- 				<option value="1">鈴聲1 -->
<!-- 				<option value="2">鈴聲2 -->
<!-- 				<option value="3">鈴聲3 -->
<!-- 				<option value="4">鈴聲4 -->
<!-- 				<option value="5">鈴聲5 -->
<!-- 			 </select> -->
<!-- 	    </td> -->
<!-- 	</tr> -->
	<div class="form-group">
      <label for="clockring">鈴聲:</label>
      <select size="1" name="clockring">
				<option value="0">聖誕節
				<option value="1">卡農
				<option value="2">圓舞曲
				<option value="3">少女的祈禱
				<option value="4">貝多芬進行曲
			 </select>
    </div>
	

	
<!-- 	<tr> -->
		
<!-- 			<td> -->
<!-- 			類型: -->
<!-- 			<select size="1" name="clocktype"> -->
<!-- 				<option value="0">運動 -->
<!-- 				<option value="1">睡眠 -->
<!-- 				<option value="2">預約 -->
<!-- 				<option value="3">服藥 -->
<!-- 				<option value="4">餐會 -->
<!-- 				<option value="5">自訂 -->
<!-- 			 </select> -->
<!-- 	    </td> -->
			
<!-- 	</tr> -->
	<div class="form-group">
      <label for="clocktype">類型:</label>
      <select size="1" name="clocktype">
				<option value="0">運動
				<option value="1">睡眠
				<option value="2">預約
				<option value="3">服藥
				<option value="4">餐會
				<option value="5">自訂
			 </select>
    </div>
	
	
	
		<label for="clocktime">時間:</label>
		<div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-control-wrapper">
                        <input type="text" id="time" class="form-control floating-label" placeholder="選擇時間" name="clocktime">
                    </div>
                </div>
            </div>
        </div>
	 	
	
	
<!-- </table> -->
<br>
<input type="hidden" name="memno" size="45" value="${memVO.memno}" />
<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
<input type="hidden" name="action" value="insert">
<button type="button" class="btn-lg btn-warning" onclick=clocksub();><i class="fa fa-check" aria-hidden="true"></i>送出新增</button>
</FORM>
</div>
</body>
</html>
