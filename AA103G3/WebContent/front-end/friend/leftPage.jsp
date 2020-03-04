<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>個人專頁左頁</title>
</head>
<body>
	<div class="personalPic">
				<img
					src="<%=request.getContextPath()%>/personal/personal.do?memno=${memnoThisPage}">
				<h2>${memVOO.name}</h2>
				<c:if test="${!isSelf}">
					<c:if test="${!isFrid}">
						<c:if test="${!isApply}">
							<button class="sendRequest" onclick="sendRequest();">加好友</button>
							<button class="sended" style="display: none;">已送出好友申請</button>
						</c:if>
						<c:if test="${isApply}">
							<button>已送出好友申請</button>
						</c:if>
					</c:if>
					<c:if test="${isFrid}">
						<button>已好友</button>
						<form method="post" action="<%=request.getContextPath()%>/friend/friend.do">
							<button type="submit">取消好友</button>
							<input type="hidden" name="action" value="delete_for_friend">
							<input type="hidden" name="memno" value="${memnop}">
							<input type="hidden" name="frino" value="${memnoThisPage}">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						</form>				
					</c:if>
				</c:if>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6" style="padding: 0 0 0 30px;">
					<form method="post" action="<%=request.getContextPath()%>/front-end/friend/friends.jsp">
						<button type="submit" style="width: 100%;">好友</button>
						<input type="hidden" name="memno" value="${memnoThisPage}">
					</form>
					
				</div>
				<div class="col-xs-12 col-sm-6" style="padding: 0 30px 0 0;">
					<button style="width: 100%;">公開</button>
				</div>
			</div>

			<div class="about">
				<h4
					style="border-bottom: solid 1px #eee; padding-bottom: 10px; color: #0E9EA3; font-weight: bold;">關於我</h4>
				<p>生日：1996年12月26日</p>
				<p>XXXXXX</p>
				<p>XXXXXXX</p>
				<p>XXXXX</p>
				<p>XXXXXXXXXXXXXXX</p>
			</div>
			<c:if test="${isSelf}">
				<div class="friendask">
					<h4
						style="border-bottom: solid 1px #eee; padding: 10px 0 10px 5px; color: #0E9EA3; font-weight: bold;">好友申請</h4>
					<div class="row">
						<c:forEach var="friendVO"
							items="${friSvc.getAllByFriFriend(memnop,\"0\")}">
							<div class="col-xs-12 col-sm-12 friendrequest">
								<div class="col-sm-5 friendimg">
									<img
										src="<%=request.getContextPath()%>/personal/personal.do?memno=${friendVO.memno}"
										style="width: 100%; height: 100%;">
								</div>
								<div class="col-xs-7 col-sm-7 requestinfo">
									<a href="<%=request.getContextPath()%>/front-end/personalPage.jsp?memno=${friendVO.memno}">${memSvc.getOneMem(friendVO.memno).name}</a><br>
									<button onclick="comfirmFri(this,${friendVO.memno},${memnop})">確認好友</button>
									<button onclick="cancleFri(this,${friendVO.memno},${memnop})">取消</button>
								</div>

							</div>

						</c:forEach>
					</div>
				</div>
			</c:if>

			<div class="ach">
				<h4
					style="border-bottom: solid 1px #eee; padding: 10px 0 10px 5px; color: #0E9EA3; font-weight: bold;">成就勳章</h4>
				<div class="row">
				<c:forEach var="memAchVO" items="${memAchList}">
					<div class="col-xs-4 col-sm-4">
						<img src="image/ach1.png" style="width: 100%;">
					</div>
				</c:forEach>
				</div>
			</div>

</body>
</html>