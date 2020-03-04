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
		if ("update_for_status".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
				
			try {
				Integer memno = Integer.parseInt(req.getParameter("memno"));
				Integer frino = Integer.parseInt(req.getParameter("frino"));
				Date addDate = new Date(System.currentTimeMillis());

				/***************************
				 * 2.�}�l�ק���
				 *****************************************/
				FriendService friendSvc = new FriendService();
				FriendVO friendVO = friendSvc.updateFriend(memno, frino,addDate,"1");
				FriendVO friendVO2 = friendSvc.addFriend(frino,memno,addDate,"1");

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
		if ("cancle_for_friend".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			try {
				Integer memno = Integer.parseInt(req.getParameter("memno"));
				Integer frino = Integer.parseInt(req.getParameter("frino"));

				/***************************
				 * 2.�}�l�ק���
				 *****************************************/
				FriendService friendSvc = new FriendService();
				friendSvc.deleteFriend(memno, frino);


				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
		if ("delete_for_friend".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			String requestURL = req.getParameter("requestURL");
			
			try {
				Integer memno = Integer.parseInt(req.getParameter("memno"));
				Integer frino = Integer.parseInt(req.getParameter("frino"));

				/***************************
				 * 2.�}�l�ק���
				 *****************************************/
				FriendService friendSvc = new FriendService();
				friendSvc.deleteFriend(memno, frino);
				System.out.println(requestURL);
				friendSvc.deleteFriend(frino, memno);
				
				String url = requestURL;
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\������jsp
				successView.forward(req, res);


				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

}
