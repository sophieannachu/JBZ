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
				 
		 if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

				try {
					/***************************1.接收請求參數***************************************/
					Integer memno = new Integer(req.getParameter("memno"));
					Integer achno = new Integer(req.getParameter("achno"));
					
					/***************************2.開始刪除資料***************************************/
					MemAchService memAchSvc = new MemAchService();
					MemAchVO memAchVO = memAchSvc.getOneMemAch(memno,achno);
					memAchSvc.deleteMemAch(memno,achno);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/
					AchService achSvc = new AchService();
					if(requestURL.equals("/front-end/ach/listMems_ByAchno.jsp") || requestURL.equals("/front-end/ach/listAllAch.jsp"))
						req.setAttribute("listMems_ByAchno",achSvc.getEmpsByDeptno(memAchVO.getAchno())); // 資料庫取出的list物件,存入request
					
					String url = requestURL;
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
	
	}

}
