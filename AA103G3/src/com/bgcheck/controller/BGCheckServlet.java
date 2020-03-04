package com.bgcheck.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bgcheck.model.*;

/**
 * Servlet implementation class BGCheckServlet
 */
@WebServlet("/BGCheckServlet")
public class BGCheckServlet extends HttpServlet {

	public BGCheckServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert_BG".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				System.out.println(123);
				Integer bgBfMeat = null;
				try {
					if (req.getParameter("bgBfMeat").trim() != "") {
						bgBfMeat = new Integer(req.getParameter("bgBfMeat").trim());
					}
				} catch (NumberFormatException e) {
					bgBfMeat = 0;
				}

				Integer bgAfMeat = null;
				try {
					if (req.getParameter("bgAfMeat").trim() != "") {
						bgAfMeat = new Integer(req.getParameter("bgAfMeat").trim());
					}

				} catch (NumberFormatException e) {
					bgAfMeat = 0;
				}

				Integer bgBfSleep = null;
				try {
					if (req.getParameter("bgBfSleep").trim() != "") {
						bgBfSleep = new Integer(req.getParameter("bgBfSleep").trim());
					}

				} catch (NumberFormatException e) {
					bgBfSleep = 0;
				}

				java.sql.Timestamp checkDate = null;
				try {
					checkDate = java.sql.Timestamp.valueOf(req.getParameter("checkDate").trim());
				} catch (IllegalArgumentException e) {
					checkDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J����ɶ�!");
				}

				Integer memno = new Integer(req.getParameter("memno").trim());

				BGCheckVO bgCheckVO = new BGCheckVO();
				bgCheckVO.setBgBfMeat(bgBfMeat);
				bgCheckVO.setBgAfMeat(bgAfMeat);
				bgCheckVO.setBgBfSleep(bgBfSleep);
				bgCheckVO.setCheckDate(checkDate);
				bgCheckVO.setMemno(memno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bgCheckVO", bgCheckVO);// �t����J�榡���~��basicCheckVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.�}�l�s�W���
				 ***************************************/
				BGCheckService bgCheckSvc = new BGCheckService();
				bgCheckVO = bgCheckSvc.addBGCheck(bgBfMeat, bgAfMeat, bgBfSleep, checkDate, memno);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				errorMsgs.add("�s�W���");
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
//		if ("getOne_For_Update_BG".equals(action)) { // �Ӧ�listAllEmp.jsp ��
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
//				Integer bgCheckno = new Integer(req.getParameter("bgCheckno"));
//
//				/***************************
//				 * 2.�}�l�d�߸��
//				 ****************************************/
//				BGCheckService bgCheckSvc = new BGCheckService();
//				BGCheckVO bgCheckVO = bgCheckSvc.getOneBGCheck(bgCheckno);
//
//				/***************************
//				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
//				 ************/
//				req.setAttribute("bgCheckVO", bgCheckVO); // ��Ʈw���X��empVO����,�s�Jreq
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

		if ("update_BG".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				Integer bgCheckno = new Integer(req.getParameter("bgCheckno").trim());

				Integer bgBfMeat = null;
				try {
					if (req.getParameter("bgBfMeat").trim() != "") {
						bgBfMeat = new Integer(req.getParameter("bgBfMeat").trim());
					}
				} catch (NumberFormatException e) {
					bgBfMeat = 0;
				}
				Integer bgAfMeat = null;
				try {
					if (req.getParameter("bgAfMeat").trim() != "") {
						bgAfMeat = new Integer(req.getParameter("bgAfMeat").trim());
					}

				} catch (NumberFormatException e) {
					bgAfMeat = 0;
				}

				Integer bgBfSleep = null;
				try {
					if (req.getParameter("bgBfSleep").trim() != "") {
						bgBfSleep = new Integer(req.getParameter("bgBfSleep").trim());
					}

				} catch (NumberFormatException e) {
					bgBfSleep = 0;
				}
				
				java.sql.Timestamp checkDate = null;
				try {
					checkDate = java.sql.Timestamp.valueOf(req.getParameter("checkDate").trim());
				} catch (IllegalArgumentException e) {
					checkDate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J����ɶ�!");
				}

				Integer memno = new Integer(req.getParameter("memno").trim());
				
				
				BGCheckVO bgCheckVO = new BGCheckVO();				
				bgCheckVO.setBgCheckno(bgCheckno);
				bgCheckVO.setBgBfMeat(bgBfMeat);
				bgCheckVO.setBgAfMeat(bgAfMeat);
				bgCheckVO.setBgBfSleep(bgBfSleep);
				bgCheckVO.setCheckDate(checkDate);
				bgCheckVO.setMemno(memno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bgCheckVO", bgCheckVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/healthCheck.jsp?action=getOne_For_Update_BG");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/***************************
				 * 2.�}�l�ק���
				 *****************************************/
				BGCheckService bgCheckSvc = new BGCheckService();
				bgCheckVO = bgCheckSvc.updateBGCheck(bgCheckno, bgBfMeat, bgAfMeat, bgBfSleep,
						checkDate, memno);


		}
		if ("delete_BG".equals(action)) { // �Ӧ�listAllEmp.jsp ��
										// /dept/listEmps_ByDeptno.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|:
																// �i�ର�i/emp/listAllEmp.jsp�j
																// ��
																// �i/dept/listEmps_ByDeptno.jsp�j
																// �� �i
																// /dept/listAllDept.jsp�j

			try {
				/***************************
				 * 1.�����ШD�Ѽ�
				 ***************************************/
				Integer bgCheckno = new Integer(req.getParameter("bgCheckno"));

				/***************************
				 * 2.�}�l�R�����
				 ***************************************/
				BGCheckService bgCheckSvc = new BGCheckService();
				BGCheckVO bgCheckVO = bgCheckSvc.getOneBGCheck(bgCheckno);
				bgCheckSvc.deleteBGCheck(bgCheckno);

				/***************************

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				System.out.println("OK123");
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}

}
