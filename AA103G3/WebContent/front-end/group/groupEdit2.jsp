<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="BIG5"%>
<%@ page import="com.groupinfo.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

	<!-- table-striped(條紋), table-bordered(框線), table-condensed(padding)-->
	<div class="container">
			<!-- <div class="col-sm-1">
				</div> -->

			<div class="col-sm-9">
				<div class="col-sm-12 search" style="padding-top: 30px;">
					<!-- 1.套用form-horizontal -->
					<form class="form-horizontal">
						<div class="form-group">
							<!-- 2.label 套用格線& control-label -->
							<label for="name" class="col-sm-1 control-label">查詢:</label>
							<!-- 3.input用格線包圍 -->
							<div class="col-sm-3">
								<input type="text" name="" id="name" class="form-control"></input>
							</div>
						</div>
					</form>
				</div>
				<div class="col-sm-12 space"></div>
				<table class="table table-striped table-hover" border="1">


					
					
					

					
						
							<div style="border:solid 1px;">
								<form action="<%=request.getContextPath()%>/back-front/GroupinfoServlet.do"
									enctype="multipart/form-data" method="post">
									揪團名稱: <input type="text" name="groupName" value="${groupInfoVO.group_name}"><br> 
									團主編號: <input type="text" name="groupMemno" value="${groupInfoVO.memno}"><br>
									 揪團地址: <input type="text" name="groupLoc" value="${groupInfoVO.group_loc}"><br> 
									 開團時間: <input type="text" name="group_time" value="${groupInfoVO.group_time}"><br> 
									 開團緯度: <input type="text" name="group_long" value="${groupInfoVO.group_long}"><br> 
									 開團經度: <input type="text" name="group_lati" value="${groupInfoVO.group_lati}"><br> 
										
									揪團人數: <input type="text" name="groupCount" value="${groupInfoVO.group_count}"><br> 
									 揪團圖片: <input type="file" name="fname"><br> 
										 	<input type="hidden" name="insertPic" value="<%=session.getAttribute("insertPic") %>">
											<input type="hidden" name="group_no" value="${groupInfoVO.group_no}">
					    					<input type="hidden" name="action" value="update">
					    					<input type="hidden" name="groupCheck" value="${groupInfoVO.group_check}">
					    					
											<input type="submit" value="Submit">
				
			
								</form>
								<img src="ShowGroupInfo?group_no=${groupInfoVO.group_no}" style="width: 20%;">
							</div>
			
				</table>

				<div class="col-sm-12 text-center">
					<ul class="pagination pagination-lg">
						<li><a href="#">&laquo;</a></li>
						<li><a href="#">1</a></li>
						<li class="active"><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul>
				</div>
			</div>
		</div>


	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>