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
String clockType[] ={"�B��","�ίv","�w��","�A��","�\�|","�ۭq"}; 
String clockType1[] = {"�t�ϸ`","�d�A","��R��","�֤k����ë","���h��i�榱"};
%>
<%SimpleDateFormat df = new SimpleDateFormat("HH:mm");%>
<html>
<head>
<title>�x���C��</title>


<script>
	
</script>


</head>
<body>



    	
   			<div style="min-height:250px;">
   			
    			<table class="table-striped table table-hover" >
					<tr class="success">
						
						<th>�ɶ�</th>
						<th>�Բ�</th>
						<th>�g��</th>
						<th>�a�n</th>
						<th>����</th>
						<th>�R��</th>
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
							    <button type="submit" class="fa fa-times" value="�R��"></button>
							    <input type="hidden" name="clockno" value="<%=clockVO.getClockno()%>">
							    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
							    <input type="hidden" name="action"value="delete"></FORM>
							</td>
						</tr>
					<%}%>
					
				</table>
   			</div>
			
    
</body>
</html>
