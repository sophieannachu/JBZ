<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<script>
$(document).ready(function() {
        $("#datePicker").datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true,
			todayHighlight : true
		});
		
		$("#pwd").blur(function(){
			var pwd=$("#pwd").val();
			if(pwd.trim().length<5){
				$("#pwd_Err").html("<i class='fa fa-times'></i><b>請輸入5位數以上之密碼</b>").removeClass("hidden");
				$("#pwd_Corr").addClass("hidden");
				$("#pwd").parent().parent().addClass("has-error");
			}else{
				$("#pwd_Err").html("");
				$("#pwd_Err").addClass("hidden");
				$("#pwd_Corr").removeClass("hidden");
				$("#pwd").parent().parent().removeClass("has-error");
				$("#pwd").parent().parent().addClass("has-success");
			}
		});
		
		$("#pwd2").blur(function(){
			var pwd=$("#pwd").val();
			var pwd2=$("#pwd2").val();
			if(pwd2.trim().length<=0){
				$("#pwd2_Err").html("<i class='fa fa-times'></i><b>請輸入確認密碼</b>").removeClass("hidden");
				$("#pwd2_Corr").addClass("hidden");
				$("#pwd2").parent().parent().addClass("has-error");
			}else if(pwd != pwd2){
				$("#pwd2_Err").html("<i class='fa fa-times'></i><b>確認密碼不符</b>").removeClass("hidden");
				$("#pwd2_Corr").addClass("hidden");
				$("#pwd2").parent().parent().addClass("has-error");
			}else{
				$("#pwd2_Err").html("");
				$("#pwd2_Err").addClass("hidden");
				$("#pwd2_Corr").removeClass("hidden");
				$("#pwd2").parent().parent().removeClass("has-error");
				$("#pwd2").parent().parent().addClass("has-success");
			}
		});
		
		$("#name").blur(function(){
			var name=$("#name").val();
			if(name.trim().length<=0){
				$("#name_Err").html("<i class='fa fa-times'></i><b>請輸入姓名</b>").removeClass("hidden");
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
		
		$("#id").blur(function(){
			var id=$("#id").val();
			if(id.trim().length<=0){
				$("#id_Err").html("<i class='fa fa-times'></i><b>請輸入暱稱</b>").removeClass("hidden");
				$("#id_Corr").addClass("hidden");
				$("#id").parent().parent().addClass("has-error");
			}else{
				$("#id_Err").html("");
				$("#id_Err").addClass("hidden");
				$("#id_Corr").removeClass("hidden");
				$("#id").parent().parent().removeClass("has-error");
				$("#id").parent().parent().addClass("has-success");
			}
		});
		
		$("input[name=sex]").blur(function(){
			var sex=$("input[name=sex]:checked").val();
			if(sex===undefined || sex.length<=0){
				$("#sex_Err").html("<i class='fa fa-times'></i><b>請選擇性別</b>").removeClass("hidden");
				$("#sex_Corr").addClass("hidden");
			}else{
				$("#sex_Err").html("");
				$("#sex_Err").addClass("hidden");
				$("#sex_Corr").removeClass("hidden");
			}
		});
		
		$("#bir").change(function(){
			var bir=$("#bir").val();
			console.log(bir);
			if(bir.length<=0){
				$("#bir_Err").html("<i class='fa fa-times'></i><b>請選擇生日</b>").removeClass("hidden");
				$("#bir_Corr").addClass("hidden");
				$("#bir").parent().parent().parent().addClass("has-error");
			}else{
				$("#bir_Err").html("");
				$("#bir_Err").addClass("hidden");
				$("#bir_Corr").removeClass("hidden");
				$("#bir").parent().parent().parent().removeClass("has-error");
				$("#bir").parent().parent().parent().addClass("has-success");
			}
		});
		
		$("#phone").blur(function(){
			var phone=$("#phone").val();
			if(phone.trim().length<=0){
				$("#phone_Err").html("<i class='fa fa-times'></i><b>請輸入手機</b>").removeClass("hidden");
				$("#phone_Corr").addClass("hidden");
				$("#phone").parent().parent().addClass("has-error");
			}else if(phone.length<10){
				$("#phone_Err").html("<i class='fa fa-times'></i><b>請重新輸入手機</b>").removeClass("hidden");
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
				$("#email_Err").html("<i class='fa fa-times'></i><b>請輸入信箱</b>").removeClass("hidden");
				$("#email_Corr").addClass("hidden");
				$("#email").parent().parent().addClass("has-error");
			}else if(email.indexOf('@')==-1 || email.indexOf('.')==-1){
				$("#email_Err").html("<i class='fa fa-times'></i><b>請重新輸入信箱</b>").removeClass("hidden");
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
		
		$("select[name=act_type]").blur(function(){
			var act_type=$("select[name=act_type] option:selected").val();
			if(act_type===undefined || act_type.length<=0){
				$("#act_type_Err").html("<i class='fa fa-times'></i><b>請選擇活動類型</b>").removeClass("hidden");
				$("#act_type_Corr").addClass("hidden");
				$("select[name=act_type]").parent().parent().addClass("has-error");
			}else{
				$("#act_type_Err").html("");
				$("#act_type_Err").addClass("hidden");
				$("#act_type_Corr").removeClass("hidden");
				$("select[name=act_type]").parent().parent().removeClass("has-error");
				$("select[name=act_type]").parent().parent().addClass("has-success");
			}
		});
		
		$("#height").blur(function(){
			var height=$("#height").val();
			var format = /^([1-9]|[1-9][0-9]|[1][0-9][0-9]|25[0])+(\.[0-9]{0,1})?$/;
			if(height.trim().length<=0){
				$("#height_Err").html("<i class='fa fa-times'></i><b>請輸入身高</b>").removeClass("hidden");
				$("#height_Corr").addClass("hidden");
				$("#height").parent().parent().addClass("has-error");
			}else if(!format.test(height)){
				$("#height_Err").html("<i class='fa fa-times'></i><b>請重新輸入身高 (1-250)</b>").removeClass("hidden");
				$("#height_Corr").addClass("hidden");
				$("#height").parent().parent().addClass("has-error");
			}else{
				$("#height_Err").html("");
				$("#height_Err").addClass("hidden");
				$("#height_Corr").removeClass("hidden");
				$("#height").parent().parent().removeClass("has-error");
				$("#height").parent().parent().addClass("has-success");
			}
		});
		
		$("#weight").blur(function(){
			var weight=$("#weight").val();
			var format = /^([1-9]|[1-9][0-9]|[1][0-9][0-9]|25[0])+(\.[0-9]{0,1})?$/;
			if(weight.trim().length<=0){
				$("#weight_Err").html("<i class='fa fa-times'></i><b>請輸入體重</b>").removeClass("hidden");
				$("#weight_Corr").addClass("hidden");
				$("#weight").parent().parent().addClass("has-error");
			}else if(!format.test(weight)){
				$("#weight_Err").html("<i class='fa fa-times'></i><b>請重新輸入體重 (1-250)</b>").removeClass("hidden");
				$("#weight_Corr").addClass("hidden");
				$("#weight").parent().parent().addClass("has-error");
			}else{
				$("#weight_Err").html("");
				$("#weight_Err").addClass("hidden");
				$("#weight_Corr").removeClass("hidden");
				$("#weight").parent().parent().removeClass("has-error");
				$("#weight").parent().parent().addClass("has-success");
			}
		});
		
		$("#submit_register").click(function(e){
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
			
			var pwd=$("#pwd_Err").html();
			var pwd2=$("#pwd2_Err").html();
			if(pwd.trim().length != 0){
				errCount++;
				$("#pwd_Err").removeClass("hidden");
				$("#pwd").parent().parent().addClass("has-error");
			}else{
				$("#pwd_Err").addClass("hidden");
			}
			if(pwd2.trim().length != 0){
				errCount++;
				$("#pwd2_Err").removeClass("hidden");
				$("#pwd2").parent().parent().addClass("has-error");
			}else{
				$("#pwd2_Err").addClass("hidden");
			}
			
			var name=$("#name_Err").html();
			if(name.trim().length != 0){
				errCount++;
				$("#name_Err").removeClass("hidden");
				$("#name").parent().parent().addClass("has-error");
			}else{
				$("#name_Err").addClass("hidden");
			}
			
			var id=$("#id_Err").html();
			if(id.trim().length != 0){
				errCount++;
				$("#id_Err").removeClass("hidden");
				$("#id").parent().parent().addClass("has-error");
			}else{
				$("#id_Err").addClass("hidden");
			}
			
			var sex=$("#sex_Err").html();
			if(sex.trim().length != 0){
				errCount++;
				$("#sex_Err").removeClass("hidden");
			}else{
				$("#sex_Err").addClass("hidden");
			}
			
			var bir=$("#bir_Err").html();
			if(bir.trim().length != 0){
				errCount++;
				$("#bir_Err").removeClass("hidden");
				$("#bir").parent().parent().parent().addClass("has-error");
			}else{
				$("#bir_Err").addClass("hidden");
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
			
			var act_type=$("#act_type_Err").html();
			if(act_type.trim().length != 0){
				errCount++;
				$("#act_type_Err").removeClass("hidden");
				$("select[name=act_type]").parent().parent().addClass("has-error");
			}else{
				$("#act_type_Err").addClass("hidden");
			}
			
			var height=$("#height_Err").html();
			if(height.trim().length != 0){
				errCount++;
				$("#height_Err").removeClass("hidden");
				$("#height").parent().parent().addClass("has-error");
			}else{
				$("#height_Err").addClass("hidden");
			}
			
			var weight=$("#weight_Err").html();
			if(weight.trim().length != 0){
				errCount++;
				$("#weight_Err").removeClass("hidden");
				$("#weight").parent().parent().addClass("has-error");
			}else{
				$("#weight_Err").addClass("hidden");
			}
			if(errCount!=0){
				return false;
			}else{
				$("form").submit();
			}
		});
		
// 		$("#acc").change(acc_check);
// 		$("#bir").on("blur", function(e) {
// 			var bir=$("#bir").val();
// 			console.log(bir);
// 			if(bir.length<=0){
// 				$("#bir_Err").html("<b>請選擇生日</b>").removeClass("hidden");
// 			}else{
// 				$("#bir_Err").html("");
// 				$("#bir_Err").addClass("hidden");
// 			}
// 		});
		
	});
	
	function acc_check(){
		var acc=$("#acc").val();
		if(acc.trim().length<5){
			$("#acc_Err").html("<i class='fa fa-times'></i><b>請輸入5位數以上之帳號</b>").removeClass("hidden");
			$("#acc").parent().parent().addClass("has-error");
			$("#acc_Corr").addClass("hidden");
		}else{
			$.ajax({
				 type:"POST",
				 url:"<%=request.getContextPath()%>/mem/MemServlet.do",
				 data:{acc:acc,
					 action:"acc_check"
				 },
				 dataType:"text",
				 success:function (data){
					 if(data == "true"){
						 $("#acc_Err").html("");
						 $("#acc_Err").addClass("hidden");
						 $("#acc_Corr").removeClass("hidden");
						 $("#acc").parent().parent().removeClass("has-error");
						 $("#acc").parent().parent().addClass("has-success");
					 }else{
						 $("#acc_Err").html("<i class='fa fa-times'></i><b>此帳號已有人使用</b>").removeClass("hidden");
						$("#acc").parent().parent().addClass("has-error");
						 $("#acc_Corr").addClass("hidden");
					 }
			     },
	       		error:function(){alert("error")}
			});
		}
		
	}
	function magic1(){
		var acc="aa103g3", pwd="aa103g3", pwd2="aa103g3", name="吳小志", id="吳神", bir="1999-05-05", phone="0915123789", email="JBZ05@gmail.com", height="180", weight="80";
		$("#male").attr("checked", true);
		$("#low").attr("selected", true);
		$("#acc").val(acc);
		$("#pwd").val(pwd);
		$("#pwd2").val(pwd2);
		$("#name").val(name);
		$("#id").val(id);
		$("#bir").val(bir);
		$("#phone").val(phone);
		$("#email").val(email);
		$("#height").val(height);
		$("#weight").val(weight);
		
		pwd=$("#pwd").val();
		if(pwd.trim().length<5){
			$("#pwd_Err").html("<b>請輸入5位數以上之密碼</b>").removeClass("hidden");
			$("#pwd_Corr").addClass("hidden");
			$("#pwd").parent().parent().addClass("has-error");
		}else{
			$("#pwd_Err").html("");
			$("#pwd_Err").addClass("hidden");
			$("#pwd_Corr").removeClass("hidden");
			$("#pwd").parent().parent().removeClass("has-error");
			$("#pwd").parent().parent().addClass("has-success");
		}
	
		pwd2=$("#pwd2").val();
		if(pwd2.trim().length<=0){
			$("#pwd2_Err").html("<b>請輸入確認密碼</b>").removeClass("hidden");
			$("#pwd2_Corr").addClass("hidden");
			$("#pwd2").parent().parent().addClass("has-error");
		}else if(pwd != pwd2){
			$("#pwd2_Err").html("<b>確認密碼不符</b>").removeClass("hidden");
			$("#pwd2_Corr").addClass("hidden");
			$("#pwd2").parent().parent().addClass("has-error");
		}else{
			$("#pwd2_Err").html("");
			$("#pwd2_Err").addClass("hidden");
			$("#pwd2_Corr").removeClass("hidden");
			$("#pwd2").parent().parent().removeClass("has-error");
			$("#pwd2").parent().parent().addClass("has-success");
		}
	
		name=$("#name").val();
		if(name.trim().length<=0){
			$("#name_Err").html("<b>請輸入姓名</b>").removeClass("hidden");
			$("#name_Corr").addClass("hidden");
			$("#name").parent().parent().addClass("has-error");
		}else{
			$("#name_Err").html("");
			$("#name_Err").addClass("hidden");
			$("#name_Corr").removeClass("hidden");
			$("#name").parent().parent().removeClass("has-error");
			$("#name").parent().parent().addClass("has-success");
		}
	
		id=$("#id").val();
		if(id.trim().length<=0){
			$("#id_Err").html("<b>請輸入暱稱</b>").removeClass("hidden");
			$("#id_Corr").addClass("hidden");
			$("#id").parent().parent().addClass("has-error");
		}else{
			$("#id_Err").html("");
			$("#id_Err").addClass("hidden");
			$("#id_Corr").removeClass("hidden");
			$("#id").parent().parent().removeClass("has-error");
			$("#id").parent().parent().addClass("has-success");
		}
	
		sex=$("input[name=sex]:checked").val();
		if(sex===undefined || sex.length<=0){
			$("#sex_Err").html("<b>請選擇性別</b>").removeClass("hidden");
			$("#sex_Corr").addClass("hidden");
		}else{
			$("#sex_Err").html("");
			$("#sex_Err").addClass("hidden");
			$("#sex_Corr").removeClass("hidden");
		}
	
		bir=$("#bir").val();
		console.log(bir);
		if(bir.length<=0){
			$("#bir_Err").html("<b>請選擇生日</b>").removeClass("hidden");
			$("#bir_Corr").addClass("hidden");
			$("#bir").parent().parent().parent().addClass("has-error");
		}else{
			$("#bir_Err").html("");
			$("#bir_Err").addClass("hidden");
			$("#bir_Corr").removeClass("hidden");
			$("#bir").parent().parent().parent().removeClass("has-error");
			$("#bir").parent().parent().parent().addClass("has-success");
		}
	
		phone=$("#phone").val();
		if(phone.trim().length<=0){
			$("#phone_Err").html("<b>請輸入手機</b>").removeClass("hidden");
			$("#phone_Corr").addClass("hidden");
			$("#phone").parent().parent().addClass("has-error");
		}else if(phone.length<10){
			$("#phone_Err").html("<b>請重新輸入手機</b>").removeClass("hidden");
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
			$("#email_Err").html("<b>請輸入信箱</b>").removeClass("hidden");
			$("#email_Corr").addClass("hidden");
			$("#email").parent().parent().addClass("has-error");
		}else if(email.indexOf('@')==-1 || email.indexOf('.')==-1){
			$("#email_Err").html("<b>請重新輸入信箱</b>").removeClass("hidden");
			$("#email_Corr").addClass("hidden");
			$("#email").parent().parent().addClass("has-error");
		}else{
			$("#email_Err").html("");
			$("#email_Err").addClass("hidden");
			$("#email_Corr").removeClass("hidden");
			$("#email").parent().parent().removeClass("has-error");
			$("#email").parent().parent().addClass("has-success");
		}
	
		act_type=$("select[name=act_type] option:selected").val();
		if(act_type===undefined || act_type.length<=0){
			$("#act_type_Err").html("<b>請選擇活動類型</b>").removeClass("hidden");
			$("#act_type_Corr").addClass("hidden");
			$("select[name=act_type]").parent().parent().addClass("has-error");
		}else{
			$("#act_type_Err").html("");
			$("#act_type_Err").addClass("hidden");
			$("#act_type_Corr").removeClass("hidden");
			$("select[name=act_type]").parent().parent().removeClass("has-error");
			$("select[name=act_type]").parent().parent().addClass("has-success");
		}
	
		height=$("#height").val();
		var format = /^([1-9]|[1-9][0-9]|[1][0-9][0-9]|25[0])+(\.[0-9]{0,1})?$/;
		if(height.trim().length<=0){
			$("#height_Err").html("<b>請輸入身高</b>").removeClass("hidden");
			$("#height_Corr").addClass("hidden");
			$("#height").parent().parent().addClass("has-error");
		}else if(!format.test(height)){
			$("#height_Err").html("<b>請重新輸入身高 (1-250)</b>").removeClass("hidden");
			$("#height_Corr").addClass("hidden");
			$("#height").parent().parent().addClass("has-error");
		}else{
			$("#height_Err").html("");
			$("#height_Err").addClass("hidden");
			$("#height_Corr").removeClass("hidden");
			$("#height").parent().parent().removeClass("has-error");
			$("#height").parent().parent().addClass("has-success");
		}
	
		weight=$("#weight").val();
		var format = /^([1-9]|[1-9][0-9]|[1][0-9][0-9]|25[0])+(\.[0-9]{0,1})?$/;
		if(weight.trim().length<=0){
			$("#weight_Err").html("<b>請輸入體重</b>").removeClass("hidden");
			$("#weight_Corr").addClass("hidden");
			$("#weight").parent().parent().addClass("has-error");
		}else if(!format.test(weight)){
			$("#weight_Err").html("<b>請重新輸入體重 (1-250)</b>").removeClass("hidden");
			$("#weight_Corr").addClass("hidden");
			$("#weight").parent().parent().addClass("has-error");
		}else{
			$("#weight_Err").html("");
			$("#weight_Err").addClass("hidden");
			$("#weight_Corr").removeClass("hidden");
			$("#weight").parent().parent().removeClass("has-error");
			$("#weight").parent().parent().addClass("has-success");
		}
		
		acc=$("#acc").val();
		if(acc.trim().length<5){
			$("#acc_Err").html("<b>請輸入5位數以上之帳號</b>").removeClass("hidden");
			$("#acc_Corr").addClass("hidden");
			$("#acc").parent().parent().addClass("has-error");
		}else{
			$.ajax({
				 type:"POST",
				 url:"<%=request.getContextPath()%>/mem/MemServlet.do",
				 data:{acc:acc,
					 action:"acc_check"
				 },
				 dataType:"text",
				 success:function (data){
					 if(data == "true"){
						 $("#acc_Err").html("");
						 $("#acc_Err").addClass("hidden");
						 $("#acc_Corr").removeClass("hidden");
						 $("#acc").parent().parent().removeClass("has-error");
						$("#acc").parent().parent().addClass("has-success");
					 }else{
						 $("#acc_Err").html("<b>此帳號已有人使用</b>").removeClass("hidden");
						 $("#acc_Corr").addClass("hidden");
						 $("#acc").parent().parent().addClass("has-error");
					 }
			     },
	       		error:function(){alert("error")}
			});
		}
	}
</script>