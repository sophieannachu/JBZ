$(function() {
	$("#tabs").tabs();
});

// create the chart
function distanceAll(distance) {
	$('.distance').highcharts('StockChart', {
		chart : {
			width : 775,
			shadow : true
		},

		rangeSelector : {
			selected : 1
		},

		title : {
			text : '運動距離統計'
		},

		series : [ {
			type : 'column',
			color : '#f1b406',
			name : '距離 (Km)',
			data : distance,
			dataGrouping : {
				units : [ [ 'week', // unit name
				[ 1 ] // allowed multiples
				], [ 'month', [ 1, 2, 3, 4, 6 ] ] ]
			}
		} ]
	});
}
function durationAll(duration) {
	$('.duration').highcharts(
			'StockChart',
			{
				chart : {
					width : 775,
					shadow : true
				},

				rangeSelector : {
					selected : 1
				},

				title : {
					text : '運動時間統計'
				},
				series : [ {
					type : 'column',
					color : '#8fd3d6',
					name : '時間 ',
					data : duration,
					dataGrouping : {
						units : [ [ 'week', // unit name
						[ 1 ] // allowed multiples
						], [ 'month', [ 1, 2, 3, 4, 6 ] ] ]
					},
					tooltip : {
						formatter : function() {
							var min = Math.floor(this.y);
							return "<b style='font-weight:5px;'>" + this.x
									+ "</b><br>" + this.series.name + ":" + min
									+ "min";
						}
					}
				} ],
			});
}

function calAll(cal) {
	console.log(cal);
	$('.cal').highcharts('StockChart', {
		chart : {
			width : 775,
			shadow : true
		},

		rangeSelector : {
			selected : 1
		},

		title : {
			text : '消耗卡路里統計'
		},
		series : [ {
			type : 'column',
			color : '#f04615',
			name : '大卡(Kcal) ',
			data : cal,
			dataGrouping : {
				units : [ [ 'week', // unit name
				[ 1 ] // allowed multiples
				], [ 'month', [ 1, 2, 3, 4, 6 ] ] ]
			}
		} ],
	});
}

function calTake(caltake) {
	console.log(caltake);
	$('.caltake').highcharts('StockChart', {
		chart : {
			width : 775,
			shadow : true
		},

		rangeSelector : {
			selected : 1
		},

		title : {
			text : '卡路里攝取統計'
		},
		series : [ {
			type : 'column',
			name : '大卡(Kcal) ',
			color : '#86d872',
			data : caltake,
			dataGrouping : {
				units : [ [ 'week', // unit name
				[ 1 ] // allowed multiples
				], [ 'month', [ 1, 2, 3, 4, 6 ] ] ]
			}
		} ],
	});
}
arrComCal = new Array();
arrComCalTake = new Array();
arrComDistance = new Array();
arrComDuration = new Array();

function getData(data) {
	var j = 0;
	for (var i = (data.length - 1); i >= 0; i--) {
		arrDistance = new Array();
		arrDistance[0] = data[i].date;
		arrDistance[1] = Math.floor(data[i].distance / 1000);
		arrComDistance[j] = arrDistance;
		j++;
	}
}

function getAllData(data) {
	var j = 0;
	for (var i = (data.length - 1); i >= 0; i--) {
		arrDuration = new Array();
		arrDuration[0] = data[i].date;
		arrDuration[1] = data[i].duration;
		arrComDuration[j] = arrDuration;

		arrCal = new Array();
		arrCal[0] = data[i].date;
		arrCal[1] = data[i].cal;
		arrComCal[j] = arrCal;
		j++;
	}
}

function getCalData(data) {
	var j = 0;
	for (var i = (data.length - 1); i >= 0; i--) {
		arrCaltake = new Array();
		arrCaltake[0] = data[i].date;
		arrCaltake[1] = data[i].cal;
		arrComCalTake[j] = arrCaltake;
		j++;
	}
}

//For更新大頭貼
$(function() {
	$(".uploadphoto").css("opacity", "0");
	$(".uploadshow").css("background-color", "rgba(0,0,0,0.5)");
	$(".uploadshow").css("opacity", "0");

	$(".uploadshow").hover(function() {
		$(".uploadphoto").css("opacity", "1");
		$(this).css("opacity", "1");
	}, function() {
		$(".uploadphoto").css("opacity", "0");
		$(this).css("opacity", "0");
	})
})

