package com.sport.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sport.model.*;

public class SportServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

		if ("getOne_For_Display_GPS".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			String requestURL = req.getParameter("requestURL");
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer sportrec_no = Integer.parseInt(req.getParameter("sportrec_no"));
								
				/***************************2.�}�l�d�߸��*****************************************/
				SportService sportSvc = new SportService();
				Sport sport = sportSvc.getOneGPSSport(sportrec_no);
				if (sport == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("sport", sport); // ��Ʈw���X��empVO����,�s�Jreq
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}		
		}
		
		if ("delete_For_watch".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer sportrec_no = Integer.parseInt(req.getParameter("sportrec_no"));
				System.out.println(sportrec_no);
								
				/***************************2.�}�l�d�߸��*****************************************/
				SportService sportSvc = new SportService();
				sportSvc.deleteSport(sportrec_no);

				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/


				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}		
		}

		if ("delete_For_sport".equals(action)) { // �Ӧ�addEmp.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer sportrec_no = Integer.parseInt(req.getParameter("sportrec_no"));
				System.out.println(sportrec_no);
								
				/***************************2.�}�l�d�߸��*****************************************/
				SportService sportSvc = new SportService();
				sportSvc.deleteSport(sportrec_no);

				errorMsgs.add("�R�����");
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				String url = "/front-end/sportdairy.jsp?action=sportanyl";
				System.out.println("OK");
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}		
		}
		
		if ("getOne_For_Other_GPS".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer sportrec_no = Integer.parseInt(req.getParameter("sportrec_no"));
								
				/***************************2.�}�l�d�߸��*****************************************/
				SportService sportSvc = new SportService();
				Sport sport = sportSvc.getOneGPSSport(sportrec_no);
				if (sport == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("sport", sport); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/gpssport/GPSsport_forOther.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("");
				failureView.forward(req, res);
			}		
		}
		
	}
}
