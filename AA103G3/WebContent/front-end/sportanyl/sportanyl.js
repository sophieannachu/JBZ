arrDate = new Array();
arrCal = new Array();
arrDistance = new Array();
arrDuration = new Array();
arrWDate = new Array();
arrWCal = new Array();
arrWDuration = new Array();
arrAllDate = new Array();
arrAllCal = new Array();
arrAllDuration = new Array();

function getData(data) {
	var j = 0;
	for (var i = (data.length - 1); i >= 0; i--) {
		arrDistance[j] = data[i].distance;
		arrCal[j] = data[i].cal;
		arrDuration[j] = data[i].duration / 60;
		arrDate[j] = data[i].date.toString();
		j++;
	}
}

function getDataWatch(data) {
	var m = 0;
	for (var i = (data.length - 1); i >= 0; i--) {
		arrWCal[m] = data[i].cal;
		arrWDuration[m] = data[i].duration / 60;
		arrWDate[m] = data[i].date.toString();
		m++;
	}
}

function getDataAll(data) {
	var n = 0;
	for (var i = (data.length - 1); i >= 0; i--) {
		arrAllCal[n] = data[i].cal;
		arrAllDuration[n] = data[i].duration / 60;
		arrAllDate[n] = data[i].date.toString();
		n++;
	}
}

$(function() {
	$("#tabs").tabs();
	$("#tab2").tabs();
	$("#tab3").tabs();
});

function distanceAll(date, distance) {
	if(date.length==0){
		 $('.distanceAll').html("還沒有運動紀錄，趕快使用APP運動一下!");
	}else{
	$('.distanceAll').highcharts(
			{
				chart : {
					type : 'column'
				},
				title : {
					text : 'GPS運動距離分析'
				},
				xAxis : [ {
					categories : date,
					crosshair : true
				} ],
				yAxis : {
					min : 0,
					title : {
						text : '公里(Km)'
					}
				},
				legend : {
					enabled : false
				},
				tooltip : {
					formatter : function() {
						return "<b style='font-weight:5px;'>" + this.x
								+ "</b><br>" + this.series.name + ":" + this.y
								+ "m";
					}
				},
				series : [ {
					name : '距離',
					data : distance
				} ]
			});
	}
}
function durationGPS(date, duration) {
	if(date.length==0){
		 $('.durationGPS').html("還沒有運動紀錄，趕快使用APP運動一下!");
	}else{
	$('.durationGPS').highcharts(
			{
				chart : {
					type : 'column'
				},
				title : {
					text : 'GPS運動時間統計'
				},
				xAxis : [ {
					categories : date,
					crosshair : true
				} ],
				yAxis : {
					min : 0,
					title : {
						text : '分(min)'
					}
				},
				legend : {
					enabled : false
				},
				tooltip : {
					formatter : function() {
						var min = Math.floor(this.y);
						return "<b style='font-weight:5px;'>" + this.x
								+ "</b><br>" + this.series.name + ":" + min
								+ "min";
					}
				},
				series : [ {
					name : '運動時間',
					color : '#6ba451',
					data : duration
				} ]
			});
	}
}

function durationWatch(date, duration) {
	if(date.length==0){
		 $('.durationWatch').html("還沒有運動紀錄，趕快使用APP運動一下!");
	}else{
	$('.durationWatch').highcharts(
			{
				chart : {
					type : 'column'
				},
				title : {
					text : '計時運動時間統計'
				},
				xAxis : [ {
					categories : date,
					crosshair : true
				} ],
				yAxis : {
					min : 0,
					title : {
						text : '分(min)'
					}
				},
				legend : {
					enabled : false
				},
				tooltip : {
					formatter : function() {
						var min = Math.floor(this.y);
						return "<b style='font-weight:5px;'>" + this.x
								+ "</b><br>" + this.series.name + ":" + min
								+ "min";
					}
				},
				series : [ {
					name : '運動時間',
					color : '#6ba451',
					data : duration
				} ]
			});
	}
}
function durationAll(date, duration) {
	if(date.length==0){
		 $('.durationAll').html("還沒有運動紀錄，趕快使用APP運動一下!");
	}else{
	$('.durationAll').highcharts(
			{
				chart : {
					type : 'column'
				},
				title : {
					text : '運動時間統計'
				},
				xAxis : [ {
					categories : date,
					crosshair : true
				} ],
				yAxis : {
					min : 0,
					title : {
						text : '分(min)'
					}
				},
				legend : {
					enabled : false
				},
				tooltip : {
					formatter : function() {
						var min = Math.floor(this.y);
						return "<b style='font-weight:5px;'>" + this.x
								+ "</b><br>" + this.series.name + ":" + min
								+ "min";
					}
				},
				series : [ {
					name : '運動時間',
					color : '#6ba451',
					data : duration
				} ]
			});
	}
}
function calGPS(date, cal) {
	if(date.length==0){
		 $('.calGPS').html("還沒有運動紀錄，趕快使用APP運動一下!");
	}else{
	$('.calGPS').highcharts(
			{
				chart : {
					type : 'column'
				},
				title : {
					text : 'GPS運動燃燒卡路里統計'
				},
				xAxis : [ {
					categories : date,
					crosshair : true
				} ],
				yAxis : {
					min : 0,
					title : {
						text : '大卡(Kcal)'
					}
				},
				legend : {
					enabled : false
				},
				tooltip : {
					formatter : function() {
						return "<b style='font-weight:5px;'>" + this.x
								+ "</b><br>" + this.series.name + ":" + this.y
								+ "Kcal";
					}
				},
				series : [ {
					name : '燃燒卡路里',
					color : '#f1b406',
					data : cal
				} ]
			});
	}
}

function calAll(date, cal) {
	if(date.length==0){
		 $('.calAll').html("還沒有運動紀錄，趕快使用APP運動一下!");
	}else{
	$('.calAll').highcharts(
			{
				chart : {
					type : 'column'
				},
				title : {
					text : '燃燒卡路里統計'
				},
				xAxis : [ {
					categories : date,
					crosshair : true
				} ],
				yAxis : {
					min : 0,
					title : {
						text : '大卡(Kcal)'
					}
				},
				legend : {
					enabled : false
				},
				tooltip : {
					formatter : function() {
						return "<b style='font-weight:5px;'>" + this.x
								+ "</b><br>" + this.series.name + ":" + this.y
								+ "Kcal";
					}
				},
				series : [ {
					name : '燃燒卡路里',
					color : '#f1b406',
					data : cal
				} ]
			});
	}
}

function calWatch(date, cal) {
	if(date.length==0){
		 $('.calWatch').html("還沒有運動紀錄，趕快使用APP運動一下!");
	}else{
	$('.calWatch').highcharts(
			{
				chart : {
					type : 'column'
				},
				title : {
					text : '計時運動燃燒卡路里統計'
				},
				xAxis : [ {
					categories : date,
					crosshair : true
				} ],
				yAxis : {
					min : 0,
					title : {
						text : '大卡(Kcal)'
					}
				},
				legend : {
					enabled : false
				},
				tooltip : {
					formatter : function() {
						return "<b style='font-weight:5px;'>" + this.x
								+ "</b><br>" + this.series.name + ":" + this.y
								+ "Kcal";
					}
				},
				series : [ {
					name : '燃燒卡路里',
					color : '#f1b406',
					data : cal
				} ]
			});
	}
}