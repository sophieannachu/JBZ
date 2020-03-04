package com.clock.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clock.model.ClockService;
import com.clock.model.ClockVO;



public class ClockServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("comingcoming");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("clockno");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J���u�s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
				Integer clockno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("���u�s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ClockService clockSvc = new ClockService();
				ClockVO clockVO = clockSvc.getOneClock(clockno);
//				if (empVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("clockVO", clockVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp ���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j		
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer clockno = new Integer(req.getParameter("clockno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ClockService clockSvc = new ClockService();
				ClockVO clockVO = clockSvc.getOneClock(clockno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("clockVO", clockVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƨ��X�ɥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
	
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer clockno = new Integer(req.getParameter("clockno").trim());
				String clocktime = new String(req.getParameter("clocktime"));
				Timestamp clocktime1 = Timestamp.valueOf(clocktime); 
				String clockinfo = req.getParameter("clockinfo").trim();
				String clocksche = req.getParameter("clocksche").trim();				
				
				

				String clockring = null;
			
				clockring = new String(req.getParameter("clockring").trim());
				

				Integer clocktype = null;
				
					clocktype = new Integer(req.getParameter("clocktype").trim());
				

				Integer memno = new Integer(req.getParameter("memno").trim());

				ClockVO clockVO = new ClockVO();
				clockVO.setClockno(clockno);
				clockVO.setClocktime(clocktime1);
				clockVO.setClockinfo(clockinfo);
				clockVO.setClocksche(clocksche);
				clockVO.setClockring(clockring);
				clockVO.setClocktype(clocktype);
				clockVO.setMemno(memno);

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", clockVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //�{�����_
//				}
				
				/***************************2.�}�l�ק���*****************************************/
				ClockService clockSvc = new ClockService();
				clockVO = clockSvc.updateClock(clockno, clocktime1, clockinfo, clocksche, clockring,clocktype,memno);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/				
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(deptno)); // ��Ʈw���X��list����,�s�Jrequest

              
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);   // �ק令�\��,���^�e�X�ק諸�ӷ�����
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
        	
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String clocktime = new String(req.getParameter("clocktime"));
				java.sql.Timestamp clocktime1 = Timestamp.valueOf("1990-12-25 " + clocktime + ":55");
				
				String requestURL = req.getParameter("requestURL").trim();
				String clockinfo = req.getParameter("clockinfo").trim();
				String[] clocksches = req.getParameterValues("clocksche");
//				String clocksche = req.getParameter("clocksche").trim();				
				
				String clocksche="";
				for(String clockscheAll:clocksches){
					 
					clocksche+=clockscheAll+" ";
				}
				String clockschecopy = clocksche;
				if(clocksche.equals(clockschecopy)){
					clocksche = "�C��";
				}
				
				

			
			
				String clockring = req.getParameter("clockring").trim();
				System.out.println("clockring"+clockring);

				 
				
				 Integer clocktype = new Integer(req.getParameter("clocktype").trim());
					System.out.println("clocktype"+clocktype);

				Integer memno = new Integer(req.getParameter("memno").trim());
				System.out.println("memno"+memno);
				ClockVO clockVO = new ClockVO();
	
				clockVO.setClocktime(clocktime1);
				clockVO.setClockinfo(clockinfo);
				clockVO.setClocksche(clocksche);
				clockVO.setClockring(clockring);
				clockVO.setClocktype(clocktype);
				clockVO.setMemno(memno);


				/***************************2.�}�l�ק���*****************************************/
				ClockService clockSvc = new ClockService();
				clockVO = clockSvc.addClock( clocktime1, clockinfo, clocksche, clockring,clocktype,memno);
					
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				req.setAttribute("clocksweat", "clocksweat");
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//			}
		}
		
       
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j

			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer clockno = new Integer(req.getParameter("clockno"));
				
				/***************************2.�}�l�R�����***************************************/
				ClockService clockSvc = new ClockService();
				ClockVO clockVO = clockSvc.getOneClock(clockno);
				clockSvc.deleteClock(clockno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // ��Ʈw���X��list����,�s�Jrequest
				
			
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("nextClockPage".equals(action)) {
		
		String url="/front-end/ClockIndex.jsp";
		RequestDispatcher succseeView = req.getRequestDispatcher(url);
		succseeView.forward(req,res);
				
		}
		if ("nextClockPage1".equals(action)) {
		
			String url="/front-end/ClockIndex.jsp";
			RequestDispatcher succseeView = req.getRequestDispatcher(url);
			succseeView.forward(req,res);
			
		}
		
		
		
	}
	
}
