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
		
		 if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL");

				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
					
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
										
					/***************************2.�}�l�s�W���***************************************/
					BasicCheckService basicCheckSvc = new BasicCheckService();
					basicCheckVO = basicCheckSvc.addBasicCheck(weight,bmi,bmr,bFat,waisyline,checkDate,memno);
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					errorMsgs.add("�s�W���\�I");
					String url = requestURL;
//					String url = "/basiccheck/listAllbasicCheck.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������jsp
					successView.forward(req, res);				
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}
		 if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp ���ШD

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j		
				
				try {
					/***************************1.�����ШD�Ѽ�****************************************/
					Integer basicCheckno = new Integer(req.getParameter("basicCheckno"));
					
					/***************************2.�}�l�d�߸��****************************************/
					BasicCheckService basicCheckSvc = new BasicCheckService();
					BasicCheckVO basicCheckVO = basicCheckSvc.getOneBasicCheck(basicCheckno);
									
					/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
					req.setAttribute("basicCheckVO", basicCheckVO); // ��Ʈw���X��empVO����,�s�Jreq
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_emp_input.jsp
					successView.forward(req, res);

					/***************************��L�i�઺���~�B�z************************************/
				} catch (Exception e) {
					errorMsgs.add("�ק��ƨ��X�ɥ���:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}
			
			
			if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
//				String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j

//				try {
					/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
					Integer basicCheckno = new Integer(req.getParameter("basicCheckno").trim());
					
					if(req.getParameter("weight").trim()==""&&req.getParameter("bmi").trim()==""&&req.getParameter("bmr").trim()==""&&
							req.getParameter("bFat").trim()==""&&req.getParameter("waisyline").trim()==""){
						errorMsgs.add("�魫�B�y��B��׽Цܤֶ�g�@��");
					}
					Integer weight = null;
					try {
						if(req.getParameter("weight").trim()!=""){
							weight = new Integer(req.getParameter("weight").trim());
						}					
					} catch (NumberFormatException e) {
						weight = 0;
						errorMsgs.add("�魫�ж�Ʀr");
					}
					
					Double bmi = null;
					try {
						if(req.getParameter("bmi").trim()!=""){
							bmi = new Double(req.getParameter("bmi").trim());
						}
						
					} catch (NumberFormatException e) {
						bmi = 0.0;
						errorMsgs.add("�����q�ƭ������Ʀr");
					}
					
					Double bmr = null;
					try {
						if(req.getParameter("bmr").trim()!=""){
							bmr = new Double(req.getParameter("bmr").trim());
						}
						
					} catch (NumberFormatException e) {
						bmr = 0.0;
						errorMsgs.add("��¦�N�²v�����Ʀr");
					}
				
					Double bFat = null;
					try {
						if(req.getParameter("bFat").trim()!=""){
							bFat = new Double(req.getParameter("bFat").trim());
						}
						
					} catch (NumberFormatException e) {
						bFat = 0.0;
						errorMsgs.add("�������Ʀr");
					}
					
					Double waisyline = null;
					try {
						if(req.getParameter("waisyline").trim()!=""){
							waisyline = new Double(req.getParameter("waisyline").trim());
						}
						
					} catch (NumberFormatException e) {
						waisyline = 0.0;
						errorMsgs.add("�y������Ʀr");
					}
					
					java.sql.Timestamp checkDate = null;
					try {
						checkDate = java.sql.Timestamp.valueOf(req.getParameter("checkDate").trim());
					} catch (IllegalArgumentException e) {
						checkDate=new java.sql.Timestamp(System.currentTimeMillis());
						errorMsgs.add("�п�J����ɶ�!");
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
						System.out.println("shaa");// �t����J�榡���~��empVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/basiccheck/update_basicCheck_input.jsp");
						failureView.forward(req, res);
						return; //�{�����_
					}
					
					/***************************2.�}�l�ק���*****************************************/
					BasicCheckService basicCheckSvc = new BasicCheckService();
					basicCheckVO = basicCheckSvc.updateBasicCheck(basicCheckno, weight, bmi, bmr, bFat,waisyline, checkDate,memno);
					
					/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/				
//					errorMsgs.add("�ק令�\�I");
//	                String url = "/front-end/healthCheck.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url);   // �ק令�\��,���^�e�X�ק諸�ӷ�����
//					successView.forward(req, res);

					/***************************��L�i�઺���~�B�z*************************************/
//				} catch (Exception e) {
//					errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//					System.out.println("fufu");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/basiccheck/update_basicCheck_input.jsp");
//					failureView.forward(req, res);
//				}
			}
			if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp���ШD

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j

				try {
					/***************************1.�����ШD�Ѽ�***************************************/
					Integer basicCheckno = new Integer(req.getParameter("basicCheckno"));
					
					/***************************2.�}�l�R�����***************************************/
					BasicCheckService basicCheckSvc = new BasicCheckService();
					BasicCheckVO basicCheckVO = basicCheckSvc.getOneBasicCheck(basicCheckno);
					basicCheckSvc.deleteBasicCheck(basicCheckno);
					
					/***************************3.�R������,�ǳ����(Send the Success view)***********/
//					DeptService deptSvc = new DeptService();
//					if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//						req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // ��Ʈw���X��list����,�s�Jrequest
					
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
		
					errorMsgs.add("�R����ƥ���:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}
	}

}
