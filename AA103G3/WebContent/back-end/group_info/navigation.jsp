<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <input id="hamburger" type="checkbox" class="hamburger-checkbox">
  <!-- <label for="hamburger" class="hamburger-label" role="button" aria-labelledby="menu">&#xf0c9;</label> -->
  <label for="hamburger" class="hamburger-label" role="button" >&#xf0c9;</label>
  
<nav role="navigation" class="sidebar">
    <ul id="fff" class="menu">
     
      <li class="two" >		
		<div class="member">
		<span>
			<img src="<%=request.getContextPath()%>/back-front/group_info/img/main_04.jpg">
		</span>
		
			<ul>
			<li><a href="<%=request.getContextPath()%>/back-front/group_info/index.jsp">首頁</a></li>
<!-- 			<li><a href="slideShow.jsp">輪播牆管理</a></li> -->
			<li><a href="<%=request.getContextPath()%>/back-front/group_info/slideShow.jsp">管理者登入</a></li>
			<li><a href="<%=request.getContextPath()%>/back-front/group_info/slideShow.jsp">最新健康資訊維護</a></li>
			<li><a href="<%=request.getContextPath()%>/back-front/group_info/groupInfo.jsp">揪團管理 </a></li>
		</ul>
		</div>
		<div class="member2">
		<img src="<%=request.getContextPath()%>/back-front/group_info/img/main2_01.jpg">
			<ul>
				<li><a href="#">會員資料查詢</a></li>
				<li><a href="#">權限管理</a></li>
			</ul>
		</div>
		</li>
    </ul>
  </nav>
