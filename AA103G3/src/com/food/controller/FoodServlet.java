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
		System.out.println("�i�ӤF");
//		
//		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String str = req.getParameter("foodno");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J���u�s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-front/food.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				Integer empno = null;
//				try {
//					empno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("���u�s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				FoodService foodSvc = new FoodService();
//				FoodVO foodVO = foodSvc.getOneFood(empno);
//				if (foodVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("empVO", foodVO); // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
		if ("updateFood".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer fd_no = new Integer(req.getParameter("fd_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				FoodService foodSvc = new FoodService();
				FoodVO foodVO = foodSvc.getOneFood(fd_no);
			
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("foodVO", foodVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-front/food/updateFood.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
		}
//		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			System.out.println("�i��update");
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
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
				
				/***************************2.�}�l�ק���*****************************************/
				FoodService foodSvc = new FoodService();
				foodVO = foodSvc.updateFood(fd_no ,fd_name,fd_spone,fd_sptwo,fd_spthr,fd_spfor,fd_spfir,fd_spsix,prot,fat,mono,poly,sfa,trans,cho,carb,sugar,df,na,ca,k,cal);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("foodVO", foodVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-front/food/listAllFd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
		}

		 if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
				
//				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
//				req.setAttribute("errorMsgs", errorMsgs);

//				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
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
			        	errorMsgs.add("�w�g���������A�Э��s��J");
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
//						req.setAttribute("empVO", foodVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/emp/addEmp.jsp");
//						failureView.forward(req, res);
//						return;
//					}
					
					/***************************2.�}�l�s�W���***************************************/
//					FoodService foodSvc = new FoodService();
					foodVO = foodSvc.addFood(fd_name,fd_spone,fd_sptwo,fd_spthr,fd_spfor,fd_spfir,fd_spsix,prot,fat,mono,poly,sfa,trans,cho,carb,sugar,df,na,ca,k,cal);
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					req.setAttribute("foodsweat", "foodsweat");
				}
					String url = "/front-end/foodIndex.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);				
//					res.sendRedirect(requestURL);				
					
					/***************************��L�i�઺���~�B�z**********************************/
//				} catch (Exception e) {
//					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//				}
			}
		
		
		if ("deleteFood".equals(action)) { // �Ӧ�listAllEmp.jsp

//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
	
//			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer fd_no = new Integer(req.getParameter("fd_no"));
				String requestURL= req.getParameter("requestURL");
				/***************************2.�}�l�R�����***************************************/
				FoodService foodSvc = new FoodService();
				foodSvc.deleteFood(fd_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "back-front/food/listAllFd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:"+e.getMessage());
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
				out.write("�d�L���G");
			}else{
				array = new JSONArray(fd_list);
				out.write(array.toString());
			}
			out.flush();
			out.close();
		}
	 }
	}

		 
	

