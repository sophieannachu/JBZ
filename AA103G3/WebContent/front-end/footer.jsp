<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<body>
		
				<div class="container-fluid footer">
					<div class="container">
						<div class="row"><!--row-->
							<div class="col-xs-12 col-sm-6">
							<h2>COPYRIGHT</h2>
							<div style="float:left;margin-top:-15px;padding-right: 8px;">
							<img src="<%=request.getContextPath()%>/front-end/image/logowhite.png" width="80px;" >
							</div>
							<p>呷百利股份有限公司</p>
							<p>地　　址：803 中壢市中大路300號資策會</p>
							<p>客服信箱：nml4564@gmail.com</p>
							
								
							</div>
							<div class="col-xs-12 col-sm-6">
							<h2>QUICKLINK</h2>
							<div>
								<div style="margin-top:-10px;">
								<div class="row">
									<div class="col-xs-12 col-sm-6" style="padding-right: 0;">
										<ul class="downmenu clearfix">
											<li>
												
												<a href="" class="hover">聯絡我們</a>
											</li>
											<li>
												<a href="" class="hover">APP</a>
											</li>
											<li>
												<a href="" class="hover">健康分析</a>
											</li>
											<li>
												<a href="" class="hover">健康資訊</a>
											</li>
											<li>
												<a href="" class="hover">登入註冊</a>
											</li>
										</ul>
									</div>
									<div class="col-xs-12 col-sm-6" style="text-align:left;">
										<img src="<%=request.getContextPath()%>/front-end/image/jbz_qrcode.jpg" width="80px;" >
									</div>
								</div>
								</div>
							</div>
							</div>
						</div>
					</div>
					  	
					<div class="copy">
						<h5 style="margin:0;color: #fff;">Copyrightc2016 呷百利JIABAZE All Rights Reserved</h5>
					</div>	
				</div>
<script>
(function () {
	$("body").append("<img id='goTopButton' style='display: none; z-index: 5; cursor: pointer;' title='回到頂端'/>");
	var img = "<%=request.getContextPath()%>/front-end/image/btn_pt.png",
//		locatioin = 0, // 按鈕出現在螢幕的高度
	right = 0, // 距離右邊 px 值
	opacity = 0.6, // 透明度
	speed = 500, // 捲動速度
	$button = $("#goTopButton"),
	$body = $(document),
	$win = $(window);
	$button.attr("src", img);

	$button.on({
		mouseover: function() {$button.css("opacity", 1);},
		mouseout: function() {$button.css("opacity", opacity);},
		click: function() {$("html, body").animate({scrollTop: 0}, speed);}
	});

	window.goTopMove = function () {
		var scrollH = $body.scrollTop(),
		winH = $win.height(),
		winW = $win.width(),
		css = {"bottom": 0 + "px", "position": "fixed", "right": winW * right, "opacity": opacity};
		if(scrollH > 20) {
		$button.css(css);
		$button.fadeIn("slow");
		} else {
		$button.fadeOut("slow");
		}
	};

	$win.on({
		scroll: function() {goTopMove();},
		resize: function() {goTopMove();}
		});
})();
</script>
	</body>