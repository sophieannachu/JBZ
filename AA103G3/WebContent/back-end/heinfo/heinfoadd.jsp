<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="BIG5"%>
<%@ page import="com.heinfo.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.sql.Connection"%>
<%
	java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<title>健康資訊維護</title>
<link href="<%=request.getContextPath()%>/back-end/heinfo/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/back-end/heinfo/js/previewImage.js"></script>
<script src="<%=request.getContextPath()%>/back-end/heinfo/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<script type="text/javascript">

</script>
<body>
<!-- 	<div class="well"> -->
<!-- 		<div id="datetimepicker1" class="input-append date"> -->
<!-- 			<input data-format="dd/MM/yyyy hh:mm:ss" type="text"></input> -->
<!-- 			<span class="add-on">  -->
<!-- 				<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i> -->
<!-- 			</span> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="htmleaf-container"> -->

<!-- 		<div class="container kv-main"> -->
<!-- 			<div class="row"> -->

				<div class="span12 col-sm-12" style="padding:0 0;margin-bottom:20px;margin-top:20px;">
					<div style="text-align: center">
						<h2>新增健康資訊</h2>
					</div>
					<form class="form-horizontal" id="form" enctype="multipart/form-data" METHOD="POST" ACTION="<%=request.getContextPath()%>/heinfo/heinfo.do">

						<div class="form-group">
							<label for="heinfoname" class="col-sm-2 control-label text-left">健康資訊名稱</label>
							<div class="col-sm-9 controls">
								<input type="text" id="heinfoname" name="heinfoname" class="form-control">
							</div>
						</div>


						<div class="form-group">
							<label for="heinfodetail" class="col-sm-2 control-label">健康資訊內容</label>
							<div class="col-sm-9 controls">
								<textarea class="form-control" rows="7" id="heinfodetail" name="heinfodetail"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="heinfophoto" class="col-sm-2 control-label">健康資訊照片</label>
							<div class="col-sm-9 controls">
								<input id="file" type="file" class="file" name="heinfophoto">
								<%--                              <input type="hidden" name="insertPic" value="<%=session.getAttribute("insertPic") %>"> --%>
							</div>
						</div>

						<div class="form-group">
								<button type="submit" class="btn btn-success">新增</button>
								<input type="hidden" name="heinfodate" value="<%=date_SQL%>">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
								<input type="hidden" name="action" value="insert">
						</div>

					</form>
				</div>
<!-- 			</div> -->
<!-- 		</div> -->

<!-- 	</div> -->


</body>
</html>