<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.clock.model.*"%>
<%
ClockVO clockVO = (ClockVO) request.getAttribute("clockVO");
%>

<html>
<head>
<title>�s�W�x��</title></head>

        
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
                      alert("����J�x����T");
              }
        	  else if(clockform.clocktime.value == "")
              {
              	 	alert("����J�ɶ�");
              }
        	  else {
              	clockform.submit();
              }
        }
        </script>
<body>


<div style="width:1000px;min-height:350px;">
<h3 style="font-weight:bold;font-family:'�L�n������'"><i class="fa fa-clock-o" aria-hidden="true"></i> �s�W�x��</h3>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/clock/clock.do" name="clockform" >

    
  
<!-- <table class="hover" style="padding:15px;"> -->

<!-- 	<tr> -->
		
<!-- 		<td>��T:<input type="TEXT" name="clockinfo" size="45" placeholder="�п�J�ԲӸ�T" -->
<!-- 			value="" /></td> -->
<!-- 	</tr> -->
	<div class="form-group">
      <label for="clockinfo">��T:</label>
      <input type="TEXT" name="clockinfo" size="45" placeholder="�п�J�ԲӸ�T"
			value="" />
    </div>
<!-- 	<tr> -->
		
<!-- 		<td> -->
<!-- 		�P���X: -->
<!-- 		<input name="clocksche" type="checkbox" value="�@"/>�@ -->
<!-- 		<input name="clocksche" type="checkbox" value="�G"/>�G -->
<!-- 		<input name="clocksche" type="checkbox" value="�T"/>�T -->
<!-- 		<input name="clocksche" type="checkbox" value="�|"/>�| -->
<!-- 		<input name="clocksche" type="checkbox" value="��"/>�� -->
<!-- 		<input name="clocksche" type="checkbox" value="��"/>�� -->
<!-- 		<input name="clocksche" type="checkbox" value="��"/>�� -->
<!-- 		<input type="checkbox" name="all" onclick="check_all(this,'clocksche')" />�C�� -->
<!-- 	</tr> -->
	<div class="form-group">
      <label for="clocksche">�P���X:</label>
      <input name="clocksche" type="checkbox" value="�@"/>�@
		<input name="clocksche" type="checkbox" value="�G"/>�G
		<input name="clocksche" type="checkbox" value="�T"/>�T
		<input name="clocksche" type="checkbox" value="�|"/>�|
		<input name="clocksche" type="checkbox" value="��"/>��
		<input name="clocksche" type="checkbox" value="��"/>��
		<input name="clocksche" type="checkbox" value="��"/>��
		<input type="checkbox" name="all" onclick="check_all(this,'clocksche')" />�C��
    </div>
	
<!-- 	<tr> -->
		
<!-- 			<td> -->
<!-- 			�a�n: -->
<!-- 			<select size="1" name="clockring"> -->
<!-- 				<option value="1">�a�n1 -->
<!-- 				<option value="2">�a�n2 -->
<!-- 				<option value="3">�a�n3 -->
<!-- 				<option value="4">�a�n4 -->
<!-- 				<option value="5">�a�n5 -->
<!-- 			 </select> -->
<!-- 	    </td> -->
<!-- 	</tr> -->
	<div class="form-group">
      <label for="clockring">�a�n:</label>
      <select size="1" name="clockring">
				<option value="0">�t�ϸ`
				<option value="1">�d�A
				<option value="2">��R��
				<option value="3">�֤k����ë
				<option value="4">���h��i�榱
			 </select>
    </div>
	

	
<!-- 	<tr> -->
		
<!-- 			<td> -->
<!-- 			����: -->
<!-- 			<select size="1" name="clocktype"> -->
<!-- 				<option value="0">�B�� -->
<!-- 				<option value="1">�ίv -->
<!-- 				<option value="2">�w�� -->
<!-- 				<option value="3">�A�� -->
<!-- 				<option value="4">�\�| -->
<!-- 				<option value="5">�ۭq -->
<!-- 			 </select> -->
<!-- 	    </td> -->
			
<!-- 	</tr> -->
	<div class="form-group">
      <label for="clocktype">����:</label>
      <select size="1" name="clocktype">
				<option value="0">�B��
				<option value="1">�ίv
				<option value="2">�w��
				<option value="3">�A��
				<option value="4">�\�|
				<option value="5">�ۭq
			 </select>
    </div>
	
	
	
		<label for="clocktime">�ɶ�:</label>
		<div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-control-wrapper">
                        <input type="text" id="time" class="form-control floating-label" placeholder="��ܮɶ�" name="clocktime">
                    </div>
                </div>
            </div>
        </div>
	 	
	
	
<!-- </table> -->
<br>
<input type="hidden" name="memno" size="45" value="${memVO.memno}" />
<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
<input type="hidden" name="action" value="insert">
<button type="button" class="btn-lg btn-warning" onclick=clocksub();><i class="fa fa-check" aria-hidden="true"></i>�e�X�s�W</button>
</FORM>
</div>
</body>
</html>
