package com.auth.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth.model.*;
import com.emp.model.*;
import com.func.model.FuncService;

public class AuthServlet2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		Integer empno=new Integer(req.getParameter("empno").trim());
		EmpVO empVO=new EmpService().getOneEmp(empno);
		HttpSession session=req.getSession();
		double reSubmitCode=(double) session.getAttribute("reSubmitCode");
		double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
		
		if("updateView".equals(action)){
			
			try{
				List<Integer> authList=new AuthService().getOne(empno);
				req.setAttribute("empVO", empVO);
				req.setAttribute("authList", authList);
				
				RequestDispatcher successView=req.getRequestDispatcher("/back-end/auth/update_emp_auth.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				System.out.println("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/update_emp_auth.jsp");
				failureView.forward(req, res);
				
			}
			
		}
		
		if("allClose".equals(action)){
			
			String reqSourceURL=req.getParameter("reqSourceURL");
			try{
				if(reSubmitCode == reSubmitCode2){
	//				Integer empno=new Integer(req.getParameter("empno").trim());
	//				String[] authArray=null;
					AuthService authSv=new AuthService();
					authSv.deleteOne(empno);
					req.setAttribute("successMsg", "權限修改成功!");
					session.removeAttribute("reSubmitCode");
				}
				RequestDispatcher successView =req.getRequestDispatcher(reqSourceURL);
				successView.forward(req, res);
			}catch(Exception e){
				new AuthService().insertOne(empno, req.getParameterValues("auth"));
				List<Integer> authList=new AuthService().getOne(empno);
				req.setAttribute("empVO", empVO);
				req.setAttribute("authList", authList);
				System.out.println("全停失敗:" + e.getMessage());
				req.setAttribute("failureMsg", "權限修改失敗...");
				RequestDispatcher failureView = req.getRequestDispatcher(reqSourceURL);
				failureView.forward(req, res);
			}
			
		}
		
		if("allOpen".equals(action)){
			
			String reqSourceURL=req.getParameter("reqSourceURL");
			try{
				if(reSubmitCode == reSubmitCode2){
	//				Integer empno=new Integer(req.getParameter("empno").trim());
					AuthService authSv=new AuthService();
					List<Integer> funcList=new FuncService().getFuncNo();
					Integer[] authArray=funcList.toArray(new Integer[0]);
					authSv.insertOne(empno, authArray);
					req.setAttribute("successMsg", "權限修改成功!");
					session.removeAttribute("reSubmitCode");
				}
				RequestDispatcher successView =req.getRequestDispatcher(reqSourceURL);
				successView.forward(req, res);
			}catch(Exception e){
				new AuthService().insertOne(empno, req.getParameterValues("auth"));
				List<Integer> authList=new AuthService().getOne(empno);
				req.setAttribute("empVO", empVO);
				req.setAttribute("authList", authList);
				System.out.println("全開失敗:" + e.getMessage());
				req.setAttribute("failureMsg", "權限修改失敗...");
				RequestDispatcher failureView = req.getRequestDispatcher(reqSourceURL);
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)){
			
			String reqSourceURL=req.getParameter("reqSourceURL");
			try{
				if(reSubmitCode == reSubmitCode2){
	//				Integer empno=new Integer(req.getParameter("empno").trim());
					String[] authArray=req.getParameterValues("auth");
					AuthService authSv=new AuthService();
					authSv.insertOne(empno, authArray);
					req.setAttribute("successMsg", "權限修改成功!");
					session.removeAttribute("reSubmitCode");
				}
				RequestDispatcher successView =req.getRequestDispatcher(reqSourceURL);
				successView.forward(req, res);
			}catch(Exception e){
				new AuthService().insertOne(empno, req.getParameterValues("auth"));
				List<Integer> authList=new AuthService().getOne(empno);
				req.setAttribute("empVO", empVO);
				req.setAttribute("authList", authList);
				System.out.println("修改失敗:" + e.getMessage());
				req.setAttribute("failureMsg", "權限修改失敗...");
				RequestDispatcher failureView = req.getRequestDispatcher(reqSourceURL);
				failureView.forward(req, res);
			}
		}
		
	}
	

}
