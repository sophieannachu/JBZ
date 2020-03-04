

$(function() {
	
	$(window).load(function() {
		$(window).bind('scroll resize', function() {
			var $this = $(this);
			var $this_Top = $this.scrollTop();

			// 當高度小於100時，關閉區塊
			if ($this_Top > 320) {

				$(".hidden1").addClass("fixed1");
				$(".fixed1").removeClass("hidden1");

			}

			if ($this_Top < 320) {

				$(".fixed1").addClass("hidden1");
			}
			$(".hidden1").removeClass("fixed1");

		}).scroll();

	});
});

function checkGroupOut(){
	$("#chatMain").css("bottom","-98%");
}
function checkGroupIn(){
	$("#chatMain").css("bottom","0");
}
$(document).on("click","#chatmenu",function() {
	
//	$("#chatMain").css("bottom","-98%");
//	  $("#chatMain").toggle(function (){
//		  
//	  });
	  console.log("ok");
	  
	 });
$(document).on("click","#chatMain2",function() {
	
//	$("#chatMain").css("bottom","0");
//	  $("#chatMain").toggle(function (){
//		  
//	  });
	console.log("ok");
	
});
$(document).on("click","#chatContentMen span img",function() {
	$(this).parent().parent().remove();
	
});
