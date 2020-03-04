package iii.jbz.server.messagecenter;

import java.io.*;
import java.util.*;
import java.util.zip.InflaterInputStream;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import iii.jbz.server.messagecenter.*;
import iii.jbz.server.messagecenter.BPCheckVO;
import iii.jbz.server.messagecenter.BPCheckDAO;
import iii.jbz.server.messagecenter.BPCheckDAO_interface;

@SuppressWarnings("serial")
@WebServlet("/BPCheckServlet")
//@MultipartConfig(fileSizeThreshold = 500000000*1024 * 1024, maxFileSize = 500000000 * 1024 * 1024, maxRequestSize = 500000000 * 5 * 1024 * 1024)
public class BPCheckServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	    final Base64.Encoder encoder = Base64.getEncoder();
		final Base64.Decoder decoder = Base64.getDecoder();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		GroupInfoService groupInfoSvc = new GroupInfoService();
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
		
		
		
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		
		BPCheckDAO_interface bpcheckDao = new BPCheckDAO();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAll")) {
			int memno = jsonObject.get("memno").getAsInt();
			List<BPCheckVO> bpCheckVOs = bpcheckDao.getAll(memno);
			writeText(response, gson.toJson(bpCheckVOs));
		} else {
			writeText(response, "");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	private void writeText(HttpServletResponse response, String outText)
			throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		System.out.println("outText: " + outText);
		out.print(outText);
	}
}
		
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
			
