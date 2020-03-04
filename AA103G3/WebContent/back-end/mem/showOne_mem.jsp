<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mem.model.*" %>
<%
// 	MemVO memVO=(MemVO)request.getAttribute("memVO");
	String[] act_array={"", "���׹B��", "���׹B��", "���׹B��"};
	pageContext.setAttribute("act_array", act_array);
%>
<html>
<head>
<title>showOne_mem.jsp</title>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/mem/css/mem2.css">
</head>
<body>
<!-- 	<div class="container container_showOne_mem"> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="col-sm-1"></div> -->
			<div class="col-sm-12 text-left" style="margin-bottom:40px;">
				<div class="col-sm-12" style="margin-bottom:8px;">
					<div class="col-sm-4">
						<img src="<%= request.getContextPath()%>/mem/ShowImg_memno?memno=${memVO.memno}" width="150" height="150" id="photo_showOne"></img>
					</div>
					<div class="col-sm-8 text-right">
						<label class="title_label" style="font-size: 26px;">�|���s�� </label><label class="data_label" style="font-size: 22px;">${memVO.memno}</label>
					</div>
					<div class="col-sm-8" style="margin-bottom:8px;">
						<label class="title_label">�W�� </label><label class="data_label">${memVO.name}</label>
					</div>
					<div class="col-sm-8" style="margin-bottom:8px;">
						<label class="title_label">�b�� </label><label class="data_label">${memVO.acc}</label>
					</div>
					<div class="col-sm-8" style="margin-bottom:8px;">
						<label class="title_label">�K�X </label><label class="data_label">${memVO.pwd}</label>
					</div>
				</div>
				<div class="col-sm-12 datarow">
					<div class="col-sm-4">
						<label class="title_label">��� </label><label class="data_label">${memVO.phone}</label>
					</div>
					<div class="col-sm-8">
						<label class="title_label">�q�l�H�c </label><label class="data_label">${memVO.email}</label>
					</div>
				</div>
				<div class="col-sm-12 datarow">
					<div class="col-sm-4">
						<label class="title_label">�ʧO </label><label class="data_label">${memVO.sex == 1 ? '�k' : '�k'}</label>
					</div>
					<div class="col-sm-4">
						<label class="title_label">�ͤ� </label><label class="data_label">${memVO.bir}</label>
					</div>
					<div class="col-sm-4 text-left">
						<label class="title_label">�������� </label><label class="data_label">${act_array[memVO.act_type]}</label>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="col-sm-12"><label style="font-size: 36px; margin-bottom: 10px; border-bottom:1px solid #3382c6; width:100%;">���d���p</label></div>
					<div class="col-sm-3">
						<label class="title_label"> ������</label>${memVO.blood == '1' ? "<span class='fa fa-check'></span>" : "<span class='fa fa-times'></span>" }
					</div>
					<div class="col-sm-3">
						<label class="title_label"> �}���f</label>${memVO.sugar == '1' ? "<span class='fa fa-check'></span>" : "<span class='fa fa-times'></span>" }
					</div>
					<div class="col-sm-3">
						<label class="title_label"> �x�T�J�L��</label>${memVO.oil == '1' ? "<span class='fa fa-check'></span>" : "<span class='fa fa-times'></span>" }
					</div>
					<div class="col-sm-3">
						<label class="title_label"> ���貨�P</label>${memVO.bone == '1' ? "<span class='fa fa-check'></span>" : "<span class='fa fa-times'></span>" }
					</div>
					<div class="col-sm-3">
						<label class="title_label"> ���</label>${memVO.breathe == '1' ? "<span class='fa fa-check'></span>" : "<span class='fa fa-times'></span>" }
					</div>
					<div class="col-sm-3">
						<label class="title_label"> ��Ŧ�f</label>${memVO.heart == '1' ? "<span class='fa fa-check'></span>" : "<span class='fa fa-times'></span>" }
					</div>
					<div class="col-sm-3">
						<label class="title_label"> �z�G��ê</label>${memVO.stom == '1' ? "<span class='fa fa-check'></span>" : "<span class='fa fa-times'></span>" }
					</div>
				</div>
			</div>

</body>
</html>
<!-- 			<div class="col-sm-12 text-center"> -->
<%-- 				<a class="btn btn-primary" href="<%=request.getContextPath()%>/back-end/index_mem.jsp?memno=${memVO.memno}&whichPage=<%= request.getParameter("whichPage")%>" role="button"> --%>
<!-- 					<span class="glyphicon glyphicon-repeat"></span> ��^ -->
<!-- 				</a> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->