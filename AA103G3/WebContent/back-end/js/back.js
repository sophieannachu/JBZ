function deleteFd(group_no){
	jQuery(document).ready(function(){
		var r = confirm("確定刪除嗎");
		if(r == true){
			window.location.href= "DelteFd.do?group_no="+group_no;
			
		}
		
	
	});
	
}