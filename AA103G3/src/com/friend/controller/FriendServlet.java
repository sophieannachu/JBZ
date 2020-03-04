package com.friend.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.friend.model.*;


/**
 * Servlet implementation class FriendServlet
 */
public class FriendServlet extends HttpServlet {

    public FriendServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if ("update_for_status".equals(action)) { // 來自update_emp_input.jsp的請求
				
			try {
				Integer memno = Integer.parseInt(req.getParameter("memno"));
				Integer frino = Integer.parseInt(req.getParameter("frino"));
				Date addDate = new Date(System.currentTimeMillis());

				/***************************
				 * 2.開始修改資料
				 *****************************************/
				FriendService friendSvc = new FriendService();
				FriendVO friendVO = friendSvc.updateFriend(memno, frino,addDate,"1");
				FriendVO friendVO2 = friendSvc.addFriend(frino,memno,addDate,"1");

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
		if ("cancle_for_friend".equals(action)) { // 來自update_emp_input.jsp的請求
			
			try {
				Integer memno = Integer.parseInt(req.getParameter("memno"));
				Integer frino = Integer.parseInt(req.getParameter("frino"));

				/***************************
				 * 2.開始修改資料
				 *****************************************/
				FriendService friendSvc = new FriendService();
				friendSvc.deleteFriend(memno, frino);


				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
		if ("delete_for_friend".equals(action)) { // 來自update_emp_input.jsp的請求
			String requestURL = req.getParameter("requestURL");
			
			try {
				Integer memno = Integer.parseInt(req.getParameter("memno"));
				Integer frino = Integer.parseInt(req.getParameter("frino"));

				/***************************
				 * 2.開始修改資料
				 *****************************************/
				FriendService friendSvc = new FriendService();
				friendSvc.deleteFriend(memno, frino);
				System.out.println(requestURL);
				friendSvc.deleteFriend(frino, memno);
				
				String url = requestURL;
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交原jsp
				successView.forward(req, res);


				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

}
