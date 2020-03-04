package com.sleepcheck.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sleepcheck.model.*;

/**
 * Servlet implementation class SleepCheckServlet
 */
public class SleepCheckServlet extends HttpServlet {

    public SleepCheckServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("insert_Sleep".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				java.sql.Timestamp bedTime = null;
				try {
					bedTime = java.sql.Timestamp.valueOf(req.getParameter("bedTime").trim());
				} catch (IllegalArgumentException e) {
					bedTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
				
				java.sql.Timestamp wakeTime = null;
				try {
					wakeTime = java.sql.Timestamp.valueOf(req.getParameter("wakeTime").trim());
				} catch (IllegalArgumentException e) {
					wakeTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
				
				String sleepTime = null;
				Integer sleepMin=0;
				try {
					sleepTime = req.getParameter("sleepTime").trim();
					sleepMin = Integer.parseInt((sleepTime.split("�p�� ")[0]))*60
							+Integer.parseInt(sleepTime.split("�p�� ")[1].split("��")[0]);
				} catch (NumberFormatException e) {
					sleepMin = 0;
				}

				Integer memno = new Integer(req.getParameter("memno").trim());

				SleepCheckVO sleepCheckVO = new SleepCheckVO();
				sleepCheckVO.setBedTime(bedTime);
				sleepCheckVO.setWakeTime(wakeTime);
				sleepCheckVO.setSleepTime(sleepMin);
				sleepCheckVO.setMemno(memno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sleepCheckVO", sleepCheckVO);// �t����J�榡���~��basicCheckVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.�}�l�s�W���
				 ***************************************/
				SleepCheckService sleepCheckSvc = new SleepCheckService();
				sleepCheckVO = sleepCheckSvc.addSleepCheck(bedTime, wakeTime,sleepMin, memno);
				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				errorMsgs.add("�s�W���\�I");
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
//		if ("getOne_For_Update_Sleep".equals(action)) { // �Ӧ�listAllEmp.jsp ��
//													// /dept/listEmps_ByDeptno.jsp
//													// ���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			String requestURL = req.getParameter("requestURL"); 
//
//			try {
//				/***************************
//				 * 1.�����ШD�Ѽ�
//				 ****************************************/
//				Integer sleepCheckno = new Integer(req.getParameter("sleepCheckno"));
//				/***************************
//				 * 2.�}�l�d�߸��
//				 ****************************************/
//				SleepCheckService sleepCheckSvc = new SleepCheckService();
//				SleepCheckVO sleepCheckVO = sleepCheckSvc.getOneSleepCheck(sleepCheckno);
//
//				/***************************
//				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
//				 ************/
//				req.setAttribute("sleepCheckVO", sleepCheckVO); // ��Ʈw���X��empVO����,�s�Jreq
//				String url = requestURL;
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_emp_input.jsp
//				successView.forward(req, res);
//
//				/*************************** ��L�i�઺���~�B�z ************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƨ��X�ɥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//			}
//		}

		if ("update_Sleep".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				Integer sleepCheckno = new Integer(req.getParameter("sleepCheckno").trim());
				
				java.sql.Timestamp bedTime = null;
				try {
					bedTime = java.sql.Timestamp.valueOf(req.getParameter("bedTime").trim());
				} catch (IllegalArgumentException e) {
					bedTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
				
				java.sql.Timestamp wakeTime = null;
				try {
					wakeTime = java.sql.Timestamp.valueOf(req.getParameter("wakeTime").trim());
				} catch (IllegalArgumentException e) {
					wakeTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
				
				Integer sleepTime = null;
				try {
					sleepTime = new Integer(req.getParameter("sleepTime").trim());
				} catch (NumberFormatException e) {
					sleepTime = 0;
				}

				// Integer memno = new
				// Integer(req.getParameter("memno").trim());
				Integer memno = new Integer(req.getParameter("memno").trim());
				
				SleepCheckVO sleepCheckVO = new SleepCheckVO();
				sleepCheckVO.setSleepCheckno(sleepCheckno);
				sleepCheckVO.setBedTime(bedTime);
				sleepCheckVO.setWakeTime(wakeTime);
				sleepCheckVO.setSleepTime(sleepTime);
				sleepCheckVO.setMemno(memno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sleepCheckVO", sleepCheckVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/healthCheck.jsp?action=getOne_For_Update_BP");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/***************************
				 * 2.�}�l�ק���
				 *****************************************/
				SleepCheckService sleepCheckSvc = new SleepCheckService();
				sleepCheckVO = sleepCheckSvc.updateSleepCheck(sleepCheckno, bedTime, wakeTime,sleepTime, memno);


		}
		if ("delete_Sleep".equals(action)) { // �Ӧ�listAllEmp.jsp ��
										// /dept/listEmps_ByDeptno.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); 

			try {
				/***************************
				 * 1.�����ШD�Ѽ�
				 ***************************************/
				Integer sleepCheckno = new Integer(req.getParameter("sleepCheckno"));

				/***************************
				 * 2.�}�l�R�����
				 ***************************************/
				SleepCheckService sleepCheckSvc = new SleepCheckService();
				SleepCheckVO bpCheckVO = sleepCheckSvc.getOneSleepCheck(sleepCheckno);
				sleepCheckSvc.deleteSleepCheck(sleepCheckno);


				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
