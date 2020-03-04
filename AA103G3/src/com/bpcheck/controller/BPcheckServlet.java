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

		if ("insert_BP".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
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
					errorMsgs.add("請輸入日期時間!");
				}

				Integer memno = new Integer(req.getParameter("memno").trim());

				BPCheckVO bpCheckVO = new BPCheckVO();
				bpCheckVO.setsPressure(sPressure);
				bpCheckVO.setdPressure(dPressure);
				bpCheckVO.setCheckDate(checkDate);
				bpCheckVO.setMemno(memno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bpCheckVO", bpCheckVO);// 含有輸入格式錯誤的basicCheckVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}

				/***************************
				 * 2.開始新增資料
				 ***************************************/
				BPCheckService bpCheckSvc = new BPCheckService();
				bpCheckVO = bpCheckSvc.addBPCheck(sPressure, dPressure,checkDate, memno);
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				errorMsgs.add("新增成功！");
				String url = requestURL;
				// String url = "/basiccheck/listAllbasicCheck.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交原jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("no");
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
//		if ("getOne_For_Update_BP".equals(action)) { // 來自listAllEmp.jsp 或
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
//				Integer bpCheckno = new Integer(req.getParameter("bpCheckno"));
//
//				/***************************
//				 * 2.開始查詢資料
//				 ****************************************/
//				BPCheckService bpCheckSvc = new BPCheckService();
//				BPCheckVO bpCheckVO = bpCheckSvc.getOneBPCheck(bpCheckno);
//
//				/***************************
//				 * 3.查詢完成,準備轉交(Send the Success view)
//				 ************/
//				req.setAttribute("bpCheckVO", bpCheckVO); // 資料庫取出的empVO物件,存入req
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

		if ("update_BP".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
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
					errorMsgs.add("請輸入日期時間!");
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
					req.setAttribute("bpCheckVO", bpCheckVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/healthCheck.jsp?action=getOne_For_Update_BP");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/***************************
				 * 2.開始修改資料
				 *****************************************/
				BPCheckService bpCheckSvc = new BPCheckService();
				bpCheckVO = bpCheckSvc.updateBPCheck(bpCheckno, sPressure, dPressure,checkDate, memno);

				
		}
		if ("delete_BP".equals(action)) { // 來自listAllEmp.jsp 或
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
				Integer bpCheckno = new Integer(req.getParameter("bpCheckno"));

				/***************************
				 * 2.開始刪除資料
				 ***************************************/
				BPCheckService bpCheckSvc = new BPCheckService();
				BPCheckVO bpCheckVO = bpCheckSvc.getOneBPCheck(bpCheckno);
				bpCheckSvc.deleteBPCheck(bpCheckno);


				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
