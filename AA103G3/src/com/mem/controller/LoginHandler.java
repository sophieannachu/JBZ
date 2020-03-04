package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.*;

/**
 * Servlet implementation class LoginHandler
 */
@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginHandler() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("Big5");
	    res.setContentType("text/html; charset=Big5");
	    PrintWriter out = res.getWriter();
	    
	    if("login".equals(req.getParameter("action"))){
	    	String account = req.getParameter("account");
		    String password = req.getParameter("password");
		    
		    System.out.println(account);
		    System.out.println(password);
	    	MemService memSvc = new MemService();
	    	MemVO memVO = memSvc.getOneByAccMem(account);
//	    	System.out.println(memVO);
	    	List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

	        if (memVO==null){
	        	errorMsgs.add("查無此帳號，請重新輸入");
	        }else if(!(password.equals(memVO.getPwd()))){
	        	errorMsgs.add("密碼輸入錯誤，請重新確認");
	        }else{
	        	HttpSession session = req.getSession();
	            session.setAttribute("memVO", memVO);
	            
	            try {                                                        
	                String location = (String) session.getAttribute("location");
	                if (location != null) {
	                  session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
	                  res.sendRedirect(location);            
	                  return;
	                }
	              }catch (Exception ignored) { }

	             res.sendRedirect(req.getContextPath()+"/front-end/homePage.jsp");
	             return;
	        }
	        System.out.println(errorMsgs);
	        
	        String url = "/front-end/newLogin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
	    } 
	    
	    if("logout".equals(req.getParameter("action"))){
	    	HttpSession session = req.getSession();
	    	 session.removeAttribute("memVO");
	    	 
	    	 res.sendRedirect(req.getContextPath()+"/front-end/newLogin.jsp");
             return;
	    }
	}

}
