arrWakeDate= new Array();
arrSleepTime= new Array();

function getData(oJson){
	
	var i=0;
	var j=0;
	for(var i=0;i<oJson.length;i++){
		if(oJson[i].sleepTime!=0){
			 arrSleepTime[j]=Math.round(oJson[i].sleepTime/60*10)/10;
			 arrWakeDate[j]=oJson[i].wakeDate.toString();
		     j++;
		}
	 }
}

function highcharSleep(date,sleepTime) {
    $('#tabs-1').highcharts({
        title: {
            text: '睡眠紀錄(SLEEP)',
            x: -20 //center
        },
        xAxis: {
            categories:date
        },
        yAxis: {
            title: {
                text: '小時(HR)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: 'HR'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '睡眠時間',
            data: sleepTime
        }]
    });
}

//FOR計算sleepTime
function calculate(event){
	var bedTime =$("input[name='bedTime']").val();
	var wakeTime =$("input[name='wakeTime']").val();
	
	if(bedTime!=""&&wakeTime!=""){
		var bedTimeYear=bedTime.trim().split(" ")[0].split("-")[0];
		var bedTimeMM=bedTime.trim().split(" ")[0].split("-")[1];
		var bedTimeDD=bedTime.trim().split(" ")[0].split("-")[2];
		var bedTimeHH=bedTime.trim().split(" ")[1].split(":")[0];
		var bedTimeMi=bedTime.trim().split(" ")[1].split(":")[1];
		var wakeTimeYear=wakeTime.trim().split(" ")[0].split("-")[0];
		var wakeTimeMM=wakeTime.trim().split(" ")[0].split("-")[1];
		var wakeTimeDD=wakeTime.trim().split(" ")[0].split("-")[2];
		var wakeTimeHH=wakeTime.trim().split(" ")[1].split(":")[0];
		var wakeTimeMi=wakeTime.trim().split(" ")[1].split(":")[1];
		var Date_A = new Date(bedTimeYear,bedTimeMM,bedTimeDD,bedTimeHH,bedTimeMi,0);  
		var Date_B = new Date(wakeTimeYear,wakeTimeMM,wakeTimeDD,wakeTimeHH,wakeTimeMi,0);   
		var Date_C = new Date(Date_B - Date_A);

		var sleepTime=Math.floor(Date_C.getTime() / 3600000) + "小時 " + 
				Date_C.getUTCMinutes() + "分 " ;
		$("input[name='sleepTime']").val(sleepTime);
	}else{
		$("input[name='sleepTime']").val("");
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
			 type:"GET",
			 url:"/AA103G3/front-end/sleepcheck/whichPage1.jsp",
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
     var sleepCheckno = $(event).next().val();
     $.ajax({
			 type:"GET",
			 url:"/AA103G3/front-end/sleepcheck/update_sleepCheck_input.jsp",
			 data:{sleepCheckno:sleepCheckno},
			 dataType:"text",
			 success:function (data){
				 document.getElementById("dialog-update").innerHTML =data;
				 $("#datetimepicker3").datetimepicker(opt1);
				 $("#datetimepicker4").datetimepicker(opt1);
		     },
        error:function(){alert("error")}
 		})
    
    $( "#dialog-update" ).dialog( "open" );
}
//送出更新 
function saveupdate(event) {
  var bedTime = $("input[name='bedTime1']").val();
  var wakeTime = $("input[name='wakeTime1']").val();
  var sleepTime = $("input[name='sleepTime1']").val();
  var sleepCheckno = $("input[name='sleepCheckno1']").val();
  var memno = $("input[name='memno']").val();
  $.ajax({
			 type:"POST",
			 url:"/AA103G3/sleepCheck/sleepCheck.do",
			 data:{sleepCheckno:sleepCheckno,
				 bedTime:bedTime,
				 wakeTime:wakeTime,
				 sleepTime:sleepTime,
				 memno:memno,
				 action:"update_Sleep"
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
	 var bedTime =document.getElementsByName("bedTime")[0];

	 var submit = true;
	 //收縮壓請輸入數字
	 if(bedTime.value==""){
		   $(".bedTime").html("請輸入就寢時間");
		   bedTime.focus();
		   submit=false;
		 }else{
			 $(".bedTime").html("");		 
		 }
	 
if(submit){
	 document.getElementById("sleepCheck").submit();
}
}

function init(){
 document.getElementById("btnSend").onclick=check;
}

window.onload=init;

function check2(event){
	var bedTime =document.getElementsByName("bedTime1")[0];

	 var submit = true;
	 //收縮壓請輸入數字
	 if(bedTime.value==""){
		   $(".bedTime1").html("請輸入就寢時間");
		   bedTime.focus();
		   submit=false;
		 }else{
			 $(".bedTime1").html("");		 
		 }
	 
	 if(submit){
		 saveupdate(event);
	 }
}