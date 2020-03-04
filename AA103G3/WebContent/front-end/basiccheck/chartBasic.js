arrWeight= new Array();
arrWeightDate= new Array();
arrBmi= new Array();
arrBmiDate= new Array();
arrBmr= new Array();
arrBmrDate= new Array();
arrWaisyline= new Array();
arrWaisylineDate= new Array();
arrBfat= new Array();
arrBfatDate= new Array();

function getData(oJson){
	
	var i=0;
	var j=0;
	var m=0;
	var n=0;
	var x=0;
	var y=0;
	for(var i=(oJson.length-1);i>=0;i--){
		if(oJson[i].weight>0){
			 arrWeight[j]=oJson[i].weight;
		     arrWeightDate[j]=oJson[i].checkDate.toString();
		     j++;
		}
		if(oJson[i].bmi>0){
		     arrBmi[m]=oJson[i].bmi;
		     arrBmiDate[m]=oJson[i].checkDate.toString();
			 m++;
		}
		if(oJson[i].bmr>0){
		     arrBmr[n]=oJson[i].bmr;
		     arrBmrDate[n]=oJson[i].checkDate.toString();
			 n++;
		}
		if(oJson[i].waisyline>0){
		     arrWaisyline[x]=oJson[i].waisyline;
		     arrWaisylineDate[x]=oJson[i].checkDate.toString();
			 x++;
		}
		if(oJson[i].bfat>0){
		     arrBfat[y]=oJson[i].bfat;
		     arrBfatDate[y]=oJson[i].checkDate.toString();
		     y++;
		}
	 }
}

