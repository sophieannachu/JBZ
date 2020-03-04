<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <input id="hamburger" type="checkbox" class="hamburger-checkbox">
  <!-- <label for="hamburger" class="hamburger-label" role="button" aria-labelledby="menu">&#xf0c9;</label> -->
 <label for="hamburger" class="hamburger-label" role="button" >&#xf0c9;</label>
  <%
  	List<Integer> authlist=(List<Integer>)session.getAttribute("authList");
  	Integer i=new Integer(5);
	int s=5;
	session.setAttribute("i", i);
	session.setAttribute("s", s);
  %>
<nav role="navigation" class="sidebar">
    <ul id="fff" class="menu">
		<li class="two" >		
			<div class="member">
				<span>
					<img src="<%=request.getContextPath()%>/back-end/img/main_04.jpg">
				</span>
				<ul>
					<li>
						<form method="post" id="logout" action="<%=request.getContextPath()%>/login/LoginServlet.do">
							<input type="hidden" name="action" value="logout">
						</form>
						<a onclick="document.getElementById('logout').submit();" style="">�޲z�̵n�X</a>
<!-- 						<a href="slideShow.jsp"></a> -->
					</li>
<!-- 					<li><a  href="index.jsp">����</a></li> -->
					
					<c:if test="<%= authlist.contains(3)%>" >
						<li><a href="<%=request.getContextPath()%>/back-end/groupInfo.jsp">���μf�� </a></li>
					</c:if>
					
					<c:if test="<%= !authlist.contains(3)%>" >
						<li><a href="#" onclick="authAlert()">���κ޲z </a></li>
					</c:if>
					
					<c:if test="<%= authlist.contains(4)%>" >
						<li><a href="#">���~�޲z </a></li>
					</c:if>
					
					<c:if test="<%= !authlist.contains(4)%>" >
						<li><a href="#" onclick="authAlert()">���~�޲z </a></li>
					</c:if>
					
					<c:if test="<%=authlist.contains(5)%>" >
						<li><a href="<%=request.getContextPath()%>/back-end/heinfoIndex.jsp?action=heinfoIndex">�̷s���d��T���@</a></li>
					</c:if>
					
					<c:if test="<%= !authlist.contains(5)%>" >
						<li><a href="#" onclick="authAlert()">�̷s���d��T���@</a></li>
					</c:if>
					
				</ul>
			</div>

			<div class="member1">
				<img src="<%=request.getContextPath()%>/back-end/img/main2_04.jpg">
				<ul>
				<c:if test="<%= authlist.contains(1)%>" >
					<li><a href="<%=request.getContextPath()%>/back-end/index_emp.jsp" style="color:white;">���u�޲z </a></li>
					<li><a href="<%=request.getContextPath()%>/back-end/index_auth.jsp" style="color:white;">���u�v���޲z</a></li>
				</c:if>
				<c:if test="<%= !authlist.contains(1)%>" >
					<li><a href="#" onclick="authAlert()" style="color:white;">���u�޲z </a></li>
					<li><a href="#" onclick="authAlert()" style="color:white;">���u�v���޲z</a></li>
				</c:if>
					
				</ul>
			</div>
			<div class="member2">
				<img src="<%=request.getContextPath()%>/back-end/img/main2_01.jpg">
				<ul>
				<c:if test="<%= authlist.contains(2)%>" >
					<li><a href="<%=request.getContextPath()%>/back-end/index_mem.jsp">�|����Ƭd��</a></li>
				</c:if>
				
				<c:if test="<%= !authlist.contains(2)%>" >
					<li><a href="#" onclick="authAlert()">�|����Ƭd��</a></li>
				</c:if>
					
				</ul>
			</div>
		</li>
    </ul>
  </nav>
<script>
	function authAlert(){
		swal(
			"�b���L���v��",
			"",
			'error'
   			)
	}
</script>