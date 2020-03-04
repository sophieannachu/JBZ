package com.groupinfo.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.InflaterInputStream;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.json.JSONObject;

import com.groupinfo.*;
import com.groupinfo.model.GroupInfoService;
import com.groupinfo.model.GroupInfoVO;

import com.mem.model.MemVO;

import com.google.gson.Gson;
import com.groupList.model.GroupListService;
import com.groupList.model.GroupListVO;

@MultipartConfig(fileSizeThreshold = 500000000*1024 * 1024, maxFileSize = 500000000 * 1024 * 1024, maxRequestSize = 500000000 * 5 * 1024 * 1024)
public class GroupinfoServlet extends HttpServlet {
	final Base64.Decoder decoder = Base64.getDecoder();
	final Base64.Encoder encoder = Base64.getEncoder();
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		GroupInfoService groupInfoSvc = new GroupInfoService();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		 if ("insert_One_group".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
//				
//				List<String> errorMsgs = new LinkedList<String>();
//				// Store this set in the request scope, in case we need to
//				// send the ErrorPage view.
//				req.setAttribute("errorMsgs", errorMsgs);
//
//				try {
//					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
//					String groupName = req.getParameter("groupName").trim();
//					Integer deptno = new Integer(req.getParameter("deptno").trim());
//					
//					
//
//					EmpVO empVO = new EmpVO();
//					empVO.setEname(ename);
//					empVO.setJob(job);
//					empVO.setHiredate(hiredate);
//					empVO.setSal(sal);
//					empVO.setComm(comm);
//					empVO.setDeptno(deptno);
//
//					// Send the use back to the form, if there were errors
//					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/emp/addEmp.jsp");
//						failureView.forward(req, res);
//						return;
//					}
//					
//					/***************************2.�}�l�s�W���***************************************/
//					EmpService empSvc = new EmpService();
//					empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
//					
//					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//					String url = "/emp/listAllEmp.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
//					successView.forward(req, res);				
//					
//					/***************************��L�i�઺���~�B�z**********************************/
//				} catch (Exception e) {
//					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//				}
//			}
			
		/*--------------�ק�---------------------*/
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			
			
			
//			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer group_no = new Integer(req.getParameter("group_no"));
//				String requestURL = req.getParameter("requestURL");
				System.out.println("group_no="+group_no);
				/***************************2.�}�l�d�߸��****************************************/
				GroupInfoService groupInfoSv = new GroupInfoService();
				
				GroupInfoVO groupInfoVO = groupInfoSv.getOneGroupInfo(group_no);
//				System.out.println("fff"+groupInfoVO);
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("groupInfoVO", groupInfoVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-front/group_info/groupItemEdit.jsp";
//				System.out.println("sdsd");
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				System.out.println("�L�k���o�n�ק諸���");
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-front/footer.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			
			
//			try{
				
			
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer group_no = new Integer(req.getParameter("group_no"));
				String group_name = new String(req.getParameter("groupName"));
				Integer group_memno = new Integer(req.getParameter("groupMemno"));
				Integer groupCount = new Integer(req.getParameter("groupCount"));
				Integer groupCheck = new Integer(req.getParameter("groupCheck"));
				String group_detial = new String(req.getParameter("group_detial"));
				String group_loc = new String(req.getParameter("groupLoc"));
				String group_time = new String(req.getParameter("group_time"));
				String requestURL = new String(req.getParameter("requestURL"));
				 
	            Timestamp group_time_ts = Timestamp.valueOf(group_time);  
	            System.out.println(group_time_ts);  
	            
	            System.out.println(req.getParameter("group_long"));
	            System.out.println(req.getParameter("group_lati"));
				
				String group_long = new String(req.getParameter("group_long"));
				String group_lati = new String(req.getParameter("group_lati"));
				
				Part part = req.getPart("fname");
				/***************************2.�}�l�d�߸��****************************************/
				GroupInfoService groupInfoSv = new GroupInfoService();
				byte[] photo = groupInfoSv.showGroupInfoPhoto(group_no);
				if(part.getSize() > 0){
					InputStream in =part.getInputStream();
					photo = new byte[in.available()];
					in.read(photo);
					in.close();
				}
				GroupInfoVO groupInfoVO = groupInfoSv.updateGroupInfoItem(group_no,groupCheck,
						group_memno,group_name,group_loc,group_detial,groupCount,photo,group_time_ts,group_long,group_lati);

								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("groupInfoVO", groupInfoVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
//			}catch(Exception e){
//				/***************************��L�i�઺���~�B�z**********************************/
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-front/food.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		
		/*--------------�s�W-----------------------------------------------------*/
		
			
			if("insert_One_group".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try{
				String requestURL = new String(req.getParameter("requestURL"));
				String group_name = new String(req.getParameter("groupName")).trim();
				if (group_name == null || (group_name.trim()).length() == 0) {
					errorMsgs.add("�п�J�ȹΦW��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String group_time = new String(req.getParameter("groupTime"));
				if (group_time == null || (group_time.trim()).length() == 0) {
					errorMsgs.add("�п�J���ʮɶ�");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String group_detial = new String("groupDetial");
				if (group_detial == null || (group_detial.trim()).length() == 0) {
					errorMsgs.add("�п�J�ȹά��ʲӸ`");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String group_loc = new String(req.getParameter("groupLoc"));
				if (group_loc == null || (group_loc.trim()).length() == 0) {
					errorMsgs.add("�п�J�ȹά��ʲӸ`");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				
				
				
				
				Integer group_memno = new Integer(req.getParameter("groupMemno"));
				Integer groupCount = new Integer(req.getParameter("groupCount"));
	//			Integer groupCheck = new Integer(req.getParameter("groupCheck"));
				Integer groupCheck = new Integer(0);
			
				String url = requestURL+"?action=main";
				System.out.println(url);
				Timestamp group_time_ts = Timestamp.valueOf(group_time);  
				String group_long = new String(req.getParameter("group_long"));
				String group_lati = new String(req.getParameter("group_lati"));
				String insertPic = req.getParameter("insertPic");
				
				GroupInfoService groupInfoSv = new GroupInfoService();
				
			
				//����ƶǰe���
				HttpSession session = req.getSession();
				String se = (String) session.getAttribute("insertPic");
				
				
				System.out.println("se"+se);
				if(insertPic.equals(se)){
					
					session.removeAttribute("insertPic");
					String se2 = (String) session.getAttribute("insertPic");
					System.out.println("se2"+se2);
					Part filePart1 = req.getPart("fname");
					InputStream in = filePart1.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					groupInfoSv.addGroupInfoOne(groupCheck,group_memno,group_name
							,group_loc,group_detial,groupCount,buf,group_time_ts, group_long, group_lati);
					in.close();
					groupInfoSvc = new GroupInfoService();
					List<GroupInfoVO> groupInfoVOList = groupInfoSvc.getOneNewsGroupSvc(group_memno);
					req.setAttribute("groupInfoVOList", groupInfoVOList);
					RequestDispatcher fr = req.getRequestDispatcher(url);
					fr.forward(req, res);
				}
			}catch(Exception e){
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/homePage.jsp");
					failureView.forward(req, res);
				
//					RequestDispatcher fr = req.getRequestDispatcher(requestURL);
//					fr.forward(req, res);
			}
				
		}	
			
		/*------------------------------------------------*/
		
		/*-------------------�s�W64�줸�Ϥ�----------------------*/
		
		if("insert_One64_Pic".equals(action)){
			System.out.println("sdds");
			String groupName = req.getParameter("groupName").trim();
			String groupName2=groupName.replaceAll(" ","&nbsp;").replaceAll("\r","<br/>").replaceAll("\n","&nbsp;");
			String insertPic = req.getParameter("insertPic");
			JSONObject groupNameText = new JSONObject();
			try{
				groupNameText.put("groupNameText", groupName2);
				Collection<Part> parts = req.getParts();				//req.getPart("file").write(getServletContext().getRealPath("/images_uploaded")+"/file.gif");
				int count = 0;
				for(Part part:parts){
					if(part.getName().equals("fname")){
						count++;
					
						InputStream fin = part.getInputStream();
						byte[] bFile = new byte[fin.available()];
						fin.read(bFile);
						fin.close();
						
						final String picBase64 = encoder.encodeToString(bFile);
						groupNameText.put("pic["+count+"]", picBase64);
					}
				}
				String jsonSt = groupNameText.toString();
				req.setAttribute("jsonSt", jsonSt);
				System.out.println("jsonSt"+jsonSt);
				RequestDispatcher fr = req.getRequestDispatcher("/Socket.jsp");
				fr.forward(req, res);
			}catch(Exception e){
				RequestDispatcher fr = req.getRequestDispatcher("/back-front/food.jsp");
				fr.forward(req, res);
			}
			
		}
		
		
		/*--------------------newGroup(�̷s����)----------------------------*/
		if("main".equals(action)){
			Integer memno = new Integer(req.getParameter("memno"));
			System.out.println("memno:::::"+memno);
			groupInfoSvc = new GroupInfoService();
			List<GroupInfoVO> groupInfoVOList = groupInfoSvc.getOneNewsGroupSvc(memno);
			req.setAttribute("groupInfoVOList", groupInfoVOList); 
			for(GroupInfoVO ff:groupInfoVOList){
				System.out.println("�o�O:"+ff.getMemno());
			}
			// ��Ʈw���X��empVO����,�s�Jreq
//			String requestURL = new String(req.getParameter("requestURL"));
//			System.out.println(requestURL);
			String url = "/front-end/groupIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
		}
		
		/*-----------------------------------------------------------*/
		
		/*--------------------addGroup(�̷s����)----------------------------*/
		
		if("addGroup".equals(action) ){
			String json = null;
			int sucess = 0;
			String sucessAdd ="";
			
			
			Integer group_no = new Integer(req.getParameter("group_no"));
			Integer memno = new Integer(req.getParameter("memno"));
			System.out.println("�o�O:"+group_no+":"+memno);
			GroupListService groupListSvc = new GroupListService();
			sucessAdd = groupListSvc.addGroupList(group_no,memno);
			System.out.println("sucessAdd:"+sucessAdd);
			if("sucessAdd".equals(sucessAdd)){
				List<Integer> list = new ArrayList<>();
			    list.add(group_no);
			    json = new Gson().toJson(list);
				System.out.println("json:"+json);
			}
			res.setContentType("application/json");
			PrintWriter out = res.getWriter();
			out.println(json);
//			res.getWriter().write(json);
			// ��Ʈw���X��empVO����,�s�Jreq
//			String requestURL = new String(req.getParameter("requestURL"));
//			System.out.println(requestURL);
//			String url = requestURL;
//			System.out.println("sdsd");
//			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
//			successView.forward(req, res);
		}
		
		/*------------------------------------------------------------------*/
		/*--------------------myGroup(�ڰѥ[������)----------------------------*/
		
		if("myGroup".equals(action) ){
			
			HttpSession session = req.getSession();
			MemVO memVO = (MemVO)session.getAttribute("memVO");
			System.out.print("-------AAAAA-------"+"\n");
			System.out.print("BB"+memVO.getName()+"\n");
			List<GroupListVO> groupVOlist = new ArrayList<>();
			GroupListService groupListSvc = new GroupListService();
			groupVOlist = groupListSvc.getOneMyGroupList(memVO.getMemno());
			for(int i=0;i<groupVOlist.size();i++){
				System.out.println("sss::"+groupVOlist.get(i).getGroup_no());
				
			}
			req.setAttribute("groupVOlist", groupVOlist);
			String requestURL = new String(req.getParameter("requestURL"));
//			res.getWriter().write(json);
			// ��Ʈw���X��empVO����,�s�Jreq
			String url = requestURL;
//			System.out.println("sdsd");
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
		}
		
		/*-----------------------------------------------------------*/
		
/*--------------------outGroup(�h�X�ڰѥ[������)----------------------------*/
		
		if("outGroup".equals(action) ){
			String json = null;
			int sucess = 0;
			String sucessAdd ="";
			
			HttpSession session = req.getSession();
			MemVO memVO = (MemVO)session.getAttribute("memVO");
			Integer group_no = new Integer(req.getParameter("group_no"));
			GroupListService groupListSvc = new GroupListService();
			groupListSvc.deleteGroupList(group_no,memVO.getMemno());
			
			System.out.println("�o�O:"+group_no+":"+memVO.getMemno());
			
			List<Integer> list = new ArrayList<>();
		    list.add(group_no);
		    json = new Gson().toJson(list);
			System.out.println("json:"+json);
			res.setContentType("application/json");
			PrintWriter out = res.getWriter();
			out.println(json);
		}
		/*-----------------------------------------------------------*/
		/*--------------------myCreateGroup(�ڵo�_������)----------------------------*/
		
		if("myCreateGroup".equals(action) ){
			
			HttpSession session = req.getSession();
			MemVO memVO = (MemVO)session.getAttribute("memVO");
			System.out.print("--------------------"+"\n");
			System.out.print("BB"+memVO.getName()+"\n");
			List<GroupInfoVO> groupInfoVOlist = new ArrayList<>();
			GroupInfoService groupInfotSvc = new GroupInfoService();
			groupInfoVOlist = groupInfotSvc.getAll();
//			for(int i=0;i<groupVOlist.size();i++){
//				System.out.println("sss::"+groupVOlist.get(i).getGroup_no());
//				
//			}
			req.setAttribute("groupInfoVOlist", groupInfoVOlist);
			String requestURL = new String(req.getParameter("requestURL"));
//			res.getWriter().write(json);
			// ��Ʈw���X��empVO����,�s�Jreq
			String url = requestURL;
//			System.out.println("sdsd");
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
		}
		
		/*-----------------------------------------------------------*/
		/*--------------------editMyCreateGroup(�s��ڵo�_������)----------------------------*/
		if( "editForm".equals(action) ){
			Integer group_no = new Integer(req.getParameter("group_no"));
			
			
			GroupInfoService groupInfotSvc = new GroupInfoService();
			GroupInfoVO groupInfoVO  = groupInfotSvc.getOneGroupInfo(group_no);
			req.setAttribute("groupInfoVO", groupInfoVO);
			
			HttpSession session = req.getSession();
			MemVO memVO = (MemVO)session.getAttribute("memVO");
		
			System.out.print("BB"+memVO.getName()+"\n");
			List<GroupInfoVO> groupInfoVOlist = new ArrayList<>();
			groupInfoVOlist = groupInfotSvc.getAll();
//			for(int i=0;i<groupVOlist.size();i++){
//				System.out.println("sss::"+groupVOlist.get(i).getGroup_no());
//				
//			}
			//
//			
			req.setAttribute("groupInfoVOlist", groupInfoVOlist);
			
			String url = "/front-end/group/editForm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);

			
//			HttpSession session = req.getSession();
//			MemVO memVO = (MemVO)session.getAttribute("memVO");
//			System.out.print("--------------------"+"\n");
//			System.out.print("BB"+memVO.getName()+"\n");
//			List<GroupInfoVO> groupInfoVOlist = new ArrayList<>();
//			GroupInfoService groupInfotSvc = new GroupInfoService();
//			groupInfoVOlist = groupInfotSvc.getAll();
////			for(int i=0;i<groupVOlist.size();i++){
////				System.out.println("sss::"+groupVOlist.get(i).getGroup_no());
////				
////			}
//			req.setAttribute("groupInfoVOlist", groupInfoVOlist);
//			String requestURL = new String(req.getParameter("requestURL"));
////			res.getWriter().write(json);
//			// ��Ʈw���X��empVO����,�s�Jreq
//			String url = requestURL;
////			System.out.println("sdsd");
//			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
//			successView.forward(req, res);
		}
		//�e�X�s��ڪ��ȹ�
		if( "editForm2".equals(action) ){
			Integer group_no = new Integer(req.getParameter("group_no"));
			String group_name = new String(req.getParameter("group_name"));
			String group_detial = new String(req.getParameter("group_detail"));
			String group_time = new String(req.getParameter("group_time"));
			String requestURL = new String(req.getParameter("requestURL"));
			 
            Timestamp group_time_ts = Timestamp.valueOf(group_time);  
            System.out.println(group_time_ts); 
            
        	
			
			Part part = req.getPart("fname");
//			/***************************2.�}�l�d�߸��****************************************/
			GroupInfoService groupInfoSv = new GroupInfoService();
			byte[] photo = groupInfoSv.showGroupInfoPhoto(group_no);
			if(part.getSize() > 0){
				InputStream in =part.getInputStream();
				photo = new byte[in.available()];
				in.read(photo);
				in.close();
			}
			
			GroupInfoVO groupInfoVO = groupInfoSv.updateGroupInfoItem_one(group_no,
					group_name,group_detial,photo,group_time_ts);
			GroupInfoService groupInfotSvc = new GroupInfoService();
            List<GroupInfoVO> groupInfoVOlist = new ArrayList<>();
			groupInfoVOlist = groupInfotSvc.getAll();
			req.setAttribute("groupInfoVOlist", groupInfoVOlist);
//
//							
//			/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//			req.setAttribute("groupInfoVO", groupInfoVO);         // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/front-end/groupIndex.jsp?action=myCreateGroup";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
//			
			System.out.println("�o�OeditForm2");
		}
		
		
		/*-----------------------------------------------------------*/
		//�q�L�f�֪ȹ�
				if( "editGroupOK".equals(action) ){
					Integer group_no = new Integer(req.getParameter("group_no"));
					GroupInfoService groupInfotSvc = new GroupInfoService();
		        	groupInfotSvc.updateGroupInfoEditGroupOK(group_no);
					
//					/***************************2.�}�l�d�߸��****************************************/
//									
//					/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//					req.setAttribute("groupInfoVO", groupInfoVO);         // ��Ʈw���X��empVO����,�s�Jreq
//					String url = "/front-end/groupIndex.jsp?action=myCreateGroup";
//					RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
//					successView.forward(req, res);
////					
//					System.out.println("�o�OeditForm2");
				}
				
				
				/*-----------------------------------------------------------*/
				
		
//		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String str = req.getParameter("foodno");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J���u�s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-front/food.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				Integer empno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("���u�s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				FoodService foodSvc = new FoodService();
//				FoodVO foodVO = foodSvc.getOneFood(empno);
//				if (foodVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("empVO", foodVO); // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.�����ШD�Ѽ�****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.�}�l�d�߸��****************************************/
//				FoodService empSvc = new FoodService();
//				FoodVO foodVO = empSvc.getOneEmp(foodno);
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("foodVO", foodVO);         // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/emp/update_emp_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				Integer empno = new Integer(req.getParameter("empno").trim());
//				String ename = req.getParameter("ename").trim();
//				String job = req.getParameter("job").trim();				
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
//
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("�~���ж�Ʀr.");
//				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("�����ж�Ʀr.");
//				}
//
//				Integer deptno = new Integer(req.getParameter("deptno").trim());
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEmpno(empno);
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //�{�����_
//				}
//				
//				/***************************2.�}�l�ק���*****************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
//				
//				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
//				String ename = req.getParameter("ename").trim();
//				String job = req.getParameter("job").trim();
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
//				
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("�~���ж�Ʀr.");
//				}
//				
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("�����ж�Ʀr.");
//				}
//				
//				Integer deptno = new Integer(req.getParameter("deptno").trim());
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.�}�l�s�W���***************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
//				
//				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.�����ШD�Ѽ�***************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.�}�l�R�����***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}

	

	
}
