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
				/*************************** 1.接收請求參數 ****************************************/
				Integer achno = new Integer(req.getParameter("achno"));

				/*************************** 2.開始查詢資料 ****************************************/
				AchService achSvc = new AchService();
				Set<MemAchVO> set = achSvc.getEmpsByDeptno(achno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listMems_ByAchno", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listMems_ByAchno_A".equals(action))
					url = "/front-end/ach/listMems_ByAchno.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listMems_ByAchno_B".equals(action))
					url = "/front-end/ach/listAllAch.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		if ("listAll_ByAchName".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String aName = req.getParameter("aName");

				/*************************** 2.開始查詢資料 ****************************************/
				AchService achSvc = new AchService();
				Set<MemAchVO> set = achSvc.getAll(aName);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listMems_ByAchno", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listMems_ByAchno_A".equals(action)||"listAll_ByAchName".equals(action))
					url = "/front-end/ach/listMems_ByAchno.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listMems_ByAchno_B".equals(action))
					url = "/front-end/ach/listAllAch.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
