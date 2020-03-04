window.addEventListener("load",init,false);
function init(){
	currentPage=1;
	pageSize=5; 
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
       
}

// $(function() {
// 	currentPage = $("#page").value;
// 	console.log(currentPage);
// });

function deleteOne(item){
	   deleteCheck = item;
	   sleepCheckno = item.nextSibling.value;
	   swal({
		   title: '確定要刪除嗎?',
		   type: 'warning',
		   showCancelButton: true,
		   confirmButtonColor: '#3085d6',
		   cancelButtonColor: '#d33',
		   cancelButtonText:'取消',
		   confirmButtonText: '刪除成功!'
		 }).then(function() {
	   $.ajax({
			 type:"POST",
			 url:"/AA103G3/sleepCheck/sleepCheck.do",
			 data:{sleepCheckno:sleepCheckno,action:"delete_Sleep"},
			 dataType:"text",
			 success:function (data){
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
 error:function(){alert("error")}
})
swal(
	     '刪除成功!',
	     'success'
	   );
	 })

}
function pickPage(item){
	console.log(item);
	currentPage=item.previousSibling.value;
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
     
}

function getNextPage(){
				console.log(this);
			   $.ajax({
					 type:"GET",
					 url:"/AA103G3/front-end/sleepcheck/whichPage1.jsp",
					 data:{currentPage:parseInt(currentPage)+1,pageSize:5},
					 dataType:"text",
					 success:function (data){
						 document.getElementById("showPanel").innerHTML =data;
						 currentPage=document.getElementsByName("whichPage")[0].value;
				     },
		             error:function(){alert("error")}
	             })
	             
}
function getPreviousPage(){

	   $.ajax({
			 type:"GET",
			 url:"/AA103G3/front-end/sleepcheck/whichPage1.jsp",
			 data:{currentPage:parseInt(currentPage)-1,pageSize:5},
			 dataType:"text",
			 success:function (data){
				 document.getElementById("showPanel").innerHTML =data;
				 currentPage=document.getElementsByName("whichPage")[0].value;
		     },
          error:function(){alert("error")}
      })
}
function getFirstPage(){

	   $.ajax({
			 type:"GET",
			 url:"/AA103G3/front-end/sleepcheck/whichPage1.jsp",
			 data:{currentPage:"first",pageSize:5},
			 dataType:"text",
			 success:function (data){
				 document.getElementById("showPanel").innerHTML =data;
				 currentPage=document.getElementsByName("whichPage")[0].value;
		     },
       error:function(){alert("error")}
   })
}
function getLastPage(){

	   $.ajax({
			 type:"GET",
			 url:"/AA103G3/front-end/sleepcheck/whichPage1.jsp",
			 data:{currentPage:"last",pageSize:5},
			 dataType:"text",
			 success:function (data){
				 document.getElementById("showPanel").innerHTML =data;
				 currentPage=document.getElementsByName("whichPage")[0].value;
		     },
       error:function(){alert("error")}
   })
}