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
							<p>�E�ʧQ�ѥ��������q</p>
							<p>�a�@�@�}�G803 ���c�����j��300���굦�|</p>
							<p>�ȪA�H�c�Gnml4564@gmail.com</p>
							
								
							</div>
							<div class="col-xs-12 col-sm-6">
							<h2>QUICKLINK</h2>
							<div>
								<div style="margin-top:-10px;">
								<div class="row">
									<div class="col-xs-12 col-sm-6" style="padding-right: 0;">
										<ul class="downmenu clearfix">
											<li>
												
												<a href="" class="hover">�p���ڭ�</a>
											</li>
											<li>
												<a href="" class="hover">APP</a>
											</li>
											<li>
												<a href="" class="hover">���d���R</a>
											</li>
											<li>
												<a href="" class="hover">���d��T</a>
											</li>
											<li>
												<a href="" class="hover">�n�J���U</a>
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
						<h5 style="margin:0;color: #fff;">Copyrightc2016 �E�ʧQJIABAZE All Rights Reserved</h5>
					</div>	
				</div>
<script>
(function () {
	$("body").append("<img id='goTopButton' style='display: none; z-index: 5; cursor: pointer;' title='�^�쳻��'/>");
	var img = "<%=request.getContextPath()%>/front-end/image/btn_pt.png",
//		locatioin = 0, // ���s�X�{�b�ù�������
	right = 0, // �Z���k�� px ��
	opacity = 0.6, // �z����
	speed = 500, // ���ʳt��
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