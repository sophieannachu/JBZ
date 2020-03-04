package com.mail.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class connectionMail  extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		HttpSession session=req.getSession();
		
		
		if("mailsend".equals(action)){
			String subject=req.getParameter("first_name");
			String to=req.getParameter("email");
			String messageText=req.getParameter("content");
			String requestURL=req.getParameter("requestURL");
			
			MailService sm = new MailService();	
			sm.sendMail(to, subject, messageText);
		
			RequestDispatcher successView=req.getRequestDispatcher(requestURL);
			successView.forward(req, res);
		}
		
	}
}
