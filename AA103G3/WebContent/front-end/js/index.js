 jQuery(document).ready(function( $ ) {
        $('.counter').counterUp({
            delay: 10,
            time: 5000
        });
    });
	$(document).ready(function() {
		$(".hover").mouseenter(function() {
			$(this).fadeTo(500,0.7);
		});
		$(".hover").mouseleave(function() {
			$(this).fadeTo(500,1);
		});
		$(".health").mouseenter(function() {
			$(this).fadeTo(500,0.7);
		});
		$(".health").mouseleave(function() {
			$(this).fadeTo(500,1);
		});
		// $(".menuhover").mouseenter(function() {
		// 	$(this).css("font-size","20px");
		// });
		// $(".menuhover").mouseleave(function() {
		// 	$(this).css("font-size","18px");
		// });
		$(".hamburger-menu").click(function() {
			if($(".hamburger-menu").hasClass("hamburger-menu-close")){
				$(".navigatorxs").addClass("open");
			 	$(".menuss").removeClass("hidden");
			 	$(this).addClass("hamburger-menu-open");
			 	$(this).removeClass("hamburger-menu-close");
			}else{
				 $(".navigatorxs").removeClass("open");
			 $(".menuss").addClass("hidden");
			 $(this).removeClass("hamburger-menu-open");
			  $(this).addClass("hamburger-menu-close");
			}	 
		});
	});