$(function() {
	uploadmodal = document.getElementById('photoModal');
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("closePhoto")[0];
	// When the user clicks the button, open the modal

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		uploadmodal.style.display = "none";
		preview.src="";
	}
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == uploadmodal) {
			uploadmodal.style.display = "none";
			preview.src="";
			
		}
	}
})
function uploadphoto() {
	uploadmodal.style.display = "block";
}

//上傳照片End

//Search Bar
function search() {
	var query = $(".searchbar").val();
	$
			.ajax({
				type : "post",
				url : "/AA103G3/mem/memjson.do",
				data : {
					action : "getMemByKey",
					query : query
				},
				dataType : "json",
				success : function(data) {
					var str = "";
					for (var i = 0; i < data.length; i++) {
						var src = "/AA103G3/personal/personal.do?memno="
								+ data[i].memno;
						var href = "/AA103G3/front-end/personalPage.jsp?memno="
								+ data[i].memno;
						var name = data[i].name;
						str += '<a href="' + href + '"><li><img src="' + src
								+ '">' + name + '</li></a>'
					}
					$(".friFind").html(str);
				},
				error : function(data) {
					console.log(data);
					$(".friFind").html("");
				}
			})

	if (keyCode == 40) {

	}
}

//Search Bar End

function comfirmFri(event, memno, frino) {
	$.ajax({
		type : "POST",
		url : "/AA103G3/friend/friend.do",
		data : {
			memno : memno,
			frino : frino,
			action : "update_for_status"
		},
		dataType : "text",
		success : function(data) {
			console.log($(event).parents(".friendrequest"));
			$(event).parents(".friendrequest").css("display", "none");
		},
		error : function() {
			alert("error")
		}
	});
}
function cancleFri(event, memno, frino) {
	$.ajax({
		type : "POST",
		url : "/AA103G3/friend/friend.do",
		data : {
			memno : memno,
			frino : frino,
			action : "cancle_for_friend"
		},
		dataType : "text",
		success : function(data) {
			console.log($(event).parents(".friendrequest"));
			$(event).parents(".friendrequest").css("display", "none");
		},
		error : function() {
			alert("error")
		}
	});
}

function deleteFri(event, frino, memno) {
	$.ajax({
		type : "POST",
		url : "/AA103G3/friend/friend.do",
		data : {
			memno : memno,
			frino : frino,
			action : "delete_for_friend"
		},
		dataType : "text",
		success : function(data) {
			window.location.reload();
		},
		error : function() {
			alert("error")
		}
	});
}
//上傳照片裁切
$(function() {
	doFirst();
	$('#preview').imgAreaSelect({
		aspectRatio : '1:1',
		onSelectChange : preview,
		onSelectEnd : preview
	});

	function preview(image, selection) {

		$('input[name="x1"]').val(selection.x1);
		$('input[name="y1"]').val(selection.y1);
		$('input[name="x2"]').val(selection.x2);
		$('input[name="y2"]').val(selection.y2);
	}

})

var file;
var fileReader;
var photo;
var preview;
var hide_img;
var naturalwidth;
var height;
var scale;
var x1;
var x2;
var y1;
var y2;

function doFirst() {

	photo = document.getElementById('photo');
	photo.onchange = fileChange;
	preview = document.getElementById('preview');
	// hide_img= document.getElementsByClassName('hide_img')[0];

}
var image1 = new Image();
function fileChange(e) {

	file = e.srcElement.files[0];

	fileReader = new FileReader();
	fileReader.readAsDataURL(file);

	fileReader.onload = function() {
		preview.src = fileReader.result;
		naturalwidth = preview.naturalWidth;
		if(naturalwidth<400){
			$("#preview").css("width",naturalwidth);
		}else{
			scale = naturalwidth / 400;
			height = preview.naturalHeight/scale;
			if(height>400){
				x1=0;
				x2=400;
				y1=height/2-200;
				y2=height/2+200;
			}else{
				x1=200-(height/2);
				x2=200+(height/2);
				y1=0;
				y2=height;
			}
		}
		$('#preview').imgAreaSelect({ x1: x1, y1: y1, x2: x2, y2: y2 });
	};
	
	

}

function cptUpdate(event, memno) {
	$.ajax({
		type : "POST",
		url : "/AA103G3/mem/MemServlet.do",
		data : {
			memno : memno,
			action : "update_for_cpt",
			cpt : event.value
		},
		dataType : "text",
		success : function(data) {
			window.location.reload();

		},
		error : function() {
			alert("error")
		}
	});
}

