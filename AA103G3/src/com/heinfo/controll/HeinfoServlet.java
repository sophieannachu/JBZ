package com.heinfo.controll;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.heinfo.model.HeinfoService;
import com.heinfo.model.HeinfoVO;
@WebServlet("/uploadServlet3.do")
@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=-1,maxRequestSize=-1)
public class HeinfoServlet extends HttpServlet {
	Connection con;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("okkkk11111");
		System.out.println(action);
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("heinfono");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入資訊編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer heinfono = null;
				try {
					heinfono = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 2.開始查詢資料
				 *****************************************/
				HeinfoService heinfoSvc = new HeinfoService();
				HeinfoVO heinfoVO = heinfoSvc.getOneHeinfo(heinfono);
				if (heinfoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("heinfoVO", heinfoVO); // 資料庫取出的empVO物件,存入req
				String url = "/heinfo/listOneHeinfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數
				 ****************************************/
				Integer heinfono = new Integer(req.getParameter("heinfono"));
//				String requestURL = new String(req.getParameter("requestURL"));
				/***************************
				 * 2.開始查詢資料
				 ****************************************/
				HeinfoService heinfoSvc = new HeinfoService();
				HeinfoVO heinfoVO = heinfoSvc.getOneHeinfo(heinfono);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("heinfoVO", heinfoVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/heinfoIndex.jsp?action=update_heinfo_input";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/listAllHeinfo.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				Integer heinfono = new Integer(req.getParameter("heinfono").trim());
			
				String heinfoname = req.getParameter("heinfoname").trim();
				
				String heinfodetail = req.getParameter("heinfodetail").trim();
				
//				String requestURL = new String(req.getParameter("requestURL"));
				java.sql.Date heinfodate = null;
				try {
					heinfodate = java.sql.Date.valueOf(req.getParameter("heinfodate").trim());
				} catch (IllegalArgumentException e) {
					heinfodate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
//				byte[] heinfophoto = req.getParameter("heinfophoto").trim();
				
				Part filePart1 = req.getPart("heinfophoto");
				
				InputStream in = filePart1.getInputStream();
				byte[] buf = new byte[in.available()];
			

				in.read(buf);
				in.close();
				HeinfoVO heinfoVO = new HeinfoVO();
				heinfoVO.setHeinfono(heinfono);
				heinfoVO.setHeinfoname(heinfoname);
				heinfoVO.setHeinfodetail(heinfodetail);
				heinfoVO.setHeinfodate(heinfodate);

				heinfoVO.setHeinfophoto(buf);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("heinfoVO", heinfoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/update_heinfo_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
//
//				/***************************
//				 * 2.開始修改資料
//				 *****************************************/
				HeinfoService heinfoSvc = new HeinfoService();
				
				heinfoVO = heinfoSvc.updateHeinfo(heinfono, heinfoname, heinfodetail, heinfodate, buf);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("heinfoVO", heinfoVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/heinfoIndex.jsp?action=heinfoIndex";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/update_heinfo_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		if ("insert".equals(action)) { // 來自add
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String heinfoname = req.getParameter("heinfoname").trim();
				String heinfodetail = req.getParameter("heinfodetail").trim();
				String requestURL = new String(req.getParameter("requestURL"));
				java.sql.Date heinfodate = null;
				try {
					heinfodate = java.sql.Date.valueOf(req.getParameter("heinfodate").trim());
				} catch (IllegalArgumentException e) {
					heinfodate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
					//新增照片
				 //byte[] heinfophoto = null;
//				String heinfophoto = req.getParameter("heinfophoto");
//				HttpSession session = req.getSession();
//				String se = (String) session.getAttribute("insertPic");
				
				
			
				
					
//					session.removeAttribute("insertPic");
//					String se2 = (String) session.getAttribute("insertPic");
					
					Part filePart1 = req.getPart("heinfophoto");
					InputStream in = filePart1.getInputStream();
					byte[] buf = new byte[in.available()];
					
					in.read(buf);
					in.close();
					
					HeinfoVO heinfoVO = new HeinfoVO();
					heinfoVO.setHeinfoname(heinfoname);
					heinfoVO.setHeinfodetail(heinfodetail);
					heinfoVO.setHeinfodate(heinfodate);
					heinfoVO.setHeinfophoto(buf);
//					RequestDispatcher fr = req.getRequestDispatcher(requestURL);
//					fr.forward(req, res);
					HeinfoService heinfoSvc = new HeinfoService();
					heinfoVO = heinfoSvc.addHeinfo(heinfoname, heinfodetail, heinfodate, buf);

					/***************************
					 * 3.新增完成,準備轉交(Send the Success view)
					 ***********/
				
//					String url = "/heinfo/listAllHeinfo.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(requestURL + "?action=heinfoIndex"); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);

//					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("heinfoVO", heinfoVO); // 含有輸入格式錯誤的empVO物件,也存入req
//						RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/addHeinfo.jsp");
//						failureView.forward(req, res);
//						return;
//					}
					/***************************
					 * 2.開始新增資料
					 ***************************************/
					
//				} catch (Exception e) {
//					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/addHeinfo.jsp");
//					failureView.forward(req, res);
					
			
		}
					/*************************** 其他可能的錯誤處理 **********************************/
					
//				}else{
//					RequestDispatcher fr = req.getRequestDispatcher("food.jsp");
//					fr.forward(req, res);
//				}
				
				
				
//				File pic = new File("C:\\AA103_webApp\\eclipse_WTP_workspace1\\AA103G3\\WebContent\\IMAGES","a4.jpg");
//					byte[] bfile = new byte[(int)pic.length()];
//					FileInputStream fin;
//					try {
//						fin = new FileInputStream(pic);
//						fin.read(bfile);
//						fin.close();
//					} catch (Exception e) {
//						e.printStackTrace();
//					} 

				
				
				// Send the use back to the form, if there were errors
				
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數
				 ***************************************/
				Integer heinfono = new Integer(req.getParameter("heinfono"));
				String requestURL = new String(req.getParameter("requestURL"));
				/***************************
				 * 2.開始刪除資料
				 ***************************************/
				HeinfoService heinfoSvc = new HeinfoService();
				heinfoSvc.deleteHeinfo(heinfono);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
//				String url = "/heinfo/listAllHeinfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL + "?action=heinfoIndex");// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/listAllHeinfo.jsp");
				failureView.forward(req, res);
			}
		}

		
	}

}
