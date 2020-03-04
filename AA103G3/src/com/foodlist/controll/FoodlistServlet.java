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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("fdrecno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�����s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer fdrecno = null;
				try {
					fdrecno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�����s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				FoodlistService foodlistSvc = new FoodlistService();
				FoodlistVO foodlistVO = foodlistSvc.getOneFoodlist(fdrecno);
				if (foodlistVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("foodlistVO", foodlistVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/foodlist/listOneFoodlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp ���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer fdrecno = new Integer(req.getParameter("fdrecno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				FoodlistService foodlistSvc = new FoodlistService();
				FoodlistVO foodlistVO = foodlistSvc.getOneFoodlist(fdrecno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("foodlistVO", foodlistVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/foodlist/update_foodlist_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z************************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer fdrecno = new Integer(req.getParameter("fdrecno").trim());
				String fddesc = req.getParameter("fddesc").trim();			
				
				java.sql.Date fddate = null;
				try {
					fddate = java.sql.Date.valueOf(req.getParameter("fddate").trim());
				} catch (IllegalArgumentException e) {
					fddate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				Integer fdqua = null;
				try {
					fdqua = new Integer(req.getParameter("fdqua").trim());
				} catch (NumberFormatException e) {
					fdqua = 0;
					errorMsgs.add("�ƶq�ж�Ʀr.");
				}

				Integer memno = null;
				try {
					memno = new Integer(req.getParameter("memno").trim());
				} catch (NumberFormatException e) {
					memno = 0;
					errorMsgs.add("�|�s�ж�Ʀr.");
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
					req.setAttribute("foodlistVO", foodlistVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/foodlist/update_foodlist_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				FoodlistService foodlistSvc = new FoodlistService();
				foodlistVO = foodlistSvc.updateFoodlist(fdrecno, fddesc, fddate,fdqua,memno, fd_no);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("foodlistVO", foodlistVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/foodlist/listOneFoodlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/foodlist/update_foodlist_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
        	
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
        	
        	System.out.println("calSubtatal:�ĤG"+calSubtatal);
        	
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
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);	
			/***************************�s���h���ʺA�s�W***************************************/
			
        	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
//				String fddesc = req.getParameter("fddesc").trim();
				
//				java.sql.Date fddate = null;
				
//				try {
//					fddate = java.sql.Date.valueOf(req.getParameter("fddate").trim());
//				} catch (IllegalArgumentException e) {
//					fddate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
				
//				
//				Integer fdqua = null;
//				try {
//					fdqua = new Integer(req.getParameter("fdqua").trim());
//				} catch (NumberFormatException e) {
//					fdqua = 0;
//					errorMsgs.add("�|�s�ж�Ʀr.");
//				}
//				
				
//				Integer memno = null;
//				try {
//					memno = new Integer(req.getParameter("memno").trim());
//				} catch (NumberFormatException e) {
//					memno = 0;
//					errorMsgs.add("�|�s�ж�Ʀr.");
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
//					req.setAttribute("foodlistVO", foodlistVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/foodlist/addFoodlist.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.�}�l�s�W���***************************************/
//				FoodlistService foodlistSvc = new FoodlistService();
//				foodlistVO = foodlistSvc.addFoodlist(fddesc, fddate,fdqua, memno, fd_no);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
//				String url = "/foodlist/listAllFoodlist.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
//				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/foodlist/addFoodlist.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer fdrecno = new Integer(req.getParameter("fdrecno"));
				
				/***************************2.�}�l�R�����***************************************/
				FoodlistService foodlistSvc = new FoodlistService();
				FoodlistVO foodlistVO = foodlistSvc.getOneFoodlist(fdrecno);
				foodlistSvc.deleteFoodlist(fdrecno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				
				// ��Ʈw���X��list����,�s�Jrequest
				
				String url = requestURL;
				System.out.println("URL"+url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		/***************************�H����η|���s���M��**********************************/
		if ("getDateAndMemno".equals(action)) {
//			Integer memno=null;
			Integer memno = Integer.parseInt((req.getParameter("memno")));
//			memno = 1;
			java.sql.Date fdd=null;
			fdd = java.sql.Date.valueOf(req.getParameter("fddate").trim());
		
			
			FoodlistService foodlistSvc = new FoodlistService();
			List<FoodlistVO> foodlistVO = foodlistSvc.findByDateAndMemno(fdd,memno);
			req.setAttribute("foodlistVO1", foodlistVO); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/front-end/foodIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
			successView.forward(req, res);
		
		}
		/***************************�H��������r�d��**********************************/
		if ("getKeyWord".equals(action)) {
		
			String fd_name = req.getParameter("fd_name").trim();
			
			FoodService foodSvc = new FoodService();
			List<FoodVO> fdlist = foodSvc.findByKeyWord(fd_name);
			req.setAttribute("fdlist", fdlist); 
			String url = "/front-end/foodIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		
		}
		/***************************�x�s**********************************/
		if ("getOneFd".equals(action)) {

			try {
				// Retrieve form parameters.
				Integer fd_no = new Integer(req.getParameter("fd_no"));

				FoodService dao = new FoodService();
				FoodVO foodVO = dao.getOneFood(fd_no);

				req.setAttribute("foodVO", foodVO); // ��Ʈw���X��empVO����,�s�Jreq

				// ���X��empVO�e��listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/js/foodIndex.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
	}
		/***************************�a���˥d����**********************************/
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
		/***************************�M��**********************************/
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
			req.setAttribute("foodlistVO1", foodlistVO); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/front-end/personalPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
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
		
		/***************************�s�W�U�����ƲĤG��**********************************/
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
