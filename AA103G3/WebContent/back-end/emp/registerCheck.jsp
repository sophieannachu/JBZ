<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<script>
$(document).ready(function() {
	$("#datePicker").datepicker({
		format : 'yyyy-mm-dd',
		autoclose : true,
		todayHighlight : true
	});
	
	$(".preview").hover(function(){
		$(".hide_img").css("display", "block");
		},function(){
		$(".hide_img").css("display", "none");
	});
	
	$("#name").blur(function(){
		var name=$("#name").val();
		if(name.trim().length<=0){
			$("#name_Err").html("<i class='fa fa-times'></i> <b>請輸入姓名</b>").removeClass("hidden");
			$("#name_Corr").addClass("hidden");
			$("#name").parent().parent().addClass("has-error");
		}else{
			$("#name_Err").html("");
			$("#name_Err").addClass("hidden");
			$("#name_Corr").removeClass("hidden");
			$("#name").parent().parent().removeClass("has-error");
			$("#name").parent().parent().addClass("has-success");
		}
	});
	
	$("input[name=sex]").blur(function(){
		var sex=$("input[name=sex]:checked").val();
		if(sex===undefined || sex.length<=0){
			$("#sex_Err").html("<i class='fa fa-times'></i> <b>請選擇性別</b>").removeClass("hidden");
			$("#sex_Corr").addClass("hidden");
		}else{
			$("#sex_Err").html("");
			$("#sex_Err").addClass("hidden");
			$("#sex_Corr").removeClass("hidden");
		}
	});
	
	$("#birth").change(function(){
		var bir=$("#birth").val();
		if(bir.length<=0){
			$("#birth_Err").html("<i class='fa fa-times'></i> <b>請選擇生日</b>").removeClass("hidden");
			$("#birth_Corr").addClass("hidden");
			$("#birth").parent().parent().parent().addClass("has-error");
		}else{
			$("#birth_Err").html("");
			$("#birth_Err").addClass("hidden");
			$("#birth_Corr").removeClass("hidden");
			$("#birth").parent().parent().parent().removeClass("has-error");
			$("#birth").parent().parent().parent().addClass("has-success");
		}
	});
	
	$("#phone").blur(function(){
		var phone=$("#phone").val();
		if(phone.trim().length<=0){
			$("#phone_Err").html("<i class='fa fa-times'> </i> <b>請輸入手機</b>").removeClass("hidden");
			$("#phone_Corr").addClass("hidden");
			$("#phone").parent().parent().addClass("has-error");
		}else if(phone.length<10){
			$("#phone_Err").html("<i class='fa fa-times'></i> <b>請重新輸入手機</b>").removeClass("hidden");
			$("#phone_Corr").addClass("hidden");
			$("#phone").parent().parent().addClass("has-error");
		}else{
			$("#phone_Err").html("");
			$("#phone_Err").addClass("hidden");
			$("#phone_Corr").removeClass("hidden");
			$("#phone").parent().parent().removeClass("has-error");
			$("#phone").parent().parent().addClass("has-success");
		}
	});
	
	$("#email").blur(function(){
		var email=$("#email").val();
		if(email.trim().length<=0){
			$("#email_Err").html("<i class='fa fa-times'></i> <b>請輸入信箱</b>").removeClass("hidden");
			$("#email_Corr").addClass("hidden");
			$("#email").parent().parent().addClass("has-error");
		}else if(email.indexOf('@')==-1 || email.indexOf('.')==-1){
			$("#email_Err").html("<i class='fa fa-times'></i> <b>請重新輸入信箱</b>").removeClass("hidden");
			$("#email_Corr").addClass("hidden");
			$("#email").parent().parent().addClass("has-error");
		}else{
			$("#email_Err").html("");
			$("#email_Err").addClass("hidden");
			$("#email_Corr").removeClass("hidden");
			$("#email").parent().parent().removeClass("has-error");
			$("#email").parent().parent().addClass("has-success");
		}
	});
	
	$("#submit_insert").click(function(e){
		e.preventDefault();
		var errCount=0;

		var acc=$("#acc_Err").html();
		if(acc.trim().length != 0){
			errCount++;
			$("#acc_Err").removeClass("hidden");
			$("#acc").parent().parent().addClass("has-error");
		}else{
			$("#acc_Err").addClass("hidden");
		}
		
		var name=$("#name_Err").html();
		if(name.trim().length != 0){
			errCount++;
			$("#name_Err").removeClass("hidden");
			$("#name").parent().parent().addClass("has-error");
		}else{
			$("#name_Err").addClass("hidden");
		}
		
		var sex=$("#sex_Err").html();
		if(sex.trim().length != 0){
			errCount++;
			$("#sex_Err").removeClass("hidden");
		}else{
			$("#sex_Err").addClass("hidden");
		}
		
		var birth=$("#birth_Err").html();
		if(birth.trim().length != 0){
			errCount++;
			$("#birth_Err").removeClass("hidden");
			$("#birth").parent().parent().parent().addClass("has-error");
		}else{
			$("#birth_Err").addClass("hidden");
		}
		
		var phone=$("#phone_Err").html();
		if(phone.trim().length != 0){
			errCount++;
			$("#phone_Err").removeClass("hidden");
			$("#phone").parent().parent().addClass("has-error");
		}else{
			$("#phone_Err").addClass("hidden");
		}
		
		var email=$("#email_Err").html();
		if(email.trim().length != 0){
			errCount++;
			$("#email_Err").removeClass("hidden");
			$("#email").parent().parent().addClass("has-error");
		}else{
			$("#email_Err").addClass("hidden");
		}
		
		if(errCount!=0){
			return false;
		}else{
			$("form").submit();
		}
	});
});

