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
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				String str = req.getParameter("heinfono");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J��T�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer heinfono = null;
				try {
					heinfono = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/***************************
				 * 2.�}�l�d�߸��
				 *****************************************/
				HeinfoService heinfoSvc = new HeinfoService();
				HeinfoVO heinfoVO = heinfoSvc.getOneHeinfo(heinfono);
				if (heinfoVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("heinfoVO", heinfoVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/heinfo/listOneHeinfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ�
				 ****************************************/
				Integer heinfono = new Integer(req.getParameter("heinfono"));
//				String requestURL = new String(req.getParameter("requestURL"));
				/***************************
				 * 2.�}�l�d�߸��
				 ****************************************/
				HeinfoService heinfoSvc = new HeinfoService();
				HeinfoVO heinfoVO = heinfoSvc.getOneHeinfo(heinfono);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("heinfoVO", heinfoVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/heinfoIndex.jsp?action=update_heinfo_input";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/listAllHeinfo.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
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
					errorMsgs.add("�п�J���!");
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
					req.setAttribute("heinfoVO", heinfoVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/update_heinfo_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}
//
//				/***************************
//				 * 2.�}�l�ק���
//				 *****************************************/
				HeinfoService heinfoSvc = new HeinfoService();
				
				heinfoVO = heinfoSvc.updateHeinfo(heinfono, heinfoname, heinfodetail, heinfodate, buf);

				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("heinfoVO", heinfoVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/heinfoIndex.jsp?action=heinfoIndex";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/update_heinfo_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		if ("insert".equals(action)) { // �Ӧ�add
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				String heinfoname = req.getParameter("heinfoname").trim();
				String heinfodetail = req.getParameter("heinfodetail").trim();
				String requestURL = new String(req.getParameter("requestURL"));
				java.sql.Date heinfodate = null;
				try {
					heinfodate = java.sql.Date.valueOf(req.getParameter("heinfodate").trim());
				} catch (IllegalArgumentException e) {
					heinfodate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
					//�s�W�Ӥ�
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
					 * 3.�s�W����,�ǳ����(Send the Success view)
					 ***********/
				
//					String url = "/heinfo/listAllHeinfo.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(requestURL + "?action=heinfoIndex"); // �s�W���\�����listAllEmp.jsp
					successView.forward(req, res);

//					if (!errorMsgs.isEmpty()) {
//						req.setAttribute("heinfoVO", heinfoVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//						RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/addHeinfo.jsp");
//						failureView.forward(req, res);
//						return;
//					}
					/***************************
					 * 2.�}�l�s�W���
					 ***************************************/
					
//				} catch (Exception e) {
//					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/addHeinfo.jsp");
//					failureView.forward(req, res);
					
			
		}
					/*************************** ��L�i�઺���~�B�z **********************************/
					
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
				
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ�
				 ***************************************/
				Integer heinfono = new Integer(req.getParameter("heinfono"));
				String requestURL = new String(req.getParameter("requestURL"));
				/***************************
				 * 2.�}�l�R�����
				 ***************************************/
				HeinfoService heinfoSvc = new HeinfoService();
				heinfoSvc.deleteHeinfo(heinfono);

				/***************************
				 * 3.�R������,�ǳ����(Send the Success view)
				 ***********/
//				String url = "/heinfo/listAllHeinfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(requestURL + "?action=heinfoIndex");// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/heinfo/listAllHeinfo.jsp");
				failureView.forward(req, res);
			}
		}

		
	}

}