//		/*--------------�ק�---------------------*/
//		
//		
//		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			
//			
//			
//			
//			try {
//				/***************************1.�����ШD�Ѽ�****************************************/
//				Integer group_no = new Integer(req.getParameter("group_no"));
//				/***************************2.�}�l�d�߸��****************************************/
//				GroupInfoService groupInfoSv = new GroupInfoService();
//				
//				BPCheckVO groupInfoVO = groupInfoSv.getOneGroupInfo(group_no);
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("groupInfoVO", groupInfoVO);         // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/back-front/groupItemEdit.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-front/footer.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		if ("update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			
//			
//			
//			try{
//				
//			
//				/***************************1.�����ШD�Ѽ�****************************************/
//				Integer group_no = new Integer(req.getParameter("group_no"));
//				String group_name = new String(req.getParameter("groupName"));
//				Integer group_memno = new Integer(req.getParameter("groupMemno"));
//				Integer groupCount = new Integer(req.getParameter("groupCount"));
//				Integer groupCheck = new Integer(req.getParameter("groupCheck"));
//				String group_detial = new String("123s");
//				String group_loc = new String(req.getParameter("groupLoc"));
//				Part part = req.getPart("fname");
//				/***************************2.�}�l�d�߸��****************************************/
//				GroupInfoService groupInfoSv = new GroupInfoService();
//				byte[] photo = groupInfoSv.showGroupInfoPhoto(group_no);
//				if(part.getSize() > 0){
//					InputStream in =part.getInputStream();
//					photo = new byte[in.available()];
//					in.read(photo);
//					in.close();
//				}
//				BPCheckVO groupInfoVO = groupInfoSv.updateGroupInfoPhoto(group_no,groupCheck,group_memno,group_name,group_loc,group_detial,groupCount,photo);
//
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("groupInfoVO", groupInfoVO);         // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/back-front/food.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
//				successView.forward(req, res);
//			}catch(Exception e){
//				/***************************��L�i�઺���~�B�z**********************************/
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-front/food.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		
//		/*--------------�s�W---------------------*/
//		if("insert_One_group".equals(action)){
//			String groupName = req.getParameter("groupName").trim();
//			String insertPic = req.getParameter("insertPic");
//			//req.getPart("file").write(getServletContext().getRealPath("/images_uploaded")+"/file.gif");
//			
//			BPCheckVO groupInfoVO = new BPCheckVO();
//			
//			groupInfoVO.setGroup_name(groupName);
//			HttpSession session = req.getSession();
//			String se = (String) session.getAttribute("insertPic");
//			System.out.println("se"+se);
//			if(insertPic.equals(se)){
//				
//				session.removeAttribute("insertPic");
//				String se2 = (String) session.getAttribute("insertPic");
//				System.out.println("se2"+se2);
//				Part filePart1 = req.getPart("fname");
//				InputStream in = filePart1.getInputStream();
//				byte[] buf = new byte[in.available()];
//				
//				//int  length=-1;
////				if(se != null){
////					return;
////				}
//				in.read(buf);
//				groupInfoSvc.addGroupInfoOne(groupName,buf);
//				in.close();
//				RequestDispatcher fr = req.getRequestDispatcher("/back-front/food.jsp");
//				fr.forward(req, res);
//				
//				
//			}else{
//				RequestDispatcher fr = req.getRequestDispatcher("/back-front/food.jsp");
//				fr.forward(req, res);
//			}
//			
//			
//		}
//		/*------------------------------------------------*/
//		
//		
//		
////		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
////
////			List<String> errorMsgs = new LinkedList<String>();
////			// Store this set in the request scope, in case we need to
////			// send the ErrorPage view.
////			req.setAttribute("errorMsgs", errorMsgs);
////
////			try {
////				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
////				String str = req.getParameter("foodno");
////				if (str == null || (str.trim()).length() == 0) {
////					errorMsgs.add("�п�J���u�s��");
////				}
////				// Send the use back to the form, if there were errors
////				if (!errorMsgs.isEmpty()) {
////					RequestDispatcher failureView = req
////							.getRequestDispatcher("/back-front/food.jsp");
////					failureView.forward(req, res);
////					return;//�{�����_
////				}
////				
////				Integer empno = null;
////				try {
////					empno = new Integer(str);
////				} catch (Exception e) {
////					errorMsgs.add("���u�s���榡�����T");
////				}
////				// Send the use back to the form, if there were errors
////				if (!errorMsgs.isEmpty()) {
////					RequestDispatcher failureView = req
////							.getRequestDispatcher("/emp/select_page.jsp");
////					failureView.forward(req, res);
////					return;//�{�����_
////				}
////				
////				/***************************2.�}�l�d�߸��*****************************************/
////				FoodService foodSvc = new FoodService();
////				FoodVO foodVO = foodSvc.getOneFood(empno);
////				if (foodVO == null) {
////					errorMsgs.add("�d�L���");
////				}
////				// Send the use back to the form, if there were errors
////				if (!errorMsgs.isEmpty()) {
////					RequestDispatcher failureView = req
////							.getRequestDispatcher("/emp/select_page.jsp");
////					failureView.forward(req, res);
////					return;//�{�����_
////				}
////				
////				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
////				req.setAttribute("empVO", foodVO); // ��Ʈw���X��empVO����,�s�Jreq
////				String url = "/emp/listOneEmp.jsp";
////				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
////				successView.forward(req, res);
////
////				/***************************��L�i�઺���~�B�z*************************************/
////			} catch (Exception e) {
////				errorMsgs.add("�L�k���o���:" + e.getMessage());
////				RequestDispatcher failureView = req
////						.getRequestDispatcher("/emp/select_page.jsp");
////				failureView.forward(req, res);
////			}
////		}
////		
////		
////		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
////
////			List<String> errorMsgs = new LinkedList<String>();
////			// Store this set in the request scope, in case we need to
////			// send the ErrorPage view.
////			req.setAttribute("errorMsgs", errorMsgs);
////			
////			try {
////				/***************************1.�����ШD�Ѽ�****************************************/
////				Integer empno = new Integer(req.getParameter("empno"));
////				
////				/***************************2.�}�l�d�߸��****************************************/
////				FoodService empSvc = new FoodService();
////				FoodVO foodVO = empSvc.getOneEmp(foodno);
////								
////				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
////				req.setAttribute("foodVO", foodVO);         // ��Ʈw���X��empVO����,�s�Jreq
////				String url = "/emp/update_emp_input.jsp";
////				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
////				successView.forward(req, res);
////
////				/***************************��L�i�઺���~�B�z**********************************/
////			} catch (Exception e) {
////				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
////				RequestDispatcher failureView = req
////						.getRequestDispatcher("/emp/listAllEmp.jsp");
////				failureView.forward(req, res);
////			}
////		}
////		
////		
////		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
////			
////			List<String> errorMsgs = new LinkedList<String>();
////			// Store this set in the request scope, in case we need to
////			// send the ErrorPage view.
////			req.setAttribute("errorMsgs", errorMsgs);
////		
////			try {
////				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
////				Integer empno = new Integer(req.getParameter("empno").trim());
////				String ename = req.getParameter("ename").trim();
////				String job = req.getParameter("job").trim();				
////				
////				java.sql.Date hiredate = null;
////				try {
////					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
////				} catch (IllegalArgumentException e) {
////					hiredate=new java.sql.Date(System.currentTimeMillis());
////					errorMsgs.add("�п�J���!");
////				}
////
////				Double sal = null;
////				try {
////					sal = new Double(req.getParameter("sal").trim());
////				} catch (NumberFormatException e) {
////					sal = 0.0;
////					errorMsgs.add("�~���ж�Ʀr.");
////				}
////
////				Double comm = null;
////				try {
////					comm = new Double(req.getParameter("comm").trim());
////				} catch (NumberFormatException e) {
////					comm = 0.0;
////					errorMsgs.add("�����ж�Ʀr.");
////				}
////
////				Integer deptno = new Integer(req.getParameter("deptno").trim());
////
////				EmpVO empVO = new EmpVO();
////				empVO.setEmpno(empno);
////				empVO.setEname(ename);
////				empVO.setJob(job);
////				empVO.setHiredate(hiredate);
////				empVO.setSal(sal);
////				empVO.setComm(comm);
////				empVO.setDeptno(deptno);
////
////				// Send the use back to the form, if there were errors
////				if (!errorMsgs.isEmpty()) {
////					req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
////					RequestDispatcher failureView = req
////							.getRequestDispatcher("/emp/update_emp_input.jsp");
////					failureView.forward(req, res);
////					return; //�{�����_
////				}
////				
////				/***************************2.�}�l�ק���*****************************************/
////				EmpService empSvc = new EmpService();
////				empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
////				
////				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
////				req.setAttribute("empVO", empVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
////				String url = "/emp/listOneEmp.jsp";
////				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
////				successView.forward(req, res);
////
////				/***************************��L�i�઺���~�B�z*************************************/
////			} catch (Exception e) {
////				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
////				RequestDispatcher failureView = req
////						.getRequestDispatcher("/emp/update_emp_input.jsp");
////				failureView.forward(req, res);
////			}
////		}
////
////        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
////			
////			List<String> errorMsgs = new LinkedList<String>();
////			// Store this set in the request scope, in case we need to
////			// send the ErrorPage view.
////			req.setAttribute("errorMsgs", errorMsgs);
////
////			try {
////				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
////				String ename = req.getParameter("ename").trim();
////				String job = req.getParameter("job").trim();
////				
////				java.sql.Date hiredate = null;
////				try {
////					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
////				} catch (IllegalArgumentException e) {
////					hiredate=new java.sql.Date(System.currentTimeMillis());
////					errorMsgs.add("�п�J���!");
////				}
////				
////				Double sal = null;
////				try {
////					sal = new Double(req.getParameter("sal").trim());
////				} catch (NumberFormatException e) {
////					sal = 0.0;
////					errorMsgs.add("�~���ж�Ʀr.");
////				}
////				
////				Double comm = null;
////				try {
////					comm = new Double(req.getParameter("comm").trim());
////				} catch (NumberFormatException e) {
////					comm = 0.0;
////					errorMsgs.add("�����ж�Ʀr.");
////				}
////				
////				Integer deptno = new Integer(req.getParameter("deptno").trim());
////
////				EmpVO empVO = new EmpVO();
////				empVO.setEname(ename);
////				empVO.setJob(job);
////				empVO.setHiredate(hiredate);
////				empVO.setSal(sal);
////				empVO.setComm(comm);
////				empVO.setDeptno(deptno);
////
////				// Send the use back to the form, if there were errors
////				if (!errorMsgs.isEmpty()) {
////					req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
////					RequestDispatcher failureView = req
////							.getRequestDispatcher("/emp/addEmp.jsp");
////					failureView.forward(req, res);
////					return;
////				}
////				
////				/***************************2.�}�l�s�W���***************************************/
////				EmpService empSvc = new EmpService();
////				empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
////				
////				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
////				String url = "/emp/listAllEmp.jsp";
////				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
////				successView.forward(req, res);				
////				
////				/***************************��L�i�઺���~�B�z**********************************/
////			} catch (Exception e) {
////				errorMsgs.add(e.getMessage());
////				RequestDispatcher failureView = req
////						.getRequestDispatcher("/emp/addEmp.jsp");
////				failureView.forward(req, res);
////			}
////		}
////		
////		
////		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
////
////			List<String> errorMsgs = new LinkedList<String>();
////			// Store this set in the request scope, in case we need to
////			// send the ErrorPage view.
////			req.setAttribute("errorMsgs", errorMsgs);
////	
////			try {
////				/***************************1.�����ШD�Ѽ�***************************************/
////				Integer empno = new Integer(req.getParameter("empno"));
////				
////				/***************************2.�}�l�R�����***************************************/
////				EmpService empSvc = new EmpService();
////				empSvc.deleteEmp(empno);
////				
////				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
////				String url = "/emp/listAllEmp.jsp";
////				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
////				successView.forward(req, res);
////				
////				/***************************��L�i�઺���~�B�z**********************************/
////			} catch (Exception e) {
////				errorMsgs.add("�R����ƥ���:"+e.getMessage());
////				RequestDispatcher failureView = req
////						.getRequestDispatcher("/emp/listAllEmp.jsp");
////				failureView.forward(req, res);
////			}
////		}
//	}

		
//}