function acc_check(){
	var acc=$("#acc").val();
	if(acc.trim().length<5){
		$("#acc_Err").html("<i class='fa fa-times'></i> <b>請輸入5位數以上之帳號</b>").removeClass("hidden");
		$("#acc").parent().parent().addClass("has-error");
		$("#acc_Corr").addClass("hidden");
	}else{
		$.ajax({
			 type:"POST",
			 url:"<%=request.getContextPath()%>/emp/EmpServlet.do",
			 data:{acc:acc, action:"acc_check"},
			 dataType:"text",
			 success:function (data){
				 if(data == "true"){
					 $("#acc_Err").html("");
					 $("#acc_Err").addClass("hidden");
					 $("#acc_Corr").removeClass("hidden");
					 $("#acc").parent().parent().removeClass("has-error");
					 $("#acc").parent().parent().addClass("has-success");
				 }else{
					 $("#acc_Err").html("<i class='fa fa-times'></i> <b>此帳號已有人使用</b>").removeClass("hidden");
					$("#acc").parent().parent().addClass("has-error");
					 $("#acc_Corr").addClass("hidden");
//						 $('#acc').css('border','1px #C33 solid');
				 }
		     },
       		error:function(){alert("error")}
		});
	}
	
}
function magic(){
	var acc="aa103g3", name="賴吉盛", birth="1999-05-05", phone="0915123789", email="aa103g3K@gmail.com";
	$("#male").attr("checked", true);
	$("#acc").val(acc);
	$("#name").val(name);
	$("#birth").val(birth);
	$("#phone").val(phone);
	$("#email").val(email);
	
	name=$("#name").val();
	if(name.trim().length<=0){
		$("#name_Err").html("<i class='fa fa-times'></i> <b>請輸入姓名</b>").removeClass("hidden");
		$("#name_Corr").addClass("hidden");
		$("#name").parent().parent().addClass("has-error");
	}else{
		$("#name_Err").html("");
		$("#name_Err").addClass("hidden");
		$("#name_Corr").removeClass("hidden");
		$("#name").parent().parent().removeClass("has-error");
		$("#name").parent().parent().addClass("has-success");
	}

	sex=$("input[name=sex]:checked").val();
	if(sex===undefined || sex.length<=0){
		$("#sex_Err").html("<i class='fa fa-times'></i> <b>請選擇性別</b>").removeClass("hidden");
		$("#sex_Corr").addClass("hidden");
	}else{
		$("#sex_Err").html("");
		$("#sex_Err").addClass("hidden");
		$("#sex_Corr").removeClass("hidden");
	}

	birth=$("#birth").val();
	if(birth.length<=0){
		$("#birth_Err").html("<i class='fa fa-times'></i> <b>請選擇生日</b>").removeClass("hidden");
		$("#birth_Corr").addClass("hidden");
		$("#birth").parent().parent().parent().addClass("has-error");
	}else{
		$("#birth_Err").html("");
		$("#birth_Err").addClass("hidden");
		$("#birth_Corr").removeClass("hidden");
		$("#birth").parent().parent().parent().removeClass("has-error");
		$("#birth").parent().parent().parent().addClass("has-success");
	}

	phone=$("#phone").val();
	if(phone.trim().length<=0){
		$("#phone_Err").html("<i class='fa fa-times'></i> <b>請輸入手機</b>").removeClass("hidden");
		$("#phone_Corr").addClass("hidden");
		$("#phone").parent().parent().addClass("has-error");
	}else if(phone.length<10){
		$("#phone_Err").html("<i class='fa fa-times'></i> <b>請重新輸入手機</b>").removeClass("hidden");
		$("#phone_Corr").addClass("hidden");
		$("#phone").parent().parent().addClass("has-error");
	}else{
		$("#phone_Err").html("");
		$("#phone_Err").addClass("hidden");
		$("#phone_Corr").removeClass("hidden");
		$("#phone").parent().parent().removeClass("has-error");
		$("#phone").parent().parent().addClass("has-success");
	}

	email=$("#email").val();
	if(email.trim().length<=0){
		$("#email_Err").html("<i class='fa fa-times'></i> <b>請輸入信箱</b>").removeClass("hidden");
		$("#email_Corr").addClass("hidden");
		$("#email").parent().parent().addClass("has-error");
	}else if(email.indexOf('@')==-1 || email.indexOf('.')==-1){
		$("#email_Err").html("<i class='fa fa-times'></i> <b>請重新輸入信箱</b>").removeClass("hidden");
		$("#email_Corr").addClass("hidden");
		$("#email").parent().parent().addClass("has-error");
	}else{
		$("#email_Err").html("");
		$("#email_Err").addClass("hidden");
		$("#email_Corr").removeClass("hidden");
		$("#email").parent().parent().removeClass("has-error");
		$("#email").parent().parent().addClass("has-success");
	}

	acc=$("#acc").val();
	if(acc.trim().length<5){
		$("#acc_Err").html("<i class='fa fa-times'></i> <b>請輸入5位數以上之帳號</b>").removeClass("hidden");
		$("#acc_Corr").addClass("hidden");
		$("#acc").parent().parent().addClass("has-error");
	}else{
		$.ajax({
			 type:"POST",
			 url:"<%=request.getContextPath()%>/emp/EmpServlet.do",
			 data:{acc:acc, action:"acc_check"},
			 dataType:"text",
			 success:function (data){
				 if(data == "true"){
					 $("#acc_Err").html("");
					 $("#acc_Err").addClass("hidden");
					 $("#acc_Corr").removeClass("hidden");
					 $("#acc").parent().parent().removeClass("has-error");
					$("#acc").parent().parent().addClass("has-success");
				 }else{
					 $("#acc_Err").html("<i class='fa fa-times'></i> <b>此帳號已有人使用</b>").removeClass("hidden");
					 $("#acc_Corr").addClass("hidden");
					 $("#acc").parent().parent().addClass("has-error");
				 }
		     },
       		error:function(){alert("error")}
		});
	}
}
</script>