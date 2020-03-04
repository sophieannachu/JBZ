arrAC= new Array();
arrACDate= new Array();
arrPC= new Array();
arrPCDate= new Array();
arrAH= new Array();
arrAHDate= new Array();


//Data處理
function getData(oJson){
	
	var i=0;
	var j=0;
	var m=0;
	var n=0;

	for(var i=(oJson.length-1);i>=0;i--){
		if(oJson[i].bgBfMeat>0){
			 arrAC[j]=oJson[i].bgBfMeat;
		     arrACDate[j]=oJson[i].checkDate.toString();
		     j++;
		}
		if(oJson[i].bgAfMeat>0){
		     arrPC[m]=oJson[i].bgAfMeat;
		     arrPCDate[m]=oJson[i].checkDate.toString();
			 m++;
		}
		if(oJson[i].bgBfSleep>0){
		     arrAH[n]=oJson[i].bgBfSleep;
		     arrAHDate[n]=oJson[i].checkDate.toString();
			 n++;
		}
	 }

}
//飯前血糖分析圖
function highcharAC(date,AC) {
    $('#tabs-1').highcharts({
        title: {
            text: '飯前血糖值(Glugose,AH)',
            x: -20 //center
        },
        xAxis: {
            categories:date
        },
        yAxis: {
            title: {
                text: '血糖值(mg/dl)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: [{ // Light air
                from: 100,
                to: 200,
                color: '#fcdcdc',
                label: {
                    text: '過高',
                    style: {
                        color: '#ff0000'
                    }
                }
            }]
        },
        tooltip: {
            valueSuffix: 'mg/dl'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '血糖值',
            data: AC
        }]
    });
}
//飯後血糖分析圖
function highcharPC(date,PC) {
    $('#tabs-2').highcharts({
        title: {
            text: '飯後2小血糖值(PC)',
            x: -20 //center
        },
        xAxis: {
            categories:date
        },
        yAxis: {
            title: {
                text: '血糖值(mg/dl)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: [{ // Light air
                from: 140,
                to: 300,
                color: '#fcdcdc',
                label: {
                    text: '過高',
                    style: {
                        color: '#ff0000'
                    }
                }
            }]
        },
        tooltip: {
            valueSuffix: 'mg/dl'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '血糖值',
            data: PC
        }]
    });
}

//稅前血糖分析圖
function highcharAH(date,AH) {
    $('#tabs-3').highcharts({
        title: {
            text: '睡前血糖值(AH)',
            x: -20 //center
        },
        xAxis: {
            categories:date
        },
        yAxis: {
            title: {
                text: '血糖值(mg/dl)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: [{ // Light air
                from: 100,
                to: 200,
                color: '#fcdcdc',
                label: {
                    text: '過高',
                    style: {
                        color: '#ff0000'
                    }
                }
            }]
        },
        tooltip: {
            valueSuffix: 'mg/dl'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '血糖值',
            data: AH
        }]
    });
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
			 url:"/AA103G3/front-end/bgcheck/whichPage1.jsp",
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
       var bgCheckno = $(event).next().val();
       $.ajax({
			 type:"GET",
			 url:"/AA103G3/front-end/bgcheck/update_bgCheck_input.jsp",
			 data:{bgCheckno:bgCheckno},
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
    var bgCheckno = $("input[name='bgCheckno1']").val();
    var checkDate = $("input[name='checkDate1']").val();
    var bgBfMeat = $("input[name='bgBfMeat1']").val();
    var bgAfMeat = $("input[name='bgAfMeat1']").val();
    var bgBfSleep = $("input[name='bgBfSleep1']").val();
    var memno = $("input[name='memno']").val();
    $.ajax({
			 type:"POST",
			 url:"/AA103G3/bgCheck/bgCheck.do",
			 data:{bgCheckno:bgCheckno,
				 checkDate:checkDate,
				 bgBfMeat:bgBfMeat,
				 bgAfMeat:bgAfMeat,
				 bgBfSleep:bgBfSleep,
				 memno:memno,
				 action:"update_BG"
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
	 var bgBfMeat =document.getElementsByName("bgBfMeat")[0];
	 var bgAfMeat =document.getElementsByName("bgAfMeat")[0];
	 var bgBfSleep = document.getElementsByName("bgBfSleep")[0];

	 var format = /^([1-9]|[1-9][0-9]|[1-2][0-9][0-9]|30[0])?$/;
	 var submit = true;
	 //體重請輸入數字
	 if(bgBfMeat.value!=""&& format.test(bgBfMeat.value)==false){
		$(".bgBfMeat").html("血糖請輸入數字");
		bgBfMeat.focus();
	   submit=false;
	 }else{
		 $(".bgBfMeat").html("");
	 }
	 //腰圍請輸入數字
	 if(bgAfMeat.value!=""&& format.test(bgAfMeat.value)==false){
	   $(".bgAfMeat").html("血糖請輸入數字");
	   bgAfMeat.focus();
	   submit=false;
	 }else{
		 $(".bgAfMeat").html("");
	 }

	 //體脂請輸入數字
	 if(bgBfSleep.value!=""&&format.test(bgBfSleep.value)==false){
	   $(".bgBfSleep").html("血糖請輸入數字");
	   bgBfSleep.focus();
	   submit=false;
	 }else{
		 $(".bgBfSleep").html("");
	 }


	 //體重腰圍體脂一定要選
	 if(bgBfMeat.value==""&&bgAfMeat.value==""&&bgBfSleep.value==""){
		   swal("請至少填一項");
		   bgBfMeat.focus();
		   return;
	 }
	 
if(submit){
	 document.getElementById("bgCheck").submit();
}
}

 function init(){
   document.getElementById("btnSend").onclick=check;
 }

 window.onload=init;
function check2(event){
	 var bgBfMeat =document.getElementsByName("bgBfMeat1")[0];
	 var bgAfMeat =document.getElementsByName("bgAfMeat1")[0];
	 var bgBfSleep = document.getElementsByName("bgBfSleep1")[0];

	 var format = /^([1-9]|[1-9][0-9]|[1-2][0-9][0-9]|30[0])?$/;
	 var submit = true;
	 //體重請輸入數字
	 if(bgBfMeat.value!=""&& format.test(bgBfMeat.value)==false){
		$(".bgBfMeat1").html("血糖請輸入數字");
		bgBfMeat.focus();
	   submit=false;
	 }else{
		 $(".bgBfMeat1").html("");
	 }
	 //腰圍請輸入數字
	 if(bgAfMeat.value!=""&& format.test(bgAfMeat.value)==false){
	   $(".bgAfMeat1").html("血糖請輸入數字");
	   bgAfMeat.focus();
	   submit=false;
	 }else{
		 $(".bgAfMeat1").html("");
	 }

	 //體脂請輸入數字
	 if(bgBfSleep.value!=""&&format.test(bgBfSleep.value)==false){
	   $(".bgBfSleep1").html("血糖請輸入數字");
	   bgBfSleep.focus();
	   submit=false;
	 }else{
		 $(".bgBfSleep1").html("");
	 }


	 //體重腰圍體脂一定要選
	 if(bgBfMeat.value==""&&bgAfMeat.value==""&&bgBfSleep.value==""){
		   alert("請至少填一項");
		   bgBfMeat.focus();
		   return;
	 }
	 
	 if(submit){
		 saveupdate(event);
	 }
}