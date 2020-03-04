<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.sport.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="sportSvc" scope="page" class="com.sport.model.SportService" />
<jsp:useBean id="pedometerSvc" scope="page" class="com.pedometer.model.PedometerService" />
<jsp:useBean id="groupInfoSvc" scope="page" class="com.groupinfo.model.GroupInfoService" />

<%
	Sport sport = sportSvc.getAllSum();
	pageContext.setAttribute("sport", sport);
	Integer total_pedo = pedometerSvc.getSum();
	pageContext.setAttribute("total_pedo", total_pedo);
	Integer total_group = groupInfoSvc.getCountGroup();
	pageContext.setAttribute("total_group", total_group);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>JBZ呷百利</title>
	<!-- <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script> -->
	<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/sweetalert2/4.1.8/sweetalert2.min.js"></script> -->
	<!-- <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/sweetalert2/4.1.8/sweetalert2.min.css"> -->
<link href="https://fonts.googleapis.com/css?family=Hind+Guntur" rel="stylesheet">
<script src="<%=request.getContextPath()%>/front-end/js/jquery_1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/waypoint.js"></script> 
<script src="<%=request.getContextPath()%>/front-end/js/jquery.counterup.min.js"></script> 
<script src="<%=request.getContextPath()%>/front-end/js/index.js"></script> 
	<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
<link href="<%=request.getContextPath()%>/front-end/css/index.css" rel="stylesheet">

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
			<![endif]-->
