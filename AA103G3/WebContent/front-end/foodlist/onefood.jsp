<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.foodlist.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.* "%>
<script type="text/javascript">
$().ready(function() {
$('#delete11').click(function() {
	swal("���o�n!", "�z�w�g���\�s�W��������!", "success")
	
    var foodlist = new Array();

    // var foodlist = {};
    for (i = 2; i <= $('#abc tr').length; i++) { 
    //css��ܾ��q1�}�l.���]���Ĥ@��TR�O���Y(TH)�L�ΡA�ҥH�qi=2�}��
        var food = {};
    	// console.log("=======��"+(i-1)+"�C========");
    	//�U���O���i�C�U����TD(���tinput�Pselect)
        $('#abc tr:nth-child(' + i + ')').children('td:not(:has(input,select))').each(
            function(){
                food[$(this).data('key')]=$(this).html();
                //Javascript�i�H�Ϊ���W��['�r��']=xxx�ӳ]�w�ݩ�
            })

        //�U���O���i�C�U����select
        $('#abc tr:nth-child(' + i + ') td select').each(
            function() {
                food[$(this).data('key')]=$(this).val();
            }
        )
        //�U���O���i�C�U����input(�t���ۭq���Ҫ�)	
        $('#abc tr:nth-child(' + i + ') td input[data-key]').each(
            function() {
                food[$(this).data('key')]=$(this).val();
            }
        )
        foodlist.push(food); //��C�@�C��food�[��foodlist��

    }
    // for(var food in foodlist){
    //     console.log(foodlist[food]);
    // }
    // console.log(foodlist);

    var json = JSON.stringify(foodlist); //�⪫���নJSON
    console.log();

            $.ajax({
                url:"<%=request.getContextPath()%>/foodlist/foodlist.do",
                type: "post",
                dataType:"Json",
                data:{action:"insert",json:json},
                success: function (data) {
                    document.write(data);
                }
            }); 
            
           
});
});





// var arrNO = new Array();
// var arrmeal = new Array();
// var arrNum = new Array();
// var i=0;
// var j=0;
// var m=0;
// var list=new Array();
// var fd_noArr = new Object();
// var fddescArr= new Object();
// var fdquaArr = new Object();
// var array = [];
// $(document).ready(
// 		function test(){
// 		$("#btnAjaxPost").click(function (){
// 			   $.ajax({
// 				 type:"POST",
// 				 url:"ajaxResponse.jsp",
// 				 data:creatQueryString(),
// 				 dataType:"text",
// 				 success:function (data){$("#div3").html(decodeURI(data))}
// 			   });
// 			//   $.post("ajaxResponse.jsp",creatQueryString(),function (data){$("#div3").html(decodeURI(data))});
// 		   });
// 	})
	
	
// 	$('#aaa').click(function(){
// 		alert($('#abc tr').length);
// 	});

//  function creatQueryString(){
// 	$('button').click(function() {
// // 		 rowCount = ;
		
		
// // 		JSONObject carlist = new JSONObject();
// // 		JSONObject food1 = new JSONObject();
// // 		food1.put("fd_no",arrNO[0]);
// // 		food1.put("fddesc",arrmeal[0]);
// // 		food1.put("fdqua",arrNum[0]);
		
		
//         carlist.put("food1",food1);
//         carlist.put("food2",food2);
//         $('.tdvaul').each(
//             function() {
//                 var str = $(this).html();
//                 arrNO[i]=str;
//                 i++;
//             });
 
//         $('.sl').each(
//             function() {
//                 var str =  $(this).val();
             
//                 arrmeal[j]=str;
//                 j++;
//             });

//         $('.fdqua').each(
//             function() {
//                 var str = $(this).val();
             
//                 arrNum[m]=str;
//                 m++;
//             });
//         for(var n=0;n<m;n++){
//             var aa="{fd_no:"+arrNO[n]+",fddesc:"+arrmeal[n]+",fdqua:"+arrNum[n]+"}";
//             list[n]=aa;
       
            
            
// //          if(n==0){
// //          ar newArr = new Object();
// //          newArr.fd_no=arrNO[n];
// //          newArr.fddesc=arrmeal[n];
// //          newArr.fdqua=arrNum[n];
// //          }
// //          else if(n==1){
// //         	 var newArr = new Object();
// //          }
         
//         }
       
        
//     });
	
	
// 	var name=encodeURI($("#name").val());
// 	var fd_no=encodeURI($("#birthday").val());
// 	var queryString={name:name,birthday:birthday};
// 	return queryString;
//  }
function number(aaa){
	
	cal=aaa.nextSibling.value;
	aaa.parentElement.nextSibling.innerHTML=cal*aaa.value;
	console.log(cal);
	console.log(aaa.value);
}
	
 </script>
<%-- <%FoodVO foodVO = (FoodVO) request.getAttribute("foodVO");%> --%>

	

<%Vector<FoodVO> fd = (Vector<FoodVO>) session.getAttribute("foodcart");%>
<%if (fd != null && (fd.size() > 0)) {%>

<html>
<head>
<title>Onefood.jsp</title>
</head>
<body>


<table style="width:1000px;">
	<tr style="background-color:#0E9EA3;color:#fff;" align='center' valign='middle'>
		<td>
		<h3><%=new java.sql.Date(System.currentTimeMillis())%>(�w�ﶼ��)</h3>
		</td>
	</tr>
</table>

<table style="width:1000px" id="abc" class="table table-striped table-bordered table-hover">
	<tr>
		<th>�����s��</th>
		<th>�����W��</th>
		<th>����\�O</th>
		<th>����</th>
		<th>�d����</th>
		<th>�R��</th>
	</tr>
	<%
	 for (int index = 0; index < fd.size(); index++) {
		FoodVO foodVO = fd.get(index);
	%>
	<tr align='center' valign='middle'>
		<td data-key="fd_no"><%=foodVO.getFd_no()%></td>	
		<td data-key="fd_name"><%=foodVO.getFd_name()%><input type="hidden" value="${memVO.memno}" data-key="memno"></td>
		<td>
			<select size="1" name="fooddesc" data-key="fddesc">
				<option value="���\">���\
				<option value="���\">���\
				<option value="���\">���\
			 </select>
	    </td>
		<td><input class="number" type="number" value="1" data-key="fdqua" onchange="number(this);"><input type="hidden" name="del" value="<%=foodVO.getCal()%>"></td><td data-key="fd_cal" ><%=foodVO.getCal()%></td>

		<td width="100">
		<div align="center">
	          <form name="deleteForm" action="<%=request.getContextPath()%>/foodlist/foodlist.do" method="POST">
	              <input type="hidden" name="action" value="DELFD">
	              <input type="hidden" id="<%=index %>" name="del" value="<%=index %>">
	              <button type="submit" value="�R��" class="fa fa-times"></button>
	        </form>
        </div>
        </td>
	</tr>

	<%}%>
</table>
		  

              
			<div class="row">
				
				<div class="col-md-4">
	              
	            </div>
				<div class="col-md-2">
	              <button type="button" id="delete11"  class="btn btn-danger"><i class="fa fa-check"></i> �x�s����</button>
	            </div>
	            <div class="col-md-2">
	             <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/foodlist/foodlist.do" >
		              <button type="submit"  class="btn btn-primary"><span class="fa fa-times"></span> �M  ��</button>
		              <input type="hidden" name="action"  value="clearnn" >
	              </FORM>
	              </div>
			</div>
<%}%>



</body>

</html>
