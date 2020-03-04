<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.heinfo.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	HeinfoService heinfoSvc = new HeinfoService();
	List<HeinfoVO> list = heinfoSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/back-end/heinfo/js/jquery-latest.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/heinfo/js/bootstrap.min.js"></script>
<title>所有資料 - listAllHeinfo.jsp</title>
</head>
<body bgcolor='white'>

	<table class="table table-hover table_listAllHeinfo" style="width:100%;white-space:nowrap;">
		<thead>
			<tr>
				<th>編號</th>
				<th>資訊姓名</th>
				<th>資訊詳細</th>
				<th>資訊日期</th>
<!-- 				<th>資訊照片</th> -->
				<th>修改</th>
				<th>刪除</th>
			</tr>
		</thead>
		<%@ include file="page1.file"%>
		<tbody>
			<c:forEach var="heinfoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr align='center' valign='middle'>
					<td>${heinfoVO.heinfono}</td>
					<td>${heinfoVO.heinfoname}</td>
					<td><p class="short">${heinfoVO.heinfodetail}</p></td>
					<td>${heinfoVO.heinfodate}</td>
<%-- 					<td><img src="<%=request.getContextPath()%>/heinfo/Showimg?heinfono=${heinfoVO.heinfono}" style="width:60px;height:60px;"></td> --%>
	
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/heinfo/heinfo.do">
							<input type="submit" value="修改"> <input type="hidden"
								name="heinfono" value="${heinfoVO.heinfono}">
							<%-- 			     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/heinfo/heinfo.do">
							<input type="submit" value="刪除"> 
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="heinfono" value="${heinfoVO.heinfono}">
							<input type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<%@ include file="page2.file"%>

</body>

<style type="text/css">
.more {
	font-weight: bold;
	color: #ffa73f;
	cursor: pointer;
}
.table_listAllHeinfo>tbody>tr>td {
	font-size:20px;
	text-align: center;
	vertical-align: middle;
}

.table_listAllHeinfo>thead>tr>th {
	font-size:20px;
	text-align: center;
	vertical-align: middle;
}

</style>
<script type="text/javascript">
	$(function($) {
		// 只顯示 50 字, 其它的需要按下繼續閱讀才會顯示
		$(".short").readmore({
			substr_len : 20,
			more_link : '<a class="more">繼續閱讀</a>'
		});
	});
	(function($) {
		$.fn.readmore = function(settings) {

			var opts = $.extend({}, $.fn.readmore.defaults, settings);

			this.each(function() {
				$(this).data("opts", opts);
				if ($(this).html().length > opts.substr_len) {
					abridge($(this));
					linkage($(this));
				}
			});

			function linkage(elem) {
				elem.append(elem.data("opts").more_link);
				elem.children(".more").click(
						function() {
							$(this).hide();
							$(this).siblings("span:not(.hidden)").hide()
									.siblings("span.hidden").animate({
										'opacity' : 'toggle'
									}, 1000);
						});
			}

			function abridge(elem) {
				var opts = elem.data("opts");
				var txt = elem.html();
				var len = opts.substr_len;
				var dots = "<span>" + opts.ellipses + "</span>";
				var shown = txt.substring(0, len) + dots;
				var hidden = '<span class="hidden" style="display:none;">'
						+ txt.substring(len, txt.length) + '</span>';
				elem.html(shown + hidden);
			}

			return this;
		};

		$.fn.readmore.defaults = {
			substr_len : 500,
			ellipses : '&#8230;',
			more_link : '<a class="more">Read&nbsp;More</a>'
		};

	})(jQuery);
</script>
</html>