arrSpressure= new Array();
arrSPDate= new Array();
arrDpressure= new Array();


function getData(oJson){
	
	var i=0;
	var j=0;
	var m=0;
	for(var i=0;i<oJson.length;i++){
		if(oJson[i].weight!=0){
			 arrSpressure[j]=oJson[i].sPressure;
			 arrSPDate[j]=oJson[i].checkDate.toString();
		     j++;
		}
		if(oJson[i].bmi!=0){
			 arrDpressure[m]=oJson[i].dPressure;
		     arrSPDate[m]=oJson[i].checkDate.toString();
			 m++;
		}
	 }
}

function highcharPressure(date,sPressure,dPressure) {
    $('#tabs-1').highcharts({
        title: {
            text: '血壓紀錄(BLOOD PRESSURE)',
            x: -20 //center
        },
        xAxis: {
            categories:date
        },
        yAxis: {
            title: {
                text: '血壓值(mmHg)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            plotBands: [{ // Light air
                from: 60,
                to: 90,
                color: '#d0ffff',
                label: {
                    text: '',
                    style: {
                        color: '#ff0000'
                    }
                }
            },{ // Light air
                from: 100,
                to: 140,
                color: '#d0ffff',
                label: {
                    text: '',
                    style: {
                        color: '#ff0000'
                    }
                }
            }]
        },
        tooltip: {
            valueSuffix: 'mmHg'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '收縮壓',
            data: sPressure
        },
        {
            name: '舒張壓',
            data: dPressure
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
			 url:"/AA103G3/front-end/bpcheck/whichPage1.jsp",
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
       var bpCheckno = $(event).next().val();
       $.ajax({
			 type:"GET",
			 url:"/AA103G3/front-end/bpcheck/update_bpCheck_input.jsp",
			 data:{bpCheckno:bpCheckno},
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
    var bpCheckno = $("input[name='bpCheckno1']").val();
    var checkDate = $("input[name='checkDate1']").val();
    var sPressure = $("input[name='sPressure1']").val();
    var dPressure = $("input[name='dPressure1']").val();
    var memno = $("input[name='memno']").val();
    $.ajax({
			 type:"POST",
			 url:"/AA103G3/bpCheck/bpCheck.do",
			 data:{bpCheckno:bpCheckno,
				 checkDate:checkDate,
				 sPressure:sPressure,
				 dPressure:dPressure,
				 memno:memno,
				 action:"update_BP"
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
	 var sPressure =document.getElementsByName("sPressure")[0];
	 var dPressure =document.getElementsByName("dPressure")[0];

	 var format = /^([1-9]|[1-9][0-9]|[1-2][0-9][0-9]|30[0])?$/;
	 var submit = true;
	 //收縮壓請輸入數字
	 if(sPressure.value!=""&& format.test(sPressure.value)==false){
		   $(".sPressure").html("血壓請輸入0-300");
		   sPressure.focus();
		   submit=false;
		 }else if(sPressure.value==""){
			   $(".sPressure").html("請填寫收縮壓");
			   sPressure.focus();
			   submit=false;
		 }else{
			 $(".sPressure").html("");		 
		 }
	 //舒張壓請輸入數字
	 if(dPressure.value!=""&& format.test(dPressure.value)==false){
	   $(".dPressure").html("血壓請輸入0-300");
	   dPressure.focus();
	   submit=false;
	 }else if(dPressure.value==""){
		   $(".dPressure").html("請填寫舒張壓");
		   dPressure.focus();
		   submit=false;
	 }else{
		 $(".dPressure").html("");		 
	 }
	 
if(submit){
	 document.getElementById("bpCheck").submit();
}
}

 function init(){
   document.getElementById("btnSend").onclick=check;
 }

 window.onload=init;
 
function check2(event){
	var sPressure =document.getElementsByName("sPressure1")[0];
	 var dPressure =document.getElementsByName("dPressure1")[0];

	 var format = /^([1-9]|[1-9][0-9]|[1-2][0-9][0-9]|30[0])?$/;
	 var submit = true;
	 //收縮壓請輸入數字
	 if(sPressure.value!=""&& format.test(sPressure.value)==false){
		   $(".sPressure1").html("血壓請輸入0-300");
		   sPressure.focus();
		   submit=false;
		 }else if(sPressure.value==""){
			   $(".sPressure1").html("請填寫收縮壓");
			   sPressure.focus();
			   submit=false;
		 }else{
			 $(".sPressure1").html("");		 
		 }
	 //舒張壓請輸入數字
	 if(dPressure.value!=""&& format.test(dPressure.value)==false){
	   $(".dPressure1").html("血壓請輸入0-300");
	   dPressure.focus();
	   submit=false;
	 }else if(dPressure.value==""){
		   $(".dPressure1").html("請填寫舒張壓");
		   dPressure.focus();
		   submit=false;
	 }else{
		 $(".dPressure1").html("");		 
	 }
	 
	 console.log(submit);
	 if(submit){
		 saveupdate(event);
	 }
}