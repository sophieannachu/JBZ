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
		if ("insert_Sleep".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
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
					sleepMin = Integer.parseInt((sleepTime.split("小時 ")[0]))*60
							+Integer.parseInt(sleepTime.split("小時 ")[1].split("分")[0]);
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
					req.setAttribute("sleepCheckVO", sleepCheckVO);// 含有輸入格式錯誤的basicCheckVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.開始新增資料
				 ***************************************/
				SleepCheckService sleepCheckSvc = new SleepCheckService();
				sleepCheckVO = sleepCheckSvc.addSleepCheck(bedTime, wakeTime,sleepMin, memno);
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				errorMsgs.add("新增成功！");
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交原jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
//		if ("getOne_For_Update_Sleep".equals(action)) { // 來自listAllEmp.jsp 或
//													// /dept/listEmps_ByDeptno.jsp
//													// 的請求
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
//				 * 1.接收請求參數
//				 ****************************************/
//				Integer sleepCheckno = new Integer(req.getParameter("sleepCheckno"));
//				/***************************
//				 * 2.開始查詢資料
//				 ****************************************/
//				SleepCheckService sleepCheckSvc = new SleepCheckService();
//				SleepCheckVO sleepCheckVO = sleepCheckSvc.getOneSleepCheck(sleepCheckno);
//
//				/***************************
//				 * 3.查詢完成,準備轉交(Send the Success view)
//				 ************/
//				req.setAttribute("sleepCheckVO", sleepCheckVO); // 資料庫取出的empVO物件,存入req
//				String url = requestURL;
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 ************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
//			}
//		}

		if ("update_Sleep".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
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
					req.setAttribute("sleepCheckVO", sleepCheckVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/healthCheck.jsp?action=getOne_For_Update_BP");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/***************************
				 * 2.開始修改資料
				 *****************************************/
				SleepCheckService sleepCheckSvc = new SleepCheckService();
				sleepCheckVO = sleepCheckSvc.updateSleepCheck(sleepCheckno, bedTime, wakeTime,sleepTime, memno);


		}
		if ("delete_Sleep".equals(action)) { // 來自listAllEmp.jsp 或
										// /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); 

			try {
				/***************************
				 * 1.接收請求參數
				 ***************************************/
				Integer sleepCheckno = new Integer(req.getParameter("sleepCheckno"));

				/***************************
				 * 2.開始刪除資料
				 ***************************************/
				SleepCheckService sleepCheckSvc = new SleepCheckService();
				SleepCheckVO bpCheckVO = sleepCheckSvc.getOneSleepCheck(sleepCheckno);
				sleepCheckSvc.deleteSleepCheck(sleepCheckno);


				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
