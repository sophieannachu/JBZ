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
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("clockno");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入員工編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
				Integer clockno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************2.開始查詢資料*****************************************/
				ClockService clockSvc = new ClockService();
				ClockVO clockVO = clockSvc.getOneClock(clockno);
//				if (empVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clockVO", clockVO); // 資料庫取出的empVO物件,存入req
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer clockno = new Integer(req.getParameter("clockno"));
				
				/***************************2.開始查詢資料****************************************/
				ClockService clockSvc = new ClockService();
				ClockVO clockVO = clockSvc.getOneClock(clockno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("clockVO", clockVO); // 資料庫取出的empVO物件,存入req
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
	
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
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
//					req.setAttribute("empVO", clockVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
				
				/***************************2.開始修改資料*****************************************/
				ClockService clockSvc = new ClockService();
				clockVO = clockSvc.updateClock(clockno, clocktime1, clockinfo, clocksche, clockring,clocktype,memno);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(deptno)); // 資料庫取出的list物件,存入request

              
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
        	
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
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
					clocksche = "每天";
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


				/***************************2.開始修改資料*****************************************/
				ClockService clockSvc = new ClockService();
				clockVO = clockSvc.addClock( clocktime1, clockinfo, clocksche, clockring,clocktype,memno);
					
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("clocksweat", "clocksweat");
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//			}
		}
		
       
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				Integer clockno = new Integer(req.getParameter("clockno"));
				
				/***************************2.開始刪除資料***************************************/
				ClockService clockSvc = new ClockService();
				ClockVO clockVO = clockSvc.getOneClock(clockno);
				clockSvc.deleteClock(clockno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // 資料庫取出的list物件,存入request
				
			
				RequestDispatcher successView = req.getRequestDispatcher(requestURL); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
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
