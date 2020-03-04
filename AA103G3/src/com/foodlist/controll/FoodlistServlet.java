package com.foodlist.controll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.foodlist.model.FoodService;
import com.foodlist.model.FoodVO;
import com.foodlist.model.FoodlistService;
import com.foodlist.model.FoodlistVO;
import com.basiccheck.model.BasicCheckVO;
import com.basiccheck.model.BasicCheckService;


//import com.google.gson.Gson;
//import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.mem.model.MemVO;
import com.mem.model.MemService;












public class FoodlistServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
//		System.out.println("KKKKKKKKKKKKKKKKKK");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("fdrecno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入飲食編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer fdrecno = null;
				try {
					fdrecno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("飲食編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FoodlistService foodlistSvc = new FoodlistService();
				FoodlistVO foodlistVO = foodlistSvc.getOneFoodlist(fdrecno);
				if (foodlistVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("foodlistVO", foodlistVO); // 資料庫取出的empVO物件,存入req
				String url = "/foodlist/listOneFoodlist.jsp";
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
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer fdrecno = new Integer(req.getParameter("fdrecno"));
				
				/***************************2.開始查詢資料****************************************/
				FoodlistService foodlistSvc = new FoodlistService();
				FoodlistVO foodlistVO = foodlistSvc.getOneFoodlist(fdrecno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("foodlistVO", foodlistVO); // 資料庫取出的empVO物件,存入req
				String url = "/foodlist/update_foodlist_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer fdrecno = new Integer(req.getParameter("fdrecno").trim());
				String fddesc = req.getParameter("fddesc").trim();			
				
				java.sql.Date fddate = null;
				try {
					fddate = java.sql.Date.valueOf(req.getParameter("fddate").trim());
				} catch (IllegalArgumentException e) {
					fddate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer fdqua = null;
				try {
					fdqua = new Integer(req.getParameter("fdqua").trim());
				} catch (NumberFormatException e) {
					fdqua = 0;
					errorMsgs.add("數量請填數字.");
				}

				Integer memno = null;
				try {
					memno = new Integer(req.getParameter("memno").trim());
				} catch (NumberFormatException e) {
					memno = 0;
					errorMsgs.add("會編請填數字.");
				}

				Integer fd_no = new Integer(req.getParameter("fd_no").trim());

				FoodlistVO foodlistVO = new FoodlistVO();
				foodlistVO.setFdrecno(fdrecno);
				foodlistVO.setFddesc(fddesc);
				foodlistVO.setFddate(fddate);
				foodlistVO.setFdqua(fdqua);
				foodlistVO.setMemno(memno);
				foodlistVO.setFd_no(fd_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("foodlistVO", foodlistVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/foodlist/update_foodlist_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FoodlistService foodlistSvc = new FoodlistService();
				foodlistVO = foodlistSvc.updateFoodlist(fdrecno, fddesc, fddate,fdqua,memno, fd_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("foodlistVO", foodlistVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/foodlist/listOneFoodlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/foodlist/update_foodlist_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
        	
        	 System.out.println(req.getParameter("json"));
        
        	
        	Gson gson = new Gson();
        	Type listType = new TypeToken<ArrayList<FoodlistVO>>() {}.getType();
        	ArrayList<FoodlistVO> jsonArr = gson.fromJson(req.getParameter("json"), listType);
//        	for(FoodlistVO obj : jsonArr){
//        		   System.out.println("fddesc:" + );
//        		   System.out.println("fdqua:" + );
//        		   System.out.println("fd_no:" +);
//        	}
        	java.sql.Date fddate = new java.sql.Date(System.currentTimeMillis());
//        	Integer memno = null;
//        	memno=1;
        	
        	FoodlistVO foodlistVO = new FoodlistVO();
        	Integer calSubtatal = 0;
        	FoodlistService foodlistSvc = new FoodlistService();
        	for(FoodlistVO obj : jsonArr){
//        	foodlistVO.setFddesc(obj.getFddesc());
//			foodlistVO.setFddate(fddate);
//			foodlistVO.setFdqua(obj.getFdqua());
//			foodlistVO.setMemno(obj.getMemno());
//			foodlistVO.setFd_no(obj.getFd_no());
        		calSubtatal += obj.getFd_cal();
			foodlistVO = foodlistSvc.addFoodlist(obj.getFddesc(), fddate,obj.getFdqua(), obj.getMemno(),  obj.getFd_no());
        	}
        	
        	
//        	foodlistVO = (FoodlistVO) req.getAttribute("foodlistVO");
       
        	foodlistVO.setFd_cal(calSubtatal);
        	
//        	calSubtatal=calSubtatal+foodlistVO.getFd_cal();
        	
        	System.out.println("calSubtatal:第二"+calSubtatal);
        	
        	HttpSession session = req.getSession();
        	if(session.getAttribute("calSubtatal")==null){
        		System.out.println("1111111");
        		session.setAttribute("calSubtatal", calSubtatal);
        	}else{
        		System.out.println("22222");
        		int calSubtatalPre = (Integer)(session.getAttribute("calSubtatal"));
        		calSubtatal = calSubtatalPre + calSubtatal;
        		System.out.println(calSubtatal);
        		session.setAttribute("calSubtatal", calSubtatal);
        	}
        	session.removeAttribute("foodcart");
        	
        	String url = "/front-end/foodIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);	
			/***************************新的多筆動態新增***************************************/
			
        	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String fddesc = req.getParameter("fddesc").trim();
				
//				java.sql.Date fddate = null;
				
//				try {
//					fddate = java.sql.Date.valueOf(req.getParameter("fddate").trim());
//				} catch (IllegalArgumentException e) {
//					fddate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
//				
//				Integer fdqua = null;
//				try {
//					fdqua = new Integer(req.getParameter("fdqua").trim());
//				} catch (NumberFormatException e) {
//					fdqua = 0;
//					errorMsgs.add("會編請填數字.");
//				}
//				
				
//				Integer memno = null;
//				try {
//					memno = new Integer(req.getParameter("memno").trim());
//				} catch (NumberFormatException e) {
//					memno = 0;
//					errorMsgs.add("會編請填數字.");
//				}
				
//				Integer fd_no = new Integer(req.getParameter("fd_no").trim());

//				FoodlistVO foodlistVO = new FoodlistVO();
//				foodlistVO.setFddesc(fddesc);
//				foodlistVO.setFddate(fddate);
//				foodlistVO.setFdqua(fdqua);
//				foodlistVO.setMemno(memno);
//				foodlistVO.setFd_no(fd_no);

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("foodlistVO", foodlistVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/foodlist/addFoodlist.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				FoodlistService foodlistSvc = new FoodlistService();
//				foodlistVO = foodlistSvc.addFoodlist(fddesc, fddate,fdqua, memno, fd_no);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/foodlist/listAllFoodlist.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/foodlist/addFoodlist.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer fdrecno = new Integer(req.getParameter("fdrecno"));
				
				/***************************2.開始刪除資料***************************************/
				FoodlistService foodlistSvc = new FoodlistService();
				FoodlistVO foodlistVO = foodlistSvc.getOneFoodlist(fdrecno);
				foodlistSvc.deleteFoodlist(fdrecno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				
				// 資料庫取出的list物件,存入request
				
				String url = requestURL;
				System.out.println("URL"+url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		/***************************以日期及會員編號尋找**********************************/
		if ("getDateAndMemno".equals(action)) {
//			Integer memno=null;
			Integer memno = Integer.parseInt((req.getParameter("memno")));
//			memno = 1;
			java.sql.Date fdd=null;
			fdd = java.sql.Date.valueOf(req.getParameter("fddate").trim());
		
			
			FoodlistService foodlistSvc = new FoodlistService();
			List<FoodlistVO> foodlistVO = foodlistSvc.findByDateAndMemno(fdd,memno);
			req.setAttribute("foodlistVO1", foodlistVO); // 資料庫取出的empVO物件,存入req
			String url = "/front-end/foodIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);
		
		}
		/***************************以食物關鍵字查詢**********************************/
		if ("getKeyWord".equals(action)) {
		
			String fd_name = req.getParameter("fd_name").trim();
			
			FoodService foodSvc = new FoodService();
			List<FoodVO> fdlist = foodSvc.findByKeyWord(fd_name);
			req.setAttribute("fdlist", fdlist); 
			String url = "/front-end/foodIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		
		}
		/***************************儲存**********************************/
		if ("getOneFd".equals(action)) {

			try {
				// Retrieve form parameters.
				Integer fd_no = new Integer(req.getParameter("fd_no"));

				FoodService dao = new FoodService();
				FoodVO foodVO = dao.getOneFood(fd_no);

				req.setAttribute("foodVO", foodVO); // 資料庫取出的empVO物件,存入req

				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/js/foodIndex.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
	}
		/***************************帶推薦卡路里**********************************/
		if ("getByDateMem".equals(action)) {
				
			HttpSession session = req.getSession();
			
			Integer memno = (Integer)(session.getAttribute("memno"));
			java.sql.Date checkDate = new java.sql.Date(System.currentTimeMillis());
				System.out.println(memno);
				System.out.println(checkDate);
					
			BasicCheckService dao = new BasicCheckService();
			BasicCheckVO basicCheckVO = dao.getByDateMemBasicCheck(memno,checkDate);
			Integer bmrrr = 1425;

				session.setAttribute("bmrrr", bmrrr); 

				
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/foodIndex.jsp");
				successView.forward(req, res);
				return;

				
			
	}
		/***************************清除**********************************/
		if ("clearnn".equals(action)) {
			
			HttpSession session = req.getSession();
			session.removeAttribute("foodcart");
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/foodIndex.jsp");
			successView.forward(req, res);
			return;

				
			
	}
		
		if ("getDateByMem".equals(action)) {
			Integer memno = Integer.parseInt((req.getParameter("memno")));
			String date = req.getParameter("date").trim();
			System.out.println(date);
			java.sql.Date fdd=null;
			fdd = java.sql.Date.valueOf(date.split("_")[3]);
		
			
			FoodlistService foodlistSvc = new FoodlistService();
			List<FoodlistVO> foodlistVO = foodlistSvc.findByDateAndMemno(fdd,memno);
			req.setAttribute("foodlistVO1", foodlistVO); // 資料庫取出的empVO物件,存入req
			String url = "/front-end/personalPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);
		
		}
		
		if ("findDate".equals(action)) {
			Integer memno = Integer.parseInt(req.getParameter("memno").trim());
			
			FoodlistService foodListSvc = new FoodlistService();
			List<FoodlistVO> fdlist = foodListSvc.findDateByMemno(memno);
			
			List array = new ArrayList();
			
			for(FoodlistVO foodlistVO:fdlist){
				JSONObject obj = new JSONObject();
				try{
					obj.put("date", foodlistVO.getFddate());
					obj.put("badge", "true");
				}catch(Exception e){}
				array.add(obj);
			}
			PrintWriter out = res.getWriter();
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			out.write(array.toString());
			out.flush();
			out.close();
			
		}
		
		/***************************新增下面的排第二種**********************************/
		HttpSession session = req.getSession();
		Vector<FoodVO> fd = (Vector<FoodVO>) session.getAttribute("foodcart");
		
			
				if (action.equals("DELFD")) {
					
					String del = req.getParameter("del");
					int d = Integer.parseInt(del);
					fd.removeElementAt(d);
//					session.setAttribute("foodcart",fd);
					session.setAttribute("foodcart", fd);
					String url = "/front-end/foodIndex.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, res);
				}

				else if (action.equals("AddFd")) {
					boolean match = false;
					String requestURL= req.getParameter("requestURL");

					FoodVO afood = getFood(req);
					if (fd == null) {
						fd = new Vector<FoodVO>();
						fd.add(afood);
					} else {
						for (int i = 0; i < fd.size(); i++) {
							FoodVO food = fd.get(i);						
						} 

					
					if (!match)
						fd.add(afood);
					}
					session.setAttribute("foodcart", fd);
					String url = requestURL;
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, res);
				
				}

				
		

		
	}
	
	
		private FoodVO getFood(HttpServletRequest req) {
	
			String fd_no = req.getParameter("getFd_no");
			String fd_name = req.getParameter("getFd_name");
			String cal = req.getParameter("getCal");
		
	
			FoodVO fd1 = new FoodVO();
	
			fd1.setFd_no(Integer.parseInt(fd_no));
			fd1.setFd_name(fd_name);
			fd1.setCal(Integer.parseInt(cal));
			return fd1;
	}
}
