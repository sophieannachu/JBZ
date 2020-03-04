window.addEventListener("load",init,false);
function init(){
	currentPage=1;
	pageSize=5; 
	   $.ajax({
			 type:"GET",
			 url:"/AA103G3/front-end/basiccheck/whichPage1.jsp",
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
	   basicCheckno = item.nextSibling.value;
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
					 url:"/AA103G3/basicCheck/basicCheck.do",
					 data:{basicCheckno:basicCheckno,action:"delete"},
					 dataType:"text",
					 success:function (data){
						   $.ajax({
								 type:"GET",
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
		 error:function(){alert("error")}
		})
		   swal(
		     '刪除成功!',
		     'success'
		   );
		 })
}
function pickPage(item){
	
	currentPage=item.previousSibling.value;
	console.log(currentPage);
   $.ajax({
		 type:"GET",
		 url:"/AA103G3/front-end/basiccheck/whichPage1.jsp",
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
			   $.ajax({
					 type:"GET",
					 url:"/AA103G3/front-end/basiccheck/whichPage1.jsp",
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
			 url:"/AA103G3/front-end/basiccheck/whichPage1.jsp",
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
			 url:"/AA103G3/front-end/basiccheck/whichPage1.jsp",
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
			 url:"/AA103G3/front-end/basiccheck/whichPage1.jsp",
			 data:{currentPage:"last",pageSize:5},
			 dataType:"text",
			 success:function (data){
				 document.getElementById("showPanel").innerHTML =data;
				 currentPage=document.getElementsByName("whichPage")[0].value;
		     },
       error:function(){alert("error")}
   })
}