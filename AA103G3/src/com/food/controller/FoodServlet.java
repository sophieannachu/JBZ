package com.food.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONArray;

import com.foodlist.model.FoodService;
import com.foodlist.model.FoodVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;


public class FoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("進來了");
//		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("foodno");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入員工編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-front/food.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer empno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				FoodService foodSvc = new FoodService();
//				FoodVO foodVO = foodSvc.getOneFood(empno);
//				if (foodVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", foodVO); // 資料庫取出的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
		if ("updateFood".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				Integer fd_no = new Integer(req.getParameter("fd_no"));
				
				/***************************2.開始查詢資料****************************************/
				FoodService foodSvc = new FoodService();
				FoodVO foodVO = foodSvc.getOneFood(fd_no);
			
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("foodVO", foodVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-front/food/updateFood.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
		}
//		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			System.out.println("進來update");
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer fd_no = new Integer(req.getParameter("fd_no").trim());
				String fd_name = req.getParameter("fd_name").trim();
				 
				 Integer fd_spone = null;
				 fd_spone = new Integer(req.getParameter("fd_spone").trim());
				 
				 Integer fd_sptwo = null;
				 fd_sptwo = new Integer(req.getParameter("fd_sptwo").trim());
				 
				 Integer fd_spthr = null;
				 fd_spthr = new Integer(req.getParameter("fd_spthr").trim());
				 
				 Integer fd_spfor = null;
				 fd_spfor = new Integer(req.getParameter("fd_spfor").trim());

				 Integer fd_spfir = null;
				 fd_spfir = new Integer(req.getParameter("fd_spfir").trim());

				 Integer fd_spsix = null;
				 fd_spsix = new Integer(req.getParameter("fd_spsix").trim());
				 
				 Integer prot = null;
				 prot = new Integer(req.getParameter("prot").trim());

				 Integer fat = null;
				 fat = new Integer(req.getParameter("fat").trim());

				 Integer mono = null;
				 mono = new Integer(req.getParameter("mono").trim());
				 
				 Integer poly = null;
				 poly = new Integer(req.getParameter("poly").trim());

				 Integer sfa = null;
				 sfa = new Integer(req.getParameter("sfa").trim());

				 Integer trans = null;
				 trans = new Integer(req.getParameter("trans").trim());

				 Integer cho = null;
				 cho = new Integer(req.getParameter("cho").trim());

				 Integer carb = null;
				 carb = new Integer(req.getParameter("carb").trim());

				 Integer sugar = null;
				 sugar = new Integer(req.getParameter("sugar").trim());

				 Integer df = null;
				 df = new Integer(req.getParameter("df").trim());

				 Integer na = null;
				 na = new Integer(req.getParameter("na").trim());

				 Integer ca = null;
				 ca = new Integer(req.getParameter("ca").trim());

				 Integer k = null;
				 k = new Integer(req.getParameter("k").trim());

				 Integer cal = null;
				 cal = new Integer(req.getParameter("cal").trim());

				FoodVO foodVO = new FoodVO();
				foodVO.setFd_name(fd_name);
				foodVO.setFd_spone(fd_spone);
				foodVO.setFd_sptwo(fd_sptwo);
				foodVO.setFd_spthr(fd_spthr);
				foodVO.setFd_spfor(fd_spfor);
				foodVO.setFd_spfir(fd_spfir);
				foodVO.setFd_spsix(fd_spsix);
				foodVO.setProt(prot);
				foodVO.setFat(fat);
				foodVO.setMono(mono);
				foodVO.setPoly(poly);
				foodVO.setSfa(sfa);
				foodVO.setTrans(trans);
				foodVO.setCho(cho);
				foodVO.setCarb(carb);
				foodVO.setSugar(sugar);
				foodVO.setDf(df);
				foodVO.setNa(na);
				foodVO.setCa(ca);
				foodVO.setK(k);
				foodVO.setCal(cal);
				
				/***************************2.開始修改資料*****************************************/
				FoodService foodSvc = new FoodService();
				foodVO = foodSvc.updateFood(fd_no ,fd_name,fd_spone,fd_sptwo,fd_spthr,fd_spfor,fd_spfir,fd_spsix,prot,fat,mono,poly,sfa,trans,cho,carb,sugar,df,na,ca,k,cal);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("foodVO", foodVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-front/food/listAllFd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
//				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
//				req.setAttribute("errorMsgs", errorMsgs);

//				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			 	HttpSession session=req.getSession();
			 	double reSubmitCode=(double) session.getAttribute("danny");
				double reSubmitCode2=Double.parseDouble(req.getParameter("danny"));
			 
				if(reSubmitCode == reSubmitCode2){
			 
			 		String requestURL= req.getParameter("requestURL");
			 		String fd_name = req.getParameter("fd_name").trim();
					 
			 		FoodService foodSvc = new FoodService();
			    	FoodVO foodVO = foodSvc.getOneFood_Name(fd_name);

			    	List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
				
			        if (foodVO!=null){
			        	errorMsgs.add("已經有此食物，請重新輸入");
			        	String url = "/front-end/foodIndex.jsp";
//						try{
							RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
							successView.forward(req, res);
							return;
//						}catch(IllegalStateException e){
//							RequestDispatcher successView = req.getRequestDispatcher(requestURL); 
//							successView.forward(req, res);	
//						}
						
			        }
			       
			      
			 		
					 Integer fd_spone = null;
					 fd_spone = new Integer(req.getParameter("fd_spone").trim());
					 
					 Integer fd_sptwo = null;
					 fd_sptwo = new Integer(req.getParameter("fd_sptwo").trim());
					 
					 Integer fd_spthr = null;
					 fd_spthr = new Integer(req.getParameter("fd_spthr").trim());
					 
					 Integer fd_spfor = null;
					 fd_spfor = new Integer(req.getParameter("fd_spfor").trim());

					 Integer fd_spfir = null;
					 fd_spfir = new Integer(req.getParameter("fd_spfir").trim());

					 Integer fd_spsix = null;
					 fd_spsix = new Integer(req.getParameter("fd_spsix").trim());
					 
					 Integer prot = null;
					 prot = new Integer(req.getParameter("prot").trim());

					 Integer fat = null;
					 fat = new Integer(req.getParameter("fat").trim());

					 Integer mono = null;
					 mono = new Integer(req.getParameter("mono").trim());
					 
					 Integer poly = null;
					 poly = new Integer(req.getParameter("poly").trim());

					 Integer sfa = null;
					 sfa = new Integer(req.getParameter("sfa").trim());

					 Integer trans = null;
					 trans = new Integer(req.getParameter("trans").trim());

					 Integer cho = null;
					 cho = new Integer(req.getParameter("cho").trim());

					 Integer carb = null;
					 carb = new Integer(req.getParameter("carb").trim());

					 Integer sugar = null;
					 sugar = new Integer(req.getParameter("sugar").trim());

					 Integer df = null;
					 df = new Integer(req.getParameter("df").trim());

					 Integer na = null;
					 na = new Integer(req.getParameter("na").trim());

					 Integer ca = null;
					 ca = new Integer(req.getParameter("ca").trim());

					 Integer k = null;
					 k = new Integer(req.getParameter("k").trim());

					 Integer cal = null;
					 cal = new Integer(req.getParameter("cal").trim());

					 foodVO = new FoodVO();
					foodVO.setFd_name(fd_name);
					foodVO.setFd_spone(fd_spone);
					foodVO.setFd_sptwo(fd_sptwo);
					foodVO.setFd_spthr(fd_spthr);
					foodVO.setFd_spfor(fd_spfor);
					foodVO.setFd_spfir(fd_spfir);
					foodVO.setFd_spsix(fd_spsix);
					foodVO.setProt(prot);
					foodVO.setFat(fat);
					foodVO.setMono(mono);
					foodVO.setPoly(poly);
					foodVO.setSfa(sfa);
					foodVO.setTrans(trans);
					foodVO.setCho(cho);
					foodVO.setCarb(carb);
					foodVO.setSugar(sugar);
					foodVO.setDf(df);
					foodVO.setNa(na);
					foodVO.setCa(ca);
					foodVO.setK(k);
					foodVO.setCal(cal);

					// Send the use back to the form, if there were errors
//					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("empVO", foodVO); // 含有輸入格式錯誤的empVO物件,也存入req
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/emp/addEmp.jsp");
//						failureView.forward(req, res);
//						return;
//					}
					
					/***************************2.開始新增資料***************************************/
//					FoodService foodSvc = new FoodService();
					foodVO = foodSvc.addFood(fd_name,fd_spone,fd_sptwo,fd_spthr,fd_spfor,fd_spfir,fd_spsix,prot,fat,mono,poly,sfa,trans,cho,carb,sugar,df,na,ca,k,cal);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					req.setAttribute("foodsweat", "foodsweat");
				}
					String url = "/front-end/foodIndex.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);				
//					res.sendRedirect(requestURL);				
					
					/***************************其他可能的錯誤處理**********************************/
//				} catch (Exception e) {
//					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//				}
			}
		
		
		if ("deleteFood".equals(action)) { // 來自listAllEmp.jsp

//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
	
//			try {
				/***************************1.接收請求參數***************************************/
				Integer fd_no = new Integer(req.getParameter("fd_no"));
				String requestURL= req.getParameter("requestURL");
				/***************************2.開始刪除資料***************************************/
				FoodService foodSvc = new FoodService();
				foodSvc.deleteFood(fd_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "back-front/food/listAllFd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
		}
		PrintWriter out = res.getWriter();
		List<FoodVO> fd_list = new ArrayList<FoodVO>();
		
		if("getKeyWord".equals(req.getParameter("action"))){
			try{
				
			String query = req.getParameter("query").trim();
			if(! "".equals(query)){
				FoodService foodSvc = new FoodService();
				fd_list = foodSvc.findByKeyWord2(query);
			}
			}catch(Exception e){
				return;
			}
			JSONArray array=null;
			if(fd_list.size()==0){
				out.write("查無結果");
			}else{
				array = new JSONArray(fd_list);
				out.write(array.toString());
			}
			out.flush();
			out.close();
		}
	 }
	}

		 
	