function highcharWeight(date,weight) {
	if(weight.length==0){
		 $('#tabs-1').html("還沒有健檢紀錄，新增一筆吧!");
	}else{
		 $('#tabs-1').highcharts({
		        title: {
		            text: '體重(WEIGHT)',
		            x: -20 //center
		        },
		        xAxis: {
		            categories:date
		        },
		        yAxis: {
		            title: {
		                text: '體重(Kg)'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        },
		        tooltip: {
		            valueSuffix: 'Kg'
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series: [{
		            name: '體重',
		            data: weight
		        }]
		    });
	}  
}
function highcharBmi(date,bmi) {
	if(date.length==0){
		 $('#tabs-2').html("還沒有健檢紀錄，新增一筆吧!");
	}else{
    $('#tabs-2').highcharts({
        title: {
            text: '身體質量指數(BMI)',
            x: -20 //center
        },
        xAxis: {
            categories:date
        },
        yAxis: {
            title: {
                text: ''
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: [{ // Light air
                from: 18.5,
                to: 24,
                color: 'rgba(68, 170, 213, 0.1)',
                label: {
                    text: '標準範圍',
                    style: {
                        color: '#606060'
                    }
                }
            }]
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'BMI',
            data: bmi
        }]
    });
	}
}
function highcharBmr(date,bmr) {
	if(date.length==0){
		 $('#tabs-3').html("還沒有健檢紀錄，新增一筆吧!");
	}else{
    $('#tabs-3').highcharts({
        title: {
            text: '基本代謝率(BMR)',
            x: -20 //center
        },
        xAxis: {
            categories:date
        },
        yAxis: {
            title: {
                text: '大卡(Kcal)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: 'Kcal'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'BMR',
            data: bmr
        }]
    });
	}
}
function highcharWaisyline(date,waisyline) {
	if(date.length==0){
		 $('#tabs-4').html("還沒有健檢紀錄，新增一筆吧!");
	}else{
    $('#tabs-4').highcharts({
        title: {
            text: '腰圍(WAISYLINE)',
            x: -20 //center
        },
        xAxis: {
            categories:date
        },
        yAxis: {
            title: {
                text: '公分(CM)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: 'CM'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '腰圍',
            data: waisyline
        }]
    });
	}
}
function highcharBfat(date,bfat) {
	if(date.length==0){
		 $('#tabs-5').html("還沒有健檢紀錄，新增一筆吧!");
	}else{
    $('#tabs-5').highcharts({
        title: {
            text: '體脂(FAT)',
            x: -20 //center
        },
        xAxis: {
            categories:date
        },
        yAxis: {
            title: {
                text: '(%)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '%'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '體脂',
            data: bfat
        }]
    });
	}
}




//更新視窗跳出
$( function() {
$( "#dialog-update" ).dialog({
  autoOpen: false,
  resizable: false,
  position: ["bottom",0],
  height: "auto",
  width: 800,
  modal: true,
  dialogClass: "dlg-no-close",
  close: function( event, ui ) {
	  $.ajax({
			 type:"POST",
			 url:"/AA103G3/front-end/basiccheck/whichPage1.jsp",
			 data:{currentPage:parseInt(currentPage),pageSize:5},
			 dataType:"text",
			 success:function (data){
				 document.getElementById("showPanel").innerHTML =data;
				 currentPage=document.getElementsByName("whichPage")[0].value;
		     },
             error:function(){alert("error")}
      })
   },
  buttons: {
    "取消": function() {
      $( this ).dialog( "close" );
    }
  }
});
} );

//查找單筆紀錄for更新
function update(event) {
       var basicCheckno = $(event).next().val();
       $.ajax({
			 type:"POST",
			 url:"/AA103G3/front-end/basiccheck/update_basicCheck_input.jsp",
			 data:{basicCheckno:basicCheckno},
			 dataType:"text",
			 success:function (data){
				 document.getElementById("dialog-update").innerHTML =data;
				 $("#datetimepicker3").datetimepicker(opt1);
		     },
          error:function(){alert("error")}
   		})
      
      $( "#dialog-update" ).dialog( "open" );
 }
//送出更新 
function saveupdate(event) {
    var basicCheckno = $("input[name='basicCheckpno1']").val();
    var checkDate = $("input[name='checkDate1']").val();
    var weight = $("input[name='weight1']").val();
    var bmi = $("input[name='bmi1']").val();
    var bmr = $("input[name='bmr1']").val();
    var waisyline = $("input[name='waisyline1']").val();
    var bFat = $("input[name='bFat1']").val();
    var memno = $("input[name='memno']").val();
    console.log(basicCheckno);
    $.ajax({
			 type:"POST",
			 url:"/AA103G3/basicCheck/basicCheck.do",
			 data:{basicCheckno:basicCheckno,
				 checkDate:checkDate,
				 weight:weight,
				 bmi:bmi,
				 bmr:bmr,
				 waisyline:waisyline,
				 bFat:bFat,
				 memno:memno,
				 action:"update"
			 },
			 dataType:"text",
			 success:function (data){
				 $( "#dialog-update" ).dialog( "close" );
				 swal("修改成功");	
		     },
       error:function(){alert("error")}
		})
		
}
//自檢錯誤驗證
function check(){
 var weight =document.getElementsByName("weight")[0];
 var waisyline =document.getElementsByName("waisyline")[0];
 var bFat = document.getElementsByName("bFat")[0];

	 var format = /^([1-9]|[1-9][0-9]|[1][0-9][0-9]|20[0])+(\.[0-9]{0,1})?$/;
 var submit = true;

 //體重請輸入數字
 if(weight.value!=""&& format.test(weight.value)==false){
	$(".weight").html("體重請輸入0-200");
   weight.focus();
   submit=false;
 }else{
	 $(".weight").html("");
 }
 //腰圍請輸入數字
 if(waisyline.value!=""&& format.test(waisyline.value)==false){
   $(".waisyline").html("腰圍請輸入0-200");
   waisyline.focus();
   submit=false;
 }else{
	 $(".waisyline").html("");
 }

 //體脂請輸入數字
 if(bFat.value!=""&&format.test(bFat.value)==false){
   $(".bFat").html("體脂請輸入0-200");
   bFat.focus();
   submit=false;
 }else{
	 $(".bFat").html("");
 }


 //體重腰圍體脂一定要選
 if(bFat.value==""&&waisyline.value==""&&weight.value==""){
	   swal("體重、腰圍、體脂請至少填一項");
	   weight.focus();
	   return;
 }
if(submit){
	 document.getElementById("basicCheck").submit();
}
}

 function init(){
   document.getElementById("btnSend").onclick=check;
 }

 window.onload=init;
//自檢更新錯誤驗證	 
 function check2(event){
	 var weight =document.getElementsByName("weight1")[0];
	 var waisyline =document.getElementsByName("waisyline1")[0];
	 var bFat = document.getElementsByName("bFat1")[0];

	 var format = /^([1-9]|[1-9][0-9]|[1][0-9][0-9]|20[0])+(\.[0-9]{0,1})?$/;
	 var submit = true;
	 //體重請輸入數字
	 if(weight.value!=""&& format.test(weight.value)==false){
		$(".weight1").html("體重請輸入0-200");
	   weight.focus();
	   submit=false;
	 }else{
		 $(".weight1").html("");
	 }
	 //腰圍請輸入數字
	 if(waisyline.value!=""&& format.test(waisyline.value)==false){
	   $(".waisyline1").html("腰圍請輸入0-200");
	   waisyline.focus();
	   submit=false;
	 }else{
		 $(".waisyline1").html("");
	 }

	 //體脂請輸入數字
	 if(bFat.value!=""&&format.test(bFat.value)==false){
	   $(".bFat1").html("體脂請輸入0-200");
	   bFat.focus();
	   submit=false;
	 }else{
		 $(".bFat1").html("");
	 }


	 //體重腰圍體脂一定要選
	 if(bFat.value==""&&waisyline.value==""&&weight.value==""){
		   swal("體重、腰圍、體脂請至少填一項");
		   weight.focus();
		   return;
	 }
	 
	 if(submit){
		 saveupdate(event);
	 }
}