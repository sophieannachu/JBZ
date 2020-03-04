function deleteFd(group_no,getContextPath,requestURL){
	console.log(getContextPath);
	jQuery(document).ready(function(){
		var r = confirm("確定刪除嗎");
		if(r == true){
			window.location.href= getContextPath+"/group_info/DelteFd.do?" +
					"group_no="+group_no+"&requestURL="+requestURL;
			
		}
		
	
	});
	
}