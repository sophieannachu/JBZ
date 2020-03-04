package com.basiccheck.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basiccheck.model.*;
/**
 * Servlet implementation class BasicCheckServlet
 */
public class BasicCheckServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL");

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					
					Integer weight = null;
					try {
						if(req.getParameter("weight").trim()!=""){
							weight = new Integer(req.getParameter("weight").trim());
						}					
					} catch (NumberFormatException e) {
						weight = 0;
					}
					
					Double bmi = null;
					try {
						if(req.getParameter("bmi").trim()!=""){
							bmi = new Double(req.getParameter("bmi").trim());
						}
						
					} catch (NumberFormatException e) {
						bmi = 0.0;
					}
					
					Double bmr = null;
					try {
						if(req.getParameter("bmr").trim()!=""){
							bmr = new Double(req.getParameter("bmr").trim());
						}
						
					} catch (NumberFormatException e) {
						bmr = 0.0;
					}
				
					Double bFat = null;
					try {
						if(req.getParameter("bFat").trim()!=""){
							bFat = new Double(req.getParameter("bFat").trim());
						}
						
					} catch (NumberFormatException e) {
						bFat = 0.0;
					}
					
					Double waisyline = null;
					try {
						if(req.getParameter("waisyline").trim()!=""){
							waisyline = new Double(req.getParameter("waisyline").trim());
						}
						
					} catch (NumberFormatException e) {
						waisyline = 0.0;
					}
					
					java.sql.Timestamp checkDate = null;
					try {
						checkDate = java.sql.Timestamp.valueOf(req.getParameter("checkDate").trim());
					} catch (IllegalArgumentException e) {
						checkDate=new java.sql.Timestamp(System.currentTimeMillis());
					}
					
					
					Integer memno = new Integer(req.getParameter("memno").trim());


					BasicCheckVO basicCheckVO = new BasicCheckVO();
					basicCheckVO.setWeight(weight);
					basicCheckVO.setBmi(bmi);
					basicCheckVO.setBmr(bmr);
					basicCheckVO.setbFat(bFat);
					basicCheckVO.setWaisyline(waisyline);
					basicCheckVO.setCheckDate(checkDate);
					basicCheckVO.setMemno(memno);
										
					/***************************2.開始新增資料***************************************/
					BasicCheckService basicCheckSvc = new BasicCheckService();
					basicCheckVO = basicCheckSvc.addBasicCheck(weight,bmi,bmr,bFat,waisyline,checkDate,memno);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					errorMsgs.add("新增成功！");
					String url = requestURL;
//					String url = "/basiccheck/listAllbasicCheck.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交原jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
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
					Integer basicCheckno = new Integer(req.getParameter("basicCheckno"));
					
					/***************************2.開始查詢資料****************************************/
					BasicCheckService basicCheckSvc = new BasicCheckService();
					BasicCheckVO basicCheckVO = basicCheckSvc.getOneBasicCheck(basicCheckno);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("basicCheckVO", basicCheckVO); // 資料庫取出的empVO物件,存入req
					String url = requestURL;
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
				
//				String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

//				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					Integer basicCheckno = new Integer(req.getParameter("basicCheckno").trim());
					
					if(req.getParameter("weight").trim()==""&&req.getParameter("bmi").trim()==""&&req.getParameter("bmr").trim()==""&&
							req.getParameter("bFat").trim()==""&&req.getParameter("waisyline").trim()==""){
						errorMsgs.add("體重、腰圍、體脂請至少填寫一個");
					}
					Integer weight = null;
					try {
						if(req.getParameter("weight").trim()!=""){
							weight = new Integer(req.getParameter("weight").trim());
						}					
					} catch (NumberFormatException e) {
						weight = 0;
						errorMsgs.add("體重請填數字");
					}
					
					Double bmi = null;
					try {
						if(req.getParameter("bmi").trim()!=""){
							bmi = new Double(req.getParameter("bmi").trim());
						}
						
					} catch (NumberFormatException e) {
						bmi = 0.0;
						errorMsgs.add("身體質量數值應為數字");
					}
					
					Double bmr = null;
					try {
						if(req.getParameter("bmr").trim()!=""){
							bmr = new Double(req.getParameter("bmr").trim());
						}
						
					} catch (NumberFormatException e) {
						bmr = 0.0;
						errorMsgs.add("基礎代謝率應為數字");
					}
				
					Double bFat = null;
					try {
						if(req.getParameter("bFat").trim()!=""){
							bFat = new Double(req.getParameter("bFat").trim());
						}
						
					} catch (NumberFormatException e) {
						bFat = 0.0;
						errorMsgs.add("體脂應填數字");
					}
					
					Double waisyline = null;
					try {
						if(req.getParameter("waisyline").trim()!=""){
							waisyline = new Double(req.getParameter("waisyline").trim());
						}
						
					} catch (NumberFormatException e) {
						waisyline = 0.0;
						errorMsgs.add("腰圍應填數字");
					}
					
					java.sql.Timestamp checkDate = null;
					try {
						checkDate = java.sql.Timestamp.valueOf(req.getParameter("checkDate").trim());
					} catch (IllegalArgumentException e) {
						checkDate=new java.sql.Timestamp(System.currentTimeMillis());
						errorMsgs.add("請輸入日期時間!");
					}
					
					Integer memno = new Integer(req.getParameter("memno").trim());


					BasicCheckVO basicCheckVO = new BasicCheckVO();
					basicCheckVO.setBasicCheckno(basicCheckno);
					basicCheckVO.setWeight(weight);
					basicCheckVO.setBmi(bmi);
					basicCheckVO.setBmr(bmr);
					basicCheckVO.setbFat(bFat);
					basicCheckVO.setWaisyline(waisyline);
					basicCheckVO.setCheckDate(checkDate);
					basicCheckVO.setMemno(memno);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("basicCheckVO", basicCheckVO);
						System.out.println("shaa");// 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/basiccheck/update_basicCheck_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					BasicCheckService basicCheckSvc = new BasicCheckService();
					basicCheckVO = basicCheckSvc.updateBasicCheck(basicCheckno, weight, bmi, bmr, bFat,waisyline, checkDate,memno);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/				
//					errorMsgs.add("修改成功！");
//	                String url = "/front-end/healthCheck.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
//					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
//				} catch (Exception e) {
//					errorMsgs.add("修改資料失敗:"+e.getMessage());
//					System.out.println("fufu");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/basiccheck/update_basicCheck_input.jsp");
//					failureView.forward(req, res);
//				}
			}
			if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

				try {
					/***************************1.接收請求參數***************************************/
					Integer basicCheckno = new Integer(req.getParameter("basicCheckno"));
					
					/***************************2.開始刪除資料***************************************/
					BasicCheckService basicCheckSvc = new BasicCheckService();
					BasicCheckVO basicCheckVO = basicCheckSvc.getOneBasicCheck(basicCheckno);
					basicCheckSvc.deleteBasicCheck(basicCheckno);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//					DeptService deptSvc = new DeptService();
//					if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//						req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // 資料庫取出的list物件,存入request
					
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
		
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}
	}

}
