package com.ach.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ach.model.*;
import com.memach.model.*;


/**
 * Servlet implementation class AchServlet
 */
@WebServlet("/AchServlet")
public class AchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("listMems_ByAchno_A".equals(action) || "listMems_ByAchno_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer achno = new Integer(req.getParameter("achno"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				AchService achSvc = new AchService();
				Set<MemAchVO> set = achSvc.getEmpsByDeptno(achno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listMems_ByAchno", set);    // ��Ʈw���X��set����,�s�Jrequest

				String url = null;
				if ("listMems_ByAchno_A".equals(action))
					url = "/front-end/ach/listMems_ByAchno.jsp";        // ���\��� dept/listEmps_ByDeptno.jsp
				else if ("listMems_ByAchno_B".equals(action))
					url = "/front-end/ach/listAllAch.jsp";              // ���\��� dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		if ("listAll_ByAchName".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String aName = req.getParameter("aName");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				AchService achSvc = new AchService();
				Set<MemAchVO> set = achSvc.getAll(aName);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listMems_ByAchno", set);    // ��Ʈw���X��set����,�s�Jrequest

				String url = null;
				if ("listMems_ByAchno_A".equals(action)||"listAll_ByAchName".equals(action))
					url = "/front-end/ach/listMems_ByAchno.jsp";        // ���\��� dept/listEmps_ByDeptno.jsp
				else if ("listMems_ByAchno_B".equals(action))
					url = "/front-end/ach/listAllAch.jsp";              // ���\��� dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
