package com.memach.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ach.model.AchService;
import com.memach.model.MemAchService;
import com.memach.model.MemAchVO;

/**
 * Servlet implementation class MemAchServlet
 */
@WebServlet("/MemAchServlet")
public class MemAchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
				 
		 if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp���ШD

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j

				try {
					/***************************1.�����ШD�Ѽ�***************************************/
					Integer memno = new Integer(req.getParameter("memno"));
					Integer achno = new Integer(req.getParameter("achno"));
					
					/***************************2.�}�l�R�����***************************************/
					MemAchService memAchSvc = new MemAchService();
					MemAchVO memAchVO = memAchSvc.getOneMemAch(memno,achno);
					memAchSvc.deleteMemAch(memno,achno);
					
					/***************************3.�R������,�ǳ����(Send the Success view)***********/
					AchService achSvc = new AchService();
					if(requestURL.equals("/front-end/ach/listMems_ByAchno.jsp") || requestURL.equals("/front-end/ach/listAllAch.jsp"))
						req.setAttribute("listMems_ByAchno",achSvc.getEmpsByDeptno(memAchVO.getAchno())); // ��Ʈw���X��list����,�s�Jrequest
					
					String url = requestURL;
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
	
	}

}
