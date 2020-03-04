package com.emp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.auth.model.AuthService;
import com.auth.model.AuthVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

import Utility.*;
@MultipartConfig
public class EmpServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		HttpSession session=req.getSession();
//		double reSubmitCode=(double) session.getAttribute("reSubmitCode");
//		double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
		
		if("delete".equals(action)){
			List<String> errMsgs=new LinkedList();
			req.setAttribute("errMsgs", errMsgs);
			double reSubmitCode=(double) session.getAttribute("reSubmitCode");
			double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
//			HttpSession session=req.getSession();
			
			String reqSourceURL=req.getParameter("reqSourceURL");
			
			try{
				if(reSubmitCode == reSubmitCode2){
					
					Integer empno=Integer.parseInt(req.getParameter("empno"));
					
					AuthService sv=new AuthService();
					sv.deleteOne(empno);
					EmpService sv1 =new EmpService();
					sv1.deleteEmp(empno);
					session.removeAttribute("reSubmitCode");
					req.setAttribute("successMsg", "員工資料刪除成功!");
				}
				
				RequestDispatcher successView =req.getRequestDispatcher(reqSourceURL);
				successView.forward(req, res);
			}catch(Exception e){
				req.setAttribute("failureMsg", "員工資料刪除失敗...");
				errMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView =req.getRequestDispatcher(reqSourceURL);//"/emp/listAll_emp.jsp"
				failureView.forward(req, res);
			}
		}
		if("updateView".equals(action)){
			
			List<String> errMsgs=new LinkedList();
			req.setAttribute("errMsgs", errMsgs);
//			HttpSession session=req.getSession();
			
			String reqSourceURL=req.getParameter("reqSourceURL");
			
			try{
				Integer empno =Integer.parseInt(req.getParameter("empno"));
				EmpService sv =new EmpService();
				EmpVO empVO =sv.getOneEmp(empno);
				AuthService sv2=new AuthService();
				List<Integer> authList=sv2.getOne(empno);
					
				req.setAttribute("empVO", empVO);
				req.setAttribute("authList", authList);
				session.setAttribute("photo", sv.getImg(empno));
				
				RequestDispatcher successView=req.getRequestDispatcher(reqSourceURL);
				successView.forward(req, res);
			}catch(Exception e){
				errMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(reqSourceURL);
				failureView.forward(req, res);
			}
					
		}
		
		if("update".equals(action)){
			
			List<String> errMsgs=new LinkedList();
			req.setAttribute("errMsgs", errMsgs);
			double reSubmitCode=(double) session.getAttribute("reSubmitCode");
			double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
//			HttpSession session=req.getSession();
//			double reSubmitCode=(double) session.getAttribute("reSubmitCode");
//			double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
			
			String reqSourceURL=req.getParameter("reqSourceURL");
			
			try{
				if(reSubmitCode == reSubmitCode2){
					Integer empno = Integer.parseInt(req.getParameter("empno").trim());
					
					String name=req.getParameter("name").trim();
					if(name.equals("")){
						errMsgs.add("請輸入姓名");
					}
					
					String acc=req.getParameter("acc").trim();//AJAX 檢查相同帳號
					if(acc.equals("")){
						errMsgs.add("請輸入帳號");
					}
					
					String pwd=req.getParameter("pwd").trim();
					if(pwd.equals("")){
						pwd=new EmpService().getOneEmp(empno).getPwd();
//						errMsgs.add("請輸入密碼");
					}
					
					String sex=req.getParameter("sex");
					if(sex == null){
						sex="";
						errMsgs.add("請選取性別");
					}
					
					String phone=req.getParameter("phone").trim();
					if(phone.equals("")){
						errMsgs.add("請輸入電話");
					}
					
					String email=req.getParameter("email").trim();
					if(email.equals("")){
						errMsgs.add("請輸入電子信箱");
					}
					
					Date birth=null;
					try {
						birth=Date.valueOf(req.getParameter("birth").trim());
					} catch (IllegalArgumentException e) {
						birth=new java.sql.Date(0);
						errMsgs.add("請輸入日期");
					}
					
//					byte[] photo=new EmpService().getImg(empno);
					byte[] photo=(byte[])session.getAttribute("photo");
					Part part =req.getPart("photo");
					if(part.getSize() > 0){
						InputStream in=part.getInputStream();
						photo=new byte[in.available()];
						in.read(photo);
						in.close();
					}
					
//					String[] auth=req.getParameterValues("auth");
//					AuthService sv2=new AuthService();
//					sv2.insertOne(empno, auth);
//					List<Integer> authList=sv2.getOne(empno);
					
					EmpVO empVO=new EmpVO();
					empVO.setEmpno(empno);
					empVO.setName(name);
					empVO.setAcc(acc);
					empVO.setPwd(pwd);
					empVO.setSex(sex);
					empVO.setPhone(phone);
					empVO.setEmail(email);
					empVO.setBirth(birth);
					empVO.setPhoto(photo);
					
					if(!errMsgs.isEmpty()){
						session.setAttribute("photo", photo);
						req.setAttribute("empVO", empVO);
//						req.setAttribute("authList", authList);
						RequestDispatcher failureView=req.getRequestDispatcher(reqSourceURL + "?action=updateView");
						failureView.forward(req, res);
						return;
					}
					
					EmpService sv=new EmpService();
					sv.updateEmp(empno, name, acc, pwd, sex, birth, phone, email, photo);
					session.removeAttribute("reSubmitCode");
					req.setAttribute("successMsg", "員工資料修改成功!");
				}
				System.out.println(reqSourceURL);
				RequestDispatcher successView =req.getRequestDispatcher(reqSourceURL);
				successView.forward(req, res);
			}catch(Exception e){
				req.setAttribute("failureMsg", "員工資料修改失敗...");
				errMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(reqSourceURL + "?action=updateView");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)){
			
			List<String> errMsgs=new LinkedList();
			req.setAttribute("errMsgs", errMsgs);
			double reSubmitCode=(double) session.getAttribute("reSubmitCode");
			double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
			String reqSourceURL=req.getParameter("reqSourceURL");
//			HttpSession session=req.getSession();
//			double reSubmitCode=(double) session.getAttribute("reSubmitCode");
//			double reSubmitCode2=Double.parseDouble(req.getParameter("reSubmitCode"));
			
			try{
				if(reSubmitCode == reSubmitCode2){
					
					String name=req.getParameter("name").trim();
					if(name.equals("")){
						name="賴吉盛";
						errMsgs.add("請輸入姓名");
					}
					
					String acc=req.getParameter("acc").trim();//AJAX 檢查相同帳號
					if(acc.equals("")){
						acc="123456";
						errMsgs.add("請輸入帳號");
					}
					
					String pwd=codeGenerator();
//					String pwd=req.getParameter("pwd").trim();
//					if(pwd.equals("")){
//						errMsgs.add("請輸入密碼");
//					}
					
					String sex=req.getParameter("sex");
					if(sex == null){
						sex="1";
						errMsgs.add("請選取性別");
					}
					
					String phone=req.getParameter("phone").trim();
					if(phone.equals("")){
						phone="0931126000";
						errMsgs.add("請輸入電話");
					}
					
					String email=req.getParameter("email").trim();
					if(email.equals("")){
						email="s80091901@gmail.com";
						errMsgs.add("請輸入電子信箱");
					}
					
					Date birth=null;
					try {
						birth=Date.valueOf(req.getParameter("birth").trim());
					} catch (IllegalArgumentException e) {
						birth=new java.sql.Date(0);
						errMsgs.add("請輸入日期");
					}
					
//					HttpSession session=req.getSession();
					byte[] photo=(byte[])session.getAttribute("photo");
					Part part =req.getPart("photo");
					if(part.getSize() > 0){
						InputStream in=part.getInputStream();
						photo=new byte[in.available()];
						in.read(photo);
						in.close();
					}
					
					String[] auth;
					Map<Integer, String> authMap=null;
					auth=req.getParameterValues("auth");
					if(auth != null){
						authMap=new HashMap();
						for(String s:auth){
							authMap.put(Integer.parseInt(s), "1");
						}
					}
				
					
					EmpVO empVO=new EmpVO();
					empVO.setName(name);
					empVO.setAcc(acc);
//					empVO.setPwd(pwd);
					empVO.setSex(sex);
					empVO.setPhone(phone);
					empVO.setEmail(email);
					empVO.setBirth(birth);
					empVO.setPhoto(photo);
					
					if(!errMsgs.isEmpty()){
						session.setAttribute("photo", photo);
						req.setAttribute("empVO", empVO);
						req.setAttribute("authMap", authMap);
						RequestDispatcher failureView=req.getRequestDispatcher(reqSourceURL + "?action=insertView");
						failureView.forward(req, res);
						return;
					}
					
					EmpService sv=new EmpService();
					empVO=sv.addEmp(name, acc, pwd, sex, birth, phone, email, photo);
					Integer empno=sv.getEmpno(acc);
					AuthService sv2=new AuthService();
					sv2.insertOne(empno, auth);
					session.removeAttribute("reSubmitCode");
					new MailService().sendMail(email, "JBZ員工密碼通知", "Hello! " + name + "先生/小姐 請謹記此組帳號及密碼: 帳號: " + acc + " 密碼: " + pwd + "\n" +" (已經啟用)<br>", req.getContextPath());
					req.setAttribute("successMsg", "員工資料新增成功!");
				}
				RequestDispatcher successView=req.getRequestDispatcher(reqSourceURL);
				successView.forward(req, res);
				
			}catch(Exception e){
				errMsgs.add(e.getMessage());
				req.setAttribute("failureMsg", "員工資料新增失敗...");
				RequestDispatcher failureView = req.getRequestDispatcher(reqSourceURL + "?action=insertView");
				failureView.forward(req, res);
			}
			
		}
		
		if("acc_check".equals(action)){
			Boolean accCheck=false;
			String acc=req.getParameter("acc").trim();
			EmpService empSv=new EmpService();
			EmpVO empVO=empSv.getOneByAcc(acc);
			PrintWriter out=res.getWriter();
			if(empVO == null){
				accCheck=true;
				out.print(accCheck);
			}else{
				out.print(accCheck);
			}
		}
		
//		if("insertView".equals(action)){
//			RequestDispatcher successView=req.getRequestDispatcher("/back-end/index_emp.jsp");
//			successView.forward(req, res);
//		}
	}
	
	public String codeGenerator(){
		StringBuffer code=new StringBuffer(); 
		Random r=new Random();
		while(code.length() < 9){
			int number=r.nextInt(10);//0-9
			char upper=(char)(r.nextInt(26) + 65);//65-90
			char lower=(char)(r.nextInt(26) + 97);//97-122
			code.append(number).append(upper).append(lower);
		}
		return code.toString();
	}

}