</head>
<body>
				<!-- header設定，連同滿版背景圖 -->
				<div class="container-fluid mainHeader" id="header">
					<div class="row">
						<div class="navigatorxs visible-xs">
							<!-- 			<div class="navigator"> -->
							<!-- <div class="nav"> -->
							<!-- menu的背景 -->
							
							<div class="navxs visible-xs">
								<!-- logo for PC-->

								<!-- logo for mobile -->
								<div class="visible-xs">
									<div class="col-xs-4 col-sm-4 logo visible-xs">
										<img src="<%=request.getContextPath()%>/front-end/image/logo.png" style="width:40px;">
									</div>
									<!-- menu for mobile -->
									
									<div class="col-xs-8 col-sm-8 visible-xs">
										<div class="menu-xs">
											<div class="hamburger-menu hamburger-menu-close">
												<p>Menu</p>
												<div class="hamburger-icon-open">
													<img src="<%=request.getContextPath()%>/front-end/image/menu-alt-512.png">
												</div>
												<div class="hamburger-icon-close hidden">
													<img src="<%=request.getContextPath()%>/front-end/image/x-icon-.png">
												</div>
											</div>
										</div>
									</div>
									<div class="col-xs-12 hidden menuss">
										<a href="">關於我們</a>
									</div>
									<div class="col-xs-12 hidden menuss">
										<a href="">APP</a>
									</div>
									<div class="col-xs-12 hidden menuss">
										<a href="">健康分析</a>
									</div>
									<div class="col-xs-12 hidden menuss">
										<a href="">健康資訊</a>
									</div>
									<div class="col-xs-12 hidden menuss">
										<a href="<%=request.getContextPath()%>/front-end/newLogin.jsp">登入</a>
									</div>
								</div>
							</div>
						</div>
						<div class="navigatorsm">
								<!-- menu for PC -->
							<div class="navsm hidden-xs hidden-xs">
								<div class="navBack hidden-xs">
								</div>

								<div class="col-xs-6 col-sm-6 logo hidden-xs">
									<a href="<%=request.getContextPath()%>/front-end/index.jsp">
										<img src="<%=request.getContextPath()%>/front-end/image/logo.png">
										<img src="<%=request.getContextPath()%>/front-end/image/slogan.png" style="height:35px;width: auto;margin-top: 40px;margin-left:5px;">
									</a>
								</div>
								<div class="col-xs-6 col-sm-6 hidden-xs"style="border 1px red;">
									<div class="menu">
										<ul>
											<li>
												<a href="" class="menuhover">關於我們</a>
											
											</li>  
											<li>
												<a href="" class="menuhover">APP</a>
											</li>  
											<li>
												<a href="" class="menuhover">健康分析</a>
											</li> 
											<li>
												<a href="" class="menuhover">健康資訊</a>
											</li> 
											<li>
												<a href="<%=request.getContextPath()%>/front-end/newLogin.jsp" class="menuhover">登入</a>
											</li> 
										</ul>
									</div>
								</div>
							</div>
							<!-- 		</div> -->
							<!-- 				</div> -->
						</div>
						<div class="container-fluid header">
							<div class="row" >
								<div class="col-xs-12 col-sm-12" style="padding: 0px;">
									<!-- 滿版圖、slogan -->
									<div class="moduel">
										<div class=container>						
											<div class="col-xs-12 col-sm-7">	
											</div>	
											<div class="col-xs-12 col-sm-5 right">
												<h1>活到老，動到老</h1>
												<p>JBZ是一套健康管理的系統，</p>
												<p>利用JBZ來記錄中老年人的運動及飲食習慣，</p>
												<p>並且我們希望利用社群的力量來達到健康管理的目的。 </p>
													<a href="<%=request.getContextPath()%>/front-end/mem/register_mem.jsp" class="hover">
														<div class="signup">馬上註冊</div>						
													</a>
											</div>
										</div>
									</div>
								</div>					
							</div>
						</div>
					</div>
				</div>
				
				<!-- 產品特色介紹 -->
				<div class="container info">
					<div class="row">
					<div class="col-xs-12 col-sm-6" style="text-align: center;margin-top: 150px; margin-bottom: 100px;">
						<h2>JIABAZE 隨時隨地追蹤您的健康</h2>
						<p>我們的網頁與行動 App 能自動同步，您的日記可隨時維持最新。</p>

					</div>
					<div class="col-xs-12 col-sm-6" style="text-align: center;margin-top: 40px;">
						<img src="<%=request.getContextPath()%>/front-end/image/phone_1.png" width="200px">

					</div>
					</div>
				</div>


				<div class="container main">
						<div class="row">
							<div class="col-xs-12 col-sm-12 allanyl">
								<h1>全體會員統計</h1>
								<div class="col-xs-6 col-sm-3">
									<img src="<%=request.getContextPath()%>/front-end/image/Running_icon.png">
									<span class="counter">${total_pedo }</span>
									<div>累積步數</div> 
								</div>
								<div class="col-xs-6 col-sm-3">
									<img src="<%=request.getContextPath()%>/front-end/image/stretching-exercises.png">
									<span class="counter">${sport.sport_duration}</span>
									<div>累積運動時數</div> 
								</div>
								<div class="col-xs-6 col-sm-3">
									<img src="<%=request.getContextPath()%>/front-end/image/fast-food.png">
									<span class="counter">${sport.sport_cal }</span>
									<div>累積消耗卡路里</div> 
								</div>
								<div class="col-xs-6 col-sm-3">
									<img src="<%=request.getContextPath()%>/front-end/image/users-icon.png">
									<span class="counter">${total_group}</span>
									<div>累積揪團數</div> 
								</div>
							</div>
							<!-- <div class="row"> -->
						</div>
				</div>
				<!-- 健康達人 -->
				<div class="container main">
						<div class="row">
							<div class="col-xs-12 col-sm-12 healthInfo">
								<h1>名人推薦</h1>
								<p>Join us.</P>
								<p>EVERYone can BE a MASTER For Health</p>
							</div>
								<div class="col-xs-6 col-sm-3 health" >
									<img src="<%=request.getContextPath()%>/front-end/image/5.jpg" width="381" height="497">
									<p id="health1" style="background-color: 	#09715A;">讓JBZ健康系統成為你最貼身值得信賴的夥伴！ </p>	
								</div>
								<div class="col-xs-6 col-sm-3 health" >
									<img src="<%=request.getContextPath()%>/front-end/image/6.jpg" width="381" height="497">
									<p id="health2" style="background-color: 	#E38C00;">只要每天的一點點小記錄，讓我運動的更有成就感！ </p>
								</div>
								<div class="col-xs-6 col-sm-3 health" >
									<img src="<%=request.getContextPath()%>/front-end/image/8.jpg" width="381" height="497">
									<p id="health3" style="background-color: 	#0087A6;">運動不只是可以自己做，還可以與朋友們一起活動，讓健康維持得更愉快！</p>	
								</div>
								<div class="col-xs-6 col-sm-3 health" >
									<img src="<%=request.getContextPath()%>/front-end/image/9.jpg" width="381" height="497">
									<p id="health4" style="background-color: 	#DC574E;">推薦我父母使用這系統後，他們更知道健康維護的重要，也越來越年輕了！</p>	
								</div>
						</div>
				</div>
				<!-- 健康資訊牆 -->
				<div class="container newsWall" style="margin-bottom: 80px;">
					<div class="row">
						<h1>最新健康新知</h1>	
						<hr style="height: 1px; background-color:#ddd;" >
					</div>
					<!-- for PC -->
					<div class="row hidden-xs">					
							<div class="col-xs-6 col-sm-3 news" >
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=70601" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/coffee.jpg">
									<p>馬偕醫院營養師趙強指出，喝完咖啡之後45～50分鐘，血中咖啡因濃度最高，效能約可維持3～4個小時。
									</p>
								</a>

							</div>
							<div class="col-xs-6 col-sm-3 news " >
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=65324" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/幸福.jpg">
									<p>不是年輕夫妻才要談性說愛， 只要用對方法，資深熟男熟女也能熱情如火。孩子都生了、長大成人，老夫老妻還要有性生活嗎？</p>
								</a>
							</div>
					
							<div class="col-xs-6 col-sm-3 news" >
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=68003" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/old.jpg">
									<p>日本聖加路醫院精神科醫師保阪隆稱50歲為「老後的入口」，50歲的修行決定老後是幸福或不幸的老人。</p>
								</a>
							</div>
							<div class="col-xs-6 col-sm-3 news" >
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=65307" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/葡萄安.jpg">
									<p>號稱對骨頭好的保健食品滿坑滿谷，有吃的錠劑、也有喝的，有的低鈉、有的低糖……，令人頭昏眼花，而穿白袍的銷售人員其實未必是藥師，又叫你買這不買那，究竟該怎麼辦才好？</p>
								</a>
							</div>
					</div>
					<div class="row  hidden-xs">
							<div class="col-xs-6 col-sm-3 news ">
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=67447" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/人參.jpg">
									<p>高齡105歲的香港企業家邵逸夫身體硬朗，在90歲前都堅持每天上班，直到102歲才正式退休，他長壽的養生祕訣就是每天含一片野生人參。</p>
								</a>
							</div>
							<div class="col-xs-6 col-sm-3 news">
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=63922" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/鮪魚肚.jpg">
									<p>中年男人不想受到肥胖困擾，專家們一致建議控制熱量、均衡飲食、外食時慎選菜餚及保持規律的運動，聽起來似乎是老生常談，但確實是不二法門。</p>
								</a>
							</div>
							<div class="col-xs-6 col-sm-3 news" >
								<a href="http://www.commonhealth.com.tw/blog/blogTopic.action?nid=1537" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/失智.jpg">
									<p>先前曾提到五種植物精油有助腦部記憶、認知功能，除了使用嗅聞法外，經常飲用香草茶或入菜也是有助益，英國紐堡大學研究發現香蜂草，迷迭香及鼠尾草有助記憶提升哦！</p>
								</a>
							</div>
							<div class="col-xs-6 col-sm-3 news " >
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=70026" class="hover">

									<img src="<%=request.getContextPath()%>/front-end/image/運動.jpg">
									<p>小張熱愛跑步，一週團練3天，過年前，連續參加兩場國際馬拉松，結束後感冒了一個多月，吃不下也睡不好，精神變得很差。他看了醫生，做了很多檢查，病情卻無明顯改善。</p>
								</a>
							</div>
					</div>
					<!-- for mobile -->
					<div class="row mobile visible-xs">
						<div class="row">					
							<div class="col-xs-6 col-sm-3 news" >
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=70601" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/coffee.jpg">
									<p>馬偕醫院營養師趙強指出，喝完咖啡之後45～50分鐘，血中咖啡因濃度最高，效能約可維持3～4個小時。
									</p>
								</a>

							</div>
							<div class="col-xs-6 col-sm-3 news " >
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=65324" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/幸福.jpg">
									<p>不是年輕夫妻才要談性說愛， 只要用對方法，資深熟男熟女也能熱情如火。孩子都生了、長大成人，老夫老妻還要有性生活嗎？</p>
								</a>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-3 news">
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=68003" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/old.jpg">
									<p>日本聖加路醫院精神科醫師保阪隆稱50歲為「老後的入口」，50歲的修行決定老後是幸福或不幸的老人。</p>
								</a>
							</div>
							<div class="col-xs-6 col-sm-3 news" >
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=65307" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/葡萄安.jpg">
									<p>號稱對骨頭好的保健食品滿坑滿谷，有吃的錠劑、也有喝的，有的低鈉、有的低糖……，令人頭昏眼花，而穿白袍的銷售人員其實未必是藥師，又叫你買這不買那，究竟該怎麼辦才好？</p>
								</a>
							</div>
						</div>
					</div>
					<div class="row  visible-xs">
						<div class="row">
							<div class="col-xs-6 col-sm-3 news " >
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=67447" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/人參.jpg">
									<p>高齡105歲的香港企業家邵逸夫身體硬朗，在90歲前都堅持每天上班，直到102歲才正式退休，他長壽的養生祕訣就是每天含一片野生人參。</p>
								</a>
							</div>
							<div class="col-xs-6 col-sm-3 news" >
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=63922" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/鮪魚肚.jpg">
									<p>中年男人不想受到肥胖困擾，專家們一致建議控制熱量、均衡飲食、外食時慎選菜餚及保持規律的運動，聽起來似乎是老生常談，但確實是不二法門。</p>
								</a>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-3 news" >
								<a href="http://www.commonhealth.com.tw/blog/blogTopic.action?nid=1537" class="hover">
									<img src="<%=request.getContextPath()%>/front-end/image/失智.jpg">
									<p>先前曾提到五種植物精油有助腦部記憶、認知功能，除了使用嗅聞法外，經常飲用香草茶或入菜也是有助益，英國紐堡大學研究發現香蜂草，迷迭香及鼠尾草有助記憶提升哦！</p>
								</a>
							</div>
							<div class="col-xs-6 col-sm-3 news ">
								<a href="http://www.commonhealth.com.tw/article/article.action?nid=70026" class="hover">

									<img src="<%=request.getContextPath()%>/front-end/image/運動.jpg">
									<p>小張熱愛跑步，一週團練3天，過年前，連續參加兩場國際馬拉松，結束後感冒了一個多月，吃不下也睡不好，精神變得很差。他看了醫生，做了很多檢查，病情卻無明顯改善。</p>
								</a>
							</div>
						</div>
					</div>
				</div>
				
				<!-- footer -->
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
								<div style="margin-top:-10px;">
								<div class="row">
									<div class="col-xs-12 col-sm-6" style="padding-right: 0;">
										<ul class="downmenu clearfix">
											<li>
												<a href="" class="hover">關於我們</a>
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
					  	
					<div class="copy">
						<h5 style="margin:0;color: #fff;">Copyright©2016 呷百利JIABAZE All Rights Reserved</h5>
					</div>	
				</div>
				<!--BACKtoTOP-START-->
				<a style="display:scroll;position:fixed;bottom:0px;right:5px;" href="#header" class="hover">
				<img src="<%=request.getContextPath()%>/front-end/image/btn_pt.png">
				</a>
				<!--BACKtoTOP-STOP-->



				

				<!-- jQuery -->
				<!-- <script src="//code.jquery.com/jquery.js"></script> -->
				<!-- Bootstrap JavaScript -->
<!-- 				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script> -->
				<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

</body>
</html>