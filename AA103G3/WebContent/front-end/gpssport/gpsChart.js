function init(){ 
	   arrayObject = new Array();
	   arrAlt= new Array();
	   arrSpeed = new Array();
	   arrDis = new Array();
	   var str="";
	   sportrec_no = document.getElementById("sportrec_no").value;
	   console.log(sportrec_no);
	   $.ajax({
			 type:"GET",
			 url:"/AA103G3/sport/getSportjson.do",
			 data:{sportrec_no:sportrec_no,action:"getLocation"},
			 dataType:"json",
			 success:function (data){
				 forData(data);
				 chartClimb(arrAlt,arrDis); 
				 chartPace(arrSpeed,arrDis);
				 newMap(data);
				 
		     },
           error:function(){alert("error")}
       })      
}
function forData(data){
	arrDis[0]=0;
	for(var i=0;i<data.length;i++){
		arrAlt[i]=data[i].alt;
		arrSpeed[i]=data[i].insSpeed;
		arrDis[i+1]=data[i].CumDistance;
	}
}

function newMap(data){
		   	var map = new google.maps.Map(document.getElementById('map'), {
				zoom: 0,
				center: {lat: 0, lng: 0},
				mapTypeId: google.maps.MapTypeId.TERRAIN
			});
			
			for(var i=0;i<data.length;i++){
				var LatLng = new Object();
				LatLng.lat = data[i].lat;
				LatLng.lng =  data[i].lng;
				arrayObject[i]=LatLng;
			}
			
			var flightPlanCoordinates = arrayObject;

			var flightPath = new google.maps.Polyline({
				path: flightPlanCoordinates,
				geodesic: true,
				strokeColor: '#FF0000',
				strokeOpacity: 1.0,
				strokeWeight: 2
			});
			
			var startLatlng = new google.maps.LatLng(arrayObject[0].lat,arrayObject[0].lng);
			var endLatlng = new google.maps.LatLng(arrayObject[arrayObject.length-1].lat,arrayObject[arrayObject.length-1].lng);
			var marker = new google.maps.Marker({
			    position: startLatlng,
			    title:"Start",
			    icon: 'http://www.google.com/mapfiles/ms/micons/red-dot.png'
			});
			
			var marker2 = new google.maps.Marker({
			    position: endLatlng,
			    title:"end",
			    icon: 'http://www.google.com/mapfiles/ms/micons/purple-dot.png'
			});
			
			marker.setMap(map);
			marker2.setMap(map);
			var bounds = new google.maps.LatLngBounds();
			for(var i = 0;i<arrayObject.length;i++){
				bounds.extend(new google.maps.LatLng(arrayObject[i].lat,arrayObject[i].lng));
			}
			
			map.fitBounds(bounds);
			flightPath.setMap(map);  
	   }


function chartClimb(alt,dis) {
	    $('#climb').highcharts({
	        chart: {
	            type: 'area'
	        },
	        title: {
	            text: ''
	        },
	        xAxis: {
	            allowDecimals: false,
	            labels: {
	                formatter: function () {
	                	var min = Math.floor(this.value/60);
	                	var sec = this.value%60;
	                    this.value = min+":"+sec;
	                	return this.value;
	                }
	            }
	        },
	        yAxis: {
	            title: {
	                text: ''
	            },
	            labels: {
	                formatter: function () {
	                    return this.value + 'm';
	                }
	            }
	        },
	        tooltip: {
	            formatter: function() {
	            	var min = Math.floor(this.x/60);
                	var sec = this.x%60;
                	var distance = dis[this.x];
                    this.x = min+":"+sec;
	                    return  "<b style='font-weight:5px;'>"+this.series.name +":"+ this.y+"</b>"+"m"+'<br>Time:'+this.x+
	                    '<br>Distance:'+distance;
	             }
	        },
	        series: [{
	            name: '海拔',
	            color: '#23a50c',
	            data: alt
	        }]
	    });
	}
function chartPace(speed,dis) {
    $('#pace').highcharts({
        chart: {
            type: 'area'
        },
        title: {
            text: ''
        },
        xAxis: {
            allowDecimals: false,
            labels: {
                formatter: function () {
                	var min = Math.floor(this.value/60);
                	var sec = this.value%60;
                    this.value = min+":"+sec;
                	return this.value;
                }
            }
        },
        yAxis: {
            title: {
                text: ''
            },
            labels: {
                formatter: function () {
                    return this.value + 'm';
                }
            }
        },
        tooltip: {
            formatter: function() {
            	var min = Math.floor(this.x/60);
            	var sec = this.x%60;
            	var distance = dis[this.x];
                this.x = min+":"+sec;
                    return  "<b style='font-weight:5px;'>"+this.series.name +":"+ this.y+"</b>"+"m"+'<br>Time:'+this.x+
                    '<br>Distance:'+distance;
              }
        },
        series: [{
            name: 'Pace',
            color: '#379bff',
            data: speed
        }]
    });
}
