package com.bpcheck.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bpcheck.model.*;


/**
 * Servlet implementation class BPcheckServlet
 */
public class BPcheckServlet extends HttpServlet {

    public BPcheckServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert_BP".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				Integer sPressure = null;
				try {
					sPressure = new Integer(req.getParameter("sPressure").trim());
				} catch (NumberFormatException e) {
					sPressure = 0;
				}

				Integer dPressure = null;
				try {
					dPressure = new Integer(req.getParameter("dPressure").trim());
				} catch (NumberFormatException e) {
					dPressure = 0;
				}

				java.sql.Timestamp checkDate = null;
				try {
					checkDate = java.sql.Timestamp.valueOf(req.getParameter("checkDate").trim());
				} catch (IllegalArgumentException e) {
					checkDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J����ɶ�!");
				}

				Integer memno = new Integer(req.getParameter("memno").trim());

				BPCheckVO bpCheckVO = new BPCheckVO();
				bpCheckVO.setsPressure(sPressure);
				bpCheckVO.setdPressure(dPressure);
				bpCheckVO.setCheckDate(checkDate);
				bpCheckVO.setMemno(memno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bpCheckVO", bpCheckVO);// �t����J�榡���~��basicCheckVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.�}�l�s�W���
				 ***************************************/
				BPCheckService bpCheckSvc = new BPCheckService();
				bpCheckVO = bpCheckSvc.addBPCheck(sPressure, dPressure,checkDate, memno);
				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				errorMsgs.add("�s�W���\�I");
				String url = requestURL;
				// String url = "/basiccheck/listAllbasicCheck.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("no");
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
//		if ("getOne_For_Update_BP".equals(action)) { // �Ӧ�listAllEmp.jsp ��
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
//				Integer bpCheckno = new Integer(req.getParameter("bpCheckno"));
//
//				/***************************
//				 * 2.�}�l�d�߸��
//				 ****************************************/
//				BPCheckService bpCheckSvc = new BPCheckService();
//				BPCheckVO bpCheckVO = bpCheckSvc.getOneBPCheck(bpCheckno);
//
//				/***************************
//				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
//				 ************/
//				req.setAttribute("bpCheckVO", bpCheckVO); // ��Ʈw���X��empVO����,�s�Jreq
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

		if ("update_BP".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				Integer bpCheckno = new Integer(req.getParameter("bpCheckno").trim());
				
				Integer sPressure = null;
				try {
					sPressure = new Integer(req.getParameter("sPressure").trim());
				} catch (NumberFormatException e) {
					sPressure = 0;
				}

				Integer dPressure = null;
				try {
					dPressure = new Integer(req.getParameter("dPressure").trim());
				} catch (NumberFormatException e) {
					dPressure = 0;
				}

				java.sql.Timestamp checkDate = null;
				try {
					checkDate = java.sql.Timestamp.valueOf(req.getParameter("checkDate").trim());
				} catch (IllegalArgumentException e) {
					checkDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J����ɶ�!");
				}
				
				Integer memno = new Integer(req.getParameter("memno").trim());
				
				BPCheckVO bpCheckVO = new BPCheckVO();				
				bpCheckVO.setBpCheckno(bpCheckno);
				bpCheckVO.setsPressure(sPressure);
				bpCheckVO.setdPressure(dPressure);
				bpCheckVO.setCheckDate(checkDate);
				bpCheckVO.setMemno(memno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bpCheckVO", bpCheckVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/healthCheck.jsp?action=getOne_For_Update_BP");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/***************************
				 * 2.�}�l�ק���
				 *****************************************/
				BPCheckService bpCheckSvc = new BPCheckService();
				bpCheckVO = bpCheckSvc.updateBPCheck(bpCheckno, sPressure, dPressure,checkDate, memno);

				
		}
		if ("delete_BP".equals(action)) { // �Ӧ�listAllEmp.jsp ��
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
				Integer bpCheckno = new Integer(req.getParameter("bpCheckno"));

				/***************************
				 * 2.�}�l�R�����
				 ***************************************/
				BPCheckService bpCheckSvc = new BPCheckService();
				BPCheckVO bpCheckVO = bpCheckSvc.getOneBPCheck(bpCheckno);
				bpCheckSvc.deleteBPCheck(bpCheckno);


				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
