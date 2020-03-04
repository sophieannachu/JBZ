<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.clock.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	ClockService clockSvc = new ClockService();
	List<ClockVO> list = clockSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="clockSvc1" scope="page" class="com.clock.model.ClockService" />
<%
String clockType[] ={"運動","睡眠","預約","服藥","餐會","自訂"}; 
String clockType1[] = {"聖誕節","卡農","圓舞曲","少女的祈禱","貝多芬進行曲"};
%>
<%SimpleDateFormat df = new SimpleDateFormat("HH:mm");%>
<html>
<head>
<title>鬧鐘列表</title>


<script>
	
</script>


</head>
<body>



    	
   			<div style="min-height:250px;">
   			
    			<table class="table-striped table table-hover" >
					<tr class="success">
						
						<th>時間</th>
						<th>詳細</th>
						<th>週期</th>
						<th>鈴聲</th>
						<th>種類</th>
						<th>刪除</th>
					</tr>
				<%for(ClockVO clockVO:list){%>
				
						<tr class="info">
							
							<td><%=df.format(clockVO.getClocktime())%></td>
							<td><%=clockVO.getClockinfo()%></td>
							<td><%=clockVO.getClocksche()%></td>
							<td><%=clockType1[Integer.parseInt(clockVO.getClockring())]%></td>
							<td>			
							<%=clockType[clockVO.getClocktype()]%>			
							</td>
							
							<td>
							  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/clock/clock.do">
							    <button type="submit" class="fa fa-times" value="刪除"></button>
							    <input type="hidden" name="clockno" value="<%=clockVO.getClockno()%>">
							    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
							    <input type="hidden" name="action"value="delete"></FORM>
							</td>
						</tr>
					<%}%>
					
				</table>
   			</div>
			
    
</body>
</html>
